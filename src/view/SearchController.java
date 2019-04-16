//Thomas Heck tah167 Jake Zhou xz346
package view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Photo;
import model.Search;
import model.Tag;

/**
 * This is the controller for the search page
 * @author Jake
 *
 */
public class SearchController {
	@FXML
	public TextField tagToAdd;
	@FXML
	public ComboBox<String> typeFilter;
	@FXML
	public TextField tagToSearch;
	@FXML
	public Text dateText;
	@FXML
	public Text captionText;
	@FXML
	public DatePicker endDate;
	@FXML
	public DatePicker startDate;
	@FXML 
	ListView<Photo> listView;
	@FXML 
	ListView<Tag> tagListView;
	@FXML 
	ImageView imageView;
	
	/**
	 * The list of Photos that will result from the search
	 */
	private ObservableList<Photo> obsList = FXCollections.observableArrayList();
	
	
	/**
	 * Boots up the search page to be ready to use
	 * @param mainStage
	 */
	public void init(Stage mainStage) {
		
		listView.setItems(obsList);
		ObservableList<String> o =  FXCollections.observableArrayList();
		
		o.addAll("No Filter", "jpg", "png", "gif");
		if (typeFilter.getItems().size()<1) {
			typeFilter.getItems().addAll(o);
			typeFilter.setValue("No Filter");
		}
		
		
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
		listView.getSelectionModel().clearSelection();
		listView.getSelectionModel().select(0);
		
		
	}
	
	/**
	 * The listener for this page's listview
	 * @param mainStage
	 */
	private void showItemInputDialog(Stage mainStage) {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index > -1) {
			Photo photo = listView.getSelectionModel().getSelectedItem();
			
			imageView.setImage(photo.getImage());
			Date date = photo.getDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateText.setText("Photo from: " + sdf.format(date));
			captionText.setText("Caption: " + photo.getCaption());
			tagListView.setItems(listView.getSelectionModel().getSelectedItem().get_tags());
		}
		
	}
	
	/**
	 * Goes back to the User album page
	 * @param event that comes from the back button
	 */
	public void backClick(ActionEvent event) {
		tagListView.setItems(null);
		imageView.setImage(null);
		captionText.setText("Caption: ");
		dateText.setText("Photo from: ");
		PhotosController.user_album_controller.init(PhotosController.stage);
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
	
	/**
	 * Goes right on the slideshow of photos
	 */
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
	
	/**
	 * goes left on the slideshow of photos
	 */
	public void leftClick() {
		if (listView.getSelectionModel().getSelectedItem()==null) {
			return;
		}
		int index = listView.getSelectionModel().getSelectedIndex();
		if (index -1  < 0) {
			listView.getSelectionModel().select(obsList.size());
		}else {
			listView.getSelectionModel().select(index - 1);
		}
		
	}
	
	/**
	 * Filters this user's photos to the display only the ones of the given type
	 */
	public void filterClick() {
		
		String name = typeFilter.getValue();
		
		ObservableList<Photo> filterList = FXCollections.observableArrayList();
		if (name.equals("No Filter")) {
			for (int i=0;i<obsList.size();i++) {
			
					
				filterList.add(obsList.get(i));
				
			
			
			}
		}
		
		if (name.equals("jpg")) {
			
			for (int i=0;i<obsList.size();i++) {
				
				if (obsList.get(i).toString().substring(obsList.get(i).toString().length()-3).equals("jpg")){
					
					filterList.add(obsList.get(i));
				}
			
			
			}
			
		}
		if (name.equals("png")) {
			for (int i=0;i<obsList.size();i++) {
				if (obsList.get(i).toString().substring(obsList.get(i).toString().length()-3).equals("png")){
					filterList.add(obsList.get(i));
				}
			
			}
			
		}
		if (name.equals("gif")) {
			for (int i=0;i<obsList.size();i++) {
				if (obsList.get(i).toString().substring(obsList.get(i).toString().length()-3).equals("gif")){
					filterList.add(obsList.get(i));
				}
			
			}
			
			
		}
	
		listView.setItems(filterList);
		listView.getSelectionModel().select(0);
		
	}
	
	/**
	 * Sets the listview to the results of the date search
	 */
	public void searchDateClick() {
		if (startDate.getValue()==null|| endDate.getValue()==null) {
			Alert alert = new Alert(AlertType.ERROR, "Please enter a start and an end date", ButtonType.OK);
			alert.show();
			return;
		}
		tagListView.setItems(null);
		imageView.setImage(null);
		LocalDate start = startDate.getValue();
		LocalDate end = endDate.getValue();
		/*
		String start = startDate.getText().trim();
		String end = endDate.getText().trim();*/
		
		//Getting the list of results
		obsList = Search.search_date(start, end);
		
		//Show the returned values
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
	}
	
	/**
	 * Sets the listview to the results of the tag search
	 */
	public void searchTagClick() {
		tagListView.setItems(null);
		imageView.setImage(null);
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
		listView.setItems(obsList);
		listView.getSelectionModel().select(0);
	}
	
	/**
	 * Saves the data of the user and goes to the login page
	 * @param event that comes from the logout click button
	 * @throws IOException in case something goes wrong during serialization
	 */
	public void logoutClick(ActionEvent event) throws IOException{
		tagListView.setItems(null);
		imageView.setImage(null);
		captionText.setText("Caption: ");
		dateText.setText("Photo from: ");
		PhotosController.serialize();
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
	/**
	 * Creats an album from the results of the search
	 * @param event
	 * @throws Exception
	 */
	public void create_album_from_result(ActionEvent event) throws Exception {
		
		TextInputDialog tid = new TextInputDialog();
		tid.setHeaderText("Enter name for new Album:");
		tid.setContentText("New Name: ");
		String name = tid.showAndWait().orElse(null);
		if (name == null) {
			return;
		}
		name = name.trim();
		
		Search.add_album_from_result(name, obsList);
		
	}
	
		
}