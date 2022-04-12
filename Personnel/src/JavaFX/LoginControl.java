package JavaFX;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class LoginControl {
	
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
	private void handlebtn() throws Exception {		
		
		if(GestionPersonnel.getGestionPersonnel().getRoot().checkPassword(password.getText())) {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			Stage window = (Stage) button.getScene().getWindow();
			window.setScene(new Scene(root, 800, 600));
			window.setTitle("PPE - Gestion du personnel des ligues");
			window.show();
		}
		else {
			warning.setText("Mot de passe incorrect");
		}
	}
	@FXML
	private void BackButton() throws Exception {		
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Stage window = (Stage) back.getScene().getWindow();
			window.setScene(new Scene(root, 800, 600));
			
		}
	

		@FXML
		private void AccesLigue() throws Exception {		
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Ligue.fxml"));
			Stage window = (Stage) acces.getScene().getWindow();
			window.setScene(new Scene(root, 800, 600));
			
		}
}

	

	


