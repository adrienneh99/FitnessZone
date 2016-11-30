package org.tpl.fitnesszone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import org.tpl.fitnesszone.R;


public class EquipmentBenefitsActivityCV extends AppCompatActivity {

    ImageView equipmentImage;
    TextView exerciseName;
    TextView activityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_benefits_cardview);

        equipmentImage = (ImageView)findViewById(R.id.equipment_image);
        exerciseName = (TextView)findViewById(R.id.exercise_name);
        activityType = (TextView)findViewById(R.id.activity_type);
    }
}
