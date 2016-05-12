package com.yunbao.m4.consumer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.yunbao.m4.consumer.delegate.ProdDelegate;
import com.yunbao.m4.platform.container.BaseContainer;

public class ProductController extends AbstractController {

	private ProdDelegate delegate;
	private BaseContainer container;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {

		//prepare params for DAO
		String shopId = paramHttpServletRequest.getParameter("shopid");
		Map<String, String> param = new HashMap<String, String>();
		param.put("shopid", shopId);

		delegate.process(param,container);
		return container;
	}

	public ProdDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(ProdDelegate delegate) {
		this.delegate = delegate;
	}

	public BaseContainer getContainer() {
		return container;
	}

	public void setContainer(BaseContainer container) {
		this.container = container;
	}

}
