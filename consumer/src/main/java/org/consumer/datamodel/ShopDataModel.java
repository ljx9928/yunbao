package org.consumer.datamodel;

import java.util.HashMap;
import java.util.Map;

import org.m4.platform.dao.AbstractDAO;
import org.m4.platform.datamodel.DataModel;

import com.m4.domain.model.Shop;

public class ShopDataModel implements DataModel {
	private AbstractDAO dao;

	private Shop shop;
	private Map paramForNextModel = new HashMap();;

	@Override
	public <T> void processResponse(T t) {
		if (t instanceof Shop) {
			if (((Shop) t).getId().equals("1")) {
				this.shop = (Shop) t;
				this.paramForNextModel.put("prodid", ((Shop) t).getProdId());
			}
		}
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
		return paramForNextModel;
	}

}
