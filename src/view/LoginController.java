//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginController {
	@FXML
	public TextField username;
	@FXML
	public Text setText;
	
	public void loginClick(ActionEvent event) throws Exception{

		String name = username.getText();
		if (name.equals("admin")) {

			Parent adminScreen;
			adminScreen = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/Admin.fxml"));
			Scene newScene = new Scene(adminScreen);
			Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
			mainWindow.setScene(newScene);

		}
		else if (name.equals("stock")) {


		}else if(true) { // CHECK HERE TO MAKE SURE USERNAME EXISTS 
			Parent UserAlbumScreen;
			UserAlbumScreen = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/UserAlbum.fxml"));
			Scene newScene = new Scene(UserAlbumScreen);
			Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
			mainWindow.setScene(newScene);

		}
		//check in a loaded list of valid usernames
		else {
			setText.setText("Username does not exist");

		}


	}
	
}
