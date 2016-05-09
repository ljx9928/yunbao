package org.consumer.datamodel;

import java.util.Map;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;

import com.m4.domain.model.Desk;

public class DeskDataModel implements DataModel {
	private AbstractDAO dao;

	private Desk desk;

	private Map param;

	@Override
	public <T> void processResponse(T s) {
		if (s instanceof Desk) {
			this.desk = (Desk) s;
		}
	}

	@Override
	public <T> void setDao(AbstractDAO<T> dao) {
		this.dao = dao;

	}

	@Override
	public <T> AbstractDAO<T> getDao() {
		return dao;
	}

	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}

	public void setParam(Map param) {
		this.param = param;
	}

	@Override
	public Map getParam() {
		return param;
	}
}
