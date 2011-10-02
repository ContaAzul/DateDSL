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
	 * 
	 * 
	 * @param dateFormat {@link SimpleDateFormat}
	 * @return
	 * 
	 */
	public String format(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(this.date.getTime());
	}

	public boolean isWeekend() {
		return ((date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY));
	}
	
	public boolean isToday() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedDays() == 0;
	}
	
	public boolean isPastDate() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedDays() > 0;
	}
	
	public boolean isFutureDate() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedDays() < 0;
	}
	
	public boolean isCurrentMonth() {
		return new TimeRange().startWith(date).endWith(date()).getElapsedMonths() == 0;
	}
	
	// 
	
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

	public DateBuilder subtract(TimeUnit unit) {
		date.add(unit.getType(), -unit.getSize());
		return this;
	}
	
	public DateBuilder setAsEqual(Date date) {
		date.setTime(this.date.getTime().getTime());
		return this;
	}

	public DateBuilder setAsEqual(Calendar calendar) {
		calendar.setTime(date.getTime());
		return this;
	}

	public boolean isSameDayAs(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return isSameDayAs(calendar);
	}
	
	public boolean isSameDayAs(Calendar calendar) {
		return (date.get(Calendar.ERA) == calendar.get(Calendar.ERA) &&
				date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
				date.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR));
	}
	
	public boolean isSameTimeAs(Date dateToCompare) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToCompare);
		return isSameTimeAs(calendar);
		
	}
	
	public boolean isSameTimeAs(Calendar calendar) {
		return (date.get(Calendar.HOUR) == calendar.get(Calendar.HOUR) &&
				date.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE) &&
				date.get(Calendar.SECOND) == calendar.get(Calendar.SECOND) &&
				date.get(Calendar.MILLISECOND) == calendar.get(Calendar.MILLISECOND));
	}
	
	public boolean isSameAs(Date dateToCompare) {
		return date.getTime().getTime() == dateToCompare.getTime();
	}
	
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
	
	public int getMillisecond() {
		return date.get(Calendar.MILLISECOND);
	}

	public int getSecond() {
		return date.get(Calendar.SECOND);
	}

	public int getMinute() {
		return date.get(Calendar.MINUTE);
	}

	public int getHour() {
		return date.get(Calendar.HOUR);
	}

	public int getDayOfWeek() {
		return date.get(Calendar.DAY_OF_WEEK);
	}

	public int getWeekMonth() {
		return date.get(Calendar.WEEK_OF_MONTH);
	}

	public int getWeekYear() {
		return date.get(Calendar.WEEK_OF_YEAR);
	}

	public int getDayOfMonth() {
		return date.get(Calendar.DAY_OF_MONTH);
	}

	public int getMonth() {
		return date.get(Calendar.MONTH);
	}

	public int getYear() {
		return date.get(Calendar.YEAR);
	}
	
}