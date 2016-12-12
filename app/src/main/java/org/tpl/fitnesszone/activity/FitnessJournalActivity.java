package org.tpl.fitnesszone.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.tpl.fitnesszone.R;

public class FitnessJournalActivity extends AppCompatActivity {

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

    public void saveFitnessJournal(MenuItem item) {
        // Save fitness goal & starting weight
    }


}
