package com.contaazul.dsl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeRange {

	// range().last(days(2).last(hours(3))
	// range().startWith(date).endWith(date).elapsedTime()
	// range().start(date).last(days(4))

	private Calendar startDate;

	private Calendar endDate;

	public TimeRange() {
		super();
	}

	public TimeRange(Calendar calendar, TimeUnit size) {
		super();
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate.getTime());

		this.endDate = Calendar.getInstance();
		this.endDate.setTime(startDate.getTime());
		this.endDate.add(size.type, size.size);

	}

	// public TimeRange(Date dateDebut, TimeUnit size) {
	// this.startDate = Calendar.getInstance();
	// this.startDate.setTime(dateDebut);
	//
	// this.endDate = Calendar.getInstance();
	// this.endDate.setTime(dateDebut);
	//
	// }
	//
	// public TimeRange(Calendar dateDebut, TimeUnit size) {
	// this.size = size;
	// }
	//
	// public TimeRange(Date dateDebut, Date dateFin) {
	// this.size = size;
	// }
	//
	// public TimeRange(Calendar dateDebut, Calendar dateFin) {
	// this.size = size;
	// }

	public boolean contains(final Calendar date) {
		return !date.before(startDate) && !date.after(endDate);
	}

	public boolean contains(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return contains(calendar);
	}

	public boolean contains(DateBuilder date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date.toDate());
		return contains(calendar);
	}

	/**
	 * All elaspsed types
	 * 
	 * @param g1
	 *            GregorianCalendar
	 * @param g2
	 *            GregorianCalendar
	 * @param type
	 *            int (Calendar.FIELD_NAME)
	 * 
	 * @return int number of elapsed "type"
	 */
	private int elapsed(int type) {
		GregorianCalendar gc1, gc2;
		int elapsed = 0;
		// Create copies since we will be clearing/adding
		if (endDate.after(startDate)) {
			gc2 = (GregorianCalendar) endDate.clone();
			gc1 = (GregorianCalendar) startDate.clone();
		} else {
			gc2 = (GregorianCalendar) startDate.clone();
			gc1 = (GregorianCalendar) endDate.clone();
		}
		if (type == Calendar.MONTH || type == Calendar.YEAR) {
			gc1.clear(Calendar.DATE);
			gc2.clear(Calendar.DATE);
		}
		if (type == Calendar.YEAR) {
			gc1.clear(Calendar.MONTH);
			gc2.clear(Calendar.MONTH);
		}
		while (gc1.before(gc2)) {
			gc1.add(type, 1);
			elapsed++;
		}
		return elapsed;
	}

	public TimeRange endWith(final Calendar endDate) {
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(endDate.getTime());
		return this;
	}

	public TimeRange endWith(final Date endDate) {
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(endDate);
		return this;
	}

	public TimeRange endWith(final DateBuilder endDate) {
		this.endDate = Calendar.getInstance();
		this.endDate.setTime(endDate.toDate());
		return this;
	}

	/**
	 * Elapsed days based on two Date objects
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * 
	 * @return int number of days
	 */
	public int getElapsedDays() {
		return elapsed(Calendar.DATE);
	}

	/**
	 * Elapsed months based on two Date objects
	 * 
	 * @param d1
	 *            Date
	 * @param d2
	 *            Date
	 * 
	 * @return int number of months
	 */
	public int getElapsedMonths() {
		return elapsed(Calendar.MONTH);
	}

	/**
	 * Elapsed years based on current time
	 * 
	 * @param date
	 *            Date
	 * @return int number of years
	 */
	public int getElapsedYears() {
		return elapsed(Calendar.YEAR);
	}

	/**
	 * Elapsed weeks based on current time
	 * 
	 * @param date
	 *            Date
	 * @return int number of weeks ROUNDED UP
	 */
	public int getElapsedWeeks() {
		return elapsed(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * Test si l'intersection entre les 2 p�riodes d�finies par les dates en
	 * param�tres, est vide ou pas.
	 * 
	 * @param d1
	 *            le d�but de la premi�re p�riode
	 * @param d2
	 *            la fin de la premi�re p�riode
	 * @param d3
	 *            le d�but da la seconde p�riode
	 * @param d4
	 *            la fin de la seconde p�riode
	 * @return <code>true</code> ssi les 2 p�riodes ne se chevauchent pas !
	 *         (intersection on vide).
	 */
	public boolean isDistinctFrom(TimeRange range) {

		return (range.endDate.before(startDate) && range.startDate
				.before(startDate))
				|| (range.endDate.after(endDate) && range.startDate
						.after(endDate));
	}

	public TimeRange startWith(final Calendar startDate) {
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate.getTime());
		return this;
	}

	public TimeRange startWith(final Date startDate) {
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate);
		return this;
	}

	// public DateBuilder day() {

	// }

	public TimeRange startWith(final DateBuilder startDate) {
		this.startDate = Calendar.getInstance();
		this.startDate.setTime(startDate.toDate());
		return this;
	}

	/**
	 * Calcule la diff�rence en nombre de jours, mois, ann�es entre 2 dates
	 * 
	 * @param dateDebut
	 *            La date de d�but
	 * @param dateFin
	 *            La date de fin
	 * @param uniteMesure
	 *            L'unit� de mesure pour le calcul ; 'J' pour jour, 'M' pour
	 *            mois, 'A' pour ann�e
	 * @return Le nombre d'unit�s de diff�rence calcul�.
	 */
	// public int getDifference(Date dateDebut, Date dateFin, char
	// uniteMesure) {
	//
	// int nbUnites = 0;
	//
	// if (dateDebut != null && dateFin != null) {
	// GregorianCalendar gcDebut = new
	// GregorianCalendar(dateDebut.getYear(),dateDebut.getMonth(),dateDebut.getDate());
	// GregorianCalendar gcFin = new
	// GregorianCalendar(dateFin.getYear(),dateFin.getMonth(),dateFin.getDate());
	//
	// while ( gcDebut.before(gcFin) ) {
	// // Diff�rentiel en nb de jours
	// if (uniteMesure == 'J') {
	// gcDebut.add(Calendar.DAY_OF_YEAR, 1);
	// }
	// // Diff�rentiel en nb de mois
	// else if (uniteMesure == 'M') {
	// gcDebut.add(Calendar.MONTH, 1);
	// }
	// // Diff�rentiel en nb d'ann�es
	// else if (uniteMesure == 'A') {
	// gcDebut.add(Calendar.YEAR, 1);
	// }
	// nbUnites++;
	// }
	// }
	//
	// return nbUnites;
	//
	// }

}