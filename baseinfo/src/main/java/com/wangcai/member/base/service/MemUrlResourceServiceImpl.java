package com.wangcai.member.base.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import com.wangcai.common.util.StringUtil;
import com.wangcai.common.web.Page;
import com.wangcai.member.base.dao.MemRoleMapper;
import com.wangcai.member.base.dao.MemUrlResourceMapper;
import com.wangcai.member.base.dao.MemUrlRoleMapper;
import com.wangcai.member.base.vo.MemRole;
import com.wangcai.member.base.vo.MemRoleExample;
import com.wangcai.member.base.vo.MemRoleForCheckBox;
import com.wangcai.member.base.vo.MemUrlResource;
import com.wangcai.member.base.vo.MemUrlResourceExample;
import com.wangcai.member.base.vo.MemUrlResourceExample.Criteria;
import com.wangcai.member.base.vo.MemUrlRoleExample;
import com.wangcai.member.base.vo.MemUrlRoleKey;
import com.wangcai.member.common.RequestToMethodItem;

/**
 * @描述: url资源业务管理
 * @author skm
 *
 */
@Service
public class MemUrlResourceServiceImpl implements IMemUrlResourceService{
	@Autowired
	private MemUrlResourceMapper urlResourceMapper;
	@Autowired
	private MemUrlRoleMapper urlRoleMapper;
	@Autowired
	private MemRoleMapper roleMapper;

	public List<MemUrlResource> getAllUrlResources() {
		//获取所有url资源
        MemUrlResourceExample mure = new MemUrlResourceExample();
		mure.createCriteria().andEnableEqualTo(1);
		List<MemUrlResource> resource = urlResourceMapper.selectByExample(mure);
		//获取url  authority 中间表
		List<Long> ids = new ArrayList(resource.size());
		for (MemUrlResource r: resource) {
			ids.add(r.getId());
		}
		if (ids.size() != 0) {
			MemUrlRoleExample urlauthexample = new MemUrlRoleExample();
			urlauthexample.createCriteria().andResourceidIn(ids);
			List<MemUrlRoleKey> list = urlRoleMapper.selectByExample(urlauthexample);
			Set<Long> roleids = new HashSet(100);
			for (MemUrlRoleKey ak: list) {
				roleids.add(ak.getRoleid());
			}
			//获取角色
			List<MemRole> roles = null;
			if (roleids.size() != 0) {
				MemRoleExample roleExample  = new MemRoleExample();
				roleExample.createCriteria().andRoleidIn(new ArrayList(roleids));
				roles = roleMapper.selectByExample(roleExample);
			} else {
				roles = new ArrayList();
			}
			//往url资源中拼接权限
			Map<Long, Set<Long>> map = new HashMap();  //键是url id, 值是其下权限id
			List<MemUrlRoleKey> clearList = new ArrayList();
			for (Long urlid: ids)  {
				clearList.clear();
				if (map.get(urlid) == null) {map.put(urlid, new HashSet());}
				for (MemUrlRoleKey middle: list) {
					if (middle.getResourceid().equals(urlid)) {
						map.get(urlid).add(middle.getRoleid());
						clearList.add(middle);
					}
				}
				list.removeAll(clearList);
			}
			
			for (MemUrlResource res: resource) {
				res.setConfigAttributes(new HashSet<ConfigAttribute>());
				Collection<ConfigAttribute> coll = res.getConfigAttributes();
				Set<Long> set = map.get(res.getId());
				for (MemRole a: roles) {
					if (set.contains(a.getRoleid())) {
						SecurityConfig sc = new SecurityConfig(a.getRolename());
						coll.add(sc);
					}
				}
			}
		}
		return resource;
	}
	

	public List<MemUrlResource> getUrls(Map<String, Object> condition, Page page) {
		String url = (String)condition.get("url");
		String urldesc =  (String)condition.get("urldesc");
		MemUrlResourceExample example = new MemUrlResourceExample();
		Criteria cri = example.createCriteria().andIdIsNotNull();
		if (!StringUtil.isEmpty(url)) {
			cri.andUrlLike(url + "%");
		}
		if (!StringUtil.isEmpty(urldesc)) {
			cri.andUrldescLike(urldesc + "%");
		}
		page.setTotalPage(urlResourceMapper.countByExample(example));
		return urlResourceMapper.selectByExample(example);
	}

	public MemUrlResource getUrlById(Long resourceid) {
		return urlResourceMapper.selectByPrimaryKey(resourceid);
	}

