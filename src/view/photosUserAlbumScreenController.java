//Thomas Heck tah167 Jake Zhou xz346
package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class photosUserAlbumScreenController {
	@FXML
	public TextField newAlbumName;
	@FXML
	public TextField albumInfo;
	
	public void logoutClick() {
		
	}
	public void goToSearchClick() {
		
	}
	public void deleteAlbumClick() {
		String name = albumInfo.getText();
	}
	public void editAlbumAlbumClick() {
		String name = albumInfo.getText();
	}
	public void addAlbumClick() {
		String name = newAlbumName.getText();
	}
	public void openAlbumClick() {
		String name = albumInfo.getText();
	}
	
}