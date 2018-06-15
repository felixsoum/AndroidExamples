package com.collegelasalle.felix.arrayadapterexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> myList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.clear();
                myList.add("One");
                myList.add("Two");
                myList.add("Three");
                adapter.notifyDataSetChanged();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.clear();
                myList.add("Un");
                myList.add("Deux");
                myList.add("Trois");
                adapter.notifyDataSetChanged();
            }
        });
    }
}
