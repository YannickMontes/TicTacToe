package com.example.yannick.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class activity_game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent gameIntent = getIntent();

        String Oplayer = gameIntent.getStringExtra("Oplayer");
        String Xplayer = gameIntent.getStringExtra("Xplayer");

        TextView Oview = (TextView) findViewById(R.id.labelJO);
        Oview.setText("0: "+Oplayer);

        TextView Xview = (TextView) findViewById(R.id.labelJX);
        Xview.setText("X: "+Xplayer);
    }
}
