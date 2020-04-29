package edu.ilstu;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        Notification noti = new Notification.Builder(context).setContentTitle("Alarm is Set").setContentText("Alarm Successfully Set").setSmallIcon(R.mipmap.ic_launcher).build();

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyme")
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Alarm is Set")
//                .setContentText("Alarm Successfully Set");
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);




        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, noti);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
        noti.
        }
    }