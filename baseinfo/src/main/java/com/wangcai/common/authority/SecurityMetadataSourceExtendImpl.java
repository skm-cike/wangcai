package com.wangcai.common.authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * @描述: url资源元数据管理
 * @author skm
 *
 */
public class SecurityMetadataSourceExtendImpl implements FilterInvocationSecurityMetadataSource {
	public SecurityMetadataSourceExtendImpl() {

	}
	/**
	 * @描述: 资源文件初始化,系统初始化时配置
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		loadResourceDefine();
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	private void loadResourceDefine() {
		this.requestMap = new HashMap();
		loadUserAndResource.loadResourceDefine(requestMap);
	}

    public LoadUserAndResource getLoadUserAndResource() {
        return loadUserAndResource;
    }

    public void setLoadUserAndResource(LoadUserAndResource loadUserAndResource) {
        this.loadUserAndResource = loadUserAndResource;
    }

    private  Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	private LoadUserAndResource loadUserAndResource;
}
