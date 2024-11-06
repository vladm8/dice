package com.example.dice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView[] diceTextViews;
    private TextView rollResultTextView, gameScoreTextView, rollCountTextView;
    private int gameScore = 0;
    private int rollCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceTextViews = new TextView[]{
                findViewById(R.id.dice1),
                findViewById(R.id.dice2),
                findViewById(R.id.dice3),
                findViewById(R.id.dice4),
                findViewById(R.id.dice5)
        };

        rollResultTextView = findViewById(R.id.rollResultTextView);
        gameScoreTextView = findViewById(R.id.gameScoreTextView);
        rollCountTextView = findViewById(R.id.rollCountTextView);
        Button rollButton = findViewById(R.id.rollButton);
        Button resetButton = findViewById(R.id.resetButton);

        rollButton.setOnClickListener(v -> rollDice());
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void rollDice() {
        Random random = new Random();
        int[] diceResults = new int[5];
        HashMap<Integer, Integer> diceCounts = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            int result = random.nextInt(6) + 1;
            diceResults[i] = result;
            diceTextViews[i].setText(String.valueOf(result));
            diceCounts.put(result, diceCounts.getOrDefault(result, 0) + 1);
        }

        int roundScore = 0;
        for (int value : diceCounts.keySet()) {
            int count = diceCounts.get(value);
            if (count > 1) {
                roundScore += value * count;
            }
        }

        rollCount++;
        gameScore += roundScore;

        rollResultTextView.setText("Wynik tego losowania: " + roundScore);
        gameScoreTextView.setText("Wynik gry: " + gameScore);
        rollCountTextView.setText("Liczba rzutów: " + rollCount);
    }

    private void resetGame() {
        gameScore = 0;
        rollCount = 0;

        for (TextView diceTextView : diceTextViews) {
            diceTextView.setText("?");
        }

        rollResultTextView.setText("Wynik tego losowania: 0");
        gameScoreTextView.setText("Wynik gry: 0");
        rollCountTextView.setText("Liczba rzutów: 0");
    }
}
