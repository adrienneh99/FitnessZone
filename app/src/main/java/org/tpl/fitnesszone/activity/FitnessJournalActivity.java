package org.tpl.fitnesszone.activity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import org.tpl.fitnesszone.R;
import org.tpl.fitnesszone.model.FitnessJournal;
import java.io.FileOutputStream;

public class FitnessJournalActivity extends AppCompatActivity {

    private FitnessJournal userFitnessJournal = new FitnessJournal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_journal);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.fitness_journal);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fitness_journal, menu);
        return true;
    }

    // Get the user fitness goal and starting weight and assign them to the userFitnessJournal
    public void setUserFitnessJournal() {
        EditText userFitnessGoal = (EditText) findViewById(R.id.user_fitness_goal);
        if (userFitnessGoal.getText() != null) {
            userFitnessJournal.setFitnessGoal(userFitnessGoal.getText().toString());
        }

        EditText userStartingWeight = (EditText) findViewById(R.id.user_starting_weight);
        if (userStartingWeight.getText() != null) {
            userFitnessJournal.
                    setStartingWeight(Integer.parseInt(userStartingWeight.getText().toString()));
        }
    }

    // onClick menu item method to save the user's fitness goal and starting weight
    public void saveFitnessJournal(MenuItem item) {

        setUserFitnessJournal();

        // Create a Gson object to handle the Java to JSON serialization
        Gson gson = new Gson();

        // Convert the userFitnessJournal object to JSON
        String fitnessJournalJson = gson.toJson(userFitnessJournal);

        // Save the fitnessJournal JSON to the internal storage
        String filename = "fitness_journal";
        FileOutputStream fileOutputStream;

        try {
            fileOutputStream = openFileOutput(filename,Context.MODE_PRIVATE);
            fileOutputStream.write(fitnessJournalJson.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CharSequence text = "Fitness goal saved as: " + userFitnessJournal.getFitnessGoal() +
                "\n" +
                "\n" +
                "Starting weight saved as: " + userFitnessJournal.getStartingWeight();
        int duration = Toast.LENGTH_LONG;

        Toast.makeText(this, text, duration).show();
    }

}
