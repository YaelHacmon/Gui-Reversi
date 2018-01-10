package reversiapp;

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
class GameManager {

    // board of game
    private Board board_;

    // Starting player is black by default.
    private HumanPlayer currPlayer_;

    // Opposite player is black by default.
    private HumanPlayer oppPlayer_;

    private StandardMoveLogic logic_;

    private ViewGame view_;

    /**
     * Constructor taking a board on which to play game, two players, and the logic of the moves.
     * @param b board of game
     * @param black black player
     * @param white white player
     * @param log logic to handle moves
     */
    public GameManager(ViewGame view, Board b, HumanPlayer black, HumanPlayer white, StandardMoveLogic log) {
        this.board_ = b;
        this.currPlayer_ = black;
        this.oppPlayer_ = white;
        this.view_ = view;
    }

    /**
     * Plays game with the given board, players and logic.
     */
    public void playGame() {
        // declare flag - in first turn game has not been played, current player has moves
        boolean noMoves = false;

        this.view_.showMessage("Current board:");
        this.view_.printBoard(this.board_.getBoard(), this.board_.getSize());

        /*
         * General explanation - First, build a list containing all the empty cells on the board. then, checking what
         * might be a possible move dor the player, and putting all the options into a vector. The user select a point,
         * and the board update acoording to the selected point.
         */
        // while game is not over - keep playing
        while (!this.board_.isBoardFull()) {
            // display current turn
            this.view_.messageForTurn(this.currPlayer_.getName());

            // initialize moves for black and white players
            this.logic_.updateMoveOptions(this.currPlayer_, this.board_);

            // declare move here - so we can show move later
            Position move = new Position(-1, -1);

            // if current player can play his turn
            if (this.logic_.canPlayTurn(this.currPlayer_)) {
                // show possible moves
                this.view_.messagePossibleMoves(this.currPlayer_.getPossibleMoves());

                // get next player's move
                move = this.currPlayer_.getNextMove(this.view_, this.logic_, this.board_, this.oppPlayer_);

                // check that move is allowed
                // while move isn't legal - get another move from player
                while (!this.logic_.isMoveAllowed(move, this.currPlayer_)) {
                    this.view_.showMessage("Illegal move, try again.");
                    move = this.currPlayer_.getNextMove(this.view_, this.logic_, this.board_, this.oppPlayer_);
                }

                // call logic to play move
                this.logic_.playMove(move, this.currPlayer_, this.board_, this.oppPlayer_);

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
            this.view_.printBoard(this.board_.getBoard(), this.board_.getSize());
            // message of last turn - if was played
            if (!noMoves) {
                this.view_.messagePlayerMove(move, this.currPlayer_.getName());
            }

            // switch players
            HumanPlayer temp = this.currPlayer_;
            this.currPlayer_ = this.oppPlayer_;
            this.oppPlayer_ = temp;
        }
        this.showWinner();
    }

    /**
     * Returns winner of game.
     * @return player who won this game
     */
    public void showWinner() {
        if (this.currPlayer_.getScore() > this.oppPlayer_.getScore()) {
            this.view_.messageWinner(this.currPlayer_.getName());
        } else if (this.currPlayer_.getScore() < this.oppPlayer_.getScore()) {
            this.view_.messageWinner(this.oppPlayer_.getName());
        } else {
            this.view_.showMessage("Game over! Tie!! Players have equal scores.");
        }

    }
}
