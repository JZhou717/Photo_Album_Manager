package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;


/**
 * This data structure represents a photo
 * @author Jake
 *
 */

public class Photo implements Serializable{
	
	private String caption;
	private ArrayList<Tag> tags;
	private Calendar date;
	private BufferedImage image;
	
	public Photo(BufferedImage i) {
		caption = "";
		tags = new ArrayList<Tag>();
		date = Calendar.getInstance();
			date.set(Calendar.MILLISECOND, 0);
		image = i;
		
	}
	
	public Photo create_photo_by_path(String filepath) throws IOException {
		File source = new File(filepath);
		BufferedImage image = ImageIO.read(source);
		return new Photo(image);
		
	}
	
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
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public ArrayList<Tag> getTags() {
		return this.tags;
	}
		
	
}