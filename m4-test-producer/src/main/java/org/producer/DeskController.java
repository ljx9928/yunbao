package org.producer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.m4.domain.model.Desk;

public class DeskController extends AbstractController {


	public Desk getDesk(String id) {
		Desk desk = new Desk();
		desk.setColor("Red");
		desk.setName("desk number"+id);
		return desk;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("deskPage");
		String deskId = paramHttpServletRequest.getParameter("id");
		if (StringUtils.isNotEmpty(deskId)) {
			model.addObject("desk", getDesk(deskId));
		} else {
			model.addObject("desk", getDesk("1"));
		}
		return model;
	}


}