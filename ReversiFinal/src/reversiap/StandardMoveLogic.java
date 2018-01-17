package reversiap;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Yael Hacmon, ID 313597897
 * Roni Fultheim, ID 313465965
 */

/**
 * Represent the standard move logic of a Reversi game.
 * A move is allowed only if it changes square colors, and square colors are changed if the new
 * move blocks a range of squares with the opposite color between move and a same-colored square.
 */
public class StandardMoveLogic {

    /**
     * Plays the given move for the given player on given board, according to the logic decided.
     * Changes must be made to board.
     * Derived from parent class, originally a pure virtual method.
     *
     * Plays a move by switching the color of all consequent same-colored squares from given move.
     *
     * @param move location of chosen move
     * @param player player playing move
     * @param board board on which move is played
     * @param opponent other player of game
     */
    public void playMove(Position move, Player player, Board board, Player oposite) {
        int row = move.getRow();
        int col = move.getColumn();
        ElementInBoard playerColor = player.getColor();
        ElementInBoard oppColor = oposite.getColor();
        
        System.out.println("playMove");

        /***change the others squares:***/
        changeUp(row, col, board, playerColor, oppColor);
        changeDown(row, col, board, playerColor, oppColor);
        changeLeft(row, col, board, playerColor, oppColor);
        changeRight(row, col, board, playerColor, oppColor);
        changeDownRight(row, col, board, playerColor, oppColor);
        changeDownLeft(row, col, board, playerColor, oppColor);
        changeUpLeft(row, col, board, playerColor, oppColor);
        changeUpRight(row, col, board, playerColor, oppColor);

    }

    static void changeUp(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
    	 System.out.println("playMoveUp");
        if (row < 2) {
            return;
        }
        int index = row - 1;
        Position loc = new Position(index, col);
        while (index >= 0 && board.getCell(loc) == oposite) {
            index--;
        }
        if (index >= 0 && board.getCell(loc) == player) {
            // change the squares i,col
            for (int i = index + 1; i < row; i++) {
                board.makeInColor(player, i, col);
            }
        }
        System.out.println("playMoveUp");
    }

    static void changeDown(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
    	System.out.println("playMoveDown");
        if (row > board.getSize() - 2) {
            return;
        }
        int index = row + 1;
        Position loc = new Position(index, col);
        while (index < board.getSize() && board.getCell(loc) == oposite) {
            index++;
        }
        if (index < board.getSize() && board.getCell(loc) == player) {
            // change the squares
            for (int i = index - 1; i > row; i--) {
                board.makeInColor(player, i, col);
            }
        }
        System.out.println("playMoveDown");
    }

    static void changeRight(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (col > board.getSize() - 2) {
            return;
        }
        int index = col + 1;
        Position loc = new Position(row, index);
        while (index < board.getSize() && board.getCell(loc) == oposite) {
            index++;
        }
        if (index < board.getSize() && board.getCell(loc) == player) {
            // change the squares
            for (int i = index - 1; i > col; i--) {
                board.makeInColor(player, row, i);
            }
        }
    }

    static void changeLeft(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (col < 2) {
            return;
        }
        int index = col - 1;
        Position loc = new Position(row, index);
        while (index >= 0 && board.getCell(loc) == oposite) {
            index--;
        }
        if (index >= 0 && board.getCell(loc) == player) {
            // change the squares
            for (int i = index + 1; i < col; i++) {
                board.makeInColor(player, row, i);
            }
        }
        System.out.println("playMoveDown");
    }