	public void delUrl(MemUrlResource resource) {
		urlResourceMapper.deleteByPrimaryKey(resource.getId());
	}

	public void savUrl(MemUrlResource resource) {
		if (resource.getId() == null || resource.getId() == 0) {
			resource.setEnable(1);
			urlResourceMapper.insert(resource);
		} else {
			urlResourceMapper.updateByPrimaryKey(resource);
		}
	}



	public List<MemRoleForCheckBox> getRolesById(Long urlid) {
		MemUrlRoleExample urlauthexample = new MemUrlRoleExample(); 
		 urlauthexample.createCriteria().andResourceidEqualTo(urlid);
		 List<MemUrlRoleKey> urlroles = this.urlRoleMapper.selectByExample(urlauthexample);
		 List<MemRole> roles =  this.roleMapper.selectByExample(new MemRoleExample());
		 List<MemRoleForCheckBox> roleForcheck = new ArrayList(roles.size());
		 for (MemRole role: roles) {
			 MemRoleForCheckBox cb = new MemRoleForCheckBox();
			 cb.setRole(role);
			 roleForcheck.add(cb);
			 MemUrlRoleKey k = null;
			 for (MemUrlRoleKey key: urlroles) {
				 if (role.getRoleid().equals(key.getRoleid())) {
					 k = key;
					 break;
				 }
			 }
			 if (k != null) {
				 cb.setCheck(true);
				 urlroles.remove(k);
			 }
		 }
		return roleForcheck;
	}



	public void savUrlRoles(Long urlid, List<Long> urlroleList) {
		MemUrlRoleExample example = new MemUrlRoleExample();
		example.createCriteria().andResourceidEqualTo(urlid);
		this.urlRoleMapper.deleteByExample(example);
		Set<MemUrlRoleKey> set = new HashSet();
		for (Long roleid: urlroleList) {
			MemUrlRoleKey k = new MemUrlRoleKey();
			k.setResourceid(urlid);
			k.setRoleid(roleid);
			set.add(k);
		}
		for (MemUrlRoleKey k: set) {
			urlRoleMapper.insert(k);
		}
	}
	
	/**
	 * @描述: 初始化和更新url资源
	 */
	public void savInitUrls(List<RequestToMethodItem> items) {
		MemUrlResourceExample mure = new MemUrlResourceExample();
		mure.createCriteria().andEnableNotEqualTo(-1);
		List<MemUrlResource> allUrls = urlResourceMapper.selectByExample(mure);
		List<MemUrlResource> insertList = new ArrayList();
		List<MemUrlResource> updateList = new ArrayList();
		for (int i = 0; i < allUrls.size(); i++) {
			MemUrlResource temp = allUrls.get(i);
			if (StringUtil.isEmpty(temp.getUrl())) {
				continue;
			}
			boolean update = false;
			RequestToMethodItem item = null;
			for (int j = 0; j < items.size(); j++) {
				if (temp.getUrl().equals(items.get(j).requestUrl)) {
					if (StringUtil.isEmpty(temp.getUrlname())) {
						temp.setUrlname("未定义");
						update = true;
					}
					if (!(items.get(j).controllerName + "." + items.get(j).methodName).equals(temp.getMetthodname())) {
						temp.setMetthodname(items.get(j).controllerName + "." + items.get(j).methodName);
						update = true;
					}
					item = items.get(j);
					break;
				}
			}
			
			if (item == null) {
				temp.setEnable(-1);
			} else {
				items.remove(item);
			}
			if (update) {
				updateList.add(temp);
			}
		}
		
		for (RequestToMethodItem item: items) {
			MemUrlResource urlres = new MemUrlResource();
			urlres.setEnable(1);
			urlres.setMetthodname(item.controllerName + "." + item.methodName);
			urlres.setUrl(item.requestUrl);
			urlres.setUrldesc("");
			urlres.setUrlname("未定义");
			insertList.add(urlres);
		}
		
		//保存
		for (MemUrlResource urlre: updateList) {
			this.urlResourceMapper.updateByPrimaryKey(urlre);
		}
		for (MemUrlResource urlre: insertList) {
			this.urlResourceMapper.insert(urlre);
		}
	}


	public List<MemUrlResource> getAllUrlResourcesForTree() {
		MemUrlResourceExample mure = new MemUrlResourceExample();
		mure.createCriteria().andEnableEqualTo(1);
		List<MemUrlResource> resource = urlResourceMapper.selectByExample(mure);
		return resource;
	}
}
