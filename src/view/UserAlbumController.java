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


public class UserAlbumController {
	@FXML
	public TextField newAlbumName;
	@FXML
	public TextField albumInfo;
	
	public void logoutClick(ActionEvent event) throws Exception {
		Parent loginScreen;
		loginScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene newScene = new Scene(loginScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);

	}
	public void goToSearchClick(ActionEvent event) throws Exception {
		Parent searchScreen;
		searchScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Search.fxml"));
		Scene newScene = new Scene(searchScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);
	}
	public void createAlbumClick() {
		
	}
	public void deleteAlbumClick() {
		String name = albumInfo.getText();
	}
	public void editAlbumClick() {
		String name = albumInfo.getText();
	}
	public void addAlbumClick() {
		String name = newAlbumName.getText();
	}
	public void openAlbumClick() {
		String name = albumInfo.getText();
	}
	
}