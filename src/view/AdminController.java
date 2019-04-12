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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Admin;
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
	
	public void addUserClick() {
		String name = usernameToAdd.getText().trim();
		
		
		if(Admin.user_exists(name)) {
			//username already taken
			Alert alert = new Alert(AlertType.ERROR, "Username already taken", ButtonType.OK);
			alert.show();
			return;
		}
		else {
			//adding user
			Admin.add_user(name);
		}
	}
	
	public void deleteUserClick() {
		String name = usernameToDelete.getText().trim();
		
		
		if(name.toLowerCase().equals("admin")) {
			//cannot delete the admin
			Alert alert = new Alert(AlertType.ERROR, "Cannot delete admin", ButtonType.OK);
			alert.show();
			return;
		}
		else {
			Admin.remove_user(name);
		}
	}
	
	public void logoutClick(ActionEvent event) throws Exception {
		
		PhotosController.stage.setScene(PhotosController.login_scene);
		PhotosController.stage.show();

	}
	
	
	/**
	 * Initializes the Admin List View
	 * @param mainStage the Stage to display to
	 */
	public void init(Stage mainStage) {
		
		if(listView == null) {
			Alert alert = new Alert(AlertType.ERROR, "listView is null", ButtonType.OK);
			alert.show();
		}
	
		//Populating the list
		obsList = Admin.populateUserList();
		
		obsList.add(0, new User("admin"));
		
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
			User user = Admin.getUser(index);
			usernameToDelete.setText(user.getName());
		}
	}
	
	
	
	
}