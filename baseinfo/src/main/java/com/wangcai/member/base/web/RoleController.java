package com.wangcai.member.base.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangcai.common.web.Page;
import com.wangcai.common.web.easyui.GridJson;
import com.wangcai.member.base.service.IRoleService;
import com.wangcai.member.base.vo.MemRole;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/tojsp.do")
	public Object toJsp(HttpServletRequest request) {
		return "forward:/WEB-INF/page/member/base/role.jsp";
	}
	
	
	/**
	 * @描述: 根据roleid获取role
	 *   @author 陆华
	 *  @da2015年3月30日 下午3:05:22
	 * @param req
	 * @param printWriter
	 */
	@RequestMapping("/getRole.do")
	public void getRole(HttpServletRequest req, PrintWriter printWriter) {
		Long roleid = Long.parseLong(req.getParameter("roleid"));
		MemRole role = roleService.getRoleById(roleid);
		
		printWriter.write(JSONArray.fromObject(role).toString());
		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * @描述: 获取role列表
	 *   @author 陆华
	 *  @da2015年3月30日 下午3:15:13
	 * @param req
	 * @param page
	 * @param printWriter
	 */
	@RequestMapping("/getRoles.do")
	public void getRoles(HttpServletRequest req, Page page, PrintWriter printWriter) {
		Map<String, Object> condition = new HashMap();
		condition.put("rolename", req.getParameter("rolename"));
		List list = roleService.getRoles(condition, page);
		GridJson gj = new GridJson();
		gj.setRows(list);
		gj.setTotal(page.getTotalRecord());
		JSONObject objet = JSONObject.fromObject(gj);
		printWriter.write(objet.toString());
		printWriter.flush();
		printWriter.close();
	}
	@RequestMapping("/savRoles.do")
	public void savRole(MemRole role, PrintWriter printWriter) {
		try {
			roleService.savRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/delRole.do")
	public void delRole(MemRole role, PrintWriter printWriter) {
		try {
			roleService.delRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
