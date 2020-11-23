package com.mycompany.testproj.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil extends DateUtils {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	protected DateUtil() {
	}

	private static final String dafaultFormat = "yyyy-MM-dd";

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String getCurrent() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(dafaultFormat);
		return df.format(cal.getTime());
	}

	public static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DATE_PATTERN);
		return df.format(cal.getTime());
	}

	public static String getHour(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dTime = new Date();
		TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
		sdf.setTimeZone(tz);
		String curHour = sdf.format(dTime);
		return curHour;
	}

	public static String getGMTHour(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dTime = new Date();
		String curHour = sdf.format(dTime);
		return curHour;
	}

	public static String getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.YEAR, year);

		SimpleDateFormat df = new SimpleDateFormat(dafaultFormat);
		return df.format(cal.getTime());
	}

	public static String getCurrent(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}

	public static Date getAddMonth(int amount) {
		Calendar cal = Calendar.getInstance();
		Date curDate = cal.getTime();
		return DateUtil.addMonths(curDate, amount);
	}

	public static String getAddMonth(String format, int amount) {
		Date date = getAddMonth(amount);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static Date getAddDate(int amount) {
		Calendar cal = Calendar.getInstance();
		Date curDate = cal.getTime();
		return DateUtil.addDays(curDate, amount);
	}

	public static String getAddDays(String format, int amount) {
		Date date = getAddDate(amount);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String getCurrent(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	public static String getDate(String format, int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.YEAR, year);

		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(cal.getTime());
	}

	public static boolean isDateCompare(String format, String date1,
			String date2) {
		boolean isBoolean = true;

		long tiem1 = DateUtil.toDate("yyyy-MM-dd", date1).getTime();
		long tiem2 = DateUtil.toDate("yyyy-MM-dd", date2).getTime();
		if (tiem1 < tiem2) {
			isBoolean = false;
		}
		return isBoolean;

	}

	public static String getFormatDate(String format, Date date) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String fDate = df.format(date);
		return fDate;
	}

	public static String getAddDate(String format, int hour, int days) {
		Date date = DateUtil.getAddDate(0);
		Date afterAddHour = addHours(date, hour);
		Date afterAddDays = addDays(afterAddHour, days);
		String addDays = getFormatDate(format, afterAddDays);
		return addDays;
	}

	public static String getAddDate(String format, int hour, int days,
			String searchDate) {
		String addDays = "";
		if (searchDate != null) {
			Date date = DateUtil.toDate("yyyy-MM-dd", searchDate);
			Date afterAddHour = addHours(date, hour);
			Date afterAddDays = addDays(afterAddHour, days);
			addDays = getFormatDate(format, afterAddDays);
		} else {
			Date date = DateUtil.getAddDate(0);
			Date afterAddHour = addHours(date, hour);
			Date afterAddDays = addDays(afterAddHour, days);
			addDays = getFormatDate(format, afterAddDays);
		}
		return addDays;
	}

	public static boolean checkExpire(String expireDate) {
		boolean result = true;
		String sFormat = "yyyy-MM-dd hh:ss:mm";
		SimpleDateFormat fmt = new SimpleDateFormat(sFormat);
		try {
			if (!expireDate.equals("")) {
				Date expire = fmt.parse(expireDate);
				Date currentDate = new Date();
				if (expire.after(currentDate)) {
					result = false;
				}
			} else {
				result = false;
			}
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.error(Utils.getMethodName(), e);
		}
		return result;
	}

	/**
	 * Check String is Data Format<br/>
	 * Support Format : yyyy-MM-dd HH:mm:ss, yyyy-MM-dd HH:mm, yyyy-MM-dd
	 *
	 * @param date
	 * @return boolean<br/>
	 *         true : String is Data Format.<br/>
	 *         false : String is not Data Format.<br/>
	 */
	public static boolean isDateFormat(String date) {
		boolean result = false;

		if (!result) {
			try {
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
				result = true;
			} catch (ParseException e) {
				result = false;
			} catch (Exception e) {
				result = false;
			}
		}
		if (!result) {
			try {
				new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date);
				result = true;
			} catch (ParseException e) {
				result = false;
			} catch (Exception e) {
				result = false;
			}
		}
		if (!result) {
			try {
				new SimpleDateFormat("yyyy-MM-dd").parse(date);
				result = true;
			} catch (ParseException e) {
				result = false;
			} catch (Exception e) {
				result = false;
			}
		}
		if (!result) { // Exif Date
			try {
				new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").parse(date);
				result = true;
			} catch (ParseException e) {
				result = false;
			} catch (Exception e) {
				result = false;
			}
		}

		return result;
	}

	/**
	 * 임의의 타임존 정보를 입력받아 날짜/시간을 변환해주는 메소드
	 *
	 * @param date
	 *            yyyy:MM:dd kk:mm:ss (Exif) 또는 yyyy-MM-dd kk:mm:ss (기본) 패턴의
	 *            날짜/시간 값
	 * @param timezone
	 *            -12 ~ 13 사이의 정수 값
	 * @return 날짜/시간의 패턴이 올바르지 않거나 범위 밖의 타임존 값인 경우 null을 리턴
	 * @throws ParseException
	 */
	public static String convertToGMT(String date, int timezone) {
		if (date == null || -12 > timezone || 13 < timezone) {
			return null;
		}

		String dateString = String.format("%s %s%02d00",
				date.replace("-", ":"), timezone < 0 ? "-" : "+",
				Math.abs(timezone));

		DateFormat inFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss Z");
		Date newDate = null;
		try {
			newDate = inFormat.parse(dateString);
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.error(Utils.getMethodName(), e);
			return null;
		}

		String formatedDateStr = null;
		DateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		outFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		if (newDate != null) {
			formatedDateStr = outFormat.format(newDate).toString();
		}

		return formatedDateStr;
	}


	private static Map<String, DateFormat> datFormatMap = new HashMap<String, DateFormat>();

	public static Date toDateGmt(String format, String dateString) {

		DateFormat outFormat = datFormatMap.get(format);

		if (outFormat == null) {
			outFormat = new SimpleDateFormat(format);
			datFormatMap.put(format, outFormat);
		}

		outFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return outFormat.parse(dateString);
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.error(Utils.getMethodName(), e);
		}
		return null;
	}

	public static Date toDate(String format, String dateString) {

		DateFormat outFormat = datFormatMap.get(format);

		if (outFormat == null) {
			outFormat = new SimpleDateFormat(format);
			datFormatMap.put(format, outFormat);
		}

		try {
			return outFormat.parse(dateString);
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.error(Utils.getMethodName(), e);
		}
		return null;
	}


    /**
     * Gets the date format.
     *
     * @param pattern
     *            the pattern
     * @return the date format
     */
    public static DateFormat getDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }


	/**
	 * 오늘 날짜와 차이 일 수 구하기
	 *
	 * @param compareDate
	 * @return
	 */
	public static long getDateGap(Date compareDate){

	  Date today = new Date();

	  Calendar todayDateCal = Calendar.getInstance();
	  Calendar compareDateCal = Calendar.getInstance();

	  long diffMillis = 0L;
	  long  diff = 0l;

	  todayDateCal.setTime(today);
	  compareDateCal.setTime(compareDate);

	  diffMillis = todayDateCal.getTimeInMillis() - compareDateCal.getTimeInMillis();
	  diff = diffMillis/ (24 * 60 * 60 * 1000);

	  return diff;
	 }

	/**
     * <p>
     * 입력된 년월의 마지막 일수를 return 한다.
     *
     * @param year
     *            the year
     * @param month
     *            the month
     * @return 마지막 일수
     * @see java.util.Calendar <p>
     */
    public static int getLastDayOfMonth(int year, int month) {

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    /**
     * <p>
     * 입력된 년월의 마지막 일수를 return한다.
     *
     * @param yyyymm
     *            the yyyymm
     * @return 마지막 일수
     *         <p>
     */
    public static int getLastDayOfMonth(String yyyymm) {
    	int result = 0;
    	if(yyyymm != null) {
	        Calendar cal = Calendar.getInstance();
	        int yyyy = Integer.parseInt(yyyymm.substring(0, 4));
	        int mm = Integer.parseInt(yyyymm.substring(4)) - 1;

	        cal.set(yyyy, mm, 1);
	        result = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    	}
        return result;
    }
    
    
	/**
	 * @date	2017.07.05.
	 * @author	KYOUNGDO LEE
	 * @desc	현재날짜를 YYYYMMDDHHMMSS로 리턴 : 카카오페이 서비스 구축
	 */
	public static String getyyyyMMddHHmmss(){
		/** yyyyMMddHHmmss Date Format */
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");

		return yyyyMMddHHmmss.format(new Date());
	}    
	
	/**
	 * @date	2017.07.05.
	 * @author	KYOUNGDO LEE
	 * @desc	현재날짜를 YYYYMMDD로 리턴 : 카카오페이 서비스 구축
	 */
	public static String getyyyyMMdd(){
		/** yyyyMMddHHmmss Date Format */
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

		return yyyyMMdd.format(new Date());
	}    	
	
	/**
	 * @date	2017.07.05.
	 * @author	KYOUNGDO LEE
	 * @desc	현재날짜를 HHMMSS로 리턴 : 카카오페이 서비스 구축
	 */	
	public static String getHHmmss(){
		/** yyyyMMddHHmmss Date Format */
		SimpleDateFormat HHmmss = new SimpleDateFormat("HHmmss");

		return HHmmss.format(new Date());
	}	

}
