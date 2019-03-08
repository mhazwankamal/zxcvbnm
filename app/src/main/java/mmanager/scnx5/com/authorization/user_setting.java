package mmanager.scnx5.com.authorization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;

import net.cryptobrewery.macaddress.MacAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.videolan.libvlc.Dialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;

import de.hdodenhof.circleimageview.CircleImageView;
import mmanager.scnx5.com.login.login_activity;
import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.redbox_grid_activity;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;
import mmanager.scnx5.com.mitvmanager.old_getURL;
import mmanager.scnx5.com.vd452ax3;

public class user_setting extends AppCompatActivity {

    private String username,remainningdays,server,personID;
    private getURL wget=new getURL();
    private old_getURL old_wget=new old_getURL();
    private FrameLayout rootV;
    CircleImageView profile_image;
    private String tk;
    private String Json;
    private String JsonUserSetting;
    private String password,expiry;
    private get_menu_classs_navigation menu_navigation=new get_menu_classs_navigation();
    private SharedPreferences pref,settingPref,appupdater;
    private SharedPreferences.Editor editor,settingEditor,appupdateredit;
    private Boolean loadingPage=false;
    private vd452ax3 b=new vd452ax3();
    private Boolean okhttp=false;
    private   LinearLayout showUpdateLinear;
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
            personID=extras.getString("username");
            server = extras.getString("server");
            tk = extras.getString("tk");

        }



        settingPref=getApplication().getSharedPreferences("setting",MODE_PRIVATE);
        settingEditor =settingPref.edit();
        okhttp = settingPref.getBoolean("httpclient",false);

        showUpdateLinear=(LinearLayout)findViewById(R.id.showupdate_linearl);

        appupdater=getApplication().getSharedPreferences(user_setting.this.getPackageName() + "_preferences",MODE_PRIVATE);
        appupdateredit=appupdater.edit();
        Boolean showUpdate=appupdater.getBoolean("prefAppUpdaterShow",true);

        if (showUpdate){
            showUpdateLinear.setVisibility(View.GONE);
            Log.d("update","show");
        } else {
            showUpdateLinear.setVisibility(View.VISIBLE);
            SwitchCompat showUpdateSwitch=(SwitchCompat)findViewById(R.id.showupdate_switch);

            showUpdateSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appupdateredit.putBoolean("prefAppUpdaterShow",true);
                    appupdateredit.apply();
                }
            });

            Log.d("update","hide");
        }






        if (okhttp){
            okhttp=false;
        } else {
            okhttp=true;
        }

        rootV = (FrameLayout) findViewById(R.id.root);
        LinearLayout liveTV_menu=(LinearLayout)findViewById(R.id.menu_livetv);
        LinearLayout movie_menu=(LinearLayout)findViewById(R.id.home_menu_movie);
        LinearLayout menu_home_button=(LinearLayout)findViewById(R.id.menu_home_ll);
        TextView showpass=(TextView)findViewById(R.id.showpass);
        Glide.with(user_setting.this)
                .asBitmap()
                .load("https://layar3.com/apps/home/l3_background_new.jpg")
                .apply(RequestOptions.centerCropTransform())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        rootV.setBackground(new BitmapDrawable(resource));
                    }
                });
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        Glide.with(this).load(server + "apps/home/profile-pic.png").into(profile_image);


        JsonUserSetting="none";

        try {
            if (okhttp) {
                JsonUserSetting = wget.getURL(server + "apps/setting/getUserInfo.php?tk=" + tk);
            } else {
                JsonUserSetting = old_wget.getURL(server + "apps/setting/getUserInfo.php?tk=" + tk);

            }
           // Json = wget.getURL(server + b.rvd452ax3() + ".php?tk=" + tk);

        } catch (IOException e) {
            e.printStackTrace();
        }

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
        Macaddress = pref.getString("deviceID",null);

        menu_home_button.setVisibility(View.GONE);
        movie_menu.setVisibility(View.GONE);
        liveTV_menu.setVisibility(View.GONE);

        menu_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i =new Intent();
                i.setClassName(user_setting.this,menu_navigation.getHomeActivity());
                i.putExtra("PersonID", username);
                i.putExtra("token", tk);
                i.putExtra("server", server);
                startActivity(i);
                finishAffinity();
                // finish();
            }
        });


        liveTV_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(newui_logout_main.this, exoplayer_layar.class);

                if(loadingPage) {
                    Intent intent = new Intent();
                    intent.setClassName(user_setting.this, menu_navigation.getLiveTVActivity());

                    // passing data to the book activity



                                intent.putExtra("cid",pref.getString("cid",null));
                                intent.putExtra("Title", pref.getString("channel",null));
                                intent.putExtra("Url", pref.getString("Url",null));
                                intent.putExtra("Thumbnail",  pref.getString("Thumbnail",null));
                                intent.putExtra("Sypnopsis",  pref.getString("Sypnopsis",null));
                                intent.putExtra("Category", pref.getString("Category",null));
                                intent.putExtra("channelPos", 0);
                                intent.putExtra("premium", pref.getString("premium",null));

                    //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                    // start the activity
                    intent.putExtra("username", personID);
                    intent.putExtra("liveTV", "rb");
                    intent.putExtra("tk", tk);
                    intent.putExtra("server", server);
                    intent.putExtra("json", Json);

                    user_setting.this.startActivity(intent);
                    finishAffinity();
                }
            }
        });

        movie_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent();
                intent.setClassName(user_setting.this,menu_navigation.getMovieActivity());

                // passing data to the book activity

                intent.putExtra("tk", tk);
                intent.putExtra("server", server);

                //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                // start the activity

                user_setting.this.startActivity(intent);
                finishAffinity();
            }
        });



        if (JsonUserSetting.equalsIgnoreCase("none")){
            Toast.makeText(getApplicationContext(),"There is something wrong with your internet connection!",Toast.LENGTH_SHORT).show();

        } else {

            loadingPage=true;

            JSONObject objectPremium;
            try {
                objectPremium = new JSONObject(String.valueOf(JsonUserSetting));

                username = objectPremium.getString("username");
                password= objectPremium.getString("password");
                expiry= objectPremium.getString("expiry");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            TextView usernameTx=(TextView)findViewById(R.id.setting_username);
            TextView passwordTx=(TextView)findViewById(R.id.setting_password);
            TextView expiryDateTx=(TextView)findViewById(R.id.setting_expiry_date);
            TextView days=(TextView)findViewById(R.id.setting_expiry_days);
            TextView deviceModel=(TextView)findViewById(R.id.setting_devicemodel);
            TextView deviceid=(TextView)findViewById(R.id.setting_deviceid);
            TextView androidOS=(TextView)findViewById(R.id.setting_deviceOS);

            usernameTx.setText(username);

            passwordTx.setText("********");

            showpass.setOnClickListener(new View.OnClickListener() {

                Boolean showpassword=false;

                @Override
                public void onClick(View view) {

                    if(!showpassword){
                        showpassword=true;
                        showpass.setText("hide");
                        passwordTx.setText(password);

                    } else {
                        showpassword=false;
                        showpass.setText("show");
                        passwordTx.setText("********");
                    }
                }
            });



            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
            String expiryDate = formatter.format(new Date(Long.parseLong(expiry) * 1000));

            long currentTime = System.currentTimeMillis()/1000;

            long remainingDays=Long.parseLong(expiry)-currentTime;

         //   remainingDays=remainingDays+(3600*8);

            Integer remainDays;

           // Log.d("remain","R=" + String.valueOf(remainingDays));

            remainDays=(int)remainingDays/86400;



            String remainingSubs;

                    if (remainDays == 0){

                        remainDays=(int)remainingDays/3600;

                        if (remainDays == 0){

                            remainDays=(int) remainingDays/60;

                            if (remainDays == 0){

                                remainingSubs=String.valueOf(remainDays) + " sec(s)";

                            } else {

                                remainingSubs=String.valueOf(remainDays) + " min(s)";
                            }


                        } else {

                            remainingSubs=String.valueOf(remainDays) + " hour(s)";
                        }


                    } else {

                        Integer hoursS=(int) remainingDays - (remainDays * 86400);

                        if (hoursS > 0){
                            Integer hours=(int) hoursS/3600;

                            remainingSubs=String.valueOf(remainDays) + " day(s) " + hours + " hour(s)";
                        } else {

                            remainingSubs = String.valueOf(remainDays) + " day(s)";
                        }

                    }

            expiryDateTx.setText(expiryDate);
            days.setText(remainingSubs);

            deviceModel.setText(Build.MANUFACTURER + " " + Build.MODEL);

            pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            editor = pref.edit();
            Macaddress = pref.getString("deviceID",null);

            if (Macaddress == null) {

                Macaddress=MacAddress.getMacAddr();

                if (Macaddress.equalsIgnoreCase("00:00:00:00:00:00")){
                    Macaddress= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                }


                editor.putString("deviceID", Macaddress);
                editor.apply();
            }

            deviceid.setText(Macaddress);
            androidOS.setText(currentVersion());

            Button logout=(Button)findViewById(R.id.setting_logout);

            logout.setOnClickListener(new View.OnClickListener() {
                ProgressDialog pdLoading = new ProgressDialog(user_setting.this);

                @Override
                public void onClick(View view) {

                    String logout=null;

                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();

                    try {
                        if(okhttp) {
                            logout = wget.getURL(server + "apps/exoplayer/update_offlinev4.php?username=" + username + "&mac_address=" + Macaddress);
                        } else {
                            logout = old_wget.getURL(server + "apps/exoplayer/update_offlinev4.php?username=" + username + "&mac_address=" + Macaddress);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    SharedPreferences jsonDownloaded =getApplication().getSharedPreferences("JsonHomePage",MODE_PRIVATE);
                    SharedPreferences.Editor jsonDownloadedEdit=jsonDownloaded.edit();

                    jsonDownloadedEdit.putString("jsondownloded_livenevent_json","none");
                    jsonDownloadedEdit.putString("jsondownloded_json","none");
                    jsonDownloadedEdit.putString("jsondownloded_json_code","none");
                    jsonDownloadedEdit.putString("jsondownloded_recentchannel_json","none");
                    jsonDownloadedEdit.putString("jsondownloded_trendingchannel_json","none");
                    jsonDownloadedEdit.putString("jsondownloded_mostpopular_json","none");
                    jsonDownloadedEdit.putLong("time_json_downloaded",0);
                    jsonDownloadedEdit.apply();

                    if (logout.equalsIgnoreCase("out")){

                        pdLoading.dismiss();

                        Intent intent = new Intent(user_setting.this, login_activity.class);
                        intent.putExtra("logout",true);

                        startActivity(intent);
                        finishAffinity();
                    } else {
                        pdLoading.dismiss();
                        Toast.makeText(getApplicationContext(),"There is something wrong with your internet connection. You are not properly logged out.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(user_setting.this, login_activity.class);
                        intent.putExtra("logout",true);

                        startActivity(intent);
                        finishAffinity();
                    }
                }
            });


        }

        SwitchCompat okhttp_switch=(SwitchCompat)findViewById(R.id.okhttp_switch);

        if (settingPref.getBoolean("httpclient",false)){
            okhttp_switch.setChecked(true);
        } else {
            okhttp_switch.setChecked(false);
        }

        okhttp_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (okhttp_switch.isChecked()){
                    settingEditor.putBoolean("httpclient",true);
                    settingEditor.apply();
                }  else {
                    settingEditor.putBoolean("httpclient",false);
                    settingEditor.apply();
                }

            }
        });

        SwitchCompat EPGSwitch=(SwitchCompat)findViewById(R.id.epg_switch);


        if(settingPref.getBoolean("epgenable",true)){
            EPGSwitch.setChecked(true);
        } else {
            EPGSwitch.setChecked(false);
        }

        EPGSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(EPGSwitch.isChecked()){
                    settingEditor.putBoolean("epgenable",true);
                    settingEditor.apply();

                    Log.d("settingEPG",String.valueOf(settingPref.getBoolean("epgenable",false)));
                } else {
                    settingEditor.putBoolean("epgenable",false);
                    settingEditor.apply();

                    Log.d("settingEPG",String.valueOf(settingPref.getBoolean("epgenable",false)));
                }

            }
        });

    }

    public static String currentVersion(){
        double release=Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1"));
        String codeName="Unsupported";//below Jelly bean OR above Oreo
        if(release>=4.1 && release<4.4)codeName="Jelly Bean";
        else if(release<5)codeName="Kit Kat";
        else if(release<6)codeName="Lollipop";
        else if(release<7)codeName="Marshmallow";
        else if(release<8)codeName="Nougat";
        else if(release<9)codeName="Oreo";
        else if(release<10)codeName="Pie";
        return  "Android "+ codeName+" v"+release+", API Level: "+Build.VERSION.SDK_INT;
    }
}
