//Thomas Heck tah167 Jake Zhou xz346
package view;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Album;
import model.Photo;
import model.Tag;


public class OpenAlbumController {
	@FXML
	public TextField tagToAddText;
	@FXML
	public Text captionText;
	@FXML
	public Text dateText;
	@FXML
	public ImageView imageView;
	@FXML
	public ListView<Photo> listView;
	@FXML
	public ListView<Tag> tagListView;
	@FXML
	public ListView<Album> albumListView;
	private ObservableList<Photo> obsList = FXCollections.observableArrayList();
	private ObservableList<Album> albumObsList = FXCollections.observableArrayList();

	final FileChooser fileChooser = new FileChooser();
	Stage stage;
	public void logoutClick(ActionEvent event) throws Exception {
		captionText.setText("Caption: ");
		dateText.setText("Photo from: ");
		tagListView.setItems(null);
		imageView.setImage(null);
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	public void deletePhotoClick() {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album()).deletePhotoAt(index);
	}
	public void moveToAlbumClick() {
		int index = listView.getSelectionModel().getSelectedIndex();
		//we need a listview to popup here
		
		ArrayList<String> albumList = new ArrayList<String>();
		for (int i=0;i<albumObsList.size();i++) {
			String temp = albumObsList.get(i).getName();
			if (temp.equals(PhotosController.get_album())){
				continue;
			}
			albumList.add(temp);
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(albumList.get(0).toString(), albumList);
		dialog.setTitle("Mover");
		dialog.setHeaderText("Choose album to move photo into:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(result.get()).addPhoto(listView.getSelectionModel().getSelectedItem());
			PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album()).deletePhotoAt(index);
		}
		
	}
	public void copyToAlbumClick() {
		//we need a listview to popup here
		
		ArrayList<String> albumList = new ArrayList<String>();
		for (int i=0;i<albumObsList.size();i++) {
			String temp = albumObsList.get(i).getName();
			if (temp.equals(PhotosController.get_album())){
				continue;
			}
			albumList.add(temp);
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(albumList.get(0).toString(), albumList);
		dialog.setTitle("Mover");
		dialog.setHeaderText("Choose album to copy photo into:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(result.get()).addPhoto(listView.getSelectionModel().getSelectedItem());
		}
	}
	public void editCaptionClick() {
		TextInputDialog tid = new TextInputDialog();
		tid.setHeaderText("Caption (Max 75 Characters)");
		tid.setContentText("New Caption: ");
		String result = tid.showAndWait().orElse(null);
		if (result==null) {
			return;
		}
		if (result.length() > 75) {
        	result = result.substring(0, 75) + "...";
        }
		captionText.setText("Caption: " + result);
		listView.getSelectionModel().getSelectedItem().editCaption(result);
	}
	public void deleteTagClick() {
		if (tagListView.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		
		if (tagListView.getSelectionModel().getSelectedItem().getName().equals("Location")){
			listView.getSelectionModel().getSelectedItem().setLocation();
		}
		
		listView.getSelectionModel().getSelectedItem().deleteTag(tagListView.getSelectionModel().getSelectedItem());
		
	}
	public void addTagClick() {
		
		String type;
		
		ArrayList<String> tagList = PhotosController.admin.getUserByName(PhotosController.get_user()).getTags();
		
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(tagList.get(0), tagList);
		dialog.setTitle("Tagger");
		dialog.setHeaderText("Choose type from list or select 'New Type':");
		//ButtonType buttonTypeNew = new ButtonType("New Tag");
		//alert.getButtonTypes().setAll(buttonTypeNew);
		Optional<String> result = dialog.showAndWait();
		if (!result.isPresent()){
			return;
		}
		
		if (result.get().equals("Location") && listView.getSelectionModel().getSelectedItem().getLocation() == true) {
			Alert alert = new Alert(AlertType.ERROR, "Cannot have more than one Location", ButtonType.OK);
			alert.show();
			return;
		}
		if (result.get().equals("New Type")) {
			TextInputDialog tid = new TextInputDialog();
			tid.setHeaderText("Tag Type");
			tid.setContentText("New Tag Type: ");
			String res = tid.showAndWait().orElse(null);
			if (res==null) {
				return;
			}
			if (res.equals("Location") && listView.getSelectionModel().getSelectedItem().getLocation() == true) {
				Alert alert = new Alert(AlertType.ERROR, "Cannot have more than one Location", ButtonType.OK);
				alert.show();
				return;
			}
			if (!PhotosController.admin.getUserByName(PhotosController.get_user()).getTags().contains(res)) {
				PhotosController.admin.getUserByName(PhotosController.get_user()).addTag(res);
			}
			
			type = res;
			
		}else {
			type = result.get();
		}
		TextInputDialog tid = new TextInputDialog();
		tid.setHeaderText("Tag Value");
		tid.setContentText("Value: ");
		String val = tid.showAndWait().orElse(null);
		if (val==null) {
			return;
		}
		if (result.get().equals("Location")) {
			
			listView.getSelectionModel().getSelectedItem().setLocation();
		}
		listView.getSelectionModel().getSelectedItem().addTag(type, val);
	}
	public void selectPhotoClick() {
		//this one is to add photos
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	String path = file.toURI().toString();
            Image image = new Image(path);
            
            imageView.setImage(image);
           //file:/C:/Users/theck/Pictures/guitar.png
            //String photoName = file.getName();
            Photo photo = new Photo(image, path);
            photo.setDate(file.lastModified());
            photo.setName(file.getName());
            Date date = photo.getDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateText.setText("Photo from: " + sdf.format(date));
            
            captionText.setText("Caption: " + photo.getCaption());
            //listView.getSelectionModel().select(PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album()).size()+1);
            listView.getSelectionModel().clearSelection();
            Album album = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album());
            album.addPhoto(photo);
            
        }
		
	}
	public void backClick() {
		imageView.setImage(null);
		tagListView.setItems(null);
		captionText.setText("Caption: ");
		dateText.setText("Photo from: ");
		PhotosController.user_album_controller.init(PhotosController.stage);
		PhotosController.stage.setScene(PhotosController.user_album_scene);
		PhotosController.stage.show();
	}
	public void rightClick() {
		if (listView.getSelectionModel().getSelectedItem()==null) {
			return;
		}
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index + 1 > obsList.size()) {
			listView.getSelectionModel().select(0);
		}else {
			listView.getSelectionModel().select(index + 1);
		}
		
	}
	public void leftClick() {
		if (listView.getSelectionModel().getSelectedItem()==null) {
			return;
		}
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index - 1  < 0) {
			listView.getSelectionModel().select(obsList.size());
		}else {
			listView.getSelectionModel().select(index - 1);
		}
		
	}
	public void init(Stage mainStage) {
		
		//Populating the list
		stage = mainStage;
		obsList = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbumByName(PhotosController.get_album()).populatePhotoList();
		albumObsList = PhotosController.admin.getUserByName(PhotosController.get_user()).getAlbums();
		//obsList.add(new Album("TOM"));
		listView.setItems(obsList);
		listView.setCellFactory(param -> new ListCell<Photo>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Photo name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(name.getImage());
                    imageView.setFitHeight(75);
                    
                    imageView.setPreserveRatio(true);
                    setText(name.toString());
                    setGraphic(imageView);
                }
            }
        });
		
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
			captionText.setText("Caption: " + photo.getCaption());
			Date date = photo.getDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateText.setText("Photo from: " + sdf.format(date));
			
			tagListView.setItems(listView.getSelectionModel().getSelectedItem().get_tags());
		}
		
	}
	/*
	static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            ImageView rect = new ImageView();
            if (item != null) {
                rect.setImage(item);
                setGraphic(rect);
            }
        }
    }*/
    
	
	
	
	
}