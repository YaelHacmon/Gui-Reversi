package reversiapp;

// * main.cpp
// * Implementation of main function - runs program
// */

class OldMain {

    private static final ElementInBoard BLACK = null;

    private static final ElementInBoard WHITE = null;

    public static void main() {
        // allocate menu, board, logic and view on stack - currently there is only one type of logic and view, no need
        // to allocate dynamically
        Board board = new Board(8);
        StandardMoveLogic ml = new StandardMoveLogic();
        ViewGame view = null;

        // allocate dynamically due to using abstract base type
        // first player is always the human player and is black
        HumanPlayer player1 = new HumanPlayer("X", BLACK);

        // start game - opening message
        view.showMessage("Welcome to Reversi!");

        HumanPlayer player2 = new HumanPlayer("O", WHITE);

        // allocate game manager on stack, sending abstract types by pointer and actual types by reference
        GameManager game_manger = new GameManager(view, board, player1, player2, ml);

        // play game
        game_manger.playGame();
    }

}