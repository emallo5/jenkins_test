package com.aparcsystems.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Utilities for Dates and Calendars
 * 
 */
public abstract class DateUtils {
	
	/** Seconds in a minute */
	public static final int MINUTE = 60;
	
	/** Seconds in an hour */
	public static final int HOUR = MINUTE * 60;
	
	/** Seconds in a day */
	public static final int DAY = HOUR * 24;
	
	/** Seconds in a week */
	public static final int WEEK = DAY * 7;
	
	/** Number of milliseconds in a second. */
	public static final long MILLIS_PER_SECOND = 1000;
	
	/** Number of milliseconds in a minute. */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	
	/** Number of milliseconds in a hour. */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	
	/** Number of milliseconds in a day. */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
	
	/** Number of milliseconds in a week. */
	public static final long MILLIS_PER_WEEK = 7 * MILLIS_PER_DAY;
	
	/**
	 * Date format like 2013-01-11T20:30:00.000
	 */
	public static final String YYYYMMDDTHHMMSSSSS_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	/**
	 * Date format like yyyy-MM-dd HH:mm:ss Z
	 */
	public static final String YYYYMMDDHHMMSSZ_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
	
	/**
	 * Date format like 2010-10-25 21:30:00
	 */
	public static final String YYYYMMDDHHMMSS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * Date format like 10/25/2010 21:30
	 */
	public static final String MMDDYYYYHHMM_DATE_FORMAT = "MM/dd/yyyy HH:mm";
	
	/**
	 * Date format like 10/25/2010
	 */
	public static final String MMDDYYYY_DATE_FORMAT = "MM/dd/yyyy";
	
	/**
	 * Date format like 10-25-2010
	 */
	public static final String MMDDYYYY_SLASH_DATE_FORMAT = "MM-dd-yyyy";
	
	/**
	 * Date format like 2010-10-25
	 */
	public static final String YYYYMMDD_DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * Date format like 2010-10
	 */
	public static final String YYYYMM_DATE_FORMAT = "yyyy-MM";
	
	/**
	 * Date format like Fri 5:15 AM
	 */
	public static final String EHHMMAA_DATE_FORMAT = "E hh:mm aa";
	
	/**
	 * Date format like Nov 5 3:45 PM
	 */
	public static final String MMMDHHMMAA_DATE_FORMAT = "MMM d hh:mm aa";
	
	/**
	 * Date format like Nov 5 1985 3:45 PM
	 */
	public static final String MMMDYYYYHHMMAA_DATE_FORMAT = "MMM d yyyy hh:mm aa";
	
	/**
	 * Date format like Nov 5
	 */
	public static final String MMMD_DATE_FORMAT = "MMM d";
	
	/**
	 * Date format like November 5
	 */
	public static final String MMMMD_DATE_FORMAT = "MMMM d";
	
	/**
	 * Date format like 03:45 PM
	 */
	public static final String HHMMAA_DATE_FORMAT = "hh:mm aa";
	
	/**
	 * Date format like 13:45
	 */
	public static final String HHMM_DATE_FORMAT = "HH:mm";
	
	/**
	 * Date format like Friday 5 November 2013
	 */
	public static final String EEEEDMMMMYYYY_DATE_FORMAT = "EEEE d MMMM yyyy";
	
	/**
	 * Date format like Fri 5 November 2013
	 */
	public static final String EEDMMMMYYYY_DATE_FORMAT = "EE d MMMM yyyy";
	
	/**
	 * Date format like Friday 5 November
	 */
	public static final String EEEEDMMMM_DATE_FORMAT = "EEEE d MMMM";
	
	/**
	 * Date format like Fri 5 November
	 */
	public static final String EEDMMMM_DATE_FORMAT = "EE d MMMM";
	
	/**
	 * Date format like Fri 5 Nov 15:33
	 */
	public static final String EEDMMMHHMM_DATE_FORMAT = "EE d MMM HH:mm";
	
