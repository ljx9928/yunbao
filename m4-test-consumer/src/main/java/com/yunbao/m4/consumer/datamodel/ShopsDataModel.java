package com.yunbao.m4.consumer.datamodel;

import java.util.HashMap;
import java.util.Map;

import com.yunbao.m4.model.Shop;
import com.yunbao.m4.model.Shops;
import com.yunbao.m4.platform.dao.AbstractDAO;
import com.yunbao.m4.platform.datamodel.DataModel;

public class ShopsDataModel implements DataModel {
	private AbstractDAO dao;
	private Map paramForNextModel = new HashMap();
	private Shop shop;

	@Override
	public <T> void processResponse(T t) {
		if (t instanceof Shops) {
			Shops shops = (Shops) t;

			for (Shop s : shops.getShops()) {
				if (s.getId().equals("1")) {
					this.shop = s;
					this.paramForNextModel.put("daoParams", s);
				}
			}
		} else if (t instanceof Shop) {
			if (((Shop) t).getId().equals("1")) {
				this.shop = (Shop) t;
				this.paramForNextModel.put("daoParams", t);
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
