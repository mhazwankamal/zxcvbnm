package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SyncAdapterType;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player.DefaultEventListener;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSchemeDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.VODGrid.Book_Activity;
import mmanager.scnx5.com.mitvmanager.getURL;
import mmanager.scnx5.com.mitvmanager.login_activity;
import mmanager.scnx5.com.mitvmanager.redbox_grid_activity;

import static android.view.View.VISIBLE;

public class exoplayer_layar extends AppCompatActivity {

    private String Url,Sypnopsis;
    private Context context;
    private SurfaceView surface;
    private ExoPlayer player;
    private PlayerView simpleExoPlayerView;
    public TextView Channel;
    public LinearLayout HeaderPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer_layar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        context=this;
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            Url = extras.getString("Url");
            Sypnopsis  = extras.getString("Sypnopsis");
            //  Toast.makeText(getApplicationContext(),uniqueID,Toast.LENGTH_LONG).show();
        }

        HeaderPlayer =(LinearLayout)findViewById(R.id.header);
        HeaderPlayer.setVisibility(View.GONE);



    }


    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null) {
            player.release();
            player = null;
        }
    }



    @SuppressLint("ClickableViewAccessibility")
    private void setOnGestureListeners() {
        simpleExoPlayerView.setOnTouchListener(new OnSwipeTouchListener(exoplayer_layar.this) {
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
                // User tapped once (This is what you want)
            }

            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                // User tapped twice
            }
        });
    }

    private void initializePlayer(){
        // Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        //Initialize the player
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        //Initialize simpleExoPlayerView
        simpleExoPlayerView = findViewById(R.id.exoplayer);


        simpleExoPlayerView.setPlayer(player);



        // This is the MediaSource representing the media to be played.

        // player.prepare(videoSource);
        // simpleExoPlayerView.requestFocus();
        //player.setPlayWhenReady(true);





        //set correct width based on resolution

        double width= redbox_grid_activity.getScreenWidth();

        //  int correctWidth;

        //correctWidth = Integer.valueOf(width.intValue());
       // FrameLayout ExoFrame =(FrameLayout) findViewById(R.id.root);
       // simpleExoPlayerView.getLayoutParams().width=(int)width;
       // ExoFrame.getLayoutParams().width=(int)width;

        //  setOnGestureListeners();
        new exoplayer_layar.StartChannel().execute();
    }


    public static double getScreenWidth() {

        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private class StartChannel extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(exoplayer_layar.this);
        String json="";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tPlaying Channel...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {


            getURL wget=new getURL();

            if (Sypnopsis.equalsIgnoreCase("M4K")){

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
            }else if (Sypnopsis.equalsIgnoreCase("LIVENET")){
                json=wget.getURL("http://78.129.139.44:3030/temp/deb.php");
            } else {
                json="";
            }

            return "";

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            pdLoading.dismiss();

            //  Intent i=new Intent(exoplayer_layar.this,exoplayer_layar.class );
            //  i.putExtra("playurl",Url+json);
            //
       //     Intent intent = new Intent(Intent.ACTION_VIEW);



            Uri videoUri = Uri.parse(Url + json);

            //   startActivity(Intent.createChooser(intent, "Complete action using"));
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, getPackageName()));

            // Produces Extractor instances for parsing the media data.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            MediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);

            // Prepare the player with the source.
            player.prepare(videoSource);
            player.setPlayWhenReady(true);





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
