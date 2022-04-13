package JavaFX;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import personnel.Employe;
import personnel.Ligue;
import personnel.*;

public class AjoutEmployeControl {
	Ligue ligue;
    @FXML
    private DatePicker DateArrivee;

    @FXML
    private DatePicker DateDepart;

    @FXML
    private Button back;

    @FXML
    private Button confirmer;

    @FXML
    private TextField mail;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    private String Nom, Prenom, password, Mail;
	private LocalDate dateArrivee, dateDepart;
	
    @FXML
    private void AjouterEmploye() throws Exception{
    	Nom = nom.getText();
    	Prenom = prenom.getText();
    	password = mdp.getText();
    	Mail = mail.getText();
    	dateArrivee = DateArrivee.getValue();
    	dateDepart = DateDepart.getValue();
    	
    	if (!(Nom.equals("") && Prenom.equals("") && Mail.equals("") && password.equals(""))) {
			this.ligue.addEmploye(Nom,Prenom,password,Mail,dateArrivee,dateDepart);
    	}
    	else {
    		showAlertWithoutHeaderText();
    	}
    	
    	
    }

    
    @FXML
    private void BackButton() throws Exception{
    	Parent root;
		root = FXMLLoader.load(getClass().getResource("Employe.fxml"));
		Stage window = (Stage) back.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));
    }
    @FXML
    private void ToucheRetour(KeyEvent event) throws Exception
    {
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
