package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.google.gson.Gson;
import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.adapter.CircuitWorkoutRVAdapter;
import org.tpl.fitnesszone.model.Circuit;
import org.tpl.fitnesszone.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class CircuitWorkoutActivityRV extends AppCompatActivity {

    private Circuit[] circuits;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit_workout_recyclerview);

        Intent intent = getIntent();
        String circuitFilename = intent.getExtras().getString("circuit_type");

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);

        if (circuitFilename.equals("circuit_arm")) {
            fzToolbar.setTitle(R.string.arm_workout);
        }
        else if (circuitFilename.equals("circuit_leg")) {
            fzToolbar.setTitle(R.string.leg_workout);
        }
        else {
            fzToolbar.setTitle(R.string.cardio_workout);
        }

        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.circuit_workout_recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData(circuitFilename);
        initializeAdapter();
    }

    private void initializeData(String jsonFilename) {
        // Loading the circuit info from the json file in the raw resource folder
        Gson gson = new Gson();
        int rawResourceID = ResourceUtils.getResourceId(jsonFilename, R.raw.class);
        InputStream inputStream = getResources().openRawResource(rawResourceID);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        circuits = gson.fromJson(rd, Circuit[].class);
    }

    private void initializeAdapter() {
        adapter = new CircuitWorkoutRVAdapter(circuits);
        recyclerView.setAdapter(adapter);
    }
}
