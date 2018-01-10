package reversiapp;

import java.util.ArrayList;

/*
 * Yael Hacmon, ID 313597897
 * Roni Fultheim, ID 313465965
 */
public interface ViewGame {

	/**
	 * Represents the view of the game, handles any interaction with the player.
	 * All functions are pure virtual, seeing as implementation depends on viewing tools (console, GUI, etc.)
	 */
	
	// present the board
	public void printBoard(ElementInBoard [][] board, int sizeOfBoard);

	// message to switch turns
	public void messageForTurn (String curPlayer);

	// message of possible moves
	public void messagePossibleMoves(ArrayList<Position> possibleMoves);

	// display the player's last move
	public void messagePlayerMove(Position pointToDisplay, String curPlayer);

	// message who is the winner
	public void messageWinner(String winPlayer);

	//show any type of message
	public void showMessage(String stringToShow);

	//shows switching turns message and waits for any key press
	public void messageSwitchTurns();

	//gets move from outside user of game
	Position getMoveFromUser();

	/**
	 * Shows the options in the vector by option's index, and returns user's choice.
	 *
	 * Index 0 should be menu's title, and other indexes should hold the matching message for the option.
	 * Messages should fit the format: "To MESSAGE, press INDEX"
	 */
	public int presentMenu(ArrayList<String> menuOpps);

}
