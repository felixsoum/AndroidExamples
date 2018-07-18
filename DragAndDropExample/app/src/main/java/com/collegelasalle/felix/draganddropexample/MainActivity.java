package com.collegelasalle.felix.draganddropexample;

import android.content.ClipData;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.redImage).setOnLongClickListener(this);
        findViewById(R.id.greenImage).setOnLongClickListener(this);
        findViewById(R.id.blueImage).setOnLongClickListener(this);
        final TextView textView = findViewById(R.id.textView);
        textView.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                if (dragEvent.getAction() == DragEvent.ACTION_DROP) {
                    int color = Integer.parseInt(dragEvent.getClipData().getItemAt(0).getText().toString());
                    textView.setTextColor(color);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        int colorCode = ((ColorDrawable)view.getBackground()).getColor();
        ClipData data = ClipData.newPlainText("color", Integer.toString(colorCode));
        view.startDrag(data, new DragShadowBuilder(view), null, 0);
        return true;
    }
}
