package com.m4.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "room")
public class Room {
	private String owner;
	private String size;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
