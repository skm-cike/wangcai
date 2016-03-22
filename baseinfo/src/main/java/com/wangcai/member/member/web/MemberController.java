package com.wangcai.member.member.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.wangcai.common.web.easyui.EditableGridDataHelper;
import com.wangcai.member.member.service.IMemberService;
import com.wangcai.member.member.vo.Member;

public class MemberController {
	private IMemberService memberService;

	/***
	 * 会员管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/memberManage.do")
	public Object memberManage(HttpServletRequest request) {
		return "forward:/WEB-INF/page/member/memberManage.jsp";
	}

	/***
	 * 会员消费管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/memberConsumption.do")
	public Object memberConsumption(HttpServletRequest request) {
		return "forward:/WEB-INF/page/member/memberConsumption.jsp";
	}
	@RequestMapping("/memberSave.do")
	public void memberSave(HttpServletRequest request) {
		String json = request.getParameter("data");
		EditableGridDataHelper helper = EditableGridDataHelper.data2bean(json, Member.class);
		memberService.savMember(helper);
	}
}
