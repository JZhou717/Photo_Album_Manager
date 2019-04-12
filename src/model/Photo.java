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
		
	}
	
		
	
}