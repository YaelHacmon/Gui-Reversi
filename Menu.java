package reversiapp;

import java.util.ArrayList;

/*
 * Yael Hacmon, ID 313597897
 * Roni Fultheim, ID 313465965
 */

public class Menu {

	
	private ArrayList<String> playerOptions_;
	
	public Menu() {
		//initialize messages to user as strings in the player options string vector
		playerOptions_.add("Choose white player's type:"); //index 0
		playerOptions_.add("play against a human player"); //index 1
	}


	public Player getPlayerByUserChoice(ViewGame view) {
	
		int choice = view.presentMenu(playerOptions_);
	
		//create and return a player type according to user's choice TODO - should return by reference or as pointer?
		switch(choice) {
			case 1:
				return new HumanPlayer("O", ElementInBoard.WHITE);
		
			default:
				view.showMessage("Problem creating players");
				break;
		}
		return new HumanPlayer("O",  ElementInBoard.WHITE);
	}
}
