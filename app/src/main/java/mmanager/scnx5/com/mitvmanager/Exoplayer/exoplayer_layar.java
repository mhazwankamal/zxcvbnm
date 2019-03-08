package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.BoringLayout;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alkathirikhalid.util.ConnectionAppCompactActivity;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;

import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.TransitionManager;

import net.cryptobrewery.macaddress.MacAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TimeZone;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import mmanager.scnx5.com.abcxtengtyou;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.abdyxoorp;
import mmanager.scnx5.com.authorization.get_menu_classs_navigation;
import mmanager.scnx5.com.authorization.newui_logout_main;
import mmanager.scnx5.com.authorization.user_setting;
import mmanager.scnx5.com.decrypt.decrypt;
import mmanager.scnx5.com.mitvmanager.FloatingMiTV;
import mmanager.scnx5.com.mitvmanager.R;


import mmanager.scnx5.com.mitvmanager.RedBoxGrid.LiveBook;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.redbox_grid_activity;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;
import mmanager.scnx5.com.mitvmanager.old_getURL;
import mmanager.scnx5.com.mitvmanager.setViewSizeByReso;
import mmanager.scnx5.com.vd452ax3;


import static android.view.View.VISIBLE;
import static java.lang.Integer.parseInt;

public class exoplayer_layar extends ConnectionAppCompactActivity implements Player.EventListener {

    private String Url, Sypnopsis;
    private Context context;
    private SurfaceView surface;
    private ExoPlayer player;
    private PlayerView simpleExoPlayerView;
    public TextView Channel,resolution;
    public LinearLayout HeaderPlayer;
    public LinkedHashSet<String> SETVODJsonCategory;
    public List<LiveBook> VOD, AllChannel;
    public List<ExoBook> EPG;

    public List<String> VODJsonId;
    public List<String> VODJsonName;
    public List<String> VODJsonLogoPath;
    public List<String> VODJsonUrl;
    public List<String> VODJsonCategory;
    public List<String> VODJsonsypnopsis;
    public List<String> VODJsonpremium;
    public List<Integer> VODJsonepg;
    public List<String> VODJsonprograminfo;
    public List<String> VODJsonprograminfo2;
    public ArrayList<String> VODCat;
    private DefaultBandwidthMeter bandwidthMeter;
    private DefaultAllocator allocator;
    private TrackSelector trackSelector;
    private int counter = 0;
    private Date initiateDate, nextDate;
    private DefaultLoadControl loadControl;
    private long bufferedDurationMs;
    private boolean allowOSD=false;
    private Uri videoUri;
    private DataSource.Factory dataSourceFactory;
    private MediaSource videoSource;
    private TrackSelection.Factory videoTrackSelectionFactory;
    private ProgressDialog pdLoading;
    private long timerCountDownBuffering;
    private Integer maxretrybuffer;
    private boolean firststart=true;
    private static String EEE = ".php";
    private String tk = "", channel, Watchcategory, thumbnail,server,channelid,json,premium;
    private Integer channelPosition=0;
    private String logoutResponse="";
    private Boolean controllerView = false;
   // private APIService mAPIService;
    private Handler handler = new Handler();
    private String Macaddress;
    private String Model = Build.MODEL;
    private String DeviceModel;
    private String Manufacturer = Build.MANUFACTURER;
    private String IPAddressV4;
    private decrypt d=new decrypt();
    private String username, passwrd, updateinfo;
    private Boolean stoprunnable=false;
    private log_ dlog=new log_();
    private Boolean debug=false;
    private vd452ax3 b=new vd452ax3();
    public RecyclerView myrvRB,myrvRBSwitchChannel;
    public RecyclerViewAdapterCategoryExoPlayer myAdapterRB;
    public RecyclerViewAdapterSwitchChannelExoPlayer MyAdapterRBSwitchChannel;
    private TimeBar myTimeBar;
    private ImageButton play,pause;
    private Integer HLSWidth,HLSHeight;
    private FrameLayout frameSwitchChannel,frameSwichCategory,FrameHeaderMenu;
    private TextView ChannelCategory,ChannelViews;
    private LinearLayout premiumText;
    private   getURL wget=new getURL();
    private old_getURL old_wget=new old_getURL();
    private String epgJson;
    private TextView nowShowingProgram,channeltimeline,channelNameTxt;
    private boolean epgframeopen=false;
    private Boolean falseVersion=false,maxConnection=false;
    private String firstDevice,SecounDevice;
    private ImageView ChannelIcon;
    private CountDownTimer sourceerrorTimer;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref,connectPref,setttingPref;
    private get_menu_classs_navigation menu_navigation=new get_menu_classs_navigation();
    private ImageView InternetSpeed;
    private String remainingdays,personID;
    private Boolean switchChannelExoPlayer;
    private String CurrentchannelViews,ua="",host="none",port;
    private Boolean Exoplayingstate=false;
    private FrameLayout switchChannel,epg_channel;
    private ListView listviewCat;
    private LinearLayout epg_ll_switchChannel;
    private  RecyclerViewAdapterRBExo myAdapterRBExo;
    private RecyclerView ChannelListingGridView;
    private LinearLayout gridview_channelswitch_frame;
    private ImageView logo_l3;
    private CountDownTimer showToggleCount;
    private Boolean exoplayertimerun=false;
    private double width,height;
    private Boolean httperror=false;
    private Boolean okhttp;
    private CountDownTimer BufferingCount;
    private Boolean EPGenable;
    private String yatchlink="";
    private Boolean setProxyUp=false;
    private TextView exo_layar_clock;
    private String md5;
    private setViewSizeByReso setView=new setViewSizeByReso();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
       // java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
      //  java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exoplayer_layar_1080);
        dlog.log_d(debug,"contentView","1080");

       // height= Resources.getSystem().getDisplayMetrics().heightPixels;
        width= Resources.getSystem().getDisplayMetrics().widthPixels;
//        dlog.log_d(debug,"height",String.valueOf(height));
//      //  Toast.makeText(getApplicationContext(),"height " + String.valueOf(height) + " Width=" + width,Toast.LENGTH_SHORT).show();
//        if (height > 1080 && width > 1920){
//            setContentView(R.layout.activity_exoplayer_layar_1440);
//            dlog.log_d(debug,"contentView","1440");
//           // Toast.makeText(getApplicationContext(),"1440",Toast.LENGTH_SHORT).show();
//        } else if (height > 720 && width > 1900){
//          setContentView(R.layout.activity_exoplayer_layar_1080);
//            dlog.log_d(debug,"contentView","1080");
//           // Toast.makeText(getApplicationContext(),"1080",Toast.LENGTH_SHORT).show();
//        } else {
//            setContentView(R.layout.activity_exoplayer_layar_720);
//            dlog.log_d(debug,"contentView","720");
//           // Toast.makeText(getApplicationContext(),"720",Toast.LENGTH_SHORT).show();
//        }

        int setChannelwidth =(int) (width * 0.67);
        LinearLayout FrameChannelPick=(LinearLayout) findViewById(R.id.vodlisting);
        FrameChannelPick.getLayoutParams().width = setChannelwidth;
        dlog.log_d(debug,"width",String.valueOf(setChannelwidth));

