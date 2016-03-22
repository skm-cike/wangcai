package com.wangcai.member.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangcai.common.util.StringUtil;
import com.wangcai.common.web.Page;
import com.wangcai.member.base.dao.MemRoleMapper;
import com.wangcai.member.base.vo.MemRole;
import com.wangcai.member.base.vo.MemRoleExample;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private MemRoleMapper roleMapper;

	public MemRole getRoleById(Long roleid) {
		return roleMapper.selectByPrimaryKey(roleid);
	}

	public List<MemRole> getRoles(Map<String, Object> condition, Page page) {
		String rolename = (String)condition.get("rolename");
		MemRoleExample example = new MemRoleExample();
		example.setPage(page);
		if (!StringUtil.isEmpty(rolename)) {
			example.createCriteria().andRolenameEqualTo(rolename);
		}
		page.setTotalRecord(roleMapper.countByExample(example));
		return roleMapper.selectByExample(example);
	}

	public void savRole(MemRole role) {
		if (role.getRoleid() == null || role.getRoleid() == 0l) {
			role.setEnable(1);
			roleMapper.insert(role);
		} else {
			roleMapper.updateByPrimaryKey(role);
		}
	}

	public void delRole(MemRole role) {
		roleMapper.deleteByPrimaryKey(role.getRoleid());
	}
}
