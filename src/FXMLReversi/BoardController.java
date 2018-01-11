package FXMLReversi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import reversiapp.Board;
import reversiapp.ElementInBoard;
import reversiapp.GUIAdapter;
import reversiapp.Position;

public class BoardController extends GridPane {
    private final GUIAdapter adapter; // TODO - held or singleton?? not clear

    // image to every cell
    private final ImageView[][] Cells;

    private final int boardSize;

    private boolean firstTime = true;

    private Board lastBoard;

    private Color firstPlayerColor;

    private Color secondPlayerColor;

    // TODO- CONSTRUCTOR
    public BoardController(String configFilePath) {
        // open file to get settings
        // create FileReader from given path - must use try-catch (exception thrown if file does not exist)
        String size = "";
        String color1 = "";
        String color2 = "";

        try {
            FileReader fReader = new FileReader(configFilePath);

            // create buffered reader for easy file reading
            BufferedReader br = new BufferedReader(fReader);

            // reading algorithm type
            String algorithmType = br.readLine().trim();

            // reading size of board
            size = br.readLine();

            // reading color of first player
            color1 = br.readLine();

            // reading color of second player
            color2 = br.readLine();

            // finished reading - close reader
            br.close();

        } catch (FileNotFoundException e) {
            // catch file not found exception
            System.out.println("File was not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occured while reading input file");
            e.printStackTrace();
        }

        this.boardSize = Integer.parseInt(size);
        this.firstPlayerColor = this.getColor(color1);
        this.secondPlayerColor = this.getColor(color2);
    }

    /**
     * Parses given string into color type (Colors: blue, green, red, yellow, white, black, orange, pink, purple);
     * @param s string to turn into color
     * @return Color represented by string
     */
    private Color getColor(String s) {
        switch (s) {
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "red":
                return Color.RED;
            case "yellow":
                return Color.YELLOW;
            case "white":
                return Color.WHITE;
            case "black":
                return Color.BLACK;
            case "orange":
                return Color.ORANGE;
            case "pink":
                return Color.PINK;
            case "purple":
                return Color.PURPLE;
            default:
                // this should never happen, as color format is checked when entered
                System.out.println("problem in parsing color, no such color format");
                System.exit(0);
        }
        return null;
    }

    public void draw(Board board) {
        this.lastBoard = board;
        this.draw();
    }

    public void draw() {
        int height;
        int width;

        int cellHeight = height / this.boardSize;
        int cellWidth = width / this.boardSize;

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
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