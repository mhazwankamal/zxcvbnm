package mmanager.scnx5.com.mitvmanager;

import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.webkit.URLUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class isUrlValid {
    OkHttpClient client;
    public Boolean isUrl (String url) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        client = new OkHttpClient.Builder()
                //  .connectTimeout(10, TimeUnit.SECONDS)
                //.writeTimeout(10, TimeUnit.SECONDS)
                // .readTimeout(30, TimeUnit.SECONDS)
                .dns(new EasyDns())
                .build();

        Request request = new Request.Builder()
                .header("User-Agent","VLC/3.0.0-git LibVLC/3.0.0-git")
                .url(url)
                .build();

        Response response = null;

        response = client.newCall(request).execute();

        if (response.code() == 404){
            return false;
        } else {
            return true;
        }


    }
}
