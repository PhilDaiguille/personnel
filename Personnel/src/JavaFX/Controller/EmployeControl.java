package JavaFX.Controller;

import static commandLineMenus.rendering.examples.util.InOut.getString;

import java.util.ResourceBundle;
import java.util.SortedSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import personnel.Employe;
import personnel.Ligue;

public class EmployeControl implements Initializable {

	private ObservableList<Employe> observableEmploye;
	private Ligue ligue;
	private static SortedSet<Employe> employes;

	@FXML
	private TableColumn<Employe, String> DateArrivee;

	@FXML
	private TableColumn<Employe, String> DateDepart;

	@FXML
	private TableColumn<Employe, String> Nom, Prenom, Mail, Habilitation;

	@FXML
	private Label nomLigue;

	@FXML
	private Button back, AccesAdd, AccesModif;

	@FXML
	private TableView<Employe> tableEmploye;

	@FXML
	private void BackButton() throws Exception {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../Graphique/Ligue.fxml"));
		Stage window = (Stage) back.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));

	}

	@FXML
	private void ToucheRetour(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ESCAPE) {
			BackButton();
		}

	}

	@Override
	public void initialize(java.net.URL url, ResourceBundle ressource) {

		ligue = LigueControl.getLigue();
		employes = ligue.getEmployes();

		observableEmploye = FXCollections.observableArrayList(employes);

		tableEmploye.setItems(observableEmploye);
		Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		Mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		DateArrivee.setCellValueFactory(new PropertyValueFactory<>("DateArrivee"));
		DateDepart.setCellValueFactory(new PropertyValueFactory<>("DateDepart"));

		Habilitation.setCellValueFactory(
				cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().estAdmin(ligue))));

	}

	@FXML
	private void AccesAjoutEmploye() throws Exception {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../Graphique/AjoutEmploye.fxml"));
		Stage window = (Stage) AccesAdd.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));
	}
	
	@FXML
	private void AccesGererEmploye() throws Exception {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../Graphique/ModifEmploye.fxml"));
		Stage window = (Stage) AccesModif.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));
	}

}
