package com.peak.android;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.peaknode.android.R;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.d("Peak", "LoginActivity onCreate()");
    }

    public void loginBtnOnClick(View v) {
        EditText et = (EditText) findViewById(R.id.et_username);
        String username = et.getText().toString().trim();

        et = (EditText) findViewById(R.id.et_password);
        String password = et.getText().toString().trim();

        if(!username.equals("") && !password.equals(""))
            new LoginTask().execute(username, password);
    }

    private class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {

                List<NameValuePair> pairs = new ArrayList<NameValuePair>(2);
                pairs.add(new BasicNameValuePair("username", strings[0]));
                pairs.add(new BasicNameValuePair("password", strings[1]));

                return HttpClient.SendHttpPost("http://157.182.194.137:8000/login", pairs);
            }

            catch(Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            handleLoginResult(result);
        }
    }

    private void handleLoginResult(String result) {
        JSONObject obj = null;
        int error = 0;

        try {
            // Create a JSONObject from the result String.
            obj = new JSONObject(result);

            error = 1;

            // Try to see if there is an "error" key.
            String errorMsg = obj.getString("error");

            // This will throw a JSONException if there is no value for the key (successful login).

            // At this point in code there is a value for the "error" key (unsuccessful login).

            // Display it the error message.
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
        }

        catch(Exception e) {
            if(error == 0) // Error converting original result String to JSONObject.
                e.printStackTrace();

            else if(error == 1) {// Error from not finding "error" key.

                try {
                    Toast.makeText(this, "Welcome, " + obj.getString("realname"), Toast.LENGTH_LONG).show();
                }

                catch(Exception e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}
