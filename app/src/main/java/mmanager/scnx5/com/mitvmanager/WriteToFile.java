package mmanager.scnx5.com.mitvmanager;

import android.content.Context;
import java.io.FileOutputStream;


public class WriteToFile {


    public void WriteToFile(String filename,String content,Context context) {

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
