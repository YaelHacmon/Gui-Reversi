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

	//TODO- CONSTRUCTOR

	private final GUIAdapter adapter;
	//image to every cell
	private final ImageView[][] Cells;
	private final int size;

	private boolean firstTime = true;

	private Board lastBoard;
	size = lastBoard.getSize;

	public void draw(Board board) {
		lastBoard = board;   
		this.draw();
	}

	public void draw() {
		int height; 
		int width; 

		int cellHeight = height / size;
		int cellWidth = width / size;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ElementInBoard currCell = lastBoard.getCell(new Position(i, j));
				if (firstTime) {
					int row = i;
					int col = j;
					Cells[i][j] = new ImageView();

					//TODO
					
				};
			}
		}
		firstTime = false;
	}
	
	//TODO
	public Position calcMouseClick(MouseEvent event) {

	}
}