package com.peak.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONObject;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.d("Peak", "MainActivity onCreate()");

        try {
            Intent intent = getIntent();
            String userObjStr = intent.getStringExtra("userObjStr");
            JSONObject userObj = new JSONObject(userObjStr);

            TextView tv = (TextView) findViewById(R.id.tv_temp);
            tv.setText(userObj.toString(4));
        }

        catch(Exception e) {
            Log.e("Peak", e.toString());
        }
    }
}
