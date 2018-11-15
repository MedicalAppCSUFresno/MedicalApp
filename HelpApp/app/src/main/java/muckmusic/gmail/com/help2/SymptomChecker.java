package muckmusic.gmail.com.help2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SymptomChecker extends AppCompatActivity {

    private ListView lstSearch;
    private EditText edtSearch;
    private ArrayAdapter<String> adapter;

    String data[]= {
            "headache", "coughing", "sneezing", "twitching", "itchy",
            "ache", "constant", "throbbing", "rash", "migraine",
            "painful", "drowsy", "tired", "dizzy"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker);

        lstSearch = (ListView)findViewById(R.id.lstSearch);
        edtSearch = (EditText)findViewById(R.id.edtSearch);
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView)

    }
}
