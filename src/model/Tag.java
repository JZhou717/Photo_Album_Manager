package model;

import java.io.Serializable;

/**
 * This model represents a tag that can be added to Photos
 * @author Tom, Jake
 *
 */

public class Tag implements Serializable{
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -4817523966395760834L;
	
	/**
	 * The type of the tag
	 */
	private String name;
	
	/**
	 * The value of the tag
	 */
	private String value;
	
	/**
	 * Creates a new tag instance with the given type and value
	 * @param name type of the tag
	 * @param value the value to store in this tag
	 */
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Returns the type of the tag
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the value in the tag
	 * @return
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets the type of the tag
	 * @param name new tag type to set it to
	 */
	public void setName(String name) {
		this.name = name;
	}		
	
	/**
	 * Sets the contents of the tag
	 * @param value the new content of the tag
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * returns a string of the tag_type, tag_value
	 */
	public String toString() {
		return name + ", " + value;
	}
		
}