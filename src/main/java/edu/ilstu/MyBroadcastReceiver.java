package edu.ilstu;

import android.app.AlertDialog;
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

        Notification noti = new Notification.Builder(context).setContentTitle("Alarm Firing").setContentText("One-Time Alarm").setSmallIcon(R.mipmap.ic_launcher).build();




        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyme")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm Firing")
                .setContentText("One-Time Alarm");

//        AlertDialog dialog = new AlertDialog.Builder(context)
//                .setTitle("Alarm Firing")
//                .setMessage("One-Time Alarm")
//                .setNegativeButton("Dismiss",null)
//                .show();


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(200,builder.build());


        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, noti);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
        }
    }