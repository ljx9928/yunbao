package com.yunbao.m4.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "desk")
public class Desk {
	private String color;
	private String name;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
