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

	public static ObservableList<Photo> tag_search_results(String type, String value) {
		
		ObservableList<Photo> ret = FXCollections.observableArrayList();
		
		User current_user = PhotosController.get_admin().getUserByName(PhotosController.get_user());
		Album album;
		ObservableList<Album> user_albums = current_user.getAlbums();
		Photo photo;
		ObservableList<Tag> photo_tags;
		Tag tag;
		
		//Going through each Album of the User
		for(int i = 0; i < user_albums.size(); i++) {
			album = user_albums.get(i);
			//Going through each Photo in the Album
			for(int j = 0; j < album.size(); j++) {
				photo = album.getPhotoAt(j);
				photo_tags = photo.get_tags();
				//Going through each Tag in the Photo
				for(int k = 0; k < photo_tags.size(); k++) {
					//See if there is a match on the tag type
					tag = photo_tags.get(i);
					
				}
			}
		}
		
		return ret;
	}
	
	
}