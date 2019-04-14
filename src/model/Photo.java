package model;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


/**
 * This data structure represents a photo
 * @author Jake
 *
 */

public class Photo implements Serializable{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4246464515728856009L;
	
	private String caption;
	private transient ObservableList<Tag> tag_list;
	private ArrayList<Tag> serializable_tag_list;
	private Calendar date;
	private transient Image image;
	private String filepath;
	private String name;
	
	public Photo(Image i, String path) {
		this.filepath = path;
		caption = "";
		tag_list = FXCollections.observableArrayList();
		date = Calendar.getInstance();
			date.set(Calendar.MILLISECOND, 0);
		image = i;
		
	}
	
	public static Photo create_photo_by_path(String filepath) {
		
		try {
			File file = new File(filepath);
	        
        	String path = file.toURI().toString();
            Image image = new Image(path);
            Photo photo = new Photo(image, path);
            photo.setName(file.getName());
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
	
	public void set_filepath(String path) {
		filepath = path;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public void addTag(String name, String value) {
		tag_list.add(new Tag(name, value));
	}
	
	public void deleteTag(Tag tag) {
		tag_list.remove(tag);
	}
	
	public void editCaption(String newCaption) {
		caption = newCaption;
	}
	
	public Calendar getDate() {
		return this.date;
	}
	
	public Image getImage() {
		return this.image;
	}

	public void retrieve_serialized_data() {
		// TODO Auto-generated method stub
		tag_list = FXCollections.observableArrayList(serializable_tag_list);
		
		File file = new File(filepath);
    	String path = file.toURI().toString();
        image = new Image(path);
				
	}
	
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
	public String toString() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean in_date_range(String start, String end) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}