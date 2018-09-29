package mmanager.scnx5.com.mitvmanager;

import android.content.res.Resources;

public class getscreenratio {

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}

