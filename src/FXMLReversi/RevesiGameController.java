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
import reversiapp.ViewGame;

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

    private BoardController boardController;

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
        // get just color from path - for labels
        String colorX = playerX.substring(11, playerX.length() - 5);
        String colorO = playerO.substring(11, playerO.length() - 5);

        // intialize labels
        this.currPlayerText.setText("Current player: " + colorX);
        this.blackPlayerScoreText.setText(colorX + " player score: 2");
        this.whitePlayerScoreText.setText(colorO + " player score: 2");

        // create listener with the given labels
        ReversiListener listener = new ReversiListener(this.currPlayerText, this.whitePlayerScoreText,
                this.blackPlayerScoreText);

        // CREATE GAME
        // TODO - add listener to gameManager/board (i think board)
        // TODO - change MazeBoard to Cont

        // allocate menu, board, logic and view on stack - currently there is only one type of logic and view, no need
        // to allocate dynamically
        Board board = new Board(bSize);

        StandardMoveLogic ml = new StandardMoveLogic();

        ViewGame view = null;

        // allocate dynamically due to using abstract base type
        // first player is always the human player and is black
        HumanPlayer player1 = new HumanPlayer("X", ElementInBoard.BLACK);

        // start game - opening message
        view.showMessage("Welcome to Reversi!");

        HumanPlayer player2 = new HumanPlayer("O", ElementInBoard.WHITE);

        // allocate game manager on stack, sending abstract types by pointer and actual types by reference
        GameManager game_manger = new GameManager(view, board, player1, player2, ml);

        // play game
        game_manger.playGame();

        // TODO - change to boardController
        BoardController boardControl = new BoardController(board);
        boardControl.setPrefWidth(400);
        boardControl.setPrefHeight(400);
        this.root.getChildren().add(0, boardControl);
        boardControl.draw();

    }
}
