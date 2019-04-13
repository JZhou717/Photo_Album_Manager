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
	
	private ObservableList<Photo> obsList = FXCollections.observableArrayList();
	
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
		
		//If searching for two tags
		if(tag.indexOf("AND") != -1 || tag.indexOf("OR") != -1) {
			//Parsing the tags into two
			String[] tags_list;
			String tag1;
			String tag2;
			
			if(tag.indexOf("AND") != -1) {
				tags_list = tag.split("AND", 2);
			}
			else {
				tags_list = tag.split("OR", 2);
			}
			
			tag1 = tags_list[0].trim();
			tag2 = tags_list[1].trim();
			
			ObservableList<Photo> list1 = Search.search_tag(tag1);
			
			ObservableList<Photo> list2 = Search.search_tag(tag2);
			
			obsList = Search.merge_lists(list1, list2);
			
		}
		//Just searching for one tag
		else {
			obsList = Search.search_tag(tag.trim());
		}
		
		//Show the returned values
		
	}
	
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
		
}