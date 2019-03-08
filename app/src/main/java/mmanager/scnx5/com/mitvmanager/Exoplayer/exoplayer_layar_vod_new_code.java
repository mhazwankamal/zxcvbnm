package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.CaptioningManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkathirikhalid.util.ConnectionAppCompactActivity;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.text.CaptionStyleCompat;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultAllocator;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.TransitionManager;

import net.cryptobrewery.macaddress.MacAddress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TimeZone;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.abdyxoorp;
import mmanager.scnx5.com.abdyxoorpvod;
import mmanager.scnx5.com.authorization.get_menu_classs_navigation;
import mmanager.scnx5.com.decrypt.decrypt;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.LiveBook;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;
import mmanager.scnx5.com.mitvmanager.old_getURL;
import mmanager.scnx5.com.mitvmanager.setViewSizeByReso;
import mmanager.scnx5.com.vd452ax3;

import static android.view.View.VISIBLE;
import static java.lang.Integer.parseInt;

public class exoplayer_layar_vod_new_code extends ConnectionAppCompactActivity implements Player.EventListener {

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
    private DefaultTrackSelector  trackSelector;
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
    private FrameLayout play,pause,fitScreen,fit169;
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
    private Boolean EPGenable;
    private TextView VODtimeprogress,VODtimeduration;
    private Boolean totaldurationset;
    private TextView movieTitleDisplay;
    private Boolean setProxyUp=false;
    private setViewSizeByReso setView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
       // java.lang.System.setProperty("java.net.preferIPv4Stack", "true");
      //  java.lang.System.setProperty("java.net.preferIPv6Addresses", "false");


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exoplayer_layar_vod_new_code);
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

        dlog.log_d(debug,"width",String.valueOf(setChannelwidth));

        int setTimeBarwidth =(int) (width * 0.80);
        DefaultTimeBar exotimebar=(DefaultTimeBar) findViewById(R.id.timebarexoplayer);



        exotimebar.getLayoutParams().width=setTimeBarwidth;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        context = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            channelid= extras.getString("movieId");
            Url = extras.getString("Url");
            tk = extras.getString("tk");
            channel = extras.getString("Channel");
            Watchcategory = extras.getString("category");
            server = extras.getString("server");
            thumbnail =extras.getString("thumbnail");
            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }


       // mAPIService = GetApiUtils.getAPIService("");

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        connectPref = getApplicationContext().getSharedPreferences("connection", MODE_PRIVATE);

        setttingPref=getApplication().getSharedPreferences("setting",MODE_PRIVATE);
        okhttp = setttingPref.getBoolean("httpclient",false);
        EPGenable =setttingPref.getBoolean("epgenable",true);

        setView=new setViewSizeByReso();

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

        movieTitleDisplay = (TextView)findViewById(R.id.movietiitledisplay);

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
        play = (FrameLayout) findViewById(R.id.play_exo_button);
        pause = (FrameLayout) findViewById(R.id.pause_exo_button);




        HeaderPlayer.setVisibility(View.GONE);
        FrameHeaderMenu.setVisibility(View.GONE);

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


        FrameHeaderMenu.setVisibility(View.GONE);

        channelNameTxt = (TextView) findViewById(R.id.channelname);
        ChannelIcon = (ImageView) findViewById(R.id.channelIcon);
        nowShowingProgram=(TextView)findViewById(R.id.nowshowProgram);
        channeltimeline=(TextView)findViewById(R.id.channel_timeline);

        //vod
        VODtimeprogress =(TextView)findViewById(R.id.exoplayer_vod_currentposition);
        VODtimeduration=(TextView)findViewById(R.id.exoplayer_vod_duration);

        InternetSpeed=(ImageView)findViewById(R.id.internet_speed);
      //  logo_l3=(ImageView)findViewById(R.id.logo_l3_exo);
       // logo_l3.setVisibility(View.GONE);
        //menu navigation
        LinearLayout menu_home_button=(LinearLayout)findViewById(R.id.menu_home_ll);
        LinearLayout movie_menu=(LinearLayout)findViewById(R.id.home_menu_movie);
        LinearLayout setting_menu=(LinearLayout)findViewById(R.id.menu_settings_ll);











        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                player.setPlayWhenReady(true);
                simpleExoPlayerView.hideController();
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
                pause.requestFocus();
               // Log.d("play","test");
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(false);

                simpleExoPlayerView.hideController();
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                play.requestFocus();
               // Log.d("pause","test");
            }
        });

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


        myTimeBar.addListener(new TimeBar.OnScrubListener() {
            @Override
            public void onScrubStart(TimeBar timeBar, long position) {
                Log.d("ScrubStart","start");
                handler.removeCallbacks(showBuffered);
            }

            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                Log.d("ScrubMove",String.valueOf(position));
                setTimeBarTime(position);
            }

            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                Log.d("ScrubStop","stop");
                player.seekTo(position);
                handler.postDelayed(showBuffered,1);
            }
        });

        setOnGestureListeners();
    }

    private void setTimeBarTime(long pos){

        int seconds = (int) (pos / 1000) % 60 ;
        int minutes = (int) ((pos / (1000*60)) % 60);
        int hours   = (int) ((pos / (1000*60*60)) % 24);

        String totalCurrentPosition="";
        String adjustminutes="",adjustseconds="";
        if(minutes <10) {
            adjustminutes="0" + String.valueOf(minutes);
        } else {
            adjustminutes=String.valueOf(minutes);
        }

        if(seconds <10) {
            adjustseconds="0" + String.valueOf(seconds);
        } else {
            adjustseconds=String.valueOf(seconds);
        }


        if (hours < 1){
            totalCurrentPosition = adjustminutes + ":" + adjustseconds;
        } else {
            totalCurrentPosition = String.valueOf(hours) + ":" + adjustminutes + ":" + adjustseconds;
        }

        VODtimeprogress.setText(totalCurrentPosition);

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

        if (HeaderPlayer.getVisibility() == View.VISIBLE ){
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



            if (parseInt(Build.VERSION.SDK) > 5
                    && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
                    && event.getRepeatCount() == 0) {


                return true;
            }

            if (parseInt(Build.VERSION.SDK) > 5
                    && keyCode == KeyEvent.KEYCODE_DPAD_LEFT
                    && event.getRepeatCount() == 0) {

                return true;
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

         } else {

           releaseall();
           finish();
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

        MyAdapterRBSwitchChannel=new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar_vod_new_code.this,EPG);
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

        MyAdapterRBSwitchChannel=new RecyclerViewAdapterSwitchChannelExoPlayer(exoplayer_layar_vod_new_code.this,EPG);
        myrvRBSwitchChannel.setAdapter(MyAdapterRBSwitchChannel);
        myrvRBSwitchChannel.smoothScrollToPosition(channelPos);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnGestureListeners() {
        simpleExoPlayerView.setOnTouchListener(new OnSwipeTouchListener(exoplayer_layar_vod_new_code.this) {
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

            abdyxoorpvod z = new abdyxoorpvod();


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
         // Log.d("tk & chanel id",tk + " and " + channelid);
             //   Log.d("proxy",ch);
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

                    Toast.makeText(exoplayer_layar_vod_new_code.this, "There is something wrong with your internet connection.", Toast.LENGTH_SHORT);
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
        bandwidthMeter = new DefaultBandwidthMeter();
        videoTrackSelectionFactory =new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector();

        allocator = new DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE);


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
        new exoplayer_layar_vod_new_code.checkLatestApps().execute();
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
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            movieTitleDisplay.setText(channel);
            if (pdLoading != null && pdLoading.isShowing()) {

                // pdLoading.dismiss();

            }

        }

        if(playbackState == Player.STATE_ENDED){
            Exoplayingstate=false;
            simpleExoPlayerView.hideController();
            finish();
        }

        if (playbackState == Player.STATE_BUFFERING) {

            allowOSD=false;
            Exoplayingstate=false;
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
            if (pdLoading != null && !pdLoading.isShowing()) {
                //  Toast.makeText(exoplayer_layar.this,String.valueOf(playbackState),Toast.LENGTH_SHORT).show();

                pdLoading = new ProgressDialog(exoplayer_layar_vod_new_code.this);

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
                    if (exoplayer_layar_vod_new_code.this.isDestroyed()) {

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
                        CustomDialogSourceErrorClass cdde=new CustomDialogSourceErrorClass(exoplayer_layar_vod_new_code.this);
                        cdde.show();

                        simpleExoPlayerView.setShowBuffering(PlayerView.SHOW_BUFFERING_NEVER);


                        //   finish();
                    }

                }
                counter = counter + 1;

                break;

            case ExoPlaybackException.TYPE_RENDERER:
                Toast.makeText(exoplayer_layar_vod_new_code.this, "ERROR TYPE_SOURCE " + error.getRendererException().getMessage(), Toast.LENGTH_LONG).show();
                Log.e("ERROR TYPE_RENDERER", "TYPE_RENDERER: " + error.getRendererException().getMessage());
                break;

            case ExoPlaybackException.TYPE_UNEXPECTED:
                Toast.makeText(exoplayer_layar_vod_new_code.this, "ERROR TYPE_SOURCE " + error.getUnexpectedException().getMessage(), Toast.LENGTH_LONG).show();
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
                Toast.makeText(exoplayer_layar_vod_new_code.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG).show();
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
                    CustomLatestApkVersion clav=new CustomLatestApkVersion(exoplayer_layar_vod_new_code.this);
                    clav.show();
                    //return;
                } else {

                    new exoplayer_layar_vod_new_code.checkForMaxConnection().execute();

                }
            }
        }

    }

    public void exit_dueto_oldversion(){
       // Toast.makeText()
        Intent i =new Intent();
        i.setClassName(exoplayer_layar_vod_new_code.this,menu_navigation.getHomeActivity());
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
                    checkMaxConnectionn = wget.getURL(server + "apps/exoplayer/update_onlinev7.php?mac_address=" + Macaddress + "&tk=" + tk + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=vod");
                } else {
                    checkMaxConnectionn = old_wget.getURL(server + "apps/exoplayer/update_onlinev7.php?mac_address=" + Macaddress + "&tk=" + tk + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=vod");

                }
                } catch (IOException e) {
                e.printStackTrace();
            }


            /* checkMaxConnectionn = oldwget.getURL(server + "apps/exoplayer/update_onlinev4.php?mac_address=" + Macaddress + "&username=" + username + "&model=" + DeviceModel + "&channel=" + channelid + "&opt=livetv");
             */

            firststart = false;

            if(checkMaxConnectionn != null) {

             //   Log.d("jsonMax","V " + checkMaxConnectionn);

                String[] cmax=checkMaxConnectionn.split(",");
                tk=cmax[1];
                JSONObject GetDataJSONOnline;
                dlog.log_d(debug, "jsonMax", String.valueOf(d.decryptStr(cmax[0])));

                try {
                    GetDataJSONOnline = new JSONObject(String.valueOf(d.decryptStr(cmax[0])));
                    String onlineStatus = GetDataJSONOnline.getString("token");

                    if (onlineStatus.equalsIgnoreCase("max")) {
                        maxConnection = true;

                        firstDevice = GetDataJSONOnline.getString("device0");
                        SecounDevice = GetDataJSONOnline.getString("device1");


                    } else {
                        CurrentchannelViews = String.valueOf(GetDataJSONOnline.getInt("view"));
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
                Toast.makeText(exoplayer_layar_vod_new_code.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG).show();
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
                    CustomDialogMaxConnection cdmC=new CustomDialogMaxConnection(exoplayer_layar_vod_new_code.this,firstDevice,SecounDevice);
                    cdmC.show();
                    return;
                } else {

                    new exoplayer_layar_vod_new_code.StartChannel().execute();

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



         if (!EPGenable){
             epgJson="disable";
         }


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


                    new YouTubeExtractor(exoplayer_layar_vod_new_code.this) {
                        @Override
                        public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                            if (ytFiles != null) {


                                int itag = 22;
                                String downloadUrl = ytFiles.get(17).getUrl();
                                Toast.makeText(exoplayer_layar_vod_new_code.this, downloadUrl, Toast.LENGTH_LONG).show();
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

                player = ExoPlayerFactory.newSimpleInstance(exoplayer_layar_vod_new_code.this, trackSelector);

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



                player.addListener(exoplayer_layar_vod_new_code.this);
                Player.VideoComponent test = player.getVideoComponent();


                test.addVideoListener(new VideoListener() {
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


                String VideoUrilayar3 = "layar3.com";

                if (combineUrl.toLowerCase().contains(VideoUrilayar3.toLowerCase())) {
                   // combineUrl = combineUrl.replace("portal.layar3.com", "10.10.10.10");

                }
              // combineUrl="http://192.168.0.103/polisevo/play.m3u8";
                videoUri = Uri.parse(combineUrl);


                   dlog.log_d(debug,"playUrl",combineUrl);
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

                dataSourceFactory = new DefaultDataSourceFactory(context, "VLC/3.0.0-git LibVLC/3.0.0-git", bandwidthMeter);


                /* dataSourceFactory = new DefaultDataSourceFactory(context, oldwget.getURL(server + x.abcxtengtyou() + EEE));
                 */

                // Produces Extractor instances for parsing the media data.
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory().setConstantBitrateSeekingEnabled(true);

                // videoUri = Uri.parse("http://10.10.10.10:8888/live/mhazwankamal/1234/14012.m3u8");

                int type = Util.inferContentType(videoUri);

                String layar3 = "portal.layar3.com";
                String layar3test = "ms1-int.layar3.com";



                if (setProxyUp) {
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

                        //    Toast.makeText(exoplayer_layar.this,"DASH" + String.valueOf(type),Toast.LENGTH_SHORT).show();
                        break;
                    case C.TYPE_HLS:
                        DefaultLoadErrorHandlingPolicy df = new DefaultLoadErrorHandlingPolicy();

                         videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
                       // videoSource = new HlsMediaSource.Factory(dataSourceFactory).setLoadErrorHandlingPolicy(df).createMediaSource(videoUri);
                        //videoSource=new HlsMediaSource(videoUri,dataSourceFactory, handler, null);

                        // Toast.makeText(exoplayer_layar.this,"HLS" + String.valueOf(type),Toast.LENGTH_SHORT).show();
                      //  player.prepare(videoSource);
                     //   player.setPlayWhenReady(true);
                        break;
                    case C.TYPE_OTHER:
                        videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).setExtractorsFactory(extractorsFactory).createMediaSource(videoUri);

                        //    Toast.makeText(exoplayer_layar.this,"OTHERS" + String.valueOf(type),Toast.LENGTH_SHORT).show();

                        break;
                    default: {
                        throw new IllegalStateException("Unsupported type: " + type);
                    }
                }



                URL newURL= null;
                try {
                    newURL = new URL(combineUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                String srtUriString=newURL.getProtocol() + "://" +newURL.getHost()+":"+newURL.getPort()+ newURL.getFile().substring(0, newURL.getFile().lastIndexOf(".")) + ".srt";
                Uri srtUri=Uri.parse(srtUriString);
           //     Log.d("Subtitle",srtUriString);
                Format subtitleFormat=Format.createTextSampleFormat(null, MimeTypes.APPLICATION_SUBRIP,
                        null, Format.NO_VALUE, Format.NO_VALUE, "en", null, Format.OFFSET_SAMPLE_RELATIVE);

                MediaSource textMediaSource = new SingleSampleMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(srtUri, subtitleFormat, C.TIME_UNSET);
                MergingMediaSource mediaSourceMerged = new MergingMediaSource(videoSource, textMediaSource);

                player.prepare(mediaSourceMerged);
                player.setPlayWhenReady(true);



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
                simpleExoPlayerView.getSubtitleView().setApplyEmbeddedStyles(false);
                simpleExoPlayerView.getSubtitleView().setStyle(new CaptionStyleCompat(Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT,CaptionStyleCompat.EDGE_TYPE_OUTLINE,Color.BLACK,null));
                simpleExoPlayerView.getSubtitleView().setFixedTextSize(TypedValue.COMPLEX_UNIT_PX, setView.getPixelByReso(0.04));
                simpleExoPlayerView.hideController();
                simpleExoPlayerView.setControllerShowTimeoutMs(0);




                controllerView = false;


                channelNameTxt.setText(channel);
                //    TextView CategoryNameTxt = (TextView) findViewById(R.id.channelcategory);
                //  CategoryNameTxt.setText(category);
                Glide.with(exoplayer_layar_vod_new_code.this).load(thumbnail).into(ChannelIcon);





                resolution.setVisibility(View.GONE);


                HeaderPlayer.setVisibility(View.VISIBLE);
                FrameHeaderMenu.setVisibility(View.VISIBLE);
                handler.postDelayed(showBuffered, 1);



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

               totaldurationset=false;

//
//                if (!switchChannelExoPlayer) {
//                    new exoplayer_layar_vod_new_code.LoadVODInBookList().execute();
//                } else {
//                    if(EPGenable){
//                    new exoplayer_layar_vod_new_code.UpdateEPG().execute();
//                }
//                }
        }
    }



    private Runnable ExoPlayerTimer = new Runnable() {
        @Override
        public void run() {

            exoplayertimerun=true;

            long lastPlay=pref.getLong("lastChangeChannel",0);

            long currentTime = System.currentTimeMillis()/1000;


            if(currentTime - lastPlay > 14400){
           //   if(currentTime - lastPlay > 5){

                CustomDialogClass cdd=new CustomDialogClass(exoplayer_layar_vod_new_code.this);
                cdd.show();

            }else {



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
                i.setClassName(exoplayer_layar_vod_new_code.this,menu_navigation.getHomeActivity());
                i.putExtra("PersonID", username);
                i.putExtra("token", tk);
                i.putExtra("server", server);
                startActivity(i);
                releaseall();
                finishAffinity();
            } else {
                Toast.makeText(exoplayer_layar_vod_new_code.this,"There is something wrong with your internet connection.",Toast.LENGTH_LONG);
                if(player != null)
                    player.stop();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }




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



            if (HeaderPlayer.getVisibility() == View.GONE ) {



                HeaderPlayer.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(HeaderPlayer, View.ALPHA, 0.2f, 1.0f).setDuration(500).start();

                FrameHeaderMenu.setVisibility(View.VISIBLE);
                ObjectAnimator.ofFloat(FrameHeaderMenu, View.ALPHA, 0.2f, 1.0f).setDuration(500).start();

                handler.postDelayed(showBuffered, 1);

                /*pause.setVisibility(View.VISIBLE);*/





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

            myTimeBar.setEnabled(true);

           if(Exoplayingstate) {

               if (player.getDuration() > 0 && !totaldurationset) {

                   myTimeBar.setDuration(player.getDuration());
                   totaldurationset = true;
                   //  Log.d("Totalduration", String.valueOf(player.getDuration()));

                   int seconds = (int) (player.getDuration() / 1000) % 60;
                   int minutes = (int) ((player.getDuration() / (1000 * 60)) % 60);
                   int hours = (int) ((player.getDuration() / (1000 * 60 * 60)) % 24);

                   String totalDuration = "";
                   String adjustminutes = "", adjustseconds = "";

                   if (minutes < 10) {
                       adjustminutes = "0" + String.valueOf(minutes);
                   } else {
                       adjustminutes = String.valueOf(minutes);
                   }

                   if (seconds < 10) {
                       adjustseconds = "0" + String.valueOf(seconds);
                   } else {
                       adjustseconds = String.valueOf(seconds);
                   }


                   if (hours < 1) {
                       totalDuration = adjustminutes + ":" + adjustseconds;
                   } else {
                       totalDuration = String.valueOf(hours) + ":" + adjustminutes + ":" + adjustseconds;
                   }

                   VODtimeduration.setText(totalDuration);
               }
           }

            if(allowOSD) {

                if(player != null) {
                    myTimeBar.setPosition(player.getCurrentPosition());
                    myTimeBar.setBufferedPosition(player.getBufferedPosition());
                    myTimeBar.setKeyTimeIncrement(30000);

                    int seconds = (int) (player.getCurrentPosition() / 1000) % 60 ;
                    int minutes = (int) ((player.getCurrentPosition() / (1000*60)) % 60);
                    int hours   = (int) ((player.getCurrentPosition() / (1000*60*60)) % 24);

                    String totalCurrentPosition="";
                    String adjustminutes="",adjustseconds="";
                    if(minutes <10) {
                        adjustminutes="0" + String.valueOf(minutes);
                    } else {
                        adjustminutes=String.valueOf(minutes);
                    }

                    if(seconds <10) {
                        adjustseconds="0" + String.valueOf(seconds);
                    } else {
                        adjustseconds=String.valueOf(seconds);
                    }


                    if (hours < 1){
                        totalCurrentPosition = adjustminutes + ":" + adjustseconds;
                    } else {
                        totalCurrentPosition = String.valueOf(hours) + ":" + adjustminutes + ":" + adjustseconds;
                    }

                    VODtimeprogress.setText(totalCurrentPosition);

                }
            }
                // and here comes the "trick"
                handler.postDelayed(this, 1000);

        }
    };

}
