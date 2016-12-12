package org.tpl.fitnesszone.activity;

import android.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.google.gson.Gson;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.fragment.DatePickerFragment;
import org.tpl.fitnesszone.model.EquipmentExercise;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class WorkoutTrackerActivity extends AppCompatActivity {

    private EquipmentExercise[] equipmentExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_tracker);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.daily_workout_tracker);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        initializeData();

        // Supply the spinner with the array using an instance of ArrayAdapter
        Spinner spinner1 = (Spinner)findViewById(R.id.exercise_spinner1);
        Spinner spinner2 = (Spinner)findViewById(R.id.exercise_spinner2);
        Spinner spinner3 = (Spinner)findViewById(R.id.exercise_spinner3);
        Spinner spinner4 = (Spinner)findViewById(R.id.exercise_spinner4);
        Spinner spinner5 = (Spinner)findViewById(R.id.exercise_spinner5);


        ArrayList<String> equipmentExerciseNames = new ArrayList<String>();
        for (int i = 0; i < equipmentExercises.length; i++) {
            equipmentExerciseNames.add(equipmentExercises[i].getExerciseName());
        }

        // Creating an ArrayAdapter using the string array and default simple spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.
                simple_spinner_dropdown_item, equipmentExerciseNames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);

    }

    // Method to show the DatePickerDialog when the user clicks the Date button
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    // Method to handle the click events for the arm, leg, and cardio checkboxes
    public void onCheckboxClicked(View view) {

        // Determine if the checkbox is clicked
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked...
        if (checked && (view.getId() == R.id.arms)){
            // **POPULATE THE EXERCISE SPINNER WITH ALL ARM CIRCUIT EXERCISES**
        }
        else if (checked && (view.getId() == R.id.legs)) {
            // **POPULATE THE EXERCISE SPINNER WITH ALL LEG CIRCUIT EXERCISES**
        }
        else if (checked){
            // **POPULATE THE EXERCISE SPINNER WITH ALL CARDIO CIRCUIT EXERCISES**
        }
    }

    private void initializeData() {
        // Loading the equipmentExercise info from the json file in the raw resource folder
        Gson gson = new Gson();
        InputStream inputStream = getResources().openRawResource(R.raw.equipment_exercise);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        equipmentExercises = gson.fromJson(rd, EquipmentExercise[].class);
    }

    String FILENAME = "workout_tracker_file";
    String string = "workout_tracker";

    // Method to handle the click event for the save button
    public void saveWorkoutTrackerInfo(View view) {


    }








}
