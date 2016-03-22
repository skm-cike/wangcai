package com.wangcai.common.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
/**
 * @描述:　记录日志
 * @author skm
 *
 */
public class LogRecordInterceptor implements WebRequestInterceptor{
	private List<String> urlCache = new ArrayList(100);
	public void afterCompletion(WebRequest arg0, Exception arg1)
			throws Exception {
		
	}

	public void postHandle(WebRequest arg0, ModelMap arg1) throws Exception {
		
	}

	public void preHandle(WebRequest arg0) throws Exception {
		
	}
}
