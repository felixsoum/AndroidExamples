package com.collegelasalle.felix.tic_tac_toeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private int[][] slotValues = new int[3][3];
    private int currentTurn = 1;
    private boolean isGameFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateTurnText();
        int[] buttonIds = new int[]{
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }

        Button button = findViewById(R.id.restartButton);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (isGameFinished) {
            return;
        }

        String description = view.getContentDescription().toString();
        Log.d("indices", description);
        String[] indices = description.split(",");
        int x = Integer.parseInt(indices[0]);
        int y = Integer.parseInt(indices[1]);

        int slotValue = slotValues[x][y];
        if (slotValue != 0) {
            return;
        }

        slotValue = currentTurn;
        slotValues[x][y] = currentTurn;
        Button button = (Button)view;
        button.setText(currentTurn == 1 ? "X" : "O");

        for (int i = 0; i < 3; i++) {
            if (    slotValue == slotValues[i][0] &&
                    slotValue == slotValues[i][1] &&
                    slotValue == slotValues[i][2]) {
                isGameFinished = true;
                break;
            }

            if (    slotValue == slotValues[0][i] &&
                    slotValue == slotValues[1][i] &&
                    slotValue == slotValues[2][i]) {
                isGameFinished = true;
                break;
            }
        }

        isGameFinished = isGameFinished || (
                        slotValue == slotValues[0][0] &&
                        slotValue == slotValues[1][1] &&
                        slotValue == slotValues[2][2]);

        isGameFinished = isGameFinished || (
                        slotValue == slotValues[0][2] &&
                        slotValue == slotValues[1][1] &&
                        slotValue == slotValues[2][0]);

        if (isGameFinished) {
            TextView textView = findViewById(R.id.textView);
            textView.setText(String.format("Game over, player %d wins.", currentTurn));
            return;
        }

        currentTurn = currentTurn == 1 ? 2 : 1;
        updateTurnText();
    }

    private void updateTurnText() {
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.format("Player %d's turn.", currentTurn));
    }
}
