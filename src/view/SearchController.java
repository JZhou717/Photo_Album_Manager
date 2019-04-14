//Thomas Heck tah167 Jake Zhou xz346
package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Photo;
import model.Search;
import model.Tag;


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
	
	private ObservableList<Photo> obsList = FXCollections.observableArrayList();
	
	public void init(Stage mainStage) {
		
		listView.setItems(obsList);
		ObservableList<String> o =  FXCollections.observableArrayList();
		o.addAll("No Filter", "jpg", "png", "gif");
		typeFilter.getItems().addAll(o);
		typeFilter.setValue("No Filter");
		
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
			Photo photo = listView.getSelectionModel().getSelectedItem();
			
			imageView.setImage(photo.getImage());
			Date date = photo.getDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            dateText.setText("Photo from: " + sdf.format(date));
			captionText.setText("Caption: " + photo.getCaption());
			tagListView.setItems(listView.getSelectionModel().getSelectedItem().get_tags());
		}
		
	}
	
	public void backClick(ActionEvent event) throws Exception {
		tagListView.setItems(null);
		imageView.setImage(null);
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
	
	public void filterClick() {
		
		String name = typeFilter.getValue();
		
		
		if (name.equals("No Filter")) {
			return;
		}
		ObservableList<Photo> filterList = FXCollections.observableArrayList();
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
	
	public void logoutClick(ActionEvent event) throws Exception {
		tagListView.setItems(null);
		imageView.setImage(null);
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
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