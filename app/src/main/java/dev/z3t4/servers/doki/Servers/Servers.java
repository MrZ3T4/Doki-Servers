package dev.z3t4.servers.doki.Servers;

public class Servers {

    public String getNiceURL(String url){

        String video_url = "";

        if (url.contains("natsuki")){
            video_url = getNatsuki(url);
        } else if (url.contains("embedsito")){
            video_url = getFembed(url);
        } else if (url.contains("ok.ru")){
            video_url = url;
        }

        return video_url;
    }

    public String getNatsuki(String url){
        String natsuki = url.replace("embed", "check");
        return natsuki;
    }

    public String getFembed(String url){
        String fembed = url.replace("embedsito", "fembed");
        return fembed;
    }


}
