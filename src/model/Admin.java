package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.PhotosController;

/**
 * This data structure holds the list of Users in the system
 * @author Jake
 *
 */

public class Admin implements Serializable{

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = -3044287036009027432L;
	
	/**
	 * The list of users in the system
	 */
	private transient ObservableList<User> user_list;
	
	/**
	 * ArrayList that is only used when the program is opened or closed
	 */
	private ArrayList<User> serializable_user_list;
	
	/**
	 * The file we are serializing to
	 */
	public static final String storeFile = "Admin.dat";
	
	public ObservableList<User> populateUserList() {
		return user_list;
	}
	
	/**
	 * Creates a new Admin instance
	 */
	public Admin() {
		if(user_list == null) {
			System.out.println("user_list not read properly from serialization");
			user_list = FXCollections.observableArrayList();
			user_list.add(new User("admin"));
		}
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
	
	public User getUserByName(String n) {
		
		String name = n.toLowerCase().trim();
		String existing_name;
		
		User ret;
		
		if(user_list.size() == 0) {
			Alert alert = new Alert(AlertType.ERROR, "List empty. User does not exist", ButtonType.OK);
			alert.show();
			return null;
		}
		else {
			for(int i = 0; i < user_list.size(); i++) {
				existing_name = user_list.get(i).getName().toLowerCase().trim();
				if(name.equals(existing_name)) {
					ret = user_list.get(i);
					return ret;
				}
			}
		}
		//Could not find the user in the list
		Alert alert = new Alert(AlertType.ERROR, "User does not exist", ButtonType.OK);
		alert.show();
		return null;
		
		
	}
	
	/*
	 * Reads the serialized data back into the program
	 * @throws IOException if something is wrong with the file we are trying to access
	 * @throws ClassNotFoundExceptin if something is wrong with a model we are trying to read
	 */
	public static Admin retrieve_serialized_data() throws IOException, ClassNotFoundException{
		
		Admin ret;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(PhotosController.storeDir + File.separator + storeFile));
			ret = (Admin) ois.readObject();
			
			//user_list = FXCollections.observableArrayList(serializable_user_list);
			
			return ret;
		}
		catch(EOFException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Serialize the data stored in admin
	 * @throws IOException if something goes wrong with serializing
	 */
	public void serialize() throws IOException{
		
		serializable_user_list = new ArrayList<User>(user_list);
		
		ObjectOutputStream oos = new ObjectOutputStream(
			new FileOutputStream(PhotosController.storeDir + File.separator + storeFile));
		oos.writeObject(this);
		oos.close();
	}
}