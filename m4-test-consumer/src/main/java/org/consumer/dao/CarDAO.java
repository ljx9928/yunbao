package org.consumer.dao;

import org.m4.platform.dao.AbstractDAO;

import com.m4.domain.model.Product;

public class CarDAO extends AbstractDAO<Product> {

	@Override
	public Product call() {
		
		@SuppressWarnings("unchecked")
		Product p = super.getForObject(url+"?id={prodid}", Product.class, getParams());
		return p;
	}
}
