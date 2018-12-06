package com.red.star.wechat.data.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR;

public final class DateUtil {

    public final static String DATE_FORMAT_DD = "dd";
    public final static String DATE_FORMAT_MM = "MM";
    public final static String DATE_FORMAT_YY = "yy";
    private final static String DATE_FORMAT_YYYY = "yyyy";
    public final static String DATE_FORMAT_MMDD = "MMdd";
    public final static String DATE_FORMAT_DDMM = "ddMM";
    public final static String DATE_FORMAT_YYMM = "yyMM";
    public final static String DATE_FORMAT_YYMMDD = "YYMMdd";
    public final static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public final static String DATE_FORMAT_LOCAL_TIME = "HHmmss";
    public final static String DATE_FORMAT_LOCAL_DATE_TIME = "yyyyMMddHHmmss";
    public final static String DATE_FORMAT_UTC_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public final static String DATE_FORMAT_SETTLEMENT_DATE = "MM/dd/yyyy HH:mm:ss";
    public final static String DATE_FORMAT_DDMMMYYYY = "dd MMM YYYY";
    public final static String TIME_FORMAT = "HH:mm";
    public final static String DATE_FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_YYYY_MM_DD_HHMMSS_YTD = "yyyy年MM月dd日 HH:mm:ss";
    public final static String DATE_FORMAT_YYYY_MM_DD_HHMMSS_DOT = "yyyy.MM.dd HH:mm:ss";
    public final static String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    private DateUtil() {
    }

