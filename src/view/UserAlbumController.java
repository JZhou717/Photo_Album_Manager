//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
		Parent searchScreen;
		searchScreen = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Search.fxml"));
		Scene newScene = new Scene(searchScreen);
		Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
		mainWindow.setScene(newScene);
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
	}
	public void addAlbumClick() {
		String name = newAlbumName.getText();
	}
	public void openAlbumClick() {
		String name = albumInfo.getText();
	}
	public void init(Stage mainStage) {
		
		//Populating the list
		//WORKING HERE
		obsList = PhotosController.admin.getUserByName(PhotosController.get_user()).populateAlbumList();
		//obsList.add(new Album("TOM"));
		listView.setItems(obsList);
		
		listView
			.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
				(obs, oldVal, newVal) ->
					showItemInputDialog(mainStage));
		listView.getSelectionModel().select(0);
		
		
	}
	
	private void showItemInputDialog(Stage mainStage) {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index > -1) {
			Album album = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumAt(index);
			albumInfo.setText(album.getName());
		}
		
	}
	
	
}