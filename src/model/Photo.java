package model;


import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


/**
 * This data structure represents a photo Album
 * @author Jake
 *
 */

public class Photo{
	
	private String caption;
	private ArrayList<Tag> tags;
	private Calendar date;
	private Image image;
	
	public String getCaption() {
		return this.caption;
	}
	public void addTag(String name, String value) {
		tags.add(new Tag(name, value));
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
	public ArrayList<Tag> getTags() {
		return this.tags;
	}
		
	
}