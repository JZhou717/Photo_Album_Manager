package model;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -4817523966395760834L;
	
	private String name;
	private String value;
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return this.name;
	}
	public String getValue() {
		return this.value;
	}
	public void setName(String name) {
		this.name = name;
	}		
	public void setValue(String value) {
		this.value = value;
	}
	public String toString() {
		return name + ", " + value;
	}
		
}