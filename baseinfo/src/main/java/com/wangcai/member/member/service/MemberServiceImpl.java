package com.wangcai.member.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangcai.common.util.StringUtil;
import com.wangcai.common.web.easyui.EditableGridDataHelper;
import com.wangcai.member.member.dao.MemberMapper;
import com.wangcai.member.member.vo.Member;
import com.wangcai.member.member.vo.MemberExample;

@Service
public class MemberServiceImpl implements IMemberService {
	private MemberMapper memberMapper;

	public void savMember(EditableGridDataHelper<Member> member) {
		List<Member> members = member.getObjects();
		List<Member> insertUsers = new ArrayList();
		List<Member> updateMappers = new ArrayList();
		for (Member m : members) {
			if (StringUtil.isEmpty(m.getPassword())) {
				m.setPassword(StringUtil.getMD5("123456"));
			}
			if (m.getId() == null || m.getId() == 0) {
				insertUsers.add(m);
			} else {
				updateMappers.add(m);
			}
		}
		for (Member m : insertUsers) {
			memberMapper.insert(m);
		}
		for (Member m : updateMappers) {
			MemberExample e = new MemberExample();
			//e.createCriteria().andIdEqualTo(m.getId());
			//memberMapper.updateByExample(m, e);
		}
	}

	public void delMember() {
		// TODO Auto-generated method stub

	}

	public List<Member> getMembers() {
		return null;
	}

}
