package mmanager.scnx5.com.authorization;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dcastalia.localappupdate.DownloadApk;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import mmanager.scnx5.com.abcsesscxz;
import mmanager.scnx5.com.abcvodzxc;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.decrypt.decrypt;
import mmanager.scnx5.com.mitvmanager.FloatingMiTV;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RequestPermissionHandler;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.redbox_grid_activity;
import mmanager.scnx5.com.mitvmanager.VODGrid.vod_grid_activity;
import mmanager.scnx5.com.mitvmanager.getURL;


public class logout_main extends AppCompatActivity {
    private RequestPermissionHandler mRequestPermissionHandler;
    public String personID="No-one",uniqueID="",tk="",server,changelogURL;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private decrypt d=new decrypt();
    private abcyxoorp a=new abcyxoorp();
    private abcsesscxz b=new abcsesscxz();
    private abcvodzxc c=new abcvodzxc();
    private Handler handler = new Handler();
    private boolean writepermission=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
      //  java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
      //  java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        android.provider.Settings.System.putString(getContentResolver(), android.provider.Settings.System.WIFI_STATIC_DNS1, "8.8.8.8");
        android.provider.Settings.System.putString(getContentResolver(), android.provider.Settings.System.WIFI_STATIC_DNS2, "8.8.4.4");

