package com.wangcai.member.base.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangcai.common.web.Page;
import com.wangcai.common.web.easyui.EditableGridDataHelper;
import com.wangcai.common.web.easyui.GridJson;
import com.wangcai.common.web.easyui.SuccJson;
import com.wangcai.member.base.service.IMemUserService;
import com.wangcai.member.base.vo.MemRoleForCheckBox;
import com.wangcai.member.base.vo.MemUser;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IMemUserService userService;
	@RequestMapping("/touser.do")
	public String toJsp() {
		return "forward:/WEB-INF/page/member/base/user.jsp";
	}
	
	
	
	@RequestMapping("/getUsers.do")
	@ResponseBody
	public Object getUsers(HttpServletRequest req, Page page) {
		
		Map<String, Object> condition = new HashMap();
		condition.put("login", req.getParameter("login"));
		condition.put("username", req.getParameter("username"));
		condition.put("cardid", req.getParameter("cardid"));
		List<MemUser> list = userService.getUsers(condition, page);
		return new GridJson(page.getTotalRecord(), list);
	}
	
	@RequestMapping("/login")
	public void validUser(HttpServletRequest req , HttpServletResponse res) {
		MemUser user = userService.validUser(req.getParameter("login"),  req.getParameter("password"));
		System.out.println("===================***************************");
	}
	
	@RequestMapping("/getUserById")
	public void getUserById(HttpServletRequest req, PrintWriter printWriter) {
		Long userid = Long.parseLong(req.getParameter("userid"));
		MemUser user = userService.getUserById(userid);
		printWriter.write(JSONObject.fromObject(user).toString());
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping("/delUser")
	public void delUser(MemUser user, PrintWriter printWriter) {
		try {
			userService.delUser(user);
			SuccJson json = new SuccJson();
			json.setSuccess(true);
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			SuccJson json = new SuccJson();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		}
	}
	
	@RequestMapping("/savUser.do")
	public void savUser(MemUser user, PrintWriter printWriter) {
		try {
			userService.savUser(user);
			SuccJson json = new SuccJson();
			json.setSuccess(true);
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			SuccJson json = new SuccJson();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		}
	}
	
	@RequestMapping("/savUsers.do")
	public void savUsers(HttpServletRequest req, PrintWriter printWriter) {
		try {
			String jsonstr = req.getParameter("data");
			EditableGridDataHelper<MemUser> helper = EditableGridDataHelper.data2bean(jsonstr, MemUser.class);
			userService.savUsers(helper);
			SuccJson json = new SuccJson();
			json.setSuccess(true);
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			SuccJson json = new SuccJson();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		}
	}
	
	@RequestMapping("/getRoles.do")
	public void getRoles(HttpServletRequest req, PrintWriter printWriter) {
		Long userid = Long.parseLong(req.getParameter("userid"));
		List<MemRoleForCheckBox> roles = userService.getRolesById(userid);
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		for (MemRoleForCheckBox b: roles) {
			sb.append("<input type='checkbox' name='userroles' value='" + b.getRole().getRoleid() + "' ");
			if (b.isCheck()) {
				sb.append(" checked='checked' ");
			}
			sb.append(">" + b.getRole().getRolename() + "</input>");
		}
		
		sb.append("</div>");
		Map<String, String> map = new HashMap();
		map.put("data", sb.toString());
		printWriter.write(JSONObject.fromObject(map).toString());
		printWriter.flush();
		printWriter.close();
	}
	@RequestMapping("/savUserRoles.do")
	public void savUrlRoles(HttpServletRequest req, PrintWriter printWriter) {
		Long userid  = Long.parseLong(req.getParameter("userid"));
		String userroles = req.getParameter("userroles");
		List<Long> userroleList = new ArrayList();
		String[] strs = userroles.split(",");
		for (String s: strs) {
			userroleList.add(Long.parseLong(s));
		}
		try {
			userService.savUserRoles(userid, userroleList);
			SuccJson json = new SuccJson();
			json.setSuccess(true);
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			SuccJson json = new SuccJson();
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			printWriter.write(JSONObject.fromObject(json).toString());
			printWriter.flush();
			printWriter.close();
		}
	}
}
