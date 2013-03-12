package chronos;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

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

	public static int getCurrentWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);

		SimpleDateFormat format = new SimpleDateFormat("ww");
		format.setCalendar(cal);
		format.format(System.currentTimeMillis());

		int week = Integer.parseInt(format.format(System.currentTimeMillis()));
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
	}
}
