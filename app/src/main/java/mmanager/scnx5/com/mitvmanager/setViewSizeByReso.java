package mmanager.scnx5.com.mitvmanager;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

public class setViewSizeByReso {
    Integer width,height;



    public void setSize(View view,double widthFactor,double heightFactor){

        width= Resources.getSystem().getDisplayMetrics().widthPixels;
        height= Resources.getSystem().getDisplayMetrics().heightPixels;

        if (heightFactor > 0.00) {

            int setHeight= (int) (height * heightFactor);
            view.getLayoutParams().height = setHeight;
            //Log.d("Height",String.valueOf(setHeight));
        }

        if (widthFactor > 0.00) {

            int setWidth = (int) (width * widthFactor);
            view.getLayoutParams().width = setWidth;
           // Log.d("Width",String.valueOf(setWidth));
        }
    }

    public int getPixelByReso(double ratio){

        width= Resources.getSystem().getDisplayMetrics().widthPixels;
        height= Resources.getSystem().getDisplayMetrics().heightPixels;

        int pixel=0;

        pixel = (int) (height * ratio);

        return pixel;
    }
}
