package mmanager.scnx5.com.authorization;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EzLinearLayout {

    public Context main;

    public LinearLayout EzLinearLayout(Context mainContent) {
        main=mainContent;
         return new LinearLayout(main);
    }

    public void addTextView(TextView TextName,String text){

        TextName=new TextView(main);
        TextName.setText(text);
    }

}
