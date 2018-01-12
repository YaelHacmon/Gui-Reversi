package reversiapp;

/*
 * Roni Fultheim, ID: 313465965
 * Yael Hacmon, ID: 313297897
 * HumanPlayer.cpp
 */
public class HumanPlayer extends Player {

    HumanPlayer(Player other) {
        super(other);
    }

    public HumanPlayer(String name, ElementInBoard c) {
        super(name, c);
    }

    public Position getNextMove(ViewGame view, StandardMoveLogic logic, Board board, Player other) {
        // ask for move
        view.showMessage("Please enter your moves row,col:");

        // return choice of move
        return view.getMoveFromUser();
    }

    @Override
    public Player clone() throws CloneNotSupportedException {
        return new HumanPlayer(this);
    }

}
