package in.gurulabs.timecare.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import in.gurulabs.timecare.service.AlarmService;

public class AlarmStopReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentService = new Intent(context, AlarmService.class);
        context.stopService(intentService);
    }
}