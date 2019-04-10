//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class LoginController {
	@FXML
	public TextField username;
	@FXML
	public Text setText;
	
	public void loginClick() {
		
		String name = username.getText();
		if (name.equals("admin")) {
			
			
		}
		else if (name.equals("stock")) {
			
			
		}
		//check in a loaded list of valid usernames
		else {
			setText.setText("Username does not exist");
			
		}
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
	
}
