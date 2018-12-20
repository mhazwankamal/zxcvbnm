package mmanager.scnx5.com.encrypt;

public class dummyencrypt {

    protected StringBuffer dummyencr(String str){

        StringBuffer buffer = new StringBuffer(str);
        buffer.reverse();

        return buffer;
    }
}
