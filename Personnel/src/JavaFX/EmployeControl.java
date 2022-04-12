package JavaFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class EmployeControl {
	@FXML
	private Button back;
	
	@FXML
	private void BackButton() throws Exception {		
			Parent root;
			root = FXMLLoader.load(getClass().getResource("Ligue.fxml"));
			Stage window = (Stage) back.getScene().getWindow();
			window.setScene(new Scene(root, 800, 600));
			
	}
}
