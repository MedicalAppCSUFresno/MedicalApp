package edu.fresnostate.mail.getthatcheckedout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ProfilePage1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page1);

        ImageButton docBtn = (ImageButton) findViewById(R.id.doc);
        docBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });

        ImageButton stethBtn = (ImageButton) findViewById(R.id.steth);
        stethBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, SymptomChecker.class);
                startActivity(startIntent);
            }
        });

        ImageButton rxBtn = (ImageButton) findViewById(R.id.rx);
        rxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });

        ImageButton pillbottleBtn = (ImageButton) findViewById(R.id.pillbottle);
        pillbottleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, PillID.class);
                startActivity(startIntent);
            }
        });



        ImageButton monitorBtn = (ImageButton) findViewById(R.id.monitor);
        monitorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });
/*
        ImageButton ivBtn = (ImageButton) findViewById(R.id.iv);
        ivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });

        ImageButton hospitalBtn = (ImageButton) findViewById(R.id.hospital);
        hospitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });

        ImageButton needleBtn = (ImageButton) findViewById(R.id.needle);
        needleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });
        ImageButton nurseBtn = (ImageButton) findViewById(R.id.nurse);
        nurseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(ProfilePage1.this, DoctorFinder.class);
                startActivity(startIntent);
            }
        });
*/
    }
}