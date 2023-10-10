package in.gurulabs.timecare.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Objects;


import in.gurulabs.timecare.R;
import in.gurulabs.timecare.activity.MainActivity;


public class NotificationUtils {

    private static final String CHANNEL_ID = "ReminderDefault";
    private static final String CHANNEL_NAME = "Event Default";
    private static final String CHANNEL_DESC = "This is Default Event Notification Channel";


    private static void createNotificationChannel(@NonNull Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            channel.enableLights(true);
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);
            channel.setVibrationPattern(new long[]{0, 400, 200, 400});
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    public static void sendNotification(@NonNull Context context, int id, String title, String desc, int priority) {

        createNotificationChannel(context);


        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, id,
                notificationIntent, 0);


        //1.Create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active)
                .setContentTitle(title)
                .setContentText(desc)
                .setPriority(priority)
                .setVibrate(new long[]{0, 400, 200, 400})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(intent)
                .setAutoCancel(true);

        //step 3
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        //step 4
        notificationManagerCompat.notify(id, builder.build());
    }




}
