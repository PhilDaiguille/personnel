package JavaFX.Controller;

import java.util.ResourceBundle;
import java.util.SortedSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class LigueControl implements Initializable {

	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private SortedSet<Ligue> ligues = gestionPersonnel.getLigues();
	private static Ligue ligue;

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ListView<Ligue> myListView;
	@FXML
	private Button back, insert, acces, delete, modif;
	@FXML
	private Label myLabel;

	@FXML
	private TextField InsertLigue;

	static String liguec;

	@Override
	public void initialize(java.net.URL url, ResourceBundle ressource) {

		myListView.getItems().addAll(ligues);
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ligue>() {
			@Override
			public void changed(ObservableValue<? extends Ligue> url, Ligue ressource, Ligue ressource2) {

				myListView.getSelectionModel().getSelectedItem();
				ligue = myListView.getSelectionModel().getSelectedItem();

				liguec = myListView.getSelectionModel().getSelectedItem().toString();
				myLabel.setText(liguec);

			}

		});
	}

	@FXML
	private void AddLigue() throws Exception {
		if (InsertLigue.getText() != "") {
			String ligue = InsertLigue.getText();
			insert.getScene().getWindow();
			gestionPersonnel.addLigue(ligue);
			InsertLigue.setText("");
			myListView.getItems().removeAll(ligues);
			myListView.getItems().addAll(ligues);
		} else {
			showAlertWithoutHeaderText();
		}

	}

	@FXML
	private void DeleteLigue() throws Exception {
		myListView.getSelectionModel().getSelectedItem();
		Ligue ligue = myListView.getSelectionModel().getSelectedItem();
		delete.getScene().getWindow();
		ligue.remove();

		myListView.getItems().removeAll(ligues);
		myListView.getItems().addAll(ligues);

	}

	@FXML
	private void ModifLigue() throws Exception {
		if (InsertLigue.getText() != "") {
			myListView.getSelectionModel().getSelectedItem();
			modif.getScene().getWindow();
			ligue.setNom(InsertLigue.getText());
			InsertLigue.setText("");
			myListView.getItems().removeAll(ligues);
			myListView.getItems().addAll(ligues);

		} else {
			showAlertWithoutHeaderText();
		}

	}

	@FXML
	private void AccesEmploye() throws Exception {
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../Graphique/Employe.fxml"));
		Stage window = (Stage) acces.getScene().getWindow();
		window.setScene(new Scene(root, 800, 600));

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

	public static Ligue getLigue() {
		return ligue;
	}

	public static void setLigue(Ligue ligue) throws Exception {

		LigueControl.ligue = ligue;
	}

	public static String getNomLigue() {
		return liguec;
	}

	private void showAlertWithoutHeaderText() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning alert");

		alert.setHeaderText(null);
		alert.setContentText("Erreur le champs libre");

		alert.showAndWait();
	}

}
