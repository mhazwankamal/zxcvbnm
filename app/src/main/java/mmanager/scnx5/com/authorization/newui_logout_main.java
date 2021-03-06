package mmanager.scnx5.com.authorization;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkathirikhalid.util.ConnectionAppCompactActivity;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.transition.ViewPropertyAnimationFactory;
import com.bumptech.glide.request.transition.ViewPropertyTransition;
import com.dcastalia.localappupdate.DownloadApk;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.tuyenmonkey.mkloader.MKLoader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Timer;

import mmanager.scnx5.com.abcsesscxz;
import mmanager.scnx5.com.abcvodzxc;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.decrypt.decrypt;
import mmanager.scnx5.com.mitvmanager.Exoplayer.OnSwipeTouchListener;
import mmanager.scnx5.com.mitvmanager.Exoplayer.exoplayer_layar;
import mmanager.scnx5.com.mitvmanager.FloatingMiTV;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.LiveBook;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.RecyclerViewAdapterRB;
import mmanager.scnx5.com.mitvmanager.RedBoxGrid.redbox_grid_activity;
import mmanager.scnx5.com.mitvmanager.RequestPermissionHandler;
import mmanager.scnx5.com.mitvmanager.VODGrid.vod_grid_activity;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.log_;
import mmanager.scnx5.com.mitvmanager.old_getURL;
import mmanager.scnx5.com.vd452ax3;

import static android.view.View.VISIBLE;


public class newui_logout_main extends ConnectionAppCompactActivity {
    private RequestPermissionHandler mRequestPermissionHandler;
    public String personID="No-one",uniqueID="",tk="",server,changelogURL;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    private decrypt d=new decrypt();
    private abcyxoorp a=new abcyxoorp();

    private abcvodzxc c=new abcvodzxc();
    private Handler handler = new Handler();
    private boolean writepermission=false;
    private getURL wget=new getURL();
    private old_getURL old_wget=new old_getURL();
    private String Jsonliveandevent,JsonRecentChannels,JsonTrendingChannel,JsonMostPopular;
    public List<HomeTrendBook> VODTrendingChannel,VODRecentChannel,VODMostPopular;
    public List<LiveEventBook> BookLiveEvent;
    public List<AllBook> AllChannelCategory;
    public List<String> VODJsonId;
    public List<String> VODJsonName;
    public List<String> VODJsonLogoPath;
    public List<String> VODJsonUrl;
    public List<String> VODJsonCategory;
    public List<String> VODJsonsypnopsis;
    public List<String> VODJsonpremium;
    public List<Integer> VODJsonEPG;
  //  private RecyclerView myrvRB,myrvTrend;
    private RecyclerView myLiveEvent,myrvMP,myrvTC,myrvrb;
    private RecyclerViewAdapterHomeTrend myAdapterHomeYW,getMyAdapterHomeTC;
    RecyclerViewAdapterHomeTrend myAdapterHomeTrend;
    RecyclerViewAdapterHomeLastWatch myAdapterHomeLastWatch;
    RecyclerViewAdapterHomeMostPopular myAdapterHomeMostPopular;
    RecyclerViewAdapterHomeLiveEvent myAdapterHomeLiveEvent;
    private RecyclerViewVerticalAdapterHome myAdapterHomeVertical;
    private Boolean debug=true;
    private LinearLayout ll_last_watching;
    private log_ dlog=new log_();
    private Integer scrollPos=-1;
    private LinearLayout scrollingview,lastWatching_ll,trending_channel_ll,most_popular_ll,ll_livenet_container;
    private Integer scrollPosLastWatching=0;
    private Boolean lastwatching_ll_focus=false;
    private SharedPreferences pref,pref2,setttingPref,jsonDownloaded;
    private MKLoader threepulse0,threepulse1,threepulse2,threepulse3;
    private SharedPreferences.Editor editor,editor2,jsonDownloadedEdit;
    private ScrollView home_scroll;
    private vd452ax3 b=new vd452ax3();
    private String JsonLiveTV,Json;
    private get_menu_classs_navigation menu_navigation=new get_menu_classs_navigation();
    private String remainingdays="";
    public ImageView backgroundImage;
    private Boolean loadingPage=false;
    private  SliderLayout sliderLayout;
    private LinearLayout  liveTV_menu;
    private LinearLayout ll_image_indicator;
    private  TextView liveTitleEvent,live_date,live_time;
    private Integer liveEventPos=0;
    private Integer HomeScrollPOS=0;
    private FrameLayout rootView;
    private Boolean okhttp;
    private Boolean downloadJsonAgain=false;
    CountDownTimer AutoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.newui_logout_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            LinearLayout liveEventContainer = (LinearLayout) findViewById(R.id.live_event_containter);
            ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) liveEventContainer.getLayoutParams();

            int width= Resources.getSystem().getDisplayMetrics().widthPixels;
            dlog.log_d(debug,"newloginheight",String.valueOf(width));
            if (width < 1920){
                params.topMargin=(int) (255* Resources.getSystem().getDisplayMetrics().density);

            } else {
                params.topMargin=(int) (230 * Resources.getSystem().getDisplayMetrics().density);

            }


        }

        mRequestPermissionHandler = new RequestPermissionHandler();

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            server = extras.getString("server");
            tk = extras.getString("token");
            personID = extras.getString("PersonID");


            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }
        JsonRecentChannels="none";

        pref = getApplicationContext().getSharedPreferences("HomeUI", MODE_PRIVATE);
        pref2 = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        setttingPref=getApplication().getSharedPreferences("setting",MODE_PRIVATE);
        okhttp = setttingPref.getBoolean("httpclient",false);

        if (okhttp){
            okhttp=false;
        } else {
            okhttp=true;
        }


        editor = pref.edit();
        editor2=pref2.edit();
       // myrvYW = (RecyclerView) findViewById(R.id.li_lastwatching_channel);
      threepulse1 =(MKLoader) findViewById(R.id.loading_spinner_home);
       threepulse2=(MKLoader) findViewById(R.id.loading_spinner_home2);
       threepulse3=(MKLoader) findViewById(R.id.loading_spinner_home3);
       home_scroll=(ScrollView) findViewById(R.id.scroll_home);
       myrvTC = (RecyclerView) findViewById(R.id.li_trending_channel);
       myrvrb=(RecyclerView) findViewById(R.id.li_lastwatching_channel);
       myrvMP=(RecyclerView)findViewById(R.id.li_most_popular);
       lastWatching_ll = (LinearLayout) findViewById(R.id.lastwatching_ll);
       trending_channel_ll=(LinearLayout)findViewById(R.id.trending_channel_ll);
       most_popular_ll=(LinearLayout)findViewById(R.id.most_popular_ll);
       ll_livenet_container=(LinearLayout)findViewById(R.id.liveEvent);
       liveTV_menu=(LinearLayout)findViewById(R.id.menu_livetv);
       LinearLayout movie_menu=(LinearLayout)findViewById(R.id.home_menu_movie);
       LinearLayout setting_menu=(LinearLayout)findViewById(R.id.menu_settings_ll);
       rootView=(FrameLayout)findViewById(R.id.root);

        Glide.with(newui_logout_main.this)
                .asBitmap()
                .load("https://layar3.com/apps/home/l3_background_new.jpg")
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        rootView.setBackground(new BitmapDrawable(resource));
                    }
                });



