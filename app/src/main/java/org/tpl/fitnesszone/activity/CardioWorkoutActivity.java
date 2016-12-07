package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.Circuit;
import org.tpl.fitnesszone.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class CardioWorkoutActivity extends AppCompatActivity {

    private Circuit[] circuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_workout);

//        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
//        fzToolbar.setTitle(R.string.cardio_workout);
//        setSupportActionBar(fzToolbar);
//
//        initializeData();
//
//        TextView circuitAction = (TextView)findViewById(R.id.circuit_action);
//        TextView exerciseNameLabel = (TextView)findViewById(R.id.exercise_name_label);
//        TextView circuitExerciseName = (TextView)findViewById(R.id.circuit_exercise_name);
//        TextView repetitionsLabel = (TextView)findViewById(R.id.repetitions_label);
//        TextView circuitRepetitions = (TextView)findViewById(R.id.circuit_repetitions);
//        TextView setsLabel = (TextView)findViewById(R.id.sets_label);
//        TextView circuitSets = (TextView)findViewById(R.id.circuit_sets);
//        TextView restBetweenSetsLabel = (TextView)findViewById(R.id.rest_between_sets_label);
//        TextView circuitRestBetweenSets = (TextView)findViewById(R.id.circuit_rest_between_sets);
//
//        circuitAction.setText(circuit.getAction());
//        exerciseNameLabel.setText(R.string.exercise_name);
//        circuitExerciseName.setText(circuit.getExerciseName());
//        repetitionsLabel.setText(R.string.repetitions);
//        circuitRepetitions.setText(circuit.getRepetitions());
//        setsLabel.setText(R.string.sets);
//        circuitSets.setText(circuit.getSets());
//        restBetweenSetsLabel.setText(R.string.rest_between_sets);
//        circuitRestBetweenSets.setText(circuit.getRestBetweenSets());

    }

//    private void initializeData() {
//        // Loading the cardio circuit info from the json file in the raw resource folder
//        Gson gson = new Gson();
//        InputStream inputStream = getResources().openRawResource(R.raw.circuit_cardio);
//        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
//        circuit = gson.fromJson(rd, Circuit[].class);
//    }
}
