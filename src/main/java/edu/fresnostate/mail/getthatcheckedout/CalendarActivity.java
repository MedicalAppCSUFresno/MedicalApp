package edu.fresnostate.mail.getthatcheckedout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private Button returnHome;
    private Button pillReminder;
    private Button docAppointment;
    private Button refillReminder;
    CalendarView calendarView;
    TextView myDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);
            }
        });

        returnHome = (Button) findViewById(R.id.returnHome);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHomePage();
            }
        });

        pillReminder = (Button) findViewById(R.id.pillReminder);
        pillReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(CalendarActivity.this, AlarmActivity.class);
                startActivity(startIntent);
            }
        });

        docAppointment = (Button) findViewById(R.id.docReminder);
        docAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(CalendarActivity.this, AppointmentAlarm.class);
                startActivity(startIntent);
            }
        });

        refillReminder = (Button) findViewById(R.id.pillRefill);
        refillReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(CalendarActivity.this, PillAlarm.class);
                startActivity(startIntent);
            }
        });

    }

    public void returnHomePage() {
        Intent intent = new Intent(this, ProfilePage1.class);
        startActivity(intent);
    }
}
