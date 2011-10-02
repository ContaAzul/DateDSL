package com.unimondes.dsl.datedsl;

import java.util.Calendar;
import java.util.Date;

public final class DateDsl {
	
	private DateDsl() {}
	
	public static DateBuilder date() {
		return new DateBuilder();
	}
	
	/**
	 * Creation du builder a l'aide d'une date précise.
	 * @param date Date 
	 * @return DateBuilder
	 */
	public static DateBuilder date(Date date) {
		return new DateBuilder(date);
	}

	/**
	 * Creation du builder avec une date précise représentée par un 
	 * Calendar.
	 * @param calendar Calendar
	 * @return DateBuilder
	 */
	public static DateBuilder date(Calendar calendar) {
		return new DateBuilder(calendar);
	}

	/**
	 * Création du builder initialisé a la date de création.
	 * @return DateBuilder
	 */
	public static DateBuilder now() {
		return new DateBuilder();
	}

	/**
	 * Création du builder initialisé a la date du lendemain (hh/mm/ss = celle de creation).
	 * @return DateBuilder
	 */
	public static DateBuilder tomorrow() {
		DateBuilder date = new DateBuilder(new Date());
		return date.add(days(1));
	}

	/**
	 * Création du builder initialisé a la date de la veille (hh/mm/ss = celle de creation).
	 * @return DateBuilder
	 */
	public static DateBuilder yesterday() {
		DateBuilder date = new DateBuilder(new Date());
		return date.subtract(days(1));
	}

	public static DateBuilder emptyDate() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		return new DateBuilder(cal.getTime());
	}
	
	public static TimeUnit months(int n) {
		return new TimeUnit(Calendar.MONTH, n);
	}

	public static TimeUnit years(int n) {
		return new TimeUnit(Calendar.YEAR, n);
	}

	public static TimeUnit days(int n) {
		return new TimeUnit(Calendar.DAY_OF_MONTH, n);
	}

	public static TimeUnit workingDays(int n) {
		return new TimeUnit(Calendar.DAY_OF_MONTH, n, true);
	}

	public static TimeUnit hours(int n) {
		return new TimeUnit(Calendar.HOUR, n);
	}

	public static TimeUnit minutes(int n) {
		return new TimeUnit(Calendar.MINUTE, n);
	}

	public static TimeUnit secondes(int n) {
		return new TimeUnit(Calendar.SECOND, n);
	}

	public static TimeUnit milliSecondes(int n) {
		return new TimeUnit(Calendar.MILLISECOND, n);
	}

}
