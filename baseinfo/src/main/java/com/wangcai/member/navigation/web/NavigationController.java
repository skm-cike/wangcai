package com.wangcai.member.navigation.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @描述: 商品管理
 * @author skm
 *
 */
@Controller
@RequestMapping("/member")
public class NavigationController {
	@RequestMapping("/welcome.do")
	public String fwdPage() {
		return "forward:/WEB-INF/page/member/index.jsp";
	}
	
	
}
