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

public class Game implements Serializable {



    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    public Boolean gameOver;

    public Game() {

        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public TileState choose(int row, int column) {
        if (board[row][column] != BLANK)
            return INVALID;
        if (board[row][column] == BLANK)
            if (playerOneTurn == true) {
                board[row][column] = CROSS;
            } else {
                board[row][column] = CIRCLE;
            }

        movesPlayed += 1;

        playerOneTurn = !playerOneTurn;
        return board[row][column];
    }

    public GameState won(int row, int column) {

        // Booleans used to check if someone has won the game
        boolean horizontal = true;
        boolean vertical = true;
        boolean diagonal = true;
        boolean diagonal2 = true;

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

        if (horizontal == true || vertical == true || diagonal == true || diagonal2 == true) {
            gameOver = true;
            if (board[row][column] == CROSS) {
                return PLAYER_ONE;
            } else {
                return PLAYER_TWO;
            }
        }

        if (movesPlayed == (BOARD_SIZE * BOARD_SIZE)) {
            gameOver = true;
            return DRAW;
        } else {
        return IN_PROGRESS;
        }
    }
}


