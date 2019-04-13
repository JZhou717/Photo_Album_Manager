//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Photo;
import model.Search;


public class SearchController {
	@FXML
	public TextField tagToAdd;
	@FXML
	public TextField tagToSearch;
	@FXML
	public TextField endDate;
	@FXML
	public TextField startDate;
	@FXML 
	ListView<Photo> listView;
	@FXML ImageView imageView;
	
	private ObservableList<Photo> obsList = FXCollections.observableArrayList();;
	
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
		
		//Parse tag into tag type and tag_value
		String tag_type;
		String tag_value;
		String[] split_tag = tag.split("=", 2);
		//If there was no comma
		if(split_tag.length < 2) {
			Alert alert = new Alert(AlertType.ERROR, "Please input a valid pair of values: tag_type=tag_value", ButtonType.OK);
			alert.show();
			return;
		}
		
		//If the tag_type is empty
		tag_type = split_tag[0].trim();
		if(tag_type.equals("")) {
			Alert alert = new Alert(AlertType.ERROR, "Please input non empty tag_type value", ButtonType.OK);
			alert.show();
			return;
		}
		
		//If the tag_value is empty
		tag_value = split_tag[1].trim();
		if(tag_value.equals("")) {
			Alert alert = new Alert(AlertType.ERROR, "Please input non empty tag_value value", ButtonType.OK);
			alert.show();
			return;
		}
		//if there are more than one comma
		if(tag_value.indexOf("=") != -1) {
			Alert alert = new Alert(AlertType.WARNING, "You have inputted text with more than one equals sign, all text after the first equals sign will now be considered the tag_value", ButtonType.OK);
			alert.show();
		}
		
		//Run the search and get a list of the photos with these tags
		obsList = Search.tag_search_results(tag_type, tag_value);
		
		//Show the returned values
		
	}
	
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
		
}