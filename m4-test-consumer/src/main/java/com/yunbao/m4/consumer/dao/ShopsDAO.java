package com.yunbao.m4.consumer.dao;

import com.yunbao.m4.model.Shops;
import com.yunbao.m4.platform.dao.AbstractDAO;

public class ShopsDAO extends AbstractDAO<Shops> {
	@Override
	public Shops call() {
		
		Shops ps = super.getForObject(url, Shops.class);
		return ps;
	}
}