package mmanager.scnx5.com.CONST;

import java.io.IOException;

import mmanager.scnx5.com.mitvmanager.getURL;

public class CONST {

    public static String layar3="true";
    public static String rayal3="false";
    public static String rayal3max="expired";
    public static String layar3N="trueN";

    public String CONST(){

        return "";
    }

    public String getEn(){

        return layar3;

    }

    public String getEnN(){

        return layar3N;

    }

    public String getDeEn(){

        return rayal3;

    }

    public String getDeEnMax(){

        return rayal3max;

    }

    public String getMainServer(){
        getURL wget=new getURL();
        String server="";
        try {
            server=wget.getURL("http://scnx5.sytes.net/serverlist.php");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return server;
    }





}