	/**
	 * Date format like Friday November
	 */
	public static final String EEEEMMMM_DATE_FORMAT = "EEEE MMMM";
	
	/**
	 * Date format like 5 Nov
	 */
	public static final String DMMM_DATE_FORMAT = "d MMM";
	
	/**
	 * Date format like 5 Nov 2012
	 */
	public static final String DMMMYYYY_DATE_FORMAT = "d MMM yyyy";
	/**
	 * Date format like 05 Nov 2012
	 */
	public static final String DDMMMYYYY_DATE_FORMAT = "dd MMM yyyy";
	/**
	 * Date format like 5 Nov 2012 - 13:55 hs
	 */
	public static final String DMMMYYYY_HHMM_DATE_FORMAT = "d MMM yyyy - HH:mm";
	
	/**
	 * Date format like NOV
	 */
	private static final String MMM = "MMM";
	
	public enum DayOfWeek {
		
		SUNDAY("Sunday", true),
		MONDAY("Monday", false),
		TUESDAY("Tuesday", false),
		WEDNESDAY("Wednesday", false),
		THURSDAY("Thursday", false),
		FRIDAY("Friday", false),
		SATURDAY("Saturday", true);
		
		private String name;
		private Boolean weekend;
		
		private DayOfWeek(String name, Boolean weekend) {
			this.name = name;
			this.weekend = weekend;
		}
		
		public Boolean isWeekend() {
			return weekend;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		public static DayOfWeek findByName(String name) {
			DayOfWeek dayOfWeek = null;
			for (DayOfWeek each : values()) {
				if (each.name().equalsIgnoreCase(name)) {
					dayOfWeek = each;
					break;
				}
			}
			return dayOfWeek;
		}
	}
	
	public static final void init() {
		// nothing...
	}
	
	/**
	 * @param dateFormatted The formatted string to parse
	 * @param dateFormat
	 * @return A date that represents the formatted string
	 */
	public static Date parse(String dateFormatted, String dateFormat) {
		return DateUtils.parse(dateFormatted, new SimpleDateFormat(dateFormat), false);
	}
	
	public static Date parse(String dateFormatted, String dateFormat, boolean useUtc) {
		return DateUtils.parse(dateFormatted, new SimpleDateFormat(dateFormat), useUtc);
	}
	
	/**
	 * @param dateFormatted The formatted string to parse
	 * @param dateFormat
	 * @return A date that represents the formatted string
	 */
	public static Date parse(String dateFormatted, SimpleDateFormat dateFormat) {
		return parse(dateFormatted, dateFormat, false);
	}
	
	public static Date parse(String dateFormatted, SimpleDateFormat dateFormat, boolean useUtc) {
		Date date = null;
		if (StringUtils.isNotEmpty(dateFormatted)) {
			try {
				if (useUtc) {
					dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
				}
				date = dateFormat.parse(dateFormatted);
			} catch (ParseException e) {
				throw new RuntimeException("Error parsing the dateFormatted: " + dateFormatted + " pattern: "
						+ dateFormat.toPattern(), e);
			}
		}
		return date;
	}
	
	/**
	 * @param date The {@link java.util.Date} to be formatted
	 * @param dateFormat The {@link java.text.DateFormat} used to format the {@link java.util.Date}
	 * @return A String that represent the date with the pattern
	 */
	public static String format(Date date, String dateFormat) {
		return DateUtils.format(date, dateFormat, false);
	}
	
	public static String format(Date date, String dateFormat, boolean useUtc) {
		return DateUtils.format(date, new SimpleDateFormat(dateFormat), useUtc);
	}
	
	/**
	 * Transform the {@link java.util.Date} to a {@link String} using the received {@link java.text.SimpleDateFormat}
	 * 
	 * @param date The {@link java.util.Date} to be formatted
	 * @param dateFormat The {@link java.text.DateFormat} used to format the {@link java.util.Date}
	 * @return A String that represent the date with the pattern
	 */
	public static String format(Date date, DateFormat dateFormat) {
		return format(date, dateFormat, false);
	}
	
