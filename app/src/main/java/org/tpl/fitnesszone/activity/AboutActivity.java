package org.tpl.fitnesszone.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.tpl.fitnesszone.R;

public class AboutActivity extends AppCompatActivity {

    ImageButton tplButton;
    ImageButton cultivateHealthButton;
    ImageButton regisButton;
    Button developerBioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar fzToolbar = (Toolbar) findViewById(R.id.fz_toolbar);
        fzToolbar.setTitle(R.string.about_my_fz_app);
        setSupportActionBar(fzToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        tplButton = (ImageButton) findViewById(R.id.tpl_button);
        tplButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.tpl.org/our-work/parks-for-people/fitness-zone-area%C2%AE"));
                startActivity(browserIntent);
            }
        });

        cultivateHealthButton = (ImageButton) findViewById(R.id.cultivate_health_button);
        cultivateHealthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.regis.edu/Community-Gateway/Cultivate-Health"));
                startActivity(browserIntent);
            }
        });

        regisButton = (ImageButton) findViewById(R.id.regis_university_button);
        regisButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.regis.edu/"));
                startActivity(browserIntent);
            }
        });

        developerBioButton = (Button) findViewById(R.id.developer_button);
        developerBioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.linkedin.com/in/adriennehelmsmajor"));
                startActivity(browserIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_about, menu);
        return true;
    }

}
