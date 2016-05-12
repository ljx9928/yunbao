package com.yunbao.m4.producer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.yunbao.m4.model.House;

public class HouseController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("House");

		String houseId = paramHttpServletRequest.getParameter("id");
		if (StringUtils.isNotEmpty(houseId)) {
			model.addObject("house", getHouse(houseId));
		} else {
			model.addObject("house", getHouse("2"));
		}

		return model;
	}

	public House getHouse(String houseId) {
		House p = new House();
		p.setLocation("dalian number " + houseId);
		p.setDescription("this is a new house");
		return p;
	}

}
