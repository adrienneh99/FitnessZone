package org.tpl.fitnesszone.activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.tpl.fitnesszone.R;

public class SplashActivity extends AppCompatActivity {

    // Duration of wait in milliseconds where 1000 = 1 second
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Handler to start HomeActivity and close SplashActivity after 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // An intent to start the HomeActivity
                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
