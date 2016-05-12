package com.yunbao.m4.consumer.dao;

import com.yunbao.m4.model.Product;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.util.LogUtil;

public class CarDAO extends AbstractDAO<Product> {

	@Override
	public Product call() {
		LogUtil.debug(CarDAO.class, "Calling CarDAO...");
		@SuppressWarnings("unchecked")
		Product p = super.getForObject(url + "?id={prodid}", Product.class, getParams());
		return p;
	}
}
