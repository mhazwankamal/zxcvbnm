package mmanager.scnx5.com.mitvmanager.Exoplayer;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.setViewSizeByReso;

public class CustomDialogClasscontinuePlay extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public CountDownTimer cdt;
    public long lastplayposition;
    public CustomDialogClasscontinuePlay(Activity a,long lastplay) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.lastplayposition=lastplay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Theme_Transparent);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_continue_playing);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FrameLayout FramerootCont=(FrameLayout) findViewById(R.id.fcontinue_root);
        LinearLayout rootCont=(LinearLayout) findViewById(R.id.continue_root);

        setViewSizeByReso setView=new setViewSizeByReso();


        setView.setSize(FramerootCont,1,1);
        setView.setSize(rootCont,0.4,-1);

        yes = (Button) findViewById(R.id.yes_button);
        no = (Button) findViewById(R.id.no_button);


        int seconds = (int) (lastplayposition / 1000) % 60;
        int minutes = (int) ((lastplayposition / (1000 * 60)) % 60);
        int hours = (int) ((lastplayposition / (1000 * 60 * 60)) % 24);

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

        yes.setText("Continue playing at " + totalCurrentPosition);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        yes.requestFocus();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_button:
                dismiss();
                ((exoplayer_layar_vod_new_code)c).continueToStartChannel(lastplayposition);

                break;
            case R.id.no_button:
                dismiss();
                ((exoplayer_layar_vod_new_code)c).continueToStartChannel(0);
                //   c.finish();
                break;
            default:
                c.finish();
                break;
        }

       // dismiss();

    }
}