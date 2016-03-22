package com.wangcai.member.base.service;

import java.util.List;
import java.util.Map;

import com.wangcai.common.web.Page;
import com.wangcai.common.web.easyui.EditableGridDataHelper;
import com.wangcai.member.base.vo.MemRoleForCheckBox;
import com.wangcai.member.base.vo.MemUser;

/**
 * @描述: 系统用户业务管理
 * @author skm
 *
 */
public interface IMemUserService {
	MemUser getUserById(Long id);
	MemUser getUserByLogin(String login);
	List<MemUser> getUsers(Map<String, Object> condition, Page page);
	void delUser(MemUser user);
	void savUser(MemUser user);
	List<MemRoleForCheckBox> getRolesById(Long userid);
	void savUserRoles(Long userid, List<Long> urlroleList);
	void savUsers(EditableGridDataHelper<MemUser> helper);
	MemUser validUser(String parameter, String parameter2);
}
