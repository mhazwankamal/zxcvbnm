package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import mmanager.scnx5.com.mitvmanager.R;

public class android_player_vod extends AppCompatActivity {
    private String Url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_player_vod);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            Url = extras.getString("Url");

        }
        Url="http://storage.layar3.com:8888/vod/English/Ant-Man.And.The.Wasp.2018.720p.BluRay.x264-%5bYTS.AM%5d.mp4";
        Uri uri = Uri.parse(Url);
        final VideoView simpleVideoView = (VideoView) findViewById(R.id.simpleVideoView); // initiate a video view
        simpleVideoView.setVideoURI(uri);
        simpleVideoView.start();
        // create an object of media controller
        MediaController mediaController = new MediaController(this);
        // set media controller object for a video view
        simpleVideoView.setMediaController(mediaController);

        new CountDownTimer(10000, 10000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                simpleVideoView.seekTo(80000);

            }

        }.start();

    }
}
