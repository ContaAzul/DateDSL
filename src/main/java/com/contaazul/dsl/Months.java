package com.contaazul.dsl;

import java.util.Calendar;

public enum Months {
	JANUARY(Calendar.JANUARY), FEBRUARY(Calendar.FEBRUARY), MARCH(
			Calendar.MARCH), APRIL(Calendar.APRIL), MAY(Calendar.MAY), JUNE(
			Calendar.JUNE), JULY(Calendar.JULY), AUGUST(Calendar.AUGUST), SEPTEMBER(
			Calendar.SEPTEMBER), OCTOBER(Calendar.OCTOBER), NOVEMBER(
			Calendar.NOVEMBER), DECEMBER(Calendar.DECEMBER);

	final int calendaMonth;

	Months(int calendaMonth) {
		this.calendaMonth = calendaMonth;
	}
}