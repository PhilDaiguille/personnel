package JavaFX;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RunApplication extends Application {

	public void start(Stage primaryStage) throws IOException {
		
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			primaryStage.setScene(new Scene(root, 600, 400));
			primaryStage.setResizable(false);
			primaryStage.setTitle("PPE - Gestion du personnel des ligues");
			primaryStage.show();
			Image image = new Image("D:/User/Documents/GitHub/personnel/Personnel/src/JavaFX/icon.png");
			primaryStage.getIcons().add(image);

	}

	public static void main(String[] args) throws Exception{

		launch(args);
	}

}
