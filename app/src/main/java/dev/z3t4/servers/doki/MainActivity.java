package dev.z3t4.servers.doki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.htetznaing.xgetter.XGetter;

import java.nio.file.FileSystemLoopException;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.z3t4.servers.doki.Servers.GetNatsukiVideo;
import dev.z3t4.servers.doki.Servers.Servers;
import dev.z3t4.servers.doki.Servers.xGetterUrl;
import dev.z3t4.servers.doki.Utils.CheckAndFilter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text)
    TextInputEditText editText;
    @BindView(R.id.text_result)
    AppCompatTextView textView;
    @BindView(R.id.button)
    MaterialButton button;

    private String pre_url, url;
    private boolean isValid;

    private Servers servers = new Servers();
    private CheckAndFilter checkAndFilter = new CheckAndFilter();
    private GetNatsukiVideo getNatsukiVideo;
    private xGetterUrl xGetterUrl = new xGetterUrl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        button.setOnClickListener(view -> {
            url = editText.getText().toString();

            if (editText.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "El campo está vacio", Toast.LENGTH_SHORT).show();
            } else {
                isValid = checkAndFilter.checkFLV(url);
                if (isValid){

                    editText.setText("");
                    textView.setText("Procesando...");

                    pre_url = servers.getNiceURL(url);

                    System.out.println(pre_url);

                    switch (checkAndFilter.filterFLV(pre_url)){

                        case 1:
                            getNatsukiVideo = new GetNatsukiVideo(pre_url, textView);
                            getNatsukiVideo.execute();
                            break;
                        case 2:
                        case 3:
                            xGetterUrl.getXGetterUrl(this, pre_url, textView);
                            break;

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Enlace no compatible con los servidores de AnimeFLV", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
