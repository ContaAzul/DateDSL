package com.contaazul.dsl;

import static com.contaazul.dsl.DateDsl.date;
import static com.contaazul.dsl.DateDsl.days;
import static com.contaazul.dsl.DateDsl.emptyDate;
import static com.contaazul.dsl.DateDsl.hours;
import static com.contaazul.dsl.DateDsl.milliSecondes;
import static com.contaazul.dsl.DateDsl.minutes;
import static com.contaazul.dsl.DateDsl.now;
import static com.contaazul.dsl.DateDsl.range;
import static com.contaazul.dsl.DateDsl.tomorrow;
import static com.contaazul.dsl.DateDsl.workingDays;
import static com.contaazul.dsl.DateDsl.years;
import static com.contaazul.dsl.DateDsl.yesterday;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @author <a href="mailto:jgaucher@sedona.Fr">Julien Gaucher</a>
 *
 */
public class DateDslTest {

	public void testDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmptyDate() {
		Date date = emptyDate().toDate();
		assertEquals(0, date(date).getMillisecond());
		assertEquals(0, date(date).getSecond());
		assertEquals(0, date(date).getMinute());
		assertEquals(0, date(date).getHour());
		assertEquals(1, date(date).getDayOfMonth());
		//assertEquals(0, date(date).getDayOfWeek());
		assertEquals(Calendar.JANUARY, date(date).getMonth());
		assertEquals(1970, date(date).getYear());

	}
	
	@Test
	public void testNow() {
		
		//test avec setEqual
		Date date1 = new Date();
		Date date2 = now().setAsEqual(date1).toDate();
		assertEquals(date1, date2);
		
		
		//autre version
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		Date date3 = cal.getTime();
		
		Date date4 = now().clearTime().toDate();
		
		assertEquals(date3, date4);
		
	}

