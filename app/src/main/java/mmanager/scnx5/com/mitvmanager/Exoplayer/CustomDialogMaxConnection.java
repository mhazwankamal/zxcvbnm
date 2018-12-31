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
import android.widget.TextView;
import android.widget.Toast;

import mmanager.scnx5.com.mitvmanager.R;

public class CustomDialogMaxConnection extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    public CountDownTimer cdt;
    public String firstD,SecondD;
    public CustomDialogMaxConnection(Activity a,String first,String second) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        firstD=first;
        SecondD=second;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.Theme_Transparent);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_maxconnection);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        String displaymaxconnection="You have reached limit of concurrent connection for this account";
         displaymaxconnection=displaymaxconnection + "\nLogged device(s) :";
         if (SecondD.equalsIgnoreCase("none")){
            displaymaxconnection=  displaymaxconnection + " "+firstD;
        }else {
             displaymaxconnection=  displaymaxconnection + " "+firstD + "& " + SecondD;

         }


        TextView displayMax=(TextView)findViewById(R.id.message_max);
        displayMax.setText(displaymaxconnection);


    }

    @Override
    public void onClick(View v) {

        dismiss();
    }
}