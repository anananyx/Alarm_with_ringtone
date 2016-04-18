package com.example.yuexiao.alarm_with_ringtone;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Calendar cal=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnsetalarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();


            }
        });


    }



    private void setAlarm(){
        int hour=((TimePicker) findViewById(R.id.timepicker)).getCurrentHour();
        int min=((TimePicker)findViewById(R.id.timepicker)).getCurrentMinute();


        Toast.makeText(this,hour+":"+min,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,sender);



    }

}