    static void changeUpLeft(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
    	System.out.println("playMoveUPLeft");
    	if (col < 2 || row < 2) {
            return;
        }
        int indexCol = col - 1;
        int indexRow = row - 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol >= 0 && indexRow >= 0 && board.getCell(loc) == oposite) {
            indexCol--;
            indexRow--;
        }
        if (indexCol >= 0 && indexRow >= 0 && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow + 1, j = indexCol + 1; i < row && j < col; i++, j++) {
                board.makeInColor(player, i, j);
            }
        }
        System.out.println("playMoveUPLeft");
    }

    static void changeDownLeft(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
    	System.out.println("playMoveDOWNLeft");
    	if (col < 2 || row > board.getSize() - 2) {
            return;
        }
        int indexCol = col - 1;
        int indexRow = row + 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol >= 0 && indexRow != board.getSize() && board.getCell(loc) == oposite) {
            indexCol--;
            indexRow++;
        }
        if (indexCol >= 0 && indexRow != board.getSize() && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow - 1, j = indexCol + 1; i > row && j < col; i--, j++) {
                board.makeInColor(player, i, j);
            }
        }
        System.out.println("playMoveDOWNLeft");
    }

    static void changeUpRight(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
    	System.out.println("playMoveUPRight");
    	if (col > board.getSize() - 2 || row < 2) {
            return;
        }
        int indexCol = col + 1;
        int indexRow = row - 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol != board.getSize() && indexRow >= 0 && board.getCell(loc) == oposite) {
            indexCol++;
            indexRow--;
        }
        if (indexCol != board.getSize() && indexRow >= 0 && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow + 1, j = indexCol - 1; i < row && j > col; i++, j--) {
                board.makeInColor(player, i, j);
            }
        }
        System.out.println("playMoveUPRight");
    }

    static void changeDownRight(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
    	System.out.println("playMoveDOWNRight");
    	if (col > board.getSize() - 2 || row > board.getSize() - 2) {
            return;
        }
        int indexCol = col + 1;
        int indexRow = row + 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol != board.getSize() && indexRow != board.getSize() && board.getCell(loc) == oposite) {
            indexCol++;
            indexRow++;
        }
        if (indexCol != board.getSize() && indexRow != board.getSize() && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow - 1, j = indexCol - 1; i > row && j > col; i--, j--) {
                board.makeInColor(player, i, j);
            }
        }
        System.out.println("playMoveDOWNRight");
    }

    /**
     * Checks if given player can currently play a turn.
     * A player can play if the list of player's next possible moves is not empty.
     * Even so, a derived class may need a completely different implementation, and therefore it is still virtual.
     *
     * @param player pointer to player
     * @return true if player can currently play a turn
     */
    public boolean canPlayTurn(Player player) {
        // check if player can play moves
        return player.hasPossibleMoves();
    }

    /**
     * Returns if a given move by a given player on given board is allowed.
     * A move is allowed if it is a possible move, meaning it is contained
     * in the player's possible moves vector.
     *
     * @param move location of move to check if legal
     * @param player pointer to player playing this move
     * @return true if move is legal and can be played
     */
    public boolean isMoveAllowed(Position move, Player player) {
  
        return player.getPossibleMoves().contains(move);
    }

    /**
     * Updates the list of possible moves for the given player on the given board.
     *
     * @param player player of which to update moves
     * @param board  board on which game is played
     */
    public void updateMoveOptions(Player player, Board board) {
    ElementInBoard playerColor = player.getColor();
		ArrayList<Position> moves = new ArrayList<Position>();
		
		for (int i = 0; i < board.getSize(); i++){
			for (int j = 0; j < board.getSize(); j++){
				if (board.isCellEmpty(i, j)){  // check that it's an empty cell
					// left
					if (j > 0 && !board.isCellEmpty(i, j-1) && board.compareCellColors(playerColor, new Position(i, j-1))) {   
						for (int t = 2; j-t >= 0; t++){
							if (board.compareCellColors(playerColor, new Position(i, j-t))) {
								boolean flag = true;
								Position candidate = new Position(i,j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if  (board.isCellEmpty(i, j-t)){
								break;
							}
						}
					}
					// left oblique line
					if (j > 0 && i-1 > 0 && !board.isCellEmpty(i-1, j-1) && board.compareCellColors(playerColor, new Position(i-1, j-1))) { 
						for (int t = 2; j-t >= 0 && i - t >= 0; t++) {
							if (board.compareCellColors(playerColor, new Position(i-t, j-t))) {
								boolean flag = true;
								Position candidate = new Position(i,j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i-t, j-t)){
								break;
							}
						}
					}
					if (i - 1 > 0  && !board.isCellEmpty(i-1, j) && board.compareCellColors(playerColor, new Position(i-1, j))) { // up
						for (int t = 2; i-t >= 0; t++) {
							if (board.compareCellColors(playerColor, new Position(i-t, j))) {
								boolean flag = true;
								Position candidate = new Position(i,j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i-t, j)){
								break;
							}
						}
					}
					if(j < board.getSize()  && i-1 > 0 && !board.isCellEmpty(i-1, j+1) && board.compareCellColors(playerColor, new Position(i-1, j+1))) {
						for (int t = 2; j+t < board.getSize() && i-t >= 0 ; t++) {
							if (board.compareCellColors(playerColor, new Position(i-t, j+t))) {
								boolean flag = true;
								Position candidate = new Position(i, j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i-t, j+t)){
								break;
							}
						}
					}
					if (j < board.getSize()  && !board.isCellEmpty(i, j+1) && board.compareCellColors(playerColor, new Position(i, j+1))) { // right
						for (int t = 2; j+t < board.getSize(); t++) {
							if (board.compareCellColors(playerColor, new Position(i, j+t))) {
								boolean flag = true;
								Position candidate = new Position(i,j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i, j+t)){
								break;
							}
						}
					}
					if (j < board.getSize() && i < board.getSize() && !board.isCellEmpty(i+1, j+1) && board.compareCellColors(playerColor, new Position(i+1, j+1))) {
						for (int t = 2; j + t < board.getSize() && i + t < board.getSize(); t++) {
							if (board.compareCellColors(playerColor, new Position(i+t, j+t))) {
								boolean flag = true;
								Position candidate = new Position(i, j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i+t, j+t)){
								break;
							}
						}
					}
					if ( i < board.getSize() && !board.isCellEmpty(i+1, j) && board.compareCellColors(playerColor, new Position(i+1, j))) { // down
						for (int t = 2; i+t < board.getSize(); t++) {
							if (board.compareCellColors(playerColor, new Position(i+t, j))) {
								boolean flag = true;
								Position candidate = new Position(i + 1, j+1);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i+t, j)){
								break;
							}
						}
					}
					if (j > 0 && i < board.getSize() && !board.isCellEmpty(i+1, j-1) && board.compareCellColors(playerColor, new Position(i+1, j-1))){
						for (int t = 2; j-t >= 0 && i + t < board.getSize(); t++) {
							if (board.compareCellColors(playerColor, new Position(i+t, j-t))) {
								boolean flag = true;
								Position candidate = new Position(i,j);
								if (moves.contains(candidate)) {
									flag = false;
								}
								if (flag){
									moves.add(new Position(i,j));
									break;
								}
							} else if (board.isCellEmpty(i+t, j-t)){
								break;
							}
						}
					}
				}
			}
		}
        player.updatePossibleMoves(moves);
    }

};