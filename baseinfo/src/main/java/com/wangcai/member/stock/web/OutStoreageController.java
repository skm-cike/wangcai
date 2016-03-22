package com.wangcai.member.stock.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/stock/outstoreage")
public class OutStoreageController{
	@RequestMapping("/topage.do")
	public String fwdOutStoreage() {
		return "forward:/WEB-INF/page/member/stock/outstoreage.jsp";
	}
	
	
}
