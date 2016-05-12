package com.yunbao.m4.consumer.datamodel;

import java.util.Map;

import com.yunbao.m4.model.Product;
import com.yunbao.m4.model.Truck;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.datamodel.DataModel;

public class TruckDataModel implements DataModel {
	private AbstractDAO dao;

	private Truck truck;

	@Override
	public <T> void processResponse(T s) {
		if (s instanceof Product) {
			Truck t = new Truck();
			t.setBrand(((Product) s).getColor());
			t.setDriver(((Product) s).getProdName());
			this.truck = t;
		}
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
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
