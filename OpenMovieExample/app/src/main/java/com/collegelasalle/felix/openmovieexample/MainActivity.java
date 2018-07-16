package com.collegelasalle.felix.openmovieexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final String requestFormat = "http://www.omdbapi.com/?apikey=%s&t=%s";
    private final String apiKey = "4671df2";
    private final String requestTitle = "Blade+Runner";
    private String request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = String.format(requestFormat, apiKey, requestTitle);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });
    }

    private void download() {
        new DownloadMovieTask(this).execute(request);
    }

    public void parseJson(String json) {
        String text = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            String title = jsonObject.getString("Title");
            String year = jsonObject.getString("Year");
            String genre = jsonObject.getString("Genre");
            text = String.format("Title: %s\nYear: %s\nGenre: %s", title, year, genre);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
    }
}
