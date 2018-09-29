package mmanager.scnx5.com.mitvmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class login_activity extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private EditText etEmail;
    private EditText etPassword;
    private String loginUrl="https://layar3.com/apps/login_fix.php";
    public String email;
    public String userpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etEmail=(EditText)findViewById(R.id.username);
        etPassword=(EditText)findViewById(R.id.password);


        if(checkFileExist("userinfo.txt")){


            File file = new File(getApplicationContext().getFilesDir(),"userinfo.txt");

//Read text from file
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
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

            userpass=text.toString();
            String[] userlogin=userpass.split(",");
         //     Toast.makeText(this,userpass, Toast.LENGTH_LONG).show();
           etEmail.setText(userlogin[0]);
           etPassword.setText(userlogin[1].trim());

            email = etEmail.getText().toString();
            final String password = etPassword.getText().toString();

            userpass=email + "," + password;

            new CountDownTimer(10, 10) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {

                    new AsyncLogin().execute(loginUrl,userpass);
                }

            }.start();



        }



        final Button loginB=(Button) findViewById(R.id.loginBtn);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



             //   EditText personIDET =(EditText) findViewById(R.id.editText) ;
               // String personID=personIDET.getText().toString();
              //  Intent i = new Intent(getBaseContext(), MainActivity.class);
               // i.putExtra("PersonID", personID);
               // startActivity(i);
                // Get text from email and passord field



                email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                userpass=email + "," + password;
                // Initialize  AsyncLogin() class with email and password


                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Username and Password.",Toast.LENGTH_SHORT).show();

                }else {

                    new AsyncLogin().execute(loginUrl, userpass);
                }
               // String model="test";
                //new AsyncLogin().execute(loginUrl,email,password,model);

            }
        });

    }



    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(login_activity.this);
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

            String userpassAsync=params[1];

            String[] userinfo=userpassAsync.split(",");

            try {

               // Toast.makeText(getApplicationContext(),userinfo[1] + " " + userinfo[2],Toast.LENGTH_SHORT).show();
                Log.d("userinfo",userinfo[0] + " " + userinfo[1]);
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
                        .appendQueryParameter("username", userinfo[0])
                        .appendQueryParameter("password", userinfo[1]);
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

                String filename ="userinfo.txt";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(userpass.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent i = new Intent(getBaseContext(), check_authorization.class);
                 i.putExtra("PersonID", email);
                 startActivity(i);


            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(getApplicationContext(),"Something went wrong. Connection problem",Toast.LENGTH_SHORT).show();

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
