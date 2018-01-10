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

	//board of game
	private Board board_;
	//Starting player is black by default.
	private HumanPlayer currPlayer_ ;
	//Opposite player is black by default.
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
	public GameManager(ViewGame view, Board b, HumanPlayer black, HumanPlayer white, StandardMoveLogic log){
		this.board_ = b;
		this.currPlayer_ = black;
		this.oppPlayer_ = white;
		this.view_ = view;
	}

	/**
	 * Plays game with the given board, players and logic.
	 */
	public void playGame(){
		//declare flag - in first turn game has not been played, current player has moves
		boolean noMoves = false;

		view_.showMessage("Current board:");
		view_.printBoard(board_.getBoard(), board_.getSize());

		/* General explanation - First, build a list containing all the
		 * empty cells on the board. then, checking what might be a possible
		 * move dor the player, and putting all the options into a vector.
		 * The user select a point, and the board update acoording to the
		 *  selected point. 
		 */
		//while game is not over - keep playing
		while (!board_.isBoardFull())
		{
			//display current turn
			view_.messageForTurn(currPlayer_.getName());

			//initialize moves for black and white players
			logic_.updateMoveOptions(currPlayer_, board_);

			//declare move here - so we can show move later
			Position move = new Position(-1,-1);

			//if current player can play his turn
			if (logic_.canPlayTurn(currPlayer_)) {
				//show possible moves
				view_.messagePossibleMoves(currPlayer_.getPossibleMoves());

				//get next player's move
				move = currPlayer_.getNextMove(view_, logic_, board_,oppPlayer_);

				//check that move is allowed
				//while move isn't legal - get another move from player
				while (!logic_.isMoveAllowed(move, currPlayer_))
				{
					view_.showMessage("Illegal move, try again.");
					move = currPlayer_.getNextMove(view_, logic_, board_,oppPlayer_);
				}

				//call logic to play move
				logic_.playMove(move, currPlayer_, board_, oppPlayer_);

				//update flag
				noMoves = false;
			}
			//if current player cannot play his turn
			else
			{
				//if the second player cannot play - show message and switch turns
				if (!noMoves) {
					view_.messageSwitchTurns();
					noMoves = true;

				}
				else
				{
					//if both players did not play - game is over, there are no more moves left in game
					view_.showMessage("No possible moves for both players.");
					break;
				}
			}

			//show board and last moves
			view_.showMessage("\nCurrent board:");
			view_.printBoard(board_.getBoard(), board_.getSize());
			//message of last turn - if was played
			if (!noMoves) {
				view_.messagePlayerMove(move, currPlayer_.getName());
			}

			//switch players
			HumanPlayer temp = currPlayer_;
			currPlayer_ = oppPlayer_;
			oppPlayer_ = temp;
		}
		showWinner();
	}

	/**
	 * Returns winner of game.
	 * @return player who won this game
	 */
	public void showWinner(){
		if (currPlayer_.getScore() > oppPlayer_.getScore()) {
			view_.messageWinner(currPlayer_.getName());
		}
		else if (currPlayer_.getScore() < oppPlayer_.getScore())
		{
			view_.messageWinner(oppPlayer_.getName());
		}
		else
		{
			view_.showMessage("Game over! Tie!! Players have equal scores.");
		}

	}
}



