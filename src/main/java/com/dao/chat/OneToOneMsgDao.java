/**
 * 
 */
package com.dao.chat;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.base.dao.BaseDao;
import com.base.dao.IBaseDao;
import com.base.entity.Page;
import com.entity.chat.FriendGroup;
import com.entity.chat.FriendsInGroup;
import com.entity.chat.OneToOneMsg;

/**
 * @author bin
 *
 */
@Repository
public class OneToOneMsgDao extends BaseDao<OneToOneMsg, Long> implements IBaseDao<OneToOneMsg, Long> {

	/**
	 * 获取与好友的聊天信息
	 * @param memberId
	 * @param firendMemberId
	 * @return
	 */
	public List<OneToOneMsg> getOneMsg(Page page,long memberId, long firendMemberId)throws Exception{
		String hql = "from OneToOneMsg where ((fromId = ? and toId = ?) or (fromId = ? and toId = ?)) " 
	                     + page.getWhereClause() + " order by id desc";
		Query query = super.getSession().createQuery(hql);
		query.setParameter(0, memberId);
		query.setParameter(1, firendMemberId);
		query.setParameter(2, firendMemberId);
		query.setParameter(3, memberId);
		query.setMaxResults(page.getLimit());
		return query.list();
	}
    public void test(){
    	System.out.println(get(2L));
    }
    public void update(){
    	/*String hql = "update DB_ONETOONEMSG set content = 'cccccccccc' where fromId = 1002";
    	Query query = super.getSession().createSQLQuery(hql);
    	query.executeUpdate();*/
    	System.out.println(get(1L));
    	System.out.println(get(2L));
    }
}
