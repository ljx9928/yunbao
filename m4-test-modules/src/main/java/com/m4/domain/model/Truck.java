package com.m4.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "truck")
public class Truck {
	private String brand;
	private String driver;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
