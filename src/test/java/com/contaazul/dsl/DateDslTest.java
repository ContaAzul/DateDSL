package com.contaazul.dsl;

import static com.contaazul.dsl.DateDsl.date;
import static com.contaazul.dsl.DateDsl.days;
import static com.contaazul.dsl.DateDsl.emptyDate;
import static com.contaazul.dsl.DateDsl.hours;
import static com.contaazul.dsl.DateDsl.milliSecondes;
import static com.contaazul.dsl.DateDsl.minutes;
import static com.contaazul.dsl.DateDsl.month;
import static com.contaazul.dsl.DateDsl.months;
import static com.contaazul.dsl.DateDsl.now;
import static com.contaazul.dsl.DateDsl.range;
import static com.contaazul.dsl.DateDsl.secondes;
import static com.contaazul.dsl.DateDsl.tomorrow;
import static com.contaazul.dsl.DateDsl.workingDays;
import static com.contaazul.dsl.DateDsl.years;
import static com.contaazul.dsl.DateDsl.yesterday;
import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.MARCH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @author <a href="mailto:jgaucher@sedona.Fr">Julien Gaucher</a>
 * 
 */
public class DateDslTest {

	@Test
	public void containsCalendar() {
		assertFalse( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2013, 12, 31 ).toCalendar() ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 2 ).toCalendar() ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 2 ).toCalendar() ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 3 ).toCalendar() ) );
		assertFalse( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 4 ).toCalendar() ) );
	}

	@Test
	public void containsDate() {
		assertFalse( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2013, 12, 31 ).toDate() ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 2 ).toDate() ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 2 ).toDate() ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 3 ).toDate() ) );
		assertFalse( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 4 ).toDate() ) );
	}

	@Test
	public void containsDateBuilder() {
		assertFalse( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2013, 12, 31 ) ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 2 ) ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 2 ) ) );
		assertTrue( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 3 ) ) );
		assertFalse( range().startWith( date( 2014, 1, 1 ) )
				.endWith( date( 2014, 1, 3 ) )
				.contains( date( 2014, 1, 4 ) ) );
	}

	@Test
	public void firstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.MONTH, Calendar.JANUARY );
		cal.set( Calendar.DAY_OF_MONTH, 4 );

		Date date = date( cal ).firstDayOfMonth().toDate();
		assertEquals( 1, date( date ).getDayOfMonth() );
	}

	@Test
	public void firstDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.MONTH, Calendar.JANUARY );
		cal.set( Calendar.DAY_OF_MONTH, 4 );

		Date date = date( cal ).firstDayOfNextMonth().toDate();
		assertEquals( 1, date( date ).getDayOfMonth() );
		assertEquals( Calendar.FEBRUARY, date( date ).getMonth() );

		cal.set( Calendar.MONTH, Calendar.JANUARY );
		cal.set( Calendar.DAY_OF_MONTH, 31 );
		Date date2 = date( cal ).firstDayOfNextMonth().toDate();
		assertEquals( 1, date( date2 ).getDayOfMonth() );
		assertEquals( Calendar.FEBRUARY, date( date2 ).getMonth() );
	}

	@Test
	public void isSameAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertFalse( now().setAsEqual( calendar ).add( milliSecondes( 2 ) )
				.isSameAs( calendar ) );
		assertTrue( now().setAsEqual( calendar ).isSameAs( calendar ) );
	}

	@Test
	public void isSameAsDate() {
		Date date = new Date();
		assertFalse( now().setAsEqual( date ).add( milliSecondes( 2 ) ).isSameAs( date ) );
		assertTrue( now().setAsEqual( date ).isSameAs( date ) );
	}

	@Test
	public void isSameDayAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertFalse( now().setAsEqual( calendar ).add( days( 2 ) )
				.isSameDayAs( calendar ) );
		assertTrue( now().setAsEqual( calendar ).add( minutes( 2 ) )
				.isSameDayAs( calendar ) );
		assertTrue( now().setAsEqual( calendar ).isSameDayAs( calendar ) );
	}

	@Test
	public void isSameDayAsDate() {
		Date date = new Date();
		assertFalse( now().setAsEqual( date ).add( days( 2 ) ).isSameDayAs( date ) );
		assertTrue( now().setAsEqual( date ).add( minutes( 2 ) ).isSameDayAs( date ) );
		assertTrue( now().setAsEqual( date ).isSameDayAs( date ) );
	}

	@Test
	public void isSameTimeAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertFalse( now().setAsEqual( calendar ).add( days( 2 ) )
				.add( milliSecondes( 2 ) ).isSameTimeAs( calendar ) );
		assertTrue( now().setAsEqual( calendar ).add( days( 2 ) )
				.isSameTimeAs( calendar ) );
	}

	@Test
	public void isSameTimeAsDate() {
		Date date = new Date();
		assertFalse( now().setAsEqual( date ).add( days( 2 ) ).add( milliSecondes( 2 ) )
				.isSameTimeAs( date ) );
		assertTrue( now().setAsEqual( date ).add( days( 2 ) ).isSameTimeAs( date ) );
	}

	@Test
	public void lastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.MONTH, Calendar.JANUARY );
		cal.set( Calendar.DAY_OF_MONTH, 4 );

		Date date = date( cal ).lastDayOfMonth().toDate();
		assertEquals( 31, date( date ).getDayOfMonth() );

		cal.set( Calendar.MONTH, Calendar.APRIL );
		Date date2 = date( cal ).lastDayOfMonth().toDate();
		assertEquals( 30, date( date2 ).getDayOfMonth() );
	}

	@Test
	public void lastDayOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.MONTH, Calendar.MARCH );
		cal.set( Calendar.DAY_OF_MONTH, 31 );

		Date date = date( cal ).lastDayOfNextMonth().toDate();
		assertEquals( 30, date( date ).getDayOfMonth() );
		assertEquals( Calendar.APRIL, date( date ).getMonth() );
	}

	@Test
	public void makeDate() {
		Date date = date().withYear( 2008 ).withMonth( Calendar.JANUARY )
				.withDayOfMonth( 15 ).toDate();

		assertEquals( 15, date( date ).getDayOfMonth() );
		assertEquals( Calendar.JANUARY, date( date ).getMonth() );

		Date date2 = date().withYear( 2008 ).withMonth( Calendar.JANUARY )
				.withDayOfWeek( Calendar.WEDNESDAY ).toDate();

		assertEquals( Calendar.WEDNESDAY, date( date2 ).getDayOfWeek() );
		assertEquals( Calendar.JANUARY, date( date2 ).getMonth() );
	}

	@Test
	public void rangeCalendarTimeUnit() {
		Calendar past = Calendar.getInstance();
		past.setTimeInMillis( 0L );
		Calendar future = Calendar.getInstance();
		future.setTimeInMillis( Long.MAX_VALUE );

		assertTrue( range().startWith( past ).endWith( future ).contains( now() ) );
	}

	@Test
	public void rangeDateBuilderTimeUnit() {
		assertTrue( range().startWith( yesterday() ).endWith( tomorrow() )
				.contains( now() ) );
	}

	@Test
	public void rangeDateTimeUnit() {
		Date past = new Date( 0L );
		Date future = new Date( Long.MAX_VALUE );

		assertTrue( range().startWith( past ).endWith( future ).contains( now() ) );
	}

	@Test
	public void testAdd() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set( Calendar.MILLISECOND, 0 );
		cal1.set( Calendar.SECOND, 0 );
		cal1.set( Calendar.MINUTE, 0 );
		cal1.set( Calendar.HOUR_OF_DAY, 0 );
		cal1.set( Calendar.DAY_OF_WEEK, Calendar.MONDAY );

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime( cal1.getTime() );
		cal2.add( Calendar.DATE, 3 );

		Calendar cal3 = Calendar.getInstance();
		cal3.setTime( cal1.getTime() );
		cal3.add( Calendar.DATE, 7 );

		Date date1 = date( cal1 ).add( days( 3 ) ).clearTime().toDate();
		assertEquals( cal2.getTime(), date1 );

		Date date2 = date( cal1 ).add( workingDays( 3 ) ).clearTime().toDate();
		assertEquals( cal2.getTime(), date2 );

		Date date3 = date( cal1 ).add( days( 7 ) ).clearTime().toDate();
		assertEquals( cal3.getTime(), date3 );

		Date date4 = date( cal1 ).add( workingDays( 7 ) ).clearTime().toDate();
		assertEquals( Calendar.WEDNESDAY, date( date4 ).getDayOfWeek() );

		Calendar cal5 = Calendar.getInstance();
		cal5.setTime( cal1.getTime() );
		cal5.set( Calendar.DAY_OF_WEEK, Calendar.FRIDAY );
		Date lundi = date( cal5 ).add( days( 3 ) ).clearTime().toDate();
		Date mercredi = date( cal5 ).add( workingDays( 3 ) ).clearTime().toDate();
		assertEquals( Calendar.MONDAY, date( lundi ).getDayOfWeek() );
		assertEquals( Calendar.WEDNESDAY, date( mercredi ).getDayOfWeek() );
	}

	@Test
	public void testClearTime() {
		Calendar calNow = Calendar.getInstance();

		Date now = now().clearTime().toDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime( now );

		assertEquals( 0, cal.get( Calendar.HOUR ) );
		assertEquals( 0, cal.get( Calendar.MINUTE ) );
		assertEquals( 0, cal.get( Calendar.SECOND ) );
		assertEquals( 0, cal.get( Calendar.MILLISECOND ) );

		assertEquals( calNow.get( Calendar.DAY_OF_MONTH ),
				cal.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( calNow.get( Calendar.MONTH ), cal.get( Calendar.MONTH ) );
		assertEquals( calNow.get( Calendar.YEAR ), cal.get( Calendar.YEAR ) );
	}

	@Test
	public void testEndOfDay() {
		Calendar calNow = Calendar.getInstance();

		Date now = now().endOfDay().toDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime( now );

		assertEquals( 23, cal.get( Calendar.HOUR_OF_DAY ) );
		assertEquals( 59, cal.get( Calendar.MINUTE ) );
		assertEquals( 59, cal.get( Calendar.SECOND ) );
		assertEquals( 999, cal.get( Calendar.MILLISECOND ) );

		assertEquals( calNow.get( Calendar.DAY_OF_MONTH ),
				cal.get( Calendar.DAY_OF_MONTH ) );
		assertEquals( calNow.get( Calendar.MONTH ), cal.get( Calendar.MONTH ) );
		assertEquals( calNow.get( Calendar.YEAR ), cal.get( Calendar.YEAR ) );
	}

	@Test
	public void testDate() {
		Calendar cal = Calendar.getInstance();

		Date date = date().toDate();
		assertEquals( cal.get( Calendar.MILLISECOND ),
				date( date ).getMillisecond(), 20 );
		assertEquals( cal.get( Calendar.SECOND ), date( date ).getSecond() );
		assertEquals( cal.get( Calendar.MINUTE ), date( date ).getMinute() );
		assertEquals( cal.get( Calendar.HOUR_OF_DAY ), date( date ).getHourOfDay() );
		assertEquals( cal.get( Calendar.HOUR ), date( date ).getHour() );
		assertEquals( cal.get( Calendar.DAY_OF_MONTH ), date( date ).getDayOfMonth() );
		assertEquals( cal.get( Calendar.DAY_OF_WEEK ), date( date ).getDayOfWeek() );
		assertEquals( cal.get( Calendar.MONTH ), date( date ).getMonth() );
		assertEquals( cal.get( Calendar.YEAR ), date( date ).getYear() );
	}

	@Test
	public void testDays() {
		DateBuilder dt = emptyDate();
		assertEquals( 1, dt.getDayOfMonth() );
		assertEquals( Calendar.THURSDAY, dt.getDayOfWeek() );

		dt.add( days( 31 ) );
		assertEquals( 1, dt.getDayOfMonth() );
		assertEquals( Calendar.SUNDAY, dt.getDayOfWeek() );
		assertEquals( Calendar.FEBRUARY, dt.getMonth() );

		dt.subtract( days( 1 ) );
		assertEquals( 31, dt.getDayOfMonth() );
		assertEquals( Calendar.SATURDAY, dt.getDayOfWeek() );
		assertEquals( Calendar.JANUARY, dt.getMonth() );
	}

	@Test
	public void testDsl() {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DAY_OF_MONTH, 2 );
		cal.set( Calendar.MILLISECOND, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.HOUR_OF_DAY, 0 );

		Date date = now().add( days( 2 ) ).clearTime().toDate();

		assertEquals( cal.getTime(), date );
	}

	@Test
	public void testEmptyDate() {
		Date date = emptyDate().toDate();
		assertEquals( 0, date( date ).getMillisecond() );
		assertEquals( 0, date( date ).getSecond() );
		assertEquals( 0, date( date ).getMinute() );
		assertEquals( 0, date( date ).getHour() );
		assertEquals( 1, date( date ).getDayOfMonth() );
		// assertEquals(0, date(date).getDayOfWeek());
		assertEquals( Calendar.JANUARY, date( date ).getMonth() );
		assertEquals( 1970, date( date ).getYear() );

	}

	@Test
	public void testFirstDayOfMonth() {
		Calendar testCal = Calendar.getInstance();
		testCal.set( Calendar.MONTH, Calendar.MARCH );
		testCal.set( Calendar.DAY_OF_MONTH, 1 );

		Date date = now().withMonth( Calendar.MARCH ).firstDayOfMonth().toDate();

		assertEquals( testCal.get( Calendar.DAY_OF_MONTH ), date( date )
				.getDayOfMonth() );
		assertEquals( testCal.get( Calendar.MONTH ), date( date ).getMonth() );
	}

	@Test
	public void testGetElapsedDays() {

		assertEquals(
				1,
				range().startWith( yesterday().clearTime() )
						.endWith( now().clearTime() )
						.getElapsedDays() );

		assertEquals(
				2,
				range().startWith( yesterday().clearTime() )
						.endWith( tomorrow().clearTime() )
						.getElapsedDays() );
	}

	@Test
	public void testGetElapsedDaysWithTwoLeapYear() {
		assertEquals(
				1829,
				range().startWith( date( 2015, FEBRUARY, 28 ).clearTime() )
						.endWith( date( 2020, MARCH, 1, 12, 0, 0, 0 ) )
						.getElapsedDays() );
	}

	@Test
	public void testGetElapsedDaysWithOneLeapYear() {
		assertEquals(
				1828,
				range().startWith( date( 2015, FEBRUARY, 27 ).clearTime() )
						.endWith( date( 2020, FEBRUARY, 28, 16, 0, 0, 0 ) )
						.getElapsedDays() );
	}

	@Test
	public void testGetElapsedMonths() {
		assertEquals(
				1,
				range().startWith(
						now().subtract( month() ).clearTime().firstDayOfMonth() )
						.endWith( now().clearTime().firstDayOfMonth() )
						.getElapsedMonths() );

		assertEquals(
				10,
				range().startWith(
						now().subtract( months( 10 ) ).clearTime()
								.firstDayOfMonth() )
						.endWith( now().clearTime().firstDayOfMonth() )
						.getElapsedMonths() );
	}

	@Test
	public void testGetElapsedWeeks() {
		assertEquals(
				2,
				range().startWith(
						now().clearTime().add( days( 8 ) ) )
						.endWith( now().clearTime() )
						.getElapsedWeeks() );
		assertEquals(
				3,
				range().startWith(
						now().clearTime() )
						.endWith( now().clearTime().add( days( 20 ) ) )
						.getElapsedWeeks() );
		assertEquals(
				18,
				range().startWith(
						now().clearTime() )
						.endWith( now().clearTime().add( days( 120 ) ) )
						.getElapsedWeeks() );
	}

	@Test
	public void testHours() {
		DateBuilder dt = emptyDate();
		assertEquals( 0, dt.getHour() );
		assertEquals( 0, dt.getHourOfDay() );
		assertEquals( 1, dt.getDayOfMonth() );

		dt.add( hours( 50 ) );
		assertEquals( 2, dt.getHour() );
		assertEquals( 2, dt.getHourOfDay() );
		assertEquals( 3, dt.getDayOfMonth() );

		dt.subtract( hours( 3 ) );
		assertEquals( 11, dt.getHour() );
		assertEquals( 23, dt.getHourOfDay() );
		assertEquals( 2, dt.getDayOfMonth() );
	}

	@Test
	public void testIsDistinct() {

		assertFalse( range().startWith( now() ).endWith( tomorrow() )
				.isDistinctFrom( range().startWith( yesterday() ).endWith( now() ) ) );
		assertTrue( range()
				.startWith( yesterday() )
				.endWith( now() )
				.isDistinctFrom(
						range().startWith( tomorrow() ).endWith(
								tomorrow().add( days( 1 ) ) ) ) );
	}

	@Test
	public void testIsIn() {
		DateBuilder past = now().withYear( 2005 ).withMonth( Calendar.JANUARY )
				.withDayOfMonth( 1 ).clearTime();
		DateBuilder future = now().withYear( 2006 ).withMonth( Calendar.DECEMBER )
				.withDayOfMonth( 31 ).clearTime();

		TimeRange range = range().startWith( past ).endWith( future );
		assertTrue( range.contains( now().withYear( 2005 ) ) );
		assertTrue( range.contains( now().withYear( 2006 ) ) );
		assertFalse( range.contains( now().withYear( 2007 ) ) );
		assertFalse( range.contains( now().withYear( 2004 ) ) );
		assertTrue( range.contains( past ) );
		assertTrue( range.contains( future ) );
	}

	@Test
	public void testIsWeekend() {
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.DAY_OF_WEEK, Calendar.SATURDAY );
		assertTrue( date( cal ).isWeekend() );

		Date date1 = cal.getTime();
		assertTrue( date( date1 ).isWeekend() );

		cal.set( Calendar.DAY_OF_WEEK, Calendar.SATURDAY );
		assertTrue( date( cal ).isWeekend() );

		Date date2 = cal.getTime();
		assertTrue( date( date2 ).isWeekend() );

		cal.set( Calendar.DAY_OF_WEEK, Calendar.MONDAY );
		assertFalse( date( cal ).isWeekend() );

		Date date3 = cal.getTime();
		assertFalse( date( date3 ).isWeekend() );
	}

	@Test
	public void testMinutes() {
		DateBuilder dt = emptyDate();
		assertEquals( 0, dt.getMinute() );
		assertEquals( 0, dt.getHourOfDay() );

		dt.add( minutes( 150 ) );
		assertEquals( 30, dt.getMinute() );
		assertEquals( 2, dt.getHourOfDay() );

		dt.subtract( minutes( 31 ) );
		assertEquals( 59, dt.getMinute() );
		assertEquals( 1, dt.getHourOfDay() );
	}

	@Test
	public void testMonths() {
		DateBuilder dt = emptyDate();
		assertEquals( Calendar.JANUARY, dt.getMonth() );

		dt.add( months( 2 ) );
		assertEquals( Calendar.MARCH, dt.getMonth() );

		dt.subtract( months( 1 ) );
		assertEquals( Calendar.FEBRUARY, dt.getMonth() );
	}

	@Test
	public void testNow() {

		// test avec setEqual
		Date date1 = new Date();
		Date date2 = now().setAsEqual( date1 ).toDate();
		assertEquals( date1, date2 );

		// autre version
		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.MILLISECOND, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.HOUR_OF_DAY, 0 );

		Date date3 = cal.getTime();

		Date date4 = now().clearTime().toDate();

		assertEquals( date3, date4 );

	}

	@Test
	public void testSecondes() {
		DateBuilder dt = emptyDate();
		assertEquals( 0, dt.getSecond() );
		assertEquals( 0, dt.getMinute() );

		dt.add( secondes( 500 ) );
		assertEquals( 20, dt.getSecond() );
		assertEquals( 8, dt.getMinute() );

		dt.subtract( secondes( 21 ) );
		assertEquals( 59, dt.getSecond() );
		assertEquals( 7, dt.getMinute() );
	}

	@Test
	public void testSetEqualCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DATE, -1 );

		Date date2 = now().add( days( 2 ) ).setAsEqual( cal ).toDate();

		assertEquals( cal.getTime(), date2 );
		assertNotSame( cal.getTime(), date2 );

	}

	@Test
	public void testSetEqualDate() {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DATE, -1 );
		Date date1 = cal.getTime();

		Date date2 = now().add( days( 2 ) ).setAsEqual( date1 ).toDate();

		assertEquals( date1, date2 );
		assertNotSame( date1, date2 );

	}

	@Test
	public void testTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DATE, 1 );
		cal.set( Calendar.MILLISECOND, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.HOUR_OF_DAY, 0 );
		Date date1 = cal.getTime();
		Date date2 = tomorrow().clearTime().toDate();
		assertEquals( date1, date2 );

		// test avec setEqual
		Date date3 = new Date();
		Date date4 = tomorrow().setAsEqual( date3 ).toDate();
		assertEquals( date3, date4 );
	}

	@Test
	public void testAddWorkingDays() {
		DateBuilder dt = emptyDate();
		assertEquals( 1, dt.getDayOfMonth() );
		assertEquals( Calendar.THURSDAY, dt.getDayOfWeek() );

		dt.add( workingDays( 30 ) );
		assertEquals( 12, dt.getDayOfMonth() );
		assertEquals( Calendar.THURSDAY, dt.getDayOfWeek() );
		assertEquals( Calendar.FEBRUARY, dt.getMonth() );
	}

	@Test
	public void testSubtractWorkingDays() {
		DateBuilder dt = emptyDate();
		assertEquals( 1, dt.getDayOfMonth() );
		assertEquals( Calendar.JANUARY, dt.getMonth() );
		assertEquals( Calendar.THURSDAY, dt.getDayOfWeek() );

		dt.subtract( workingDays( 9 ) );
		assertEquals( 19, dt.getDayOfMonth() );
		assertEquals( Calendar.FRIDAY, dt.getDayOfWeek() );
		assertEquals( Calendar.DECEMBER, dt.getMonth() );
	}

	@Test
	public void testYears() {
		DateBuilder dt = emptyDate();
		assertEquals( 1970, dt.getYear() );

		dt.add( years( 2 ) );
		assertEquals( 1972, dt.getYear() );

		dt.subtract( years( 1 ) );
		assertEquals( 1971, dt.getYear() );
	}

	@Test
	public void testYesterday() {

		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DATE, -1 );
		cal.set( Calendar.MILLISECOND, 0 );
		cal.set( Calendar.SECOND, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.HOUR_OF_DAY, 0 );

		Date date1 = cal.getTime();

		Date date2 = yesterday().clearTime().toDate();

		assertEquals( date1, date2 );

		// test avec setEqual
		Date date3 = new Date();
		Date date4 = yesterday().setAsEqual( date3 ).toDate();
		assertEquals( date3, date4 );
	}

	@Test
	public void monthsBeetween() throws Exception {
		assertEquals( 9,
				range().startWith( date( 2014, 1, 10 ) )
						.endWith( date( 2014, 10, 14 ) ).getMonthsBetween() );
		assertEquals( 8,
				range().startWith( date( 2014, 1, 10 ) )
						.endWith( date( 2014, 10, 8 ) ).getMonthsBetween() );
	}

	@Test
	public void weeksBeetween() throws Exception {
		assertEquals( 2,
				range().startWith( date( 2014, 6, 4 ) )
						.endWith( date( 2014, 6, 19 ) ).getWeeksBetween() );
		assertEquals( 6,
				range().startWith( date( 2014, 6, 4 ) )
						.endWith( date( 2014, 7, 19 ) ).getWeeksBetween() );
	}
}
