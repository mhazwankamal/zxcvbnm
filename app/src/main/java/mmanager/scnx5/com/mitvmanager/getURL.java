package mmanager.scnx5.com.mitvmanager;

import android.os.StrictMode;
import android.widget.Toast;

import org.xbill.DNS.ExtendedResolver;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getURL {



    OkHttpClient client;

        public String getURL(String url) throws IOException {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);



            System.setProperty("http.proxyHost", "");
            System.setProperty("http.proxyPort", "");

            Resolver defaultResolver = Lookup.getDefaultResolver();
            Resolver cloudflareFirstResolver = new SimpleResolver("1.1.1.1");
            Resolver cloudflareSecondResolver = new SimpleResolver("1.0.0.1");
            Resolver googleFirstResolver = new SimpleResolver("8.8.8.8");
            Resolver googleSecondResolver = new SimpleResolver("8.8.4.4");
            Resolver layar3Dns=new SimpleResolver("178.128.80.43");

            Lookup.setDefaultResolver(new ExtendedResolver(new Resolver[]{cloudflareFirstResolver,
                    defaultResolver}));

            client = new OkHttpClient.Builder()
                 //  .connectTimeout(10, TimeUnit.SECONDS)
                   //.writeTimeout(10, TimeUnit.SECONDS)
                  // .readTimeout(30, TimeUnit.SECONDS)
                   .dns(new EasyDns())
                   .build();

            Request request = new Request.Builder()
                    .header("User-Agent","3rayal")
                    .url(url)
                    .build();

            Response response = null;

            response = client.newCall(request).execute();



            return response.body().string();
        }



     /*
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.setProperty("java.net.preferIPv4Addresses", "true");
        System.setProperty("java.net.preferIPv6Addresses", "false");
        System.setProperty("validated.ipv6", "false");
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
    */
}

