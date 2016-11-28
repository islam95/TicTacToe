package com.islamdudaev.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0=yellow 1=red

    int[] gameState = {2,2,2,2,2,2,2,2,2}; // 2 means empty

    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void clickButton(View view){

        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f); // move it 1000px of the top of the screen.

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winning : winPositions){
                if(gameState[winning[0]] == gameState[winning[1]] &&
                        gameState[winning[1]] == gameState[winning[2]] &&
                        gameState[winning[0]] != 2){
                    // someone has won!

                    String winner = "Red";

                    if(gameState[winning[0]] == 0){
                        winner = "Yellow";
                    }

                    TextView message = (TextView) findViewById(R.id.winnerMessage);

                    message.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){

        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        int activePlayer = 0; // resetting

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < grid.getChildCount(); i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
