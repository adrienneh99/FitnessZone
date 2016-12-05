package org.tpl.fitnesszone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.google.gson.Gson;
import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.adapter.EquipmentExerciseListRVAdapter;
import org.tpl.fitnesszone.model.EquipmentExercise;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class EquipmentExerciseListActivityRV extends AppCompatActivity {

    private EquipmentExercise[] equipmentExercises;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_exercise_list_recyclerview);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.fitness_equipment_and_exercises);
        setSupportActionBar(fzToolbar);

        recyclerView = (RecyclerView) findViewById(R.id.equipment_exercise_list_recyclerview);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        // Loading the equipmentExercise info from the json file in the raw resource folder
        Gson gson = new Gson();
        InputStream inputStream = getResources().openRawResource(R.raw.equipment_exercise);
        Reader rd = new BufferedReader(new InputStreamReader(inputStream));
        equipmentExercises = gson.fromJson(rd, EquipmentExercise[].class);
    }

    private void initializeAdapter() {
        adapter = new EquipmentExerciseListRVAdapter(equipmentExercises);
        recyclerView.setAdapter(adapter);
    }
}
