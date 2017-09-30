package com.example.yannick.tictactoe;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class activity_game extends AppCompatActivity {

    private boolean isPlayingX;

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

        isPlayingX = true;
    }

    public void onClickButton(View view)
    {
        Button clicked = (Button) findViewById(view.getId());

        clicked.setText((isPlayingX ? "X" : "O"));

        isPlayingX = !isPlayingX;

        clicked.setClickable(false);

        String[][] grid = this.getValues();

        this.checkWin(grid);

        if (this.checkFull(grid))
        {
            displayEnd();
        }
    }

    private String[][] getValues()
    {
        String[][] values = new String[3][3];
        TableLayout grid = (TableLayout) findViewById(R.id.grid);
        for(int i=0;  i<grid.getChildCount(); i++)
        {
            TableRow row = (TableRow) grid.getChildAt(i);
            for(int j=0; j< row.getChildCount(); j++)
            {
                Button button = (Button)row.getChildAt(j);
                values[i][j] = button.getText().toString();
            }
        }

        return values;
    }

    private boolean checkFull(String[][] grid)
    {
        for(int i=0; i<grid.length; i++)
        {
            for(int j=0; j<grid[i].length; j++)
            {
                if(grid[i][j].equals(""))
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkWin(String[][] grid)
    {
        String diag = checkDiag(grid);
        if(diag !=null)
        {
            displayWinner(diag);
            return;
        }
        String line = checkLine(grid);
        if(line !=null)
        {
            displayWinner(line);
            return;
        }
        String columns = checkCol(grid);
        if(columns !=null)
        {
            displayWinner(columns);
            return;
        }
    }

    private void displayWinner(String winner)
    {
        WinDialog dialog = new WinDialog();
        dialog.setWinner(winner);
        dialog.show(getFragmentManager(), "Ok");
        System.out.println("WINNER FOUND");
    }

    private void displayEnd()
    {
        WinDialog dialog = new WinDialog();
        dialog.setWinner(null);
        dialog.show(getFragmentManager(), "Ok");
        System.out.println("END");
    }

    private String checkDiag(String[][] grid)
    {
        boolean win = true;
        String old = grid[0][0];

        //First check diag 1
        for(int i=0; i<grid.length; i++)
        {
            if(!old.equals(grid[i][i]))
            {
                win = false;
                i=grid.length;
            }
        }

        if(win && !old.equals(""))
        {
            return old;
        }

        old = grid[0][(grid[0].length)-1];
        win = false;
        for(int j=grid.length-1; j>=0; j--)
        {
            if(!old.equals(grid[(grid.length -1) - j][j]))
            {
                win = false;
                j = -1;
            }
        }

        if(win && !old.equals(""))
        {
            return old;
        }

        return null;
    }

    private String checkLine(String[][] grid)
    {
        for(int i=0; i<grid.length; i++)
        {
            boolean win = true;
            String old = grid[i][0];
            for(int j=0; j<grid[i].length; j++)
            {
                if(old.equals("") || !old.equals(grid[i][j]))
                {
                    win = false;
                    j = grid[i].length;
                }
            }
            if(win)
            {
                return old;
            }
        }

        return null;
    }

    private String checkCol(String[][] grid)
    {
        for(int i=0; i<grid[0].length; i++)
        {
            boolean win = true;
            String old = grid[0][i];
            for(int j=0; j<grid.length; j++)
            {
                if(old.equals("") || !old.equals(grid[j][i]))
                {
                    win = false;
                    j = grid.length;
                }
            }
            if(win)
            {
                return old;
            }
        }

        return null;
    }
}
