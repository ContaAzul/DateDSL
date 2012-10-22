package com.contaazul.dsl;

class TimeUnit {

	final int type;

	final int size;

	boolean workingDay = false;

	public TimeUnit(int type, int size) {
		this.type = type;
		this.size = size;
	}

	public TimeUnit(int type, int size, boolean workingDay) {
		this.type = type;
		this.size = size;
		this.workingDay = true;
	}
}