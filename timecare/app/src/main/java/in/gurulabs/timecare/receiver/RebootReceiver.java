package in.gurulabs.timecare.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.List;

import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableRoomDatabase;
import in.gurulabs.timecare.service.AlarmService;
import in.gurulabs.timecare.utils.Tools;

public class RebootReceiver extends BroadcastReceiver {

    private static final String TAG = "RebootReceiver";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

               Log.e(TAG,"ANDAR AAYA");

                scheduleRebootAlarms(context);

            }

        }

    }




    private void scheduleRebootAlarms(Context context) {

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<TimeTable> l = TimeTableRoomDatabase.getInstance(context).timeTableDao().findNotificationSent();

                for(TimeTable timeTable :l){


                    Tools.setRepeatingAlarm(context,timeTable.getUid(),timeTable.getAlarm_notify_time());
                   // Log.e(TAG, "ID : " + timeTable.getUid() + " TIME : " + timeTable.getAlarm_notify_time());
                }


            }
        });
        thread.start();
    }

    private void startAlarmService(Context context, int id,  String title, String desc) {
        Intent intentService = new Intent(context, AlarmService.class);
        intentService.putExtra("id", id);
        intentService.putExtra("title", title);
        intentService.putExtra("desc", desc);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

}
