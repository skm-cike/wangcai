package com.wangcai.member.member.service;

import java.util.List;

import com.wangcai.common.web.easyui.EditableGridDataHelper;
import com.wangcai.member.member.vo.Member;

public interface IMemberService {
	void savMember(EditableGridDataHelper<Member> members);

	void delMember();

	List<Member> getMembers();
}