	public static String format(Date date, DateFormat dateFormat, boolean useUtc) {
		if (useUtc) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		return date != null ? dateFormat.format(date) : null;
	}
	
	public static String formatDate(Date date) {
		return format(date, DateFormat.getDateInstance());
	}
	
	public static String formatTime(Date date) {
		return format(date, DateFormat.getTimeInstance(DateFormat.SHORT));
	}
	
	/**
	 * Creates a {@link java.util.Date} for the specified day
	 * 
	 * @param year The year
	 * @param monthOfYear The month number (starting on 0)
	 * @param dayOfMonth The day of the month
	 * @return The {@link java.util.Date}
	 */
	public static Date getDate(int year, int monthOfYear, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, monthOfYear, dayOfMonth);
		truncate(calendar);
		return calendar.getTime();
	}
	
	public static Date getDate(long milliseconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		return calendar.getTime();
	}
	
	public static Date getTime(int hour, int minutes) {
		return getTime(hour, minutes, true);
	}
	
	public static Date getTime(int hour, int minutes, Boolean is24Hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(is24Hour ? Calendar.HOUR_OF_DAY : Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	public static Date changeHourAndResetMinutesAndSeconds(Date date, int hour) {
		return DateUtils.changeHourMinutesAndSeconds(date, hour, 0, 0, true);
	}
	
	public static Date changeHourMinutesAndSeconds(Date date, int hour, int minutes, int seconds, Boolean is24Hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(is24Hour ? Calendar.HOUR_OF_DAY : Calendar.HOUR, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	public static int getYear() {
		return getYear(now());
	}
	
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}
	
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHour(Date date, Boolean is24Hour) {
		return DateUtils.getHour(date, TimeZone.getDefault(), is24Hour);
	}
	
	public static int getHour(Date date, TimeZone timeZone, Boolean is24Hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(timeZone);
		return calendar.get(is24Hour ? Calendar.HOUR_OF_DAY : Calendar.HOUR);
	}
	
	public static int getMinute(Date date) {
		return DateUtils.getMinute(date, TimeZone.getDefault());
	}
	
	public static int getMinute(Date date, TimeZone timeZone) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setTimeZone(timeZone);
		return calendar.get(Calendar.MINUTE);
	}
	
	public static DayOfWeek getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return DayOfWeek.values()[dayOfWeek - 1];
	}
	
	public static DayOfWeek getDayOfWeekByName(String dayName) {
		return DayOfWeek.findByName(dayName);
	}
	
	public static boolean isDateOnWeekend(Date date) {
		return getDayOfWeek(date).isWeekend();
	}
	
	public static Date setHour(Date date, int hours, Boolean is24Hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(is24Hour ? Calendar.HOUR_OF_DAY : Calendar.HOUR, hours);
		return calendar.getTime();
	}
	
	public static Date addSeconds(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	public static Date addHours(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}
	
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}
	
	public static Date addMonths(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	public static Date addYears(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	
	/**
	 * Truncate the date removing hours, minutes, seconds and milliseconds
	 * 
	 * @param date The {@link java.util.Date} to truncate
	 * @return The truncated {@link java.util.Date}
	 */
	public static Date truncate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		truncate(calendar);
		return calendar.getTime();
	}
	
	/**
	 * Truncate the {@link java.util.Calendar} removing hours, minutes, seconds and milliseconds
	 * 
	 * @param calendar The {@link java.util.Calendar} to truncate
	 */
	public static void truncate(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * @return the current moment
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * @return the current moment in UTC
	 */
	public static Date nowUTC() {
		return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
	}
	
	/**
	 * @param date The date to compare
	 * @param startDate The left between' side
	 * @param endDate The right between's side
	 * @return <code>true</code> if the date is in the middle of startDate and endDate
	 */
	public static boolean isBetween(Date date, Date startDate, Date endDate) {
		return DateUtils.isBeforeEquals(startDate, date) && DateUtils.isAfterEquals(endDate, date);
	}
	
	/**
	 * Tests if the date is before than the specified dateToCompare.
	 * 
	 * @param date the date to compare with the dateToCompare.
	 * @param dateToCompare the date to compare with the date.
	 * @return <code>true</code> if the instant of time represented by <code>date</code> object is earlier than the
	 *         instant represented by <tt>dateToCompare</tt>; <code>false</code> otherwise.
	 */
	public static boolean isBefore(Date date, Date dateToCompare) {
		return date.compareTo(dateToCompare) < 0;
	}
	
	/**
	 * Tests if the date is before or equals than the specified dateToCompare.
	 * 
	 * @param date the date to compare with the dateToCompare.
	 * @param dateToCompare the date to compare with the date.
	 * @return <code>true</code> if the instant of time represented by <code>date</code> object is earlier or equal than
	 *         the instant represented by <tt>dateToCompare</tt>; <code>false</code> otherwise.
	 */
	public static boolean isBeforeEquals(Date date, Date dateToCompare) {
		return date.compareTo(dateToCompare) <= 0;
	}
	
	/**
	 * Tests if the date is after or equals than the specified dateToCompare.
	 * 
	 * @param date the date to compare with the dateToCompare.
	 * @param dateToCompare the date to compare with the date.
	 * @return <code>true</code> if the instant of time represented by <code>date</code> object is later or equal than
	 *         the instant represented by <tt>dateToCompare</tt>; <code>false</code> otherwise.
	 */
	public static boolean isAfterEquals(Date date, Date dateToCompare) {
		return date.compareTo(dateToCompare) >= 0;
	}
	
	/**
	 * Tests if the date is after than the specified dateToCompare.
	 * 
	 * @param date the date to compare with the dateToCompare.
	 * @param dateToCompare the date to compare with the date.
	 * @return <code>true</code> if the instant of time represented by <code>date</code> object is later than the
	 *         instant represented by <tt>dateToCompare</tt>; <code>false</code> otherwise.
	 */
	public static boolean isAfter(Date date, Date dateToCompare) {
		return date.compareTo(dateToCompare) > 0;
	}
	
	/**
	 * Returns true if two periods overlap
	 * 
	 * @param startDate1 the period one start date
	 * @param endDate1 the period one end date
	 * @param startDate2 the period two start date
	 * @param endDate2 the period two end date
	 * @return true if overlap
	 */
	public static boolean periodsOverlap(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
		return (startDate1.before(endDate2) || startDate1.equals(endDate2))
				&& (endDate1.after(startDate2) || endDate1.equals(startDate2));
	}
	
	/**
	 * Returns true if the first period contains the second periods
	 * 
	 * @param startDate1 the period one start date
	 * @param endDate1 the period one end date
	 * @param startDate2 the period two start date
	 * @param endDate2 the period two end date
	 * @return true if the first period contains the second period
	 */
	public static boolean containsPeriod(Date startDate1, Date endDate1, Date startDate2, Date endDate2) {
		return DateUtils.isBeforeEquals(startDate1, startDate2) && DateUtils.isAfterEquals(endDate1, endDate2);
	}
	
	private static Calendar todayCalendar() {
		Calendar calendar = Calendar.getInstance();
		DateUtils.truncate(calendar);
		return calendar;
	}
	
	/**
	 * @return a day after today
	 */
	public static Date tomorrow() {
		Calendar calendar = DateUtils.todayCalendar();
		return DateUtils.addDays(calendar.getTime(), 1);
	}
	
	/**
	 * @return this day
	 */
	public static Date today() {
		Calendar calendar = DateUtils.todayCalendar();
		return calendar.getTime();
	}
	
	/**
	 * @return a day before today
	 */
	public static Date yesterday() {
		Calendar calendar = DateUtils.todayCalendar();
		return DateUtils.addDays(calendar.getTime(), -1);
	}
	
	/**
	 * @param months amount of months to move the calendar
	 * @return a date that is <code>months</code> in the future/past. Use negative values for past dates.
	 */
	public static Date monthsAway(int months) {
		return DateUtils.addMonths(DateUtils.todayCalendar().getTime(), months);
	}
	
	/**
	 * @return a date that is one month in the future
	 */
	public static Date oneMonthInFuture() {
		return DateUtils.monthsAway(1);
	}
	
	/**
	 * @return a date that is one month in the past
	 */
	public static Date oneMonthInPast() {
		return DateUtils.monthsAway(-1);
	}
	
	/**
	 * @param date Date that includes the desired month in order to calculate the last day of that month
	 * @return the date of the last day of the month
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		DateUtils.truncate(calendar);
		return calendar.getTime();
	}
	
	/**
	 * @param fromDate the start date
	 * @param toDate the end date
	 * @return an integer representing the amount of days between fromDate and toDate
	 */
	public static Integer differenceInDays(Date fromDate, Date toDate) {
		Long diff = toDate.getTime() - fromDate.getTime();
		diff = diff / (DateUtils.MILLIS_PER_DAY);
		return diff.intValue();
	}
	
	/**
	 * @param fromDate the start date
	 * @param toDate the end date
	 * @return an integer representing the amount of days between fromDate and toDate
	 */
	public static Integer differenceInDaysRoundedUp(Date fromDate, Date toDate) {
		Long diff = toDate.getTime() - fromDate.getTime();
		diff = (long)Math.ceil((double)diff / (DateUtils.MILLIS_PER_DAY));
		return diff.intValue();
	}
	
	/**
	 * @param fromDate the start date
	 * @param toDate the end date
	 * @return an integer representing the amount of hours between fromDate and toDate
	 */
	public static Integer differenceInHours(Date fromDate, Date toDate) {
		Long diff = toDate.getTime() - fromDate.getTime();
		diff = diff / (DateUtils.MILLIS_PER_HOUR);
		return diff.intValue();
	}
	
	/**
	 * @param fromDate the start date
	 * @param toDate the end date
	 * @return an integer representing the amount of minutes between fromDate and toDate
	 */
	public static Integer differenceInMinutes(Date fromDate, Date toDate) {
		Long diff = toDate.getTime() - fromDate.getTime();
		diff = diff / (DateUtils.MILLIS_PER_MINUTE);
		return diff.intValue();
	}
	
	public static String formatSecondsAndMilli(long duration) {
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		return seconds + "s, " + (duration - (seconds * 1000)) + "ms";
	}
	

	public static boolean isSameDay(Date fromDate, Date toDate) {
		return ((getDay(toDate) - getDay(fromDate)) == 0) && ((getMonth(toDate) - getMonth(fromDate)) == 0)
				&& ((getYear(toDate) - getYear(fromDate)) == 0);
	}
	
	public static int getYearsToNow(Date date) {
		return differenceInYears(date, DateUtils.now());
	}
	
	public static int differenceInYears(Date firstDate, Date secondDate) {
		Calendar firstDateCalendar = Calendar.getInstance();
		firstDateCalendar.setTime(firstDate);
		Calendar secondDateCalendar = Calendar.getInstance();
		secondDateCalendar.setTime(secondDate);
		int age = secondDateCalendar.get(Calendar.YEAR) - firstDateCalendar.get(Calendar.YEAR);
		if (secondDateCalendar.get(Calendar.MONTH) < firstDateCalendar.get(Calendar.MONTH)) {
			age--;
		} else if ((secondDateCalendar.get(Calendar.MONTH) == firstDateCalendar.get(Calendar.MONTH))
				&& (secondDateCalendar.get(Calendar.DAY_OF_MONTH) < firstDateCalendar.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}
		return age;
	}
	
	public static String getMonthName(Date date) {
		return DateUtils.format(date, MMM);
	}

    public static long toUnixTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    public static Date fromUnixToDate(long seconds) {
        return new Date(seconds * 1000);

    }

}
