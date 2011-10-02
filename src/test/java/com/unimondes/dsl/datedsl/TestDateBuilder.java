package com.unimondes.dsl.datedsl;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Calendar;

import static com.unimondes.dsl.datedsl.DateDsl.date;

public class TestDateBuilder {
	
	@Test
	public void testWithMillisecond() {
		Calendar date = date().withMillisecond(1200).toCalendar();
		Assert.assertEquals(1200, date.get(Calendar.MILLISECOND));
	}
	
	@Test
	public void testWithSecond() {
		Calendar date = date().withSecond(36).toCalendar();
		Assert.assertEquals(36, date.get(Calendar.SECOND));
	}
	
	@Test
	public void testWithMinute() {
		Calendar date = date().withMinute(7).toCalendar();
		Assert.assertEquals(7, date.get(Calendar.MINUTE));
	}
	
	@Test
	public void testWithHour() {
		Calendar date = date().withHour(23).toCalendar();
		Assert.assertEquals(23, date.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testWithDayOfWeek() {
		Calendar date = date().withDayOfWeek(4).toCalendar();
		Assert.assertEquals(4, date.get(Calendar.DAY_OF_WEEK));
	}
	
	@Test
	public void testWithDayOfMonth() {
		Calendar date = date().withDayOfMonth(9).toCalendar();
		Assert.assertEquals(9, date.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testWithMonth() {
		Calendar date = date().withMonth(11).toCalendar();
		Assert.assertEquals(11, date.get(Calendar.MONTH));
	}
	
	@Test
	public void testWithYear() {
		Calendar date = date().withYear(2020).toCalendar();
		Assert.assertEquals(2020, date.get(Calendar.YEAR));
	}
	
	
	@Test
	public void testFirstDayOfMonth() {
		Calendar date = date().withMonth(Calendar.NOVEMBER).firstDayOfMonth().toCalendar();
		Assert.assertEquals(1, date.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testLastDayOfMonth() {
		Calendar date = date().withMonth(Calendar.NOVEMBER).lastDayOfMonth().toCalendar();
		Assert.assertEquals(30, date.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testFirstDayOfNextMonth() {
		Calendar date = date().withMonth(Calendar.NOVEMBER).firstDayOfNextMonth().toCalendar();
		Assert.assertEquals(1, date.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testLastDayOfNextMonth() {
		Calendar date = date().withMonth(Calendar.NOVEMBER).lastDayOfNextMonth().toCalendar();
		Assert.assertEquals(31, date.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testToString() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testToDate() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testToCalendar() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testFormat() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testMondayIsWeekend() {
		Assert.assertFalse(date().withDayOfWeek(Calendar.MONDAY).isWeekend());
	}
	
	@Test
	public void testTuesdayIsWeekend() {
		Assert.assertFalse(date().withDayOfWeek(Calendar.TUESDAY).isWeekend());
	}
	
	@Test
	public void testWednesdayIsWeekend() {
		Assert.assertFalse(date().withDayOfWeek(Calendar.WEDNESDAY).isWeekend());
	}
	
	@Test
	public void testThursdayIsWeekend() {
		Assert.assertFalse(date().withDayOfWeek(Calendar.THURSDAY).isWeekend());
	}
	
	@Test
	public void testFridayIsWeekend() {
		Assert.assertFalse(date().withDayOfWeek(Calendar.FRIDAY).isWeekend());
	}
	
	@Test
	public void testSaturdayIsWeekend() {
		Assert.assertTrue(date().withDayOfWeek(Calendar.SATURDAY).isWeekend());
	}
	
	@Test
	public void testSundayIsWeekend() {
		Assert.assertTrue(date().withDayOfWeek(Calendar.SUNDAY).isWeekend());
	}
	
	
	@Test
	public void testTodayIsTodayDate() {
		Calendar today = Calendar.getInstance();
		Assert.assertTrue(date(today).isToday());
	}
	
	@Test
	public void testTodayIsNotPastDate() {
		Calendar today = Calendar.getInstance();
		Assert.assertFalse(date(today).isPastDate());
	}
	
	@Test
	public void testTomorowIsNotPastDate() {
		Calendar tomorow = Calendar.getInstance();
		tomorow.add(Calendar.DAY_OF_MONTH, 1);
		Assert.assertFalse(date(tomorow).isPastDate());
	}

	@Test
	public void testYesterdayIsPastDate() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Assert.assertTrue(date(yesterday).isPastDate());
	}
	
	@Test
	public void testTodayIsNotFutureDate() {
		Calendar today = Calendar.getInstance();
		Assert.assertFalse(date(today).isFutureDate());
	}
	
	@Test
	public void testTomorowIsFutureDate() {
		Calendar Tomorow = Calendar.getInstance();
		Tomorow.add(Calendar.DAY_OF_MONTH, 1);
		Assert.assertTrue(date(Tomorow).isFutureDate());
	}
	
	@Test
	public void testYesterdayIsNotFutureDate() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		Assert.assertFalse(date(yesterday).isFutureDate());
	}
	
	@Test
	public void testIsCurrentMonth() {
		Calendar now = Calendar.getInstance();
		Assert.assertTrue(date(now).isCurrentMonth());
	}
	
	@Test
	public void testAdd() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testSubtract() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testSetAsEqualCalendar() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testIsSameDayAsDate() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testIsSameDayAsCalendar() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testIsSameTimeAsDate() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testIsSameTimeAsCalendar() {
		Assert.fail("Not Implement");
	}

	@Test
	public void testIsSameAsDate() {
		Assert.fail("Not Implement");
	}

	@Test
	public void IsSameAsCalendar() {
		Assert.fail("Not Implement");
	}
	
	@Test
	public void testGetMillisecond() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MILLISECOND, 21);
		Assert.assertEquals(21, date(date).getMillisecond());
	}
	
	@Test
	public void testGetSecond() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.SECOND, 15);
		Assert.assertEquals(15, date(date).getSecond());
	}
	
	@Test
	public void testGetMinute() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MINUTE, 30);
		Assert.assertEquals(30, date(date).getMinute());
	}
	
	@Test
	public void testGetHour() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.HOUR, 3);
		Assert.assertEquals(3, date(date).getHour());
	}

	@Test
	public void testGetDayOfWeek() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_WEEK, 5);
		Assert.assertEquals(5, date(date).getDayOfWeek());
	}
	
	@Test
	public void testGetWeekMonth() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.WEEK_OF_MONTH, 7);
		Assert.assertEquals(7, date(date).getWeekMonth());
	}
	
	@Test
	public void testGetWeekYear() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.WEEK_OF_YEAR, 30);
		Assert.assertEquals(30, date(date).getWeekYear());
	}
	
	@Test
	public void testGetDayOfMonth() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 15);
		Assert.assertEquals(15, date(date).getDayOfMonth());
	}
	
	@Test
	public void testGetMonth() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, Calendar.NOVEMBER);
		Assert.assertEquals(Calendar.NOVEMBER, date(date).getMonth());
	}

	@Test
	public void testGetYear() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.YEAR, 2011);
		Assert.assertEquals(2011, date(date).getYear());
	}
	
}
