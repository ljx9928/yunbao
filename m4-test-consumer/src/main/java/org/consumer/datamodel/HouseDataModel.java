package org.consumer.datamodel;

import java.util.Map;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;

import com.m4.domain.model.House;

public class HouseDataModel implements DataModel {
	private AbstractDAO dao;

	private House house;

	private Map param;
	
	@Override
	public <T> void processResponse(T s) {
		if (s instanceof House) {
			this.house = (House) s;
		}
	}

	@Override
	public <T> void setDao(AbstractDAO<T> dao) {
		this.dao=dao;
		
	}

	@Override
	public <T> AbstractDAO<T> getDao() {
		return dao;
	}

	 


	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public void setParam(Map param) {
		this.param = param;
	}

	@Override
	public Map getParam() {
		return param;
	}
}
