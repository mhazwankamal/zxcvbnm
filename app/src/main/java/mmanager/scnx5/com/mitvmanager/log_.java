package mmanager.scnx5.com.mitvmanager;

import android.util.Log;

public class log_ {

    public void log_d(Boolean Debug,String Tag,String Content){
        if(Debug)
        Log.d(Tag,Content);
    }
}
