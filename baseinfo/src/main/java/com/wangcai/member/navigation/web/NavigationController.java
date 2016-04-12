package com.wangcai.member.navigation.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @描述: 商品管理
 * @author skm
 *
 */
@Controller
@RequestMapping("/member")
@EnableAutoConfiguration
public class NavigationController {
	@RequestMapping("/welcome.do")
    @ResponseBody
	public String fwdPage() {
		return "forward:/WEB-INF/page/member/index.jsp";
	}

    public static void main(String[] args) {
        SpringApplication.run(NavigationController.class, args);
    }
}
