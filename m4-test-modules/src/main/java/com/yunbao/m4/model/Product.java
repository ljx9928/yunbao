package com.yunbao.m4.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
	private String prodId;
	private String prodName;
	private String color;

	public String getProdId() {
		return prodId;
	}

	@XmlElement
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	@XmlElement
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getColor() {
		return color;
	}

	@XmlElement
	public void setColor(String color) {
		this.color = color;
	}

}
