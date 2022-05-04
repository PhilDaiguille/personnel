package JavaFX.Controller;

import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import personnel.Ligue;

public class ModifEmployeControl implements Initializable {
	Ligue ligue;
	@FXML
	private DatePicker DateArrivee, DateDepart;

	@FXML
	private Button back, confirmer;

	@FXML
	private TextField nom, prenom, mail, mdp;

	private LocalDate dateArrivee, dateDepart;

	@Override
	public void initialize(java.net.URL url, ResourceBundle ressource) {

		ligue = LigueControl.getLigue();

	}

	@FXML
	private void ModifierEmploye() throws Exception {

	}

	@FXML
	private void BackButton() throws Exception {
		Parent root;
		root = FXMLLoader
				.load(getClass().getResource("../Graphique/Employe.fxml"));
		Stage window = (Stage) back.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));
	}

	@FXML
	private void ToucheRetour(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ESCAPE) {
			BackButton();
		}

	}

	@FXML
	private void showAlertWithoutHeaderText() throws Exception {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning alert");

		alert.setHeaderText(null);
		alert.setContentText("Erreur champs libre");

		alert.showAndWait();
	}

}
