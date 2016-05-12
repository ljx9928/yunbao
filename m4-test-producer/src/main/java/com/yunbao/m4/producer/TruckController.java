package com.yunbao.m4.producer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.yunbao.m4.model.Truck;

public class TruckController extends AbstractController {

	public Truck getTruck(String id) {
		Truck truck = new Truck();
		truck.setBrand("DongFeng");
		truck.setDriver("Auto Driver number " + id);
		return truck;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("Truck");
		String roomId = paramHttpServletRequest.getParameter("id");
		if (StringUtils.isNotEmpty(roomId)) {
			model.addObject("Truck", getTruck(roomId));
		} else {
			model.addObject("Truck", getTruck("3"));
		}
		return model;
	}

}