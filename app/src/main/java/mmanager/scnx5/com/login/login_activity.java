package mmanager.scnx5.com.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alkathirikhalid.util.ConnectionAppCompactActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mmanager.scnx5.com.CONST.CONST;
import mmanager.scnx5.com.abcauthabc;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.authorization.newui_logout_main;
import mmanager.scnx5.com.decrypt.decrypt;
import mmanager.scnx5.com.encrypt.encrypt;
import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;
import mmanager.scnx5.com.mitvmanager.old_getURL;


public class login_activity extends ConnectionAppCompactActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText etEmail;
    private EditText etPassword;
    public String email,password;
    public String userpass;
    public CONST c=new CONST();
    private abcyxoorp a=new abcyxoorp();
    private abcauthabc b=new abcauthabc();
    private Boolean fileExisted=false;
    private String[] userlogin;
    private getURL wget=new getURL();
    private log_ dLog=new log_();
    private Boolean debug=true; //print debug log
    private Boolean logout;
    private String server;
    private String tk="";
    private String tkn;
    private old_getURL old_wget=new old_getURL();
    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
       // java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main2);
        logout=false;
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            logout = extras.getBoolean("logout");

            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }


        etEmail=(EditText)findViewById(R.id.username);
        etPassword=(EditText)findViewById(R.id.password);


        try {
            server=wget.getURL("http://178.128.80.43:8080/serverlist.php?server=1");
        } catch (IOException e) {
            e.printStackTrace();
        }

        pref = getApplicationContext().getSharedPreferences("LoginInfo", MODE_PRIVATE);
        editor = pref.edit();

        email = pref.getString("username",null);
        password= pref.getString("password",null);

        dLog.log_d(debug,"userShared","=" + email);

        if(email != null){

            etEmail.setText(email);
            etPassword.setText(password);

            userpass=email + ":" + password;

            if(!logout){
                new CountDownTimer(10, 10) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {

//                        encrypt e=new encrypt();
////                        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
////                        String access=userpass+","+androidId+email;
////                        String encraccess= e.encryptStr(access);

                           new AsyncLogin().execute(server + b.rabcauthabc() + ".php", userpass);


                        // new AsyncLogin().execute(a.xyoprup() + b.rabcauthabc() + ".php" ,userpass);
                    }

                }.start();



            }

        }





        final Button loginB=(Button) findViewById(R.id.loginBtn);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString();
                String password = etPassword.getText().toString();


                    userpass = email + ":" + password;
                // Initialize  AsyncLogin() class with email and password

                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Username and Password.",Toast.LENGTH_SHORT).show();

                }else {

                   //


//                    encrypt e=new encrypt();
//                    String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//                    String access=email+","+password+","+androidId+email;
//                    String encraccess= e.encryptStr(access);


                    dLog.log_d(debug,"server",userpass);


                    new AsyncLogin().execute(server + b.rabcauthabc() + ".php", userpass);




                }


            }
        });




    }





    @Override
    public void connectedOrConnecting() {

    }

    @Override
    public void connected() {

    }

    @Override
    public void typeWifi() {

    }

    @Override
    public void typeMobile() {

    }

    @Override
    public void connectedConnectionFast() {

    }

    @Override
    public void connectedConnectionSlow() {

    }

    @Override
    public void noNetwork() {
        Toast.makeText(getApplicationContext(),"Your internet connection loss!",Toast.LENGTH_SHORT).show();
    }


    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(login_activity.this);
        HttpURLConnection conn;
        URL url = null;
        String encraccess;
        private String pr="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
//            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {

            String userpassAsync=params[1];

           // String[] userinfo=userpassAsync.split(":");

            try {

               // Toast.makeText(getApplicationContext(),userinfo[1] + " " + userinfo[2],Toast.LENGTH_SHORT).show();
                //Log.d("userinfo",userinfo[0] + " " + userinfo[1]);
                // Enter URL address where your php file resides
                String passUrl=params[0];
                url = new URL(passUrl);
            //    Log.d("myTag", "Checking with server");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                encrypt e=new encrypt();

              //  getCurrentTime getTime=new getCurrentTime();

              //  Log.d("CurrentTime",getTime.getCurrentTime());

             //   makeJSON mkJSON = new makeJSON();
           //     DeviceUuidFactory DeviceUUIDNo = new DeviceUuidFactory(login_activity.this.getApplicationContext());
             //   String uniqueID=DeviceUUIDNo.getDeviceUuid().toString();
               // String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

               // String access=userinfo[0]+":"+userinfo[1];

                //String JSONaccess=mkJSON.createLoginJSON(access);

            //    Log.d("access",access);


                String encraccess= e.encryptStr(userpassAsync);

                //encraccess=encraccess + "." + userinfo[2];


               // Log.d("access",encraccess.trim());

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("access", encraccess);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                   // Log.d("myTag", "Get response:" + result.toString());

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            //pdLoading.dismiss();

          //  Log.d("result",result);

            JSONObject objectPremium = null;
            String tkn="";
            try {
                objectPremium = new JSONObject(String.valueOf(result));
                tkn = objectPremium.getString("token");
                decrypt dc=new decrypt();
                pr=tkn;
            //    Log.d("Result",tkn);

                result=dc.decryptStr(tkn);

                //  String[] next=result.split(":");

        //       Log.d("Resultdc",result);
            } catch (JSONException e) {
                e.printStackTrace();
            }





            //create json to read data
            objectPremium = null;
            String login="",username="",passwrd="",nextA="",deviceid="";
            try {
                objectPremium = new JSONObject(String.valueOf(result));
               // String loginData = objectPremium.getString("data");


                login = objectPremium.getString("login");
                if(login.equalsIgnoreCase("true")){
                    nextA = objectPremium.getString("activity");

                 }
                username = objectPremium.getString("username");
                passwrd = objectPremium.getString("password");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            userpass=username + "," + passwrd;

          //  Log.d("deviceId",deviceid);

            if(login.equalsIgnoreCase(c.getEn()))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */



                editor.putString("username", username);
                editor.putString("password", passwrd);
                editor.apply();

              /*  String filename ="userinfo.txt";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(userpass.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/


                String nextActivity=nextA;
              //  Intent i = new Intent(getBaseContext(), nextactivity.class);
              //  Intent i =new Intent();
               Intent i = new Intent(getApplicationContext(),newui_logout_main.class);

              //  i.setClassName(login_activity.this, nextActivity);
                i.putExtra("PersonID", email);
                i.putExtra("token", pr);
                i.putExtra("server", server);
         //       Log.d("pr",pr);
                startActivity(i);
                finish();

            }else if (login.equalsIgnoreCase(c.getDeEn())){

                // If username and password does not match display a error message
                Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();

            }
            else if (login.equalsIgnoreCase(c.getDeEnMax())){

                // If username and password does not match display a error message
                Toast.makeText(getApplicationContext(),"Your account is not active!",Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getApplicationContext(),"Something went wrong.\nCheck your internet connection via this device." + login,Toast.LENGTH_SHORT).show();

            }
        }

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
