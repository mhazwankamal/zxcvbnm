package mmanager.scnx5.com.decrypt;

public class dummydecrypt {

    protected StringBuffer dummydencr(String str){

        StringBuffer buffer = new StringBuffer(str);
        buffer.reverse();

        return buffer;
    }
}
