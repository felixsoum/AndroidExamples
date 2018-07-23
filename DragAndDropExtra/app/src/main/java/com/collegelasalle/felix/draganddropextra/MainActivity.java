package com.collegelasalle.felix.draganddropextra;

import android.content.ClipData;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnLongClickListener, OnDragListener {

    private View token;
    private TextView selectNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectNumber = findViewById(R.id.selectNumber);
        token = findViewById(R.id.imageView);
        token.setOnLongClickListener(this);

        for (int id : new int[] {
            R.id.textView1,
            R.id.textView2,
            R.id.textView3,
            R.id.textView4,
            R.id.textView5,
            R.id.textView6,
            R.id.textView7,
            R.id.textView8,
            R.id.textView9
        }) {
            findViewById(id).setOnDragListener(this);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        view.startDragAndDrop(ClipData.newPlainText("",""), new DragShadowBuilder(view), null ,0);
        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
            int[] loc = new int[2];
            view.getLocationInWindow(loc);
            token.setX(loc[0] + token.getWidth()/3);
            token.setY(loc[1] - token.getHeight());

            TextView textView = (TextView)view;
            selectNumber.setText(textView.getText());
        }
        return true;
    }
}
