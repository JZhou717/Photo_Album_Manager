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
import javafx.stage.Stage;
import model.User;

/**
 * This class controls the admin page that shows a ListView of the users and includes the ability to add or remove users
 * @author Jake
 *
 */
public class AdminController {
	@FXML
	public TextField usernameToDelete;
	@FXML
	public TextField usernameToAdd;
	@FXML
	ListView<User> listView;
	
	//The observable list of the users
	private ObservableList<User> obsList = FXCollections.observableArrayList();
	
	/**
	 * The action associated with the add user album. Adds a user to the list of users
	 */
	public void addUserClick() {
		String name = usernameToAdd.getText().trim();
		
		
		if(PhotosController.admin.user_exists(name)) {
			//username already taken
			Alert alert = new Alert(AlertType.ERROR, "Username already taken", ButtonType.OK);
			alert.show();
			return;
		}
		else {
			//adding user
			PhotosController.admin.add_user(name);
			//PhotosController.admin.getUserByName(name).addTag("Location");
			//PhotosController.admin.getUserByName(name).addTag("Person");
			
		}
	}
	
	/**
	 * The aciton associated with the delete user click. Removes the user with the name taken from the assocaited text field
	 */
	public void deleteUserClick() {
		String name = usernameToDelete.getText().trim();
		
		
		if(name.toLowerCase().equals("admin")) {
			//cannot delete the admin
			Alert alert = new Alert(AlertType.ERROR, "Cannot delete admin", ButtonType.OK);
			alert.show();
			return;
		}
		else {
			PhotosController.admin.remove_user(name);
		}
	}
	
	/**
	 * Takes us back to the login page after serializing
	 * @param event
	 * @throws Exception
	 */
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.serialize();
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
	
	/**
	 * Initializes the Admin List View
	 * @param mainStage the Stage to display to
	 */
	public void init(Stage mainStage) {
	
		//Populating the list
		obsList = PhotosController.admin.populateUserList();
		listView.setItems(obsList);
		
		listView
			.getSelectionModel()
			.selectedIndexProperty()
			.addListener(
				(obs, oldVal, newVal) ->
					showItemInputDialog(mainStage));
		listView.getSelectionModel().select(0);
		
	}
	
	/**
	 * The listener for the listview of users
	 * @param mainStage the stage that we are checking the list on
	 */
	private void showItemInputDialog(Stage mainStage) {
		
		int index = listView.getSelectionModel().getSelectedIndex();
		
		if(index > -1) {
			User user = PhotosController.admin.getUser(index);
			usernameToDelete.setText(user.getName());
		}
	}
	
	
	
	
}