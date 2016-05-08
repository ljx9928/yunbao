package org.consumer.dao;

import org.m4.platform.dao.AbstractDAO;

import com.m4.domain.model.Product;

public class TruckDAO extends AbstractDAO<Product> {

	@Override
	public Product call() {
		Product p = super.getForObject(url, Product.class);
		System.out.println(((com.m4.domain.model.Product) p).getProdName());
		return p;
	}

}
