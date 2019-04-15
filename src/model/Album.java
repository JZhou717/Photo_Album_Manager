package model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
	
	public Album(String name, ObservableList<Photo> photo_list) {
		this.name = name;
		myPhotos = photo_list;
	}
	
	public int size() {
		return this.size;
	}
	
	public void set_size(int s) {
		this.size = s;
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
		size++;
		
	}
	
	public void deletePhotoAt(int i) {
		myPhotos.remove(i);
		size--;
		
	}
	
	public String toString() {
		Date newDate = this.getNewest().getDate().getTime();
		Date oldDate = this.getOldest().getDate().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return this.name + "\nSize: " + this.size + "\nRange: "+ sdf.format(oldDate) + "-" + sdf.format(newDate);
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
	public Photo getOldest() {
		if (myPhotos.size()<1) {
			return null;
		}
		Photo oldest = myPhotos.get(0);
		for (int i=0; i<myPhotos.size();i++) {
			if (myPhotos.get(i).older(oldest)) {
				oldest = myPhotos.get(i);
			}
		}
		return oldest;
	}
	public Photo getNewest() {
		if (myPhotos.size()<1) {
			return null;
		}
		Photo newest = myPhotos.get(0);
		for (int i=0; i<myPhotos.size();i++) {
			if (myPhotos.get(i).newer(newest)) {
				newest = myPhotos.get(i);
			}
		}
		return newest;
	}
	public void serialize() {
		// TODO Auto-generated method stub
		serializable_photo_list = new ArrayList<Photo>(myPhotos);
		for(int i = 0; i < myPhotos.size(); i++) {
			myPhotos.get(i).serialize();
		}
	}
	
	
	
}