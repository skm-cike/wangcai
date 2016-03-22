package com.wangcai.common.authority;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
/**
 * @描述: 权限决策管理器
 * @author skm
 *
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {
	/**
	 * @描述: 判断是否有读取该页面的权限，没有则抛出访问权限异常
	 */
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null){
			return;
		}
		Iterator<ConfigAttribute> ite=configAttributes.iterator();
		while(ite.hasNext()){
			ConfigAttribute ca=ite.next();
			try {
				String needRole=((SecurityConfig)ca).getAttribute();
				for(GrantedAuthority ga:authentication.getAuthorities()){
					if(needRole.equals(ga.getAuthority())){
						return;
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		throw new AccessDeniedException("no right");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
