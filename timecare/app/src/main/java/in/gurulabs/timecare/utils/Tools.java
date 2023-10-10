/*
 * *
 *  * Created by guruLabs on 12/31/20 6:02 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/31/20 5:53 PM
 *
 */

package in.gurulabs.timecare.utils;

import static android.content.Context.ALARM_SERVICE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import in.gurulabs.timecare.BuildConfig;
import in.gurulabs.timecare.R;
import in.gurulabs.timecare.activity.MainActivity;
import in.gurulabs.timecare.receiver.DailyReceiver;

public class Tools {



    public static String currentVersion = BuildConfig.VERSION_NAME;





    public static String getTimeIn24Format(String time){


        String [] finalFromTime = time.split(":");

        int fromhourIn24 = Integer.parseInt(finalFromTime[0]);
        int fromminute = Integer.parseInt(finalFromTime[1]);

        @SuppressLint("DefaultLocale") String fromConvertedTimeFor24Hr = String.format("%02d", fromhourIn24) +":"+ String.format("%02d", fromminute);

        return  fromConvertedTimeFor24Hr;

    }


    public static String getTimeIn12Format(String time){

        String [] finalFromTime = time.split(":");

        int fromhourIn24 = Integer.parseInt(finalFromTime[0]);
        int fromminute = Integer.parseInt(finalFromTime[1]);

        int fromhourIn12 = fromhourIn24 % 12;
        @SuppressLint("DefaultLocale") String fromConvertedTimeFor12Hr = String.format("%02d:%02d %s", fromhourIn12 == 0 ? 12 : fromhourIn12,
                fromminute, fromhourIn24 < 12 ? "am" : "pm");

        return fromConvertedTimeFor12Hr;


    }


    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }
    public static void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void showSnackWithButton(View view, String title) {
        Snackbar.make(view, title, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
    }


    public static void showBackupCreatedSnackBar(View view, String title, Context context) {
        Snackbar.make(view, title, Snackbar.LENGTH_LONG)
               .show();
    }

    public static void showShortLengthSnack(View view, String title) {
        Snackbar.make(view, title, Snackbar.LENGTH_SHORT)
                .show();
    }

    public static void showSnackWithFinish(Activity activity,View view, String title) {
        Snackbar.make(view, title, Snackbar.LENGTH_LONG)
                .show();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                activity.finish(); // kill current activity
            }
        };
        new Timer().schedule(task, 1000);

    }



    public static void setRepeatingAlarm(@NonNull Context context, int id,long alarmNotifyTime) {

        // cancel already scheduled reminders
        cancelAlarm(context, id);


        // Enable a receiver
        ComponentName receiver = new ComponentName(context, DailyReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(context, DailyReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        intent.putExtra("id", id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager == null)
            return;


        if (Build.VERSION.SDK_INT < 23) {
            if (Build.VERSION.SDK_INT >= 19) {
                if (System.currentTimeMillis() < alarmNotifyTime)
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmNotifyTime, pendingIntent);
            } else {
                if (System.currentTimeMillis() < alarmNotifyTime)
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alarmNotifyTime, pendingIntent);
            }
        } else {
            if (System.currentTimeMillis() < alarmNotifyTime)
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmNotifyTime, pendingIntent);
        }

       // Log.e("TIME 2", String.valueOf(alarmNotifyTime));
    }

    public static void cancelAlarm(@NonNull Context context, int id) {
        Intent intent = new Intent(context, DailyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Objects.requireNonNull(am).cancel(pendingIntent);
        pendingIntent.cancel();
    }
    public static void cancelNotification(Context ctx, int notifyId) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) ctx.getSystemService(ns);
        nMgr.cancel(notifyId);
    }


}
