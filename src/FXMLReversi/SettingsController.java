package FXMLReversi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
    @FXML
    private TextField boardSize;

    @FXML
    private TextField playerColor;

    @FXML
    private TextField opponentColor;

    @FXML
    private Text messageText;

    @FXML
    private Button exitBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // define returning to menu scene when exit button is pressed
        this.exitBtn.setOnAction((event) -> {
            // return to menu scene
            try {
                // load scene
                FXMLLoader settingsLoader = new FXMLLoader(this.getClass().getResource("MenuFXML.fxml"));
                Parent settingsParent = (Parent) settingsLoader.load();
                Scene settingsScene = new Scene(settingsParent);
                // set scene
                Stage theStage = (Stage) this.exitBtn.getScene().getWindow();
                theStage.setScene(settingsScene);
                // show stage
                theStage.show();
            } catch (Exception ex) {
                System.out.println("Return to menu error:");
                ex.printStackTrace();
            }
        });

        // create tooltip to notify of color format
        Tooltip t = new Tooltip("Colors: Blue, Green, Red, Yellow, White, Black");
        // set tooltip to both color fields
        this.playerColor.setTooltip(t);
        this.opponentColor.setTooltip(t);

    }

    @FXML
    protected void save(ActionEvent event) {
        String playerC = this.playerColor.getText();
        String oponnentC = this.opponentColor.getText();
        String bSize = this.boardSize.getText();
        int size = Integer.parseInt(bSize);

        if (size % 2 == 1) {
            this.messageText.setText("Board size must even, please try again");
            this.messageText.setFill(Color.RED);
        } else if (size > 20 || size < 4) {
            this.messageText.setText("Board size must be between 4 and 20, please try again");
            this.messageText.setFill(Color.RED);
        } else if (!this.isColor(playerC) || !this.isColor(oponnentC)) {
            this.messageText.setText("Unrecognized color format, please try again");
            this.messageText.setFill(Color.RED);
        } else if (playerC.equals(oponnentC)) {
            this.messageText.setText("Players' colors must be different, please try again");
            this.messageText.setFill(Color.RED);
        } else {
            // create string to save, format: boardSize \n ../../pics/playerColor.png \n ../../pics/opponentColor.png
            String output = this.boardSize + "\n../../pics/" + this.playerColor + ".png\n../../pics/"
                    + this.opponentColor + ".png";

            // save to file
            // if succeeded - show message
            if (this.writeOutputString(output, "defaultSettings.txt")) {
                this.messageText.setText("Saved successfully");
                this.messageText.setFill(Color.BLUE);
            } else {
                this.messageText.setText("Saving failed, please try again");
                this.messageText.setFill(Color.RED);
            }
        }
    }

    /**
     * Verifies if a given string is in correct color format (if represents an existing color)
     * @param s string to check
     * @return
     */
    protected boolean isColor(String s) {
        // return true if given string is one of the possible 7 colors
        return (s.equals("Blue") || s.equals("Green") || s.equals("Red") || s.equals("Yellow") || s.equals("White")
                || s.equals("Black"));
    }

    /**
     * Writes string to the given output file path.
     * @param s string to write
     * @param outputPath path of output file
     * @return true if string was successfully written, false otherwise
     */
    private boolean writeOutputString(String s, String outputPath) {
        // write output string to given file path
        File file = null;
        PrintWriter printOut = null;
        boolean flag = true; // assume success
        try {
            // creating output file
            file = new File(outputPath);

            // if file does not exist - create new file
            if (!file.exists()) {
                file.createNewFile();
            }

            // create PrintWriter to write easily
            // pass true in creation of file output stream to overwrite the current file
            printOut = new PrintWriter(new FileOutputStream(file, true));

            // write to file - does not throw exceptions
            printOut.write(s);
            // check if an error occurred, while it is so output a message and retry to write file
            if (printOut.checkError()) {
                flag = false;
            }

        } catch (NullPointerException e) { // TODO - what should be done with these println?
            // catch if path was null
            flag = false;
            System.out.println("File path is null");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // catch if PrintWriter could not find path
            flag = false;
            System.out.println("File was not found");
            e.printStackTrace();
        } catch (IOException e) {
            // catch an IO exception in file creation
            flag = false;
            System.out.println("Could not create output file");
            e.printStackTrace();
        } finally {
            // close print writer at any case (releases all associated system resources) - does not throw exceptions
            if (printOut != null) {
                printOut.close();
            }
        }

        // return message according to flag of success
        return flag;
    }

}