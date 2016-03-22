package com.wangcai.member.stock.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @描述: 商品管理
 * @author skm
 *
 */
@Controller
@RequestMapping("/member/stock/goodscategory")
public class GoodsCategoryController {
	@RequestMapping("/togoodscategory.do")
	public String fwdPage() {
		return "forward:/WEB-INF/page/member/stock/goodscategory.jsp";
	}
	
	
}
