package com.wangcai.common.web;

import org.springframework.web.bind.annotation.RequestMapping;

public class BaseController {
	protected String toJSP(String jspName) {
		return "forward:/WEB-INF/page/syinit/protal/portal.jsp";
	}
	
	private Object getAnnocation() {
		String head = this.getClass().getAnnotation(RequestMapping.class).value()[0];
		return null;
	}
}
