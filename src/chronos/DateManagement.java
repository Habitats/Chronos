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
		return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(start);
	}

	public static String getFormattedSimple(Date start) {
		return new SimpleDateFormat("dd.MM").format(start);
	}

	public static Date getDateFromString(String str) {
		try {
			return new SimpleDateFormat("dd.MM.yyyy").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Weekday getWeekday(Date start) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		int ordinal = cal.get(Calendar.DAY_OF_WEEK);
		return Weekday.getWeekday(ordinal);
	}

	public static int getCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);

		SimpleDateFormat format = new SimpleDateFormat("ww");
		format.setCalendar(cal);
		format.format(System.currentTimeMillis());

		int week = Integer.parseInt(format.format(System.currentTimeMillis()));
		return week;
	}

	/**
	 * method that returns which week the date-parameter is in.
	 * 
	 * @param start
	 * @return
	 */
	public static int getWeek(Date start) {
		// litt hjelp her patrick
		int week = 0;
		return week;
	}

	public static int getCurrentYear() {
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(System.currentTimeMillis()));
		return year;
	}

	public static void main(String[] args) {
		System.out.println(DateManagement.getFormattedFull(new Date()));
		System.out.println(DateManagement.getFormattedSimple(new Date()));
		System.out.println("Current years: " + getCurrentYear());
		System.out.println("Current week: " + getCurrentWeek());
		System.out.println(getFormattedSimple(getDateFromString("20.12.2013")));
	}
}
