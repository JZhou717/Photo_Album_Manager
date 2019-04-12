package model;


import java.io.Serializable;

import javafx.collections.ObservableList;


/**
 * This data structure represents a photo Album
 * @author Jake
 *
 */

public class Album implements Serializable{
	
	private String name;
	private ObservableList<Photo> myPhotos;
	
	public Album(String name) {
		this.name = name;
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
	
}