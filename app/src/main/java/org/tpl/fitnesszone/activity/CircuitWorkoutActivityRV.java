package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.os.Bundle;
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
        //Mike added this:
        String circuitArm = intent.getExtras().getString("circuit_arm");
        //Mike Commented out this:
//        String circuitArm = intent.getExtras().get("circuit_arm");


        // ***NEED TO UPDATE TOOLBAR TITLE DEPENDING ON WHETHER ARM, LEG OR CARDIO ARE CLICKED***
        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.cardio_workout);
        setSupportActionBar(fzToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.circuit_workout_recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData(circuitArm);
        initializeAdapter();
    }
    //Mike changed this method to accept the string parameter for the json filename:
    private void initializeData(String jsonFilename) {
        // Loading the circuit info from the json file in the raw resource folder
        Gson gson = new Gson();

        //Mike added this, it changed the  like the follow it and is commented out:
        int rawResourceID = ResourceUtils.getResourceId(jsonFilename, R.raw.class);
        InputStream inputStream = getResources().openRawResource(rawResourceID);
//        InputStream inputStream = getResources().openRawResource(R.raw.circuit_cardio);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        circuits = gson.fromJson(rd, Circuit[].class);
    }

    private void initializeAdapter() {
        adapter = new CircuitWorkoutRVAdapter(circuits);
        recyclerView.setAdapter(adapter);
    }
}
