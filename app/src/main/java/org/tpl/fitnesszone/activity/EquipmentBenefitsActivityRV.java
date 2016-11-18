package org.tpl.fitnesszone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.adapter.RVAdapter;
import org.tpl.fitnesszone.model.ExerciseEquipment;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class EquipmentBenefitsActivityRV extends AppCompatActivity {

    private ExerciseEquipment[] exerciseEquipments;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_equipment_benefits_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.equipment_benefits_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData() {

        // Loading the equipment info from the json file in the raw resource folder
        Gson gson = new Gson();
        InputStream inputStream = getResources().openRawResource(R.raw.equipment);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        exerciseEquipments = gson.fromJson(rd, ExerciseEquipment[].class);
    }

    private void initializeAdapter() {
        adapter = new RVAdapter(exerciseEquipments);
        recyclerView.setAdapter(adapter);
    }
}
