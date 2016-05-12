package com.yunbao.m4.platform.container;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

public class BaseContainer extends ModelAndView {

	private View view;

	public void buildPageContainer(Map argMap){
		
	}
	@Override
	public View getView() {

		return super.getView();
	}

	@Override
	public void setView(View view) {
		super.setView(view);
	}

}
