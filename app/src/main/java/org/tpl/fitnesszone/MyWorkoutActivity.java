package org.tpl.fitnesszone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MyWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workout);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.my_workout);
        setSupportActionBar(fzToolbar);
    }

    // Called when the user clicks the Cardio Workout Button
    public void openCardioWorkoutUI(View view){
        Intent intent = new Intent(MyWorkoutActivity.this, CardioWorkoutActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks the Arm Workout Button
    public void openArmWorkoutUI(View view){
        Intent intent = new Intent(MyWorkoutActivity.this, ArmWorkoutActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks the Leg Workout Button
    public void openLegWorkoutUI(View view){
        Intent intent = new Intent(MyWorkoutActivity.this, LegWorkoutActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks the Fitness Journal Button
    public void openFitnessJournalUI(View view){
        Intent intent = new Intent(MyWorkoutActivity.this, FitnessJournalActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks the Daily Workout Tracker Button
    public void openWorkoutTrackerUI(View view){
        Intent intent = new Intent(MyWorkoutActivity.this, WorkoutTrackerActivity.class);
        startActivity(intent);
    }

}
