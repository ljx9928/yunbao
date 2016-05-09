package com.m4.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "house")
public class House {

	private String location;
	private String description;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
