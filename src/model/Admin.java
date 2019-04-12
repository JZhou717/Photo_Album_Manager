package model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * This data structure holds the list of Users in the system
 * @author Jake
 *
 */

public class Admin implements Serializable{

	/**
	 * Default Serial Version ID 
	 */
	private static final long serialVersionUID = 1L;	
	
	/**
	 * The list of users in the system
	 */
	private ObservableList<User> user_list;
	
	public ObservableList<User> populateUserList() {
		return user_list;
	}
	
	/**
	 * Creates a new Admin instance
	 */
	public Admin() {
		user_list = FXCollections.observableArrayList();
		user_list.add(new User("admin"));
	}
	
	/**
	 * Function to see if the username for the intended new user is already taken
	 * @param n String of the name of the user we're trying to add
	 * @return true if the username is already in the list (case insensitive), false if not
	 */
	public boolean user_exists(String n) {
		//Name of the user to add
		String name = n.toLowerCase();
		//Temp String to hold name of a user already in the list
		String existing_name;
		
		for(int i = 0; i < user_list.size(); i++) {
			existing_name = user_list.get(i).getName().toLowerCase();
			if(name.equals(existing_name)) {
				return true;
			}
		}
		//Went through the whole list without finding a match in the username
		return false;
	}
	
	/**
	 * Adds a user to the list
	 * @param n String of the name of the user
	 */
	public void add_user(String n) {
		// TODO Auto-generated method stub
		User new_user = new User(n);
		user_list.add(new_user);
	}
	
	/**
	 * removes a user from the list
	 * @param n String of the name of the user to remove
	 */
	public void remove_user(String n) {
		
		String name = n.toLowerCase();
		if(n.equals("admin")) {
			//cannot delete the admin
			Alert alert = new Alert(AlertType.ERROR, "Cannot delete admin", ButtonType.OK);
			alert.show();
			return;
		}
		
		String existing_name;
		
		//No users in system
		if(user_list.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "List empty. User does not exist", ButtonType.OK);
			alert.show();
			return;
		}
		else {
			for(int i = 0; i < user_list.size(); i++) {
				existing_name = user_list.get(i).getName().toLowerCase();
				if(name.equals(existing_name)) {
					user_list.remove(i);
					return;
				}
			}
			//Went through the whole list without finding the user
			//cannot delete the admin
			Alert alert = new Alert(AlertType.ERROR, "User does not exist", ButtonType.OK);
			alert.show();
			return;
		}
		
	}

	/**
	 * Gets the user at the given index
	 * @param index int value of the index of the user
	 * @return User at the index
	 */
	public User getUser(int index) {
		return user_list.get(index);
	}
	
	
	/**
	 * Serialize the data stored in admin
	 */
	public void serialize() {
		Alert alert = new Alert(AlertType.INFORMATION, "ADMIN SERIALIZING!!!", ButtonType.OK);
		alert.show();
	}
}