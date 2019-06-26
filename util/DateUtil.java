package com.demo.util;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.datetime.DateFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sx-9129 2016/10/18/0018
 *         日期操作相关的工具
 */
@Slf4j
public class DateUtil {


	/**
	 * @param createTime 传入的日期
	 * @param count      时间间隔
	 * @return
	 * @author sx-9129 2016/10/18/
	 * 根据传入的日期与时间间隔，计算过期的时间
	 */
	public static Date calExpiredTime(Date createTime, int stepType, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createTime);
		calendar.add(stepType, count);
		return calendar.getTime();
	}

	/**
	 * @param count 提前月的数量
	 * @return 月的范围
	 * @author 丁峰
	 * 计算前count个月的范围，如果月为0，代表当月
	 */
	public static Date[] getLastetMonthN(int count) {
		Date[] date = new Date[2];
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -count);
		int minMonDay = calendar.getMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, minMonDay);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		date[0] = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		int maxMonDay = calendar.getMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, maxMonDay);
		date[1] = calendar.getTime();
		return date;
	}

	/**
	 * @return
	 * @author sx-9129 2016/10/31
	 * 获取当前时间的“年-月”信息
	 */
	public static String getLastYearMonth() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		calendar.add(Calendar.MONTH, -1);
		sb.append(calendar.get(Calendar.YEAR));
		sb.append("-");
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month);
		return sb.toString();
	}

	/**
	 * @param date
	 * @return
	 * @author sx-9129 2016/11/07
	 * 获取指定时间的“年月”信息
	 * 格式yyyyMM
	 */
	public static String getYearMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder sb = new StringBuilder();
		sb.append(calendar.get(Calendar.YEAR));
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month);
		return sb.toString();
	}

	public static String getToHour(Date date) {
		DateFormatter dateFormatter = new DateFormatter("yyyyMMddHH");
		String dateTo = dateFormatter.print(date, Locale.CHINA);
		return dateTo;
	}

	/**
	 * @param date
	 * @return
	 * @author sx-9129 2016/12/01
	 * 获取指定时间的“年-月-日”信息
	 */
	public static String getYearMonthDay(Date date) {
		DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
		String dateTo = dateFormatter.print(date, Locale.CHINA);
		return dateTo;
	}

	/**
	 * @param day
	 * @return
	 * @author sx-9129 2016/12/01
	 * 获取指定时间的“年-月-日”信息
	 */
	public static Date getYearMonthDate(String day) {
		DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
		Date dateTo = null;
		try {
			dateTo = dateFormatter.parse(day, Locale.CHINA);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateTo;
	}

	/**
	 * @return
	 * @author sx-9129 2016/12/01
	 * 获取当天日期
	 */
	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * @return
	 * @author sx-9129 2016/12/01
	 * 获取当天日期字符串
	 */
	public static String getTodayString() {
		return getYearMonthDay(getToday());
	}

	/**
	 * @return 当前年月
	 * @author 丁峰
	 * 获取当前年月
	 */
	public static String getNowYearMonth() {
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		sb.append(calendar.get(Calendar.YEAR));
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month);
		return sb.toString();
	}

	/**
	 * @param date
	 * @return 昨天的日期
	 * @author 丁峰
	 * 获得昨天的日期
	 */
	public static Date yesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		Date resultDate = calendar.getTime();
		return resultDate;
	}

	/**
	 * @param count
	 * @return
	 * @author 丁峰
	 * 获得以这
	 */
	public static String getYearMonthCount(int count) {
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder();
		calendar.add(Calendar.MONTH, count);
		sb.append(calendar.get(Calendar.YEAR));
		sb.append("-");
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month);
		return sb.toString();
	}


	/**
	 * @param dateStr 时间字符串
	 * @return 增加一天之后的时间字符串
	 * @author 丁峰
	 * 加一天，时间格式为yyyy-MM-dd
	 */
	public static String addOneDay(String dateStr) {
		try {
			DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
			Date date = dateFormatter.parse(dateStr, Locale.CHINA);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			date = calendar.getTime();
			String dateTo = dateFormatter.print(date, Locale.CHINA);
			return dateTo;
		} catch (ParseException e) {
			log.error("DingPlus： dingplus-right[]DateUtil[]addOneDay[]param dateStr={} cause={}", dateStr, Throwables.getStackTraceAsString(e));
		}
		return null;
	}


	/**
	 * @param date 日期
	 * @return 将日期设为周日
	 * @author 丁峰
	 * 返回离date最近的上一个周日的日期
	 */
	public static Date toSunday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date dateResult = calendar.getTime();
		return dateResult;
	}


	/**
	 * @param date 日期
	 * @return 本月第一天的日期
	 * @author 丁峰
	 * 将日期设为本月的第一天 00:00:00
	 */
	public static Date toEndOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date dateResult = calendar.getTime();
		return dateResult;
	}

	/**
	 * @param date 日期
	 * @return 将日期设置date所处年份的最后一天
	 * @author 丁峰
	 */
	public static Date toEndOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.DATE, -1);
		Date dateResult = calendar.getTime();
		return dateResult;
	}

	/**
	 * @param date 日期
	 * @return
	 * @author 丁峰
	 * 将日期转化到零点
	 */
	public static Date toFirstTimeOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date result = calendar.getTime();
		return result;
	}


	/**
	 * @param date1
	 * @param date2
	 * @return 是否为同一天
	 * @author 丁峰
	 * 判断是否为同一天
	 */
	public static boolean sameDay(Date date1, Date date2) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int day1 = calendar.get(Calendar.DATE);
		int month1 = calendar.get(Calendar.MONTH);
		int year1 = calendar.get(Calendar.YEAR);
		calendar.setTime(date2);
		int day2 = calendar.get(Calendar.DATE);
		int month2 = calendar.get(Calendar.MONTH);
		int year2 = calendar.get(Calendar.YEAR);
		return year2 == year1 && month1 == month2 && day1 == day2;
	}

	/**
	 * @param date 日期
	 * @return 递增后的日期
	 * @author 丁峰
	 * 日期按天递增
	 */
	public static Date dayIncrement(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	/**
	 * @param dateFrom 起始日期
	 * @param dateTo   结束日期
	 * @return 分区后的日期列表
	 * @author 丁峰
	 * 按月对日期分区
	 */
	public static List<Date> getPartitionedDates(Long dateFrom, Long dateTo) {
		Date from = new Date(dateFrom);
		Date to = new Date(dateTo);
		List<Date> dateList = new ArrayList<>();
		dateList.add(from);
		Date nextDate = toEndOfMonth(from);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nextDate);
		do {
			calendar.add(Calendar.MONTH, 1);
			nextDate = calendar.getTime();
			if (nextDate.getTime() < dateTo) {
				dateList.add(nextDate);
			} else {
				break;
			}
		} while (true);
		dateList.add(to);
		return dateList;

	}


	/**
	 * @param month 月份，格式为yyyyMM
	 * @return
	 * @author 丁峰
	 * 增加一个月
	 */
	public static String addOneMonth(String month) {
		DateFormatter dateFormatter = new DateFormatter("yyyyMM");
		Date date;
		try {
			date = dateFormatter.parse(month, Locale.CHINA);
		} catch (ParseException e) {
			log.error("DingPlus： dingplus-right[]DateUtil[]addMonth[]parse dateStr error", month, Throwables.getStackTraceAsString(e));
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		Date resultDate = calendar.getTime();
		String resultStr = dateFormatter.print(resultDate, Locale.CHINA);
		return resultStr;
	}

	public static String addMonth(String month, int num) {
		DateFormatter dateFormatter = new DateFormatter("yyyyMM");
		Date date;
		try {
			date = dateFormatter.parse(month, Locale.CHINA);
		} catch (ParseException e) {
			log.error("DingPlus： dingplus-right[]DateUtil[]addMonth[]parse dateStr error", month, Throwables.getStackTraceAsString(e));
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, num);
		Date resultDate = calendar.getTime();
		String resultStr = dateFormatter.print(resultDate, Locale.CHINA);
		return resultStr;
	}

	public static Boolean isFirstDayofMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (day == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static Date convertDate(Long time) {
		if (time == null) {
			return null;
		}
		return new Date(time);
	}

	private static SimpleDateFormat floorHourFormat = new SimpleDateFormat("yyyyMMddHH");

	/**
	 * 向下取整
	 *
	 * @param date 传入时间：2017-03-18 09:xx:xx
	 * @return 返回 2017-03-18 09:00:00
	 */
	public static Date floorHour(Date date) throws ParseException {
		return floorHourFormat.parse(floorHourFormat.format(date));
	}

	/**
	 * 按照奇怪格式传入的时间
	 *
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date floorHour(String date, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date day = simpleDateFormat.parse(date);
		return floorHour(day);
	}

	/**
	 * 按照默认格式传入时间
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date floorHour(String date) throws ParseException {
		return floorHourFormat.parse(date);
	}

	/**
	 * 判断date1是否在date2之前
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean before(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		return c1.before(c2);
	}

	/**
	 * 判断date1是否在date2之前
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean before(String date1, String date2, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(dateFormat.parse(date1));
			c2.setTime(dateFormat.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c1.before(c2);
	}

	public static List getDateListByMonth(String month) {
		Date day = null;
		try {
			day = new SimpleDateFormat("yyyyMM").parse(month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Long> list = new ArrayList<>();
		//分区表按日分区，先获取分区依据的每天的毫秒值
		for (int i = 0; i < 31; i++) {
			day = dayIncrement(day);
			list.add(day.getTime());
		}
		return list;
	}

}
