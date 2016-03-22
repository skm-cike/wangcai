package com.wangcai.common.authority;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
/**
 * @描述: 用户管理器
 * @author skm
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{
	private final Log log = LogFactory.getLog(getClass());
	@Autowired
	private LoadUserAndResource loadUserAndResource;
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = null;
		try {
			user = (UserDetails)loadUserAndResource.LoadUser(username);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (user == null) {
			throw new UsernameNotFoundException("用户没有找到!");
		}
		return user;
	}
}
