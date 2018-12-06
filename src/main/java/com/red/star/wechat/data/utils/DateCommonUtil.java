package com.red.star.wechat.data.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xulonglong on 2017/11/19.
 */
public class DateCommonUtil {

	private static long SECOND_LONG = 1000L;

	private static long MINUTE_LONG = 60 * SECOND_LONG;

	private static long HOUR_LONG = 60 * MINUTE_LONG;

	private static long DAY_LONG = 24 * HOUR_LONG;

	private static long MONTH_LONG=30*DAY_LONG;

	private static long YEAR_LONG=365*DAY_LONG;

	private static String[] WEEKS = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;


	/**
	 * 时间转换成一小时前/一天前/年-月-日
	 */
	public static String timeToStr(Date date){
		StringBuffer timeStr=new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;

		if(ago<=ONE_MINUTE) {
			timeStr.append("刚刚");
		}else if(ago <= ONE_HOUR){
			timeStr.append(ago / ONE_MINUTE).append("分钟前");
		}else if (ago <= ONE_DAY){
			timeStr.append(ago / ONE_HOUR).append("小时前");
		}else{
			timeStr.append(DateToString(date));
		}
		return timeStr.toString();
	}


	/**
	 * 得到某一天的开始时间，精确到毫秒
	 * @param date 日期
	 * @return 某一天的0时0分0秒0毫秒的那个Date
	 */
	public static Date beginOfDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到某一天的最后时间，精确到毫秒
	 *
	 * @param date 日期
	 * @return 某一天的下一天的0时0分0秒0毫秒的那个Date减去1毫秒所得到的Date
	 */
	public static Date endOfDay(Date date) {
		date = beginOfDay(date);
		return endOfDayByBeginOfDate(date);
	}

