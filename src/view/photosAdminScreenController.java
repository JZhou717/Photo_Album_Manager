//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class photosAdminScreenController {
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
	
	
	
	
}