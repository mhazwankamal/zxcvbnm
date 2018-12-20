package mmanager.scnx5.com.mitvmanager;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class checkFileExist {


    public boolean checkFileExist(String filepath,Context context){

        File file = new File(context.getFilesDir(),filepath);

        if (file.exists()){
            return true;
        }
        else{
            return false;
        }
    }




}
