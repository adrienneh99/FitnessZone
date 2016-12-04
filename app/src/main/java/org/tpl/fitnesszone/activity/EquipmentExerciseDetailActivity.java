package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.EquipmentExercise;

public class EquipmentExerciseDetailActivity extends AppCompatActivity {

    // Declare an equipmentExercise object
    EquipmentExercise equipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_exercise_detail);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.fitness_equipment_exercises);
        setSupportActionBar(fzToolbar);

        Intent intent = getIntent();
        EquipmentExercise equipment = (EquipmentExercise) intent.getParcelableExtra("id");
    }


}
