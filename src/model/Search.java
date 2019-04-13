package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.PhotosController;

/**
 * This model runs the search functions
 * @author Jake
 *
 */

public class Search{

	public static ObservableList<Photo> tag_search_results() {
		
		ObservableList<Photo> ret = FXCollections.observableArrayList();
		User current_user = PhotosController.get_admin().getUserByName(PhotosController.get_user());
		ObservableList<Album> user_albums = current_user.getAlbums();
		Album album;
		
		//Going through each album of the user
		for(int i = 0; i < user_albums.size(); i++) {
			album = user_albums.get(i);
			//Going through each Photo in the album
			for(int j = 0; j < album.size(); j++) {
				
			}
		}
		
		return ret;
	}
	
	
}