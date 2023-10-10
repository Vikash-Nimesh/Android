package in.gurulabs.timecare.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.utils.Tools;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        Tools.setSystemBarLight(this);
        Tools.setSystemBarColor(this,R.color.white);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startActivityMainDelay();

    }


    private void startActivityMainDelay() {
        // Show splash screen for 2 seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish(); // kill current activity
            }
        };
        new Timer().schedule(task, 2000);
    }

}