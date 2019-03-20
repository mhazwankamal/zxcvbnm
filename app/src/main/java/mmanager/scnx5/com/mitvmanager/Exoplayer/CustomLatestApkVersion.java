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
import android.widget.TextView;

import mmanager.scnx5.com.mitvmanager.R;
import mmanager.scnx5.com.mitvmanager.setViewSizeByReso;

public class CustomLatestApkVersion extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public CountDownTimer cdt;
    public CustomLatestApkVersion(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Theme_Transparent);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_latest_appversion);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        FrameLayout flatestapk=(FrameLayout)findViewById(R.id.frame_latestapk);
        String displaymaxconnection="Please update to latest application version to continue.";

        setViewSizeByReso setView=new setViewSizeByReso();
        setView.setSize(flatestapk,1,1);

        TextView displayMax=(TextView)findViewById(R.id.message_max);
        displayMax.setText(displaymaxconnection);
        yes=(Button)findViewById(R.id.go_home);
        yes.setOnClickListener(this);
        yes.requestFocus();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_home:
                dismiss();
                ((exoplayer_layar)c).exit_dueto_oldversion();
                 break;
            default:
                break;
        }

       // dismiss();

    }
}