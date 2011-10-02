package com.unimondes.dsl.datedsl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeRange {

	private Calendar startDate;
	private Calendar endDate;

	public TimeRange() {
		super();
	}
	
	public TimeRange(Calendar calendar, TimeUnit size) {
		super();
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate.getTime());
		
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(startDate.getTime());
		this.endDate.add(size.getType(), size.getSize());
	}

	public TimeRange startWith(final Date startDate) {
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate);
		return this;
	}
	
	public TimeRange startWith(final Calendar startDate) {
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate.getTime());
		return this;
	}
	
	public TimeRange startWith(final DateBuilder startDate) {
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate.toDate());
		return this;
	}
	
	public TimeRange endWith(final Date endDate) {
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(endDate);
		return this;
	}
	
	public TimeRange endWith(final Calendar endDate) {
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(endDate.getTime());
		return this;
	}
	
	public TimeRange endWith(final DateBuilder endDate) {
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(endDate.toDate());
		return this;
	}
	
	public boolean contains(final Calendar date) {
		return !date.before(startDate) && !date.after(endDate);		
	}
	
	public boolean contains(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return contains(calendar);
	}
	
	public boolean contains(DateBuilder date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date.toDate());
		return contains(calendar);
	}

	/**
	 * Elapsed days based on two Date objects
	 *
	 * @param d1 Date
	 * @param d2 Date
	 *
	 * @return int number of days
	 */
	public int getElapsedDays() {
		return elapsed(Calendar.DATE);
	}

	/**
	 * Elapsed months based on two Date objects
	 *
	 * @param d1 Date
	 * @param d2 Date
	 *
	 * @return int number of months
	 */
	public int getElapsedMonths() {
		return elapsed(Calendar.MONTH);
	}
	
	/**
	 * Elapsed years based on current time
	 *
	 * @param date Date
	 * @return int number of years
	 */
	public int getElapsedYears() {
		return elapsed(Calendar.YEAR);
	}
	    

	 /**
     * All elaspsed types
     *
     * @param g1 GregorianCalendar
     * @param g2 GregorianCalendar
     * @param type int (Calendar.FIELD_NAME)
     *
     * @return int number of elapsed "type"
     */
	private int elapsed(int type) {
		
        GregorianCalendar gc1, gc2;
        int elapsed = 0;
        // Create copies since we will be clearing/adding
        if (endDate.after(startDate)) {
            gc2 = (GregorianCalendar) endDate.clone();
            gc1 = (GregorianCalendar) startDate.clone();
        } else {
            gc2 = (GregorianCalendar) startDate.clone();
            gc1 = (GregorianCalendar) endDate.clone();
        }
        
        gc1.clear(Calendar.HOUR);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.HOUR);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MILLISECOND);
        
        if (type == Calendar.MONTH || type == Calendar.YEAR) {
            gc1.set(Calendar.DAY_OF_MONTH, 1);
            gc2.set(Calendar.DAY_OF_MONTH, 1);
        }
        if (type == Calendar.YEAR) {
            gc1.clear(Calendar.MONTH);
            gc2.clear(Calendar.MONTH);
        }
        while (gc1.before(gc2)) {
            gc1.add(type, 1);
            elapsed++;
        }
        
        if (startDate.after(endDate)) {
        	elapsed *= -1;
        }
        
        return elapsed;
        
    }
	
	public boolean isDistinctFrom(TimeRange range)  {
		return (range.endDate.before(startDate) && range.startDate.before(startDate)) 
			|| (range.endDate.after(endDate) && range.startDate.after(endDate));
	}
	
}