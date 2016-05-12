package com.yunbao.m4.consumer.datamodel;

import java.util.Map;

import com.yunbao.m4.model.Product;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.datamodel.DataModel;

public class CarDataModel implements DataModel {
	private AbstractDAO dao;

	private Product prod;

	private Map param;
	@Override
	public <T> void processResponse(T s) {
		if (s instanceof Product) {
			this.prod = (Product) s;
		}
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	@Override
	public <T> void setDao(AbstractDAO<T> dao) {
		this.dao = dao;

	}

	@Override
	public <T> AbstractDAO<T> getDao() {
		return dao;
	}

	public Map getParam() {
		return param;
	}

	public void setParam(Map param) {
		this.param = param;
	}

}
