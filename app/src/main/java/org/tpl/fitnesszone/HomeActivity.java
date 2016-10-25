package org.tpl.fitnesszone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        setSupportActionBar(fzToolbar);
    }

//    Called when the user clicks the Find a Fitness Zone Button
    public void openLocationUI(View view){
        Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
        startActivity(intent);
    }

    //    Called when the user clicks the Health Benefits Button
    public void openHealthBenefitsUI(View view){
        Intent intent = new Intent(HomeActivity.this, HealthBenefitsActivity.class);
        startActivity(intent);
    }

    //    Called when the user clicks the My Workout Button
    public void openMyWorkoutUI(View view){
        Intent intent = new Intent(HomeActivity.this, MyWorkoutActivity.class);
        startActivity(intent);
    }

    //    Called when the user clicks the Fitness Journal Button
    public void openFitnessJournalUI(View view){
        Intent intent = new Intent(HomeActivity.this, FitnessJournalActivity.class);
        startActivity(intent);
    }

    //    Called when the user clicks the About Button
    public void openAboutUI(View view){
        Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(intent);
    }

}
