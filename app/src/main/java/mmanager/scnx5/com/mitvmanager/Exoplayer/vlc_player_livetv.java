//package mmanager.scnx5.com.mitvmanager.Exoplayer;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.res.Configuration;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
//import android.text.Html;
//import android.util.Log;
//import android.view.KeyEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.view.WindowManager;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.videolan.libvlc.IVLCVout;
//import org.videolan.libvlc.LibVLC;
//import org.videolan.libvlc.Media;
//import org.videolan.libvlc.MediaPlayer;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.ref.WeakReference;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
//import mmanager.scnx5.com.abcyxoorp;
//import mmanager.scnx5.com.abdyxoorp;
//import mmanager.scnx5.com.mitvmanager.R;
//import mmanager.scnx5.com.mitvmanager.getURL;
//
//
//public class vlc_player_livetv extends AppCompatActivity implements IVLCVout.Callback {
//    public final static String TAG = "MainActivity";
//    private String mFilePath;
//    private SurfaceView mSurface;
//    private SurfaceHolder holder;
//    private FrameLayout OSDisplayFrame;
//    private TextView movieTitle,startDuration,EndDuration,movieCategory,bufferingText,subTitlesView;
//    private ImageButton playButton,pauseButton;
//    private ImageView seekPreviewImage;
//    private JSONArray SubtitlesData;
//    private SeekBar seekBar;
//    private LibVLC libvlc=null;
//    private MediaPlayer mMediaPlayer = null;
//    private int mVideoWidth;
//    private int mVideoHeight;
//    private int subtitlesPosition;
//    private String Url,MovieName,tk,MovieCategory;
//    private Handler handler = new Handler();
//    private long totalMovieDuration,seekPos;
//    private boolean mediaNotPlaying=true,setSeekBarFirstTimeAppear=true,OSDisplay=false,onStartSeekBarDragging=false;
//    private long onProgressSeekbar=0;
//    private String username, passwrd, updateinfo;
//
//
//    Uri uri;
//
//    //implementation 'de.mrmaffen:vlc-android-sdk:2.0.6'
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vlc__vod__rtmp);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        Bundle extras = getIntent().getExtras();
//        if(extras !=null)
//        {
//            Url = extras.getString("Url");
//            MovieName = extras.getString("Title");
//            MovieCategory = extras.getString("category");
//            tk=extras.getString("tk");
//
//            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
//        }
//
//
//        String layar3="layar3.com";
//
//        if(Url.toLowerCase().contains(layar3.toLowerCase())){
//
//         // setupProxy(true);
//        } else {
//        //    setupProxy(false);
//        }
//
//
//
//
//        mFilePath = Url;
//
//        Log.d(TAG, "Playing: " + mFilePath);
//        mSurface = (SurfaceView) findViewById(R.id.surface);
//        holder = mSurface.getHolder();
//
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        setSize(mVideoWidth, mVideoHeight);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//      //  setOnGestureListeners();
//        //  extractMediaData();
//        createPlayer(mFilePath);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        releasePlayer();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        releasePlayer();
//    }
//
//
//    /**
//     * Used to set size for SurfaceView
//     *
//     * @param width
//     * @param height
//     */
//    private void setSize(int width, int height) {
//        mVideoWidth = width;
//        mVideoHeight = height;
//        if (mVideoWidth * mVideoHeight <= 1)
//            return;
//
//        if (holder == null || mSurface == null)
//            return;
//
//        int w = getWindow().getDecorView().getWidth();
//        int h = getWindow().getDecorView().getHeight();
//        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
//        if (w > h && isPortrait || w < h && !isPortrait) {
//            int i = w;
//            w = h;
//            h = i;
//        }
//
//        float videoAR = (float) mVideoWidth / (float) mVideoHeight;
//        float screenAR = (float) w / (float) h;
//
//        if (screenAR < videoAR)
//            h = (int) (w / videoAR);
//        else
//            w = (int) (h * videoAR);
//
//        holder.setFixedSize(mVideoWidth, mVideoHeight);
//        LayoutParams lp = mSurface.getLayoutParams();
//        lp.width = w;
//        lp.height = h;
//        mSurface.setLayoutParams(lp);
//        mSurface.invalidate();
//    }
//
//
//
//
//    /**
//     * Creates MediaPlayer and plays video
//     *
//     * @param media
//     */
//    private void createPlayer(String media) {
//        releasePlayer();
//        try {
//            if (media.length() > 0) {
//                //   Toast toast = Toast.makeText(this, media, Toast.LENGTH_LONG);
//                //toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
//                //          0);
//                //  toast.show();
//            }
//
//            // Create LibVLC
//            // TODO: make this more robust, and sync with audio demo
//            final ArrayList<String> options = new ArrayList<>();
//            //options.add("--subsdec-encoding <encoding>");
//            options.add("--aout=opensles");
//            options.add("--audio-time-stretch"); // time stretching
//            options.add("-vvv"); // verbosity
//            libvlc = new LibVLC(this,options);
//            holder.setKeepScreenOn(true);
//
//            // Creating media player
//            mMediaPlayer = new MediaPlayer(libvlc);
//            mMediaPlayer.setEventListener(mPlayerListener);
//
//            // Seting up video output
//            final IVLCVout vout = mMediaPlayer.getVLCVout();
//            vout.setVideoView(mSurface);
//            vout.addCallback(this);
//            vout.attachViews();
//
//            Media m = new Media(libvlc, Uri.parse(media));
//            mMediaPlayer.setMedia(m);
//            mMediaPlayer.play();
//
//          //  new VLC_VOD_RTMP.generateSubtitles().execute();
//
//            createOSDisplay();
//            trimCache();
//
//            if (checkFileExist("token_number_play.txt")) {
//
//                Boolean fileExisted = true;
//                File file = new File(getApplicationContext().getFilesDir(), "token_number_play.txt");
//                //Read text from file
//                StringBuilder text = new StringBuilder();
//
//                try {
//                    BufferedReader br = new BufferedReader(new FileReader(file));
//                    String line;
//
//                    while ((line = br.readLine()) != null) {
//                        text.append(line);
//                        text.append('\n');
//                    }
//                    br.close();
//                } catch (IOException e) {
//                    //You'll need to add proper error handling here
//                }
//
//                String tokenAndDate = text.toString();
//                String[] tokendate = tokenAndDate.split(",");
//
//                //  lastplayed=convertStringToDate(tokendate[1]);
//                //Log.d("Lasplay",String.valueOf(tokendate[1]));
//
//                Date currentTime = Calendar.getInstance().getTime();
//
//                String s = tokendate[1].trim();
//                long lastimeplayed=0;
//                try {
//                    lastimeplayed = Long.parseLong(s);
//                    long difference=Math.abs(currentTime.getTime()-lastimeplayed);
//
//                    long differenceDates = difference / (1000);
//                    //     Log.d("TimeCurrent",String.valueOf(currentTime.getTime()));
//                    //     Log.d("Lasplay",String.valueOf(lastimeplayed));
//
//
//                    if (differenceDates >60){
//                        updateinfo = username + ",token";
//                    } else {
//                        updateinfo = username + "," + tokendate[0];
//
//                    }
//
//                    //     Log.d("link",updateinfo);
//
//                    //    Log.d("Differ",String.valueOf(difference));
//                    //     Log.d("Difference",String.valueOf(differenceDates));
//
//                } catch (NumberFormatException e) {
//                    //     Log.d("NumberFormatException: ", e.getMessage());
//                }
//
//
//
//            } else {
//
//                updateinfo = username + ",token";
//            }
//
//
//        //    handler.postDelayed(updatePlayTime, 1);
//
//        } catch (Exception e) {
//            // Toast.makeText(this, "Error in creating player!", Toast
//            //      .LENGTH_LONG).show();
//            finish();
//        }
//    }
//
//    private void releasePlayer() {
//        if (libvlc == null)
//            return;
//        mMediaPlayer.stop();
//        final IVLCVout vout = mMediaPlayer.getVLCVout();
//        vout.removeCallback(this);
//        vout.detachViews();
//        holder = null;
//        libvlc.release();
//        libvlc = null;
//      //  setupProxy(false);
//        mVideoWidth = 0;
//        mVideoHeight = 0;
//        if(OSDisplay){
//            handler.removeCallbacks(updateSeekBarTime);
//            handler.removeCallbacks(updateSubtitles);
//            trimCache();
//        }
//    }
//
//    private void createOSDisplay(){
//
//        OSDisplayFrame = (FrameLayout)findViewById(R.id.osdscreen);
//        OSDisplayFrame.setVisibility(View.GONE);
//
//        movieTitle = (TextView) findViewById(R.id.vlc_movie_name);
//        movieCategory=(TextView)findViewById(R.id.vlc_movie_category);
//        startDuration = (TextView) findViewById(R.id.vlc_currentposition);
//        EndDuration = (TextView) findViewById(R.id.vlc_movieduration);
//        bufferingText = (TextView) findViewById(R.id.vlc_buffering_text);
//        playButton = (ImageButton) findViewById(R.id.vlc_play);
//        pauseButton = (ImageButton) findViewById(R.id.vlc_pause);
//        seekPreviewImage=(ImageView)findViewById(R.id.previewImage_vlc);
//        subTitlesView=(TextView)findViewById(R.id.vlc_subtitles);
//
//        playButton.setVisibility(View.GONE);
//        pauseButton.setVisibility(View.GONE);
//        bufferingText.setVisibility(View.GONE);
//        subTitlesView.setVisibility(View.GONE);
//
//        movieTitle.setText(MovieName);
//        movieCategory.setText(MovieCategory);
//
//        seekBar=(SeekBar)findViewById(R.id.vlc_seekBar);
//
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playButton.setVisibility(View.GONE);
//                pauseButton.setVisibility(View.VISIBLE);
//                mMediaPlayer.play();
//            }
//        });
//
//        pauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mMediaPlayer.pause();
//                playButton.setVisibility(View.VISIBLE);
//                pauseButton.setVisibility(View.GONE);
//
//            }
//        });
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                SetSeekforPlay(i);
//                onStartSeekBarDragging=true;
//                Log.d("ProgressChanged",String.valueOf(i));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                onStartSeekBarDragging=true;
//                //     handler.postDelayed(checkForImageSeekPreview, 1);
//                handler.removeCallbacks(updateSeekBarTime);
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                SetSeektoPlay();
//                handler.postDelayed(updateSeekBarTime, 1);
//                //  handler.removeCallbacks(checkForImageSeekPreview);
//                //  onStartSeekBarDragging=false;
//                //  onProgressSeekbar=0;
//                onStartSeekBarDragging=false;
//            }
//        });
//
//    }
//
//
//
//    private class generateSubtitles extends AsyncTask<String, String, String> {
//        String subtitleJson="";
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            getURL wget = new getURL();
//
//            try {
//                URL newURL=new URL(Url);
//                //     Log.d("getHost",newURL.getHost());
//                //    Log.d("getPath",newURL.getPath());
//                //     Log.d("getProtocl",newURL.getProtocol());
//
//                //    Log.d("getFilename",newURL.getFile().substring(0, newURL.getFile().lastIndexOf(".")));
//
//             //   subtitleJson=wget.getURL("http://stream.layar3.com/parseSRT.php?url=" + newURL.getProtocol() + "://" +newURL.getHost()+ newURL.getFile().substring(0, newURL.getFile().lastIndexOf(".")) + ".srt");
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
//
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            //this method will be running on UI thread
//
//            if(subtitleJson.equalsIgnoreCase("none")) {
//            }
//            else{
//                JSONObject objectPremium = null;
//
//                try {
//                    objectPremium = new JSONObject(String.valueOf(subtitleJson));
//                    SubtitlesData = (JSONArray) objectPremium.getJSONArray("data");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                subtitlesPosition=0;
//
//                handler.postDelayed(updateSubtitles, 1);
//
//            }
//        }
//    }
//
//
//
//    private Runnable updateSubtitles = new Runnable() {
//
//        @Override
//        public void run() {
//            /* do what you need to do */
//            String content;
//            long currentPlaying,startMS,stopMS;
//
//            currentPlaying= mMediaPlayer.getTime();
//            try {
//
//                for(int i =subtitlesPosition;i<SubtitlesData.length();i++) {
//                    content = SubtitlesData.getJSONObject(i).getString("text");
//                    startMS = SubtitlesData.getJSONObject(i).getLong("startMS");
//                    stopMS = SubtitlesData.getJSONObject(i).getLong("stopMS");
//
//                    if (startMS < currentPlaying && currentPlaying < stopMS) {
//                        subTitlesView.setText(Html.fromHtml(content));
//                        subTitlesView.setVisibility(View.VISIBLE);
//                        subtitlesPosition = i;
//                        break;
//                    } else {
//                        subTitlesView.setVisibility(View.GONE);
//                    }
//
//
//                    //  Log.d("startMS", String.valueOf(startMS));
//                    //  Log.d("stopMS", String.valueOf(stopMS));
//                    // Log.d("Content", content);
//
//                    //   Log.d("currentPlayingPositing", String.valueOf(mMediaPlayer.getTime()));
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            /* and here comes the "trick" */
//            handler.postDelayed(this, 100);
//        }
//    };
//
//    private Runnable updateSeekBarTime = new Runnable() {
//
//        long hoursf,minutesf,secondsf;
//        String currentpost,TotalmoviedurationString;
//        @Override
//        public void run() {
//            /* do what you need to do */
//            long currentPos=mMediaPlayer.getTime();
//            float curPosPercent=(float)currentPos/totalMovieDuration;
//            int curPos=(int)(curPosPercent*100);
//
//            if(onStartSeekBarDragging == false) {
//                seekBar.setProgress(curPos);
//            }
//            if (setSeekBarFirstTimeAppear) {
//
//
//                hoursf = TimeUnit.MILLISECONDS.toHours(totalMovieDuration) % 24;
//                minutesf = TimeUnit.MILLISECONDS.toMinutes(totalMovieDuration) % 60;
//                secondsf = TimeUnit.MILLISECONDS.toSeconds(totalMovieDuration) % 60;
//
//                setSeekBarFirstTimeAppear=false;
//            }
//
//            long hours = TimeUnit.MILLISECONDS.toHours(currentPos) % 24;
//            long minutes = TimeUnit.MILLISECONDS.toMinutes(currentPos) % 60;
//            long seconds = TimeUnit.MILLISECONDS.toSeconds(currentPos) % 60;
//
//            currentpost="";
//
//            if (hours == 0){
//                currentpost = String.format("%d:%02d", minutes, seconds);
//
//            } else {
//                currentpost = String.format("%d:%02d:%02d", hours, minutes, seconds);
//            }
//
//            TotalmoviedurationString= String.format("%2d:%02d:%02d",hoursf,minutesf,secondsf);
//
//            startDuration.setText(currentpost);
//            EndDuration.setText(TotalmoviedurationString);
//            /*
//            String currentpost= String.format("%2d:%02d:%02d",
//                    TimeUnit.MILLISECONDS.toHours(currentPos),
//                    TimeUnit.MILLISECONDS.toMinutes(currentPos),
//                    TimeUnit.MILLISECONDS.toSeconds(currentPos) -
//                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(TimeUnit.MILLISECONDS.toHours(currentPos)))
//            );
//            */
//            Log.d("Current Access",String.valueOf(currentpost));
//            Log.d("TotalMovieDuration",String.valueOf(TotalmoviedurationString));
//            Log.d("Execute",String.valueOf(curPosPercent));
//            /* and here comes the "trick" */
//            handler.postDelayed(this, 1000);
//        }
//    };
//
//    private void getMediaInformation(){
//        totalMovieDuration=mMediaPlayer.getLength();
//        Log.d(TAG,"Movie duration:" + String.valueOf(totalMovieDuration));
//    }
//
//    public void SetSeekforPlay(Integer i){
//        float SeekBarPosPercent=(float) i/100;
//        seekPos=(long)(SeekBarPosPercent * totalMovieDuration );
//
//        long hours = TimeUnit.MILLISECONDS.toHours(seekPos) % 24;
//        long minutes = TimeUnit.MILLISECONDS.toMinutes(seekPos) % 60;
//        long seconds = TimeUnit.MILLISECONDS.toSeconds(seekPos) % 60;
//
//        String currentpost="";
//        if (hours == 0){
//            currentpost = String.format("%d:%02d", minutes, seconds);
//
//        } else {
//            currentpost = String.format("%d:%02d:%02d", hours, minutes, seconds);
//        }
//        startDuration.setText(currentpost);
///*
//        if (i < 25){
//            Glide.with(this).load("https://cnet2.cbsistatic.com/img/OMkq__G5Y5PkJL13g7SfnZ7Yq5o=/830x467/2016/07/18/a9734f4f-56f6-4244-aa3b-87eef85ae0bb/amazon-video-logo.jpg").into(seekPreviewImage);
//        } else if (i < 50){
//            Glide.with(this).load("http://www.dailyrindblog.com/wp-content/uploads/2016/04/9izxraeGT.jpg").into(seekPreviewImage);
//        } else if (i < 75){
//            Glide.with(this).load("https://spielverlagerung.com/wp-content/uploads/sites/2/2018/08/Video.png").into(seekPreviewImage);
//        }
//*/
//        // int x=seekBar.getThumb().getBounds().centerX();
//
//        float newPos=(SeekBarPosPercent * 1720)-(float)(seekPreviewImage.getWidth()/2);
//        seekPreviewImage.setX(newPos);
//    }
//
//    private Runnable checkForImageSeekPreview = new Runnable() {
//
//        @Override
//        public void run() {
//
//            if (onStartSeekBarDragging) {
//                if (onProgressSeekbar == seekBar.getProgress()) {
//
//                  //  new VLC_VOD_RTMP.loadBitmapIntoPreview().execute();
//
//                }else {
//                    onProgressSeekbar=seekBar.getProgress();
//                }
//            }
//            handler.postDelayed(this, 1);
//        }
//
//    };
//
//    private class loadBitmapIntoPreview extends AsyncTask<String, String, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//            //this method will be running on UI thread
//
//        }
//    }
//
//    public void SetSeektoPlay() {
//        subtitlesPosition=0;
//        mMediaPlayer.setTime(seekPos);
//    }
//
//    /**
//     * Registering callbacks
//     */
//    private MediaPlayer.EventListener mPlayerListener = new MyPlayerListener(this);
//
//    @Override
//    public void onNewLayout(IVLCVout vout, int width, int height, int visibleWidth, int visibleHeight, int sarNum, int sarDen) {
//        if (width * height == 0)
//            return;
//
//        // store video size
//        mVideoWidth = width;
//        mVideoHeight = height;
//        setSize(mVideoWidth, mVideoHeight);
//    }
//
//    @Override
//    public void onSurfacesCreated(IVLCVout vout) {
//
//    }
//
//    @Override
//    public void onSurfacesDestroyed(IVLCVout vout) {
//
//    }
//
//    @Override
//    public void onHardwareAccelerationError(IVLCVout vlcVout) {
//        Log.e(TAG, "Error with hardware acceleration");
//        this.releasePlayer();
//        Toast.makeText(this, "Error with hardware acceleration", Toast.LENGTH_LONG).show();
//    }
//
//    private void setupProxy(boolean set) throws IOException {
//
//        if (set) {
//
//            abdyxoorp z = new abdyxoorp();
//
//            getURL wget = new getURL();
//
//            abcyxoorp x = new abcyxoorp();
//
//            // Toast.makeText(login_activity.this,x.xyoprup()+ z.xyxoprup() + ".php",Toast.LENGTH_SHORT).show();
//
//            // Log.d("proxy",wget.getURL(x.xyoprup()+ z.xyxoprup() + ".php"));
//
//            String ch = wget.getURL(x.xyoprup() + z.xyxoprup() + ".php?tk=" + tk);
//            String[] separ = ch.split(":");
//
//
//            System.setProperty("http.proxyHost", separ[0]);
//            System.setProperty("http.proxyPort", separ[1]);
//        } else {
//            System.setProperty("http.proxyHost", "");
//            System.setProperty("http.proxyPort", "");
//
//        }
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        Log.d("CDA", "onBackPressed Called");
//        if(OSDisplay) {
//            OSDisplay=false;
//            OSDisplayFrame.setVisibility(View.GONE);
//            handler.removeCallbacks(updateSeekBarTime);
//
//            pauseButton.setVisibility(View.GONE);
//            playButton.setVisibility(View.GONE);
//            setSeekBarFirstTimeAppear=true;
//            onStartSeekBarDragging=false;
//        }else{
//            finish();
//        }
//    }
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//
//        if (Integer.parseInt(Build.VERSION.SDK) > 5
//                && keyCode == KeyEvent.KEYCODE_BACK
//                && event.getRepeatCount() == 0) {
//            Log.d("CDA", "onKeyDown Called");
//            onBackPressed();
//            return true;
//        }
//
//        if (Integer.parseInt(Build.VERSION.SDK) > 5
//                && keyCode == KeyEvent.KEYCODE_DPAD_CENTER
//                && event.getRepeatCount() == 0) {
//            Log.d("CDA", "onKeyDown Called");
//            toggleShowController();
//            if (OSDisplay) {
//                SetSeekforPlay(seekBar.getProgress());
//                SetSeektoPlay();
//                onStartSeekBarDragging=false;
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    private void setOnGestureListeners() {
//        mSurface.setOnTouchListener(new OnSwipeTouchListener(vlc_player_livetv.this) {
//            @Override
//            public void onSwipeRight() {
//                super.onSwipeRight();
//                // Swipe to the right
//            }
//
//            @Override
//            public void onSwipeLeft() {
//                super.onSwipeLeft();
//                // Swipe to the left
//            }
//
//            @Override
//            public void onClick() {
//                super.onClick();
//                toggleShowController();
//
//                // User tapped once (This is what you want)
//            }
//
//            @Override
//            public void onDoubleClick() {
//                super.onDoubleClick();
//
//                // User tapped twice
//            }
//
//
//        });
//    }
//
//    public void SetBufferingDisplay(float buffer){
//        bufferingText.setVisibility(View.VISIBLE);
//        bufferingText.setText("Buffering " + String.valueOf(buffer) + "%");
//        int bufferPercent=(int) buffer;
//        if(OSDisplay){
//            if(bufferPercent <100) {
//                playButton.setVisibility(View.GONE);
//                pauseButton.setVisibility(View.GONE);
//            }else {
//                playButton.setVisibility(View.VISIBLE);
//                pauseButton.setVisibility(View.VISIBLE);
//            }
//        }
//    }
//
//    public void toggleShowController() {
//
//
//        if (OSDisplay) {
//
//        } else if (totalMovieDuration > 0) {
//            OSDisplayFrame.setVisibility(View.VISIBLE);
//            pauseButton.setVisibility(View.VISIBLE);
//            handler.postDelayed(updateSeekBarTime, 1);
//            OSDisplay = true;
//        }
//    }
//
//
//    private static class MyPlayerListener implements MediaPlayer.EventListener {
//        private WeakReference<vlc_player_livetv> mOwner;
//
//        public MyPlayerListener(vlc_player_livetv owner) {
//            mOwner = new WeakReference<vlc_player_livetv>(owner);
//        }
//
//        @Override
//        public void onEvent(MediaPlayer.Event event) {
//            vlc_player_livetv player = mOwner.get();
//
//            switch (event.type) {
//                case MediaPlayer.Event.EndReached:
//                    Log.d(TAG, "MediaPlayerEndReached");
//                    player.releasePlayer();
//                    break;
//                case MediaPlayer.Event.Playing:
//                    player.getMediaInformation();
//                    break;
//                case MediaPlayer.Event.Paused:
//                    break;
//                case MediaPlayer.Event.Stopped:
//                case MediaPlayer.Event.Buffering:
//                    player.SetBufferingDisplay(event.getBuffering());
//                    break;
//                default:
//                    player.bufferingText.setVisibility(View.GONE);
//                    break;
//            }
//        }
//    }
//
//    public boolean checkFileExist(String filepath){
//
//        File file = new File(getApplicationContext().getFilesDir(),filepath);
//
//        if (file.exists()){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    public void trimCache() {
//        try {
//            File dir = getCacheDir();
//            if (dir != null && dir.isDirectory()) {
//                deleteDir(dir);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//    }
//
//    public static boolean deleteDir(File dir)
//    {
//        if (dir != null && dir.isDirectory()) {
//            String[] children = dir.list();
//            for (int i = 0; i < children.length; i++) {
//                boolean success = deleteDir(new File(dir, children[i]));
//                if (!success) {
//                    return false;
//                }
//            }
//        }
//        return dir.delete();
//    }
//
//
//    private void saveTokenToFile(String tokendate){
//        String filename ="token_number_play.txt";
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(tokendate.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}