package mmanager.scnx5.com.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.cryptobrewery.macaddress.MacAddress;

import org.videolan.libvlc.Dialog;

import java.io.IOException;

import javax.crypto.Mac;

import mmanager.scnx5.com.login.login_activity;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.redbox_grid_activity;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;

public class user_setting extends AppCompatActivity {

    private String username,remainningdays,server;
    private getURL wget=new getURL();
/*  private String Macaddress=MacAddress.getMacAddr();
  */
  private String Macaddress;
  private log_ dlog=new log_();
    private boolean debug=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);


        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            username = extras.getString("username");
            remainningdays= extras.getString("remaining");
            server= extras.getString("server");

            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }

    /*  if (Macaddress.equalsIgnoreCase("00:00:00:00:00:00")){
            Macaddress= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        }*/

        TextView usernameTxt=(TextView)findViewById(R.id.usernameTxt);
        usernameTxt.setText(username);

        TextView ExpiryLeft=(TextView)findViewById(R.id.expiry);
        ExpiryLeft.setText(remainningdays + " day(s) remaining");

        LinearLayout logoutButton=(LinearLayout)findViewById(R.id.SETTINGLL);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        Macaddress=pref.getString("deviceID",null);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String logoutResponse = null;
                try {
                    logoutResponse = wget.getURL(server + "apps/exoplayer/update_offlinev4.php?mac_address=" + Macaddress + "&username=" + username);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                dlog.log_d(debug,"logoutresponse",logoutResponse);

                if (logoutResponse.equalsIgnoreCase("out")){

                    Intent i=new Intent(getApplicationContext(),login_activity.class);
                    i.putExtra("logout",true);
                    finishAffinity();
                    startActivity(i);


                } else {
                    Toast.makeText(getApplicationContext(),"Unkown error. Please try again later",Toast.LENGTH_SHORT).show();
                }




            }
        });

    }
}
