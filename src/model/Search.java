package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import view.PhotosController;

/**
 * This model runs the search functions
 * @author Jake
 *
 */

public class Search{
	
	/**
	 * Merges two lists while removing photos that were added twice due to AND or OR
	 * @param list1 the ObservableList of Photos for the first tag
	 * @param list2 the ObservableList of Photos for the second tag
	 * @return ObservableList of Photos without duplicates
	 */
	public static ObservableList<Photo> merge_lists(ObservableList<Photo> list1, ObservableList<Photo> list2) {
		
		ObservableList<Photo> ret = list1;
		Photo photo2;
		
		//Going through everything in list2
		for(int i = 0; i < list2.size(); i ++) {
			photo2 = list2.get(i);
			//Checking to see if list1 already has this photo
			if(list_has_photo(ret, photo2)) {
				continue;
			}
			else {
				ret.add(photo2);
			}
		}
		
		return ret;
		
	}
	
	/**
	 * Checks to see if the photo given is in the list already
	 * @param list the ObservableList of Photos that we are checking
	 * @param photo the Photo we are checking
	 * @return true if the list already has the photo, false otherwise
	 */
	private static boolean list_has_photo(ObservableList<Photo> list, Photo photo) {
		
		//A photo from the list
		Photo list_photo;
		
		for(int i = 0; i < list.size(); i++) {
			list_photo = list.get(i);
			if(list_photo.equals(photo)) {
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * Checks the format of the input dates and gets the list of results using {@link date_search_results()}
	 * @param start The start of the date range
	 * @param end The end of the date range
	 * @return observable list of photos that were last modified within the date range
	 */
	public static ObservableList<Photo> search_date(String start, String end) {
		
		// Check the format of the date here
		
		
		//format checked, getting actual results
		return date_search_results(start, end);
	}


	/**
	 * Parses the tag type and tag value and runs {@link #tag_search_results()} if the values are valid
	 * @param tag the full tag value in the format of tag_type=tag_value
	 * @return the observable list of photos of the results of the search
	 */
	public static ObservableList<Photo> search_tag(String tag) {
		//Parse tag into tag type and tag_value
		String tag_type;
		String tag_value;
		String[] split_tag = tag.split("=", 2);
		//If there was no comma
		if(split_tag.length < 2) {
			Alert alert = new Alert(AlertType.ERROR, "Please input a valid pair of values: tag_type=tag_value", ButtonType.OK);
			alert.show();
			return null;
		}
		
		//If the tag_type is empty
		tag_type = split_tag[0].trim();
		if(tag_type.equals("")) {
			Alert alert = new Alert(AlertType.ERROR, "Please input non empty tag_type value", ButtonType.OK);
			alert.show();
			return null;
		}
		
		//If the tag_value is empty
		tag_value = split_tag[1].trim();
		if(tag_value.equals("")) {
			Alert alert = new Alert(AlertType.ERROR, "Please input non empty tag_value value", ButtonType.OK);
			alert.show();
			return null;
		}
		//if there are more than one comma
		if(tag_value.indexOf("=") != -1) {
			Alert alert = new Alert(AlertType.WARNING, "You have inputted text with more than one equals sign, all text after the first equals sign will now be considered the tag_value", ButtonType.OK);
			alert.show();
		}
		
		//Run the search and get a list of the photos with these tags
		return tag_search_results(tag_type, tag_value);
	}
	
	/**
	 * Checks to see if the current user has any photos with last modified date between the input values
	 * @param start the start of the range of dates to check
	 * @param end the end of the range of dates to check
	 * @return ObservableList of Photos that were last modified within the dates
	 */
	private static ObservableList<Photo> date_search_results(String start, String end) {
		
		ObservableList<Photo> ret = FXCollections.observableArrayList();
		
		User current_user = PhotosController.get_admin().getUserByName(PhotosController.get_user());
		
		Album album;
		ObservableList<Album> user_albums = current_user.getAlbums();
		Photo photo;
		
		//Going through each Album of the user
		for(int i = 0; i < user_albums.size(); i++) {
			album = user_albums.get(i);
			//Going through each Photo in the Album
			for(int j = 0; j < album.size(); j++) {
				photo = album.getPhotoAt(j);
				if(photo.in_date_range(start, end)) {
					ret.add(photo);
				}
			}
		}
		
		return ret;
		
	}
	
	/**
	 * Checks to see if the current user has any photos with the tag input
	 * @param type String type of the tag
	 * @param value String  value of the tag
	 * @return ObservableList of Photos that has the tag
	 */
	public static ObservableList<Photo> tag_search_results(String type, String value) {
		
		ObservableList<Photo> ret = FXCollections.observableArrayList();
		
		//Gets the current user
		User current_user = PhotosController.get_admin().getUserByName(PhotosController.get_user());
		
		Album album;
		ObservableList<Album> user_albums = current_user.getAlbums();
		Photo photo;
		ObservableList<Tag> photo_tags;
		
		//Going through each Album of the User
		for(int i = 0; i < user_albums.size(); i++) {
			album = user_albums.get(i);
			
			//Going through each Photo in the Album
			for(int j = 0; j < album.size(); j++) {
				photo = album.getPhotoAt(j);
				photo_tags = photo.get_tags();
				if(photo_has_match(photo_tags, type, value)) {
					ret.add(photo);
					break;
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Checks to see if there is a matching tag in the photo_tags list with the tag given
	 * @param photo_tags the list of tags that we are checking through
	 * @param type the type of the tag we are looking for
	 * @param value the value of the tag we are looking for
	 * @return true if there is a match in the list, false otherwise
	 */
	private static boolean photo_has_match(ObservableList<Tag> photo_tags, String type, String value) {
		
		Tag tag;
		//Going through each Tag in the Photo
		for(int i = 0; i < photo_tags.size(); i++) {
			tag = photo_tags.get(i);
			//See if there is a match on the tag type
			if(tag.getName().equalsIgnoreCase(type)) {
				//See if there is a match on the tag value
				if(tag.getValue().equalsIgnoreCase(value)) {
					return true;
				}
			}
		}
		
		return false;
		
	}
		
}