	/**
	 * 根据某一天的开始时间，得到某一天的最后时间，精确到毫秒
	 * @param date 日期
	 * @return 某一天的下一天的0时0分0秒0毫秒的那个Date减去1毫秒所得到的Date
	 */
	public static Date endOfDayByBeginOfDate(Date date) {
		date = nextDay(date);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MILLISECOND, -1);
		date = c.getTime();
		return date;
	}

	/**
	 * 得到指定日期的下一天
	 * @param date 日期
	 * @return 传入日期的下一天
	 */
	public static Date nextDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		return date;
	}


	/**
	 * 获取指定时间
	 * @param date 指定日期
	 * @param diffDay 天数
	 * @return 自指定日期后的若干天的日期
	 */
	public static Date getDay(Date date, Integer diffDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, diffDay);
		return cal.getTime();
	}



	/**
	 * 得到本周周一
	 * @return Date
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		c.add(Calendar.DATE, -dayOfWeek + 1);
		return c.getTime();
	}



	/**
	 * 得到本周周日
	 * @return Date
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		c.add(Calendar.DATE, -dayOfWeek + 7);
		return c.getTime();
	}

	/**
	 * Date 转String
	 * @return Date
	 */
	public static String DateToString(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String str=sdf.format(date);
		return str;
	}

	/**
	 * Date 转String
	 * @return Date
	 */
	public static String DateToString2(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String str=sdf.format(date);
		return str;
	}

	/**
	 * 输出字符串类型的格式化日期
	 * @param dt Date
	 * @param pattern 格式(yyyy-MM-dd/yyyy-MM-dd HH:mm:ss)
	 * @return sDate
	 */
	public static String getFormatDate(Date dt, String pattern){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(dt);
	}

	/**
	 * 字符串型日期/时间转DATE
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date getFormatDate(String str){
		if (str == null || "".equals(str)){
			return null;
		}
		if (str.length() <= 10) {
			return getDateByString(str, "yyyy-MM-dd");
		} else {
			return getDateByString(str, "yyyy-MM-dd HH:mm:ss");
		}
	}

	/**
	 * 字符串型日期/时间转DATE
	 * @param str  日期时间的字符串
	 * @param pattern   格式(yyyy-MM-dd/yyyy-MM-dd HH:mm:ss)
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateByString(String str, String pattern){
		Date date=null;
		try {
			SimpleDateFormat df3 = new SimpleDateFormat();
			df3.applyPattern(pattern);
			date=df3.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 获取时间的月日
	 * @param date
	 * @return
	 */
	public static String getDateMMDD(Date date){
		StringBuffer time=new StringBuffer();
		String mmdd=getFormatDate(date,"MM-dd");
		time.append(mmdd.replaceAll("-","月")).append("日");
		return time.toString();
	}

	public static Integer diffDate(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return new Long((date2.getTime() - calendar.getTimeInMillis()) / DAY_LONG).intValue();
	}

	/**
	 * 获取当前时间前天时间
	 * @param days
	 * @return
	 */
	public static String getDaysDate(Integer days){
		GregorianCalendar worldTour = new GregorianCalendar();
		worldTour.add(GregorianCalendar.DATE, -days); // 当前日期加100天
		Date d = worldTour.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String date = df.format(d);
		return date;
	}

	/**
	 * 某一个月第一天和最后一天
	 * @param date
	 * @return
	 */
	public static Map<String, String> getFirstdayLastdayMonth(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);//-1:上个月， 0：当前月
		Date theDate = calendar.getTime();
		//第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		day_first = str.toString();
		//后一天
		calendar.add(Calendar.MONTH, 1);    //加一个月
		calendar.set(Calendar.DATE, 1);        //设置为该月第一天
		calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, String> map = new HashMap<String, String>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}

	/**
	 * 当月第一天
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();
	}

	/**
	 * 当月最后一天
	 * @return
	 */
	public static String getLastDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
		return str.toString();
	}

	/**
	 * 获取明年的年份
	 */
	public static int getNextYear(){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		calendar.setTime(date);
		calendar.add(Calendar.YEAR,1);
		date = calendar.getTime();
		String yyyy=getFormatDate(date,"yyyy");
		return Integer.valueOf(yyyy);
	}

	/**
	 * 获取当前系统的UNIX时间戳
	 */
	public static String getUnixTime() throws ParseException{
		Timestamp appointTime=Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = df.parse(String.valueOf(appointTime));
		long s=date.getTime();
		return String.valueOf(s).substring(0,10);
	}

	/**
	 * 格式化月日
	 * flag true-MM:dd/false-MM月dd日
	 */
	public static String dateFormatByMMdd(Date date,boolean flag){
		String pattern=null;
		if(flag){
			pattern="MM:dd";
		}else{
			pattern="MM月dd日";
		}
		SimpleDateFormat dateFm = new SimpleDateFormat(pattern);
		return dateFm.format(date);
	}

	public static String getWeek(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
			w = 0;
		}
		return WEEKS[w];
	}


	public static String getWeek(String day) {
		if(day.equals(getFormatDate(new Date(),"yyyy-MM-dd"))) {
			return "今天";
		}else if (day.equals(getFormatDate(getDay(new Date(),1),"yyyy-MM-dd"))) {
			return "明天";
		}else if(day.equals(getFormatDate(getDay(new Date(),2),"yyyy-MM-dd"))){
			return "后天";
		}else {
			return getWeek(DateUtil.getDateByString(day,"yyyy-MM-dd"));
		}
	}

	/**
	 * 当前时间跟指定的时间周期对比
	 * @param startDate 活动开始时间
	 * @param endDate 活动结束日期
	 * @return compareTo 0:相等,-1:小于,1:大于
	 */
	public static int compare(Date startDate, Date endDate){
		Date currentDate = new Date();
		if(currentDate.compareTo(startDate)<0){
			return 0; //活动未开始
		}else if(currentDate.compareTo(endDate)>=0) {
			return 2;//活动结束
		}else {
			return 1;//活动进行中
		}
	}

}