    public static String getDateAsDD(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_DD);
        return format.format(date);
    }

    public static String getDateAsMM(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_MM);
        return format.format(date);
    }

    public static String getDateAsYY(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_YY);
        return format.format(date);
    }

    public static String getDateAsYYYY(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY);
        return format.format(date);
    }

    public static String getDateAsMMDD(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_MMDD);
        return format.format(date);
    }

    public static String getDateAsDDMM(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_DDMM);
        return format.format(date);
    }

    public static String getDateAsYYMM(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYMM);
        return format.format(date);
    }

    public static String getDateAsYYMMDD(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYMMDD);
        return format.format(date);
    }

    public static String getDateAsYYYYMMDD(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        return format.format(date);
    }

    public static String getDateAsYYYYMMDDHHMM(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM);
        return format.format(date);
    }

    public static String getDateAsHHMMSS(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_LOCAL_TIME);
        return format.format(date);
    }

    public static String getDateAsYYYYMMDDHHMMSS(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_LOCAL_DATE_TIME);
        return format.format(date);
    }

    public static Date getDateAsYYYYMMDDHHMMSS(String strDate) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_LOCAL_DATE_TIME);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date getDateAsYYYYMMDD(String strDate) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static Date getDateAsYMD(String strDate) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return new Date();
        }
    }


    public static Date getDateAsYYYYMMDDHHMM(String strDate) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getDateFormat(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Date getResponseDate(String localDateTime) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_LOCAL_DATE_TIME);
        try {
            return dateFormat.parse(localDateTime);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * This method is used return response date with host date and time.
     *
     * @param localDate - MMdd
     * @param localTime - HHmmss
     * @return Date
     * @throws ParseException
     */
    public static Date getResponseDate(String localDate, String localTime) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYY + DATE_FORMAT_MMDD + DATE_FORMAT_LOCAL_TIME);
        try {
            return dateFormat.parse(Calendar.getInstance().get(Calendar.YEAR)
                    + String.format("%04d", Integer.parseInt(localDate))
                    + String.format("%06d", Integer.parseInt(localTime)));
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * Get the previous or next dates based on format and days. Ex: Current Date
     * = Sun Oct 09 11:00:00 IST 2015, format=Calendar.DAY_OF_YEAR and days = 2
     * then return Sun Oct 11 00:00:00 IST 2015.
     *
     * @param format - Calendar.MONTH etc.
     * @param days   - number of days
     * @return Date
     */
    public static Date getDateWithFormat(int format, int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(format, days);
        cal.set(HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    public static Date getDateByUTC(String utcDate) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_UTC_ISO_8601);
        try {
            return format.parse(utcDate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String getFormattedSettlementDate(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_SETTLEMENT_DATE);
        return df.format(date);
    }

    public static String getDateAsDDMMMYYYY(Date date) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT_DDMMMYYYY);
        return format.format(date);
    }

    public static String getTimeAsHHMM(Date date) {
        DateFormat format = new SimpleDateFormat(TIME_FORMAT);
        return format.format(date);
    }

    /**
     * Converted the date into jwt issued date.
     *
     * @param date
     * @return Long
     */
//    public static Long getIssuedAtDate(Date date) {
//        DateFormat format = new SimpleDateFormat(DATE_FORMAT_LOCAL_DATE_TIME);
//        return NumberUtil.getLong(format.format(date));
//    }

    /**
     * Get expiry date based on date and tokenExpiryPeriod. Ex: Date =
     * 22-07-2016 12:00:00, period=ONE_DAY(2) then method returns 23-07-2016
     * 12:00:00.
     *
     * @param date
     * @param period
     * @return Long
     */
//    public static Long getExpiryDate(Date date, TokenExpiryPeriod period) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        if (period == null) {
//            period = TokenExpiryPeriod.NO_EXPIRY;
//        }
//        switch (period) {
//        case ONE_HOUR:
//            cal.add(Calendar.HOUR, 1);
//            break;
//        case ONE_DAY:
//            cal.add(Calendar.DATE, 1);
//            break;
//        case ONE_WEEK:
//            cal.add(Calendar.DATE, 7);
//            break;
//        case ONE_MONTH:
//            cal.add(Calendar.MONTH, 1);
//            break;
//        case ONE_YEAR:
//            cal.add(Calendar.YEAR, 1);
//            break;
//        case NO_EXPIRY:
//            cal.add(Calendar.YEAR, 100);
//            break;
//        default:
//            break;
//        }
//        DateFormat format = new SimpleDateFormat(DATE_FORMAT_LOCAL_DATE_TIME);
//        return NumberUtil.getLong(format.format(cal.getTime()));
//    }

    /**
     * getDays from past Date. past date should be not greater than current
     * date.if any exception result will be -1
     *
     * @param date
     */
    public static Long getDaysByPastDate(Date pastDate) {
        DateFormat format = new SimpleDateFormat(DateUtil.DATE_FORMAT_SETTLEMENT_DATE);
        Date currentDate;
        try {
            currentDate = format.parse(DateUtil.getFormattedSettlementDate(new Date()));
            pastDate = format.parse(DateUtil.getFormattedSettlementDate(pastDate));
            return (currentDate.getTime() - pastDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            return -1L;
        }
    }

    /**
     * getHours from past time . the time format should be 24 .It will check
     * with in one day. the result may come between 0-24 .if may exception
     * result will be -1
     *
     * @return Long
     */
    public static Long getHoursByPastTime(Long pastTime) {
        DateFormat format = new SimpleDateFormat(DateUtil.DATE_FORMAT_SETTLEMENT_DATE);
        Date currentDate;
        try {
            currentDate = format.parse(DateUtil.getFormattedSettlementDate(new Date()));
            return (currentDate.getTime() - pastTime) / (60 * 60 * 1000) % 24;
        } catch (ParseException e) {
            return -1L;
        }
    }

    public static String getDateAsYYYY_MM_DD_HHMMSS(Date date) {
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD_HHMMSS);
        return df.format(date);
    }

    public static String getDateAsYYYY_MM_DD_HHMMSS_YTD(Date date) {
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD_HHMMSS_YTD);
        return df.format(date);
    }

    public static String getDateAsYYYY_MM_DD_HHMMSS_DOT(Date date) {
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD_HHMMSS_DOT);
        return df.format(date);
    }

    public static String getDateAsYYYY_MM_DD(Date date) {
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YYYY_MM_DD);
        return df.format(date);
    }

    public static Date getDateyyyymmdd(Date date) throws ParseException {
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String s1 = sdf.format(date);
        return sdf.parse(s1);
    }

    /**
     * Validate the client request date time before settlement date(today 2400)
     * or not
     *
     * @param reqDate
     * @return boolean
     */
    public static boolean beforeSettlementTime(Date reqDate) {
        Calendar settCal = Calendar.getInstance();
        settCal.setTime(new Date());
        settCal.add(DATE, 1);
        settCal.set(Calendar.HOUR_OF_DAY, 0);
        settCal.set(Calendar.MINUTE, 0);
        settCal.set(Calendar.SECOND, 0);
        settCal.set(Calendar.MILLISECOND, 0);
        return reqDate.getTime() < settCal.getTime().getTime();
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     *
     * @param dateFormat
     * @param millSec
     * @return transferLongToDate(" yyyy - MM - dd HH : mm : ss ", 1245678944);
     */
    public static String transferLongToDateStr(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 把long 转换成 日期
     *
     * @param dateFormat
     * @param millSec
     * @return
     */
    public static Date transferLongToDate(String dateFormat, Long millSec) {
        Date date = new Date(millSec);
        return date;
    }

    /**
     * 字符串型日期/时间转DATE
     *
     * @param str     日期时间的字符串
     * @param pattern 格式(yyyy-MM-dd/yyyy-MM-dd HH:mm:ss)
     * @return
     * @throws ParseException
     */
    public static Date getDateByString(String str, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat df3 = new SimpleDateFormat();
            df3.applyPattern(pattern);
            date = df3.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *
     * */
    public static Calendar getCalendarTime(Date time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        calendar.add(DATE, 1);
        return calendar;
    }

    public static Calendar getCalendarMinusTime(Date time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(time);
        calendar.add(DATE, -1);
        calendar.add(HOUR, -1);
        return calendar;
    }


    /**
     * 时区转换
     *
     * @param time           时间字符串
     * @param pattern        格式 "yyyy-MM-dd HH:mm"
     * @param nowTimeZone    eg:+8，0，+9，-1 等等
     * @param targetTimeZone 同nowTimeZone
     * @return
     */
    public static String timeZoneTransfer(String time, String pattern, String nowTimeZone, String targetTimeZone, String targetPattern) {
        if (CheckUtil.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + nowTimeZone));
        Date date;
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(targetPattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT" + targetTimeZone));
        return sdf.format(date);
    }
}
