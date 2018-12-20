package mmanager.scnx5.com.login;

public class makeJSON {

    public String createLoginJSON(String str){

        String[] getData=str.split(":");

        str= "{\"username\":\"" + getData[0] + "\",";
        str+="\"password\":\"" + getData[1] + "\",";
        str+="\"time\":\"" + getData[2] + "\"}";
        return str;
    }
}
