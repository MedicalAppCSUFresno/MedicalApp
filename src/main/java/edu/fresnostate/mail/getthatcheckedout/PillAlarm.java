package edu.fresnostate.mail.getthatcheckedout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class PillAlarm extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 3;
    private Button returnCalen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_alarm);

        //Set Onclick Listener.
        findViewById(R.id.alarmbtn1Pill).setOnClickListener(this);
        findViewById(R.id.alarmbtn2Pill).setOnClickListener(this);
        returnCalen = (Button) findViewById(R.id.returnCalen);
        returnCalen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(PillAlarm.this, CalendarActivity.class);
                startActivity(startIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        EditText editText = findViewById(R.id.refillInput);
        TimePicker timePicker = findViewById(R.id.alarmClock3);

        // Set notificationID and text.
        Intent intent = new Intent(PillAlarm.this, AlarmReciever3.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());

        //getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(PillAlarm.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.alarmbtn1Pill:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                //Set Alarm.
                //set(type, milliseconds, intent)
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.alarmbtn2Pill:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Cancelled :(", Toast.LENGTH_SHORT).show();
                break;


        }


    }
}
