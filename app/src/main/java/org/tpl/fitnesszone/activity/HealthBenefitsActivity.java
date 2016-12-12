package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.tpl.fitnesszone.R;

public class HealthBenefitsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_benefits);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.health_benefits);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    // Called when the user clicks the Fitness Equipment Benefits Button
    public void openEquipmentBenefitsUI(View view){
        Intent intent = new Intent(HealthBenefitsActivity.this, EquipmentExerciseListActivityRV.class);
        startActivity(intent);
    }


}
