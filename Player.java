package reversiapp;

import java.util.ArrayList;

/*
 * Yael Hacmon, ID 313597897
 * Roni Fultheim, ID 313465965
 */
public class Player {

	private String name_;
	private ElementInBoard color_;
	private int score_;
	private ArrayList<Position> possibleMoves_;

	Player(String name, ElementInBoard c){
		this.name_ = name;
		this.color_ = c;
	}

	Player(Player other){
		this.name_ = other.getName();
		this.color_ = other.getColor();
		this.score_ = other.getScore();
		this.possibleMoves_ = other.getPossibleMoves();

	}

	public ElementInBoard getColor() {
		return color_;
	}

	public String getName() {
		return name_;
	}

	public int getScore() {
		return score_;
	}
	
	public ArrayList<Position> getPossibleMoves() {
		return possibleMoves_;
	}


	public boolean hasPossibleMoves() {
		//check if vector is empty - and return the opposite answer: true if possible moves exist, meaning vector is non-empty
		return !possibleMoves_.isEmpty();
	}


	public void updatePossibleMoves(ArrayList<Position> locs) {
		//use copy constructor to copy the given vector to data member
		//vector copies its internal elements as well - allows
		this.possibleMoves_ = new ArrayList<Position>(locs);
	}

	// if current Player "ate" the opposite Player - the opposite Player's score decreases
	public void decreaseScore (int toDecrease) {
		score_ = score_ - toDecrease;
	}

	// if current Player "ate" the opposite Player - the opposite Player's score increases
	public void increaseScore (int toIncrease) {
		score_ = score_ + toIncrease;
	}
}