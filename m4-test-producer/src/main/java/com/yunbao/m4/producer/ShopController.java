package com.yunbao.m4.producer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.yunbao.m4.model.Shop;
import com.yunbao.m4.model.Shops;

public class ShopController extends AbstractController {

	public Shop getShop() {
		Shop shop = new Shop();
		shop.setName("water shop");
		shop.setAddress("address 1");
		return shop;
	}

	public Shop getShop(String id) {
		Shop shop = new Shop();
		shop.setId(id);
		shop.setName("water shop");
		shop.setAddress("address 1");
		shop.setProdId("1");
		return shop;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("shopPage");
		// model.addObject("myshop", getShop());
		String shopId = paramHttpServletRequest.getParameter("id");
		if (StringUtils.isNotEmpty(shopId)) {
			model.addObject("shop", getShop(shopId));
		} else {
			model.addObject("shops", getShops());
		}
		return model;
	}

	private Shops getShops() {

		Shops shopes = new Shops();

		List shops = new ArrayList();

		Shop shop1 = new Shop();
		shop1.setId("1");
		shop1.setName("water shop");
		shop1.setAddress("address 1");

		Shop shop2 = new Shop();
		shop2.setId("2");
		shop2.setName("water shop2");
		shop2.setAddress("address 2");

		shops.add(shop1);
		shops.add(shop2);

		shopes.setShops(shops);
		return shopes;
	}

}