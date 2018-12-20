package mmanager.scnx5.com.encrypt;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class encrypt {

    public String encryptStr(String str){

        String encryptRtS="";

        byte[] data = new byte[0];
        try {
            data = str.getBytes("UTF-8");
            encryptRtS = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        dummyencrypt dmencr=new dummyencrypt();

        StringBuffer encryptRtSBf=dmencr.dummyencr(encryptRtS);

        encryptRtS=encryptRtSBf.toString();

        return encryptRtS;
    }

}
