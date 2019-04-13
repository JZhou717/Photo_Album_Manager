package model;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


/**
 * This data structure represents a photo
 * @author Jake
 *
 */

public class Photo implements Serializable{
	
	private String caption;
	private transient ObservableList<Tag> tag_list;
	private ArrayList<Tag> serializable_tag_list;
	private Calendar date;
	private Image image;
	
	public Photo(Image i) {
		caption = "";
		tag_list = FXCollections.observableArrayList();
		date = Calendar.getInstance();
			date.set(Calendar.MILLISECOND, 0);
		image = i;
		
	}
	
	public static Photo create_photo_by_path(String filepath) throws IOException {
		//File source = new File(filepath);
		//BufferedImage image = ImageIO.read(source);
		Image image = new Image(filepath);
		return new Photo(image);
		
	}
	
	public String getCaption() {
		return this.caption;
	}
	
	public void addTag(String name, String value) {
		tag_list.add(new Tag(name, value));
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
				
	}
	
	public void serialize() {
		// TODO Auto-generated method stub
		serializable_tag_list = new ArrayList<Tag>(tag_list);
	}
		
	
}