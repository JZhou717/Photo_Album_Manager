//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class OpenAlbumController {
	@FXML
	public TextField tagToAddText;
	
	
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	public void deletePhotoClick() {
		
	}
	public void moveToAlbumClick() {
		
	}
	public void copyToAlbumClick() {
		
	}
	public void editCaptionClick() {
		
	}
	public void deleteTagClick() {
		
	}
	public void addTagClick() {
		String tag = tagToAddText.getText();
	}
	public void selectPhotoClick() {
		
	}
	public void backClick() {
		
	}
	
	
}