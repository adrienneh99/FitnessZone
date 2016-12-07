package org.tpl.fitnesszone.activity;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.fragment.DatePickerFragment;

public class WorkoutTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_tracker);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.daily_workout_tracker);
        setSupportActionBar(fzToolbar);
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


}
