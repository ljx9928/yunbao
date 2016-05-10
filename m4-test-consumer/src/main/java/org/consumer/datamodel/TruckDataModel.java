package org.consumer.datamodel;

import java.util.Map;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;

import com.m4.domain.model.Product;
import com.m4.domain.model.Truck;

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
