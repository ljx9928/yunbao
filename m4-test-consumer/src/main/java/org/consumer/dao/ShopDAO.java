package org.consumer.dao;

import java.util.HashMap;
import java.util.Map;

import org.m4.platform.dao.AbstractDAO;

import com.m4.domain.model.Shop;

public class ShopDAO extends AbstractDAO<Shop> {
	@Override
	public Shop call() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", "1");

		Shop p = super.getForObject(url+"?id={id}", Shop.class, param);
		return p;
	}
}
