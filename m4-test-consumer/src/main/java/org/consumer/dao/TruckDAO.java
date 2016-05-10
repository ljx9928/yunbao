package org.consumer.dao;

import java.util.HashMap;
import java.util.Map;

import org.m4.platform.dao.AbstractDAO;

import com.m4.domain.model.Product;

public class TruckDAO extends AbstractDAO<Product> {

	@Override
	public Product call() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", "1");
		Product p = super.getForObject(url+"?id=1", Product.class, param);
		return p;
	}

}
