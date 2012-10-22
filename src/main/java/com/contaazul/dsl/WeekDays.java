package com.contaazul.dsl;

import java.util.Calendar;

public enum WeekDays{
	SUNDAY(Calendar.SUNDAY), MONDAY(Calendar.MONDAY), THURSDAY(
			Calendar.THURSDAY), WEDNESDAY(Calendar.WEDNESDAY), TUESDAY(Calendar.TUESDAY), FRIDAY(
					Calendar.FRIDAY), SATURDAY(Calendar.SATURDAY);
	
	final int calendaWeekday;
	
	WeekDays(int calendaWeekDay) {
		this.calendaWeekday = calendaWeekDay;
	}
}