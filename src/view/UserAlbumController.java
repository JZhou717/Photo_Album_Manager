//Thomas Heck tah167 Jake Zhou xz346
package view;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Admin;
import model.Album;
import model.User;


public class UserAlbumController {
	@FXML
	public TextField newAlbumName;
	@FXML
	public TextField albumInfo;
	
	@FXML
	ListView<Album> listView;
	
	//observable list of albums
	private ObservableList<Album> obsList = FXCollections.observableArrayList();
	
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	public void goToSearchClick(ActionEvent event) throws Exception {
		PhotosController.stage.setScene(PhotosController.search_scene);
		PhotosController.stage.show();
	}
	public void createAlbumClick() {
		String name = newAlbumName.getText();
		PhotosController.admin.getUserByName(PhotosController.get_user()).addAlbum(name);
	}
	public void deleteAlbumClick() {
		String name = albumInfo.getText();
		PhotosController.admin.getUserByName(PhotosController.get_user()).removeAlbum(name);
		
	}
	public void editAlbumClick() {
		String name = albumInfo.getText();
		if (PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(name)==null) {
			Alert alert = new Alert(AlertType.ERROR, "Album does not exist", ButtonType.OK);
			alert.show();
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
		//update listview here
	}
	public void openAlbumClick() {
		String name = albumInfo.getText();
		if (PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(name)==null) {
			Alert alert = new Alert(AlertType.ERROR, "Album does not exist", ButtonType.OK);
			alert.show();
			return;
		}else {
			PhotosController.set_album(name);
			PhotosController.open_album_controller.init(PhotosController.stage);
			PhotosController.stage.setScene(PhotosController.open_album_scene);
			PhotosController.stage.show();
		}
		
		
	}
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
	}
	
	private void showItemInputDialog(Stage mainStage) {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index > -1) {
			Album album = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumAt(index);
			albumInfo.setText(album.getName());
		}
		
	}
	
	
}