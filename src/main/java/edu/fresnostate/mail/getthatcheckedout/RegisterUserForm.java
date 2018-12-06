package edu.fresnostate.mail.getthatcheckedout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUserForm extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private EditText mEmail;
    private EditText mUsername;
    private EditText mName;
    private EditText mAge;
    private EditText mPhone;
    private EditText mAddress;
    private Button mRegister;
    private Spinner mBloodtype;
    private Spinner mMarital;
    private Spinner mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_form);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // Views
        mEmail = findViewById(R.id.fieldEmail);
        mUsername = findViewById(R.id.fieldUsername);
        mName = findViewById(R.id.fieldName);
        mAge = findViewById(R.id.fieldAge);
        mPhone = findViewById(R.id.fieldPhone);
        mAddress = findViewById(R.id.fieldAddress);
        mRegister = findViewById(R.id.regButton);
        mBloodtype = findViewById(R.id.btypeSpinner);
        mMarital = findViewById(R.id.maritalSpinner);
        mGender = findViewById(R.id.genderSpinner);

        mUsername.setText(usernameFromEmail(mAuth.getCurrentUser().getEmail()));
        mEmail.setText(mAuth.getCurrentUser().getEmail());



        Spinner genspinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> genadapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genspinner.setAdapter(genadapter);

        Spinner btspinner = (Spinner) findViewById(R.id.btypeSpinner);
        ArrayAdapter<CharSequence> btadapter = ArrayAdapter.createFromResource(this,
                R.array.bloodtype_array, android.R.layout.simple_spinner_item);
        btadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        btspinner.setAdapter(btadapter);

        Spinner marspinner = (Spinner) findViewById(R.id.maritalSpinner);
        ArrayAdapter<CharSequence> maradapter = ArrayAdapter.createFromResource(this,
                R.array.marital_array, android.R.layout.simple_spinner_item);
        maradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marspinner.setAdapter(maradapter);


        mRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String rEmail = mEmail.getText().toString().trim();
                String rUsername = mUsername.getText().toString().trim();
                String rName = mName.getText().toString().trim();
                String rAge = mAge.getText().toString().trim();
                String rPhone = mPhone.getText().toString().trim();
                String rAddress = mAddress.getText().toString().trim();
                String rGender = mGender.getSelectedItem().toString().trim();
                String rBloodtype = mBloodtype.getSelectedItem().toString().trim();
                String rMarital = mMarital.getSelectedItem().toString().trim();

                if(TextUtils.isEmpty(rEmail)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(rUsername)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                }


                onAuthSuccess(mAuth.getCurrentUser(), rAge, rBloodtype, rPhone, rAddress, rMarital, rName, rGender);


                Intent startIntent = new Intent(RegisterUserForm.this, ProfilePage1.class);
                startActivity(startIntent);
                RegisterUserForm.this.finish();
            }

        });

    }

    private void onAuthSuccess(FirebaseUser user, String age, String bloodtype, String phone, String address, String marital, String name, String gender) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail(), age, bloodtype, phone, address, marital, name, gender);

        // Go to MainActivity
        startActivity(new Intent(RegisterUserForm.this, ProfilePage1.class));
        finish();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String username, String email, String age, String bloodtype, String phone, String address, String marital, String name, String gender) {
        User user = new User(name, email, age, bloodtype, phone, address, marital, username, gender);

        mDatabase.child("users").child(userId).setValue(user);
    }

    public static class User {

        public String tusername;
        public String temail;
        public String tage;
        public String tphone;
        public String tbloodtype;
        public String taddress;
        public String tmarital;
        public String tname;
        public String tgender;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String username, String email, String age, String bloodtype, String phone, String address, String marital, String name, String gender) {
            this.tusername = username;
            this.temail = email;
            this.taddress = address;
            this.tage = age;
            this.tbloodtype = bloodtype;
            this.tmarital = marital;
            this.tname = name;
            this.tphone= phone;
            this.tgender = gender;
        }

    }
}
