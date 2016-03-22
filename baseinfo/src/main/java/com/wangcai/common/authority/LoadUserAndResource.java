package com.wangcai.common.authority;

import com.wangcai.member.base.service.IMemUrlResourceService;
import com.wangcai.member.base.service.IMemUserService;
import com.wangcai.member.base.vo.MemUrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @描述: 查找user和其权限,以后可以修改 
 * @author skm
 *
 */
//XXX 各个不同的系统需要修改
public class LoadUserAndResource implements Runnable{
	private static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap;
	@Autowired
	private  IMemUserService userService;
	@Autowired
	private  IMemUrlResourceService urlResourceService;
	
	//FIXME
	public  UserDetails LoadUser(String name) {
		return (UserDetails)userService.getUserByLogin(name);
	}
	
	//FIXME
	public  void loadResourceDefine(Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap1) {
		resourceMap = resourceMap1;
		Thread thread = new Thread(this);
		thread.start();
	}

	public  void flushResourceDefine() {
		List<MemUrlResource> list = urlResourceService.getAllUrlResources();
		if (list != null) {
			resourceMap.clear();
			for (MemUrlResource res: list) {
				resourceMap.put(new AntPathRequestMatcher(res.getUrl()), res.getConfigAttributes());
			}
		}
	}

	public void run() {
		while (true) {
			flushResourceDefine();
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}
}
