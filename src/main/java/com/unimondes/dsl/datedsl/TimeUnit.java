package com.unimondes.dsl.datedsl;

public class TimeUnit {

	private final int type;
	private final int size;
	private boolean workingDay = false;

    /**
     *
     * Create timeunit
     *
     * @param type int (Calendar.FIELD_NAME)
     * @param size size of unit
     *
     */
	public TimeUnit(int type, int size) {
		this.type = type;
		this.size = size;
	}

    /**
     *
     * Create timeunit
     *
     * @param type int (Calendar.FIELD_NAME)
     * @param size size of unit
     * @param workingDay workingday
     *
     */
	public TimeUnit(int type, int size, boolean workingDay) {
		this.type = type;
		this.size = size;
		this.workingDay = true;
	}

    /**
     *
     * Get type
     *
     * @return int (Calendar.FIELD_NAME)
     *
     */
	public int getType() {
		return type;
	}

    /**
     *
     * Get size
     *
     * @return int
     *
     */
	public int getSize() {
		return size;
	}

    /**
     *
     * Unit is workday?
     *
     */
	public boolean isWorkingDay() {
		return workingDay;
	}

}