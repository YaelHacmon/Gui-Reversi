package FXMLReversi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import reversiapp.Board;
import reversiapp.ElementInBoard;
import reversiapp.GameManager;
import reversiapp.HumanPlayer;
import reversiapp.ReversiListener;
import reversiapp.StandardMoveLogic;

public class RevesiGameController implements Initializable {
    @FXML
    private HBox root;

    @FXML
    private GridPane board;

    @FXML
    private Label currPlayerText;

    @FXML
    private Label whitePlayerScoreText;

    @FXML
    private Label blackPlayerScoreText;

    @FXML
    private Label messages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // open file to get settings
        // create FileReader from given path - must use try-catch (exception thrown if file does not exist)
        String size = "";
        String color1 = "";
        String color2 = "";

        try {
            FileReader fReader = new FileReader("defaultSettings.txt");

            // create buffered reader for easy file reading
            BufferedReader br = new BufferedReader(fReader);

            br.readLine().trim();

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

        this.createGame(Integer.parseInt(size), color1, color2);

        /*
         * this.boardController = new BoardController(); this.boardController.setPadding(new Insets(10, 10, 10, 10));
         * this.boardController.setPrefHeight(500); this.boardController.setPrefWidth(500);
         * this.board.getChildren().add(0, this.boardController);
         *
         * this.root.widthProperty().addListener((observable, oldValue, newValue) -> { double boardNewWidth =
         * newValue.doubleValue() - 240 - 20; this.boardController.setPrefWidth(boardNewWidth);
         * this.boardController.setPadding(new Insets(10, 10, 10, 10)); this.boardController.draw(); });
         *
         * this.root.heightProperty().addListener((observable, oldValue, newValue) -> { double boardNewHeight =
         * newValue.doubleValue() - 20; this.boardController.setPrefHeight(boardNewHeight);
         * this.boardController.setPadding(new Insets(10, 10, 10, 10)); this.boardController.draw(); });
         */
    }

    /*
     * public void redraw(Board b) { this.boardController.draw(b); }
     *
     * public void changeCurrentPlayer(String player) { this.currPlayerText.setText(player); }
     *
     * public void changeWhitePlayerScore(String score) { this.whitePlayerScoreText.setText(score); }
     *
     * public void changeBlackPlayerScore(String score) { this.blackPlayerScoreText.setText(score); }
     */

    private void createGame(int bSize, String playerX, String playerO) {
        // get just color from path - for labels (pics/COLOR.png) -> COLOR
        String colorX = playerX.substring(5, playerX.length() - 5);
        String colorO = playerO.substring(5, playerO.length() - 5);

        // intialize labels
        this.currPlayerText.setText("Current player: " + colorX);
        this.blackPlayerScoreText.setText(colorX + " player score: 2");
        this.whitePlayerScoreText.setText(colorO + " player score: 2");

        // create listener with the given labels
        ReversiListener listener = new ReversiListener(this.currPlayerText, this.whitePlayerScoreText,
                this.blackPlayerScoreText, this.messages);

        // CREATE GAME
        // TODO - add listener to gameManager/board (i think game manager)

        // create board by given size
        Board board = new Board(bSize);

        // create move logic
        StandardMoveLogic ml = new StandardMoveLogic();

        // allocate dynamically due to using abstract base type
        // first player is always the human player and is black
        HumanPlayer player1 = new HumanPlayer(colorX, ElementInBoard.BLACK);

        HumanPlayer player2 = new HumanPlayer(colorO, ElementInBoard.WHITE);

        // allocate game manager on stack, sending abstract types by pointer and actual types by reference
        GameManager game_manger = new GameManager(board, player1, player2, ml, listener);

        // create board controller to show board
        BoardController boardController = new BoardController(board, playerX, playerO, game_manger);
        boardController.setPrefWidth(400);
        boardController.setPrefHeight(400);
        this.root.getChildren().add(0, boardController);
    }
}
