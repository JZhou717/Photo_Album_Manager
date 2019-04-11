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
		Parent userAlbumScreen;
		userAlbumScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/UserAlbum.fxml"));
		Scene newScene = new Scene(userAlbumScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);
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
		Parent loginScreen;
		loginScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		Scene newScene = new Scene(loginScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);

	}
	
		
}