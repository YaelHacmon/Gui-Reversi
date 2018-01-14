package FXMLReversi;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import reversiapp.Board;
import reversiapp.GameManager;
import reversiapp.Position;

public class BoardController extends GridPane {
    private Board board;

    private String pathXColor;

    private String pathOColor;

    private GameManager manager;

    // TODO- CONSTRUCTOR
    public BoardController(Board b, String pathX, String pathO, GameManager gm) {
        this.board = b;
        this.pathXColor = pathX;
        this.pathOColor = pathO;
        this.manager = gm;
    }

    public void draw() {
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        int cellHeight = height / this.board.getSize();
        int cellWidth = width / this.board.getSize();

        // go over board and draw according to current board state
        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {
                // if empty - draw empty image
                if (this.board.isCellEmpty(i, j)) {
                    this.add(new ImageView(this.getClass().getResource("pics/Empty.png").toExternalForm()), j, i);
                } else if (this.board.isCellBlack(i, j)) {
                    // if X player - draw path's image
                    ImageView iv = new ImageView(this.getClass().getResource(this.pathXColor).toExternalForm());
                    iv.setFitWidth(cellWidth);
                    iv.setFitHeight(cellHeight);
                    this.add(iv, j, i);
                } else {
                    // if O player - draw path's image
                    ImageView iv = new ImageView(this.getClass().getResource(this.pathOColor).toExternalForm());
                    iv.setFitWidth(cellWidth);
                    iv.setFitHeight(cellHeight);
                    this.add(iv, j, i);
                }
            }
        }
    }

    /**
     * Handles the event of a player clicking on the board.
     * @param event
     */
    public void calcMouseClick(MouseEvent event) {
        double x = event.getSceneX();
        double y = event.getSceneY();

        this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        int pressedX = (int) ((width / this.board.getSize()) * x);
        int pressedY = (int) ((width / this.board.getSize()) * y);

        boolean gameContinues = this.manager.playTurn(new Position(pressedX, pressedY));

        if (!gameContinues) {
            Alert.display("Game over!", this.manager.winner());
            // TODO - return to menu?
            System.exit(0);
        }
    }
}