package mmanager.scnx5.com.mitvmanager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private RequestPermissionHandler mRequestPermissionHandler;
    private DeviceUuidFactory DeviceUUIDNo;
    private Context context = this;
    public String uniqueID;
    public TextView token;
    public String personID="No-one";
    public String permissionWirteExt;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public Button clickme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            personID = extras.getString("PersonID");
          //  Toast.makeText(getApplicationContext(),personID,Toast.LENGTH_LONG).show();
        }


//declare UI
        mRequestPermissionHandler = new RequestPermissionHandler();
        final Button request=(Button)findViewById(R.id.request);
        clickme = (Button) findViewById(R.id.btn1);
        token=(TextView)findViewById(R.id.Message);

//check permission

       // checkPermissionReadPhoneState();



        clickme.setEnabled(false);
        request.setEnabled(false);
        request.setVisibility(View.GONE);


       // String base64 = Base64.encodeToString(encrpt, Base64.DEFAULT);
        DeviceUUIDNo = new DeviceUuidFactory(context.getApplicationContext());
        uniqueID=DeviceUUIDNo.getDeviceUuid().toString();


//        Toast.makeText(context, "android_id= " + base64, Toast.LENGTH_LONG).show();
        // String urlLogin ="http://scnx5.sytes.net/mitv/getIP.php";
   //      String urlLogin ="http://scnx5.sytes.net/mitv/checkdevice.php?Uid=" + uniqueID;

 //       String serverStatus=getURL(urlLogin);
         String urlLogin="http://scnx5.sytes.net/mitv/checkdevice.php";
         new AsyncRequestAuthorization().execute(urlLogin,uniqueID);


        //serverStatus=serverStatus.trim();
