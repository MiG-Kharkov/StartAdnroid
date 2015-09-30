package ua.com.webtuning.startandroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by mig on 30.09.15.
 */
public class PrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}
