//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class photosSearchScreenController {
	@FXML
	public TextField tagToAdd;
	@FXML
	public TextField tagToSearch;
	@FXML
	public TextField endDate;
	@FXML
	public TextField startDate;
	
	public void backClick() {
		
	}
	public void deleteTagClick() {
		
	}
	public void addTagClick() {
		String tag = tagToAdd.getText();
	}
	public void editCaptionClick() {
		
	}
	public void searchDateClick() {
		String start = startDate.getText();
		String end = endDate.getText();
	}
	public void searchTagClick() {
		String tag = tagToSearch.getText();
	}
	public void logoutClick() {
		
	}
	
		
}