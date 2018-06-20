package com.collegelasalle.felix.firebaseanalyticsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Button button1 = findViewById(R.id.item1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEvent(1, "Apple");
            }
        });

        Button button2 = findViewById(R.id.item2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEvent(2, "Orange");
            }
        });

        Button button3 = findViewById(R.id.item3);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEvent(3, "Banana");
            }
        });
    }

    private void sendEvent(int itemID, string fruitType) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemID);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, fruitType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
