package com.coughinglouse.rjko.galileifeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.coughinglouse.rjko.galileifeed.utils.Costanti;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button getBtn;
    private TextView result;
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String sParsedHeader = "div.item-page h2";
    public static final String sParsedUrl = "a.at_url";
    public static final String sParsedModDate = "td.at_mod_date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (TextView) findViewById(R.id.result);
        getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWebsite(Costanti.AVVISI_GENITORI);
            }
        });
    }

    private void getWebsite(final String urlToFetch) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect(urlToFetch).get();

                    String header = doc.select(sParsedHeader).first().text();
                    Elements fns = doc.select(sParsedUrl);
                    Elements mds = doc.select(sParsedModDate);

                    builder.append(header).append("\n");

                    for (int i = 0; i < fns.size(); ++i) {

                        builder.append("\n").append("Link: ").append(fns.get(i).attr("href"))
                                .append("\n").append("Data: ").append(mds.get(i).text())
                        .append("\n");
                    }

                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

}