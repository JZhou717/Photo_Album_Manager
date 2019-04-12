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
import model.Admin;


public class LoginController {
	@FXML
	public TextField username;
	@FXML
	public Text setText;
	
	private AdminController admin_controller = new AdminController();
	
	public void loginClick(ActionEvent event) throws Exception{

		String name = username.getText().trim();
		
		if (name.equals("admin")) {
			
			PhotosController.stage.setScene(PhotosController.admin_scene);
			PhotosController.admin_controller.init(PhotosController.stage);
			PhotosController.stage.show();
			
		}
		else if (name.equals("stock")) {


		}else if( true) {//Admin.user_exists(name)) {
			
			/*
			Parent UserAlbumScreen;
			UserAlbumScreen = FXMLLoader.load(getClass().getResource("/view/UserAlbum.fxml"));
			Scene newScene = new Scene(UserAlbumScreen);
			Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
			mainWindow.setScene(newScene);
			*/
			
			

		}
		//check in a loaded list of valid usernames
		else {
			setText.setText("Username does not exist");

		}
		

	}
	
}