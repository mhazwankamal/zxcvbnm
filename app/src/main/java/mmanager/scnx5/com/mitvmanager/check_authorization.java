package mmanager.scnx5.com.mitvmanager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class check_authorization extends AppCompatActivity {
    private RequestPermissionHandler mRequestPermissionHandler;
    private DeviceUuidFactory DeviceUUIDNo;
    private Context context = this;
    public String uniqueID;
    public String personID="No-one";
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public CardView cardViewError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_authorization);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            personID = extras.getString("PersonID");
            //  Toast.makeText(getApplicationContext(),personID,Toast.LENGTH_LONG).show();
        }

        cardViewError=(CardView)findViewById(R.id.card_view);
        cardViewError.setVisibility(View.GONE);

        mRequestPermissionHandler = new RequestPermissionHandler();

        //Generate UUID no
        DeviceUUIDNo = new DeviceUuidFactory(context.getApplicationContext());
        uniqueID=DeviceUUIDNo.getDeviceUuid().toString();

        String urlLogin="https://layar3.com/apps/checkdevice.php";
        new check_authorization.AsyncRequestAuthorization().execute(urlLogin,uniqueID);

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

    private class AsyncRequestAuthorization extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(check_authorization.this);
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



             //  clickme.setEnabled(true);
             //   LinearLayout serverLI=(LinearLayout) findViewById(R.id.serverlayout);
            //    serverLI.setBackgroundResource(R.drawable.serverstatus_background);

           //     TextView serverText=(TextView)findViewById(R.id.ServertextView);
            //    serverText.setText("Welcome " + personID + "!");
            //    serverText.setTextColor(Color.WHITE);

                //getIPaddress
             //   String dateExpiry=getURL("http://scnx5.sytes.net/mitv/getExpiry.php?user=" + personID);

           //     token.setText("Device : " + Build.MANUFACTURER + " " + Build.MODEL + "\nAndroid Version :" + Build.VERSION.RELEASE + "\nExpiry Date : " + dateExpiry + "\nMiTV-Manager-v1.2");

//            token.setText(String.format("Manufacturer : %s\nModel       : %s\nBrand       : %s\nAndroid OS  : Android %s", Build.MANUFACTURER, Build.MODEL, Build.BRAND, Build.VERSION.SDK_INT));

                Intent i = new Intent(getBaseContext(), main_menu.class);
                i.putExtra("PersonID", personID);
                i.putExtra("DeviceID", uniqueID);
                finish();
                startActivity(i);


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
                String requestAuthorizeURL="https://layar3.com/apps/request.php";
              //  String requestAuthorization ="http://scnx5.sytes.net/mitv/request.php?Uid=" + uniqueID + "&user=" + personID + "&model=" + model;
                String personModel=personID + "," + model;


                new check_authorization.AsyncSubmitRequestforAuthorization().execute(requestAuthorizeURL,uniqueID,personModel);


            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(getApplicationContext(),"Something went wrong. Connection problem",Toast.LENGTH_SHORT).show();

            }
        }

    }


    private class AsyncSubmitRequestforAuthorization extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(check_authorization.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tCheck...");
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
                cardViewError.setVisibility(View.VISIBLE);
                TextView errorMessage=(TextView) findViewById(R.id.error_display);
                errorMessage.setText("Hi " + personID + "\nYour device is not authorized. Please request admin to authorize your device.");

            }else if (result.equalsIgnoreCase("requested")){

                // If username and password does not match display a error message
              //  token.setText("Please contact admin to expedite approval of authorization on this device.");

                cardViewError.setVisibility(View.VISIBLE);
                TextView errorMessage=(TextView) findViewById(R.id.error_display);
                errorMessage.setText("Hi " + personID + "\nYour device is not authorized. However, request for authorization had been submitted.Please request admin to authorize your device.");

                //Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();

            } else  {
                cardViewError.setVisibility(View.VISIBLE);
                TextView errorMessage=(TextView) findViewById(R.id.error_display);
                errorMessage.setText("Something went wrong. Connection problem");

              //  Toast.makeText(getApplicationContext(),"Something went wrong. Connection problem",Toast.LENGTH_SHORT).show();

            }
        }

    }

}
