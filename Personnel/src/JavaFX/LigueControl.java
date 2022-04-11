package JavaFX;

import java.util.ResourceBundle;
import java.util.SortedSet;

import javax.print.DocFlavor.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MeshView;
import javafx.stage.Stage;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class LigueControl implements Initializable{
	
	private GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private SortedSet<Ligue> ligues = gestionPersonnel.getLigues();
	
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ListView<Ligue> myListView;
	@FXML
	private Button back;
	@FXML
	private Label myLabel;
	
	String liguec;
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		
		myListView.getItems().addAll(ligues);
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ligue>() {
			@Override
			public void changed(ObservableValue<? extends Ligue> arg0, Ligue arg1, Ligue arg2) {
				
				myListView.getSelectionModel().getSelectedItem();
				liguec = myListView.getSelectionModel().getSelectedItem().toString();
				myLabel.setText(liguec);
			
				
				
			}

			
		});
	}
	@FXML
	private void BackButton() throws Exception {		
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
			Stage window = (Stage) back.getScene().getWindow();
			window.setScene(new Scene(root, 600, 400));
			
		}

	
	
}


