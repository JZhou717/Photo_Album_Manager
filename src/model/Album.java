package model;


import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This data structure represents a photo Album
 * @author Jake
 *
 */

public class Album implements Serializable{
	
	private String name;
	private transient ObservableList<Photo> myPhotos;
	
	private ArrayList<Photo> serializable_photo_list;
	
	public Album(String name) {
		this.name = name;
		myPhotos = FXCollections.observableArrayList();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void rename(String rename) {
		this.name = rename;
	}
	
	public ObservableList<Photo> getPhoto(){
		return this.myPhotos;
	}
	
	public void addPhoto(Photo newPhoto){
		myPhotos.add(newPhoto);
	}
	
	public void deletePhoto(Photo unwanted) {
		myPhotos.remove(unwanted);
	}
	
	public String toString() {
		return this.name;
	}

	public void retrieve_serialized_data() {
		// TODO Auto-generated method stub
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