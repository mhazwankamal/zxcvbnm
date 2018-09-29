package mmanager.scnx5.com.mitvmanager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class livetv_menu extends AppCompatActivity {
    private RequestPermissionHandler mRequestPermissionHandler;
    public String personID="No-one",uniqueID="";
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

     //   View decorView = getWindow().getDecorView();
        // Hide the status bar.
       // int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
       // decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //  ActionBar actionBar = getActionBar();
        // actionBar.hide();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.livetv_menu);



        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            personID = extras.getString("PersonID");
            uniqueID= extras.getString("DeviceID");
            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }

        //initialize
        mRequestPermissionHandler = new RequestPermissionHandler();

        TextView usernameTxt=(TextView)findViewById(R.id.usernameTxt);
        usernameTxt.setText(personID);

         new livetv_menu.getExipryDate().execute();

        LinearLayout LiveTVLL=(LinearLayout)findViewById(R.id.livettlclick);
        LinearLayout VODLL=(LinearLayout)findViewById(R.id.VODLL);
        LinearLayout SettingLL=(LinearLayout)findViewById(R.id.livettlclick);

     /*   LiveTVLL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                checkPermissionWriteExtStorage(); //to start MITV

                return false;
            }
        });
*/
        LiveTVLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermissionWriteExtStorage(); //to start MITV

            }
        });


        VODLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent i=new Intent(getBaseContext(), vod_grid_activity.class);
              //  startActivity(i);

                Intent i=new Intent(getApplicationContext(), redbox_grid_activity.class);
                startActivity(i);

               // new main_menu.PrepareVODdata().execute("","");
            }
        });

    }



        public class getExipryDate extends AsyncTask<String,String, String>   {
            String dateExpiry;


            @Override
            protected String doInBackground(String... params) {
                 dateExpiry=getURL("http://scnx5.sytes.net/mitv/apps/getExpiry.php?user=" + personID);

                return "";
            }
            @Override
            protected void onPostExecute(String result) {
                TextView ExpiryLeft=(TextView)findViewById(R.id.expiry);
                ExpiryLeft.setText(dateExpiry);
            }
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
        ProgressDialog pdLoading = new ProgressDialog(livetv_menu.this);

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


                Intent FloatWinIntent =new Intent(livetv_menu.this,FloatingMiTV.class);
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
                new livetv_menu.AsyncStarMitv().execute("", "");
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
      // ProgressDialog pdLoading = new ProgressDialog(main_menu.this);


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

            json= getURL("http://scnx5.sytes.net/mitv/apps/vod/getvodlist.php");



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