        int setTimeBarwidth =(int) (width * 0.86);
        DefaultTimeBar exotimebar=(DefaultTimeBar) findViewById(R.id.timebarexoplayer);
        exotimebar.getLayoutParams().width=setTimeBarwidth;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        context = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Url = extras.getString("Url");
            Sypnopsis = extras.getString("Sypnopsis");
            tk = extras.getString("tk");
            thumbnail = extras.getString("Thumbnail");
            channel = extras.getString("Title");
            Watchcategory = extras.getString("Category");
            server = extras.getString("server");
            channelid = extras.getString("cid");
            json=extras.getString("json");
            channelPosition=extras.getInt("channelPos");
            premium=extras.getString("premium");
            personID=extras.getString("username");
            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }


       // mAPIService = GetApiUtils.getAPIService("");

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        connectPref = getApplicationContext().getSharedPreferences("connection", MODE_PRIVATE);

        setttingPref=getApplication().getSharedPreferences("setting",MODE_PRIVATE);
        okhttp = setttingPref.getBoolean("httpclient",false);
        EPGenable =setttingPref.getBoolean("epgenable",true);

        if (okhttp){
            okhttp=false;
        } else {
            okhttp=true;
        }

        if(EPGenable){
            EPGenable=true;
        } else {
            EPGenable=false;
        }

       // Log.d("okhttp","get " + String.valueOf(okhttp));

        Macaddress = pref.getString("deviceID",null);

        if (Macaddress == null) {

            Macaddress=MacAddress.getMacAddr();

            if (Macaddress.equalsIgnoreCase("00:00:00:00:00:00")){
                Macaddress= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            }


            editor.putString("deviceID", Macaddress);
            editor.apply();
        }
        decrypt dc = new decrypt();
        String tkn = dc.decryptStr(tk);

        JSONObject objectPremium = null;

        try {
            objectPremium = new JSONObject(String.valueOf(tkn));
            username = objectPremium.getString("username");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        HeaderPlayer = (LinearLayout) findViewById(R.id.header);
        FrameHeaderMenu=(FrameLayout)findViewById(R.id.exoplayer_header);
        play = (ImageButton) findViewById(R.id.play_exo_button);
        pause = (ImageButton) findViewById(R.id.pause_exo_button);

        HeaderPlayer.setVisibility(View.GONE);
        FrameHeaderMenu.setVisibility(View.GONE);

        exo_layar_clock=(TextView)findViewById(R.id.exo_clock);

       /* play.setVisibility(View.GONE);
        pause.setVisibility(View.GONE);*/



       /* new CountDownTimer(2000, 2000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

              //  HeaderPlayer.setVisibility(View.GONE);
                if (!maxConnection && !falseVersion) {
                    new exoplayer_layar.LoadVODInBookList().execute();
                }
            }

        }.start();*/


        firststart = true;
        frameSwitchChannel= (FrameLayout) findViewById(R.id.frame_exo_switch_channel);
        switchChannel = (FrameLayout) findViewById(R.id.switch_channel_exo);
        epg_channel = (FrameLayout) findViewById(R.id.switch_channel_exo_epg);
        simpleExoPlayerView = findViewById(R.id.exoplayer);

        myTimeBar=(TimeBar)findViewById(R.id.timebarexoplayer);
        resolution=(TextView)findViewById(R.id.resolution);
        frameSwichCategory = (FrameLayout)findViewById(R.id.exo_channel_category_focus);
        ChannelViews = (TextView)findViewById(R.id.views);
        premiumText=(LinearLayout)findViewById(R.id.premium);

        epg_ll_switchChannel = (LinearLayout)findViewById(R.id.epg_ll_switchchannel);
        ChannelListingGridView = (RecyclerView)findViewById(R.id.recyclerview_id);
        listviewCat = (ListView) findViewById(R.id.redboxvodcategorlist);
        gridview_channelswitch_frame=(LinearLayout)findViewById(R.id.gridview_channelswitch_frame);
        gridview_channelswitch_frame.setVisibility(View.GONE);
        
        ChannelCategory = (TextView)findViewById(R.id.recyclerview_channel_category_top) ;
        myrvRBSwitchChannel= (RecyclerView) findViewById(R.id.recyclerview_channel_category);

        frameSwitchChannel.setVisibility(View.GONE);
        FrameHeaderMenu.setVisibility(View.GONE);

        channelNameTxt = (TextView) findViewById(R.id.channelname);
        ChannelIcon = (ImageView) findViewById(R.id.channelIcon);
        nowShowingProgram=(TextView)findViewById(R.id.nowshowProgram);
        channeltimeline=(TextView)findViewById(R.id.channel_timeline);

        InternetSpeed=(ImageView)findViewById(R.id.internet_speed);
      //  logo_l3=(ImageView)findViewById(R.id.logo_l3_exo);
       // logo_l3.setVisibility(View.GONE);
        //menu navigation
        LinearLayout menu_home_button=(LinearLayout)findViewById(R.id.menu_home_ll);
        LinearLayout movie_menu=(LinearLayout)findViewById(R.id.home_menu_movie);
        LinearLayout setting_menu=(LinearLayout)findViewById(R.id.menu_settings_ll);





        menu_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i =new Intent();
                i.setClassName(exoplayer_layar.this,menu_navigation.getHomeActivity());
                i.putExtra("PersonID", username);
                i.putExtra("token", tk);
                i.putExtra("server", server);
                startActivity(i);
                releaseall();
                finishAffinity();
               // finish();
            }
        });

        movie_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent();
                intent.setClassName(exoplayer_layar.this,menu_navigation.getMovieActivity());

                // passing data to the book activity

                intent.putExtra("tk", tk);
                intent.putExtra("server", server);

                //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                // start the activity

                exoplayer_layar.this.startActivity(intent);
            }
        });


        setting_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                intent.setClassName(exoplayer_layar.this,menu_navigation.getSettingActivity());

                // passing data to the book activity


                intent.putExtra("server", server);
                intent.putExtra("tk",tk);

                //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                // start the activity

                exoplayer_layar.this.startActivity(intent);
            }
        });

        switchChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlog.log_d(debug,"test click","click");
                if (epgframeopen) {
   //                 frameSwitchChannel.setVisibility(View.VISIBLE);
//                myrvRB.setVisibility(View.VISIBLE);
                    gridview_channelswitch_frame.setVisibility(View.VISIBLE);
  //                  myrvRBSwitchChannel.setVisibility(View.VISIBLE);
    //                myrvRBSwitchChannel.scrollToPosition(channelPosition);
                   editor.putInt("channelPos",channelPosition);
                   editor.apply();
                    HeaderPlayer.setVisibility(View.GONE);
                    FrameHeaderMenu.setVisibility(View.GONE);

                   // new exoplayer_layar.LoadVODInBookList().execute();
                }

            }
        });

        epg_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (epgframeopen) {
                    frameSwitchChannel.setVisibility(View.VISIBLE);
//                    myrvRB.setVisibility(View.VISIBLE);
                    //gridview_channelswitch_frame.setVisibility(View.VISIBLE);
                    myrvRBSwitchChannel.setVisibility(View.VISIBLE);
                    myrvRBSwitchChannel.scrollToPosition(channelPosition);
                    editor.putInt("channelPos",channelPosition);
                    editor.apply();
                    HeaderPlayer.setVisibility(View.GONE);
                    FrameHeaderMenu.setVisibility(View.GONE);

                    // new exoplayer_layar.LoadVODInBookList().execute();
                }

            }
        });

      /*  play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player.setPlayWhenReady(true);
                simpleExoPlayerView.hideController();
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(false);
                simpleExoPlayerView.hideController();
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
            }
        });*/

        handler.removeCallbacksAndMessages(null);


    /*    String DeviceModel=Manufacturer + " " + Model;

        Intent FloatWinIntent =new Intent(exoplayer_layar.this,FloatingMiTV.class);
        FloatWinIntent.putExtra("mac_address", Macaddress);
        FloatWinIntent.putExtra("username", username);
        FloatWinIntent.putExtra("model", DeviceModel);
        FloatWinIntent.putExtra("channelid", channelid);
        FloatWinIntent.putExtra("server", server);
        startService(FloatWinIntent);
  */


        setView.setSize(epg_ll_switchChannel,0.8,-1);

        setOnGestureListeners();
    }


    @Override
    protected void onStart() {
        super.onStart();
     //   initializePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();

        okhttp = setttingPref.getBoolean("httpclient",false);
        EPGenable =setttingPref.getBoolean("epgenable",true);

        if (okhttp){
            okhttp=false;
        } else {
            okhttp=true;
        }

        if(EPGenable){
            EPGenable=true;
        } else {
            EPGenable=false;
        }

        releaseall();
        initializePlayer();

        if (HeaderPlayer.getVisibility() == View.VISIBLE || frameSwitchChannel.getVisibility() == View.VISIBLE){
            allowOSD=false;
            handler.postDelayed(showBuffered,1);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        releaseall();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseall();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseall();
    }

    private void releaseall() {
        if (player != null) {
            player.release();
            player = null;
        }
        if(sourceerrorTimer != null)
        sourceerrorTimer.cancel();

        dlog.log_d(debug,"firststart",String.valueOf(firststart));
      /*  if(!firststart) {

            if (!logoutResponse.equalsIgnoreCase("out")) {
                try {
                    logoutResponse = wget.getURL(server + "apps/exoplayer/update_offlinev3.php?mac_address=" + Macaddress + "&username=" + username + "&channel=" + channelid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                dlog.log_d(debug, "logout", server + "apps/exoplayer/update_offlinev3.php?mac_address=" + Macaddress + "&username=" + username + "&channel=" + channelid);

            }
        }
*/
        setupProxy(false);
        handler.removeCallbacks(ExoPlayerTimer);
        handler.removeCallbacks(showBuffered);
     //   trimCache();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(showToggleCount!=null)
        showToggleCount.cancel();
        if (parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();

            return true;
        }

        if (parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_DPAD_CENTER
                && event.getRepeatCount() == 0) {

            toggleShowController();
            return true;
        }

        if (parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_ENTER
                && event.getRepeatCount() == 0) {

            toggleShowController();
            return true;
        }

        if (frameSwitchChannel.getVisibility() == VISIBLE) {

            if (parseInt(Build.VERSION.SDK) > 5
                    && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
                    && event.getRepeatCount() == 0) {

                String direction = "right";
                setCategoryScroll(direction);

                return true;
            }

            if (parseInt(Build.VERSION.SDK) > 5
                    && keyCode == KeyEvent.KEYCODE_DPAD_LEFT
                    && event.getRepeatCount() == 0) {
                String direction = "left";
                setCategoryScroll(direction);

                return true;
            }


        }
        dlog.log_d(debug,"keypress",String.valueOf(keyCode) + " " + String.valueOf(event));

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (HeaderPlayer.getVisibility() == VISIBLE) {

            ObjectAnimator.ofFloat(HeaderPlayer, View.ALPHA, 1.0f, 0.0f).setDuration(500).start();
            ObjectAnimator.ofFloat(FrameHeaderMenu, View.ALPHA, 1.0f, 0.0f).setDuration(500).start();

            new CountDownTimer(500, 500) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    HeaderPlayer.setVisibility(View.GONE);
                    FrameHeaderMenu.setVisibility(View.GONE);
                   }

            }.start();


            handler.removeCallbacks(showBuffered);
          //  simpleExoPlayerView.hideController();

        } else if (frameSwitchChannel.getVisibility() == VISIBLE) {
            frameSwitchChannel.setVisibility(View.GONE);
          //  myrvRB.setVisibility(View.GONE);
            myrvRBSwitchChannel.setVisibility(View.GONE);
            HeaderPlayer.setVisibility(View.VISIBLE);
            FrameHeaderMenu.setVisibility(View.VISIBLE);
            handler.postDelayed(showBuffered,1);
        }else if(gridview_channelswitch_frame.getVisibility() == VISIBLE){

            gridview_channelswitch_frame.setVisibility(View.GONE);
            HeaderPlayer.setVisibility(View.VISIBLE);
            FrameHeaderMenu.setVisibility(View.VISIBLE);
            handler.postDelayed(showBuffered,1);

        } else {
            Intent i =new Intent();
            i.setClassName(exoplayer_layar.this,menu_navigation.getHomeActivity());
            i.putExtra("PersonID", username);
            i.putExtra("token", tk);
            i.putExtra("server", server);
            startActivity(i);
            releaseall();
            finishAffinity();

        }
    }


    public void setCategoryScroll(String direction){

        ViewGroup container=(ViewGroup)findViewById(R.id.frame_exo_switch_channel);

        TransitionManager.beginDelayedTransition(container,
                new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));


        String CurrentCategory=ChannelCategory.getText().toString();
        String VODCatName="";
        Integer currentPos=0,totalSize=0,displayPosition=0;
        totalSize=VODCat.size();
        for(int i=0;i<VODCat.size();i++){
            VODCatName=VODCat.get(i).toString();
            if(CurrentCategory.equalsIgnoreCase(VODCatName)){
               dlog.log_d(debug,"GetCurrentPosition",String.valueOf(i));
                currentPos=i;
                break;
            }

        }


        if(direction.equalsIgnoreCase("right")){
            if(currentPos<totalSize-1) {
                ChannelCategory.setText(VODCat.get(currentPos + 1).toString());
                displayPosition=currentPos+1;
            } else {
                ChannelCategory.setText(VODCat.get(0).toString());
                displayPosition=0;
            }
        } else if(direction.equalsIgnoreCase("left")){
            if(currentPos==0){
                ChannelCategory.setText(VODCat.get(totalSize - 1).toString());
                displayPosition=totalSize-1;
            } else {
                ChannelCategory.setText(VODCat.get(currentPos - 1).toString());
                displayPosition=currentPos - 1;
            }
        }


        CurrentCategory=VODCat.get(displayPosition).toString();
        String id,name,logoPath,url,category,sypnopsis,premium,ProgInfo1,ProgInfo2;
        Integer epgid;
        EPG=new ArrayList<>();
        for (int i=0;i<VODJsonName.size();i++){
            id =VODJsonId.get(i).toString();
            name=VODJsonName.get(i).toString();
            category=VODJsonCategory.get(i).toString();
            logoPath=VODJsonLogoPath.get(i).toString();
            url=VODJsonUrl.get(i).toString();
            sypnopsis=VODJsonsypnopsis.get(i).toString();
            premium=VODJsonpremium.get(i).toString();
            epgid=VODJsonepg.get(i);
            ProgInfo1=VODJsonprograminfo.get(i).toString();
            ProgInfo2=VODJsonprograminfo2.get(i).toString();
            // try {
            // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
            //} catch (IOException e) {
            //      e.printStackTrace();
            //  }
            if(CurrentCategory.equalsIgnoreCase(category)){
                EPG.add(new ExoBook(name, category, url, logoPath, sypnopsis,id,premium,epgid,ProgInfo1,ProgInfo2));


            }
        }

        MyAdapterRBSwitchChannel=new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar.this,EPG);
        myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);
        // int scrollpos= myAdapterRB.get;
       // dlog.log_d(debug,"getscrollposition",String.valueOf(scrollpos));
      //  myrvRB.scrollToPosition(2);
    }

    public void SyncEPFWithChannelSwitch(Integer channelPos){

      //  String CurrentCategory=ChannelCategory.getText().toString();
        String VODCatName="";
        Integer currentPos=0,totalSize=0,displayPosition=0;
        totalSize=VODCat.size();
//        for(int i=0;i<VODCat.size();i++){
//            VODCatName=VODCat.get(i).toString();
//            if(CurrentCategory.equalsIgnoreCase(VODCatName)){
//                dlog.log_d(debug,"GetCurrentPosition",String.valueOf(i));
//                currentPos=i;
//                break;
//            }
//
//        }


//        if(direction.equalsIgnoreCase("right")){
//            if(currentPos<totalSize-1) {
//                ChannelCategory.setText(VODCat.get(currentPos + 1).toString());
//                displayPosition=currentPos+1;
//            } else {
//                ChannelCategory.setText(VODCat.get(0).toString());
//                displayPosition=0;
//            }
//        } else if(direction.equalsIgnoreCase("left")){
//            if(currentPos==0){
//                ChannelCategory.setText(VODCat.get(totalSize - 1).toString());
//                displayPosition=totalSize-1;
//            } else {
//                ChannelCategory.setText(VODCat.get(currentPos - 1).toString());
//                displayPosition=currentPos - 1;
//            }
//        }


        String CurrentCategory=Watchcategory;
        String id,name,logoPath,url,category,sypnopsis,premium,ProgInfo1,ProgInfo2;
        Integer epgid;
        EPG=new ArrayList<>();
        for (int i=0;i<VODJsonName.size();i++){
            id =VODJsonId.get(i).toString();
            name=VODJsonName.get(i).toString();
            category=VODJsonCategory.get(i).toString();
            logoPath=VODJsonLogoPath.get(i).toString();
            url=VODJsonUrl.get(i).toString();
            sypnopsis=VODJsonsypnopsis.get(i).toString();
            premium=VODJsonpremium.get(i).toString();
            epgid=VODJsonepg.get(i);
            ProgInfo1=VODJsonprograminfo.get(i).toString();
            ProgInfo2=VODJsonprograminfo2.get(i).toString();
            // try {
            // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
            //} catch (IOException e) {
            //      e.printStackTrace();
            //  }
            if(CurrentCategory.equalsIgnoreCase(category)){
                EPG.add(new ExoBook(name, category, url, logoPath, sypnopsis,id,premium,epgid,ProgInfo1,ProgInfo2));


            }
        }

        MyAdapterRBSwitchChannel=new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar.this,EPG);
        myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);
        myrvRBSwitchChannel.smoothScrollToPosition(channelPos);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnGestureListeners() {
        simpleExoPlayerView.setOnTouchListener(new OnSwipeTouchListener(exoplayer_layar.this) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();

                // Swipe to the right
                String direction = "right";
                setCategoryScroll(direction);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                // Swipe to the left
                String direction = "left";
                setCategoryScroll(direction);
            }

            @Override
            public void onClick() {
                super.onClick();
                if(showToggleCount!=null)
                showToggleCount.cancel();
                toggleShowController();

                // User tapped once (This is what you want)
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();

                // User tapped twice
            }


        });
    }

    private void setupProxy(boolean set) {



        if (set) {
            String puser,ppass;

                abdyxoorp z = new abdyxoorp();


                abcyxoorp x = new abcyxoorp();

                // Toast.makeText(login_activity.this,x.xyoprup()+ z.xyxoprup() + ".php",Toast.LENGTH_SHORT).show();

                // Log.d("proxy",wget.getURL(x.xyoprup()+ z.xyxoprup() + ".php"));

                String ch = null;
                try {
                    if(okhttp) {
                        ch = wget.getURL(server + z.xyxoprup() + ".php?tk=" + tk + "&channel=" + channelid);
                    } else {
                        ch = old_wget.getURL(server + z.xyxoprup() + ".php?tk=" + tk + "&channel=" + channelid);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        //   Log.d("tk & chanel id",tk + " and " + channelid);

                /*   ch = oldwget.getURL(server + z.xyxoprup() + ".php?tk=" + tk);*/
                if (ch != null) {

                    String[] rapes=ch.split(",");
                    tk=rapes[1];
                    ch=d.decryptStr(rapes[0]);

                    String[] separ = ch.split(":");
//                    puser=separ[0];
//                    ppass=separ[1];
                    host = separ[0];
                    port = separ[1];
                } else {

                    Toast.makeText(exoplayer_layar.this, "There is something wrong with your internet connection.", Toast.LENGTH_SHORT);
                    return;
                }


            System.setProperty("http.proxyHost", host);
            System.setProperty("http.proxyPort", port);
        //    Log.d("proxyHost",host);
         //   Log.d("port",port);
//            System.setProperty("http.proxyUser", "xxx");
//            System.setProperty("http.proxyPassword", "xxxx");
               // Authenticator.setDefault(new ProxyAuthenticator("xxx","xxxx"));





//            try {
//                String Ip= wget.getURL("https://server1.layar3.com/test.php");
//                Log.d("IPaddress","x " + Ip);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
            else {
                System.setProperty("http.proxyHost", "");
                System.setProperty("http.proxyPort", "");

            }

    }

    private void initializePlayer() {
        // Create a default TrackSelector
      //  bandwidthMeter = new DefaultBandwidthMeter();
      //  videoTrackSelectionFactory =new AdaptiveTrackSelection.Factory(bandwidthMeter);
     //   trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

     //   allocator = new DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE);


        // This is the MediaSource representing the media to be played.

        // player.prepare(videoSource);
        // simpleExoPlayerView.requestFocus();
        //player.setPlayWhenReady(true);


        //set correct width based on resolution

        // double width= redbox_grid_activity.getScreenWidth();

        //  int correctWidth;

        //correctWidth = Integer.valueOf(width.intValue());
        // FrameLayout ExoFrame =(FrameLayout) findViewById(R.id.root);
        // simpleExoPlayerView.getLayoutParams().width=(int)width;
        // ExoFrame.getLayoutParams().width=(int)width;

        switchChannelExoPlayer=false;
        new exoplayer_layar.checkLatestApps().execute();
    }


    public static double getScreenWidth() {

        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playWhenReady && playbackState == Player.STATE_READY) {
            counter = 0;
            allowOSD=true;
            Exoplayingstate=true;
            if (pdLoading != null && pdLoading.isShowing()) {

                // pdLoading.dismiss();

            }

        }

        if(playbackState == Player.STATE_ENDED){
            Exoplayingstate=false;
            simpleExoPlayerView.hideController();
        }

        if (playbackState == Player.STATE_BUFFERING) {

            allowOSD=false;
            Exoplayingstate=false;

            if(player.getBufferedPosition() > player.getCurrentPosition()){
              //  player.setPlayWhenReady(true);

              BufferingCount=  new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if(playbackState != Player.STATE_BUFFERING){
                            BufferingCount.cancel();
                        }
                    }

                    public void onFinish() {
                        // player.prepare(videoSource);
                        //    player.setPlayWhenReady(true);
                        Log.d("PlayAfterBuffer","Play again");
                        player.release();
                        new exoplayer_layar.checkLatestApps().execute();

                    }

                }.start();

              // player.retry();

            }

            if (pdLoading != null && !pdLoading.isShowing()) {
                //  Toast.makeText(exoplayer_layar.this,String.valueOf(playbackState),Toast.LENGTH_SHORT).show();

                pdLoading = new ProgressDialog(exoplayer_layar.this);

                pdLoading.setMessage("\tBuffering...");
                pdLoading.setCancelable(true);


                //pdLoading.show();
            }
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        switch (error.type) {
            case ExoPlaybackException.TYPE_SOURCE:
                // Toast.makeText(exoplayer_layar.this,"ERROR TYPE_SOURCE " + error.getSourceException().getMessage(),Toast.LENGTH_LONG).show();
                Log.e("ERROR TYPE_SOURCE", "TYPE_SOURCE: " + error.getSourceException().getMessage());

                if (player != null) {
                 //   player.stop();
                    //player.release();
                    //player = null;
                }

        /*        if(counter==0){
                initiateDate = new Date();
                }
                if(counter>0){
                nextDate = new Date();
                long milis=nextDate.getTime() - initiateDate.getTime();
                long second=milis/1000;
                initiateDate=nextDate;
                if (second >10){
                    counter=0;
                    }

                }*/


                if (counter < maxretrybuffer) {

                    timerCountDownBuffering = counter * 1000 + 1;

                    sourceerrorTimer=new CountDownTimer(500, 100) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                           // player.prepare(videoSource);
                        //    player.setPlayWhenReady(true);
                            player.retry();

                           // new exoplayer_layar.checkLatestApps().execute();

                        }

                    }.start();
                } else {
                    if (pdLoading != null && pdLoading.isShowing()) {
                        pdLoading.dismiss();
                    }
                    if (exoplayer_layar.this.isDestroyed()) {

                    } else {
                        //Toast.makeText(exoplayer_layar.this, "Sorry, this channel is currently down.", Toast.LENGTH_LONG).show();
                        //Url = "http://portal.layar3.com/Layar3.mp4";
                      //  setupProxy(false);
                        //new exoplayer_layar.StartChannel().execute();

                        /*
                        new MaterialStyledDialog.Builder(exoplayer_layar.this)
                                .setTitle("SOURCE ERROR!")
                                .setStyle(Style.HEADER_WITH_TITLE)
                                .setDescription("Sorry, this channel is currently down.")
                                .setPositiveText("OK")
                                .withDarkerOverlay(true)
                                .withIconAnimation(true)
                                .setHeaderColor(R.color.red)
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        releaseall();
                                      //  finish();

                                    //    Log.d("MaterialStyledDialogs", "Do something!");
                                    }
                                })
                                //.setNegativeText(...)
                                //.onNegative(...)
                                //.setNeutralText(...)
                                //.onNeutral(...)
                                .show();
                                    */
                        CustomDialogSourceErrorClass cdde=new CustomDialogSourceErrorClass(exoplayer_layar.this);
                        cdde.show();

                        simpleExoPlayerView.setShowBuffering(PlayerView.SHOW_BUFFERING_NEVER);


                        //   finish();
                    }

                }
                counter = counter + 1;

                break;

            case ExoPlaybackException.TYPE_RENDERER:
                Toast.makeText(exoplayer_layar.this, "ERROR TYPE_SOURCE " + error.getRendererException().getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR TYPE_RENDERER", "TYPE_RENDERER: " + error.getRendererException().getMessage());
                break;

            case ExoPlaybackException.TYPE_UNEXPECTED:
                Toast.makeText(exoplayer_layar.this, "ERROR TYPE_SOURCE " + error.getUnexpectedException().getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR TYPE_UNEXPECTED", "TYPE_UNEXPECTED: " + error.getUnexpectedException().getMessage());
                break;
        }


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
        SharedPreferences.Editor cEdit=connectPref.edit();
        cEdit.putString("internet","fast");
        cEdit.apply();

       // Glide.with(exoplayer_layar.this).load("https://cdn2.iconfinder.com/data/icons/sports-attitudes/1451/run-512.png").into(InternetSpeed);
    }

    @Override
    public void connectedConnectionSlow() {
        SharedPreferences.Editor cEdit=connectPref.edit();
        cEdit.putString("internet","slow");
        cEdit.apply();

    //    Glide.with(exoplayer_layar.this).load("https://cdn2.iconfinder.com/data/icons/black-animal-svg-icons/512/snail_escargot-512.png").into(InternetSpeed);
    }

    @Override
    public void noNetwork() {
        Toast.makeText(getApplicationContext(),"Your internet connection loss!",Toast.LENGTH_SHORT).show();

    }

    private class checkLatestApps extends AsyncTask<String,String,String>
    {
        //ProgressDialog pdLoading = new ProgressDialog(exoplayer_layar.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            //   pdLoading.setMessage("\tLoading...");
            //   pdLoading.setCancelable(false);
            //   pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {

            String updateJSON = null;
            try {
                if(okhttp) {
                    updateJSON = wget.getURL(server + "apps/update/get_update_changelog.php?tk=" + tk);


                } else {
                    updateJSON = old_wget.getURL(server + "apps/update/get_update_changelog.php?tk=" + tk);
                }
            } catch (IOException e) {

                e.printStackTrace();
            }



            /* updateJSON = oldwget.getURL(server + "apps/update/update-changelog.json");
             */


            if(updateJSON != null) {

                String[] atapdu=updateJSON.split(",");
                tk=atapdu[1];
           //     Log.d("tokenatapdu","v " + tk);
                JSONObject GetDataJSONUpdate;
                try {
                    GetDataJSONUpdate = new JSONObject(String.valueOf(d.decryptStr(atapdu[0])));
                    Integer AppsVersion = GetDataJSONUpdate.getInt("latestVersionCode");

                    try {
                        PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
                        Integer version = pInfo.versionCode;

                     //   if (AppsVersion > version) {

                            String forceUpdate = null;
                            try {

                                if(okhttp) {

                                    forceUpdate = wget.getURL(server + "apps/exoplayer/force_update2.php?tk=" + tk);
                                   // Log.d("responseUpdateJsonForce",String.valueOf(updateJSON));
                                } else {
                                    forceUpdate = old_wget.getURL(server + "apps/exoplayer/force_update2.php?tk=" + tk);

                                }
                         //       Log.d("forceUpdate","Value " + forceUpdate);

                                String[] fyes=forceUpdate.split(",");
                                tk=fyes[1];
                                forceUpdate=d.decryptStr(fyes[0]);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            /* forceUpdate = oldwget.getURL(server + "apps/exoplayer/force_update.php");
                             */
                            if (forceUpdate.equalsIgnoreCase("go")) {
                                if (AppsVersion > version) {

                                    falseVersion = true;
                                }else {
                                    falseVersion=false;
                                }
                            }

                            if(forceUpdate.equalsIgnoreCase("stop")){

                                falseVersion=true;

                            }


                      //  }

                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }
            } else {
                httperror=true;
            }
            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            if (httperror) {
                Toast.makeText(exoplayer_layar.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG).show();
                return;
            }  else {

                if (falseVersion) {

                    // Toast.makeText(exoplayer_layar.this,"Your application version is outdated. Please update!",Toast.LENGTH_LONG).show();
                    // finish();
              /*      new MaterialStyledDialog.Builder(exoplayer_layar.this)
                            .setTitle("OLD APPLICATION VERSION DETECTED!")
                            .setStyle(Style.HEADER_WITH_TITLE)
                            .setDescription("You are using old application version. Please update to the latest version.")
                            .setPositiveText("OK")

                            .withDarkerOverlay(true)
                            .withIconAnimation(true)
                            .setHeaderColor(R.color.red)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    releaseall();
                                    finish();

                                }
                            })
                            //.setNegativeText(...)
                            //.onNegative(...)
                            //.setNeutralText(...)
                            //.onNeutral(...)
                            .show();
                            */
                    CustomLatestApkVersion clav=new CustomLatestApkVersion(exoplayer_layar.this);
                    clav.show();

                    SharedPreferences appupdater=getApplication().getSharedPreferences(exoplayer_layar.this.getPackageName() + "_preferences",MODE_PRIVATE);
                    SharedPreferences.Editor appupdateredit=appupdater.edit();
                    appupdateredit.putBoolean("prefAppUpdaterShow",true);
                    appupdateredit.apply();

                    //return;
                } else {

                    new exoplayer_layar.checkForMaxConnection().execute();

                }
            }
        }

    }

    public void exit_dueto_oldversion(){
       // Toast.makeText()
        Intent i =new Intent();
        i.setClassName(exoplayer_layar.this,menu_navigation.getHomeActivity());
        i.putExtra("PersonID", username);
        i.putExtra("token", tk);
        i.putExtra("server", server);
        startActivity(i);
        releaseall();
        finishAffinity();

    }


    private class checkForMaxConnection extends AsyncTask<String,String,String>
    {
        //ProgressDialog pdLoading = new ProgressDialog(exoplayer_layar.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            //   pdLoading.setMessage("\tLoading...");
            //   pdLoading.setCancelable(false);
            //   pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {

            DeviceModel = Manufacturer + " " + Model;

            String checkMaxConnectionn = null;
            try {
                if (okhttp) {
                    checkMaxConnectionn = wget.getURL(server + "apps/exoplayer/update_onlinev7.php?mac_address=" + Macaddress + "&tk=" + tk + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=livetv");
                } else {
                    checkMaxConnectionn = old_wget.getURL(server + "apps/exoplayer/update_onlinev7.php?mac_address=" + Macaddress + "&tk=" + tk + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=livetv");

                }
                } catch (IOException e) {
                e.printStackTrace();
            }


            /* checkMaxConnectionn = oldwget.getURL(server + "apps/exoplayer/update_onlinev4.php?mac_address=" + Macaddress + "&username=" + username + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=livetv");
             */

            firststart = false;

            if(checkMaxConnectionn != null) {

             //   Log.d("jsonMax","V " + checkMaxConnectionn);
            //    dlog.log_d(debug, "jsonMax", String.valueOf(checkMaxConnectionn));

                String[] cmax=checkMaxConnectionn.split(",");
                tk=cmax[1];

                dlog.log_d(debug, "jsonMax", String.valueOf(cmax[0]));

                   dlog.log_d(debug, "jsonMax2", String.valueOf(d.decryptStr(cmax[0])));

                setProxyUp=false;
                JSONObject GetDataJSONOnline;

         //       dlog.log_d(debug, "token", tk);

                try {
                    GetDataJSONOnline = new JSONObject(String.valueOf(d.decryptStr(cmax[0])));
                    String onlineStatus = GetDataJSONOnline.getString("token");

                    if (onlineStatus.equalsIgnoreCase("max")) {
                        maxConnection = true;

                        firstDevice = GetDataJSONOnline.getString("device0");
                        SecounDevice = GetDataJSONOnline.getString("device1");


                    } else {
                        CurrentchannelViews = String.valueOf(GetDataJSONOnline.getInt("view"));
                        yatchlink = GetDataJSONOnline.getString("yatchlink");
                        setProxyUp = GetDataJSONOnline.getBoolean("proxy");
                       //CurrentchannelViews ="n/a";
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                httperror=true;
            }
            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            if(httperror){
                Toast.makeText(exoplayer_layar.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG).show();
                return;

            } else {

                if (maxConnection) {

                    //  Toast.makeText(exoplayer_layar.this,"Max connection devices detected!",Toast.LENGTH_LONG).show();
                    // finish();
                    /*
                    new MaterialStyledDialog.Builder(exoplayer_layar.this)
                            .setTitle("MAX CONNECTION")
                            //.setStyle(Style.HEADER_WITH_TITLE)
                            .setDescription("Please logout from any of of these devices:\n1. " + firstDevice + "\n2. " + SecounDevice)
                            .setPositiveText("OK")
                            .withDarkerOverlay(true)
                            .withIconAnimation(true)
                            .setHeaderColor(R.color.red)
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    releaseall();
                                    finish();

                                    //   Log.d("MaterialStyledDialogs", "Do something!");
                                }
                            })
                            //.setNegativeText(...)
                            //.onNegative(...)
                            //.setNeutralText(...)
                            //.onNeutral(...)
                            .show();
                        */
                    CustomDialogMaxConnection cdmC=new CustomDialogMaxConnection(exoplayer_layar.this,firstDevice,SecounDevice);
                    cdmC.show();
                    return;
                } else {

                    new exoplayer_layar.StartChannel().execute();

                }
            }
        }

    }


    private class StartChannel extends AsyncTask<String, String, String> {

        String json = "", customLoad;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread



        }

        @Override
        protected String doInBackground(String... params) {

            HLSWidth = 0;
            HLSHeight = 0;







            // CurrentchannelViews = wget.getURL(server + "apps/exoplayer/getchannelinfo.php?channelid="+channelid);


            //     customLoad = wget.getURL(server + "apps/exoplayer/getcustomcontrol.php");

            /*
            if (Sypnopsis.equalsIgnoreCase("M4K")) {

                String user = "";

                if (checkFileExist("userinfo.txt")) {


                    File file = new File(getApplicationContext().getFilesDir(), "userinfo.txt");

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
                    } catch (IOException e) {
                        //You'll need to add proper error handling here
                    }

                    String userpass = text.toString();
                    String[] userlogin = userpass.split(",");
                    //     Toast.makeText(this,userpass, Toast.LENGTH_LONG).show();

                    user = userlogin[0];

                }


                String myiptv4kurl = "https://layar3.com/apps/livetv/getlivetvtoken.php?u=" + user;
                Log.d("getliveTokenUrl", myiptv4kurl);
                String UserToken = wget.getURL(myiptv4kurl);
                Log.d("getliveTokenUrl", UserToken);
                String getChannelisting = wget.getURL("https://layar3.com/apps/livetv/getchannel.php");
                Log.d("getChannelisting", getChannelisting);

                String playlisttokenurl = getChannelisting + UserToken;
                // String playlisttokenurl ="http://myiptv4k.com/api/v1/lives?token=" + UserToken;
                Log.d("playlisttokenurl", playlisttokenurl);

                String playlisttoken = wget.getURL(playlisttokenurl);
                Log.d("PlayUrl", Url + "\n" + playlisttoken);
                JSONObject objectPremium = null;
                try {
                    objectPremium = new JSONObject(String.valueOf(playlisttoken));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");
                    for (int i = 0; i < VodData.length(); i++) {
                        JSONObject VODLive = VodData.getJSONObject(i).getJSONObject("live");
                        String playUrl = VODLive.getString("url");
                        String containsURL = Url.substring(8);
                        if (playUrl.contains(containsURL)) {

                            String playUrlToken = playUrl.substring(playUrl.indexOf("?"));
                            json = playUrlToken;
                            // Log.d("ContainsURL", playUrlToken);
                            //  Log.d("playUrl",playUrl);
                            break;
                        }
                    }

                    // Toast.makeText(Book_Activity.this,playUrl,Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else
                */



         if(!switchChannelExoPlayer && EPGenable) {
                epgJson=null;
             try {
                 if (okhttp) {
                     epgJson = wget.getURL(server + "apps/epg/all_epg.txt");
                 } else {
                     epgJson = old_wget.getURL(server + "apps/epg/all_epg.txt");

                 }

             } catch (IOException e) {
                 e.printStackTrace();
             }
         }

         if (!EPGenable){
             epgJson="disable";
         }


           /* epgJson = oldwget.getURL(server + "apps/epg/all_epg.txt");
*/

            if (Sypnopsis.equalsIgnoreCase("REDBOX")) {
                try {
                    if(okhttp) {
                        json = wget.getURL("http://163.172.181.152:8030/rbtv/token21.php");
                    } else {
                        json = old_wget.getURL("http://163.172.181.152:8030/rbtv/token21.php");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (Sypnopsis.equalsIgnoreCase("LIVENET")) {
                try {

                    json = wget.getURL("http://212.83.158.140:6060/aves.nettv/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                json = "";

            }




            //   Log.d("Exoplayer",tk);

    /*       String expiry= wget.getURL("https://layar3.com/apps/exoplayer/updateLastPlay.php?tk="+tk);

            Log.d("Exoplayer",expiry);

            if (expiry.equalsIgnoreCase("expired")){
                finish();
            }

*/
            return "";


            }

            @Override
        protected void onPostExecute(String result) {


                //this method will be running on UI thread
                //  pdLoading.dismiss();

                //  Intent i=new Intent(exoplayer_layar.this,exoplayer_layar.class );
                //  i.putExtra("playurl",Url+json);
                //
                //     Intent intent = new Intent(Intent.ACTION_VIEW);
                // Url="https://www.youtube.com/watch?v=kjFBbIwuDYU";


                String getLiveYT = "youtube";
                if (Url.toLowerCase().contains(getLiveYT.toLowerCase())) {

                    String idPos = "?v=";

                    Integer pos = Url.indexOf(idPos);
                    getLiveYT = Url.substring(pos + 3);


                    new YouTubeExtractor(exoplayer_layar.this) {
                        @Override
                        public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                            if (ytFiles != null) {


                                int itag = 22;
                                String downloadUrl = ytFiles.get(17).getUrl();
                                Toast.makeText(exoplayer_layar.this, downloadUrl, Toast.LENGTH_LONG).show();
                                Url = downloadUrl;

                            }
                        }
                    }.extract(Url, true, true);


                }


                //  Url="http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0";

                //Initialize the player
/*
            Integer minBuffer = 300000;
            Integer maxBuffer = 600000;
            Integer bufferPlayBack = 2500;
            Integer bufferPlayBackRebuffer = 5000;
            maxretrybuffer = 3;
            try {
                JSONObject ObjCustomLoad = new JSONObject(String.valueOf(customLoad));
                minBuffer = ObjCustomLoad.getInt("minBuffer");
                maxBuffer = ObjCustomLoad.getInt("maxBuffer");
                bufferPlayBack = ObjCustomLoad.getInt("bufferplayback");
                bufferPlayBackRebuffer = ObjCustomLoad.getInt("bufferplaybackafterrebuffer");
                maxretrybuffer = ObjCustomLoad.getInt("maxretrybuffer");

            } catch (JSONException e) {
                e.printStackTrace();
            }

*/
                maxretrybuffer = 2;

                player = ExoPlayerFactory.newSimpleInstance(exoplayer_layar.this);

           /* player = ExoPlayerFactory.newSimpleInstance(exoplayer_layar.this, trackSelector, new CustomLoadControl(new CustomLoadControl.EventListener() {
                @Override
                public void onBufferedDurationSample(long bufferedDurationUs) {
                    bufferedDurationMs = bufferedDurationUs;
                }
            }));
          */  //   player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

                //Initialize simpleExoPlayerView


                simpleExoPlayerView.setPlayer(player);
                simpleExoPlayerView.isClickable();
                player.addListener(exoplayer_layar.this);
                Player.VideoComponent VideoResolutionDetection = player.getVideoComponent();

                VideoResolutionDetection.addVideoListener(new VideoListener() {
                    @Override
                    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
                        HLSWidth = width;
                        HLSHeight = height;


                        if (HLSWidth != 0) {
                            String resolusi = "";
                            if (HLSHeight > 720) {
                                resolusi = "1080p HD";
                            } else if (HLSHeight > 540) {
                                resolusi = "720p HD";
                            } else {
                                resolusi = String.valueOf(HLSHeight) + "p SD";
                            }

                            resolution.setVisibility(View.VISIBLE);

                            resolution.setText(resolusi);
                            HLSWidth = 0;
                            dlog.log_d(debug, "HLS Height", String.valueOf(HLSHeight));

                            dlog.log_d(debug, "HLS Width", String.valueOf(HLSWidth));
                        }

                    }

                    @Override
                    public void onRenderedFirstFrame() {

                    }
                });


                String combineUrl = Url + json;


//                String VideoUrilayar3 = "layar3.com";
////
////                if (combineUrl.toLowerCase().contains(VideoUrilayar3.toLowerCase())) {
////                   // combineUrl = combineUrl.replace("portal.layar3.com", "10.10.10.10");
////
////                }


                if(yatchlink.equalsIgnoreCase("none")){

                } else {
                    combineUrl=yatchlink;
                }

              // combineUrl="http://tvku.live:8888/live/34t469ClM6/vF36CNbvf9/592.ts";
                videoUri = Uri.parse(combineUrl);
               // Log.d("CombineURL",combineUrl);

                //   startActivity(Intent.createChooser(intent, "Complete action using"));
                // Produces DataSource instances through which media data is loaded.
//            dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, getPackageName()));




      /*   try {
             abcyxoorp a = new abcyxoorp();
             abcxtengtyou x = new abcxtengtyou();
             ua = wget.getURL(server + x.abcxtengtyou() + EEE);
         } catch (IOException e) {
             e.printStackTrace();
         }*/

                dataSourceFactory = new DefaultDataSourceFactory(context, "3rayal", bandwidthMeter);

//                DefaultHttpDataSourceFactory owndataSourceFactory = new DefaultHttpDataSourceFactory(
//                        "3rayal",
//                        null /* listener */,
//                        DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
//                        DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
//                        true /* allowCrossProtocolRedirects */
//                );
//
//                DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
//                        context,
//                        null /* listener */,
//                        owndataSourceFactory
//                );


                /* dataSourceFactory = new DefaultDataSourceFactory(context, oldwget.getURL(server + x.abcxtengtyou() + EEE));
                 */

                // Produces Extractor instances for parsing the media data.
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory().setConstantBitrateSeekingEnabled(true);

                // videoUri = Uri.parse("http://10.10.10.10:8888/live/mhazwankamal/1234/14012.m3u8");

                int type = Util.inferContentType(videoUri);

                String layar3 = "portal.layar3.com";
                String layar3test = "ms1-int.layar3.com";



               // if (Url.toLowerCase().contains(layar3.toLowerCase()) || Url.toLowerCase().contains(layar3test.toLowerCase())) {
                if(setProxyUp){
                    setupProxy(true);
                } else {
                    setupProxy(false);
                }




                videoSource = null;
                //  videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
                switch (type) {
                    case C.TYPE_DASH:
                        DataSource.Factory manifestDataSourceFactory =
                                new DefaultHttpDataSourceFactory(Util.getUserAgent(context, getPackageName()));
                        DashChunkSource.Factory dashChunkSourceFactory =
                                new DefaultDashChunkSource.Factory(
                                        new DefaultHttpDataSourceFactory(Util.getUserAgent(context, getPackageName()), bandwidthMeter));
                        videoSource = new DashMediaSource.Factory(dashChunkSourceFactory,
                                manifestDataSourceFactory).createMediaSource(videoUri);
                        player.setPlayWhenReady(true);

                        player.prepare(videoSource);
                        //    Toast.makeText(exoplayer_layar.this,"DASH" + String.valueOf(type),Toast.LENGTH_SHORT).show();
                        break;
                    case C.TYPE_HLS:
                        DefaultLoadErrorHandlingPolicy df = new DefaultLoadErrorHandlingPolicy();

                       videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
                     //   videoSource = new HlsMediaSource.Factory(dataSourceFactory).setLoadErrorHandlingPolicy(df).createMediaSource(videoUri);
                        //videoSource=new HlsMediaSource(videoUri,dataSourceFactory, handler, null);

                        player.prepare(videoSource);
                        player.setPlayWhenReady(true);
                        // Toast.makeText(exoplayer_layar.this,"HLS" + String.valueOf(type),Toast.LENGTH_SHORT).show();

                        break;
                    case C.TYPE_OTHER:
                        videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).setExtractorsFactory(extractorsFactory).createMediaSource(videoUri);

                        player.prepare(videoSource);
                        player.setPlayWhenReady(true);
                        //    Toast.makeText(exoplayer_layar.this,"OTHERS" + String.valueOf(type),Toast.LENGTH_SHORT).show();

                        break;
                    default: {
                        throw new IllegalStateException("Unsupported type: " + type);
                    }
                }

                //  MediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

                // Prepare the player with the source.

//                new CountDownTimer(10000,1000){
//
//
//                    @Override
//                    public void onTick(long l) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        videoUri = Uri.parse("http://10.10.10.10:8888/live/mhazwankamal/1234/14012.m3u8");
//                         player.setPlayWhenReady(false);
//
//                        videoSource=new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
//
//                        player.setPlayWhenReady(true);
//                       player.prepare(videoSource);
//                    }
//                }.start();


                simpleExoPlayerView.hideController();
                simpleExoPlayerView.setControllerShowTimeoutMs(0);


                controllerView = false;


                channelNameTxt.setText(channel);
                //    TextView CategoryNameTxt = (TextView) findViewById(R.id.channelcategory);
                //  CategoryNameTxt.setText(category);
                Glide.with(exoplayer_layar.this).load(thumbnail).into(ChannelIcon);


                ChannelViews.setText(CurrentchannelViews);

                if (premium.equalsIgnoreCase("yes")) {
                    premiumText.setVisibility(View.VISIBLE);
                } else {
                    premiumText.setVisibility(View.GONE);
                }

                resolution.setVisibility(View.GONE);


                HeaderPlayer.setVisibility(View.VISIBLE);
                FrameHeaderMenu.setVisibility(View.VISIBLE);
                handler.postDelayed(showBuffered, 1);
                switchChannel.requestFocus();
                epg_ll_switchChannel.setVisibility(View.VISIBLE);

                showToggleCount = new CountDownTimer(5000, 50) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {

                        if (HeaderPlayer.getVisibility() == VISIBLE) {

                            ObjectAnimator.ofFloat(HeaderPlayer, View.ALPHA, 1.0f, 0.0f).setDuration(500).start();
                            ObjectAnimator.ofFloat(FrameHeaderMenu, View.ALPHA, 1.0f, 0.0f).setDuration(500).start();

                            new CountDownTimer(500, 500) {

                                public void onTick(long millisUntilFinished) {
                                }

                                public void onFinish() {
                                    HeaderPlayer.setVisibility(View.GONE);
                                    FrameHeaderMenu.setVisibility(View.GONE);

                                    handler.removeCallbacks(showBuffered);
                                }

                            }.start();


                        }
                        // switchChannel.requestFocus();
                        // epg_ll_switchChannel.setVisibility(View.GONE);

                    }
                }.start();

                // handler.postDelayed(showBuffered, 1);

                Long now = System.currentTimeMillis();

                editor.putLong("lastChangeChannel", now / 1000);
                editor.apply();

                if (exoplayertimerun)
                    handler.removeCallbacks(ExoPlayerTimer);

                handler.postDelayed(ExoPlayerTimer, 1);


                if (!switchChannelExoPlayer) {

                    new CountDownTimer(2000, 2000) {

                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            new exoplayer_layar.LoadVODInBookList().execute();
                        }

                    }.start();


                } else {
                    if(EPGenable){
                        new CountDownTimer(2000, 2000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                new exoplayer_layar.UpdateEPG().execute();
                            }

                        }.start();

                }
                }
        }
    }

    public void switchChannel(String url,String channelName,String imgUrl,String premium,String channelid,String program,String playStart,String playEnd,Integer cposition,String SwitchCategory,String sypnop){

    //    dlog.log_d(debug,"playUrlother",url);

    /*    try {
          //  String switchoutchannel=wget.getURL(server + "apps/exoplayer/switchout_channel.php?channelid="+ this.channelid);
            dlog.log_d(debug,"removechannel",server + "apps/exoplayer/switchout_channel.php?channelid="+ this.channelid);

        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        allowOSD=false;
        nowShowingProgram.setText(program);
        channeltimeline.setText(playStart + "-" + playEnd);
        Watchcategory=SwitchCategory;
        simpleExoPlayerView.setShowBuffering(true);
        //if(Exoplayingstate)
        if (player != null)
        player.release();

        //releaseall();

        Sypnopsis=sypnop;
        switchChannelExoPlayer=true;
        setupProxy(false);
        frameSwitchChannel.setVisibility(View.GONE);
        gridview_channelswitch_frame.setVisibility(View.GONE);
        this.channelid=channelid;
        Url =url;
        thumbnail=imgUrl;
        channel=channelName;
        this.premium=premium;
        logoutResponse="";
        firststart=true;
        channelPosition=cposition;
        SyncEPFWithChannelSwitch(channelPosition);
        counter=0;
        //initializePlayer();
        new exoplayer_layar.checkLatestApps().execute();

        new CountDownTimer(timerCountDownBuffering, timerCountDownBuffering) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

              // LoadEPGSecoundTime();

            }

        }.start();


        editor.putString("Url",Url);
        editor.putString("Sypnopsis",Sypnopsis);
        editor.putString("Thumbnail",thumbnail);
        editor.putString("channel",channel);
        editor.putString("Category",Watchcategory);
        editor.putString("cid",channelid);
        editor.putString("premium",premium);
        editor.apply();


    }

    private Runnable ExoPlayerTimer = new Runnable() {
        @Override
        public void run() {

            exoplayertimerun=true;

            long lastPlay=pref.getLong("lastChangeChannel",0);

            long currentTime = System.currentTimeMillis()/1000;

                //14400
            if(currentTime - lastPlay > 14400){
           //   if(currentTime - lastPlay > 5){

                CustomDialogClass cdd=new CustomDialogClass(exoplayer_layar.this);
                cdd.show();

            }else {


                //300000
                handler.postDelayed(ExoPlayerTimer,300000);
               // handler.postDelayed(ExoPlayerTimer,5000);
            }

        }
    };

    public void RunExoPlayerTimerBack(){

//        try {
//            wget.getURL(server + "apps/exoplayer/update_onlinev5.php?mac_address=" + Macaddress + "&tk=" + tk + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=livetv");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Long now=System.currentTimeMillis();

        editor.putLong("lastChangeChannel",now/1000);
        editor.apply();

        handler.postDelayed(ExoPlayerTimer,1);

    }

    public void properlyLogout(){

        logoutResponse = "none";

        try {
            logoutResponse = wget.getURL(server + "apps/exoplayer/update_offlinev3.php?mac_address=" + Macaddress + "&username=" + username + "&channel=" + channelid);

            if (logoutResponse.equalsIgnoreCase("out")){
                Intent i =new Intent();
                i.setClassName(exoplayer_layar.this,menu_navigation.getHomeActivity());
                i.putExtra("PersonID", username);
                i.putExtra("token", tk);
                i.putExtra("server", server);
                startActivity(i);
                releaseall();
                finishAffinity();
            } else {
                Toast.makeText(exoplayer_layar.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG);
                if(player != null)
                    player.stop();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
/*
    private Runnable updatePlayTime = new Runnable() {

        long hoursf, minutesf, secondsf;
        String json,response;
        getURL wget=new getURL();
        JSONObject objectPremium=null;

        @Override
        public void run() {
            /* do what you need to do
            String DeviceModel=Manufacturer + " " + Model;

            String checkRunnable=wget.getURL(server + "apps/exoplayer/getRunnable.php");

            if (checkRunnable.equalsIgnoreCase("stop")){
                stoprunnable=true;
            }

            if(stoprunnable) {
                return;
            }

            if(Build.VERSION.SDK_INT >= 21) {
                //only api 21 above


            mAPIService.updateonlinestatus(Macaddress,username,DeviceModel,channel,"livetv","none").enqueue(new Callback<Access>() {
                @Override
                public void onResponse(Call<Access> call, Response<Access> response) {

                    Log.d("Update", response.body().gettoken());
                    String getresponse = response.body().gettoken();
                  //  updateinfo = username + "," + getresponse;


                    if (getresponse.equalsIgnoreCase("max")) {
                        Toast.makeText(exoplayer_layar.this,"Max login devices detected!",Toast.LENGTH_LONG).show();
                        releaseall();
                        finish();
                    } else {
                       // Date currentTime = Calendar.getInstance().getTime();
                       // long currentDateTime =  currentTime.getTime();

                       // Log.d("Timeupdate", currentDateTimeString);

                      //  saveTokenToFile(getresponse+","+String.valueOf(currentDateTime));
                    }

                }

                @Override
                public void onFailure(Call<Access> call, Throwable t) {

                }
            });

            }else{

                //trimCache();
                //only api 21 down



                json= wget.getURL("https://layar3.com/apps/exoplayer/update_online.php?mac_address="+Macaddress+"&username=" +username+"&model=" + DeviceModel + "&channel=" + channel + "&opt=livetv" + "&data=none");
             //   Toast.makeText(exoplayer_layar.this,json,Toast.LENGTH_LONG).show();
                try {
                    objectPremium = new JSONObject(String.valueOf(json));

                    String response=objectPremium.getString("token");

                  //  updateinfo = username + "," + response;

                    if(response.equalsIgnoreCase("max")){
                        Toast.makeText(exoplayer_layar.this,"Max login devices detected!",Toast.LENGTH_LONG).show();
                        releaseall();
                        finish();
                    }else {
                       // Date currentTime = Calendar.getInstance().getTime();
                       // long currentDateTime =  currentTime.getTime();

                       //  Log.d("Timeupdate", String.valueOf(currentDateTime));

                       // saveTokenToFile(response+","+String.valueOf(currentDateTime));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /* and here comes the "trick"
            handler.postDelayed(this, 5000);
        }
    };
*/



    private Date convertStringToDate(String dateString) {
        //String dateString = "03/26/2012 11:49:00 AM";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    Date convertedDate = new Date();
    try

    {
      convertedDate = dateFormat.parse(dateString);
    } catch(
    ParseException e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
        return  convertedDate;
    }

    private void saveTokenToFile(String tokendate){
        String filename ="token_number_play.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(tokendate.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class LoadVODInBookList extends AsyncTask<String,String,String>
    {
        //ProgressDialog pdLoading = new ProgressDialog(exoplayer_layar.this);
        String firstCat="";
        ArrayList<String> ChannelList;
        String programName,playStarCurrent,playEndCurrent;
        Boolean noWorkingEPG=false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
         //   pdLoading.setMessage("\tLoading...");
         //   pdLoading.setCancelable(false);
         //   pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {

            String id,name,logoPath,url,category,sypnopsis,premium,ProgInfo1,ProgInfo2;
            Integer epgid;
        //    getURL wget=new getURL();
          //  String json= wget.getURL(server + "apps/livetv/vd452ax3.php");
          //  String json= wget.getURL(server+b.rvd452ax3()+".php?tk="+tk);

            if(epgJson != null) {

                try {
                    JSONObject objectPremium = new JSONObject(String.valueOf(json));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");
                    JSONArray EPGdata=null;

                    if (!epgJson.equalsIgnoreCase("disable")) {
                        JSONObject objectPremium2 = new JSONObject(String.valueOf(epgJson));
                        EPGdata = (JSONArray) objectPremium2.getJSONArray("data");
                    }

                    SETVODJsonCategory = new LinkedHashSet<String>();
                    VODJsonId = new ArrayList<String>();
                    VODJsonName = new ArrayList<String>();
                    VODJsonLogoPath = new ArrayList<String>();
                    VODJsonUrl = new ArrayList<String>();
                    VODJsonCategory = new ArrayList<String>();
                    VODJsonsypnopsis = new ArrayList<String>();
                    VODJsonpremium = new ArrayList<String>();
                    VODJsonepg = new ArrayList<Integer>();
                    VODJsonprograminfo = new ArrayList<String>();
                    VODJsonprograminfo2 = new ArrayList<String>();
                    //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
                    String VODid, VODname, VODlogo, VODurl, VODcategory, VODsypnopsis, VODPremium, prinfo = "", prinfo2 = "";
                    Integer Epgid;
                    Boolean found;
                    for (int i = 0; i < VodData.length(); i++) {
                        VODid = VodData.getJSONObject(i).getString("id");
                        VODname = VodData.getJSONObject(i).getString("name");
                        VODlogo = VodData.getJSONObject(i).getString("logoPath");
                        VODurl = VodData.getJSONObject(i).getString("playUrl");
                        VODcategory = VodData.getJSONObject(i).getString("genre");
                        VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");
                        VODPremium = VodData.getJSONObject(i).getString("premium");
                        Epgid = VodData.getJSONObject(i).getInt("epg");
                        found = false;
                        noWorkingEPG = false;
                        //    dlog.log_d(debug, "EPGID", String.valueOf(Epgid));

                        prinfo = "No working EPGhazwan-hazwan-";
                        prinfo2 = "No working EPGhazwan-hazwan-";

                        if (Epgid != 0) {

                            if (!epgJson.equalsIgnoreCase("disable")) {



                            for (int j = 0; j < EPGdata.length(); j++) {


                                Integer PID = EPGdata.getJSONObject(j).getInt("pid");

                                //   dlog.log_d(debug, "Check EPG ID", String.valueOf(Epgid) + "vs" + String.valueOf(PID));


                                if ((Epgid - PID) == 0) {

                                    JSONArray EPGArray = (JSONArray) EPGdata.getJSONObject(j).getJSONArray("egp");

                                    for (int k = 0; k < EPGArray.length(); k++) {

                                        Long Now = System.currentTimeMillis() / 1000;
                                        String ts = Now.toString();

                                        String program = EPGArray.getJSONObject(k).getString("program");
                                        String start = EPGArray.getJSONObject(k).getString("play");
                                        String end = EPGArray.getJSONObject(k).getString("end");

                                        Long EpgStart = Long.parseLong(start);
                                        Long EpgEnd = Long.parseLong(end);
                                        // dlog.log_d(debug, "current", String.valueOf(Now));
                                        // dlog.log_d(debug, "CheckEPG", String.valueOf(EpgStart) + "<" + String.valueOf(Now) + "<" + String.valueOf(EpgEnd));

                                        if (EpgStart < Now && Now < EpgEnd) {
                                            // dlog.log_d(debug, "CheckEPG", String.valueOf(EpgStart) + "<" + String.valueOf(Now) + "<" + String.valueOf(EpgEnd));

                                            //    dlog.log_d(debug, "program", program);
                                            //      dlog.log_d(debug, "program", start);
                                            //     dlog.log_d(debug, "program", end);
                                            if (k <= EPGArray.length() - 2) {
                                                String program2 = EPGArray.getJSONObject(k + 1).getString("program");
                                                String start2 = EPGArray.getJSONObject(k + 1).getString("play");
                                                String end2 = EPGArray.getJSONObject(k + 1).getString("end");
                                      /*      dlog.log_d(debug, "program", program2);
                                            dlog.log_d(debug, "program", start2);
                                            dlog.log_d(debug, "program", end2);*/
                                                prinfo2 = program2 + "hazwan" + start2 + "hazwan" + end2;
                                            } else {
                                                String program2 = "No working EPG";
                                                String start2 = "-";
                                                String end2 = "-";

                                                prinfo2 = program2 + "hazwan" + start2 + "hazwan" + end2;

                                            }

                                            prinfo = program + "hazwan" + start + "hazwan" + end;

                                            if (VODid.equalsIgnoreCase(channelid)) {


                                                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                                                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                                                String startPlay = formatter.format(new Date(Long.parseLong(start) * 1000));
                                                String endPlay = formatter.format(new Date(Long.parseLong(end) * 1000));

                                                programName = program;
                                                playStarCurrent = startPlay;
                                                playEndCurrent = endPlay;

                                            }

                                            break;
                                        } else {

                                            program = EPGArray.getJSONObject(0).getString("program");
                                            start = EPGArray.getJSONObject(0).getString("play");
                                            end = EPGArray.getJSONObject(0).getString("end");

                                          //  dlog.log_d(debug, "EPGID", String.valueOf(Epgid));

                                            String program2 = EPGArray.getJSONObject(1).getString("program");
                                            String start2 = EPGArray.getJSONObject(1).getString("play");
                                            String end2 = EPGArray.getJSONObject(1).getString("end");

                                            prinfo = program + "hazwan" + start + "hazwan" + end;

                                            if (VODid.equalsIgnoreCase(channelid)) {

                                                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                                                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                                                String startPlay = formatter.format(new Date(Long.parseLong(start) * 1000));
                                                String endPlay = formatter.format(new Date(Long.parseLong(end) * 1000));

                                                programName = program;
                                                playStarCurrent = startPlay;
                                                playEndCurrent = endPlay;

                                            }
                                            prinfo2 = program2 + "hazwan" + start2 + "hazwan" + end2;

                                        }


                                    }
                                    found = true;
                                }
                                if (found)
                                    break;
                               }
                            } else {
                                prinfo = "No working EPGhazwan-hazwan-";
                                prinfo2 = "No working EPGhazwan-hazwan-";
                            }

                        } else {
                            prinfo = "No working EPGhazwan-hazwan-";
                            prinfo2 = "No working EPGhazwan-hazwan-";

                            if (VODid.equalsIgnoreCase(channelid)) {
                                noWorkingEPG = true;
                            }

                        }

                        VODJsonId.add(VODid);
                        VODJsonName.add(VODname);
                        VODJsonLogoPath.add(VODlogo);
                        VODJsonUrl.add(VODurl);
                        VODJsonCategory.add(VODcategory);
                        VODJsonsypnopsis.add(VODsypnopsis);
                        VODJsonpremium.add(VODPremium);
                        VODJsonepg.add(Epgid);
                        VODJsonprograminfo.add(prinfo);
                        VODJsonprograminfo2.add(prinfo2);
                        SETVODJsonCategory.add(VODcategory);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // Toast.makeText(getApplicationContext(),objectPremium.getBoolean("state"),Toast.LENGTH_LONG).show();


                //   myrvRB = (RecyclerView) findViewById(R.id.recyclerview_channel_category_top);
                //   myAdapterRB = new RecyclerViewAdapterCategoryExoPlayer(exoplayer_layar.this,VODCat);
            } else {
                httperror=true;
            }

            return "";

        }

        @Override
        protected void onPostExecute(String result) {

                //this method will be running on UI thread
          //      pdLoading.dismiss();

              //  ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.listview,VODCat);
             //   listView.setAdapter(adapter);

             //   myrvRB.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
               // myrvRB.setAdapter(myAdapterRB);
              //  myrvRB.setVisibility(View.GONE);

            if (httperror){
                Toast.makeText(exoplayer_layar.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG).show();
                return;

            }else {

                String id, name, logoPath, url, category, sypnopsis, premium, ProgInfo1, ProgInfo2;
                Integer epgid;


                VODCat = new ArrayList<String>();

                for (String s : SETVODJsonCategory) {
                    //       Log.d("SETVODJsonCategory",s);

                    VODCat.add(s);


                }



/*
            for(int i=0;i<VODCat.size();i++){
                firstCat=VODCat.get(i).toString();
                break;
            }

*/

                EPG = new ArrayList<>();
                for (int i = 0; i < VODJsonName.size(); i++) {
                    id = VODJsonId.get(i).toString();
                    name = VODJsonName.get(i).toString();
                    category = VODJsonCategory.get(i).toString();
                    logoPath = VODJsonLogoPath.get(i).toString();
                    url = VODJsonUrl.get(i).toString();
                    sypnopsis = VODJsonsypnopsis.get(i).toString();
                    premium = VODJsonpremium.get(i).toString();
                    epgid = VODJsonepg.get(i);
                    ProgInfo1 = VODJsonprograminfo.get(i).toString();
                    ProgInfo2 = VODJsonprograminfo2.get(i).toString();
                    // try {
                    // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
                    //} catch (IOException e) {
                    //      e.printStackTrace();
                    //  }
                    if (Watchcategory.equalsIgnoreCase(category)) {
                        EPG.add(new ExoBook(name, category, url, logoPath, sypnopsis, id, premium, epgid, ProgInfo1, ProgInfo2));

                    }
                }


                width = Resources.getSystem().getDisplayMetrics().widthPixels;
                ArrayAdapter adapter = null;
                dlog.log_d(debug, "height", String.valueOf(width));
                if (width > 1920) {
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, VODCat);

                    dlog.log_d(debug, "contentView", "1440");
                } else if (width > 1280) {
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview, VODCat);

                    dlog.log_d(debug, "contentView", "1080");

                } else {
                    adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listview_720, VODCat);

                    dlog.log_d(debug, "contentView", "720");
                }

                listviewCat.setAdapter(adapter);


                listviewCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        String CurrentCategory = VODCat.get(i).toString();
                        String id, name, logoPath, url, category, sypnopsis, premium, ProgInfo1, ProgInfo2;
                        Integer epgid;
                        EPG = new ArrayList<>();
                        for (i = 0; i < VODJsonName.size(); i++) {
                            id = VODJsonId.get(i).toString();
                            name = VODJsonName.get(i).toString();
                            category = VODJsonCategory.get(i).toString();
                            logoPath = VODJsonLogoPath.get(i).toString();
                            url = VODJsonUrl.get(i).toString();
                            sypnopsis = VODJsonsypnopsis.get(i).toString();
                            premium = VODJsonpremium.get(i).toString();
                            epgid = VODJsonepg.get(i);
                            ProgInfo1 = VODJsonprograminfo.get(i).toString();
                            ProgInfo2 = VODJsonprograminfo2.get(i).toString();
                            // try {
                            // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
                            //} catch (IOException e) {
                            //      e.printStackTrace();
                            //  }
                            if (CurrentCategory.equalsIgnoreCase(category)) {
                                EPG.add(new ExoBook(name, category, url, logoPath, sypnopsis, id, premium, epgid, ProgInfo1, ProgInfo2));


                            }
                        }

                        myAdapterRBExo = new RecyclerViewAdapterRBExo(exoplayer_layar.this, EPG);
                        ChannelListingGridView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
                        ChannelListingGridView.setAdapter(myAdapterRBExo);

                        // MyAdapterRBSwitchChannel=new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar.this,EPG);
                        // myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);

                    }
                });


                myAdapterRBExo = new RecyclerViewAdapterRBExo(exoplayer_layar.this, EPG);
                ChannelListingGridView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
                ChannelListingGridView.setAdapter(myAdapterRBExo);


                ChannelCategory.setText(Watchcategory);


                MyAdapterRBSwitchChannel = new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar.this, EPG);

                myrvRBSwitchChannel.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);

                if (noWorkingEPG) {
                    channeltimeline.setText("-");
                    nowShowingProgram.setText("No working EPG");

                } else {
                    channeltimeline.setText(playStarCurrent + "-" + playEndCurrent);
                    nowShowingProgram.setText(programName);

                }

                epgframeopen = true;

            }
        }

    }

    private class getLatestChannelViews extends AsyncTask<String,String,String>
    {
        //ProgressDialog pdLoading = new ProgressDialog(exoplayer_layar.this);
         Integer currentViewInt;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            //   pdLoading.setMessage("\tLoading...");
            //   pdLoading.setCancelable(false);
            //   pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {

            try {
                String currentView=wget.getURL(server + "apps/channel/update_channel_views.php?tk=" + tk + "&channelid=" + channelid);
                currentViewInt=parseInt(currentView) + 1;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {

            ChannelViews.setText(String.valueOf(currentViewInt));
        }

    }



    private class UpdateEPG extends AsyncTask<String,String,String>{

        Boolean noWorkingEPG=false;
        String programName="",playStarCurrent="",playEndCurrent="";

        @Override
        protected String doInBackground(String... strings) {

            JSONObject objectPremium2 = null;
            JSONArray EPGdata=null;
            try {

                objectPremium2 = new JSONObject(String.valueOf(epgJson));
                EPGdata = (JSONArray) objectPremium2.getJSONArray("data");

               Boolean found;
            for (int i=0;i<MyAdapterRBSwitchChannel.getItemCount();i++){


                //Log.d("PrintEPG",EPG.get(i).getTitle());

                String prinfo = "No working EPGhazwan-hazwan-";
                String prinfo2 = "No working EPGhazwan-hazwan-";
                found=false;
                noWorkingEPG=false;
                if (EPG.get(i).getepgId() != 0) {

                    if (!epgJson.equalsIgnoreCase("disable")) {



                        for (int j = 0; j < EPGdata.length(); j++) {


                            Integer PID = EPGdata.getJSONObject(j).getInt("pid");

                            //   dlog.log_d(debug, "Check EPG ID", String.valueOf(Epgid) + "vs" + String.valueOf(PID));


                            if ((EPG.get(i).getepgId() - PID) == 0) {

                                JSONArray EPGArray = (JSONArray) EPGdata.getJSONObject(j).getJSONArray("egp");

                                for (int k = 0; k < EPGArray.length(); k++) {

                                    Long Now = System.currentTimeMillis() / 1000;
                                    String ts = Now.toString();

                                    String program = EPGArray.getJSONObject(k).getString("program");
                                    String start = EPGArray.getJSONObject(k).getString("play");
                                    String end = EPGArray.getJSONObject(k).getString("end");

                                    Long EpgStart = Long.parseLong(start);
                                    Long EpgEnd = Long.parseLong(end);
                                    // dlog.log_d(debug, "current", String.valueOf(Now));
                                    // dlog.log_d(debug, "CheckEPG", String.valueOf(EpgStart) + "<" + String.valueOf(Now) + "<" + String.valueOf(EpgEnd));

                                    if (EpgStart < Now && Now < EpgEnd) {
                                        // dlog.log_d(debug, "CheckEPG", String.valueOf(EpgStart) + "<" + String.valueOf(Now) + "<" + String.valueOf(EpgEnd));

                                        //    dlog.log_d(debug, "program", program);
                                        //      dlog.log_d(debug, "program", start);
                                        //     dlog.log_d(debug, "program", end);
                                        if (k <= EPGArray.length() - 2) {
                                            String program2 = EPGArray.getJSONObject(k + 1).getString("program");
                                            String start2 = EPGArray.getJSONObject(k + 1).getString("play");
                                            String end2 = EPGArray.getJSONObject(k + 1).getString("end");
                                      /*      dlog.log_d(debug, "program", program2);
                                            dlog.log_d(debug, "program", start2);
                                            dlog.log_d(debug, "program", end2);*/
                                            prinfo2 = program2 + "hazwan" + start2 + "hazwan" + end2;

                                            EPG.get(i).setProgInfo2(prinfo2);

                                        } else {
                                            String program2 = "No working EPG";
                                            String start2 = "-";
                                            String end2 = "-";

                                            prinfo2 = program2 + "hazwan" + start2 + "hazwan" + end2;
                                            EPG.get(i).setProgInfo2(prinfo2);
                                        }

                                        prinfo = program + "hazwan" + start + "hazwan" + end;
                                        EPG.get(i).setProgInfo1(prinfo);
                                        if (EPG.get(i).getId().equalsIgnoreCase(channelid)) {


                                            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                                            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                                            String startPlay = formatter.format(new Date(Long.parseLong(start) * 1000));
                                            String endPlay = formatter.format(new Date(Long.parseLong(end) * 1000));

                                            programName = program;
                                            playStarCurrent = startPlay;
                                            playEndCurrent = endPlay;

                                        }

                                        break;
                                    } else {

                                        program = EPGArray.getJSONObject(0).getString("program");
                                        start = EPGArray.getJSONObject(0).getString("play");
                                        end = EPGArray.getJSONObject(0).getString("end");


                                        String program2 = EPGArray.getJSONObject(1).getString("program");
                                        String start2 = EPGArray.getJSONObject(1).getString("play");
                                        String end2 = EPGArray.getJSONObject(1).getString("end");

                                        prinfo = program + "hazwan" + start + "hazwan" + end;
                                        EPG.get(i).setProgInfo1(prinfo);
                                        if (EPG.get(i).getId().equalsIgnoreCase(channelid)) {

                                            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                                            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                                            String startPlay = formatter.format(new Date(Long.parseLong(start) * 1000));
                                            String endPlay = formatter.format(new Date(Long.parseLong(end) * 1000));

                                            programName = program;
                                            playStarCurrent = startPlay;
                                            playEndCurrent = endPlay;

                                        }
                                        prinfo2 = program2 + "hazwan" + start2 + "hazwan" + end2;
                                        EPG.get(i).setProgInfo2(prinfo2);
                                    }


                                }
                                found = true;
                            }
                            if (found)
                                break;
                        }
                    } else {
                        prinfo = "No working EPGhazwan-hazwan-";
                        prinfo2 = "No working EPGhazwan-hazwan-";
                    }

                } else {
                    prinfo = "No working EPGhazwan-hazwan-";
                    prinfo2 = "No working EPGhazwan-hazwan-";

                    if (EPG.get(i).getId().equalsIgnoreCase(channelid)) {
                        noWorkingEPG = true;
                    }

                }


            }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String result) {



            MyAdapterRBSwitchChannel = new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar.this, EPG);
         //   myrvRBSwitchChannel.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);

            if (noWorkingEPG) {
                channeltimeline.setText("-");
                nowShowingProgram.setText("No working EPG");

            } else {
                channeltimeline.setText(playStarCurrent + "-" + playEndCurrent);
                nowShowingProgram.setText(programName);

            }
        }




    }

    public void toggleShowControllerChannel(){



        if (HeaderPlayer.getVisibility() == View.GONE && frameSwitchChannel.getVisibility() == View.GONE) {
           // HeaderPlayer.setVisibility(View.VISIBLE);
            FrameHeaderMenu.setVisibility(View.VISIBLE);
            handler.postDelayed(showBuffered, 1);

            /*pause.setVisibility(View.VISIBLE);*/

            //  simpleExoPlayerView.showController();
        }

    }

    public void toggleShowController(){



            if (HeaderPlayer.getVisibility() == View.GONE && frameSwitchChannel.getVisibility() == View.GONE && gridview_channelswitch_frame.getVisibility() == View.GONE) {



                HeaderPlayer.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(HeaderPlayer, View.ALPHA, 0.2f, 1.0f).setDuration(500).start();

                FrameHeaderMenu.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(FrameHeaderMenu, View.ALPHA, 0.2f, 1.0f).setDuration(500).start();

                handler.postDelayed(showBuffered, 1);
                switchChannel.requestFocus();
                epg_ll_switchChannel.setVisibility(View.VISIBLE);
                /*pause.setVisibility(View.VISIBLE);*/

                new CountDownTimer(500,500) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {

                       new exoplayer_layar.getLatestChannelViews().execute();

                       if(EPGenable){
                           //new exoplayer_layar.UpdateEPG().execute();
                       }

                    }
                }.start();



                //  simpleExoPlayerView.showController();
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

    public void trimCache() {
        try {
            File dir = getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir)
    {
        if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            boolean success = deleteDir(new File(dir, children[i]));
            if (!success) {
                return false;
            }
        }
    }
        return dir.delete();
    }

    private void LoadEPGSecoundTime() {

        String firstCat="";
        ArrayList<String> ChannelList;
        String programName="",playStarCurrent="",playEndCurrent="";
        Boolean noWorkingEPG=false;

        String id,name,logoPath,url,category,sypnopsis,premium,ProgInfo1,ProgInfo2;
        Integer epgid;
        //    getURL wget=new getURL();
        //  String json= wget.getURL(server + "apps/livetv/vd452ax3.php");
        //  String json= wget.getURL(server+b.rvd452ax3()+".php?tk="+tk);


        try {
            JSONObject objectPremium = new JSONObject(String.valueOf(json));
            JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

            JSONObject objectPremium2 = new JSONObject(String.valueOf(epgJson));
            JSONArray EPGdata = (JSONArray) objectPremium2.getJSONArray("data");

            SETVODJsonCategory = new LinkedHashSet<String>();
            VODJsonId = new ArrayList<String>();
            VODJsonName = new ArrayList<String>();
            VODJsonLogoPath = new ArrayList<String>();
            VODJsonUrl = new ArrayList<String>();
            VODJsonCategory = new ArrayList<String>();
            VODJsonsypnopsis = new ArrayList<String>();
            VODJsonpremium=new ArrayList<String>();
            VODJsonepg=new ArrayList<Integer>();
            VODJsonprograminfo=new ArrayList<String>();
            VODJsonprograminfo2=new ArrayList<String>();
            //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
            String VODid,VODname,VODlogo,VODurl,VODcategory,VODsypnopsis,VODPremium,prinfo="",prinfo2="";
            Integer Epgid;
            Boolean found;
            for (int i = 0; i < VodData.length(); i++) {
                VODid=VodData.getJSONObject(i).getString("id");
                VODname = VodData.getJSONObject(i).getString("name");
                VODlogo = VodData.getJSONObject(i).getString("logoPath");
                VODurl = VodData.getJSONObject(i).getString("playUrl");
                VODcategory = VodData.getJSONObject(i).getString("genre");
                VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");
                VODPremium = VodData.getJSONObject(i).getString("premium");
                Epgid =VodData.getJSONObject(i).getInt("epg");
                found=false;
                noWorkingEPG=false;
                dlog.log_d(debug, "EPGID", String.valueOf(Epgid));

                prinfo="No working EPGhazwan-hazwan-";
                prinfo2="No working EPGhazwan-hazwan-";

                if (Epgid != 0){

                    for (int j = 0; j < EPGdata.length(); j++) {


                        Integer PID = EPGdata.getJSONObject(j).getInt("pid");

                        dlog.log_d(debug, "Check EPG ID", String.valueOf(Epgid) + "vs" + String.valueOf(PID));


                        if((Epgid - PID)== 0) {

                            JSONArray EPGArray = (JSONArray) EPGdata.getJSONObject(j).getJSONArray("egp");

                            for (int k = 0; k < EPGArray.length(); k++) {

                                Long Now = System.currentTimeMillis()/1000;
                                String ts = Now.toString();

                                String program = EPGArray.getJSONObject(k).getString("program");
                                String start = EPGArray.getJSONObject(k).getString("play");
                                String end = EPGArray.getJSONObject(k).getString("end");

                                Long EpgStart=Long.parseLong(start);
                                Long EpgEnd=Long.parseLong(end);
                             /*   dlog.log_d(debug, "current", String.valueOf(Now));
                                dlog.log_d(debug, "CheckEPG", String.valueOf(EpgStart) + "<" + String.valueOf(Now) + "<" + String.valueOf(EpgEnd));
*/
                                if(EpgStart < Now && Now < EpgEnd) {
//                                    dlog.log_d(debug, "CheckEPG", String.valueOf(EpgStart) + "<" + String.valueOf(Now) + "<" + String.valueOf(EpgEnd));

                             /*       dlog.log_d(debug, "program", program);
                                    dlog.log_d(debug, "program", start);
                                    dlog.log_d(debug, "program", end);*/
                                    if (k <= EPGArray.length() - 2) {
                                        String program2 = EPGArray.getJSONObject(k + 1).getString("program");
                                        String start2 = EPGArray.getJSONObject(k + 1).getString("play");
                                        String end2 = EPGArray.getJSONObject(k + 1).getString("end");
                               /*         dlog.log_d(debug, "program", program2);
                                        dlog.log_d(debug, "program", start2);
                                        dlog.log_d(debug, "program", end2);*/
                                        prinfo2=program2 + "hazwan" + start2 + "hazwan" + end2;
                                    } else {
                                        String program2 = "No working EPG";
                                        String start2 = "-";
                                        String end2 = "-";

                                        prinfo2=program2 + "hazwan" + start2 + "hazwan" + end2;

                                    }

                                    prinfo=program + "hazwan" + start + "hazwan" + end;

                                    if (VODid.equalsIgnoreCase(channelid)){


                                        SimpleDateFormat formatter=new SimpleDateFormat("hh:mm");
                                        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                                        String startPlay=formatter.format(new Date(Long.parseLong(start) * 1000));
                                        String endPlay=formatter.format(new Date(Long.parseLong(end) * 1000));

                                        programName=program;
                                        playStarCurrent=startPlay;
                                        playEndCurrent=endPlay;

                                    }

                                    break;
                                } else {

                                    program = EPGArray.getJSONObject(0).getString("program");
                                    start = EPGArray.getJSONObject(0).getString("play");
                                    end = EPGArray.getJSONObject(0).getString("end");


                                    String program2 = EPGArray.getJSONObject(1).getString("program");
                                    String start2 = EPGArray.getJSONObject(1).getString("play");
                                    String end2 = EPGArray.getJSONObject(1).getString("end");

                                    prinfo=program + "hazwan" + start + "hazwan" + end;

                                    if (VODid.equalsIgnoreCase(channelid)) {

                                        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                                        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
                                        String startPlay = formatter.format(new Date(Long.parseLong(start) * 1000));
                                        String endPlay = formatter.format(new Date(Long.parseLong(end) * 1000));

                                        programName = program;
                                        playStarCurrent = startPlay;
                                        playEndCurrent = endPlay;

                                    }
                                    prinfo2=program2 + "hazwan" + start2 + "hazwan" + end2;

                                }


                            }
                            found=true;
                        }
                        if(found)
                            break;
                    }

                } else {
                    prinfo="No working EPGhazwan-hazwan-";
                    prinfo2="No working EPGhazwan-hazwan-";

                    if (VODid.equalsIgnoreCase(channelid)) {
                        noWorkingEPG=true;
                    }

                }

                VODJsonId.add(VODid);
                VODJsonName.add(VODname);
                VODJsonLogoPath.add(VODlogo);
                VODJsonUrl.add(VODurl);
                VODJsonCategory.add(VODcategory);
                VODJsonsypnopsis.add(VODsypnopsis);
                VODJsonpremium.add(VODPremium);
                VODJsonepg.add(Epgid);
                VODJsonprograminfo.add(prinfo);
                VODJsonprograminfo2.add(prinfo2);
                SETVODJsonCategory.add(VODcategory);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Toast.makeText(getApplicationContext(),objectPremium.getBoolean("state"),Toast.LENGTH_LONG).show();

        VODCat=new ArrayList<String>();

        for (String s : SETVODJsonCategory) {
         //   Log.d("SETVODJsonCategory",s);

            VODCat.add(s);


        }



/*
            for(int i=0;i<VODCat.size();i++){
                firstCat=VODCat.get(i).toString();
                break;
            }

*/

        EPG=new ArrayList<>();
        for (int i=0;i<VODJsonName.size();i++){
            id =VODJsonId.get(i).toString();
            name=VODJsonName.get(i).toString();
            category=VODJsonCategory.get(i).toString();
            logoPath=VODJsonLogoPath.get(i).toString();
            url=VODJsonUrl.get(i).toString();
            sypnopsis=VODJsonsypnopsis.get(i).toString();
            premium=VODJsonpremium.get(i).toString();
            epgid=VODJsonepg.get(i);
            ProgInfo1=VODJsonprograminfo.get(i).toString();
            ProgInfo2=VODJsonprograminfo2.get(i).toString();
            // try {
            // bitmap = BitmapFactory.decodeStream((InputStream)new URL(logoPath).getContent());
            //} catch (IOException e) {
            //      e.printStackTrace();
            //  }
            if(Watchcategory.equalsIgnoreCase(category)){
                EPG.add(new ExoBook(name, category, url, logoPath, sypnopsis,id,premium,epgid,ProgInfo1,ProgInfo2));

            }
        }


        ChannelCategory.setText(Watchcategory);


        MyAdapterRBSwitchChannel=new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar.this,EPG);
        myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);

        if(noWorkingEPG){
            channeltimeline.setText("-");
            nowShowingProgram.setText("No working EPG");

        }else {
            channeltimeline.setText(playStarCurrent + "-" + playEndCurrent);
            nowShowingProgram.setText(programName);

        }

        epgframeopen=true;




    }


    private Runnable showBuffered = new Runnable() {


        @Override
        public void run() {
            // do what you need to do

       //     float decimalFormat=(float)player.getBufferedPercentage()/100;
         //   float realBufferedPosition=decimalFormat * player.getDuration();

       //     dlog.log_d(debug,"Duration",String.valueOf(player.getDuration()));
       //     dlog.log_d(debug,"Buffer percentage",String.valueOf(player.getBufferedPercentage()));
           // dlog.log_d(debug,"Buffer position",String.valueOf(realBufferedPosition));

         //   dlog.log_d(debug,"Current Position",String.valueOf(player.getCurrentPosition()));

            myTimeBar.setEnabled(false);





            if(allowOSD) {

                if(player != null) {
                    myTimeBar.setDuration(player.getDuration());
                    myTimeBar.setPosition(player.getCurrentPosition());
                    myTimeBar.setBufferedPosition(player.getBufferedPosition());
                    Log.d("BufferedPosition",String.valueOf(player.getBufferedPosition()));
                    Log.d("CurrentPosition",String.valueOf(player.getCurrentPosition()));
                }
            }
                // and here comes the "trick"
                handler.postDelayed(this, 1000);

        }
    };

}
