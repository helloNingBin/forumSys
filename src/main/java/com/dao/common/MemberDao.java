package com.dao.common;


import java.math.BigInteger;

import com.base.dao.BaseDao;
import com.entity.LoginTrace;
import com.entity.Member;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao extends BaseDao<Member, Long> {
	@Override
	public boolean save(Member entity) {
		super.save(entity);
		return true;
	}

	/**
	 * 根据账号，密码查询会员
	 * @param phone   手机号
	 * @param loginPwd 登录密码
	 */
	public Member checkLogin(String phone, String loginPwd) {
		String hql = "from Member where phone = ? and loginPwd = ?";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, phone);
		query.setParameter(1, loginPwd);
		return (Member) query.uniqueResult();
	}
	/**
	 * 是否是好友
	 * @param memberId       登录会员id
	 * @param firendMemberId 好友id
	 * @return
	 * @throws Exception
	 */
	public boolean isFirend(long memberId,long firendMemberId)throws Exception{
		String sql = "select count(1) from DB_FRIENDSINGROUP f where f.memberId = ? and f.firendMemberId = ?";
		Query query = super.getSession().createSQLQuery(sql);
		query.setParameter(0, memberId);
		query.setParameter(1, firendMemberId);
		return ((BigInteger)query.uniqueResult()).compareTo(BigInteger.ZERO) == 0 ? false : true;
	}
}