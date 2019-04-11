//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class AdminController {
	@FXML
	public TextField usernameToDelete;
	@FXML
	public TextField usernameToAdd;
	
	public void addUserClick() {
		String name = usernameToAdd.getText();
	}
	public void deleteUserClick() {
		String name = usernameToDelete.getText();
	}
	
	public void logoutClick(ActionEvent event) throws Exception {
		Parent loginScreen;
		loginScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene newScene = new Scene(loginScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);

	}
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}