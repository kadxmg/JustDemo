package demo.just.journey.justdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "journey_demo";

    void TestLocal(){

        Locale locale;
        LocaleList ll;

        Locale.setDefault(new Locale("zh","cn"));

        locale = Locale.getDefault();
        String language = locale.getLanguage() + "-" + locale.getCountry();
        Log.d(TAG,"Locale.getDefault: " + language);

        Log.d(TAG,"LocaleList.getDefault:");
        ll = LocaleList.getDefault();
        for(int i = 0;i < ll.size(); i++) {
            locale = ll.get(i);
            language = locale.getLanguage() + "-" + locale.getCountry();
            Log.d(TAG,language);
        }

        Log.d(TAG,"LocaleList.getAdjustedDefault:");
        ll = LocaleList.getAdjustedDefault();
        for(int i = 0;i < ll.size(); i++) {
            locale = ll.get(i);
            language = locale.getLanguage() + "-" + locale.getCountry();
            Log.d(TAG,language);
        }

        Log.d(TAG,"Resources.getSystem():");
        ll = Resources.getSystem().getConfiguration().getLocales();
        for(int i = 0;i < ll.size(); i++) {
            locale = ll.get(i);
            language = locale.getLanguage() + "-" + locale.getCountry();
            Log.d(TAG,language);
        }
    }
    public void OnTest(View view) {
        TestLocal();
    }
    public void OnANR(View view) {
        try {
            Toast.makeText(this,"please trun off and turn on the screen.",Toast.LENGTH_LONG).show();
            Thread.sleep(5000);
        } catch (Exception e) {

        }
    }
    public void OnCrash(View view) {
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private final BroadcastReceiver receiver = new BroadcastReceiver(){

        @Override
        public void onReceive(final Context context, final Intent intent) {
            // Do your action here
            Log.d(TAG,"onReceive:" + intent);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver, filter);
    }
}
