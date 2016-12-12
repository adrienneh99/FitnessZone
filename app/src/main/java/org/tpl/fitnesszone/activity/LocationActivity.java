package org.tpl.fitnesszone.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.Location;
import org.tpl.fitnesszone.util.PermissionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class LocationActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    //Request code for location permission request.
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    //Flag indicating whether a requested permission has been denied after returning in
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;
    private Location[] locations;
    private LatLngBounds.Builder boundsBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.find_a_fz);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Loading the locations from the json file in the raw resource folder
        Gson gson = new Gson();
        InputStream inputStream = getResources().openRawResource(R.raw.locations);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        locations = gson.fromJson(rd, Location[].class);
    }

    // Load up all the locations, create bounds that include all map locations, add a marker for
    // each location, and center the camera within the bounds.
    private void setUpMap() {
        boundsBuilder = new LatLngBounds.Builder();

        for (Location location:locations) {
            LatLng latLng = new LatLng(location.getLat(), location.getLng());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location.getName()));
            boundsBuilder.include(latLng);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(boundsBuilder.build().getCenter(), 15));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
        setUpMap();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
    }

    // Enables the My Location layer if the fine location permission has been granted
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
        else if (mMap != null) {
            // Access to the location has been granted to the app
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();

        // Return false so we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position)
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE){
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableMyLocation();
        }
        else {
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission wasn't granted, display error dialog
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true).show(getSupportFragmentManager(),
                "dialog");
    }

}
