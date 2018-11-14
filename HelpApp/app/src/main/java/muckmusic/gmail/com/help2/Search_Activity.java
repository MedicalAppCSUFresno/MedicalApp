package muckmusic.gmail.com.help2;

import android.app.Activity;
import android.os.Bundle;

import muckmusic.gmail.com.help2.ui.search.SearchFragment;

public class Search_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search__activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SearchFragment.newInstance())
                    .commitNow();
        }
    }
}
