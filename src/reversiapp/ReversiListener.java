package reversiapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Listens to the game, and observes the changes. Holds the lables that should be changed
 */
public class ReversiListener {
    @FXML
    private Label currPlayerText;

    @FXML
    private Label whitePlayerScoreText;

    @FXML
    private Label blackPlayerScoreText;

    /**
     * Creates listener with given labels
     * @param currPlayerText label of current player
     * @param whitePlayerScoreText label of "X" player's score
     * @param blackPlayerScoreText label of "O" player's score
     */
    public ReversiListener(Label currPlayerText, Label whitePlayerScoreText, Label blackPlayerScoreText) {
        this.currPlayerText = currPlayerText;
        this.whitePlayerScoreText = whitePlayerScoreText;
        this.blackPlayerScoreText = blackPlayerScoreText;
    }

    /**
     * Updates current playing player
     * @param player name
     */
    public void changeCurrentPlayer(String player) {
        this.currPlayerText.setText(player);
    }

    /**
     * Updates score of O (second) player
     * @param score new score
     */
    public void changeOPlayerScore(int score) {
        this.whitePlayerScoreText.setText(Integer.toString(score));
    }

    /**
     * Updates score of X (first) player
     * @param score new score
     */
    public void changeXPlayerScore(int score) {
        this.blackPlayerScoreText.setText(Integer.toString(score));
    }

}
