package dev.z3t4.servers.doki.Servers;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.htetznaing.xgetter.Model.XModel;
import com.htetznaing.xgetter.XGetter;

import java.util.ArrayList;

public class xGetterUrl {

    private String URL;

    public void getXGetterUrl(Context context, String url, AppCompatTextView textView){

        XGetter xGetter = new XGetter(context);
        xGetter.onFinish(new XGetter.OnTaskCompleted() {

            @Override
            public void onTaskCompleted(ArrayList<XModel> vidURL, boolean multiple_quality) {
                    URL = vidURL.get(0).getUrl();
                    System.out.println(URL);
                    textView.setText(URL);
            }

            @Override
            public void onError() {
                    textView.setText("Video no disponible debido a que está declarado con derechos de autor. Intenta con otro enlace.");
            }
        });

        xGetter.find(url);
    }

}
