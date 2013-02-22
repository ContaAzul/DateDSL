package com.contaazul.dsl;

public class TimeUnit {

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

	public int getType() {
		return type;
	}

	public int getSize() {
		return size;
	}

}