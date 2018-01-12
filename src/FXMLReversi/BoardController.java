package FXMLReversi;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import reversiapp.Board;
import reversiapp.Position;

public class BoardController extends GridPane {
    private Board board;

    private String pathXColor;

    private String pathOColor;

    // TODO- CONSTRUCTOR
    public BoardController(Board b, String pathX, String pathO) {
        this.board = b;
        this.pathXColor = pathX;
        this.pathOColor = pathO;
    }

    public void draw() {
        this.getChildren().clear();

        // go over board and draw according to current board state
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                if (!this.board.isCellEmpty(i, j)) {
                    if (this.board.isCellEmpty(i, j)) {
                        this.add(new ImageView(this.getClass().getResource("../../pics/Empty.png").toExternalForm()), j,
                                i);
                    } else if (this.board.isCellBlack(i, j)) {
                        this.add(new ImageView(this.getClass().getResource(this.pathXColor).toExternalForm()), j, i);
                    } else {
                        this.add(new ImageView(this.getClass().getResource(this.pathOColor).toExternalForm()), j, i);
                    }
                }
            }
        }
    }

    // TODO
    public Position calcMouseClick(MouseEvent event) {

    }
}