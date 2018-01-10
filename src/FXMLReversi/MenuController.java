package FXMLReversi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuController implements Initializable {
	@FXML
	private Button startGameBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private Button exitBtn;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		exitBtn.setOnAction((event) -> {
			System.exit(0);
		});
	}

	@FXML
	void changeSettings(ActionEvent event) {
		try {
			FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("SettingsFXML.fxml"));
			Parent settingsParent = (Parent) settingsLoader.load();
			Scene settingsScene = new Scene(settingsParent);
			Stage theStage = (Stage) settingsBtn.getScene().getWindow();
			theStage.setScene(settingsScene);
		}   catch (Exception ex) {
			System.out.println("ChangeSettings error:");
			ex.printStackTrace();
		}
	}

	@FXML
	void startGame(ActionEvent event) {
		try {
			FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameFXML.fxml"));
			Parent gameParent = (Parent) gameLoader.load();
			Scene gameScene = new Scene(gameParent);
			Stage theStage = (Stage) startGameBtn.getScene().getWindow();
			theStage.setScene(gameScene);
		}   catch (Exception ex) {
			System.out.println("ChangeSettings error:");
			ex.printStackTrace();
		}
		Thread gameThread = new Thread(){
			@Override
			//TODO
			public void run() {

			}
		};
		gameThread.start();
	}

}