//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class SearchController {
	@FXML
	public TextField tagToAdd;
	@FXML
	public TextField tagToSearch;
	@FXML
	public TextField endDate;
	@FXML
	public TextField startDate;
	
	public void backClick(ActionEvent event) throws Exception {
		PhotosController.stage.setScene(PhotosController.user_album_scene);
		PhotosController.stage.show();
		/* UPDATE THIS TO THE NEW IMPLEMENTATION
		Parent userAlbumScreen;
		userAlbumScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/UserAlbum.fxml"));
		Scene newScene = new Scene(userAlbumScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);
		*/
		
	}
	public void deleteTagClick() {
		
	}
	public void addTagClick() {
		String tag = tagToAdd.getText();
	}
	public void editCaptionClick() {
		
	}
	public void searchDateClick() {
		String start = startDate.getText();
		String end = endDate.getText();
	}
	public void searchTagClick() {
		String tag = tagToSearch.getText();
	}
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
		
}