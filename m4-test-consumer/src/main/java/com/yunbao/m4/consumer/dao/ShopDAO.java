package com.yunbao.m4.consumer.dao;

import java.util.HashMap;
import java.util.Map;

import com.yunbao.m4.model.Shop;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.util.LogUtil;

public class ShopDAO extends AbstractDAO<Shop> {
	@Override
	public Shop call() {
		LogUtil.debug(ShopDAO.class, "Calling ShopDAO...");
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", "1");

		Shop p = super.getForObject(url+"?id={id}", Shop.class, param);
		return p;
	}
}
