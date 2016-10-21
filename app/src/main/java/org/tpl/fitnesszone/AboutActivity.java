package org.tpl.fitnesszone;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView developerView;

    ImageButton tplButton;
    ImageButton cultivateHealthButton;
    ImageButton regisButton;
    Button developerBioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

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
                        Uri.parse("www.linkedin.com/in/adrienne-major-593a589"));
                startActivity(browserIntent);
            }
        });

    }
}
