package mmanager.scnx5.com.decrypt;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

import mmanager.scnx5.com.encrypt.dummyencrypt;

public class decrypt {

    public String decryptStr(String str){

        String encryptRtS="";

       dummydecrypt dmdecrypt=new dummydecrypt();

       StringBuffer encryptRtSBf=dmdecrypt.dummydencr(str);

        encryptRtS=encryptRtSBf.toString();

        byte[] data = new byte[0];
        try {
            data = Base64.decode(encryptRtS, Base64.DEFAULT);
            encryptRtS =new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        return encryptRtS;
    }
}
