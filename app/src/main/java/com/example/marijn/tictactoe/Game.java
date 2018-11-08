package com.example.marijn.tictactoe;

import android.widget.TextView;

import java.io.Serializable;

public class Game implements Serializable {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {

        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        if (board[row][column] != TileState.BLANK)
            return TileState.INVALID;
        if (board[row][column] == TileState.BLANK)
            if (playerOneTurn == true) {
                board[row][column] = TileState.CROSS;
            } else {
                board[row][column] = TileState.CIRCLE;
            }

        movesPlayed += 1;
        playerOneTurn = !playerOneTurn;
        return board[row][column];
    }
}
