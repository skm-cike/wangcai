package com.wangcai.member.base.web;

import com.wangcai.common.web.Page;
import com.wangcai.common.web.easyui.GridJson;
import com.wangcai.common.web.easyui.SuccJson;
import com.wangcai.common.web.easyui.TreeGridJson;
import com.wangcai.member.base.service.IMemUrlResourceService;
import com.wangcai.member.base.vo.MemRoleForCheckBox;
import com.wangcai.member.base.vo.MemUrlResource;
import com.wangcai.member.common.RequestToMethodItem;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/urlresource")
//@EnableAutoConfiguration
public class UrlResourceController {
	@Autowired
	private IMemUrlResourceService urlResourceService;
	@RequestMapping("/urlresource.do")
	public String toJsp() {
		return "forward:/WEB-INF/page/member/base/urlresource.jsp";
	}
	@RequestMapping("/getUrls.do")
	public void getUrls(HttpServletRequest req, Page page, PrintWriter printWriter) {
		Map<String, Object> condition = new HashMap();
		condition.put("url", req.getParameter("url"));
		condition.put("urldesc", req.getParameter("urldesc"));
		List<MemUrlResource> list = urlResourceService.getUrls(condition, page);
		GridJson gj = new GridJson();
		gj.setRows(list);
		gj.setTotal(page.getTotalRecord());
		JSONObject objet = JSONObject.fromObject(gj);
		printWriter.write(objet.toString());
		printWriter.flush();
		printWriter.close();
	}
	@RequestMapping("/getUrlById.do")
	public void getUrlById(HttpServletRequest req, PrintWriter printWriter) {
		Long resourceid = Long.parseLong(req.getParameter("resourceid"));
		MemUrlResource resource = urlResourceService.getUrlById(resourceid);
		printWriter.write(JSONObject.fromObject(resource).toString());
		printWriter.flush();
		printWriter.close();
	}
	
	@RequestMapping("/delUrl.do")
	public void delUrl(MemUrlResource resource, PrintWriter printWriter) {
		try {
			urlResourceService.delUrl(resource);
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
	
	@RequestMapping("/savUrl.do")
	public void savUrl(MemUrlResource resource, PrintWriter printWriter) {
		try {
			urlResourceService.savUrl(resource);
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
		Long urlid = Long.parseLong(req.getParameter("urlid"));
		List<MemRoleForCheckBox> roles = urlResourceService.getRolesById(urlid);
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		for (MemRoleForCheckBox b: roles) {
			sb.append("<input type='checkbox' name='urlroles' value='" + b.getRole().getRoleid() + "' ");
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
	@RequestMapping("/savUrlRoles.do")
	public void savUrlRoles(HttpServletRequest req, PrintWriter printWriter) {
		Long urlid  = Long.parseLong(req.getParameter("urlid"));
		String urlroles = req.getParameter("urlroles");
		List<Long> urlroleList = new ArrayList();
		String[] strs = urlroles.split(",");
		for (String s: strs) {
			urlroleList.add(Long.parseLong(s));
		}
		try {
			urlResourceService.savUrlRoles(urlid, urlroleList);
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
	
	@RequestMapping(value="/init.do", method = RequestMethod.POST)
	@ResponseBody
	public Object initUrls(HttpServletRequest request) {
		ServletContext servletContext = request.getSession().getServletContext();
		if (servletContext == null) {
			return null;
		}
		WebApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext); // 请求url和处理方法的映射
		List<RequestToMethodItem> requestToMethodItemList = new ArrayList<RequestToMethodItem>(); // 获取所有的RequestMapping
		Map<String, HandlerMapping> allRequestMappings = BeanFactoryUtils
				.beansOfTypeIncludingAncestors(appContext,
						HandlerMapping.class, true, false);
		for (HandlerMapping handlerMapping : allRequestMappings.values()) { // 本项目只需要RequestMappingHandlerMapping中的URL映射
			if (handlerMapping instanceof RequestMappingHandlerMapping) {
				RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
				Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping
						.getHandlerMethods();
				for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods
						.entrySet()) {
					RequestMappingInfo requestMappingInfo = requestMappingInfoHandlerMethodEntry
							.getKey();
					HandlerMethod mappingInfoValue = requestMappingInfoHandlerMethodEntry
							.getValue();
					RequestMethodsRequestCondition methodCondition = requestMappingInfo
							.getMethodsCondition();
//					String requestType = SetUtils.first(
//							methodCondition.getMethods()).name();
//					PatternsRequestCondition patternsCondition = requestMappingInfo
//							.getPatternsCondition();
//					String requestUrl = SetUtils.first(patternsCondition
//							.getPatterns());
					//String requestType = ((RequestMethod)(new ArrayList(methodCondition.getMethods()).get(0))).name();
					String requestType = null;
					PatternsRequestCondition patternsCondition = requestMappingInfo
							.getPatternsCondition();
					String requestUrl = new ArrayList<String>(patternsCondition.getPatterns()).get(0);
					String controllerName = mappingInfoValue.getBeanType()
							.toString();
					String requestMethodName = mappingInfoValue.getMethod()
							.getName();
					Class<?>[] methodParamTypes = mappingInfoValue.getMethod()
							.getParameterTypes();
					RequestToMethodItem item = new RequestToMethodItem(
							requestUrl, requestType, controllerName,
							requestMethodName, methodParamTypes);
					requestToMethodItemList.add(item);
				}
				break;
			}
		}
		
		try {
			this.urlResourceService.savInitUrls(requestToMethodItemList);
			return new SuccJson(true);
		} catch (Exception e) {
			e.printStackTrace();
			return  new SuccJson(false, e.getMessage());
		}
	}
	
	@RequestMapping(value="/getTreeGrid.do", method=RequestMethod.POST)
	@ResponseBody
	public Object getUrlToTreeGridJson(HttpServletRequest request) {
		List<MemUrlResource> resources = urlResourceService.getAllUrlResourcesForTree();
		return new TreeGridJson(resources.size(), resources, null);
	}

//    public static void main(String[] args) {
//        SpringApplication.run(UrlResourceController.class, args);
//    }
}
