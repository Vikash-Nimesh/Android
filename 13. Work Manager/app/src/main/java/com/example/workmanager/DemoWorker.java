package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {


    public static final String KEY_TASK_STATUS = "task_status";
    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Data data = getInputData();
        int counterLimit = data.getInt(MainActivity.KEY_COUNTER_VALUE,1000);

        Data dataToSend = new Data.Builder().putString(KEY_TASK_STATUS,"Task Done Successfully").build();


        for (int i = 0;i<counterLimit;i++){
            Log.i("TAGY","Current count :"+i);
        }

        return Result.success(dataToSend);
    }
}
