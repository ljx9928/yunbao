package org.m4.platform.datamodel;

import java.util.Map;

import org.m4.platform.dao.AbstractDAO;

public interface DataModel {
	public <T> void processResponse(T t);
	public <T> void setDao(AbstractDAO<T> dao);
	public <T> AbstractDAO<T> getDao();
	
	public Map getParam();
}
