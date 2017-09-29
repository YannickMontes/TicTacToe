package com.example.yannick.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity","OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runGame(View view)
    {
        Intent gameActivity = new Intent(this, activity_game.class);
        EditText Xedit = (EditText) findViewById(R.id.nomX);
        EditText Yedit = (EditText) findViewById(R.id.nomO);
        String Xplayer = Xedit.getText().toString();
        String Oplayer = Yedit.getText().toString();
        gameActivity.putExtra("Oplayer", Oplayer);
        gameActivity.putExtra("Xplayer", Xplayer);
        startActivity(gameActivity);
    }
}
