/**
 * 
 */
package com.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author bin
 *
 */
public class DateUtils {
	/**
	 * 判断两个日期是否相等（忽略秒以下的）
	 */
	public static boolean isEqualMinute(Date date,Date lastDate){
		if(lastDate == null || date == null){
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		Calendar lastCalendar = Calendar.getInstance();
		lastCalendar.setTime(lastDate);
		lastCalendar.set(Calendar.MILLISECOND, 0);
		lastCalendar.set(Calendar.SECOND, 0);
		return calendar.compareTo(lastCalendar) == 0;
	}
}
