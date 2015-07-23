package com.contaazul.dsl;

import java.util.Calendar;

public enum WeekDays {
	SUNDAY(Calendar.SUNDAY), MONDAY(Calendar.MONDAY), TUESDAY(Calendar.TUESDAY), WEDNESDAY(Calendar.WEDNESDAY), THURSDAY(
			Calendar.THURSDAY), FRIDAY(Calendar.FRIDAY), SATURDAY(Calendar.SATURDAY);

	final int calendaWeekday;

	WeekDays(int calendaWeekDay) {
		this.calendaWeekday = calendaWeekDay;
	}
}
