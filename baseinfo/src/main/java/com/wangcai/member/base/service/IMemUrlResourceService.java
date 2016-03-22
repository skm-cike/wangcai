package com.wangcai.member.base.service;

import java.util.List;
import java.util.Map;

import com.wangcai.common.web.Page;
import com.wangcai.member.base.vo.MemRoleForCheckBox;
import com.wangcai.member.base.vo.MemUrlResource;
import com.wangcai.member.common.RequestToMethodItem;

/**
 * @描述:系统url资源业务管理
 * @author skm
 *
 */
public interface IMemUrlResourceService {
	List<MemUrlResource> getAllUrlResources();

	List<MemUrlResource> getUrls(Map<String, Object> condition, Page page);

	MemUrlResource getUrlById(Long resourceid);

	void delUrl(MemUrlResource resource);

	void savUrl(MemUrlResource resource);

	List<MemRoleForCheckBox> getRolesById(Long urlid);

	void savUrlRoles(Long urlid, List<Long> urlroleList);
	
	/**
	 * @描述: 初始化url资源
	 *   @author 陆华
	 *  @da2015年4月4日 下午4:50:35
	 * @param urlStrs
	 */
	void savInitUrls(List<RequestToMethodItem> items);

	List<MemUrlResource> getAllUrlResourcesForTree();
}
