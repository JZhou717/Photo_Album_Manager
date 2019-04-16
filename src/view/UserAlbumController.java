//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Album;

/**
 * The controller for the User Album page
 * @author Tom
 *
 */
public class UserAlbumController {
	@FXML
	public TextField newAlbumName;
	@FXML
	public TextField albumInfo;
	
	@FXML
	ListView<Album> listView;
	
	//observable list of albums
	private ObservableList<Album> obsList = FXCollections.observableArrayList();
	
	/**
	 * Goes back to the login page after saving data
	 * @param event
	 * @throws Exception
	 */
	public void logoutClick(ActionEvent event) throws Exception {
		albumInfo.setText("");
		newAlbumName.setText("");
		PhotosController.serialize();
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
	/**
	 * Goes to the search page
	 * @param event that comes from the button click
	 */
	public void goToSearchClick(ActionEvent event){
		
		newAlbumName.setText("");
		PhotosController.search_controller.init(PhotosController.stage);
		PhotosController.stage.setScene(PhotosController.search_scene);
		PhotosController.stage.show();
	}
	
	/**
	 * Create a new album for this user
	 */
	public void createAlbumClick() {
		String name = newAlbumName.getText();
		PhotosController.admin.getUserByName(PhotosController.get_user()).addAlbum(name);
	}
	
	/**
	 * Delete an album this user has
	 */
	public void deleteAlbumClick() {
		String name = albumInfo.getText();
		PhotosController.admin.getUserByName(PhotosController.get_user()).removeAlbum(name);
		albumInfo.setText("");
		listView.getSelectionModel().clearSelection();
		
	}
	
	/**
	 * Edit the name of an existing album
	 */
	public void editAlbumClick() {
		String name = albumInfo.getText();
		if (PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(name)==null) {
			/*
			Alert alert = new Alert(AlertType.ERROR, "Album does not exist", ButtonType.OK);
			alert.show();
			*/
			return;
		}
		TextInputDialog tid = new TextInputDialog();
		tid.setHeaderText("Enter new name for Album:");
		tid.setContentText("New Name: ");
		String result = tid.showAndWait().orElse(null);
		if (result==null) {
			return;
		}
		PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(name).rename(result);
		int index = listView.getSelectionModel().getSelectedIndex();
		obsList.set(index, PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumAt(index));
		albumInfo.setText(result);
		//update listview here
	}
	
	/**
	 * Goes to the open album page for the selected album
	 */
	public void openAlbumClick() {
		
		newAlbumName.setText("");
		String name = albumInfo.getText();
		if (PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(name)==null) {
			/*
			Alert alert = new Alert(AlertType.ERROR, "Album does not exist", ButtonType.OK);
			alert.show();
			*/
			return;
		}else {
			PhotosController.set_album(name);
			PhotosController.open_album_controller.init(PhotosController.stage);
			PhotosController.stage.setScene(PhotosController.open_album_scene);
			PhotosController.stage.show();
		}
		
		
	}
	
	/**
	 * Boots up the user album page to be ready for use
	 * @param mainStage
	 */
	public void init(Stage mainStage) {
		
		//Populating the list
		
		obsList = PhotosController.admin.getUserByName(PhotosController.get_user()).populateAlbumList();
		//obsList.add(new Album("TOM"));
		listView.setItems(obsList);
		
		//listener
		listView
			.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
				(obs, oldVal, newVal) ->
					showItemInputDialog(mainStage));
		listView.getSelectionModel().select(0);
		
		albumInfo.setEditable(false);
		for (int i=0;i<obsList.size();i++) {
			obsList.set(i, PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumAt(i));
		}
		
	}
	/**
	 * The listener for the listview for the list of albums on this page
	 * @param mainStage the stage this listener is on
	 */
	private void showItemInputDialog(Stage mainStage) {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index > -1) {
			Album album = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumAt(index);
			albumInfo.setText(album.getName());
		}
		
	}
	
	
}