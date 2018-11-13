package muckmusic.gmail.com.help2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button skipBtn = (Button) findViewById(R.id.skip);
        skipBtn.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, DoctorLocator.class);
                startActivity(startIntent);
            }
        });
    }
}
