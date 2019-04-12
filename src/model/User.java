package model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * This data structure represents a user
 * @author Jake
 *
 */

public class User implements Serializable{

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4206077079088077119L;
	
	/**
	 * Username
	 */
	private String username;
	
	/**
	 * This user's albums. Observable list to show in UserAblum Listview
	 */
	private ObservableList<Album> myAlbums;
	
	/**
	 * Constructor for a new user
	 * @param name The string username needed to create the user
	 */
	public User(String name) {
		this.username = name;
		this.myAlbums = FXCollections.observableArrayList();
	}
	
	/**
	 * Gets the name of the user
	 * @return String of the username
	 */
	public String getName() {
		return this.username;
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
	public ObservableList<Album> getAlbums(){
		return this.myAlbums;
	}
	public Album addAlbum(String n) {
		String name = n.trim();
		String existing_name;
		
		//Searching to see if this album name already exists for this user
		for(int i = 0; i < myAlbums.size(); i++) {
			existing_name = myAlbums.get(i).getName().toLowerCase();
			if(name.toLowerCase().equals(existing_name)) {
				Alert alert = new Alert(AlertType.ERROR, "album name already in use", ButtonType.OK);
				alert.show();
				return null;
			}
		}
		//Did not find an album with the same name
		Album new_album = new Album(name);
		myAlbums.add(new_album);
		return new_album;
		
		
	}
	public ObservableList<Album> populateAlbumList() {
	
		return myAlbums;
	}
}