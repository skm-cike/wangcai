package com.wangcai.member.base.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.wangcai.common.util.ObjectUtil;
import com.wangcai.common.util.StringUtil;
import com.wangcai.common.web.Page;
import com.wangcai.common.web.easyui.EditableGridDataHelper;
import com.wangcai.member.base.dao.MemRoleMapper;
import com.wangcai.member.base.dao.MemUserMapper;
import com.wangcai.member.base.dao.MemUserRoleMapper;
import com.wangcai.member.base.vo.MemRole;
import com.wangcai.member.base.vo.MemRoleExample;
import com.wangcai.member.base.vo.MemRoleForCheckBox;
import com.wangcai.member.base.vo.MemUser;
import com.wangcai.member.base.vo.MemUserExample;
import com.wangcai.member.base.vo.MemUserExample.Criteria;
import com.wangcai.member.base.vo.MemUserRoleExample;
import com.wangcai.member.base.vo.MemUserRoleKey;

/**
 * @描述: 系统用户业务管理
 * @author skm
 *
 */
@Service
public class MemUserServiceImpl implements IMemUserService{
	@Autowired
	private MemUserMapper userMapper;
	@Autowired
	private MemRoleMapper roleMapper;
	@Autowired
	private MemUserRoleMapper userroleMapper;
	public MemUser getUserById(Long id) {
		MemUser user = userMapper.selectByPrimaryKey(id);
		if (user.getEnable() == -1) {
			return null;
		}
		return user;
	}

	public MemUser getUserByLogin(String login) {
		MemUserExample example = new MemUserExample();
		example.createCriteria().andLoginEqualTo(login);
		List<MemUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return null;
		}

		MemUser user = list.get(0);
		MemUserRoleExample userRoleExample = new MemUserRoleExample();
		userRoleExample.createCriteria().andUseridEqualTo(user.getId());
		List<MemUserRoleKey> keys = this.userroleMapper.selectByExample(userRoleExample);
		Set<Long> roleids = new HashSet(keys.size());
		for (MemUserRoleKey key: keys) {
			roleids.add(key.getRoleid());
		}
		MemRoleExample roleExample = new MemRoleExample();
		roleExample.createCriteria().andRoleidIn(new ArrayList(roleids));
		List<MemRole> roles = this.roleMapper.selectByExample(roleExample);
		user.setAuthorities(new HashSet(roles));
		return user;
	}

	public List<MemUser> getUsers(Map<String, Object> condition, Page page) {
		MemUserExample example = new MemUserExample();
		Criteria cri = example.createCriteria();
		String login = (String)condition.get("login");
		String username = (String)condition.get("username");
		String cardid = (String)condition.get("cardid");
		if (!StringUtil.isEmpty(login)) {
			cri.andLoginLike(login + "%");
		}
		if (!StringUtil.isEmpty(username)) {
			cri.andUsernameLike(username + "%");
		}
		if (!StringUtil.isEmpty(cardid)) {
			cri.andCardidLike(cardid + "%");
		}
		
		//只查找有效的行
		List val = new ArrayList<Integer>();
		val.add(0);
		val.add(1);
		cri.andEnableIn(val);
		page.setTotalRecord(userMapper.countByExample(example));
		return userMapper.selectByExample(example);
	}

	
	public void delUser(MemUser user) {
		//userMapper.deleteByPrimaryKey(user.getId());
		MemUser u = userMapper.selectByPrimaryKey(user.getId());
		u.setEnable(-1);
		userMapper.updateByPrimaryKey(u);
	}

	public void savUser(MemUser user) {
		MemUser olduser = userMapper.selectByPrimaryKey(user.getId());
		ObjectUtil.objcetMerge(olduser, user);
		userMapper.updateByPrimaryKey(user);
	}

	public List<MemRoleForCheckBox> getRolesById(Long userid) {
		List<MemRole> allroles = roleMapper.selectByExample(new MemRoleExample());
		
		MemUserRoleExample urexample = new MemUserRoleExample();
		urexample.createCriteria().andUseridEqualTo(userid);
		List<MemUserRoleKey>  userroleskey = this.userroleMapper.selectByExample(urexample);
		List<MemRoleForCheckBox> list = new ArrayList(allroles.size());
		List<MemUserRoleKey> clearKey = new ArrayList(userroleskey.size());
		for (MemRole role: allroles) {
			MemRoleForCheckBox box = new MemRoleForCheckBox();
			box.setRole(role);
			list.add(box);
			for (MemUserRoleKey key: userroleskey) {
				if (role.getRoleid().equals(key.getRoleid())) {
					box.setCheck(true);
					clearKey.add(key);
					break;
				}
			}
			userroleskey.removeAll(clearKey);
		}
		return list;
	}

	public void savUserRoles(Long userid, List<Long> urlroleList) {
		Set<Long> urlroleset = new HashSet(urlroleList);
		List<MemUserRoleKey> userRole = new ArrayList(urlroleset.size());
		for (Long u: urlroleset) {
			MemUserRoleKey key = new MemUserRoleKey();
			key.setRoleid(u);
			key.setUserid(userid);
			userRole.add(key);
		}
		for (MemUserRoleKey key: userRole) {
			this.userroleMapper.insert(key);
		}
	}

	public void savUsers(EditableGridDataHelper<MemUser> helper) {
		List<MemUser> savUser = helper.getObjects();
		List<MemUser> insertUser = new ArrayList();
		List<MemUser> updateUser = new ArrayList();
		for (MemUser user: savUser) {
			if (user.getId() == null || user.getId() == 0) {
				user.setPassword("123456");
				user.setAccountnonexpired(1);
				user.setAccountnonlocked(1);
				user.setEnable(1);
				user.setCredentialsnonexpired(1);
				insertUser.add(user);
			} else {
				updateUser.add(user);
			}
		}
		for (MemUser user: insertUser) {
			this.userMapper.insert(user);
		}
		for (MemUser user: updateUser) {
			this.userMapper.updateByPrimaryKey(user);
		}
	}

	public MemUser validUser(String login, String password) {
		MemUserExample example = new MemUserExample();
        if (login == null || password == null) {
            return null;
        }
		example.createCriteria().andLoginEqualTo(login).andPasswordEqualTo(password);
		List<MemUser> users = userMapper.selectByExample(example);
		if (users == null || users.size() != 1) {
			return null;
		}
		return users.get(0);
	}
}
