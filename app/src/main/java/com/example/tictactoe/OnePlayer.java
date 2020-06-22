package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnePlayer extends AppCompatActivity {
    @BindView(R.id.text_view_p1)
    TextView mTextViewP1;
    @BindView(R.id.text_view_p2)
    TextView mTextViewP2;
    @BindView(R.id.button_reset)
    Button mButtonReset;
    @BindView(R.id.p1)
    TextView mP1;
    @BindView(R.id.p2)
    TextView mP2;
    int p11=0;
    int p12=0;


    private BoardView boardView;
    private GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        ButterKnife.bind(this);

        boardView = findViewById(R.id.board);
        gameEngine = new GameEngine();
        boardView.setGameEngine(gameEngine);
        boardView.setMainActivity(this);

    }


    public void gameEnded(char c) {
        String msg;
        if (c == 'T') {
            msg = "تعادل 😎😎";
            newGame();
        } else if (c == 'X') {
            p11++;
            mP1.setText(""+p11);
            newGame();
            msg = " انت الرابح 🎈🎈🎉🎁✨ " + "اللاعب";
        } else {
            p12++;
            mP2.setText(""+p12);
            newGame();
            msg = " انت الرابح 🎈🎈🎉🎁✨ " + "اندرويد";
        }

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }



    public void onClick(View view) {
        newGame();
        p11=0;
        p12=0;
        mP1.setText(""+p11);
        mP2.setText(""+p12);
    }

    private void newGame() {
        gameEngine.newGame();
        boardView.invalidate();


    }

}


