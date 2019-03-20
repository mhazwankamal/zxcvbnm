package mmanager.scnx5.com.mitvmanager;

import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import org.xbill.DNS.Address;
import org.xbill.DNS.ExtendedResolver;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;

import okhttp3.Dns;

public class EasyDns implements Dns {

    private static final String LIVE_API_HOST = "easytaxi.com.br";
    private static final String LIVE_API_IP = "1.2.3.4";

    private boolean mInitialized;
    private InetAddress mLiveApiStaticIpAddress;

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        // I'm initializing the DNS resolvers here to take advantage of this method being called in a background-thread managed by OkHttp
        init();
        try {
            Log.d("collections",String.valueOf(Collections.singletonList(Address.getByName(hostname))));
            return Collections.singletonList(Address.getByName(hostname));

        } catch (UnknownHostException e) {
            // fallback to the API's static IP
            if (LIVE_API_HOST.equals(hostname) && mLiveApiStaticIpAddress != null) {
                return Collections.singletonList(mLiveApiStaticIpAddress);
            } else {
                throw e;
            }
        }
    }

    private void init() {
        if (mInitialized) return; else mInitialized = true;

//        try {
//            mLiveApiStaticIpAddress = InetAddress.getByName(LIVE_API_IP);
//        } catch (UnknownHostException e) {
//            Log.w("DNS", "Couldn't initialize static IP address");
//        }

        try {
            // configure the resolvers, starting with the default ones (based on the current network connection)
            Resolver defaultResolver = Lookup.getDefaultResolver();
            // use Google's public DNS services
            Resolver cloudflareFirstResolver = new SimpleResolver("1.1.1.1");
            Resolver cloudflareSecondResolver = new SimpleResolver("1.0.0.1");
            Resolver googleFirstResolver = new SimpleResolver("8.8.8.8");
            Resolver googleSecondResolver = new SimpleResolver("8.8.4.4");
            Resolver layar3Dns=new SimpleResolver("178.128.80.43");
            // also try using Amazon
           // Resolver amazonResolver = new SimpleResolver("205.251.198.30");
            Lookup.setDefaultResolver(new ExtendedResolver(new Resolver[]{cloudflareFirstResolver,
                    defaultResolver}));
        } catch (UnknownHostException e) {
            Log.w("DNS", "Couldn't initialize custom resolvers");
        }
    }
}