	@Test
	public void testTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		Date date1 = cal.getTime();
		Date date2 = tomorrow().clearTime().toDate();
		assertEquals(date1, date2);
		
		
		//test avec setEqual
		Date date3 = new Date();
		Date date4 = tomorrow().setAsEqual(date3).toDate();
		assertEquals(date3, date4);
		
	}
	
	@Test
	public void testYesterday() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		Date date1 = cal.getTime();
		
		Date date2 = yesterday().clearTime().toDate();
		
		assertEquals(date1, date2);
		
		
		//test avec setEqual
		Date date3 = new Date();
		Date date4 = yesterday().setAsEqual(date3).toDate();
		assertEquals(date3, date4);
	}
	
	@Test
	public void testSetEqualDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date date1 = cal.getTime();
		
		
		Date date2 = now().add(days(2)).setAsEqual(date1).toDate();
		
		assertEquals(date1, date2);
		assertNotSame(date1, date2);
		
    }
    
	@Test
    public void testSetEqualCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		
		Date date2 = now().add(days(2)).setAsEqual(cal).toDate();
		
		assertEquals(cal.getTime(), date2);
		assertNotSame(cal.getTime(), date2);
    	
    }

	public void testMonths() {
		fail("Not yet implemented");
	}

	public void testYears() {
		fail("Not yet implemented");
	}

	public void testDays() {
		fail("Not yet implemented");
	}

	public void testWorkingDays() {
		fail("Not yet implemented");
	}

	public void testHours() {
		fail("Not yet implemented");
	}

	public void testMinutes() {
		fail("Not yet implemented");
	}

	public void testSecondes() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testIsWeekend() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		assertTrue(date(cal).isWeekend());
		
		Date date1 = cal.getTime();
		assertTrue(date(date1).isWeekend());
		
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		assertTrue(date(cal).isWeekend());
		
		Date date2 = cal.getTime();
		assertTrue(date(date2).isWeekend());
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		assertFalse(date(cal).isWeekend());
		
		Date date3 = cal.getTime();
		assertFalse(date(date3).isWeekend());
		
	}
	
	@Test
	public void testClearTime() {
		
		Calendar calNow = Calendar.getInstance();
		
		
		Date now = now().clearTime().toDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		
		assertEquals(0, cal.get(Calendar.HOUR));
		assertEquals(0, cal.get(Calendar.MINUTE));
		assertEquals(0, cal.get(Calendar.SECOND));
		assertEquals(0, cal.get(Calendar.MILLISECOND));
		
		assertEquals(calNow.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(calNow.get(Calendar.MONTH), cal.get(Calendar.MONTH));
		assertEquals(calNow.get(Calendar.YEAR), cal.get(Calendar.YEAR));
		
	}
	
	
	@Test
	public void makeDate() {
		
		Date date = date().withYear(2008)
			.withMonth(Calendar.JANUARY)
			.withDayOfMonth(15)
			.toDate();
		
		assertEquals(15, date(date).getDayOfMonth());
		assertEquals(Calendar.JANUARY, date(date).getMonth());
		
		Date date2 = date().withYear(2008)
		.withMonth(Calendar.JANUARY)
		.withDayOfWeek(Calendar.WEDNESDAY)
		.toDate();

		assertEquals(Calendar.WEDNESDAY, date(date2).getDayOfWeek());
		assertEquals(Calendar.JANUARY, date(date2).getMonth());
	}
	
	
	@Test
	public void testFirstDayOfMonth() {
		
		Calendar testCal = Calendar.getInstance();
		testCal.set(Calendar.MONTH, Calendar.MARCH);
		testCal.set(Calendar.DAY_OF_MONTH, 1);
		
		
		
		Date date = now().withMonth(Calendar.MARCH).firstDayOfMonth().toDate();
		
		assertEquals(testCal.get(Calendar.DAY_OF_MONTH), date(date).getDayOfMonth());
		assertEquals(testCal.get(Calendar.MONTH), date(date).getMonth());
		
	}
	
	@Test
	public void testDsl() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		
		Date date = now().add(days(2)).clearTime().toDate();
		
		assertEquals(cal.getTime(), date);
		
	}
	
	@Test
	public void firstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 4);
        
		
		Date date = date(cal).firstDayOfMonth().toDate();
		assertEquals(1, date(date).getDayOfMonth());
        
    }
    
	@Test
    public void lastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 4);
		
		Date date = date(cal).lastDayOfMonth().toDate();
		assertEquals(31, date(date).getDayOfMonth());
		
		cal.set(Calendar.MONTH, Calendar.APRIL);
		Date date2 = date(cal).lastDayOfMonth().toDate();
		assertEquals(30, date(date2).getDayOfMonth());
        
        
    }
    
	@Test
    public void firstDayOfNextMonth() {            
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 4);
        
		
		Date date = date(cal).firstDayOfNextMonth().toDate();
		assertEquals(1, date(date).getDayOfMonth());
		assertEquals(Calendar.FEBRUARY, date(date).getMonth());
		
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date date2 = date(cal).firstDayOfNextMonth().toDate();
		assertEquals(1, date(date2).getDayOfMonth());
		assertEquals(Calendar.FEBRUARY, date(date2).getMonth());
        
    }
    
	@Test
    public void lastDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		
		Date date = date(cal).lastDayOfNextMonth().toDate();
		assertEquals(30, date(date).getDayOfMonth());
		assertEquals(Calendar.APRIL, date(date).getMonth());
    }
    
	
	
	@Test
	public void testAdd() {
		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.MILLISECOND, 0);
		cal1.set(Calendar.SECOND, 0);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.HOUR_OF_DAY, 0);
		cal1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(cal1.getTime());
		cal2.add(Calendar.DATE, 3);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(cal1.getTime());
		cal3.add(Calendar.DATE, 7);
		
		Date date1 = date(cal1).add(days(3)).clearTime().toDate();
		assertEquals(cal2.getTime(), date1);
		
		Date date2 = date(cal1).add(workingDays(3)).clearTime().toDate();
		assertEquals(cal2.getTime(), date2);
		
		Date date3 = date(cal1).add(days(7)).clearTime().toDate();
		assertEquals(cal3.getTime(), date3);
		
		Date date4 = date(cal1).add(workingDays(7)).clearTime().toDate();
		assertEquals(Calendar.WEDNESDAY, date(date4).getDayOfWeek());
		
		
		Calendar cal5 = Calendar.getInstance();
		cal5.setTime(cal1.getTime());
		cal5.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		Date lundi = date(cal5).add(days(3)).clearTime().toDate();
		Date mercredi = date(cal5).add(workingDays(3)).clearTime().toDate();
		assertEquals(Calendar.MONDAY, date(lundi).getDayOfWeek());
		assertEquals(Calendar.WEDNESDAY, date(mercredi).getDayOfWeek());
		
	}
	
	@Test
	public void isSameDayAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertFalse(now().setAsEqual(calendar).add(days(2)).isSameDayAs(calendar));
		assertTrue(now().setAsEqual(calendar).add(minutes(2)).isSameDayAs(calendar));
		assertTrue(now().setAsEqual(calendar).isSameDayAs(calendar));
	}
	
	@Test
	public void isSameDayAsDate() {
		Date date = new Date();
		assertFalse(now().setAsEqual(date).add(days(2)).isSameDayAs(date));
		assertTrue(now().setAsEqual(date).add(minutes(2)).isSameDayAs(date));
		assertTrue(now().setAsEqual(date).isSameDayAs(date));
	}

	@Test
	public void isSameAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertFalse(now().setAsEqual(calendar).add(milliSecondes(2)).isSameAs(calendar));
		assertTrue(now().setAsEqual(calendar).isSameAs(calendar));
	}
	
	@Test
	public void isSameAsDate() {
		Date date = new Date();
		assertFalse(now().setAsEqual(date).add(milliSecondes(2)).isSameAs(date));
		assertTrue(now().setAsEqual(date).isSameAs(date));
	}
	
	@Test
	public void isSameTimeAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertFalse(now().setAsEqual(calendar).add(days(2)).add(milliSecondes(2)).isSameTimeAs(calendar));
		assertTrue(now().setAsEqual(calendar).add(days(2)).isSameTimeAs(calendar));
	}
	
	@Test
	public void isSameTimeAsDate() {
		Date date = new Date();
		assertFalse(now().setAsEqual(date).add(days(2)).add(milliSecondes(2)).isSameTimeAs(date));
		assertTrue(now().setAsEqual(date).add(days(2)).isSameTimeAs(date));
	}
	
	
	@Test
	public void testIsDistinct() {
		
		assertFalse(
				range()
					.startWith(now()).endWith(tomorrow())
					.isDistinctFrom(
							range().startWith(yesterday()).endWith(now()
					)
				)
		);
		assertTrue(
				range()
					.startWith(yesterday()).endWith(now())
					.isDistinctFrom(
							range()
								.startWith(tomorrow()).endWith(tomorrow().add(days(1))
					)
				)
		);
		
		
	}
	
	@Test
	public void testGetElapsedDays() {
		
		assertEquals(1, 
				range()
					.startWith(yesterday()).endWith(now())
					.getElapsedDays()
		);
			
		assertEquals(2, 
				range()
					.startWith(yesterday()).endWith(tomorrow())
					.getElapsedDays()
		);
		
		
	}

	@Test
	public void rangeDateTimeUnit() {
		fail("TODO");
	}
	
	@Test
	public void rangeCalendarTimeUnit() {
		fail("TODO");
	}
	
	@Test
	public void rangeDateBuilderTimeUnit() {
		fail("TODO");
	}
	
	
	@Test 
	public void testIsIn() {
		
		fail("TODO");
	}
	
	@Test 
	public void containsDateBuilder() {
		
		assertFalse(
				range()
					.startWith(now()).endWith(tomorrow())
					.contains(yesterday())
		);
		
		assertTrue(range().startWith(yesterday()).endWith(tomorrow()).contains(yesterday()));
		assertTrue(range().startWith(yesterday()).endWith(tomorrow()).contains(now()));
		assertTrue(range().startWith(yesterday()).endWith(tomorrow()).contains(tomorrow()));
		assertFalse(range().startWith(now()).endWith(tomorrow()).contains(tomorrow().add(days(1))));
	}
	
	@Test 
	public void containsDate() {
		Date date = new Date();
		
		assertFalse(range().startWith(now()).endWith(tomorrow()).contains(yesterday()));
		assertTrue(range().startWith(yesterday()).endWith(tomorrow()).contains(yesterday()));
		assertTrue(range().startWith(yesterday()).endWith(tomorrow()).contains(now()));
		assertTrue(range().startWith(yesterday()).endWith(tomorrow()).contains(tomorrow()));
		assertFalse(range().startWith(now()).endWith(tomorrow()).contains(tomorrow().add(days(1))));
	}
	
	@Test 
	public void containsCalendar() {
		
		fail("TODO");
	}
	
	@Test 
	public void follow() {
		
		fail("TODO");
	}
	
	
	@Test
	public void testArrache() {
		
		int nbDays = range()
						.startWith(yesterday()).endWith(
								now().add(years(5)).add(hours(50))
						)
						.getElapsedDays();
		
		
		System.out.println(nbDays);
	}

}
