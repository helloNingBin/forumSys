package com.service.member;

import com.base.exception.RegisterException;
import com.base.service.BaseServiceInterface;
import com.entity.LoginTrace;
import com.entity.Member;

public interface MemberServiceInterface extends BaseServiceInterface<Member, Long>{
   void saveLogin(LoginTrace loginTrace)throws Exception;
   /**
    * 注册会员
   * @param member
   * @return
   * @throws RegisterException  注册验证时，抛出的异常
   * @throws Exception
   */
   boolean saveMember(Member member)throws RegisterException,Exception;

	/**
	 * 登录时，检查账号密码
	 * @param phone   用手机作为登录账号
	 * @param loginPwd  密码（加密）
	 * @return
	 * @throws Exception
	 */
   Member checkLogin(String phone, String loginPwd) throws Exception;
   void saveTest()throws Exception;
}
