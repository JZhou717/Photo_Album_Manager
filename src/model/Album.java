package model;


import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


/**
 * This data structure represents a photo Album
 * @author Jake
 *
 */

public class Album implements Serializable{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -5502974855922249071L;
	
	private String name;
	private transient ObservableList<Photo> myPhotos;
	private int size;
	private ArrayList<Photo> serializable_photo_list;
	
	public Album(String name) {
		this.name = name;
		myPhotos = FXCollections.observableArrayList();
	}
	public int size() {
		return this.size;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void rename(String rename) {
		this.name = rename;
	}
	
	public ObservableList<Photo> getPhotos(){
		return this.myPhotos;
	}
	public Photo getPhotoAt(int i) {
		return myPhotos.get(i);
	}
	
	
	
	public void addPhoto(Photo newPhoto){
		myPhotos.add(newPhoto);
	}
	
	public void deletePhotoAt(int i) {
		myPhotos.remove(i);
		
	}
	
	public String toString() {
		return this.name;
	}

	
	public ObservableList<Photo> populatePhotoList() {
		return myPhotos;
	}
	
	/**
	 * Moves data from the serializable ArrayList to the observable list we use for ListView
	 * Tells all Photos of this user to restore data too
	 */
	public void retrieve_serialized_data() {
		myPhotos = FXCollections.observableArrayList(serializable_photo_list);
		for(int i = 0; i < serializable_photo_list.size(); i++) {
			myPhotos.get(i).retrieve_serialized_data();
		}
		
	}
	
	public void serialize() {
		// TODO Auto-generated method stub
		serializable_photo_list = new ArrayList<Photo>(myPhotos);
		for(int i = 0; i < myPhotos.size(); i++) {
			myPhotos.get(i).serialize();
		}
	}
	
	
	
}