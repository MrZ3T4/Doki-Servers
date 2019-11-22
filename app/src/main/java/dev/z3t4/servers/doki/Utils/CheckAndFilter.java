package dev.z3t4.servers.doki.Utils;

public class CheckAndFilter {

    public boolean checkFLV(String url){

        if (url.contains("natsuki")){
            return true;
        } else if (url.contains("embedsito") || url.contains("fembed")){
            return true;
        } else if (url.contains("ok.ru")){
            return true;
        } else {
            return false;
        }
    }

    public int filterFLV(String pre_url){

        int filter = 0;

        if (pre_url.contains("natsuki")){
            filter = 1;
        } else if (pre_url.contains("embedsito ") || pre_url.contains("fembed")) {
            filter = 2;
        } else if (pre_url.contains("ok.ru")){
            filter = 3;
        }
        return filter;
    }


}
