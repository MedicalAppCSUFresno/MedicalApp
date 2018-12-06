package edu.fresnostate.mail.getthatcheckedout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonalProfile extends AppCompatActivity {

    public TextView pEmail;
    public TextView pUsername;
    public TextView pName;
    public TextView pAge;
    public TextView pPhone;
    public TextView pBloodtype;
    public TextView pMarital;
    public TextView pAddress;
    public TextView pGender;
    public TextView pDadded;
    public Button pLogout;
    public ImageButton pEdit;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReferenceinit = FirebaseDatabase.getInstance("https://medicalapp-23670.firebaseio.com/").getReference();
        DatabaseReference databaseReferenceUser = databaseReferenceinit.child("users");
        DatabaseReference databaseReference = databaseReferenceUser.child(mAuth.getUid());

        pBloodtype = findViewById(R.id.blood_group);
        pAddress = findViewById(R.id.education);
        pAge = findViewById(R.id.occupation);
        pPhone = findViewById(R.id.mobileNumber);
        pMarital = findViewById(R.id.marriage);
        pEmail = findViewById(R.id.email);
        pName = findViewById(R.id.designation);
        pUsername = findViewById(R.id.name);
        pGender = findViewById(R.id.gender);
        pDadded = findViewById(R.id.dob);
        pLogout = findViewById(R.id.location);
        pEdit = findViewById(R.id.edit);

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(todayDate);

        pDadded.setText(todayString);

        DatabaseReference bloodtype = databaseReference.child("tbloodtype");
        DatabaseReference address = databaseReference.child("taddress");
        DatabaseReference age = databaseReference.child("tage");
        DatabaseReference phone = databaseReference.child("tphone");
        DatabaseReference marital = databaseReference.child("tmarital");
        DatabaseReference email = databaseReference.child("temail");
        DatabaseReference name = databaseReference.child("tname");
        DatabaseReference username = databaseReference.child("tusername");
        DatabaseReference gender = databaseReference.child("tgender");

        gender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pGender.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bloodtype.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pBloodtype.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        age.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pAge.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        address.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pAddress.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        phone.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pPhone.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        marital.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pMarital.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pEmail.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pName.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        username.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                pUsername.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        pEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(PersonalProfile.this, RegisterUserForm.class);
                startActivity(startIntent);
            }
        });

    }

}
