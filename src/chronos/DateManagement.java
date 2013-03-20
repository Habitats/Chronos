package chronos;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import client.model.CalendarModel.Weekday;

/**
 * Handles date management
 */
public class DateManagement {

	public static String getFormattedFull(Date start) {
		return new SimpleDateFormat("dd.MM.yyyy - HH:mm").format(start);
	}

	public static String getFormattedDate(Date start) {
		return new SimpleDateFormat("dd.MM.yyyy").format(start);
	}

	public static String getFormattedSimple(Date start) {
		return new SimpleDateFormat("dd.MM").format(start);
	}

	public synchronized static boolean isLessThanFifteenMinFromNow(Date date) {
		long now = new Date().getTime();
		long input = date.getTime();
		long derp = (now - input) / 1000;
		// System.out.println(getFormattedFull(new Date(derp * 1000)));
		return ((input - now) / 1000) < 60 * 15 && input >= now;
	}

	public static long getClockInSeconds(Date time) {
		long hours = Integer.parseInt(new SimpleDateFormat("kk").format(time));
		long minutes = Integer.parseInt(new SimpleDateFormat("mm").format(time));
		return (hours * 3600 + minutes * 60) * 1000;
	}

	public static String getFormattedDateIntervall(Date start) {
		String returnString = "";
		returnString += getFormattedDate(start);
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 6); // number of days to add
		returnString += " - " + getFormattedDate(c.getTime());
		return returnString;
	}

	public static Date getMondayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return cal.getTime();
	}

	public static Date getDateFromString(String str) {
		try {
			return new SimpleDateFormat("dd.MM.yyyy").parse(str);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Weekday getWeekday(Date start) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		int ordinal = cal.get(Calendar.DAY_OF_WEEK);
		return Weekday.getWeekday(ordinal);
	}

	private static int getCurrentWeek() {
		return getWeek(new Date());
	}

	public static String getTimeOfDay(Date date) {
		return new SimpleDateFormat("HH:mm").format(date);
	}

	/**
	 * method that returns which week the date-parameter is in.
	 */
	public static int getWeek(Date start) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);

		SimpleDateFormat format = new SimpleDateFormat("ww");
		format.setCalendar(cal);
		try {
			return Integer.parseInt(format.format(start));
		} catch (Exception e) {
			return -1;
		}
	}

	private static int getCurrentYear() {
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(System.currentTimeMillis()));
		return year;
	}

	/**
	 * Method to increase a date by 1
	 * 
	 * @param start
	 * @return
	 */
	public static Date getNextDay(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 1); // number of days to add
		return c.getTime();
	}

	public static int getYear(Date start) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		try {
			return Integer.parseInt(format.format(start));
		} catch (Exception e) {
			return -1;
		}
	}

	public static String getNextWeek(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, 7);
		return getFormattedDate(c.getTime());
	}

	public static String getPrevWeek(Date start) {
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		c.add(Calendar.DATE, -7);
		return getFormattedDate(c.getTime());
	}

	public static void main(String[] args) {
		Singleton.getInstance().enableLog();

		Singleton.log(DateManagement.getFormattedFull(new Date()));
		Singleton.log(DateManagement.getFormattedSimple(new Date()));
		Singleton.log("Current years: " + getCurrentYear());
		Singleton.log("Current week: " + getCurrentWeek());
		Singleton.log("Current week (from date): " + getWeek(new Date()));
		Singleton.log(getFormattedSimple(getDateFromString("13.03.2013")));
		Singleton.log("Next day: " + getNextDay(new Date()));
		Singleton.log("Date today: " + getFormattedDate(new Date()));
		Singleton.log("Date intervall(one week from date): " + getFormattedDateIntervall(new Date()));
		Singleton.log("Date 7 days from now: " + getNextWeek(new Date()));
		Singleton.log("Year (from Date): " + getYear(new Date()));
		Singleton.log("Monady in week: " + getMondayOfWeek(new Date()));
		Singleton.log("Weekday: " + getWeekday(new Date()));
		Singleton.log("Formatted clock: " + getTimeOfDay(new Date()));
		Singleton.log("Strip date for time: " + getFormattedFull(stripClock(new Date())));
	}

	public static Date stripClock(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