//        sliderLayout = (SliderLayout) findViewById(R.id.imageSlider);
//        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.NONE); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderLayout.setScrollTimeInSec(5); //set scroll delay in seconds :
//
//        setSliderViews();



        //TextView modeldevice=(TextView)findViewById(R.id.userDevice);
       //TextView appsVersion=(TextView)findViewById(R.id.LayarAppsversion);


        //Image Slider Function







        LayoutInflater LInfla=getLayoutInflater();
        View v=LInfla.inflate(R.layout.welcome_user,(ViewGroup)findViewById(R.id.custom_toast));
        TextView username_welcome=(TextView)v.findViewById(R.id.welcome_user);
        ImageView profile_pic =(ImageView)v.findViewById(R.id.welcome_profile_pic);

       // Glide.with(this).load("https://www.ienglishstatus.com/wp-content/uploads/2018/04/Anonymous-Whatsapp-profile-picture.jpg").into(profile_pic);
      //  username_welcome.setText(personID.toUpperCase());

//       Toast toast=new Toast(getApplicationContext());
//       toast.setGravity(Gravity.TOP|Gravity.RIGHT,0,50);
//
//       toast.setDuration(Toast.LENGTH_LONG);
//       toast.setView(v);
//        new CountDownTimer(1500,1000) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                toast.show();
//            }
//        }.start();

       //  modeldevice.setText("via " + Build.MANUFACTURER + " " + Build.MODEL);
     //  appsVersion.setText("on Layar3 -" + version);


        String nwtk=d.decryptStr(tk);

        JSONObject objectPremium = null;
        remainingdays="";
        try {
            objectPremium = new JSONObject(String.valueOf(nwtk));
            // String loginData = objectPremium.getString("data");


            remainingdays = objectPremium.getString("days");
        } catch (JSONException e) {
            e.printStackTrace();
        }


       changelogURL=server + "apps/update/update-changelog.json";

        AppUpdater appUpdater=new AppUpdater(newui_logout_main.this);
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
                            if (okhttp) {
                                updateJSON = wget.getURL(changelogURL);
                            } else {
                                updateJSON = old_wget.getURL(changelogURL);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        JSONObject GetDataJSONUpdate;
                        try {
                            GetDataJSONUpdate=new JSONObject(String.valueOf(updateJSON));
                            String url=GetDataJSONUpdate.getString("url");


                            DownloadApk downloadApk = new DownloadApk(newui_logout_main.this);

                            downloadApk.startDownloadingApk(url);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
        appUpdater.start();





        liveTV_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(newui_logout_main.this, exoplayer_layar.class);
                handler.removeCallbacks(AutoSlideMHMKRecyclerViewLiveEvent);
                if(loadingPage) {
                    Intent intent = new Intent();
                    intent.setClassName(newui_logout_main.this, menu_navigation.getLiveTVActivity());

                    // passing data to the book activity

                    if(JsonRecentChannels.equalsIgnoreCase("first")){

                        JSONObject objectPremium = null;
                        try {
                            objectPremium = new JSONObject(String.valueOf(Json));
                            JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

                            for (int i = 0; i <1; i++) {

                                intent.putExtra("cid",VodData.getJSONObject(i).getString("id"));
                                intent.putExtra("Title", VodData.getJSONObject(i).getString("name"));
                                intent.putExtra("Url", VodData.getJSONObject(i).getString("playUrl"));
                                intent.putExtra("Thumbnail", VodData.getJSONObject(i).getString("logoPath"));
                                intent.putExtra("Sypnopsis", VodData.getJSONObject(i).getString("sypnopsis"));
                                intent.putExtra("Category", VodData.getJSONObject(i).getString("genre"));
                                intent.putExtra("channelPos", 0);
                                intent.putExtra("premium", VodData.getJSONObject(i).getString("premium"));

                                editor2.putString("Url",VodData.getJSONObject(i).getString("playUrl"));
                                editor2.putString("Sypnopsis",VodData.getJSONObject(i).getString("sypnopsis"));
                                editor2.putString("Thumbnail",VodData.getJSONObject(i).getString("logoPath"));
                                editor2.putString("channel",VodData.getJSONObject(i).getString("name"));
                                editor2.putString("Category",VodData.getJSONObject(i).getString("genre"));
                                editor2.putString("cid",VodData.getJSONObject(i).getString("id"));
                                editor2.putString("premium", VodData.getJSONObject(i).getString("premium"));
                                editor.apply();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    else {
                        intent.putExtra("cid", VODRecentChannel.get(0).getId());
                        intent.putExtra("Title", VODRecentChannel.get(0).getTitle());
                        intent.putExtra("Url", VODRecentChannel.get(0).getUrl());
                        intent.putExtra("Thumbnail", VODRecentChannel.get(0).getThumbnail());
                        intent.putExtra("Sypnopsis", VODRecentChannel.get(0).getSysnopsis());
                        intent.putExtra("Category", VODRecentChannel.get(0).getCategory());
                        intent.putExtra("channelPos", 0);
                        intent.putExtra("premium", VODRecentChannel.get(0).getpremium());

                        editor2.putString("Url",VODRecentChannel.get(0).getUrl());
                        editor2.putString("Sypnopsis",VODRecentChannel.get(0).getSysnopsis());
                        editor2.putString("Thumbnail",VODRecentChannel.get(0).getThumbnail());
                        editor2.putString("channel",VODRecentChannel.get(0).getTitle());
                        editor2.putString("Category", VODRecentChannel.get(0).getCategory());
                        editor2.putString("cid",VODRecentChannel.get(0).getId());
                        editor2.putString("premium",  VODRecentChannel.get(0).getpremium());
                        editor.apply();

                    }
                    //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                    // start the activity
                    intent.putExtra("username", personID);
                    intent.putExtra("liveTV", "rb");
                    intent.putExtra("tk", tk);
                    intent.putExtra("server", server);
                    intent.putExtra("json", Json);

                    newui_logout_main.this.startActivity(intent);
                    //   finishAffinity();
                }
            }
        });

        movie_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(AutoSlideMHMKRecyclerViewLiveEvent);
                Intent intent =new Intent();
                intent.setClassName(newui_logout_main.this,menu_navigation.getMovieActivity());

                // passing data to the book activity
                intent.putExtra("username", personID);
                intent.putExtra("tk", tk);
                intent.putExtra("server", server);

                //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                // start the activity

                newui_logout_main.this.startActivity(intent);
            }
        });


        setting_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(AutoSlideMHMKRecyclerViewLiveEvent);
                Intent intent =new Intent();
                intent.setClassName(newui_logout_main.this,menu_navigation.getSettingActivity());

                // passing data to the book activity

                intent.putExtra("tk", tk);
                intent.putExtra("server", server);

                //  Toast.makeText(mContext,"test",Toast.LENGTH_LONG).show();
                // start the activity

                newui_logout_main.this.startActivity(intent);
            }
        });





