package org.consumer.dao;

import org.m4.platform.dao.AbstractDAO;

import com.m4.domain.model.Shops;

public class ShopsDAO extends AbstractDAO<Shops> {
	@Override
	public Shops call() {
		
		Shops ps = super.getForObject(url, Shops.class);
		return ps;
	}
}