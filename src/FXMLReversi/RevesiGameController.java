package FXMLReversi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import reversiapp.Board;
import reversiapp.GUIAdapter;

public class RevesiGameController implements Initializable {
    @FXML
    private HBox root;

    @FXML
    private GridPane board;

    @FXML
    private Text currPlayerText;

    @FXML
    private Text whitePlayerScoreText;

    @FXML
    private Text blackPlayerScoreText;

    private BoardController boardController;

    // TODO
    private GUIAdapter adapter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.boardController = new BoardController();
        this.boardController.setPadding(new Insets(10, 10, 10, 10));
        this.boardController.setPrefHeight(500);
        this.boardController.setPrefWidth(500);
        this.board.getChildren().add(0, this.boardController);

        this.adapter = GUIAdapter.getInstance(true);

        this.root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 240 - 20;
            this.boardController.setPrefWidth(boardNewWidth);
            this.boardController.setPadding(new Insets(10, 10, 10, 10));
            this.boardController.draw();
        });

        this.root.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewHeight = newValue.doubleValue() - 20;
            this.boardController.setPrefHeight(boardNewHeight);
            this.boardController.setPadding(new Insets(10, 10, 10, 10));
            this.boardController.draw();
        });
    }

    public void redraw(Board b) {
        this.boardController.draw(b);
    }

    public void changeCurrentPlayer(String player) {
        this.currPlayerText.setText(player);
    }

    public void changeWhitePlayerScore(String score) {
        this.whitePlayerScoreText.setText(score);
    }

    public void changeBlackPlayerScore(String score) {
        this.blackPlayerScoreText.setText(score);
    }
}
