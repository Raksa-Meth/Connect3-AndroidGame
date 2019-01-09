package com.example.raksa.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //View
    Button buttonPlayAgain;
    GridLayout gridLayout;

    boolean isFirstPlayer = true;

    // 0 : yellow , 1 : Red , 2 : empty
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //winning position
    int [][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameIsActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlayAgain = findViewById(R.id.buttonPlayAgain);
        gridLayout = findViewById(R.id.gridLayout);

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<gridLayout.getChildCount(); i++) {
                    ImageView currentCounter = (ImageView) gridLayout.getChildAt(i);
                    currentCounter.setImageDrawable(null);

                    Arrays.fill(gameState , 2);

                    gameIsActive = true;

                    buttonPlayAgain.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public void dropIn(View view){


        ImageView counter = (ImageView) view;

        int tapperCounterID = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapperCounterID] == 2 && gameIsActive){

            changeGameState(isFirstPlayer , counter);

            counter.setTranslationY(-1500);

            if (isFirstPlayer){
                counter.setImageResource(R.drawable.red);
            }else {
                counter.setImageResource(R.drawable.yellow);
            }
            counter.animate().translationYBy(1500).setDuration(300);

            checkingWinning(isFirstPlayer);

            changePlayerTurn();
        }else {
            Toast.makeText(this,"Game has end",Toast.LENGTH_SHORT).show();
        }


    }

    private void changePlayerTurn(){

        if (isFirstPlayer){
            isFirstPlayer = false;
        }else {
            isFirstPlayer = true;
        }
    }

    private void changeGameState(boolean isFirstPlayer , ImageView counter){

        int tapperCounterID = Integer.parseInt(counter.getTag().toString());
        if (isFirstPlayer){
            gameState[tapperCounterID] = 1;
        }else {
            gameState[tapperCounterID] = 0;
        }

    }

    private void checkingWinning(boolean isFirstPlayer) {
        for (int[] winningPosition : winningPosition){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2){

                gameIsActive = false;
                buttonPlayAgain.setVisibility(View.VISIBLE);

                if (isFirstPlayer){
                    Toast.makeText(getBaseContext(),"Red Player has won" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(),"Yellow Player has won" , Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

}
