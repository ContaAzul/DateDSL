package com.unimondes.dsl.datedsl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.unimondes.dsl.datedsl.DateDsl.date;
import static com.unimondes.dsl.datedsl.DateDsl.months;

/**
 * 
 * Interface to create fluent date
 *
 */
public final class DateBuilder {
	
	private final Calendar date;

	public DateBuilder() {
		this.date = Calendar.getInstance();
	}

	public DateBuilder(Date date) {
		this.date = Calendar.getInstance();
		this.date.setTime(date);
	}
	
	public DateBuilder(Calendar calendar) {
		this.date = Calendar.getInstance();
		this.date.setTime(calendar.getTime());
	}

	/**
	 * 
	 * Set milliseconds for the date.
	 * 
	 * @param millisecond
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withMillisecond(int millisecond) {
		date.set(Calendar.MILLISECOND, millisecond);
		return this;
	}

	/**
	 * 
	 * Set secounds for the date.
	 * 
	 * @param second
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withSecond(int second) {
		date.set(Calendar.SECOND, second);
		return this;
	}

	/**
	 * 
	 * Set minutes for the date.
	 * 
	 * @param minute
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withMinute(int minute) {
		date.set(Calendar.MINUTE, minute);
		return this;
	}

	/**
	 * 
	 * Set hours for the date.
	 * 
	 * @param hour
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withHour(int hour) {
		date.set(Calendar.HOUR, hour);
		return this;
	}

	/**
	 * 
	 * Set day of week for the date.
	 *  
	 * @param day
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withDayOfWeek(int day) {
		date.set(Calendar.DAY_OF_WEEK, day);
		return this;
	}

	/**
	 * 
	 * Set day of month for the date. (1..31)
	 * 
	 * @param day
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withDayOfMonth(int day) {
		date.set(Calendar.DAY_OF_MONTH, day);
		return this;
	}

	/**
	 * Set month for the date.
	 * 
	 * @param month
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withMonth(int month) {
		date.set(Calendar.MONTH, month);
		return this;
	}

	/**
	 * 
	 * Set year for the date.
	 * 
	 * @param year
     *
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder withYear(int year) {
		date.set(Calendar.YEAR, year);
		return this;
	}


	/**
	 * 
	 * Set date to the first day of month
	 * 
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder firstDayOfMonth() {
		date.set(Calendar.DAY_OF_MONTH, 1);
		return this;
	}

	/**
	 * 
	 * Set date to the last day of month
	 * 
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder lastDayOfMonth() {
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		return this;
	}

	/**
	 * 
	 * Set date to the first day of next month
	 * 
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder firstDayOfNextMonth() {
		firstDayOfMonth().add(months(1));
		return this;
	}

	/**
	 * 
	 * Set date to the last day of next month
	 * 
	 * @return DateBuilder
	 * 
	 */
	public DateBuilder lastDayOfNextMonth() {
		add(months(1)).lastDayOfMonth();
		return this;
	}
	
	/**
	 * 
	 * @see Object
	 * 
	 */
	public String toString() {
		return date.getTime().toString();
	}
	
	/**
	 *
     * Get date instance
     *
	 * Return java.util.Date
	 * 
	 * @return {@link Date}
	 * 
	 */
	public Date toDate() {
		return date.getTime ();
	}
	
	/**
	 *
     * Get calendar instance
     *
	 * Return java.util.Calendar
	 * 
	 * @return {@link Calendar}
	 * 
	 */
	public Calendar toCalendar() {
		return date;
	}

	/**
	 * 
	 * Parse date to string
	 * 
	 * @param dateFormat {@link SimpleDateFormat}
     *
	 * @return String date
	 * 
	 */
	public String format(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(this.date.getTime());
	}

    /**
     *
     * Check is date is in weekend
     *
     */
	public boolean isWeekend() {
		return ((date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY));
	}

