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

import mmanager.scnx5.com.mitvmanager.R;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public CountDownTimer cdt;
    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Theme_Transparent);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_check_playing);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        yes = (Button) findViewById(R.id.yes_button);
        no = (Button) findViewById(R.id.no_button);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        yes.requestFocus();
        no.setText("BACK (30s)");
        cdt=new CountDownTimer(60000,1000) {

            String displayText="";

            @Override
            public void onTick(long l) {


                displayText="BACK ("+String.valueOf(l/1000)+"s)";
                no.setText(displayText);

            }

            @Override
            public void onFinish() {
                ((exoplayer_layar)c).properlyLogout();
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes_button:
                dismiss();
                ((exoplayer_layar)c).RunExoPlayerTimerBack();
                cdt.cancel();
                break;
            case R.id.no_button:
                ((exoplayer_layar)c).properlyLogout();
             //   c.finish();
                break;
            default:
                break;
        }
        cdt.cancel();
        dismiss();

    }
}