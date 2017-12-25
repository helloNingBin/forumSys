package com.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.exception.RegisterException;
import com.base.service.impl.BaseService;
import com.dao.common.MemberDao;
import com.entity.LoginTrace;
import com.entity.Member;
import com.service.member.MemberServiceInterface;

@Service
public class MemberService extends BaseService<Member, Long> implements MemberServiceInterface{
	@Autowired
	private MemberDao dao;
	@Override
	public void saveLogin(LoginTrace loginTrace) throws Exception {
		super.getSession().save(loginTrace);
	}

	@Override
	public boolean saveMember(Member member) throws RegisterException,Exception {
		//验证手机是否被使用
		long phoneCount = dao.countByProperty("phone",member.getPhone());
		if(phoneCount != 0){
			throw new RegisterException(member.getPhone() + "，该手机号已经被使用！");
		}
		//TODO 所有字段都需要在后台校验
		return this.save(member);
	}

	@Override
	public Member checkLogin(String phone, String loginPwd) throws Exception {
		// TODO 加密
		return dao.checkLogin(phone,loginPwd);
	}

	@Override
	public void saveTest() throws Exception {
		this.save(new Member());
		throw new Exception("测试。。。。。。。。。。。。。");
		
	}
}
