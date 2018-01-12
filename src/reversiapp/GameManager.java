package reversiapp;

import FXMLReversi.BoardController;

/**
 * Yael Hacmon, ID 313597897
 * Roni Fultheim, ID 313465965
 */

/**
 * Controls the game flow of a Reversi game when given a board, two players
 * and an object to control the logic of every move.
 *
 * Is responsible for playing the turns, knowing and notifying when the game ends, and printing
 * messages to the players.
 */
public class GameManager {

    // board of game
    private Board board;

    // Starting player is black by default.
    private HumanPlayer currPlayer;

    // Opposite player is black by default.
    private HumanPlayer oppPlayer;

    private StandardMoveLogic logic;

    private ReversiListener listener;

    private BoardController controller;

    /**
     * Constructor taking a board on which to play game, two players, and the logic of the moves.
     * @param b board of game
     * @param black black player
     * @param white white player
     * @param log logic to handle moves
     * @param list listener of game
     * @param bc controller of board
     */
    public GameManager(Board b, HumanPlayer black, HumanPlayer white, StandardMoveLogic log, ReversiListener list,
            BoardController bc) {
        this.board = b;
        this.currPlayer = black;
        this.oppPlayer = white;
        this.listener = list;
        this.controller = bc;
    }

    /**
     * Plays game with the given board, players and logic.
     */
    public void playGame() {
        // declare flag - in first turn game has not been played, current player has moves
        boolean noMoves = false;

        this.view_.showMessage("Current board:");
        this.view_.printBoard(this.board.getBoard(), this.board.getSize());

        /*
         * General explanation - First, build a list containing all the empty cells on the board. then, checking what
         * might be a possible move dor the player, and putting all the options into a vector. The user select a point,
         * and the board update acoording to the selected point.
         */
        // while game is not over - keep playing
        while (!this.board.isBoardFull()) {
            // display current turn
            this.view_.messageForTurn(this.currPlayer.getName());

            // initialize moves for black and white players
            this.logic.updateMoveOptions(this.currPlayer, this.board);

            // declare move here - so we can show move later
            Position move = new Position(-1, -1);

            // if current player can play his turn
            if (this.logic.canPlayTurn(this.currPlayer)) {
                // show possible moves
                this.view_.messagePossibleMoves(this.currPlayer.getPossibleMoves());

                // get next player's move
                move = this.currPlayer.getNextMove(this.view_, this.logic, this.board, this.oppPlayer);

                // check that move is allowed
                // while move isn't legal - get another move from player
                while (!this.logic.isMoveAllowed(move, this.currPlayer)) {
                    this.view_.showMessage("Illegal move, try again.");
                    move = this.currPlayer.getNextMove(this.view_, this.logic, this.board, this.oppPlayer);
                }

                // call logic to play move
                this.logic.playMove(move, this.currPlayer, this.board, this.oppPlayer);

                // update flag
                noMoves = false;
            }
            // if current player cannot play his turn
            else {
                // if the second player cannot play - show message and switch turns
                if (!noMoves) {
                    this.view_.messageSwitchTurns();
                    noMoves = true;

                } else {
                    // if both players did not play - game is over, there are no more moves left in game
                    this.view_.showMessage("No possible moves for both players.");
                    break;
                }
            }

            // show board and last moves
            this.view_.showMessage("\nCurrent board:");
            this.view_.printBoard(this.board.getBoard(), this.board.getSize());
            // message of last turn - if was played
            if (!noMoves) {
                this.view_.messagePlayerMove(move, this.currPlayer.getName());
            }

            // switch players
            HumanPlayer temp = this.currPlayer;
            this.currPlayer = this.oppPlayer;
            this.oppPlayer = temp;
        }
        this.showWinner();
    }

    /**
     * Returns winner of game.
     * @return player who won this game
     */
    public void showWinner() {
        if (this.currPlayer.getScore() > this.oppPlayer.getScore()) {
            this.view_.messageWinner(this.currPlayer.getName());
        } else if (this.currPlayer.getScore() < this.oppPlayer.getScore()) {
            this.view_.messageWinner(this.oppPlayer.getName());
        } else {
            this.view_.showMessage("Game over! Tie!! Players have equal scores.");
        }

    }
}
