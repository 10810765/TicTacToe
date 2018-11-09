package com.example.marijn.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] allButtons = {"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8", "button9", "messageDisplay"};

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        game = new Game();



        if (savedInstanceState == null) {
            return;
        } else {
            game.gameOver = savedInstanceState.getBoolean("gameEnd");
            game = (Game)savedInstanceState.getSerializable("game");

            for (String button : allButtons) {
                int resourceID = getResources().getIdentifier(button, "id", getPackageName());
                TextView text = findViewById(resourceID);
                text.setText(savedInstanceState.getString(button));
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // always call super
        outState.putBoolean("gameEnd", game.gameOver);
        outState.putSerializable("game", game);

        // Save the state with outState
        for (String button : allButtons) {
            int resourceID = getResources().getIdentifier(button, "id", getPackageName());
            TextView text = findViewById(resourceID);
            outState.putString(button, text.getText().toString());
        }
    }

    public void tileClicked(View view) {
        if (game.gameOver == true)
            return;

        int id = view.getId();

        int row = 0;
        int column = 0;

        switch (id) {
            case R.id.button1:
                row = 0;
                column = 0;
                break;
            case R.id.button2:
                row = 0;
                column = 1;
                break;
            case R.id.button3:
                row = 0;
                column = 2;
                break;
            case R.id.button4:
                row = 1;
                column = 0;
                break;
            case R.id.button5:
                row = 1;
                column = 1;
                break;
            case R.id.button6:
                row = 1;
                column = 2;
                break;
            case R.id.button7:
                row = 2;
                column = 0;
                break;
            case R.id.button8:
                row = 2;
                column = 1;
                break;
            case R.id.button9:
                row = 2;
                column = 2;
                break;
        }

        TileState state = game.choose(row, column);

        String draw = "";
        switch (state) {
            case CROSS:
                draw = "X";
                break;
            case CIRCLE:
                draw = "O";
                break;
            case INVALID:
                return;
        }

        TextView field = (TextView) view;
        field.setText(draw);

        GameState message = game.won(row, column);
        TextView editText = (TextView)findViewById(R.id.messageDisplay);

        switch (message) {
            case IN_PROGRESS:
                break;
            case PLAYER_ONE:
                editText.setText("Player One has won!");
                break;
            case PLAYER_TWO:
                editText.setText("Player Two has won!");
                break;
            case DRAW:
                editText.setText("It's a draw!");
                break;
        }
    }

    public void resetClicked(View view) {

        game = new Game();

        for (String btn : allButtons) {
            int id = getResources().getIdentifier(btn, "id", getPackageName());
            TextView btnText = findViewById(id);
            btnText.setText("");
        }
    }
}
