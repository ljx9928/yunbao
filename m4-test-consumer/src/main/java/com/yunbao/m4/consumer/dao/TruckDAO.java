package com.yunbao.m4.consumer.dao;

import java.util.HashMap;
import java.util.Map;

import com.yunbao.m4.model.Product;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.util.LogUtil;

public class TruckDAO extends AbstractDAO<Product> {

	@Override
	public Product call() {
		LogUtil.debug(TruckDAO.class, "Calling TruckDAO...");
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", "1");
		Product p = super.getForObject(url + "?id=1", Product.class, param);
		return p;
	}

}
