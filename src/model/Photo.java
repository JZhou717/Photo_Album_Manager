package model;


import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


/**
 * This data structure represents a photo
 * @author Tom, Jake
 *
 */

public class Photo implements Serializable{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4246464515728856009L;
	
	/**
	 * The caption for this photo
	 */
	private String caption;
	
	/**
	 * The observable list of tags for this photo
	 */
	private transient ObservableList<Tag> tag_list;
	
	/**
	 * The list of tags used to save the observable list on serialization and on bootup
	 */
	private ArrayList<Tag> serializable_tag_list;
	
	/**
	 * The date this image was last modified
	 */
	private Calendar date;
	
	/**
	 * The image the Photo stores
	 */
	private transient Image image;
	
	/**
	 * The filepath of the image, necessary to retrieve the image on bootup
	 */
	private String filepath;
	
	/**
	 * The name of the photo
	 */
	private String name;
	
	/**
	 * Flag to see if the photo has a location
	 */
	private boolean hasLocation = false;
	
	/**
	 * Creates an instance of the photo given an image and the filepath to where it's stored
	 * @param i the image to add to the photo
	 * @param path the path the image came from
	 */
	public Photo(Image i, String path) {
		this.filepath = path;
		caption = "";
		tag_list = FXCollections.observableArrayList();
		date = Calendar.getInstance();
		date.set(Calendar.MILLISECOND, 0);
		image = i;
		
	}
	
	/**
	 * checks to see if this photo already has a location
	 * @return true if so, false otherwise
	 */
	public boolean getLocation() {
		return this.hasLocation;
	}
	
	/**
	 * Creates a new photo instance from the given filepath
	 * @param filepath where the image is
	 * @return Photo instance of the newly created photo
	 */
	public static Photo create_photo_by_path(String filepath) {
		
		try {
			File file = new File(filepath);
	        
        	String path = file.toURI().toString();
            Image image = new Image(path);
            Photo photo = new Photo(image, path);
            photo.setName(file.getName());
            photo.setDate(file.lastModified());
            return photo;
        }
        catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
		
	}
	
	/**
	 * Get the tags for this photo
	 * @return ObservableList of this Photo's tags
	 */
	public ObservableList<Tag> get_tags() {
		return this.tag_list;
	}
	
	/**
	 * Set the filepath where this image is stored
	 * @param path the path of the image
	 */
	public void set_filepath(String path) {
		filepath = path;
	}
	
	/**
	 * Retrieves this photo's caption
	 * @return
	 */
	public String getCaption() {
		return this.caption;
	}
	
	/**
	 * Adds a tag to this photo
	 * @param name of the tag
	 * @param value of the tag
	 */
	public void addTag(String name, String value) {
		tag_list.add(new Tag(name, value));
	}
	
	/**
	 * Removes a tag given its instance
	 * @param tag the tag to remove
	 */
	public void deleteTag(Tag tag) {
		tag_list.remove(tag);
	}
	
	/**
	 * Edits the caption to the input
	 * @param newCaption the new caption
	 */
	public void editCaption(String newCaption) {
		caption = newCaption;
	}
	
	/**
	 * Retrieves the date of the photo
	 * @return date
	 */
	public Calendar getDate() {
		return this.date;
	}
	
	/**
	 * Sets the date of the photo
	 * @param lastMod the millisecond representation of the photo's date
	 */
	public void setDate(long lastMod) {
		this.date.setTimeInMillis(lastMod);
		this.date.set(Calendar.MILLISECOND,0);
	}
	
	/**
	 * Retrieves the image the photo stores
	 * @return
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Take the list of tags back from the array list into the observable list
	 * Grabs the image from the file path
	 */
	public void retrieve_serialized_data() {
		// TODO Auto-generated method stub
		tag_list = FXCollections.observableArrayList(serializable_tag_list);
    	
        image = new Image(filepath);
				
	}
	
	/**
	 * Save the observable list of tags into an array list of tags
	 */
	public void serialize() {
		// TODO Auto-generated method stub
		serializable_tag_list = new ArrayList<Tag>(tag_list);
	}
		
	/**
	 * Checks to see if the input Photo is equal to this one
	 * @return true if they have the same filepath, false otherwise
	 */
	@Override
	public boolean equals(Object p) {
		if (p == null || !(p instanceof Photo)) {
			return false;
		}
		Photo other = (Photo) p;
		
		return this.filepath == other.getFilePath();
	}

	/**
	 * retrieves the filepath of the image this photo shows
	 * @return String of the filepath
	 */
	public String getFilePath() {
		return filepath;
	}
	
	/**
	 * returns the name of the photo
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Set the name of the photo to the input
	 * @param name the new name of the photo
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Check if this photo is in the given date range
	 * @param start the start of the range
	 * @param end the end of the range
	 * @return true if so, false otherwise
	 */
	public boolean in_date_range(LocalDate start, LocalDate end) {
		// TODO Auto-generated method stub
		
		
		Date date = this.getDate().getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
		if (start.compareTo(localDate) <= 0 && end.compareTo(localDate) >= 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * Checks to see if the input photo is older than the photo calling this function
	 * @param com the Photo to compare to
	 * @return true if so, false otherwise
	 */
	public boolean older(Photo com) {
		Date date = this.getDate().getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date comDate = com.getDate().getTime();
		LocalDate comLocalDate = comDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (localDate.compareTo(comLocalDate) <= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Checks to see if the input photo is newer than the photo calling this function
	 * @param com the Photo to compare to
	 * @return true if so, false otherwise
	 */
	public boolean newer(Photo com) {
		Date date = this.getDate().getTime();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Date comDate = com.getDate().getTime();
		LocalDate comLocalDate = comDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (localDate.compareTo(comLocalDate) >= 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Change the hasLocation flag of this photo to the opposite
	 */
	public void setLocation() {
		
		if (this.hasLocation == false) {
			this.hasLocation = true;
		}else {
			this.hasLocation = false;
		}
	}
	
	
}