package mmanager.scnx5.com.mitvmanager;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class FloatingMiTV extends IntentService {
    public Handler handler;
    private boolean CheckPogoRunning,checkMiTVRunning;
    public String[] DeviceInfo;
    public Runnable checkPoGo=null;

    public FloatingMiTV() {
        super("FloatingMiTV");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {


    }

    public void onCreate() {


        super.onCreate();




        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.gsoft.mitv");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }

            if(checkFileExist("DeviceInfo.txt")){


            File file2 = new File(getApplicationContext().getFilesDir(),"DeviceInfo.txt");

//Read text from file
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file2));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            }
            catch (IOException e) {
                //You'll need to add proper error handling here
            }

                final String userpass=text.toString();
                DeviceInfo=userpass.split(",");

            }


        checkPokemonGoRunning();
       // updateOnlinestatus();
    }

    public void onDestroy() {

        super.onDestroy();

        String Updated;
        Updated = getURL("http://scnx5.sytes.net/mitv/apps/updateonlinestatus.php?deviceid=" + DeviceInfo[0] + "&user=" + DeviceInfo[1] + "&opt=out");

    }

    private void checkPokemonGoRunning (){

        CheckPogoRunning=true;

        handler = new Handler();

        checkPoGo = new Runnable() {




            @Override
            public void run() {
               handler.postDelayed(this,5000);

                String CurrentApp="";

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    // intentionally using string value as Context.USAGE_STATS_SERVICE was
                    // strangely only added in API 22 (LOLLIPOP_MR1)
                    @SuppressWarnings("WrongConstant")
                    UsageStatsManager usm = (UsageStatsManager) getSystemService("usagestats");
                    long time = System.currentTimeMillis();
                    List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                            time - 1000 * 1000, time);

                    if (appList != null && appList.size() > 0) {

                        SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                        for (UsageStats usageStats : appList) {
                            mySortedMap.put(usageStats.getLastTimeUsed(),
                                    usageStats);

                        }
                        if (mySortedMap != null && !mySortedMap.isEmpty()) {
                            CurrentApp = mySortedMap.get(
                                    mySortedMap.lastKey()).getPackageName();

                        }
                    }
                } else {
                    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningAppProcessInfo> tasks = am
                            .getRunningAppProcesses();
                    CurrentApp = tasks.get(0).processName;
                }

                if(CurrentApp.isEmpty()) {

                   // Toast.makeText(getApplicationContext(),"Unable to check mitv is running!",Toast.LENGTH_LONG).show();
                }

                if (CurrentApp.equals("com.gsoft.mitv")) {

                    //}

                    File file = new File(Environment.getExternalStorageDirectory(), "mitv.txt");
                    file.delete();
                //   Toast.makeText(getApplicationContext(),"File secured",Toast.LENGTH_LONG).show();
                    //

                     //   String Updated;
                     //   Updated = getURL("http://scnx5.sytes.net/mitv/apps/updateonlinestatus.php?deviceid=" + DeviceInfo[0] + "&user=" + DeviceInfo[1] + "&opt=in");
                    //    Toast.makeText(getApplicationContext(),Updated + ":" + CurrentApp, Toast.LENGTH_SHORT).show();


                    }else{
                    File file = new File(Environment.getExternalStorageDirectory(), "mitv.txt");
                    file.delete();

                    CheckPogoRunning=false;
                //   Toast.makeText(getApplicationContext(),"Unable to check mitv is running!",Toast.LENGTH_LONG).show();
                  //  String Updated;
                 //   Updated = getURL("http://scnx5.sytes.net/mitv/apps/updateonlinestatus.php?deviceid=" + DeviceInfo[0] + "&user=" + DeviceInfo[1] + "&opt=out");
             //      Toast.makeText(getApplicationContext(),"logout:" + Updated, Toast.LENGTH_SHORT).show();

                       // CheckPogoRunning = false;


                    //Toast.makeText(getApplicationContext(),"Curr App :" + CurrentApp + " delay : " + delay + " SnipeOne " + PokeSnipeONE + " UpdateMock " + TimerUpdateLocRun, Toast.LENGTH_SHORT).show();

                }

                if (!CheckPogoRunning){
                    handler.removeCallbacks(checkPoGo);
                }
            }
        };

        handler.postDelayed(checkPoGo,5000);
    }




    public static String getURL(String surl) {
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
    }

    public boolean checkFileExist(String filepath){

        File file = new File(getApplicationContext().getFilesDir(),filepath);

        if (file.exists()){
            return true;
        }
        else{
            return false;
        }
    }

}
