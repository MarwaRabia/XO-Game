package com.example.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwoPlayer extends AppCompatActivity implements View.OnClickListener ,Dialog.ExampleDialogListener{
    @BindView(R.id.text_view_p1)
    TextView mTextViewP1;
    @BindView(R.id.text_view_p2)
    TextView mTextViewP2;
    @BindView(R.id.button_reset)
    Button mButtonReset;
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points=0;
    private int player2Points=0;
    @BindView(R.id.p1)
    TextView mP1;
    @BindView(R.id.p2)
    TextView mP2;
    String p1="";
    String p2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player);
        ButterKnife.bind(this);
openDialog();
      initialButtons();
      mButtonReset.setOnClickListener(this);

    }


    // to initial all buttons
private  void initialButtons(){
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            String buttonID = "button_" + i + j;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i][j] = findViewById(resID);
            buttons[i][j].setOnClickListener(this);
        }
    }

}



    // to check place of space
    @Override
    public void onClick(View v) {
       if ((v.getId())==R.id.button_reset){
           resetGame();
       }

        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.BLUE);
            ((Button)v).setTextSize(130);


        }
        else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.GREEN);
            ((Button)v).setTextSize(130);

        }
        roundCount++;

        if (checkForWin()){
            if (player1Turn){
                player1Wins();
            }
            else {
                player2Wins();
            }
        }
        else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private void resetGame() {
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }

    //to check who is win
    private boolean checkForWin(){
        String[][] field = new String[3][3];

        // to put o or x in the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //to check Rows
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        //to check columns
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //to check diagonals
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;

    }

    private void player1Wins() {
       player1Points++;
       if (p1.equals("")){
           p1="اللاعب الأول";
       }
        Toast.makeText(this, p1+" انت الرابح 🎈🎈🎉🎁✨ ", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        if (p2.equals("")){
            p2="اللاعب الثاني ";
        }
        Toast.makeText(this, p2+ " انت الرابح 🎈🎈🎉🎁✨ ", Toast.LENGTH_LONG).show();
        updatePointsText();
        resetBoard();
    }




    private void draw() {
        Toast.makeText(this, "تعادل 😎😎 ", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j].setText("");
            }
        }

        player1Turn=true;
        roundCount=0;
        }



    public void openDialog() {
        Dialog exampleDialog = new Dialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String player1, String player2) {
        p1=player1;
        p2=player2;
        mTextViewP1.setText(player1+" : ");
        mTextViewP2.setText(player2+" : ");

    }
    private void updatePointsText() {

        mP1.setText(""+player1Points);
        mP2.setText(""+player2Points);


    }
}


