package reversiapp;

import java.util.ArrayList;

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
    }

    static void changeDown(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (row > 5 - 2) {
            return;
        }
        int index = row + 1;
        Position loc = new Position(index, col);
        while (index < 5 && board.getCell(loc) == oposite) {
            index++;
        }
        if (index < 5 && board.getCell(loc) == player) {
            // change the squares
            for (int i = index - 1; i > row; i--) {
                board.makeInColor(player, i, col);
            }
        }
    }

    static void changeRight(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (col > 5 - 2) {
            return;
        }
        int index = col + 1;
        Position loc = new Position(row, index);
        while (index < 5 && board.getCell(loc) == oposite) {
            index++;
        }
        if (index < 5 && board.getCell(loc) == player) {
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
    }

    static void changeUpLeft(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
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
    }

    static void changeDownLeft(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (col < 2 || row > 5 - 2) {
            return;
        }
        int indexCol = col - 1;
        int indexRow = row + 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol >= 0 && indexRow != 5 && board.getCell(loc) == oposite) {
            indexCol--;
            indexRow++;
        }
        if (indexCol >= 0 && indexRow != 5 && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow - 1, j = indexCol + 1; i > row && j < col; i--, j++) {
                board.makeInColor(player, i, j);
            }
        }
    }

    static void changeUpRight(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (col > 5 - 2 || row < 2) {
            return;
        }
        int indexCol = col + 1;
        int indexRow = row - 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol != 5 && indexRow >= 0 && board.getCell(loc) == oposite) {
            indexCol++;
            indexRow--;
        }
        if (indexCol != 5 && indexRow >= 0 && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow + 1, j = indexCol - 1; i < row && j > col; i++, j--) {
                board.makeInColor(player, i, j);
            }
        }
    }

    static void changeDownRight(int row, int col, Board board, ElementInBoard player, ElementInBoard oposite) {
        if (col > 5 - 2 || row > 5 - 2) {
            return;
        }
        int indexCol = col + 1;
        int indexRow = row + 1;
        Position loc = new Position(indexRow, indexCol);
        while (indexCol != 5 && indexRow != 5 && board.getCell(loc) == oposite) {
            indexCol++;
            indexRow++;
        }
        if (indexCol != 5 && indexRow != 5 && board.getCell(loc) == player) {
            // change the squares
            for (int i = indexRow - 1, j = indexCol - 1; i > row && j > col; i--, j--) {
                board.makeInColor(player, i, j);
            }
        }
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
        // copy vector by reference to avoid recalling getter function
        ArrayList<Position> possibleMoves = player.getPossibleMoves();

        // go over list
        for (int i = 0; i < possibleMoves.size(); i++) {
            // if move's location is equal to a possible move's location - it is allowed
            if (possibleMoves.get(i) == move) {
                return true;
            }
        }
        // otherwise, given move is not a possible move for player - and therefor is not allowed
        return false;
    }

    /**
     * Updates the list of possible moves for the given player on the given board.
     *
     * @param player player of which to update moves
     * @param board  board on which game is played
     */
    public void updateMoveOptions(Player player, Board board) {

        ArrayList<Position> possible = new ArrayList<Position>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (this.checkCanMove(i, j, board)) {
                    possible.add(new Position(i, j));
                }
            }
        }
        player.updatePossibleMoves(possible);
    }

    /***
     * check the square is empty and at least one not empty square around
     * @param row
     * @param col
     * @return
     */
    private boolean checkCanMove(int row, int col, Board board) {
        // the current square is empty
        if (board.isCellEmpty(row, col)) {
            return false;
        }
        // at least one empty square around
        if (row + 1 < 5 && board.isCellEmpty(row + 1, col)) {
            return true;
        }
        if (col - 1 >= 0 && board.isCellEmpty(row, col - 1)) {
            return true;
        }
        if (row - 1 >= 0 && board.isCellEmpty(row - 1, col)) {
            return true;
        }
        if (col + 1 < 5 && board.isCellEmpty(row, col + 1)) {
            return true;
        }
        return false;
    }
};
