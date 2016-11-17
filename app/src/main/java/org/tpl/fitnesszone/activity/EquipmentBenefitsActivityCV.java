package org.tpl.fitnesszone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.tpl.fitnesszone.R;

public class EquipmentBenefitsActivityCV extends AppCompatActivity {

    ImageView equipmentImage;
    TextView equipmentName;
    TextView activityType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_benefits_cardview);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.fitness_equipment_benefits);
        setSupportActionBar(fzToolbar);

        equipmentImage = (ImageView)findViewById(R.id.equipment_image);
        equipmentName = (TextView)findViewById(R.id.equipment_name);
        activityType = (TextView)findViewById(R.id.activity_type);
    }
}
