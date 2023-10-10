package in.gurulabs.timecare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.service.AlarmService;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);


        MaterialTextView subjectNameTextView = findViewById(R.id.subjectNameTextView);
        MaterialTextView subjectDescTextView = findViewById(R.id.subjectDescTextView);

        subjectNameTextView.setText(getIntent().getStringExtra("title"));
        subjectDescTextView.setText(getIntent().getStringExtra("desc"));



        MaterialButton openAppButton = findViewById(R.id.open_app);
        openAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm();
                Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        MaterialButton stopButton = findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAlarm();
                finish();

            }
        });


    }

    void stopAlarm(){
        Intent intentService = new Intent(AlarmActivity.this, AlarmService.class);
        stopService(intentService);

    }


}