        //   View decorView = getWindow().getDecorView();
        // Hide the status bar.
       // int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
       // decorView.setSystemUiVisibility(uiOptions);
        //        // Remember that you should never show the action bar if the
        //        // status bar is hidden, so hide that too if necessary.
        //        //  ActionBar actionBar = getActionBar();
        // actionBar.hide();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);




        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            personID = extras.getString("PersonID");
            tk = extras.getString("token");
            uniqueID= extras.getString("DeviceID");
            server= extras.getString("server");

            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }

                changelogURL=server + "apps/update/update-changelog.json";

                AppUpdater appUpdater=new AppUpdater(logout_main.this);
                appUpdater
                        .setUpdateFrom(UpdateFrom.JSON)
                        .setUpdateJSON(changelogURL)
                        .setButtonUpdateClickListener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                checkPermissionWriteExtStorage();


                                        getURL wget=new getURL();

                                String updateJSON= null;
                                try {
                                    updateJSON = wget.getURL(changelogURL);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                JSONObject GetDataJSONUpdate;
                                        try {
                                            GetDataJSONUpdate=new JSONObject(String.valueOf(updateJSON));
                                            String url=GetDataJSONUpdate.getString("url");


                                            DownloadApk downloadApk = new DownloadApk(logout_main.this);

                                            downloadApk.startDownloadingApk(url);


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                            }
                        });
                appUpdater.start();







      /*  AppUpdaterUtils appUpdaterUtils = new AppUpdaterUtils(this)
                //.setUpdateFrom(UpdateFrom.AMAZON)
                //.setUpdateFrom(UpdateFrom.GITHUB)
                //.setGitHubUserAndRepo("javiersantos", "AppUpdater")
                //...
                .withListener(new AppUpdaterUtils.UpdateListener() {
                    @Override
                    public void onSuccess(Update update, Boolean isUpdateAvailable) {
                        Log.d("Latest Version", update.getLatestVersion());
                        Log.d("Latest Version Code", update.getLatestVersionCode());
                        Log.d("Release notes", update.getReleaseNotes());
                        Log.d("URL", update.getUrlToDownload());
                        Log.d("Is update available?", Boolean.toString(isUpdateAvailable));
                    }

                    @Override
                    public void onFailed(AppUpdaterError error) {
                        Log.d("AppUpdater Error", "Something went wrong");
                    }
                });
        appUpdaterUtils.start();
*/
        //initialize
        mRequestPermissionHandler = new RequestPermissionHandler();

        TextView versionApps = (TextView)findViewById(R.id.Appsversion);

        PackageInfo pInfo = null;
        try {
            pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        String version = pInfo.versionName;
  //      String[] versionBF=version.split("-");
    //    versionApps.setText(versionBF[0]);

        TextView usernameTxt=(TextView)findViewById(R.id.usernameTxt);
        usernameTxt.setText(personID);

       String nwtk=d.decryptStr(tk);

        JSONObject objectPremium = null;
        String remainingdays="";
        try {
            objectPremium = new JSONObject(String.valueOf(nwtk));
            // String loginData = objectPremium.getString("data");


            remainingdays = objectPremium.getString("days");
        } catch (JSONException e) {
            e.printStackTrace();
        }

       String[] nwtka=nwtk.split(":-");

    //   Log.d("nwtka",tk);

    //    Log.d("nwtka",nwtk);

     //   Log.d("nwtka",nwtka[6]);


        TextView ExpiryLeft=(TextView)findViewById(R.id.expiry);
        final Integer addRemainningdays=Integer.parseInt(remainingdays) + 1;

        ExpiryLeft.setText(String.valueOf(addRemainningdays) + " day(s) remaining");


        LinearLayout LiveTVLL=(LinearLayout)findViewById(R.id.livettlclick);
        LinearLayout VODLL=(LinearLayout)findViewById(R.id.VODLL);
        LinearLayout SettingLL=(LinearLayout)findViewById(R.id.SETTINGLL);

     /*   LiveTVLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                checkPermissionWriteExtStorage(); //to start MITV'
                 w

                return false;
            }
        });
*/
        SettingLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), user_setting.class);
                i.putExtra("username", personID);
                i.putExtra("remaining", String.valueOf(addRemainningdays));
                i.putExtra("server", server);
                startActivity(i);
            }
        });

        LiveTVLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // checkPermissionWriteExtStorage(); //to start MITV
              //  Intent i=new Intent(getApplicationContext(), redbox_grid_activity.class);
              //  Intent i=new Intent(getApplicationContext(), redbox_grid_activity.class);
                Intent i=new Intent(getApplicationContext(), redbox_grid_activity.class);
                i.putExtra("tk", tk);
                i.putExtra("server", server);
                startActivity(i);
            }
        });


        VODLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent i=new Intent(getBaseContext(), vod_grid_activity.class);
              //  startActivity(i);

             //   Toast.makeText(logout_main.this,"VOD section is under maintenance",Toast.LENGTH_LONG).show();

                Intent i=new Intent(getApplicationContext(), vod_grid_activity.class);
                i.putExtra("tk", tk);
                i.putExtra("server", server);
                startActivity(i);

               // new logout_main.PrepareVODdata().execute("","");
            }
        });

    }

    private Runnable downloadNewUpdate = new Runnable() {


        @Override
        public void run() {
            /* do what you need to do */
            if (writepermission){
                downloadAndUpdate();
            }


            /* and here comes the "trick" */
            handler.postDelayed(this, 1000);
        }
    };

    private void downloadAndUpdate() {

        getURL wget=new getURL();

        String updateJSON= null;
        try {
            updateJSON = wget.getURL(changelogURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject GetDataJSONUpdate;
        try {
            GetDataJSONUpdate=new JSONObject(String.valueOf(updateJSON));
            String url=GetDataJSONUpdate.getString("url");


            DownloadApk downloadApk = new DownloadApk(logout_main.this);

            downloadApk.startDownloadingApk(url);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void Toasmsg(URL link){
        Toast.makeText(this,link.toString(),Toast.LENGTH_LONG).show();
    }

    public static int getVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {}
        return 0;
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


    private class AsyncStarMitv extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(logout_main.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {

            String contentMitvtxt = "";


            contentMitvtxt = getURL("http://scnx5.sytes.net/mitv/apps/getmitv.php");

            //  checkPermissionWriteExtStorage();
            // checkPermissionReadPhoneState();

            File file;
            FileOutputStream outputStream;
            String result = null;
            result = "false";
            try {
                file = new File(Environment.getExternalStorageDirectory(), "mitv.txt");

                outputStream = new FileOutputStream(file);
                outputStream.write(contentMitvtxt.getBytes());
                outputStream.close();

                // Toast.makeText(getBaseContext(), "File saved successfully!",
                //       Toast.LENGTH_SHORT).show();

                String fileMiTvpath=Environment.getExternalStorageState() + "/mitv.txt";

                result = "true";


            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */

                String filename = "DeviceInfo.txt";
                String fileContents = uniqueID + "," + personID;
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fileContents.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent FloatWinIntent =new Intent(logout_main.this,FloatingMiTV.class);
                startService(FloatWinIntent);


            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(getApplicationContext(),"Unable to start MiTV",Toast.LENGTH_SHORT).show();

            }
        }

    }

    public boolean checkFileExist(String filepath){

        File file = new File(filepath);

        if (file.exists()){
            return true;
        }
        else{
            return false;
        }
    }

    private void checkPermissionWriteExtStorage(){



        mRequestPermissionHandler.requestPermission(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 123, new RequestPermissionHandler.RequestPermissionListener() {
            @Override
            public void onSuccess() {
                // Toast.makeText(MainActivity.this, "request permission success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed() {
                //Toast.makeText(MainActivity.this, "request permission failed", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private class PrepareVODdata extends AsyncTask<String, String, String>
    {
        String json;
      // ProgressDialog pdLoading = new ProgressDialog(logout_main.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
         //   pdLoading.setMessage("\tLoading...");
         //   pdLoading.setCancelable(false);
     //       pdLoading.show();
//
        }
        @Override
        protected String doInBackground(String... params) {
            String result="";

            json= getURL(a.xyoprup()+c.rabcvodzxc()+".php?tk=" + tk);



            //

            result = "true";
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

          //  pdLoading.dismiss();
            Log.d("x lalu result","jalan sini doinbackground");

            if(result.equalsIgnoreCase("true"))
            {
                 Intent i=new Intent(getBaseContext(), vod_grid_activity.class);
                 i.putExtra("json",json);
                 startActivity(i);


                Log.d("TRUE","jalan sini doinbackground");
            }else if (result.equalsIgnoreCase("false")){
                Log.d("false","jalan sini doinbackground");

                Toast.makeText(getApplicationContext(),"false",Toast.LENGTH_LONG);
            }else{
                Toast.makeText(getApplicationContext(),"problem",Toast.LENGTH_LONG);
                Log.d("x tau","jalan sini doinbackground");

            }
        }

    }


}