//        editor.putInt("ScrollViewFocusPosition",getCenterScrollView(sliderLayout));
       // editor.apply();




       new newui_logout_main.loadliveandevent().execute();

    }

    public void setLiveImageBackground(String picurl){

        Glide.with(this)
                .asBitmap()
                .load(picurl)
                .apply(RequestOptions.centerCropTransform())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        backgroundImage.setBackground(new BitmapDrawable(resource));
                    }
                });



    }

    private void setSliderViews() {

        String getImageUrls="";
        JSONObject objectPremium = null;
        try {
            getImageUrls = wget.getURL(server + "apps/home/getImageSlideShow.json");
            objectPremium = new JSONObject(String.valueOf(getImageUrls));
            JSONArray ImageSlideData = (JSONArray) objectPremium.getJSONArray("data");

            for(int i=0;i<ImageSlideData.length();i++){

                SliderView sliderView = new SliderView(this);

                sliderView.setImageUrl(ImageSlideData.getJSONObject(i).getString("url"));

                sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
                sliderView.setDescription(ImageSlideData.getJSONObject(i).getString("title"));
                final int finalI = i;
                sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(SliderView sliderView) {
                        //     Toast.makeText(newui_logout_main.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                    }
                });

                //at last add this view in your layout :
                sliderLayout.addSliderView(sliderView);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (JSONException e) {
            e.printStackTrace();
        }


/*
        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(this);




            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://upcomingmovietrailerblog.files.wordpress.com/2018/04/venom-english-movie-2018.jpg?w=1075");
                    break;
                case 1:
                    sliderView.setImageUrl("https://4.bp.blogspot.com/-10fxJOuNNm0/WmVHrUrBOpI/AAAAAAAAATk/ntnGwLd5yJU-mdAD8BhEEgzGbnbzsc8HACLcBGAs/s1600/Samson-movie.jpg");
                    break;
                case 2:
                    sliderView.setImageUrl("https://images.mixroliki.ru/image/oZJT4CZjJmQ/new-hollywood-sci-fi-movies-2018-full-movie-english-hd-1080p-action-movies-2018-coming-out.jpg");
                    break;
                case 3:
                    sliderView.setImageUrl("https://i.ytimg.com/vi/wgB0UKkFPSM/maxresdefault.jpg");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
               //     Toast.makeText(newui_logout_main.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
        */
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
      //  Toast.makeText(getApplicationContext(),"Your internet connection fast!",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void connectedConnectionSlow() {
      //  Toast.makeText(getApplicationContext(),"Your internet connection slow!",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void noNetwork() {
        Toast.makeText(getApplicationContext(),"Your internet connection loss!",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume(){
        super.onResume();
        handler.removeCallbacks(AutoSlideMHMKRecyclerViewLiveEvent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        handler.removeCallbacks(AutoSlideMHMKRecyclerViewLiveEvent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        LinearLayout liveEventContainer = (LinearLayout) findViewById(R.id.live_event_containter);
        ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) liveEventContainer.getLayoutParams();


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

             liveEventContainer = (LinearLayout) findViewById(R.id.live_event_containter);
             params=(ViewGroup.MarginLayoutParams) liveEventContainer.getLayoutParams();

            int width= Resources.getSystem().getDisplayMetrics().widthPixels;
            dlog.log_d(debug,"newloginheight",String.valueOf(width));
            if (width < 1920){
                params.topMargin=(int) (255* Resources.getSystem().getDisplayMetrics().density);

            } else {
                params.topMargin=(int) (230 * Resources.getSystem().getDisplayMetrics().density);

            }


            Glide.with(newui_logout_main.this)
                    .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                    .into(backgroundImage);
            backgroundImage.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {

            params.topMargin=(int) (300* Resources.getSystem().getDisplayMetrics().density);

            Glide.with(newui_logout_main.this)
                    .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                    .apply(RequestOptions.centerCropTransform())
                    .into(backgroundImage);

            backgroundImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
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

    private class loadliveandevent extends AsyncTask<String,String,String>
    {
        String TitleType, BackgroundImageUrl, Date, Time, Title;
        Integer id,direction=0;
        String httpconnectionreturn="run";
        Long time_livenevent_json;
        String jsondownloded_livenevent_json;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingPage=false;
            threepulse1.setVisibility(View.VISIBLE);
            threepulse2.setVisibility(View.VISIBLE);
            threepulse3.setVisibility(View.VISIBLE);
            myrvTC.setVisibility(View.GONE);
            myrvMP.setVisibility(View.GONE);
            myrvrb.setVisibility(View.GONE);
            Jsonliveandevent="none";
            JsonRecentChannels="none";
            JsonTrendingChannel="none";
            JsonMostPopular="none";


        }
        @Override
        protected String doInBackground(String... params) {

            jsonDownloaded =getApplication().getSharedPreferences("JsonHomePage",MODE_PRIVATE);
            jsonDownloadedEdit=jsonDownloaded.edit();

            time_livenevent_json=jsonDownloaded.getLong("time_json_downloaded",0);
          //  Log.d("savedin",String.valueOf(time_livenevent_json));
            if (time_livenevent_json != 0){

                Long currentTime= System.currentTimeMillis()/1000;

                if(currentTime - time_livenevent_json > 900){

                    downloadJsonAgain=true;

                } else {
                    Jsonliveandevent="cache";
                    JsonRecentChannels="cache";
                    JsonTrendingChannel="cache";
                    JsonMostPopular="cache";

                }


            } else {
                downloadJsonAgain=true;
            }

          //  Log.d("Jsonliveandevent",String.valueOf(tk));

            if(downloadJsonAgain) {
                try {

                    if (okhttp) {


                        Jsonliveandevent = wget.getURL(server + "apps/home/getlive_event_json2.php?user=" + tk);


                    } else {
                        Jsonliveandevent = old_wget.getURL(server + "apps/home/getlive_event_json2.php?user=" + tk);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            if (Jsonliveandevent.equalsIgnoreCase("none")) {

                return httpconnectionreturn="stop";

            } else {
                long currentTime = System.currentTimeMillis()/1000;

                if(!downloadJsonAgain){
                    Jsonliveandevent=jsonDownloaded.getString("jsondownloded_livenevent_json","none");
                } else {
                    jsonDownloadedEdit.putString("jsondownloded_livenevent_json", Jsonliveandevent);
                    jsonDownloadedEdit.putLong("time_json_downloaded", currentTime);
                    jsonDownloadedEdit.apply();
                }
             //   Log.d("savedin",String.valueOf(Jsonliveandevent));


                String[] varntel = Jsonliveandevent.split(",");
                 tk=varntel[1];
               // String JsonStringNTEL=varntel[0];
              //  Log.d("Jsonliveandevent","L =" + tk);

                try {
                    JSONObject objectPremium = new JSONObject(d.decryptStr(String.valueOf(varntel[0])));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

                    BookLiveEvent=new ArrayList<>();
                    //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
                    for (int i = 0; i < VodData.length(); i++) {
                        TitleType = VodData.getJSONObject(i).getString("titleType");
                        BackgroundImageUrl = VodData.getJSONObject(i).getString("backgroundImage");
                        Date = VodData.getJSONObject(i).getString("date");
                        Time = VodData.getJSONObject(i).getString("time");
                        Title = VodData.getJSONObject(i).getString("title");
                        id = VodData.getJSONObject(i).getInt("channelId");


                        BookLiveEvent.add(new LiveEventBook(TitleType, BackgroundImageUrl, Date, Time, Title, id));
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            return "";

        }


        @Override
        protected void onPostExecute(String result) {

            if(httpconnectionreturn.equalsIgnoreCase("stop")){
              threepulse1.setVisibility(View.GONE);
                threepulse2.setVisibility(View.GONE);
                threepulse3.setVisibility(View.GONE);

                Toast.makeText(newui_logout_main.this,"Something went wrong. Please check your internet connection",Toast.LENGTH_SHORT).show();

                return;
            }
            else {

                backgroundImage=(ImageView) findViewById(R.id.image_background);


                //setFirstImageonLoad
//                Glide.with(newui_logout_main.this)
//                        .asBitmap()
//                        .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
//                        .apply(RequestOptions.centerCropTransform())
//                        .into(new SimpleTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                                backgroundImage.setAlpha(0);
//                                backgroundImage.animate().alpha(1).setDuration(500);
//                                backgroundImage.setBackground(new BitmapDrawable(resource));
//                            }
//                        });

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    Glide.with(newui_logout_main.this)
                            .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                            .into(backgroundImage);
                    backgroundImage.setScaleType(ImageView.ScaleType.FIT_XY);
                } else {

                    Glide.with(newui_logout_main.this)
                            .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                            .apply(RequestOptions.centerCropTransform())
                            .into(backgroundImage);
                }


                liveTitleEvent=(TextView)findViewById(R.id.live_event_tittle);
                live_date=(TextView)findViewById(R.id.live_date);
                live_time=(TextView)findViewById(R.id.live_time);

                liveTitleEvent.setText(BookLiveEvent.get(liveEventPos).getTitle());
                live_date.setText(BookLiveEvent.get(liveEventPos).getDate());
                live_time.setText(BookLiveEvent.get(liveEventPos).getTime());


                //setTitle,Date and Time



                //defineLayoutforindicator
                ll_image_indicator=(LinearLayout)findViewById(R.id.image_scroll_indicator);


                for(int i=0;i<BookLiveEvent.size();i++) {


                    ImageView imageView= new ImageView(newui_logout_main.this);
                    if(i == 0) {
                        imageView.setImageResource(R.drawable.icons8_circle_select);
                    }
                    else {
                        imageView.setImageResource(R.drawable.icons8_circle_unselect);
                    }

                 imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                 ll_image_indicator.addView(imageView);
                }
//                ImageView imageView2 = new ImageView(newui_logout_main.this);
//                imageView2.setImageResource(R.drawable.icons8_circle_unselect);
//                imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                ll_image_indicator.addView(imageView2);
//                setOnGestureListeners();
                ll_livenet_container.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View view, boolean b) {

                        if (b){
                            smoothScrollDuration(home_scroll,ll_livenet_container,0);

                             ll_livenet_container.setOnKeyListener(new View.OnKeyListener() {
                                 @Override
                                 public boolean onKey(View view, int i, KeyEvent keyEvent) {

                                     if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                                         if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT && keyEvent.getRepeatCount() == 0) {

                                             direction = 2; //right
                                             MHMKRecyclerViewLiveEvent(direction, BookLiveEvent.size());
                                      //       Log.d("Test", String.valueOf(keyEvent));
                                         }

                                         if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT && keyEvent.getRepeatCount() == 0) {

                                             direction = 1; //right
                                             MHMKRecyclerViewLiveEvent(direction, BookLiveEvent.size());
                                      //       Log.d("Test", String.valueOf(keyEvent));
                                         }

                                     }

                                     return false;
                                 }
                             });


                        }

                    }
                });



                home_scroll.setOnTouchListener(new ScrollSwipeDetector(newui_logout_main.this) {
                    static final int MIN_DISTANCE = 100;
                    private float downX, downY, upX, upY;
                    @Override
                    public void onRightToLeftSwipe() {
                        super.onRightToLeftSwipe();

                        // Swipe to the right
                        direction = 2; //right
                        MHMKRecyclerViewLiveEvent(direction,BookLiveEvent.size());

                        Log.d("Swipe", "Right");
                    }

                    @Override
                    public void onLeftToRightSwipe() {
                        super.onLeftToRightSwipe();
                        direction = 1; //left
                        MHMKRecyclerViewLiveEvent(direction,BookLiveEvent.size());

                        Log.d("Swipe", "Left");
                    }

                    public void onTopToBottomSwipe() {
                        Log.d("Swipe", "onTopToBottomSwipe!");

                    }

                    public void onBottomToTopSwipe() {
                        Log.d("Swipe", "onBottomToTopSwipe!");

                    }

                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                downX = event.getX();
                                downY = event.getY();
                                //   return true;
                            }
                            case MotionEvent.ACTION_UP: {
                                upX = event.getX();
                                upY = event.getY();

                                float deltaX = downX - upX;
                                float deltaY = downY - upY;

                                // swipe horizontal?
                                if (Math.abs(deltaX) > MIN_DISTANCE) {
                                    // left or right
                                    if (deltaX < 0) {
                                        this.onLeftToRightSwipe();
                                       // return true;
                                    }
                                    if (deltaX > 0) {
                                        this.onRightToLeftSwipe();
                                        //return true;
                                    }
                                } else {  Log.d("Swipe", "Swipe was only " + String.valueOf(Math.abs(deltaX)) + " long, need at least " + String.valueOf(MIN_DISTANCE)); }

                                    // swipe vertical?
                                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                                        // top or down
                                        if (deltaY < 0) {
                                            this.onTopToBottomSwipe();
                                           // return true;
                                        }
                                        if (deltaY > 0) {
                                            this.onBottomToTopSwipe();
                                           // return true;
                                        }
                                    } else { Log.d( "Swipe","Swipe was only " + String.valueOf(Math.abs(deltaX)) + " long, need at least " + String.valueOf(MIN_DISTANCE)); }

                                           //  return true;



                            }

                        }
                        return false;
                    }






            });

//                backgroundImage.setOnTouchListener(new OnSwipeTouchListener(newui_logout_main.this) {
//                    @Override
//                    public void onSwipeRight() {
//                        super.onSwipeRight();
//
//                        // Swipe to the right
//                        direction = 2; //right
//                        MHMKRecyclerViewLiveEvent(direction,BookLiveEvent.size());
//                        Log.d("Swipe", "Right");
//                    }
//
//                    @Override
//                    public void onSwipeLeft() {
//                        super.onSwipeLeft();
//                        // Swipe to the left
//                        direction = 1; //left
//                        MHMKRecyclerViewLiveEvent(direction,BookLiveEvent.size());
//                        Log.d("Swipe", "Left");
//                    }
//
//                    @Override
//                    public void onClick() {
//                        super.onClick();
//
//
//                        // User tapped once (This is what you want)
//                    }
//
//                    @Override
//                    public void onDoubleClick() {
//                        super.onDoubleClick();
//
//                        // User tapped twice
//                    }
//
//
//                });




               handler.postDelayed(AutoSlideMHMKRecyclerViewLiveEvent,100);




                new loadJsonLiveTV().execute();
                editor.putString("last_watch", "false");
                editor.putString("most_popular", "false");
                editor.putString("trending", "false");
                editor.apply();
            }

        }

    }


    private Runnable AutoSlideMHMKRecyclerViewLiveEvent = new Runnable(){

        @Override
        public void run() {



            Integer size=BookLiveEvent.size();

            size=size-1;

            if(liveEventPos < size){
                liveEventPos=liveEventPos+1;

            } else {
                liveEventPos=0;
            }




            ll_image_indicator.removeAllViews();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                Glide.with(newui_logout_main.this)
                        .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                        .into(backgroundImage);
            } else {

                Glide.with(newui_logout_main.this)
                        .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                        .apply(RequestOptions.centerCropTransform())
                        .into(backgroundImage);
            }

            ObjectAnimator.ofFloat(backgroundImage, View.ALPHA, 0.2f, 1.0f).setDuration(1000).start();

            liveTitleEvent.setText(BookLiveEvent.get(liveEventPos).getTitle());
            live_date.setText(BookLiveEvent.get(liveEventPos).getDate());
            live_time.setText(BookLiveEvent.get(liveEventPos).getTime());

            for (int i = 0; i < BookLiveEvent.size(); i++) {


                ImageView imageView = new ImageView(newui_logout_main.this);
                if (i == liveEventPos) {
                    imageView.setImageResource(R.drawable.icons8_circle_select);
                } else {
                    imageView.setImageResource(R.drawable.icons8_circle_unselect);
                }

                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                ll_image_indicator.addView(imageView);
            }

            handler.postDelayed(this,10000);
        }


    };




    private void MHMKRecyclerViewLiveEvent(Integer direction,Integer size){

        Boolean change=false;

        handler.removeCallbacks(AutoSlideMHMKRecyclerViewLiveEvent);

        size=size-1;

        if(direction == 1){

            if(liveEventPos == 0){
                liveEventPos=0;
            } else {
                liveEventPos=liveEventPos-1;
                change=true;
            }

        }

        if(direction == 2){

            if(liveEventPos < size){
                liveEventPos=liveEventPos+1;
                change=true;
            } else {
                liveEventPos=size;
            }

        }

        if(change) {
            ll_image_indicator.removeAllViews();
            //setFirstImageonLoad
//            Glide.with(newui_logout_main.this)
//                    .asBitmap()
//                    .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
//                    .apply(RequestOptions.centerCropTransform())
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            backgroundImage.setAlpha(0);
//                            backgroundImage.animate().alpha(1).setDuration(500);
//                            backgroundImage.setBackground(new BitmapDrawable(resource));
//                        }
//                    });



            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                Glide.with(newui_logout_main.this)
                        .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                        .into(backgroundImage);
            } else {

                Glide.with(newui_logout_main.this)
                        .load(BookLiveEvent.get(liveEventPos).getBackgroundImage())
                        .apply(RequestOptions.centerCropTransform())
                        .into(backgroundImage);
            }

            ObjectAnimator.ofFloat(backgroundImage, View.ALPHA, 0.2f, 1.0f).setDuration(1000).start();



            liveTitleEvent.setText(BookLiveEvent.get(liveEventPos).getTitle());
            live_date.setText(BookLiveEvent.get(liveEventPos).getDate());
            live_time.setText(BookLiveEvent.get(liveEventPos).getTime());

            for (int i = 0; i < BookLiveEvent.size(); i++) {


                ImageView imageView = new ImageView(newui_logout_main.this);
                if (i == liveEventPos) {
                    imageView.setImageResource(R.drawable.icons8_circle_select);
                } else {
                    imageView.setImageResource(R.drawable.icons8_circle_unselect);
                }

                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


                ll_image_indicator.addView(imageView);
            }
        }

        handler.postDelayed(AutoSlideMHMKRecyclerViewLiveEvent,10000);

    }



    @SuppressLint("ClickableViewAccessibility")
    private void setOnGestureListeners() {

    }

    private class loadJsonLiveTV extends AsyncTask<String,String,String>
    {

       String httpconnectionreturn="run",JsonCode;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingPage=false;

            myrvTC.setVisibility(View.GONE);
            myrvMP.setVisibility(View.GONE);
            myrvrb.setVisibility(View.GONE);
            JsonLiveTV="none";
        }
        @Override
        protected String doInBackground(String... params) {

            JsonCode=jsonDownloaded.getString("jsondownloded_json_code","none");

           // Log.d("JsonLiveTV",String.valueOf(JsonCode));
           // Log.d("JsonLiveTV",String.valueOf(tk));
                try {
                    if (okhttp) {
                        JsonLiveTV = wget.getURL(server + b.rvd452ax3() + ".php?tk=" + tk + "&jsoncode=" + JsonCode);
//                        int maxLogSize = 1000;
//                        for(int i = 0; i <= JsonLiveTV.length() / maxLogSize; i++) {
//                            int start = i * maxLogSize;
//                            int end = (i+1) * maxLogSize;
//                            end = end > JsonLiveTV.length() ? JsonLiveTV.length() : end;
//                            Log.d("JsonLiveTVJSONIn", JsonLiveTV.substring(start, end));
//                        }
                       // Log.d("JsonLiveTVJSONIn",String.valueOf(Json));
                    } else {
                        Json = old_wget.getURL(server + b.rvd452ax3() + ".php?tk=" + tk + "&jsoncode=" + JsonCode);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

       //    Log.d("JsonLiveTVJSON",String.valueOf(JsonLiveTV));
           if (JsonLiveTV.equalsIgnoreCase("none")) {

                return httpconnectionreturn="stop";

            }

            return "";

        }

        @Override
        protected void onPostExecute(String result) {

          if(httpconnectionreturn.equalsIgnoreCase("stop")){

                threepulse1.setVisibility(View.GONE);
                threepulse2.setVisibility(View.GONE);
                threepulse3.setVisibility(View.GONE);

                Toast.makeText(newui_logout_main.this,"Something went wrong. Please check your internet connection",Toast.LENGTH_SHORT).show();

              return;
            }
            else {
            //  Log.d("jsondownloded_json",String.valueOf(JsonLiveTV));
              if(JsonLiveTV.equalsIgnoreCase("cache")) {
                  JsonLiveTV = jsonDownloaded.getString("jsondownloded_json", "none");

              }
         //     Log.d("jsondownloded_json",String.valueOf(JsonLiveTV));

              String[] neviljson=JsonLiveTV.split(",");
              Json=d.decryptStr(neviljson[0]);
              tk=neviljson[1];
              JsonCode=neviljson[2];

//              int maxLogSize = 1000;
//              for(int i = 0; i <= Json.length() / maxLogSize; i++) {
//                  int start = i * maxLogSize;
//                  int end = (i+1) * maxLogSize;
//                  end = end > Json.length() ? Json.length() : end;
//                  Log.d("JsonLiveTV", Json.substring(start, end));
//              }

            //  Log.d("JsonLiveTV",String.valueOf(Json));
              jsonDownloadedEdit.putString("jsondownloded_json", JsonLiveTV);
              jsonDownloadedEdit.putString("jsondownloded_json_code", JsonCode);
              jsonDownloadedEdit.apply();

              new newui_logout_main.loadrecentchannel().execute();
              editor.putString("last_watch", "false");
              editor.putString("most_popular", "false");
              editor.putString("trending", "false");
              editor.apply();
          }

          }

    }



    private class loadrecentchannel extends AsyncTask<String,String,String>
    {

        String VODId, VODname, VODlogo, VODurl, VODcategory, VODsypnopsis, VODPremium;
        Integer EPG;
        String httpconnectionreturn="run";



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected String doInBackground(String... params) {



            if (downloadJsonAgain) {
                try {

                    if (okhttp) {
                        JsonRecentChannels = wget.getURL(server + "apps/home/getlastrecentchannel2.php?user=" + tk);
                    } else {
                        JsonRecentChannels = old_wget.getURL(server + "apps/home/getlastrecentchannel2.php?user=" + tk);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
          //  Log.d("JsonRecent","L =" + JsonRecentChannels);

            if (JsonRecentChannels.equalsIgnoreCase("none") ) {

                return httpconnectionreturn="stop";

            }
              else if(JsonRecentChannels.equalsIgnoreCase("first")) {



            }

              else {
                if(!downloadJsonAgain){
                    JsonRecentChannels=jsonDownloaded.getString("jsondownloded_recentchannel_json","none");
                }else {
                    jsonDownloadedEdit.putString("jsondownloded_recentchannel_json", JsonRecentChannels);
                    jsonDownloadedEdit.apply();
                }


                try {
                    JSONObject objectPremium = new JSONObject(String.valueOf(JsonRecentChannels));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");

                    //   SETVODJsonCategory = new LinkedHashSet<String>();
                    VODJsonId = new ArrayList<String>();
                    VODJsonName = new ArrayList<String>();
                    VODJsonLogoPath = new ArrayList<String>();
                    VODJsonUrl = new ArrayList<String>();
                    VODJsonCategory = new ArrayList<String>();
                    VODJsonsypnopsis = new ArrayList<String>();
                    VODJsonpremium = new ArrayList<String>();
                    VODJsonEPG = new ArrayList<Integer>();
                    VODRecentChannel = new ArrayList<>();


                    //Toast.makeText(getApplicationContext(),VODname,Toast.LENGTH_LONG).show();
                    for (int i = 0; i < VodData.length(); i++) {
                        VODId = VodData.getJSONObject(i).getString("id");
                        VODname = VodData.getJSONObject(i).getString("name");
                        VODlogo = VodData.getJSONObject(i).getString("logoPath");
                        VODurl = VodData.getJSONObject(i).getString("playUrl");
                        VODcategory = VodData.getJSONObject(i).getString("genre");
                        VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");
                        VODPremium = VodData.getJSONObject(i).getString("premium");
                        EPG = VodData.getJSONObject(i).getInt("epg");

                        VODRecentChannel.add(new HomeTrendBook(VODname, VODcategory, VODurl, VODlogo, VODsypnopsis, VODId, VODPremium, EPG, "none"));
                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }





            return "";

        }

        @Override
        protected void onPostExecute(String result) {


            threepulse1.setVisibility(View.GONE);
            loadingPage=true;
            if(httpconnectionreturn.equalsIgnoreCase("stop")){

                Toast.makeText(newui_logout_main.this,"Something went wrong. Please check your internet connection",Toast.LENGTH_SHORT).show();

                threepulse2.setVisibility(View.GONE);
                threepulse3.setVisibility(View.GONE);
                return;
            }

            else if(JsonRecentChannels.equalsIgnoreCase("first")){
                lastWatching_ll.setVisibility(View.GONE);



                new newui_logout_main.loadTrendingChannels().execute();


            }

            else {




                myrvrb.setVisibility(View.VISIBLE);

                myAdapterHomeLastWatch = new RecyclerViewAdapterHomeLastWatch(newui_logout_main.this, VODRecentChannel, server, tk, Json);
                myrvrb.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                myrvrb.setAdapter(myAdapterHomeLastWatch);

                new newui_logout_main.loadTrendingChannels().execute();
            }
        }

    }


    private class loadTrendingChannels extends AsyncTask<String,String,String>
    {

        String VODId, VODname, VODlogo, VODurl, VODcategory, VODsypnopsis, VODPremium;
        Integer EPG;
        String httpconnectionreturn="run";



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          // JsonTrendingChannel="none";
        }
        @Override
        protected String doInBackground(String... params) {

            if(downloadJsonAgain) {

                try {
                    if (okhttp) {
                        JsonTrendingChannel = wget.getURL(server + "apps/home/gettrendingchannel.php?user=" + tk);
                    } else {
                        JsonTrendingChannel = old_wget.getURL(server + "apps/home/gettrendingchannel.php?user=" + tk);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (JsonTrendingChannel.equalsIgnoreCase("none")) {

                return httpconnectionreturn="stop";

            } else {
                if(!downloadJsonAgain){
                    JsonTrendingChannel=jsonDownloaded.getString("jsondownloded_trendingchannel_json","none");
                } else {
                    jsonDownloadedEdit.putString("jsondownloded_trendingchannel_json", JsonTrendingChannel);
                    jsonDownloadedEdit.apply();
                }
                try {
                    JSONObject objectPremium = new JSONObject(String.valueOf(JsonTrendingChannel));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");
                    String view;
                    VODTrendingChannel = new ArrayList<>();
                    for (int i = 0; i < VodData.length(); i++) {
                        VODId = VodData.getJSONObject(i).getString("id");
                        VODname = VodData.getJSONObject(i).getString("name");
                        VODlogo = VodData.getJSONObject(i).getString("logoPath");
                        VODurl = VodData.getJSONObject(i).getString("playUrl");
                        VODcategory = VodData.getJSONObject(i).getString("genre");
                        VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");
                        VODPremium = VodData.getJSONObject(i).getString("premium");
                        EPG = VodData.getJSONObject(i).getInt("epg");
                        view = VodData.getJSONObject(i).getString("views");

                        VODTrendingChannel.add(new HomeTrendBook(VODname, VODcategory, VODurl, VODlogo, VODsypnopsis, VODId, VODPremium, EPG, view));

                    }

                    /*       AllChannelCategory.add(new AllBook("Trending channels",VODTrendingChannel));*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }




            return "";

        }

        @Override
        protected void onPostExecute(String result) {


            threepulse2.setVisibility(View.GONE);

            if(httpconnectionreturn.equalsIgnoreCase("stop")){

                Toast.makeText(newui_logout_main.this,"Something went wrong. Please check your internet connection",Toast.LENGTH_SHORT).show();

                threepulse3.setVisibility(View.GONE);

                return;
            } else {

                myrvTC.setVisibility(View.VISIBLE);

                myAdapterHomeTrend = new RecyclerViewAdapterHomeTrend(newui_logout_main.this, VODTrendingChannel, server, tk, Json);
                myrvTC.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                myrvTC.setAdapter(myAdapterHomeTrend);

                new newui_logout_main.loadMostPopularChannels().execute();
            }

        }

    }

    private class loadMostPopularChannels extends AsyncTask<String,String,String>
    {

        String VODId, VODname, VODlogo, VODurl, VODcategory, VODsypnopsis, VODPremium;
        Integer EPG;
        String httpconnectionreturn="run";



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  JsonMostPopular="none";
        }
        @Override
        protected String doInBackground(String... params) {

            try {
                if(okhttp) {
                    JsonMostPopular = wget.getURL(server + "apps/home/getmostpopularchannel.php?user=" + tk);
                } else {
                    JsonMostPopular = old_wget.getURL(server + "apps/home/getmostpopularchannel.php?user=" + tk);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (JsonMostPopular.equalsIgnoreCase("none")) {

                Toast.makeText(getApplicationContext(),"Something went wrong. Please check your internet connection",Toast.LENGTH_SHORT).show();
                return httpconnectionreturn="stop";

            } else {
                if(!downloadJsonAgain){
                    JsonMostPopular=jsonDownloaded.getString("jsondownloded_mostpopular_json","none");
                } else {
                    jsonDownloadedEdit.putString("jsondownloded_mostpopular_json", JsonMostPopular);
                    jsonDownloadedEdit.apply();
                }
                try {
                    JSONObject objectPremium = new JSONObject(String.valueOf(JsonMostPopular));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");
                    String view;
                    VODMostPopular = new ArrayList<>();
                    for (int i = 0; i < VodData.length(); i++) {
                        VODId = VodData.getJSONObject(i).getString("id");
                        VODname = VodData.getJSONObject(i).getString("name");
                        VODlogo = VodData.getJSONObject(i).getString("logoPath");
                        VODurl = VodData.getJSONObject(i).getString("playUrl");
                        VODcategory = VodData.getJSONObject(i).getString("genre");
                        VODsypnopsis = VodData.getJSONObject(i).getString("sypnopsis");
                        VODPremium = VodData.getJSONObject(i).getString("premium");
                        EPG = VodData.getJSONObject(i).getInt("epg");
                        view = VodData.getJSONObject(i).getString("views");

                        VODMostPopular.add(new HomeTrendBook(VODname, VODcategory, VODurl, VODlogo, VODsypnopsis, VODId, VODPremium, EPG, view));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            final Integer duration=250,speed=10;

            threepulse3.setVisibility(View.GONE);

            if(httpconnectionreturn.equalsIgnoreCase("stop")){
                Toast.makeText(newui_logout_main.this,"Something went wrong. Please check your internet connection",Toast.LENGTH_SHORT).show();

                return;
            } else {

                myrvMP.setVisibility(View.VISIBLE);


                myAdapterHomeMostPopular = new RecyclerViewAdapterHomeMostPopular(newui_logout_main.this, VODMostPopular, server, tk, Json);
                myrvMP.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                myrvMP.setAdapter(myAdapterHomeMostPopular);

//                sliderLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    @Override
//                    public void onFocusChange(View view, boolean b) {
//                        if (b) {
//
//                            home_scroll.smoothScrollTo(0,-300);
//                            //smoothScrollDuration(home_scroll,sliderLayout,duration,speed);
//                            editor.putInt("ScrollViewFocusPosition",getCenterScrollView(sliderLayout));
//                            editor.apply();
//
//
//                        }
//                    }
//                });


                lastWatching_ll.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {

                        if (b) {





                            myrvrb.setAdapter(myAdapterHomeLastWatch);
                            myrvTC.setAdapter(myAdapterHomeTrend);


                         //   Log.d("Get Top last", "v=" + String.valueOf(lastWatching_ll.getTop()));
                            // ObjectAnimator.ofInt(home_scroll,"scrollY",1).setDuration(500).start();

                            smoothScrollDuration(home_scroll,lastWatching_ll,duration);

                            editor.putString("last_watch", "true");
                            editor.putString("trending", "false");
                            editor.putInt("ScrollViewFocusPosition",getCenterScrollView(lastWatching_ll,duration));

                            editor.apply();




                     /*   for(int i=0;i<myAdapterChannelTrending.getItemCount();i++){
                            myrvrb.getChildAt(10).setFocusable(true);
                        }*/

                        }

                    }
                });

                trending_channel_ll.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {


                            myrvrb.setAdapter(myAdapterHomeLastWatch);
                            myrvTC.setAdapter(myAdapterHomeTrend);
                            myrvMP.setAdapter(myAdapterHomeMostPopular);


                        //    Log.d("Get Top trend", "v=" + String.valueOf(trending_channel_ll.getTop()));
                            //  ObjectAnimator.ofInt(home_scroll,"scrollY",getCenterScrollView(trending_channel_ll)).setDuration(500).start();
                            smoothScrollDuration(home_scroll,trending_channel_ll,duration);

                            editor.putString("trending", "true");
                            editor.putString("last_watch", "false");
                            editor.putString("most_popular", "false");
                            editor.putInt("ScrollViewFocusPosition",getCenterScrollView(trending_channel_ll,duration));

                            editor.apply();
                            //home_scroll.smoothScrollTo(0,400);
                        }

                    }
                });

                most_popular_ll.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {



                            myrvTC.setAdapter(myAdapterHomeTrend);
                            myrvMP.setAdapter(myAdapterHomeMostPopular);
                         //   Log.d("Get Top most", "v=" + String.valueOf(most_popular_ll.getTop()));
                            smoothScrollDuration(home_scroll,most_popular_ll,duration);
                            //ObjectAnimator.ofInt(home_scroll,"scrollY",getCenterScrollView(most_popular_ll)).setDuration(500).start();

                            editor.putString("trending", "false");
                            editor.putString("most_popular", "true");
                            editor.putInt("ScrollViewFocusPosition",getCenterScrollView(most_popular_ll,duration));

                            editor.apply();
                        }
                    }
                });


            }



            liveTV_menu.requestFocus();
            /*SnapHelper SH=new LinearSnapHelper();

            SH.attachToRecyclerView(myrvrb);*/
         /*myAdapterHomeYW=new RecyclerViewAdapterHomeTrend(getApplicationContext(),VODRecentChannel,1);
         myrvYW.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
         myrvYW.setAdapter(myAdapterHomeYW);

         getMyAdapterHomeTC=new RecyclerViewAdapterHomeTrend(getApplicationContext(),VODTrendingChannel,2);
         myrvTC.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
         myrvTC.setAdapter(getMyAdapterHomeTC);*/



       /*     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            scrollingview =(LinearLayout) findViewById(R.id.li_lastwatching_channel) ;

            EzLinearLayout firstFrame=new EzLinearLayout(newui_logout_main.this);*/

        /*    TextView Header1 = new TextView(getApplicationContext());
            Header1.setText("You are watching");
            Header1.setTextColor(Color.parseColor("#ffffff"));

            scrollingview.addView(Header1);

            LinearLayout ImageScroll=new LinearLayout(getApplicationContext());
            ImageScroll.setLayoutParams(params);

            scrollingview.addView(ImageScroll);

            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(100,100);

            ImageView Header2 = new ImageView(getApplicationContext());
            Header2.setLayoutParams(params1);
            Glide.with(getApplicationContext()).load("https://astrocontent.s3.amazonaws.com/Images/ChannelLogo/Pos/132_300.png").into(Header2);


            ImageScroll.addView(Header2);*/
       /*       myAdapterHomeVertical = new RecyclerViewVerticalAdapterHome(newui_logout_main.this, AllChannelCategory);
            //  myrvrb.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
            myrvrb.setOrientation(DSVOrientation.VERTICAL);
              myrvrb.setAdapter(myAdapterHomeVertical);
            myrvrb.scrollToPosition(0);
            myrvrb.setItemTransformer(new ScaleTransformer.Builder()
                    .setMaxScale(0.9f)
                    .setMinScale(0.8f)
                    .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                    .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                    .build());

          myrvrb.setClampTransformProgressAfter(2);*/

         /*   myrvRB.setOrientation(DSVOrientation.VERTICAL);
            myrvRB.setItemTransitionTimeMillis(100);
            myrvRB.setItemTransformer(new ScaleTransformer.Builder()
                    .setMinScale(0.8f)
                    .build());
            myrvRB.setNestedScrollingEnabled(true);*/
            //    new GravitySnapHelper(Gravity.TOP).attachToRecyclerView(myrvRB);

         /*   myAdapterHomeTrend=new RecyclerViewAdapterHomeTrend(newui_logout_main.this,VODTrendingChannel);
            myrvTrend.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
            myrvTrend.setAdapter(myAdapterHomeTrend);
*/

        }

    }

    public void smoothScrollDuration(final ScrollView sv,final View v,Integer gap){

        sv.smoothScrollTo(0, getCenterScrollView( v,gap));

    }

    public Integer getCenterScrollView(View view,Integer gap){

        Integer yvalue=0;

         yvalue=view.getTop() + (int) (gap * Resources.getSystem().getDisplayMetrics().density);
        //Integer Bot=view.getBottom();
      //  yvalue = Top - ((Bot-Top)/2);

        return yvalue;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {


      /*  if(lastwatching_ll_focus) {

            if (keyCode == 22)  //right
            {
                if (scrollPosLastWatching <= myAdapterHomeTrend.getItemCount()) {
                    scrollPosLastWatching = scrollPosLastWatching + 1;
                }
                myrvrb.smoothScrollToPosition(scrollPosLastWatching);
                View v=myrvrb.getChildAt(scrollPosLastWatching);
                v.setFocusable(true);
            }


            Log.d("OnKey", "Key " + String.valueOf(scrollPosLastWatching));

        }*/


       /* if (Integer.parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_DPAD_UP
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            if(scrollPos==0) {

            }else {
                scrollPos = scrollPos - 1;
            }

            if(scrollPos == -1){
                scrollPos=0;
            }

            Log.d("CurrentItem","Curr "+ myrvrb.getCurrentItem());


           return true;
        }

        if (Integer.parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_DPAD_DOWN
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            if(scrollPos==3) {

            }else {
                scrollPos=scrollPos+1;
            }

            if(scrollPos == -1){
                scrollPos=0;
            }

            Log.d("CurrentItem","Curr "+ myrvrb.getCurrentItem());


            return true;
        }*/




        //    dlog.log_d(debug,"keypress",String.valueOf(keyCode) + " " + String.valueOf(event));

        return super.onKeyDown(keyCode, event);
    }



}