    /**
     *
     * Check is date is in today
     *
     */
	public boolean isToday() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedDays() == 0;
	}

    /**
     *
     * Check is date is in past
     *
     */
	public boolean isPastDate() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedDays() > 0;
	}

    /**
     *
     * Check the date is in future
     *
     * @return
     */
	public boolean isFutureDate() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedDays() < 0;
	}

    /**
     *
     * Check is date is in current month
     *
     */
	public boolean isCurrentMonth() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedMonths() == 0;
	}

    /**
     *
     * Add @TimeUnit
     *
     * @param unit
     *
     */
	public DateBuilder add(TimeUnit unit) {

		if (unit.isWorkingDay()) {
			int nbDay = unit.getSize();
			int i = 0;
			while (i != nbDay) {

				date.add(unit.getType(), 1);

				if (!isWeekend()) {
					i++;
				}
			}	

		} else {
			date.add(unit.getType(), unit.getSize());
		}
		return this;
	}

    /**
     *
     * Substract @TimeUnit
     *
     * @param unit
     *
     */
	public DateBuilder subtract(TimeUnit unit) {
		date.add(unit.getType(), -unit.getSize());
		return this;
	}

    /**
     *
     * Set this is equals
     *
     * @param date The to set equals
     *
     */
	public DateBuilder setAsEqual(Date date) {
		date.setTime(this.date.getTime().getTime());
		return this;
	}

    /**
     *
     * Set this is equals
     *
     * @param calendar The to set equals
     *
     */
	public DateBuilder setAsEqual(Calendar calendar) {
		calendar.setTime(date.getTime());
		return this;
	}

    /**
     *
     * Check is same date
     *
     * @param date Date to compare
     *
     */
	public boolean isSameDayAs(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isSameDayAs(calendar);
	}

    /**
     *
     * Check is same date
     *
     * @param calendar Date to compare
     *
     */
	public boolean isSameDayAs(Calendar calendar) {
		return (date.get(Calendar.ERA) == calendar.get(Calendar.ERA) &&
				date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
				date.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR));
	}

    /**
     *
     * Check is same time
     *
     * @param date Date to compare
     *
     */
	public boolean isSameTimeAs(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isSameTimeAs(calendar);
		
	}

    /**
     *
     * Check is same time
     *
     * @param calendar Date to compare
     *
     */
	public boolean isSameTimeAs(Calendar calendar) {
		return (date.get(Calendar.HOUR) == calendar.get(Calendar.HOUR) &&
				date.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE) &&
				date.get(Calendar.SECOND) == calendar.get(Calendar.SECOND) &&
				date.get(Calendar.MILLISECOND) == calendar.get(Calendar.MILLISECOND));
	}

    /**
     *
     * Check is same datetime
     *
     * @param date Date to compare
     *
     */
	public boolean isSameAs(Date date) {
		return date.getTime() == date.getTime();
	}

    /**
     *
     * Check is same datetime
     *
     * @param calendar Date to compare
     *
     */
	public boolean isSameAs(Calendar calendar) {
		return date.getTime().getTime() == calendar.getTime().getTime();
	}
	
//	public boolean differenceInDays(Calendar date) {
//		
//	}
//	
//	public boolean differenceInDays(Date date) {
//		
//	}

    /**
     *
     * Get millisecond
     *
     */
	public int getMillisecond() {
		return date.get(Calendar.MILLISECOND);
	}

    /**
     *
     * Get second
     *
     */
	public int getSecond() {
		return date.get(Calendar.SECOND);
	}

    /**
     *
     * Get minute
     *
     */
	public int getMinute() {
		return date.get(Calendar.MINUTE);
	}

    /**
     *
     * Get hour
     *
     */
	public int getHour() {
		return date.get(Calendar.HOUR);
	}

    /**
     *
     * Get Day Of Week
     *
     */
	public int getDayOfWeek() {
		return date.get(Calendar.DAY_OF_WEEK);
	}

    /**
     *
     * Get Week Of Month
     *
     */
	public int getWeekMonth() {
		return date.get(Calendar.WEEK_OF_MONTH);
	}

    /**
     *
     * Get Week Of Year
     *
     */
	public int getWeekYear() {
		return date.get(Calendar.WEEK_OF_YEAR);
	}

    /**
     *
     * Get Day Of Month
     *
     */
	public int getDayOfMonth() {
		return date.get(Calendar.DAY_OF_MONTH);
	}

    /**
     *
     * Get Month
     *
     */
	public int getMonth() {
		return date.get(Calendar.MONTH);
	}

    /**
     *
     * Get year
     *
     */
	public int getYear() {
		return date.get(Calendar.YEAR);
	}
	
}