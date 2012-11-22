package com.contaazul.dsl;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateBuilder {

	private final Calendar date;

	public DateBuilder() {
		this.date = Calendar.getInstance();
	}

	public DateBuilder(Calendar calendar) {
		this.date = Calendar.getInstance();
		this.date.setTime(calendar.getTime());
	}

	public DateBuilder(Date date) {
		this.date = Calendar.getInstance();
		this.date.setTime(date);
	}

	/**
	 * Ajout de TimeUnit a une date.
	 * 
	 * @param unit
	 *            TimeUnit
	 * @return DateBuilder
	 */
	public DateBuilder add(TimeUnit unit) {

		if (unit.workingDay) {
			int nbDay = unit.size;
			int i = 0;
			while (i != nbDay) {

				date.add(unit.type, 1);

				if (!isWeekend()) {
					i++;
				}
			}

		} else {
			date.add(unit.type, unit.size);
		}
		return this;
	}

	/**
	 * Mise a 0 de la partie heure/minutes/secondes/millisecondes de la date.
	 * 
	 * @return DateBuilder
	 */
	public DateBuilder clearTime() {
		date.set(HOUR_OF_DAY, 0);
		date.clear(MINUTE);
		date.clear(SECOND);
		date.clear(MILLISECOND);
		return this;

	}

	/**
	 * Place la date au premier jour du mois.
	 * 
	 * @return DateBuilder
	 */
	public DateBuilder firstDayOfMonth() {
		date.set(DAY_OF_MONTH, 1);
		return this;
	}

	/**
	 * Place la date au premier jour du mois suivant.
	 * 
	 * @return DateBuilder
	 */
	public DateBuilder firstDayOfNextMonth() {
		firstDayOfMonth().add(DateDsl.months(1));
		return this;
	}

	public String format(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(this.date.getTime());
	}

	public int getDayOfMonth() {
		return date.get(Calendar.DAY_OF_MONTH);
	}

	public int getDayOfWeek() {
		return date.get(Calendar.DAY_OF_WEEK);
	}

	public int getHour() {
		return date.get(Calendar.HOUR);
	}

	public int getHourOfDay() {
		return date.get(Calendar.HOUR_OF_DAY);
	}

	/*
	 * une liste de geter pour recuperer facilement des valeurs
	 */
	public int getMillisecond() {
		return date.get(Calendar.MILLISECOND);
	}

	public int getMinute() {
		return date.get(Calendar.MINUTE);
	}

	public int getMonth() {
		return date.get(Calendar.MONTH);
	}

	public Months getMonths() {
		return Months.values()[date.get(Calendar.MONTH)];
	}

	public int getSecond() {
		return date.get(Calendar.SECOND);
	}

	public WeekDays getWeekDay() {
		return WeekDays.values()[date.get(Calendar.DAY_OF_WEEK)];
	}

	public int getWeekMonth() {
		return date.get(Calendar.WEEK_OF_MONTH);
	}

	public int getWeekYear() {
		return date.get(Calendar.WEEK_OF_YEAR);
	}

	public int getYear() {
		return date.get(Calendar.YEAR);
	}

	/**
	 * Checks if calendar object represent the same date and time.
	 * 
	 * @param calendar
	 *            Calendar
	 * @return true if they represent the same millisecond instant
	 */
	public boolean isSameAs(Calendar calendar) {
		return date.getTime().getTime() == calendar.getTime().getTime();
	}

	/**
	 * Checks if date object represent the same date and time.
	 * 
	 * @param date
	 *            Date
	 * @return true if they represent the same millisecond instant
	 */
	public boolean isSameAs(Date dateToCompare) {
		return date.getTime().getTime() == dateToCompare.getTime();

	}

	/**
	 * Checks if calendar object is on the same day ignoring time.
	 * 
	 * @param calendar
	 *            Calendar
	 * @return true if it is the same day
	 */
	public boolean isSameDayAs(Calendar calendar) {
		return (date.get(Calendar.ERA) == calendar.get(Calendar.ERA)
				&& date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && date
					.get(Calendar.DAY_OF_YEAR) == calendar
				.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * Checks if date object is on the same day ignoring time.
	 * 
	 * @param date
	 *            Date
	 * @return true if it is the same day
	 */
	public boolean isSameDayAs(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isSameDayAs(calendar);
	}

	/**
	 * Checks if calendar object represent the same instant in time.
	 * 
	 * @param calendar
	 *            Calendar
	 * @return true if they represent the same millisecond instant
	 */
	public boolean isSameTimeAs(Calendar calendar) {
		return (date.get(Calendar.HOUR) == calendar.get(Calendar.HOUR)
				&& date.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE)
				&& date.get(Calendar.SECOND) == calendar.get(Calendar.SECOND) && date
					.get(Calendar.MILLISECOND) == calendar
				.get(Calendar.MILLISECOND));
	}

	/**
	 * Checks if date object represent the same instant in time.
	 * 
	 * @param date
	 *            Date
	 * @return true if they represent the same millisecond instant
	 */
	public boolean isSameTimeAs(Date dateToCompare) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToCompare);
		return isSameTimeAs(calendar);

	}

	/**
	 * methode utilitaire pour determiner si c'est un jour du week end.
	 * 
	 * @return true si jour = samedi ou dimanche, false dans le cas contraire.
	 */
	public boolean isWeekend() {

		if ((date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
				|| (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Place la date au dernier jour du mois.
	 * 
	 * @return DateBuilder
	 */
	public DateBuilder lastDayOfMonth() {
		date.set(DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		return this;
	}

	/**
	 * Place la date au dernier jour du mois suivant.
	 * 
	 * @return DateBuilder
	 */
	public DateBuilder lastDayOfNextMonth() {
		add(DateDsl.months(1)).lastDayOfMonth();
		return this;
	}

	public DateBuilder setAsEqual(Calendar calendar) {
		calendar.setTime(date.getTime());
		return this;
	}

	public DateBuilder setAsEqual(Date dateExt) {
		dateExt.setTime(date.getTime().getTime());
		return this;
	}

	/**
	 * Soustraction de timeUnit a une date.
	 * 
	 * @param unit
	 *            TimeUnit
	 * @return DateBuilder
	 */
	public DateBuilder subtract(TimeUnit unit) {
		date.add(unit.type, -unit.size);
		return this;
	}

	public Calendar toCalendar() {
		return date;
	}

	public Date toDate() {
		return date.getTime();
	}

	public String toLongStringDate() {
		return null;
	}

	public String toShortStringDate() {
		return null;
	}

	@Override
	public String toString() {
		return date.getTime().toString();
	}

	public String toStringDate() {
		return null;
	}

	/**
	 * seter pour le jours du mois de la date (1..31)
	 * 
	 * @param dayOfMonth
	 * @return DateBuilder
	 */
	public DateBuilder withDayOfMonth(int day) {
		date.set(DAY_OF_MONTH, day);
		return this;
	}

	/**
	 * seter pour le jours de la semaine de la date (LUNDI/MARDI etc.).
	 * 
	 * @param dayOfWeek
	 * @return DateBuilder
	 */
	public DateBuilder withDayOfWeek(int day) {
		date.set(DAY_OF_WEEK, day);
		return this;
	}

	public DateBuilder withDayOfWeek(WeekDays day) {
		date.set(DAY_OF_WEEK, day.calendaWeekday);
		return this;
	}

	/**
	 * seter pour les heures de la date.
	 * 
	 * @param heurs
	 * @return DateBuilder
	 */
	public DateBuilder withHour(int hour) {
		date.set(HOUR, hour);
		return this;
	}

	public DateBuilder withHourOfDay(int hour) {
		date.set(HOUR_OF_DAY, hour);
		return this;
	}

	/**
	 * seter pour les millisecondes de la date.
	 * 
	 * @param millisecond
	 * @return DateBuilder
	 */
	public DateBuilder withMillisecond(int millisecond) {
		date.set(MILLISECOND, millisecond);
		return this;
	}

	/**
	 * seter pour les minutes de la date.
	 * 
	 * @param minutes
	 * @return DateBuilder
	 */
	public DateBuilder withMinute(int minute) {
		date.set(MINUTE, minute);
		return this;
	}

	/**
	 * seter pour le mois de la date.
	 * 
	 * @param month
	 * @return DateBuilder
	 */
	public DateBuilder withMonth(int month) {
		date.set(MONTH, month);
		return this;
	}

	public DateBuilder withMonth(Months month) {
		date.set(MONTH, month.calendaMonth);
		return this;
	}

	/**
	 * seter pour les secondes de la date.
	 * 
	 * @param second
	 * @return DateBuilder
	 */
	public DateBuilder withSecond(int second) {
		date.set(SECOND, second);
		return this;
	}

	/**
	 * seter pour l'ann�e de la date.
	 * 
	 * @param year
	 * @return DateBuilder
	 */
	public DateBuilder withYear(int year) {
		date.set(YEAR, year);
		return this;
	}

	public boolean isFuture() {
		return date.after(Calendar.getInstance());
	}

	public boolean isPast() {
		return date.before(Calendar.getInstance());
	}

	public boolean before(Calendar dt) {
		return date.before(dt);
	}

	public boolean before(DateBuilder dt) {
		return before(dt.toCalendar());
	}

	public boolean before(Date dt) {
		return before(DateDsl.date(dt));
	}
	
	public boolean after(Calendar dt) {
		return date.after(dt);
	}
	
	public boolean after(DateBuilder dt) {
		return after(dt.toCalendar());
	}
	
	public boolean after(Date dt) {
		return after(DateDsl.date(dt));
	}

	public boolean isToday() {
		return isSameDayAs(new Date());
	}

	
}
