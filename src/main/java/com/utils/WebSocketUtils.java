/**
 * 
 */
package com.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.chat.OneToOneMsg;

/**
 * @author bin
 * 
 */
public class WebSocketUtils {
	public static List<OneToOneMsg> orderOneToOneMsg(List<OneToOneMsg> list) {
		Date lastDate = null;
		OneToOneMsg lastMsg = null;// 迭代的上一个对象
		for (OneToOneMsg msg : list) {
			if (DateUtils.isEqualMinute(msg.getCreateDate(), lastDate)) {
				msg.setCreateDate(lastDate);
			} else {
				lastDate = msg.getCreateDate();
			}
			msg.setTimeRemark("1");
			if (lastMsg != null && DateUtils.isEqualMinute(msg.getCreateDate(), lastMsg.getCreateDate())) {
				lastMsg.setTimeRemark("0");
			}
			lastMsg = msg;
		}
		return list;
	}

	private static List<OneToOneMsg> getTestData() {
		List<OneToOneMsg> list = new ArrayList<OneToOneMsg>();
		try {
			list.add(new OneToOneMsg(1, 2, "5", CommConstant.YMDHMS_DATEFORMAT.parse("2017/12/19 22:45:11")));
			list.add(new OneToOneMsg(1, 2, "4", CommConstant.YMDHMS_DATEFORMAT.parse("2017/12/19 22:45:10")));
			list.add(new OneToOneMsg(1, 2, "3", CommConstant.YMDHMS_DATEFORMAT.parse("2017/12/19 22:44:21")));
			list.add(new OneToOneMsg(1, 2, "2", CommConstant.YMDHMS_DATEFORMAT.parse("2017/12/19 22:44:11")));
			list.add(new OneToOneMsg(1, 2, "1", CommConstant.YMDHMS_DATEFORMAT.parse("2017/12/19 22:43:11")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		List<OneToOneMsg> list = getTestData();
		orderOneToOneMsg(list);
	}
}
