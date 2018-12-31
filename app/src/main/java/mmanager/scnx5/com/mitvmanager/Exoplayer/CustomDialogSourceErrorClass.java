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

public class CustomDialogSourceErrorClass extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public CountDownTimer cdt;
    public CustomDialogSourceErrorClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Theme_Transparent);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_source_error);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



    }

    @Override
    public void onClick(View v) {

        dismiss();
    }
}