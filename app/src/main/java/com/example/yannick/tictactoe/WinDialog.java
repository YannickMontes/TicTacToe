package com.example.yannick.tictactoe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by yannick on 30/09/17.
 */

public class WinDialog extends DialogFragment{

    public String headerText;
    public String winner;

    public WinDialog()
    {
        setCancelable(false);
    }

    public void setHeaderText(String head)
    {
        this.headerText = head;
    }

    public void setWinner(String win)
    {
        this.winner = win;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = winner != null ? ("Game won by" + winner +" !") : "Game end. No winner.";
        builder.setMessage(message)
                .setPositiveButton(R.string.replay, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id)
        {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

    }).setNegativeButton(R.string.quit, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id)
            {
                System.exit(0);
            }
        });

        return builder.create();
    }
}
