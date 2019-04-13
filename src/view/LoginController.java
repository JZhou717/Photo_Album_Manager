//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class LoginController {
	@FXML
	public TextField username;
	@FXML
	public Text setText;
	
	public void loginClick(ActionEvent event) throws Exception{

		String name = username.getText().trim();
		
		if (name.equals("admin")) {
			
			PhotosController.stage.setScene(PhotosController.admin_scene);
			PhotosController.admin_controller.init(PhotosController.stage);
			PhotosController.stage.show();
			
		}
		else if(PhotosController.admin.user_exists(name)) {
			
			/*
			Parent UserAlbumScreen;
			UserAlbumScreen = FXMLLoader.load(getClass().getResource("/view/UserAlbum.fxml"));
			Scene newScene = new Scene(UserAlbumScreen);
			Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
			mainWindow.setScene(newScene);
			*/
			PhotosController.set_user(name);
			PhotosController.stage.setScene(PhotosController.user_album_scene);
			PhotosController.user_album_controller.init(PhotosController.stage);
			PhotosController.stage.show();
			
			

		}
		//check in a loaded list of valid usernames
		else {
			setText.setText("Username does not exist");

		}
		

	}
	
}