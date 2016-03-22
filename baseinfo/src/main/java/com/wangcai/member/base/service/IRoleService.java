package com.wangcai.member.base.service;

import java.util.List;
import java.util.Map;

import com.wangcai.common.web.Page;
import com.wangcai.member.base.vo.MemRole;

public interface IRoleService {
	
	MemRole getRoleById(Long roleid);

	List<MemRole> getRoles(Map<String, Object> condition, Page page);

	void savRole(MemRole role);

	void delRole(MemRole role);
}
