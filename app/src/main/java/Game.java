public class Game {

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
        TileState state = board[row][column];
        switch (state) {
            case BLANK:
                if (playerOneTurn == true) {
                    return TileState.CROSS;
                } else {
                    return TileState.CIRCLE;
                }
            case INVALID:
                return TileState.INVALID;
        }
        return state;
    }
}
