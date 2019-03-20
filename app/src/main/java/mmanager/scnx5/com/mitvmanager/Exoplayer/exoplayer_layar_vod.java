package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
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
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


import mmanager.scnx5.com.abcxtengtyou;
import mmanager.scnx5.com.abcyxoorp;
import mmanager.scnx5.com.abdyxoorp;
import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.getscreenratio;

import static android.view.View.VISIBLE;

public class exoplayer_layar_vod extends AppCompatActivity implements Player.EventListener{

    private DefaultBandwidthMeter bandwidthMeter;
    private TrackSelection.Factory videoTrackSelectionFactory;
    private TrackSelector trackSelector,trackSelector2;
    private DefaultAllocator allocator;
    private String Url,MovieName;
    private ExoPlayer player,playerPreview;
    private PlayerView simpleExoPlayerView;
    private Uri videoUri;
    private DataSource.Factory dataSourceFactory;
    private MediaSource videoSource;
    private Context context;
    private static String EEE=".php";
    private boolean controlshow=false;
    private PlayerView simpleExoPlayerViewPreview;
    private ImageView previewImg;
    private MediaMetadataRetriever retriever;
    private Integer totalDuration;
    private ProgressDialog pdLoading;
    private boolean firststart=true,fisrtstartMovie=true;
    private SeekBar sb;
    private long totalMovieDuration;
    private ImageView SeekPreviewImage;
    private Handler handler = new Handler();
    private Boolean setSeekBarFirstTimeAppear=true;
    private TextView current,maxduration;
    private String tk;
    private long seekPos;
    private VideoView simpleVideoView;
    public static final int BUFFER_SEGMENT_SIZE = 16 * 1024;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer_layar_vod);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            Url = extras.getString("Url");
            MovieName = extras.getString("Channel");
            tk=extras.getString("tk");

            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }

        context=this;

    }



    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseall();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseall();
    }

    private void releaseall(){
        if (player!=null) {
            player.release();
            player = null;
            videoSource=null;
            trackSelector=null;
            setupProxy(false);
            if(controlshow)
            handler.removeCallbacks(updateSeekBarTime);


            //if (expiry.equalsIgnoreCase("expired")){
              //  finish();
           // }


        }
    }

    private void initializePlayer(){

            // Create a default TrackSelector
        allocator = new DefaultAllocator(true, BUFFER_SEGMENT_SIZE);

        bandwidthMeter = new DefaultBandwidthMeter();


        videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                new exoplayer_layar_vod.StartChannel().execute();

            }

        }.start();
      //  new exoplayer_layar_vod.StartChannel().execute();
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
        if(playWhenReady && playbackState == Player.STATE_READY){
            totalMovieDuration=player.getDuration();
            if (pdLoading != null && pdLoading.isShowing()) {


                  pdLoading.dismiss();

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
    public void onPlayerError(ExoPlaybackException error) {

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




    private class StartChannel extends AsyncTask<String,String,String>
    {

        String json="",customLoad;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            if (pdLoading == null) {
                pdLoading = new ProgressDialog(exoplayer_layar_vod.this);
                pdLoading.setMessage("\tLoading Movie...");
                pdLoading.setCancelable(true);

                if (firststart ) {
                  //  pdLoading.show();
                    firststart=false;
                }



                new CountDownTimer(20000, 20000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {

                        //       Toast.makeText(exoplayer_layar.this,"10 seconds check",Toast.LENGTH_SHORT).show();

                        if(pdLoading.isShowing() || pdLoading != null){
                            pdLoading.dismiss();
                        }

                    }

                }.start();

            }



            }



        @Override
        protected String doInBackground(String... params) {




/*           if (Sypnopsis.equalsIgnoreCase("M4K")){

                String user="";

                if(checkFileExist("userinfo.txt")) {


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

                    user=userlogin[0];

                }



                String myiptv4kurl ="https://layar3.com/apps/livetv/getlivetvtoken.php?u=" + user;
                Log.d("getliveTokenUrl",myiptv4kurl);
                String UserToken = wget.getURL(myiptv4kurl);
                Log.d("getliveTokenUrl",UserToken);
                String getChannelisting =wget.getURL("https://layar3.com/apps/livetv/getchannel.php");
                Log.d("getChannelisting", getChannelisting);

                String playlisttokenurl =getChannelisting + UserToken;
                // String playlisttokenurl ="http://myiptv4k.com/api/v1/lives?token=" + UserToken;
                Log.d("playlisttokenurl", playlisttokenurl);

                String playlisttoken = wget.getURL(playlisttokenurl);
                Log.d("PlayUrl",Url+ "\n" + playlisttoken);
                JSONObject objectPremium = null;
                try {
                    objectPremium = new JSONObject(String.valueOf(playlisttoken));
                    JSONArray VodData = (JSONArray) objectPremium.getJSONArray("data");
                    for(int i=0;i<VodData.length();i++) {
                        JSONObject VODLive = VodData.getJSONObject(i).getJSONObject("live");
                        String playUrl = VODLive.getString("url");
                        String containsURL = Url.substring(8);
                        if (playUrl.contains(containsURL)) {

                            String playUrlToken = playUrl.substring(playUrl.indexOf("?"));
                            json=playUrlToken;
                            // Log.d("ContainsURL", playUrlToken);
                            //  Log.d("playUrl",playUrl);
                            break;
                        }
                    }

                    // Toast.makeText(Book_Activity.this,playUrl,Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            } else if (Sypnopsis.equalsIgnoreCase("REDBOX")) {
                json = wget.getURL("http://163.172.181.152:8030/rbtv/token21.php");
            }
            else {
                json="";

            }

            Log.d("Exoplayer",tk);


            Log.d("Exoplayer",expiry);

            if (expiry.equalsIgnoreCase("expired")){
                finish();
            }

*/
            getURL wget=new getURL();

            //   startActivity(Intent.createChooser(intent, "Complete action using"));
            // Produces DataSource instances through which media data is loaded.
//            dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, getPackageName()));

            abcyxoorp a=new abcyxoorp();

            abcxtengtyou x=new abcxtengtyou();

            try {
                dataSourceFactory = new DefaultDataSourceFactory(context, wget.getURL(a.xyoprup() + x.abcxtengtyou() + EEE));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Produces Extractor instances for parsing the media data.




            String layar3="layar3.com";

            if(Url.toLowerCase().contains(layar3.toLowerCase())){

              //  setupProxy(true);
            } else {
                setupProxy(false);
            }

            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            //  Intent i=new Intent(exoplayer_layar.this,exoplayer_layar.class );
            //  i.putExtra("playurl",Url+json);
            //
            //     Intent intent = new Intent(Intent.ACTION_VIEW);
            // Url="https://www.youtube.com/watch?v=kjFBbIwuDYU";





            //  Url="http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0";

            //Initialize the player
            Integer minBuffer=30000;
            Integer maxBuffer=60000;
            Integer bufferPlayBack=2500;
            Integer bufferPlayBackRebuffer=5000;

            DefaultLoadControl loadControl = new DefaultLoadControl(allocator, minBuffer, maxBuffer, bufferPlayBack, bufferPlayBackRebuffer, -1, true);

            player = ExoPlayerFactory.newSimpleInstance(context, trackSelector,loadControl);
            // playerPreview = ExoPlayerFactory.newSimpleInstance(context, trackSelector2);
            //Initialize simpleExoPlayerView
            simpleExoPlayerView = findViewById(R.id.exoplayer);
            // simpleExoPlayerViewPreview=findViewById(R.id.previewPlayerView);
            previewImg=(ImageView)findViewById(R.id.previewImage);

            simpleExoPlayerView.setPlayer(player);
            // simpleExoPlayerViewPreview.setPlayer(playerPreview);

            player.addListener(exoplayer_layar_vod.this);


            Url="http://192.168.0.103/HowToDragon.ts";
            //videoUri = Uri.parse(Url);

            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

          // videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

           // videoSource=new ExtractorMediaSource.Factory(Uri.parse(Url),dataSourceFactory, extractorsFactory, null, null);
            videoSource=new ExtractorMediaSource(Uri.parse(Url),
                    dataSourceFactory, extractorsFactory, null, null);
            player.setPlayWhenReady(true);

            player.prepare(videoSource);



/*
            Url="http://storage.layar3.com:8888/vod/English/Ant-Man.And.The.Wasp.2018.720p.BluRay.x264-%5bYTS.AM%5d.mp4";
            Uri uri = Uri.parse(Url);
             simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView); // initiate a video view
            simpleVideoView.setVideoURI(uri);
            simpleVideoView.start();
            // create an object of media controller
         //   MediaController mediaController = new MediaController(this);
            // set media controller object for a video view
           // simpleVideoView.setMediaController(mediaController);

*/



            TextView movieNameTx=(TextView) findViewById(R.id.moviename);
            movieNameTx.setText(MovieName);

            sb=(SeekBar) findViewById(R.id.seekBar2);

            ImageButton playButton=(ImageButton)findViewById(R.id.exo_play);
            ImageButton pauseButton=(ImageButton)findViewById(R.id.exo_pause);

             current=(TextView) findViewById(R.id.currentposition);
             maxduration=(TextView) findViewById(R.id.movieduration);

            getscreenratio gs=new getscreenratio();
            int width=gs.getScreenWidth();

            int center=width/2;
            float widthhalf=playButton.getLayoutParams().width / 2;
            int centerX= (int)widthhalf;
           // playButton.getLayoutParams().width=center;
            Log.d("setX",String.valueOf(centerX));
//            Log.d("setX",String.valueOf(centerX));;p


            playButton.setX(center-centerX);
           // pauseButton.getLayoutParams().width=center;
            pauseButton.setX(center-centerX);

            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    SetSeekforPlay(i);
                    /*  RequestOptions options = new RequestOptions().frame(SeekPosistion * 1000);
                    Glide.with(context).asBitmap()
                            .load(videoUri)
                           // .apply(options)
                            .into(SeekPreviewImage);
*/

                    Log.d("ProgressChanged",String.valueOf(seekBar));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Log.d("onStartTrackingTouch",String.valueOf(seekBar));
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                 //   Integer seekbarCurrentPos=seekBar.getProgress();
                 //   float SeekBarPosPercent=(float) seekbarCurrentPos/100;
                 //   Long SeekPosistion=(long)(SeekBarPosPercent * player.getDuration());

                    SetSeektoPlay();
                    Log.d("onStopTrackingTouch", String.valueOf(player.getDuration()));
                 //   Log.d("onStopTrackingTouch", String.valueOf(SeekBarPosPercent));
                  //  Log.d("onStopTrackingTouch", String.valueOf(SeekPosistion));
                }
            });



            //  MediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

            // Prepare the player with the source.
           // retriever = new MediaMetadataRetriever();
           // retriever.setDataSource(Url,new HashMap<String, String>());
           // String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

          //  totalDuration=Integer.valueOf(duration);

            simpleExoPlayerView.hideController();
            simpleExoPlayerView.setControllerShowTimeoutMs(0);
            setOnGestureListeners();


            //initialize OSD
           // TextView moviename=(TextView)findViewById(R.id.moviename);
            //moviename.setText(MovieName);

            //controllerView=false;


        }

    }

    public void SetSeekforPlay(Integer i){
       // totalMovieDuration=simpleVideoView.getDuration();
        float SeekBarPosPercent=(float) i/100;

        seekPos=(long)(SeekBarPosPercent * totalMovieDuration );



    }

    public void SetSeektoPlay(){
      //  player.prepare(videoSource,false,false);
        player.setSeekParameters(SeekParameters.CLOSEST_SYNC);
        player.seekTo(seekPos);

        //simpleVideoView.seekTo((int)seekPos);



    }



    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        if(controlshow) {
            controlshow=false;
            simpleExoPlayerView.hideController();
            handler.removeCallbacks(updateSeekBarTime);
            setSeekBarFirstTimeAppear=true;
        }else{
            finish();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {



        if (Integer.parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }

        if (Integer.parseInt(Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_DPAD_CENTER
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            toggleShowController();
            if(controlshow){

                SetSeekforPlay(sb.getProgress());
                SetSeektoPlay();
            }
            return true;
        }






        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnGestureListeners() {
        simpleExoPlayerView.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                // Swipe to the right
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                // Swipe to the left
            }

            @Override
            public void onClick() {
                super.onClick();
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

    public void toggleShowController(){

      //  simpleExoPlayerView.showController();
            if(controlshow){
            //   player.seekTo(800900);
               // player.setPlayWhenReady(false);

            }else if (totalMovieDuration>0) {
                simpleExoPlayerView.showController();

                handler.postDelayed(updateSeekBarTime, 1);

                controlshow = true;
            }



    }


    private Runnable updateSeekBarTime = new Runnable() {

        long hoursf,minutesf,secondsf;
        @Override
        public void run() {
            /* do what you need to do */
            long currentPos=player.getCurrentPosition();
            float curPosPercent=(float)currentPos/totalMovieDuration;
            int curPos=(int)(curPosPercent*100);
            if (setSeekBarFirstTimeAppear) {
                sb.setProgress(curPos);

                 hoursf = TimeUnit.MILLISECONDS.toHours(totalMovieDuration) % 24;
                 minutesf = TimeUnit.MILLISECONDS.toMinutes(totalMovieDuration) % 60;
                 secondsf = TimeUnit.MILLISECONDS.toSeconds(totalMovieDuration) % 60;



                setSeekBarFirstTimeAppear=false;
            }

            long hours = TimeUnit.MILLISECONDS.toHours(currentPos) % 24;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(currentPos) % 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(currentPos) % 60;


            String currentpost= String.format("%2d:%02d:%02d",hours,minutes,seconds);
            String TotalmoviedurationString= String.format("%2d:%02d:%02d",hoursf,minutesf,secondsf);

            current.setText(currentpost);
            maxduration.setText(TotalmoviedurationString);
            /*
            String currentpost= String.format("%2d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(currentPos),
                    TimeUnit.MILLISECONDS.toMinutes(currentPos),
                    TimeUnit.MILLISECONDS.toSeconds(currentPos) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MILLISECONDS.toHours(currentPos)))
            );
            */
            Log.d("Current Access",String.valueOf(currentpost));
            Log.d("TotalMovieDuration",String.valueOf(TotalmoviedurationString));
            Log.d("Execute",String.valueOf(curPosPercent));
            /* and here comes the "trick" */
            handler.postDelayed(this, 1000);
        }
    };

    public void loadPreview(long currentPosition, long max) {
        Long newPosition = currentPosition * 1000;
        Bitmap bitmap = retriever.getFrameAtTime(newPosition);
        previewImg.setImageBitmap(bitmap);

    }


    private void setupProxy(boolean set){

        if (set) {

            abdyxoorp z = new abdyxoorp();

            getURL wget = new getURL();

            abcyxoorp x = new abcyxoorp();

            // Toast.makeText(login_activity.this,x.xyoprup()+ z.xyxoprup() + ".php",Toast.LENGTH_SHORT).show();

            // Log.d("proxy",wget.getURL(x.xyoprup()+ z.xyxoprup() + ".php"));

            String ch = null;
            try {
                ch = wget.getURL(x.xyoprup() + z.xyxoprup() + ".php?tk="+tk);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] separ = ch.split(":");


            System.setProperty("http.proxyHost", separ[0]);
            System.setProperty("http.proxyPort", separ[1]);
        } else {
            System.setProperty("http.proxyHost", "");
            System.setProperty("http.proxyPort", "");

        }
    }

}


