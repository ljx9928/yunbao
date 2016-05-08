package org.consumer.datamodel;

import java.util.Map;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;

import com.m4.domain.model.Product;

public class TruckDataModel implements DataModel {
	private AbstractDAO dao;

	private Product prod;

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

	@Override
	public Map getParam() {
		// TODO Auto-generated method stub
		return null;
	}

}
