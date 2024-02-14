package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    public static final String KEY_COUNTER_VALUE = "key_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*
         *   Working with Constraints
         *   Running the worker under certain conditions: Device is connected to WiFi, or Battery is above 50% ....
         */
        Constraints constraints = new Constraints.Builder().setRequiresCharging(true).build();


        /*
         * Sending and Receiving Data using Work Manager
         *
         *
         *
         *
         * */

        Data data = new Data.Builder().putInt(KEY_COUNTER_VALUE,300).build();



        WorkRequest countWorkRequest = new OneTimeWorkRequest.Builder(DemoWorker.class).setConstraints(constraints).setInputData(data).build();


        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkManager.getInstance(getApplicationContext()).enqueue(countWorkRequest);
            }
        });


        //checking the state of work manager
        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(countWorkRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if (workInfo!=null){
                    Toast.makeText(MainActivity.this, workInfo.getState().name(), Toast.LENGTH_SHORT).show();
                }

                if(workInfo.getState().isFinished()){
                    Data data1 = workInfo.getOutputData();
                    Toast.makeText(MainActivity.this, data1.getString(DemoWorker.KEY_TASK_STATUS), Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}