package FXMLReversi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SettingsController implements Initializable {
    @FXML
    private TextField boardSize;

    @FXML
    private TextField playerColor; // TODO - currently in rgb format... try for open list options or something

    @FXML
    private TextField opponentColor; // TODO - currently in rgb format... try for open list options or something

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    void save(ActionEvent event) {
        // TODO - save to file
    }
}