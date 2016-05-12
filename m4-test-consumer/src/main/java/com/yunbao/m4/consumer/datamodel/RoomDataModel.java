package com.yunbao.m4.consumer.datamodel;

import java.util.Map;

import com.yunbao.m4.model.Room;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.datamodel.DataModel;

public class RoomDataModel implements DataModel {
	private AbstractDAO dao;

	private Room room;

	private Map param;

	@Override
	public <T> void processResponse(T s) {
		if (s instanceof Room) {
			this.room = (Room) s;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setParam(Map param) {
		this.param = param;
	}

	@Override
	public Map getParam() {
		return param;
	}
}
