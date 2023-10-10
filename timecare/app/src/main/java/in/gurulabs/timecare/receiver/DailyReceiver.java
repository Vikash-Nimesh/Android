package in.gurulabs.timecare.receiver;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import in.gurulabs.timecare.database.TimeTable;
import in.gurulabs.timecare.database.TimeTableRoomDatabase;
import in.gurulabs.timecare.service.AlarmService;
import in.gurulabs.timecare.utils.NotificationUtils;
import in.gurulabs.timecare.utils.PrefManager;
import in.gurulabs.timecare.utils.Tools;

public class DailyReceiver extends BroadcastReceiver {

    private static final String TAG = "DailyReceiver";

    PrefManager sharedPref;

    @Override
    public void onReceive(Context context, Intent intent) {

        scheduleAlarms(context, intent);

    }


    private void scheduleAlarms(Context context, Intent intent) {

        sharedPref=new PrefManager(context);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                TimeTable timeTable = TimeTableRoomDatabase.getInstance(context).timeTableDao().findTimeTableById(intent.getIntExtra("id", 0));


                Calendar currentCalendar = Calendar.getInstance();
                Calendar customCalendar = Calendar.getInstance();
                customCalendar.setTimeInMillis(timeTable.getAlarm_notify_time());
                long alarmNotifyTime = customCalendar.getTimeInMillis();

                if (customCalendar.before(currentCalendar)){
                    alarmNotifyTime += AlarmManager.INTERVAL_DAY * 7;
                }

                Log.e("TAG",String.valueOf(alarmNotifyTime));

                timeTable.setAlarm_notify_time(alarmNotifyTime);

                Tools.setRepeatingAlarm(context, timeTable.getUid(), alarmNotifyTime);

                TimeTableRoomDatabase.getInstance(context).timeTableDao().updateTimeTable(timeTable);



                if(timeTable.getNotificationChecked()){

                    if(timeTable.getNotificationPriority()==0){
                        if(sharedPref.getNotification()){
                            NotificationUtils.sendNotification(context,timeTable.getUid(),timeTable.getTitle(),timeTable.getDescription(),timeTable.getNotificationPriority());

                        }

                    }else if(timeTable.getNotificationPriority()==1) {
                        if(sharedPref.getNotification()){
                            startAlarmService(context,timeTable.getUid(), timeTable.getTitle(), timeTable.getDescription());

                        }

                    }

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
