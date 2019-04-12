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
import javafx.stage.Stage;


public class OpenAlbumController {
	@FXML
	public TextField tagToAddText;
	
	
	public void logoutClick(ActionEvent event) throws Exception {
		Parent loginScreen;
		loginScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene newScene = new Scene(loginScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);

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