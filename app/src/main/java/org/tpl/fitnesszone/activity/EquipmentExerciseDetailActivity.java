package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.EquipmentExercise;
import org.tpl.fitnesszone.util.ResourceUtils;

public class EquipmentExerciseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_exercise_detail);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.fitness_equipment_exercises);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        EquipmentExercise equipmentExercise = (EquipmentExercise) intent.getParcelableExtra("id");

        TextView exerciseName = (TextView)findViewById(R.id.exercise_name);
        ImageView equipmentImage = (ImageView)findViewById(R.id.equipment_image);
        TextView activityTypeLabel = (TextView)findViewById(R.id.activity_type_label);
        TextView exerciseActivityType = (TextView)findViewById(R.id.exercise_activity_type);
        TextView exerciseActivityTypeDetail = (TextView)findViewById(R.id.exercise_activity_type_detail);
        TextView musclesUsedLabel = (TextView)findViewById(R.id.muscles_used_label);
        TextView musclesUsedDetail = (TextView)findViewById(R.id.muscles_used_detail);
        TextView healthBenefitsLabel = (TextView)findViewById(R.id.health_benefits_label);
        TextView healthBenefitsDetail = (TextView)findViewById(R.id.health_benefits_detail);
        TextView instructionsLabel = (TextView)findViewById(R.id.instructions_label);
        TextView instructionsDetail = (TextView)findViewById(R.id.instructions_detail);
        TextView exerciseTipsLabel = (TextView)findViewById(R.id.exercise_tips_label);
        TextView exerciseTipsDetail = (TextView)findViewById(R.id.exercise_tips_detail);

        exerciseName.setText(equipmentExercise.getEquipmentName());

        equipmentImage.setImageResource(ResourceUtils.getResourceId(equipmentExercise.getImageName(), R.drawable.class));

        activityTypeLabel.setText(R.string.type_of_activity);

        exerciseActivityType.setText(equipmentExercise.getActivityType());

        String activityTypeDetail = "";
        for (int i=0; i<equipmentExercise.getActivityTypeDetail().length; i++){
            activityTypeDetail += equipmentExercise.getActivityTypeDetail()[i];
            activityTypeDetail += System.getProperty("line.separator");
        }
        exerciseActivityTypeDetail.setText(activityTypeDetail);

        musclesUsedLabel.setText(R.string.muscles_used);

        String musclesUsed = "";
        for (int i=0; i<equipmentExercise.getMusclesUsed().length; i++){
            musclesUsed += equipmentExercise.getMusclesUsed()[i];
            musclesUsed += System.getProperty("line.separator");
        }
        musclesUsedDetail.setText(musclesUsed);

        healthBenefitsLabel.setText(R.string.health_benefits);

        String healthBenefits = "";
        for (int i=0; i<equipmentExercise.getHealthBenefits().length; i++){
            healthBenefits += equipmentExercise.getHealthBenefits()[i];
            healthBenefits += System.getProperty("line.separator");
        }
        healthBenefitsDetail.setText(healthBenefits);

        instructionsLabel.setText(R.string.instructions);

        String instructions = "";
        for (int i=0; i<equipmentExercise.getInstructions().length; i++){
            instructions += equipmentExercise.getInstructions()[i];
            instructions += System.getProperty("line.separator");
        }
        instructionsDetail.setText(instructions);

        exerciseTipsLabel.setText(R.string.exercise_tips);

        String exerciseTips = "";
        for (int i=0; i<equipmentExercise.getExerciseTips().length; i++){
            exerciseTips += equipmentExercise.getExerciseTips()[i];
            exerciseTips += System.getProperty("line.separator");
        }
        exerciseTipsDetail.setText(exerciseTips);
    }
}
