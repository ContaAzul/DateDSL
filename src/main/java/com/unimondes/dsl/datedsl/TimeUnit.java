package com.unimondes.dsl.datedsl;

public class TimeUnit {

	private final int type;
	private final int size;
	private boolean workingDay = false;

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

	public boolean isWorkingDay() {
		return workingDay;
	}

}