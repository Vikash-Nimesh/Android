package in.gurulabs.timecare.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


import java.util.Objects;

import in.gurulabs.timecare.R;
import in.gurulabs.timecare.activity.AlarmActivity;
import in.gurulabs.timecare.receiver.AlarmStopReceiver;


public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    final String CHANNEL_ID_LOUD = "ReminderHigh";
    final String CHANNEL_NAME_LOUD = "Alarm";
    final String CHANNEL_DESC_LOUD = "This is Alarm Notification Channel";

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        mediaPlayer.setLooping(true);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int id = intent.getIntExtra("id",1);
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");


        Intent notificationIntent = new Intent(this, AlarmActivity.class);
        notificationIntent.putExtra("title",title);
        notificationIntent.putExtra("desc",desc);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, id, notificationIntent, 0);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID_LOUD, CHANNEL_NAME_LOUD, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESC_LOUD);
            channel.enableLights(true);
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);
            channel.setVibrationPattern(new long[]{0, 400, 200, 400});
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }


        Intent stopAlarmIntent = new Intent(this, AlarmStopReceiver.class);
        PendingIntent stopAlarmPendingIntent = PendingIntent.getBroadcast(this, id, stopAlarmIntent, 0);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID_LOUD)
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_baseline_access_time,getString(R.string.stop),stopAlarmPendingIntent)
                .build();

        mediaPlayer.start();

        long[] pattern = { 0, 100, 1000 };
        vibrator.vibrate(pattern, 0);

        startForeground(id, notification);

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        vibrator.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
