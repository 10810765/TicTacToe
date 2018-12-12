package com.example.marijn.tictactoe;

import java.io.Serializable;

import static com.example.marijn.tictactoe.GameState.DRAW;
import static com.example.marijn.tictactoe.GameState.IN_PROGRESS;
import static com.example.marijn.tictactoe.GameState.PLAYER_ONE;
import static com.example.marijn.tictactoe.GameState.PLAYER_TWO;
import static com.example.marijn.tictactoe.TileState.BLANK;
import static com.example.marijn.tictactoe.TileState.CIRCLE;
import static com.example.marijn.tictactoe.TileState.CROSS;
import static com.example.marijn.tictactoe.TileState.INVALID;


/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class Game implements Serializable {

    // Constant and variable used to hold the board
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    // Variables used to keep count who's turn it is
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;

    // Variable used to check if the game is over
    public Boolean gameOver;


    // When game starts, initialize the above (game) variables
    public Game() {

        // Initialize the board
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    // Check the state of a tile and take appropriate action
    public TileState choose(int row, int column) {
        if (board[row][column] != BLANK)
            return INVALID;
        if (board[row][column] == BLANK)
            if (playerOneTurn == true) {
                board[row][column] = CROSS;
            } else {
                board[row][column] = CIRCLE;
            }

        // When tile is clicked, up the moves played by one
        movesPlayed += 1;

        // Keep track of who's turn it is
        playerOneTurn = !playerOneTurn;
        return board[row][column];
    }

    // Check if someone has won the game
    public GameState won(int row, int column) {

        // Booleans used to check if someone has won the game
        boolean horizontal = true;
        boolean vertical = true;
        boolean diagonal = true;
        boolean diagonal2 = true;

        // Check if there are three X's or O's in a row
        for (int num = 0; num < BOARD_SIZE; num++) {
            if (board[row][column] != board[row][num])
                horizontal = false;

            if (board[row][column] != board[num][column])
                vertical = false;

            if (board[row][column] != board[num][num])
                diagonal = false;

            if (board[row][column] != board[(BOARD_SIZE - 1) - num][num])
                diagonal2 = false;
        }

        // If there are three X's or O's in a row, return the winner!
        if (horizontal == true || vertical == true || diagonal == true || diagonal2 == true) {
            gameOver = true;
            if (board[row][column] == CROSS) {
                return PLAYER_ONE;
            } else {
                return PLAYER_TWO;
            }
        }

        // If all tiles (grid buttons) have been clicked, return Draw
        if (movesPlayed == (BOARD_SIZE * BOARD_SIZE)) {
            gameOver = true;
            return DRAW;
        } else {
        return IN_PROGRESS;
        }
    }
}


