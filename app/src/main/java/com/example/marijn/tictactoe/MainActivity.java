package com.example.marijn.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class MainActivity extends AppCompatActivity {

    // Create a list with all the (grid) buttons
    String[] allButtons = {"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8", "button9", "messageDisplay"};

    // Variable used to hold the game
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start a new game
        game = new Game();

        // If there are previously saved instance states, restore these states
        if (savedInstanceState == null) {
            return;
        } else {

            // Restore game and gameOver values
            game.gameOver = savedInstanceState.getBoolean("gameEnd");
            game = (Game) savedInstanceState.getSerializable("game");

            // Restore the button states
            for (String button : allButtons) {
                int resourceID = getResources().getIdentifier(button, "id", getPackageName());
                TextView text = findViewById(resourceID);
                text.setText(savedInstanceState.getString(button));
            }
        }
    }

    // Save the state of the current game
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // always call super

        // Save the game and gameOver values in a bundle
        outState.putBoolean("gameEnd", game.gameOver);
        outState.putSerializable("game", game);

        // Save the state of the buttons in a bundle
        for (String button : allButtons) {
            int resourceID = getResources().getIdentifier(button, "id", getPackageName());
            TextView text = findViewById(resourceID);
            outState.putString(button, text.getText().toString());
        }
    }

    // Decide what to do when a tile is clicked
    public void tileClicked(View view) {

        // If the game is already over, do nothing (return)
        if (game.gameOver == true)
            return;

        // Instantiate row and column values
        int row = 0;
        int column = 0;

        // Give back the coordinates of the clicked button
        switch (view.getId()) {
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

        // Check the tile state of the clicked button using row and column values
        TileState state = game.choose(row, column);

        // Instantiate draw as an empty string
        String draw = "";

        // Decide what to do (depending on the state of the clicked tile)
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

        // Set the text on the clicked tile
        TextView field = (TextView) view;
        field.setText(draw);

        gameMessage(row, column);
    }


    // Display a message if the game is won or when it's a draw
    public void gameMessage(int row, int column) {

        // Check if the game is won at an end
        GameState message = game.won(row, column);
        TextView endGameMessage = findViewById(R.id.messageDisplay);

        // Decide what text to display
        switch (message) {
            case IN_PROGRESS:
                break;
            case PLAYER_ONE:
                endGameMessage.setText("Player One (X) has won!");
                break;
            case PLAYER_TWO:
                endGameMessage.setText("Player Two (O) has won!");
                break;
            case DRAW:
                endGameMessage.setText("It's a draw!");
                break;
        }
   }

   // When the reset button is clicked, reset the game
   public void resetClicked(View view) {

        // Create a new game
        game = new Game();

        // Reset all (grid) buttons
        for (String btn : allButtons) {
            int id = getResources().getIdentifier(btn, "id", getPackageName());
            TextView btnText = findViewById(id);
            btnText.setText("");
        }
    }
}
