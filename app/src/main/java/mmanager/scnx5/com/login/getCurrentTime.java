package mmanager.scnx5.com.login;

import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class getCurrentTime {

    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());

        return strDate;
    }
}
