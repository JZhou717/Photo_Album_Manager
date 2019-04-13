//Thomas Heck tah167 Jake Zhou xz346
package view;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Album;
import model.Photo;


public class OpenAlbumController {
	@FXML
	public TextField tagToAddText;
	@FXML
	public ImageView imageView;
	@FXML
	public ListView<Photo> listView;
	private ObservableList<Photo> obsList = FXCollections.observableArrayList();
	final FileChooser fileChooser = new FileChooser();
	Stage stage;
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
		//this one is to add photos
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	String path = file.toURI().toString();
            Image image = new Image(path);
            imageView.setImage(image);
            //String photoName = file.getName();
            Photo photo = new Photo(image, path);
            //writing code here
            Album album = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album());
            album.addPhoto(photo);
            
        }
		
	}
	public void backClick() {
		PhotosController.stage.setScene(PhotosController.user_album_scene);
		PhotosController.stage.show();
	}
	public void init(Stage mainStage) {
		
		//Populating the list
		
		obsList = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album()).populatePhotoList();
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
		
		
	}
	private void showItemInputDialog(Stage mainStage) {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index > -1) {
			Photo photo = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album()).getPhotoAt(index);
			imageView.setImage(photo.getImage());
		}
		
	}
	
	
}