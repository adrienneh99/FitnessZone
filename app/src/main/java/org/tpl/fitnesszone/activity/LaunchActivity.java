package org.tpl.fitnesszone.activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.tpl.fitnesszone.R;

public class LaunchActivity extends AppCompatActivity {

    // Duration of wait in milliseconds where 1000 = 1 second
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        // Handler to start HomeActivity and close LaunchActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // An intent to start the HomeActivity
                Intent intent = new Intent(LaunchActivity.this,HomeActivity.class);
                LaunchActivity.this.startActivity(intent);
                LaunchActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
