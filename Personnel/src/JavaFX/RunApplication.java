package JavaFX;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.SauvegardeImpossible;

public class RunApplication extends Application {
	private final GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	private Employe root = gestionPersonnel.getRoot();

	public void start(Stage primaryStage) throws IOException {

		Parent root;
		root = FXMLLoader.load(getClass().getResource("Graphique/Login.fxml"));
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setResizable(false);
		primaryStage.setTitle("PPE - Gestion du personnel des ligues");
		Image image = new Image(getClass().getResource("Image/icon.png").toExternalForm());
		primaryStage.getIcons().add(image);
		primaryStage.show();

		// Alerte
		primaryStage.setOnCloseRequest(evt -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Quitter");
			alert.setHeaderText("Voulez-vous vraiment quitter l'application ?");
			alert.setContentText("Choisissez :");
			ButtonType buttonTypeOne = new ButtonType("Quitter et enregister");
			ButtonType buttonTypeTwo = new ButtonType("Quitter sans enregister");
			ButtonType buttonTypeCancel = new ButtonType("Back", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne) {
				try {
					gestionPersonnel.sauvegarder();
					primaryStage.close();
				} catch (SauvegardeImpossible e) {
					System.out.println("Impossible d'effectuer la sauvegarde");
				}
			} else if (result.get() == buttonTypeTwo) {
				primaryStage.close();
			} else {
				evt.consume();
			}

		});
	}

	public static void main(String[] args) throws Exception {

		launch(args);
	}

}
