package com.yunbao.m4.producer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.yunbao.m4.model.Room;

public class RoomController extends AbstractController {

	public Room getRoom(String id) {
		Room room = new Room();
		room.setOwner("User " + id);
		room.setSize("big");
		return room;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("room");
		// model.addObject("myshop", getShop());
		String roomId = paramHttpServletRequest.getParameter("id");
		if (StringUtils.isNotEmpty(roomId)) {
			model.addObject("room", getRoom(roomId));
		} else {
			model.addObject("room", getRoom("3"));
		}
		return model;
	}

}