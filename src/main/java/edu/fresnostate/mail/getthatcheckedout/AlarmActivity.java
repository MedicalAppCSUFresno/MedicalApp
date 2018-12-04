package edu.fresnostate.mail.getthatcheckedout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //Set Onclick Listener.
        findViewById(R.id.alarmbtn1).setOnClickListener(this);
        findViewById(R.id.alarmbtn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText editText = findViewById(R.id.medicationInput);
        TimePicker timePicker = findViewById(R.id.alarmClock);

        // Set notificationID and text.
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());

        //getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.alarmbtn1:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

        }


    }
}
