package JavaFX.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import personnel.Employe;
import personnel.GestionPersonnel;

public class RootControl {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private PasswordField password;
	@FXML
	private Label warning;
	@FXML
	private Button button, back, acces;

	private final GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private Employe root = gestionPersonnel.getRoot();

	public GestionPersonnel getGestion() {
		return gestionPersonnel;
	}

	@FXML
	private void BackButton() throws Exception {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../Graphique/Accueil.fxml"));
		Stage window = (Stage) back.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));

	}

	@FXML
	private void ToucheRetour(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ESCAPE) {
			BackButton();
		}

	}

}