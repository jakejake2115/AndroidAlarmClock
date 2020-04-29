package edu.ilstu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView t1;
    TimePicker alarmTime;
    TextView textView;
    int mHour;
    int mMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.button);
        TextView t1 = findViewById(R.id.title);
        TextView textView = findViewById(R.id.textView);
        TimePicker alarmTime = findViewById(R.id.timePicker);

        createNotificationChannel();

        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        alarmTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener(){
            @Override
            public void onTimeChanged(TimePicker view,int hourOfDay,int minute){
                mHour = hourOfDay;
                mMin = minute;
//                textView.setText(textView.getText().toString()+ " "+ mHour + ":" + mMin);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Alarm Set", Toast.LENGTH_LONG).show();
                setTimer(v);



            }
        });

    }

    public void setTimer(View v){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Date date = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();

        cal_now.setTime(date);
        cal_alarm.setTime(date);

        cal_alarm.set(Calendar.HOUR_OF_DAY,mHour);
        cal_alarm.set(Calendar.MINUTE,mMin);
        cal_alarm.set(Calendar.SECOND,0);


        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }

        Intent i = new Intent(MainActivity.this,MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,24444,i,0);
        alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent);


    }

    private void createNotificationChannel(){
        CharSequence name = "AlarmNotificationChannel";
        String description = "Channel for One-Time Alarms";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notifyme",name,importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }



}


















//    public String AlarmTime(){
//
//        Integer alarmHours = alarmTime.getHour();
//        Integer alarmMinutes = alarmTime.getMinute();
//        String stringAlarmMinutes;
//
//        if (alarmMinutes<10){
//            stringAlarmMinutes = "0";
//            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
//        }else{
//            stringAlarmMinutes = alarmMinutes.toString();
//        }
//
//        String stringAlarmTime;
//
//        if(alarmHours>12){
//            alarmHours = alarmHours-12;
//            stringAlarmTime = alarmHours.toString().concat(":");
//            stringAlarmTime.concat(stringAlarmMinutes).concat(" PM");
//        }else{
//            stringAlarmTime = alarmHours.toString().concat(":");
//            stringAlarmTime.concat(stringAlarmMinutes).concat(" AM");
//        }
//        return stringAlarmTime;
//    }
//}

