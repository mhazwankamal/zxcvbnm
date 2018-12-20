package mmanager.scnx5.com.mitvmanager;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class old_getURL {

    public String getURL(String surl) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String fullString = "";
        try {

            URL url = new URL(surl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                fullString += line;
            }
            reader.close();
        } catch (Exception ex) {
            //showDialog("Verbindungsfehler.",parent);
        }

        return fullString;
    }

}
