package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartScreen extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.single)
    Button mSingle;
    @BindView(R.id.two)
    Button mTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        ButterKnife.bind(this);
        mSingle.setOnClickListener(this);
        mTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()) {
          case R.id.single:
              Intent i=new Intent(StartScreen.this, OnePlayer.class);
            startActivity(i);
              break;
          case R.id.two:
              Intent u=new Intent(StartScreen.this, TwoPlayer.class);
              startActivity(u);


      }
    }
}
