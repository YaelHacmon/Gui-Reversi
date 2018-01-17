package reversiap;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class BoardController extends GridPane {
	private Board board;

	private Color xColor;

	private Color oColor;

	private GameManager manager;

	// TODO- CONSTRUCTOR
	public BoardController(Board b, String pathX, String pathO, GameManager gm) {
		this.board = b;
		this.xColor = this.parse(pathX);
		this.oColor = this.parse(pathO);
		this.manager = gm;
		
		this.setOnMouseClicked(event -> this.calcMouseClick(event));
	}
	
	

	public void draw() {
		this.getChildren().clear();
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		int cellHeight = height / this.board.getSize();
		int cellWidth = width / this.board.getSize();
		//draw the cells of the board
		for (int i = 0; i < this.board.getSize(); i++){
			for (int j = 0; j < this.board.getSize(); j++){
				Rectangle rect = new Rectangle(cellWidth,cellHeight);
				rect.setFill(Color.BEIGE);
				rect.setStroke(Color.BLACK);

				this.add(rect, j, i);
			}
		}
		//draw ellipse if there is 'x' or 'o' on the board
		for (int i = 0; i < this.board.getSize(); i++){
			for (int j = 0; j < this.board.getSize(); j++){
				if (this.board.isCellBlack(i, j)) {
					Ellipse e = new Ellipse(cellWidth/2,cellHeight/2);
					e.setFill(this.xColor);
					this.add(e, j, i);
				} else if (this.board.isCellWhite(i, j)) {
					Ellipse e = new Ellipse(cellWidth/2,cellHeight/2);
					e.setFill(this.oColor);
					this.add(e, j, i);
				}
			}
		}
	}

	/**
	 * Handles the event of a player clicking on the board.
	 * @param event
	 */
	public void calcMouseClick(MouseEvent event) {
		System.out.println("inside");
		
		int height = (int)this.getPrefHeight();
		int width = (int)this.getPrefWidth();
		
		int cellHeight = height / this.board.getSize();
		int cellWidth = width / this.board.getSize();
		
		//calculate and cast to int
		int column = (int)event.getX()/cellWidth;
		int row = (int)event.getY()/cellHeight;
		
		boolean gameContinues = this.manager.playTurn(new Position(row, column));

		if (!gameContinues) {
			Alert.display("Game over!", this.manager.winner());
			// TODO - return to menu?
			System.exit(0);
		}
	}
	
	/**
	 * Parses the string from color name into color object
	 * @param c sting with color name
	 * @return color represnted
	 */
	private Color parse(String c) {
		switch (c) {
		case "Black":
			return Color.BLACK;
		case "White":
			return Color.WHITE;
		case "Red":
			return Color.RED;
		case "Yellow":
			return Color.YELLOW;
		case "Green":
			return Color.GREEN;
		case "Blue":
			return Color.BLUE;
		default:
			break;
		}
		return null;
	}
}