//####################################################################
/*
        if (serverStatus.equalsIgnoreCase("y")){
            clickme.setEnabled(true);
            LinearLayout serverLI=(LinearLayout) findViewById(R.id.serverlayout);
            serverLI.setBackgroundResource(R.drawable.serverstatus_background);

            TextView serverText=(TextView)findViewById(R.id.ServertextView);
            serverText.setText("Welcome " + personID + "!");
            serverText.setTextColor(Color.WHITE);

            //getIPaddress
            String dateExpiry=getURL("http://scnx5.sytes.net/mitv/getExpiry.php?user=" + personID);


            token.setText("Device : " + Build.MANUFACTURER + " " + Build.MODEL + "\nAndroid Version :" + Build.VERSION.RELEASE + "\nExpiry Date : " + dateExpiry + "\nMiTV-Manager-v1.2");

//            token.setText(String.format("Manufacturer : %s\nModel       : %s\nBrand       : %s\nAndroid OS  : Android %s", Build.MANUFACTURER, Build.MODEL, Build.BRAND, Build.VERSION.SDK_INT));

        } else {

            String returnCheck=checkRequestForAuthorizationSubmitted();

            TextView token=(TextView)findViewById(R.id.Message);

            if (returnCheck.equalsIgnoreCase("y")){
                token.setText("Please contact admin to expedite approval of authorization on this device.");
            }else if (returnCheck.equalsIgnoreCase("n")){
                token.setText("Authorization request had been submitted. Please contact admin.");
            }else{
                token.setText(serverStatus);

            }



            clickme.setEnabled(false);
          //  request.setEnabled(true);
           // request.setVisibility(View.VISIBLE);
        }
#################################################################################################
*/

        clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              checkPermissionWriteExtStorage();


           /**     String fileMitv="/storage/emulated/0/mitv.txt";

                  //  Toast.makeText(getApplicationContext(),"Yahoo..file existed",Toast.LENGTH_SHORT).show();
                   String contentMitvtxt="";


                    contentMitvtxt=getURL("http://scnx5.sytes.net/mitv/getmitv.php");

                    checkPermissionWriteExtStorage();
                    checkPermissionReadPhoneState();

                    File file;
                    FileOutputStream outputStream;
                    try {
                        file = new File(Environment.getExternalStorageDirectory(), "mitv.txt");

                        outputStream = new FileOutputStream(file);
                        outputStream.write(contentMitvtxt.getBytes());
                        outputStream.close();

                       // Toast.makeText(getBaseContext(), "File saved successfully!",
                         //       Toast.LENGTH_SHORT).show();

                        Intent FloatWinIntent =new Intent(MainActivity.this,FloatingMiTV.class);

                        startService(FloatWinIntent);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
*/
                    // add-write text into file
                 /*   try {
                        FileOutputStream fileout=openFileOutput("mitv1.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                        outputWriter.write(contentMitvtxt);
                        outputWriter.close();

                        //display file saved message
                        Toast.makeText(getBaseContext(), "File saved successfully!",
                                Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
*/


              /*      try {
                        URL url=new URL("http://163.172.181.152:8030/rbtv/token21.php");
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                        String line;
                        while ((line =reader.readLine()) != null){
                            tokenString += "test";
                            Toast.makeText(getApplicationContext(),"Oh nooo..file not existed",Toast.LENGTH_SHORT).show();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();ml,kj fcdd
                                            ';lk  hb bh } catch (IOException e) {
                        e.printStackTrace();
                    }
                    */

                  //  final TextView tokenText = (TextView) findViewById(R.id.token);
                   // tokenText.setText(tokenString);

                  //  Toast.makeText(getApplicationContext(),"token is" + tokenString,Toast.LENGTH_SHORT).show();



            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   String model=Build.MANUFACTURER + Build.MODEL;

           //     String requestAuthorization ="http://scnx5.sytes.net/mitv/request.php?Uid=" + uniqueID + "&user=" + personID + "&model=" + model;

           //     String response=getURL(requestAuthorization);

               // if (response.equalsIgnoreCase("requested")){
                 //token.setText("Request had been submitted. Please contact admin to approve it.");
              //     request.setVisibility(View.GONE);
               //    token.setText(response);
               // }

            }
        });

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

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.setProperty("java.net.preferIPv4Addresses", "true");
        System.setProperty("java.net.preferIPv6Addresses", "false");
        System.setProperty("validated.ipv6", "false");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try {
            InputStream in = connection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }

    public static String executeCommand(String command) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.setProperty("java.net.preferIPv4Addresses", "true");
        System.setProperty("java.net.preferIPv6Addresses", "false");
        System.setProperty("validated.ipv6", "false");

        StringBuilder output = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(new String[] { "sh", "-c", command });
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line;
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    private void checkPermissionWriteExtStorage(){



        mRequestPermissionHandler.requestPermission(this, new String[] {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 123, new RequestPermissionHandler.RequestPermissionListener() {
            @Override
            public void onSuccess() {
               // Toast.makeText(MainActivity.this, "request permission success", Toast.LENGTH_SHORT).show();
                new AsyncStarMitv().execute("", "");
            }

            @Override
            public void onFailed() {
                //Toast.makeText(MainActivity.this, "request permission failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void checkPermissionReadPhoneState(){
        mRequestPermissionHandler.requestPermission(this, new String[] {
                Manifest.permission.READ_PHONE_STATE
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mRequestPermissionHandler.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
    }

    public String checkRequestForAuthorizationSubmitted(){

        String model=Build.MANUFACTURER + " " + Build.MODEL;

       String requestAuthorization ="http://scnx5.sytes.net/mitv/request.php?Uid=" + uniqueID + "&user=" + personID + "&model=" + model;



        String response= null;
        try {
            URL requestAuthorization1 =new URL(requestAuthorization);
            response = getResponseFromHttpUrl(requestAuthorization1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response=response.trim();

        String result="";
        if (response.equalsIgnoreCase("submit")){
            result="n";

        }else if (response.equalsIgnoreCase("requested")){
            result="y";
        } else {
            result=response;
        }

        return result;
    }

    private class AsyncStarMitv extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);

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


            contentMitvtxt = getURL("http://scnx5.sytes.net/mitv/getmitv.php");

          //  checkPermissionWriteExtStorage();
           // checkPermissionReadPhoneState();

            File file;
            FileOutputStream outputStream;
            String result = null;
            try {
                file = new File(Environment.getExternalStorageDirectory(), "mitv.txt");

                outputStream = new FileOutputStream(file);
                outputStream.write(contentMitvtxt.getBytes());
                outputStream.close();

                // Toast.makeText(getBaseContext(), "File saved successfully!",
                //       Toast.LENGTH_SHORT).show();
                result = "false";
                String fileMiTvpath=Environment.getExternalStorageState() + "mitv.txt";
                if (checkFileExist(fileMiTvpath)) {
                    result = "true";
                } else {
                    result = "false";
                }





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
                  Intent FloatWinIntent =new Intent(MainActivity.this,FloatingMiTV.class);

                      startService(FloatWinIntent);


            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(getApplicationContext(),"Unable to start MiTV",Toast.LENGTH_SHORT).show();

            }
        }

    }


    private class AsyncRequestAuthorization extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

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

        //    String userpassAsync=params[1];

        //    String[] userinfo=userpassAsync.split(",");

            try {

                // Toast.makeText(getApplicationContext(),params[3],Toast.LENGTH_SHORT).show();

                // Enter URL address where your php file resides
                String passUrl=params[0];
                url = new URL(passUrl);
                Log.d("myTag", "Checking with server");

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

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("deviceid", params[1]);
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

                    Log.d("myTag", "Get response:" + result.toString());

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

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */
               // Toast.makeText(getApplicationContext(),"Authorized",Toast.LENGTH_SHORT).show();



                    clickme.setEnabled(true);
                    LinearLayout serverLI=(LinearLayout) findViewById(R.id.serverlayout);
                    serverLI.setBackgroundResource(R.drawable.serverstatus_background);

                    TextView serverText=(TextView)findViewById(R.id.ServertextView);
                    serverText.setText("Welcome " + personID + "!");
                    serverText.setTextColor(Color.WHITE);

                    //getIPaddress
                    String dateExpiry=getURL("http://scnx5.sytes.net/mitv/getExpiry.php?user=" + personID);

                   token.setText("Device : " + Build.MANUFACTURER + " " + Build.MODEL + "\nAndroid Version :" + Build.VERSION.RELEASE + "\nExpiry Date : " + dateExpiry + "\nMiTV-Manager-v1.2");

//            token.setText(String.format("Manufacturer : %s\nModel       : %s\nBrand       : %s\nAndroid OS  : Android %s", Build.MANUFACTURER, Build.MODEL, Build.BRAND, Build.VERSION.SDK_INT));




            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
               // Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();
/*
        String returnCheck=checkRequestForAuthorizationSubmitted();

        TextView token=(TextView)findViewById(R.id.Message);

        if (returnCheck.equalsIgnoreCase("y")){
            token.setText("Please contact admin to expedite approval of authorization on this device.");
        }else if (returnCheck.equalsIgnoreCase("n")){
            token.setText("Authorization request had been submitted. Please contact admin.");
        }else{
            token.setText(serverStatus);

        }



        clickme.setEnabled(false);
        //  request.setEnabled(true);
        // request.setVisibility(View.VISIBLE);
*/
                String model=Build.MANUFACTURER + " " + Build.MODEL;
                String requestAuthorizeURL="http://scnx5.sytes.net/mitv/request.php";
                String requestAuthorization ="http://scnx5.sytes.net/mitv/request.php?Uid=" + uniqueID + "&user=" + personID + "&model=" + model;
                String personModel=personID + "," + model;


                new AsyncSubmitRequestforAuthorization().execute(requestAuthorizeURL,uniqueID,personModel);


            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(getApplicationContext(),"Something went wrong. Connection problem",Toast.LENGTH_SHORT).show();

            }
        }

    }

    private class AsyncSubmitRequestforAuthorization extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;

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

            String RequestForAAsync=params[2];

            String[] UserModel=RequestForAAsync.split(",");

            try {

                // Toast.makeText(getApplicationContext(),params[3],Toast.LENGTH_SHORT).show();

                // Enter URL address where your php file resides
                String passUrl=params[0];
                url = new URL(passUrl);
                Log.d("myTag", "Checking with server");

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

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("deviceid", params[1])
                        .appendQueryParameter("user", UserModel[0])
                        .appendQueryParameter("model", UserModel[1]);
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

                    Log.d("myTag", "Get response:" + result.toString());

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

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("submit"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */

              token.setText("Authorization request had been submitted. Please contact admin.");


            }else if (result.equalsIgnoreCase("requested")){

                // If username and password does not match display a error message
                token.setText("Please contact admin to expedite approval of authorization on this device.");

                //Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();

            } else  {

                     token.setText(result);

                 Toast.makeText(getApplicationContext(),"Something went wrong. Connection problem",Toast.LENGTH_SHORT).show();

            }
        }

    }

}



