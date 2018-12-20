package mmanager.scnx5.com.mitvmanager;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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
import java.util.Timer;
import java.util.TreeMap;

import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;

public class FloatingMiTV extends IntentService {
    public Handler handler=new Handler();
    private boolean CheckPogoRunning,checkMiTVRunning;
    public String[] DeviceInfo;
    public Runnable checkPoGo=null;
    private String mac_address,username,model,channelid,server;
    public FloatingMiTV() {
        super("FloatingMiTV");
    }
    private getURL wget=new getURL();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mac_address = intent.getStringExtra("mac_address");
        username = intent.getStringExtra("username");
        model = intent.getStringExtra("model");
        channelid = intent.getStringExtra("channelid");
        server = intent.getStringExtra("server");
    }

    public void onCreate() {


        super.onCreate();

        // updateOnlinestatus();
        new CountDownTimer(2000, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                handler.postDelayed(checkExoPlayerActivity,1);

            }

        }.start();
    }

    public void onDestroy() {

        super.onDestroy();



    }



    private Runnable checkExoPlayerActivity = new Runnable() {


        @Override
        public void run() {
            // do what you need to do
            // and here comes the "trick"


            if (isActivityRunning("exoplayer_layar")) {
              // Log.d("CheckService","Application is runnning");
            } else {
                //Log.d("CheckService","Application is stop");
                String logoutResponse = null;
                try {
                    logoutResponse = wget.getURL(server + "apps/exoplayer/update_offlinev3.php?mac_address=" + mac_address + "&username=" + username + "&channel=" + channelid);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (logoutResponse.equalsIgnoreCase("out")){

                    handler.removeCallbacks(checkExoPlayerActivity);
                    stopSelf();
                    return;

                }

            }

            handler.postDelayed(this, 1000);

        }


    };
        protected Boolean isActivityRunning(String activityClass) {
            ActivityManager activityManager = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);

            for (ActivityManager.RunningTaskInfo task : tasks) {
                if (tasks.get(0).topActivity.getClassName().contains(activityClass)) {
                    return true;
                   //Log.d("Activity",tasks.get(0).topActivity.getClassName());
                }
            }

            return false;
        }
    }


  /*
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
                     //   Updated = getURL("http://scnx5.sytes.net/mitv/apps/updateonlinestatus.php?deviceid=" + DeviceInfo[0] + "&user=" + DeviceInfo[l3_new_logo] + "&opt=in");
                    //    Toast.makeText(getApplicationContext(),Updated + ":" + CurrentApp, Toast.LENGTH_SHORT).show();


                    }else{
                    File file = new File(Environment.getExternalStorageDirectory(), "mitv.txt");
                    file.delete();

                    CheckPogoRunning=false;
                //   Toast.makeText(getApplicationContext(),"Unable to check mitv is running!",Toast.LENGTH_LONG).show();
                  //  String Updated;
                 //   Updated = getURL("http://scnx5.sytes.net/mitv/apps/updateonlinestatus.php?deviceid=" + DeviceInfo[0] + "&user=" + DeviceInfo[l3_new_logo] + "&opt=out");
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

*/




