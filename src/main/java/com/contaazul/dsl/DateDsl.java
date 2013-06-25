/**
 * 
 */
package com.contaazul.dsl;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

/**
 * Simple Fluent interface for Date api. use with
 * <code>import static com.unimondes.dsl.DateDsl.*</code>
 * 
 * @author <a href="mailto:jgaucher@sedona.fr">Julien Gaucher</a>
 * @version 0.1
 */
public class DateDsl {

	public static final String FORMAT_ = "";

	/**
	 * Creation du builder (par defaut a la date du jour) placer les valeurs a
	 * l'aide des methodes withXXXX(); exemple :
	 * date().withYear(2008").withMonth(Calendar.JANUARY).toDate();
	 * 
	 * @return DateBuilder
	 */
	public static DateBuilder date() {
		return new DateBuilder();
	}

	/**
	 * Creation du builder avec une date pr�cise repr�sent�e par un Calendar.
	 * 
	 * @param calendar
	 *            Calendar
	 * @return DateBuilder
	 */
	public static DateBuilder date(Calendar calendar) {
		return new DateBuilder(calendar);
	}

	/**
	 * Creation du builder a l'aide d'une date pr�cise.
	 * 
	 * @param date
	 *            Date
	 * @return DateBuilder
	 */
	public static DateBuilder date(Date date) {
		return new DateBuilder(date);
	}

	public static DateBuilder date(int year, int monthOfYear, int dayOfMonth) {
		return date( year, monthOfYear, dayOfMonth, 0, 0, 0, 0 );
	}

	public static DateBuilder date(int year, int monthOfYear, int dayOfMonth,
			int hourOfDay, int minuteOfHour, int secondOfMinute,
			int millisOfSecond) {
		DateBuilder db = new DateBuilder();
		db.withYear(year);
		db.withMonth(monthOfYear);
		db.withDayOfMonth(dayOfMonth);
		db.withHourOfDay(hourOfDay);
		db.withMinute(minuteOfHour);
		db.withSecond(secondOfMinute);
		db.withMillisecond(millisOfSecond);

		return db;
	}

	public static DateBuilder date(int year, Months monthOfYear, int dayOfMonth) {
		return date( year, monthOfYear, dayOfMonth, 0, 0, 0, 0 );
	}

	public static DateBuilder date(int year, Months monthOfYear,
			int dayOfMonth, int hourOfDay, int minuteOfHour,
			int secondOfMinute, int millisOfSecond) {
		DateBuilder db = new DateBuilder();
		db.withYear(year);
		db.withMonth(monthOfYear);
		db.withDayOfMonth(dayOfMonth);
		db.withHourOfDay(hourOfDay);
		db.withMinute(minuteOfHour);
		db.withSecond(secondOfMinute);
		db.withMillisecond(millisOfSecond);

		return db;
	}

	public static TimeUnit day() {
		return days( 1 );
	}

	public static TimeUnit days(int n) {
		return new TimeUnit(DAY_OF_MONTH, n);
	}

	/**
	 * Creation d'une date au 1er janvier 1970 00h00m00s00ms, qui est la date du
	 * debut de l'univers comme tout unixien le sait.
	 * 
	 * @return DateBuilder
	 */
	public static DateBuilder emptyDate() {

		Calendar cal = Calendar.getInstance();
		cal.clear();
		return new DateBuilder(cal.getTime());
	}

	public static TimeUnit hour() {
		return hours( 1 );
	}

	public static TimeUnit hours(int n) {
		return new TimeUnit(HOUR, n);
	}

	public static TimeUnit milliSecond() {
		return milliSecondes( 1 );
	}

	public static TimeUnit milliSecondes(int n) {
		return new TimeUnit(MILLISECOND, n);
	}

	public static TimeUnit minute() {
		return minutes( 1 );
	}

	public static TimeUnit minutes(int n) {
		return new TimeUnit(MINUTE, n);
	}

	public static TimeUnit month() {
		return months( 1 );
	}

	public static TimeUnit months(int n) {
		return new TimeUnit(MONTH, n);
	}

	public static TimeUnit months(Months n) {
		return new TimeUnit(MONTH, n.calendaMonth);
	}

	/**
	 * Cr�ation du builder initialis� a la date de cr�ation.
	 * 
	 * @return DateBuilder
	 */
	public static DateBuilder now() {
		return new DateBuilder();
	}

	public static TimeRange range() {
		return new TimeRange();
	}

	public static TimeRange range(Calendar date, TimeUnit size) {
		return new TimeRange(date, size);
	}

	public static TimeRange range(Date date, TimeUnit size) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new TimeRange(cal, size);
	}

	public static TimeRange range(DateBuilder date, TimeUnit size) {
		return new TimeRange(date.toCalendar(), size);
	}

	public static TimeUnit second() {
		return secondes( 1 );
	}

	public static TimeUnit secondes(int n) {
		return new TimeUnit(SECOND, n);
	}

	/**
	 * Cr�ation du builder initialis� a la date du lendemain (hh/mm/ss = celle
	 * de creation).
	 * 
	 * @return DateBuilder
	 */
	public static DateBuilder tomorrow() {
		DateBuilder date = new DateBuilder(new Date());
		return date.add(days(1));
	}

	public static TimeUnit workingDays(int n) {
		return new TimeUnit(DAY_OF_MONTH, n, true);
	}

	public static TimeUnit year() {
		return years( 1 );
	}

	public static TimeUnit years(int n) {
		return new TimeUnit(YEAR, n);
	}

	/**
	 * Cr�ation du builder initialis� a la date de la veille (hh/mm/ss = celle
	 * de creation).
	 * 
	 * @return DateBuilder
	 */
	public static DateBuilder yesterday() {
		DateBuilder date = new DateBuilder(new Date());
		return date.subtract(days(1));
	}
}
