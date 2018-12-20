package mmanager.scnx5.com.mitvmanager;

import android.app.Application;

import com.bugsnag.android.Bugsnag;


public class layar3 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bugsnag.init(this);

    }

    @Override
    public void onTerminate(){
        super.onTerminate();
    }
}
