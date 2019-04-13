package model;

import java.io.Serializable;
import java.util.ArrayList;

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
	private transient ObservableList<Album> myAlbums;
	
	/**
	 * ArrayList that is only used when the program is opened or closed
	 */
	private ArrayList<Album> serializable_album_list;
	
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
	
	public void removeAlbum(String name) {
		name.toLowerCase();
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
	
	public Album getAlbumAt(int i) {
		return myAlbums.get(i);
	}
	
	public Album getAlbumByName(String str) {
		String existing;
		str.toLowerCase();
		if (myAlbums.size()==0) {
			Alert alert = new Alert(AlertType.ERROR, "List empty. Album does not exist", ButtonType.OK);
			alert.show();
			return null;
		}
		for (int i=0;i<myAlbums.size();i++) {
			existing = myAlbums.get(i).getName().toLowerCase();
			if (str.equals(existing)) {
				return myAlbums.get(i);
			}
		}
		Alert alert = new Alert(AlertType.ERROR, "Album does not exist", ButtonType.OK);
		alert.show();
		return null;
	}
	
	public ObservableList<Album> populateAlbumList() {
		return myAlbums;
	}
	
	public void retrieve_serialized_data() {
		myAlbums = FXCollections.observableArrayList(serializable_album_list);
		for(int i = 0; i < serializable_album_list.size(); i++) {
			myAlbums.get(i).retrieve_serialized_data();
		}
	}
	
	public void serialize() {
		serializable_album_list = new ArrayList<Album>(myAlbums);
		for(int i = 0; i < myAlbums.size(); i++) {
			myAlbums.get(i).serialize();
		}
	}
}