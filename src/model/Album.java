package model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This data structure represents a photo Album
 * @author Tom, Jake
 *
 */

public class Album implements Serializable{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -5502974855922249071L;
	
	/**
	 * name of the album
	 */
	private String name;
	
	/**
	 * Observable list of the photos for this album
	 */
	private transient ObservableList<Photo> myPhotos;
	
	/**
	 * Size of the album
	 */
	private int size;
	
	/**
	 * The serializable array list used to store myPhotos on boot down
	 */
	private ArrayList<Photo> serializable_photo_list;
	
	/**
	 * Create a new album of the given name with no photos
	 * @param name the name of the album to add
	 */
	public Album(String name) {
		this.name = name;
		myPhotos = FXCollections.observableArrayList();
	}
	
	/**
	 * Create a new album of the given name with the given list of photos taken from search results
	 * @param name the album name
	 * @param photo_list the result of a search for a certain type of photo
	 */
	public Album(String name, ObservableList<Photo> photo_list) {
		this.name = name;
		myPhotos = photo_list;
		this.size = photo_list.size();
	}
	
	/**
	 * retrieves the size of the album
	 * @return size
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Sets the size of the album
	 * @param s size to set it to
	 */
	public void set_size(int s) {
		this.size = s;
	}
	
	/**
	 * Retrieves the name of the album
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Changes the name of the album
	 * @param rename the new name to set this ablum to
	 */
	public void rename(String rename) {
		this.name = rename;
	}
	
	/**
	 * Retrieves the observable list of Photos when the controller requests it
	 * @return this album's photos
	 */
	public ObservableList<Photo> getPhotos(){
		return this.myPhotos;
	}
	
	/**
	 * Gets the photo at a certain index location in the list. Should correspond to the index from the list view listener
	 * @param i index number
	 * @return Photo instance in that index
	 */
	public Photo getPhotoAt(int i) {
		return myPhotos.get(i);
	}
	
	/**
	 * Adds a photo to the album
	 * @param newPhoto the photo to add to the list
	 */
	public void addPhoto(Photo newPhoto){
		myPhotos.add(newPhoto);
		size++;
		
	}
	
	/**
	 * removes the photo from the list
	 * @param i index of the photo to remove
	 */
	public void deletePhotoAt(int i) {
		myPhotos.remove(i);
		size--;
		
	}
	
	/**
	 * Gives a string of this album's size, and range of dates from the date on the photo least recently modified to the date on the one most recently modified
	 */
	public String toString() {
		if (this.myPhotos.size()<1) {
			return this.name + "\nSize: 0";
		}
		Date newDate = this.getNewest().getDate().getTime();
		Date oldDate = this.getOldest().getDate().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		return this.name + "\nSize: " + this.size + "\nRange: "+ sdf.format(oldDate) + "-" + sdf.format(newDate);
	}

	/**
	 * A redundant function since we forgot we already had getPhotos
	 * @return the list of photos in this albums
	 */
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
	
	/**
	 * Gets the Photo least recently modified
	 * @return the Photo in question
	 */
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
	
	/**
	 * Gets the photo most recently modified
	 * @return the Photo in question
	 */
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
	
	/**
	 * Saves the observable list to a serializable array list
	 * Tells the photos in this album to do the same for their list of tags
	 */
	public void serialize() {
		// TODO Auto-generated method stub
		serializable_photo_list = new ArrayList<Photo>(myPhotos);
		for(int i = 0; i < myPhotos.size(); i++) {
			myPhotos.get(i).serialize();
		}
	}
	
	
	
}