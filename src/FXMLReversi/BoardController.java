package FXMLReversi;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import reversiapp.Board;
import reversiapp.ElementInBoard;
import reversiapp.GUIAdapter;
import reversiapp.Position;

public class BoardController extends GridPane {

    // TODO- CONSTRUCTOR

    private final GUIAdapter adapter;

    // image to every cell
    private final ImageView[][] Cells;

    private final int size;

    private boolean firstTime = true;

    private Board lastBoard;size=lastBoard.getSize;

    public void draw(Board board) {
        this.lastBoard = board;
        this.draw();
    }

    public void draw() {
        int height;
        int width;

        int cellHeight = height / this.size;
        int cellWidth = width / this.size;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                ElementInBoard currCell = this.lastBoard.getCell(new Position(i, j));
                if (this.firstTime) {
                    int row = i;
                    int col = j;
                    this.Cells[i][j] = new ImageView();

                    // TODO

                }
                ;
            }
        }
        this.firstTime = false;
    }

    // TODO
    public Position calcMouseClick(MouseEvent event) {

    }
}