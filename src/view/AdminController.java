//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class AdminController {
	@FXML
	public TextField usernameToDelete;
	@FXML
	public TextField usernameToAdd;
	
	public void addUserClick() {
		String name = usernameToAdd.getText();
	}
	public void deleteUserClick() {
		String name = usernameToDelete.getText();
	}
	
	public void logoutCick() {
		
	}
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}