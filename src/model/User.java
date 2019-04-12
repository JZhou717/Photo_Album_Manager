package model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This data structure represents a user
 * @author Jake
 *
 */

public class User implements Serializable{

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
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
	
}