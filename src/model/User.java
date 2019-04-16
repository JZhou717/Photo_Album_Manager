package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * This data structure represents a user
 * @author Jake, Tom
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
	private transient ObservableList<Album> myAlbums;
	
	/**
	 * ArrayList that is only used when the program is opened or closed
	 */
	private ArrayList<Album> serializable_album_list;
	
	/**
	 * List of preset tags that they can choose from or add to
	 */
	private ArrayList<String> tags = new ArrayList<String>(Arrays.asList("New Type", "Location", "Person"));
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
	
	/**
	 * returns the name of this user
	 */
	@Override
	public String toString() {
		return this.username;
	}
	
	/**
	 * retrieves the observable list of albums for this user when requested by the controller
	 * @return
	 */
	public ObservableList<Album> getAlbums(){
		return this.myAlbums;
	}
	
	/**
	 * Add a blank album to the user
	 * @param n the name of the album
	 * @return the Album instance of the new album
	 */
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
	
	/**
	 * Add an existing ablum to this user
	 * @param alb the Album to add
	 * @return The album that we just added
	 */
	public Album addAlbum(Album alb) {
		
		myAlbums.add(alb);
		return alb;
	}
	
	/**
	 * returns an array list of the tags of this user
	 * @return
	 */
	public ArrayList<String> getTags(){
		return this.tags;
	}
	
	/**
	 * Add a tag for this user
	 * @param n
	 */
	public void addTag(String n) {
		tags.add(n);
	}
	
	/**
	 * Remove an album of the given name from the user
	 * @param name the name of the album to remove
	 */
	public void removeAlbum(String name) {
		name = name.toLowerCase();
		String existingName;
		if (myAlbums.size()==0) {
			Alert alert = new Alert(AlertType.ERROR, "List empty. Album does not exist", ButtonType.OK);
			alert.show();
			return;
		}
		for (int i=0;i<myAlbums.size();i++) {
			existingName = myAlbums.get(i).getName().toLowerCase();
			if (name.equals(existingName)) {
				myAlbums.remove(i);
				return;
			}
		}
		Alert alert = new Alert(AlertType.ERROR, "Abum does not exist", ButtonType.OK);
		alert.show();
		return;
	}
	
	/**
	 * Get the album at the indicated index from the list of albums
	 * @param i index to get the album from
	 * @return the album at the index value
	 */
	public Album getAlbumAt(int i) {
		return myAlbums.get(i);
	}
	
	/**
	 * Get the album of the indicated name from the list of albums
	 * @param str the name of the album
	 * @return the al;bum with the input name value
	 */
	public Album getAlbumByName(String str) {
		String existing;
		String name = str.toLowerCase();
		if (myAlbums.size()==0) {
			Alert alert = new Alert(AlertType.ERROR, "List empty. Album does not exist", ButtonType.OK);
			alert.show();
			return null;
		}
		for (int i=0;i<myAlbums.size();i++) {
			existing = myAlbums.get(i).getName().toLowerCase();
			if (name.equals(existing)) {
				return myAlbums.get(i);
			}
		}
		Alert alert = new Alert(AlertType.ERROR, "Album does not exist", ButtonType.OK);
		alert.show();
		return null;
	}
	
	/**
	 * Gets the observable list of the album this user has when requested by the controller
	 * @return
	 */
	public ObservableList<Album> populateAlbumList() {
		return myAlbums;
	}
	
	/**
	 * Checks to see if the album of the given name exists for this user
	 * @param n the name of the album to check for
	 * @return true if the album exists for this user, false otherwise
	 */
	public boolean album_exists(String n) {
		
		String name = n.toLowerCase();
		
		String existing_name;
		
		for(int i = 0; i < myAlbums.size(); i++) {
			existing_name = myAlbums.get(i).getName().toLowerCase();
			if(name.equals(existing_name)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Moves data from the serializable ArrayList to the observable list we use for ListView
	 * Tells all albums of this user to restore data too
	 */
	public void retrieve_serialized_data() {
		myAlbums = FXCollections.observableArrayList(serializable_album_list);
		for(int i = 0; i < serializable_album_list.size(); i++) {
			myAlbums.get(i).retrieve_serialized_data();
		}
	}
	
	/**
	 * Saves the list of albums for this user into a serializable array list
	 * Tells the photos to do the same for their tags
	 */
	public void serialize() {
		serializable_album_list = new ArrayList<Album>(myAlbums);
		for(int i = 0; i < myAlbums.size(); i++) {
			myAlbums.get(i).serialize();
		}
	}
}