package dev.z3t4.servers.doki.Servers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import dev.z3t4.servers.doki.Pojo.Natsuki;

public class GetNatsukiVideo extends AsyncTask<Void, Void, Void>{

    private String video_url;
    private String base_url;
    @SuppressLint("StaticFieldLeak")
    private AppCompatTextView textView;

    public GetNatsukiVideo(String base_url, AppCompatTextView textView) {
        this.base_url = base_url;
        this.textView = textView;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        textView.setText(video_url);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            Document document = Jsoup.connect(base_url).get();
                String json = document.body().text();
                Natsuki natsuki = new Gson().fromJson(json, Natsuki.class);
                String url = natsuki.getFile();
                video_url = url.substring(0, url.lastIndexOf("?"));

        } catch (IOException e) {
            doInBackground();
            e.printStackTrace();
        }

        return null;
    }
}


