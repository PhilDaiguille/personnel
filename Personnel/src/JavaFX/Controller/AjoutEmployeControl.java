package JavaFX.Controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import personnel.Ligue;

public class AjoutEmployeControl {
	Ligue ligue;
	@FXML
	private DatePicker DateArrivee, DateDepart;

	@FXML
	private Button back, confirmer, Supprimer;

	@FXML
	private TextField nom, prenom, mail;

	@FXML
	private PasswordField mdp;

	private String Nom, Prenom, password, Mail;
	private LocalDate dateArrivee, dateDepart;

	@FXML
	private void AjouterEmploye() throws Exception {
		Nom = nom.getText();
		Prenom = prenom.getText();
		password = mdp.getText();
		Mail = mail.getText();
		dateArrivee = DateArrivee.getValue();
		dateDepart = DateDepart.getValue();
		ligue = LigueControl.getLigue();

		if (!(Nom.equals("") && Prenom.equals("") && Mail.equals("") && password.equals(""))) {

			ligue.addEmploye(Nom, Prenom, Mail, password, dateArrivee, dateDepart);
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../Graphique/Employe.fxml"));
			Stage window = (Stage) back.getScene().getWindow();
			window.setScene(new Scene(root, 800, 600));
		} else {
			showAlertWithoutHeaderText();
		}

	}

	@FXML
	private void BackButton() throws Exception {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../Graphique/Employe.fxml"));
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
