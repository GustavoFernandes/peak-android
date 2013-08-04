package com.peak.android;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HttpClient {

    public static String SendHttpPost(String URL, List<NameValuePair> pairs) {

        try {

            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(URL);
            post.setEntity(new UrlEncodedFormEntity(pairs));

            long t = System.currentTimeMillis();
            HttpResponse response = (HttpResponse) client.execute(post);
            Log.i("Peak", "HTTPResponse received in [" + (System.currentTimeMillis()-t) + "ms]");

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                InputStream istream = entity.getContent();

                String resultStr = convertStreamToString(istream);
                istream.close();

                Log.i("Peak", "resultStr: " + resultStr);

                return resultStr;
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        try {

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {

            try {
                is.close();
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
