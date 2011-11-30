package com.unimondes.dsl.datedsl;

import java.util.Calendar;
import java.util.Date;

public final class DateDsl {
	
	private DateDsl() {}
	
	public static DateBuilder date() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        return new DateBuilder(cal.getTime());
	}
	
	/**
	 * Creation builder with a specific date.
     *
	 * @param date Date
     *
	 * @return DateBuilder
     *
	 */
	public static DateBuilder date(Date date) {
		return new DateBuilder(date);
	}

	/**
     * Creation builder with a specific calendar.
     *
     * @param calendar Calendar
     *
	 * @return DateBuilder
     *
	 */
	public static DateBuilder date(Calendar calendar) {
		return new DateBuilder(calendar);
	}

	/**
	 *
     * Create builder has initialized the creation date.
     *
	 * @return DateBuilder
     *
	 */
	public static DateBuilder now() {
		return new DateBuilder();
	}

	/**
	 * Create the builder initialized to the date of the next day
     *
	 * @return DateBuilder
     *
	 */
	public static DateBuilder tomorrow() {
		DateBuilder date = new DateBuilder(new Date());
		return date.add(days(1));
	}

	/**
	 *
     * Create the builder has initialized the day before the date of
     *
	 * @return DateBuilder
     *
	 */
	public static DateBuilder yesterday() {
		DateBuilder date = new DateBuilder(new Date());
		return date.subtract(days(1));
	}

    /**
     *
     * Create unit of days
     *
     * @param n number of days
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit days(int n) {
		return new TimeUnit(Calendar.DAY_OF_MONTH, n);
	}

    /**
     *
     * Create unit of months
     *
     * @param n number of months
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit months(int n) {
		return new TimeUnit(Calendar.MONTH, n);
	}

    /**
     *
     * Create unit of years
     *
     * @param n number of years
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit years(int n) {
		return new TimeUnit(Calendar.YEAR, n);
	}

    /**
     *
     * Create unit of hours
     *
     * @param n number of hours
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit hours(int n) {
		return new TimeUnit(Calendar.HOUR, n);
	}

    /**
     *
     * Create unit of minutes
     *
     * @param n number of minutes
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit minutes(int n) {
		return new TimeUnit(Calendar.MINUTE, n);
	}

    /**
     *
     * Create unit of seconds
     *
     * @param n number of seconds
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit seconds(int n) {
		return new TimeUnit(Calendar.SECOND, n);
	}

    /**
     *
     * Create unit of milliseconds
     *
     * @param n number of seconds
     *
     * @return TimeUnit
     *
     */
	public static TimeUnit milliSecondes(int n) {
		return new TimeUnit(Calendar.MILLISECOND, n);
	}

    /**
     *
     * Create unit of days ( only workind days )
     *
     * @param n number of days
     *
     * @return TimeUnit
     *
     */
    public static TimeUnit workingDays(int n) {
   		return new TimeUnit(Calendar.DAY_OF_MONTH, n, true);
   	}

}