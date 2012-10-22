/**
 * 
 */
package com.contaazul.dsl;

import static java.util.Calendar.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Simple Fluent interface for Date api.
 * use with <code>import static com.unimondes.dsl.DateDsl.*</code> 
 * @author <a href="mailto:jgaucher@sedona.fr">Julien Gaucher</a>
 * @version 0.1
 */
public class DateDsl {
	
	public static final String FORMAT_ = "";

    public enum Months{
        JANUARY(Calendar.JANUARY), FEBRUARY(Calendar.FEBRUARY), MARCH(
                        Calendar.MARCH), APRIL(Calendar.APRIL), MAY(Calendar.MAY), JUNE(
                        Calendar.JUNE), JULY(Calendar.JULY), AUGUST(Calendar.AUGUST), SEPTEMBER(
                        Calendar.SEPTEMBER), OCTOBER(Calendar.OCTOBER), NOVEMBER(
                        Calendar.NOVEMBER), DECEMBER(Calendar.DECEMBER);

        private final int calendaMonth;

        Months(int calendaMonth) {
                this.calendaMonth = calendaMonth;
        }
    }
    
    public enum WeekDays{
    	SUNDAY(Calendar.SUNDAY), MONDAY(Calendar.MONDAY), THURSDAY(
    			Calendar.THURSDAY), WEDNESDAY(Calendar.WEDNESDAY), TUESDAY(Calendar.TUESDAY), FRIDAY(
    					Calendar.FRIDAY), SATURDAY(Calendar.SATURDAY);
    	
    	private final int calendaWeekday;
    	
    	WeekDays(int calendaWeekDay) {
    		this.calendaWeekday = calendaWeekDay;
    	}
    }

	public static TimeRange range() {
		return new TimeRange();
	}
	
	public static TimeRange range(Date date, TimeUnit size) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return new TimeRange(cal, size);
	}
	
	public static TimeRange range(Calendar date, TimeUnit size) {
		return new TimeRange(date, size);
	}
	
	public static TimeRange range(DateBuilder date, TimeUnit size) {
		return new TimeRange(date.toCalendar(), size);
	}
	
	/**
	 * Creation d'une date au 1er janvier 1970 00h00m00s00ms, qui est la date
	 * du debut de l'univers comme tout unixien le sait.
	 * @return DateBuilder
	 */
	public static DateBuilder emptyDate() {

		Calendar cal = Calendar.getInstance();
		cal.clear();
		return new DateBuilder(cal.getTime());
	}

	/**
	 * Creation du builder (par defaut a la date du jour)
	 * placer les valeurs a l'aide des methodes withXXXX();
	 * exemple : date().withYear(2008").withMonth(Calendar.JANUARY).toDate();
	 * @return DateBuilder
	 */
	public static DateBuilder date() {
		return new DateBuilder();
	}

	/**
	 * Creation du builder a l'aide d'une date pr�cise.
	 * @param date Date 
	 * @return DateBuilder
	 */
	public static DateBuilder date(Date date) {
		return new DateBuilder(date);
	}

	/**
	 * Creation du builder avec une date pr�cise repr�sent�e par un 
	 * Calendar.
	 * @param calendar Calendar
	 * @return DateBuilder
	 */
	public static DateBuilder date(Calendar calendar) {
		return new DateBuilder(calendar);
	}

	public static DateBuilder date(
			int year,
			Months monthOfYear,
			int dayOfMonth,
			int hourOfDay,
			int minuteOfHour,
			int secondOfMinute,
			int millisOfSecond) {
		DateBuilder db = new DateBuilder();
		db.withYear(year);
		db.withMonth(monthOfYear);
		db.withDayOfMonth(dayOfMonth);
		db.withHourOfDay(hourOfDay);
		db.withMinute(minuteOfHour);
		db.withSecond(secondOfMinute);
		db.withMillisecond(millisOfSecond);
		
		return db;
	}
	
    public static DateBuilder date(
            int year,
            int monthOfYear,
            int dayOfMonth,
            int hourOfDay,
            int minuteOfHour,
            int secondOfMinute,
            int millisOfSecond) {
    			DateBuilder db = new DateBuilder();
    			db.withYear(year);
    			db.withMonth(monthOfYear);
    			db.withDayOfMonth(dayOfMonth);
    			db.withHourOfDay(hourOfDay);
    			db.withMinute(minuteOfHour);
    			db.withSecond(secondOfMinute);
    			db.withMillisecond(millisOfSecond);

               return db;
    }
    
	/**
	 * Cr�ation du builder initialis� a la date de cr�ation.
	 * @return DateBuilder
	 */
	public static DateBuilder now() {
		return new DateBuilder();
	}

	/**
	 * Cr�ation du builder initialis� a la date du lendemain (hh/mm/ss = celle de creation).
	 * @return DateBuilder
	 */
	public static DateBuilder tomorrow() {
		DateBuilder date = new DateBuilder(new Date());
		return date.add(days(1));
	}

	/**
	 * Cr�ation du builder initialis� a la date de la veille (hh/mm/ss = celle de creation).
	 * @return DateBuilder
	 */
	public static DateBuilder yesterday() {
		DateBuilder date = new DateBuilder(new Date());
		return date.subtract(days(1));
	}

	public static TimeUnit months(int n) {
		return new TimeUnit(MONTH, n);
	}
	
	public static TimeUnit months(Months n) {
		return new TimeUnit(MONTH, n.calendaMonth);
	}

	public static TimeUnit years(int n) {
		return new TimeUnit(YEAR, n);
	}

	public static TimeUnit days(int n) {
		return new TimeUnit(DAY_OF_MONTH, n);
	}

	public static TimeUnit workingDays(int n) {
		return new TimeUnit(DAY_OF_MONTH, n, true);
	}

	public static TimeUnit hours(int n) {
		return new TimeUnit(HOUR, n);
	}

	public static TimeUnit minutes(int n) {
		return new TimeUnit(MINUTE, n);
	}

	public static TimeUnit secondes(int n) {
		return new TimeUnit(SECOND, n);
	}

	public static TimeUnit milliSecondes(int n) {
		return new TimeUnit(MILLISECOND, n);
	}


	public static final class DateBuilder {

		private final Calendar date;

		public DateBuilder() {
			this.date = Calendar.getInstance();
		}

		public DateBuilder(Calendar calendar) {
			this.date = Calendar.getInstance();
			this.date.setTime(calendar.getTime());
		}

		public DateBuilder(Date date) {
			this.date = Calendar.getInstance();
			this.date.setTime(date);
		}

		public Date toDate() {
			return date.getTime ();
		}
		
		public String format(String dateFormat) {
			return new SimpleDateFormat(dateFormat).format(this.date.getTime());
		}
		
		public String toShortStringDate() {
			return null;
		}

		public String toStringDate() {
			return null;
		}

		public String toLongStringDate() {
			return null;
		}

		
		public Calendar toCalendar() {
			return date;
		}

		/**
		 * Ajout de TimeUnit a une date.
		 * @param unit TimeUnit
		 * @return DateBuilder
		 */
		public DateBuilder add(TimeUnit unit) {

			if (unit.workingDay) {
				int nbDay = unit.size;
				int i = 0;
				while (i != nbDay) {

					date.add(unit.type, 1);

					if (!isWeekend()) {
						i++;
					}
				}	

			} else {
				date.add(unit.type, unit.size);
			}
			return this;
		}

		/**
		 * Soustraction de timeUnit a une date.
		 * @param unit TimeUnit
		 * @return DateBuilder
		 */
		public DateBuilder subtract(TimeUnit unit) {
			date.add(unit.type, -unit.size);
			return this;
		}

		/**
		 * Mise a 0 de la partie heure/minutes/secondes/millisecondes
		 * de la date.
		 * @return DateBuilder
		 */
		public DateBuilder clearTime() {
			date.set(HOUR_OF_DAY, 0);
			date.clear(MINUTE);
			date.clear (SECOND);
			date.clear(MILLISECOND);
			return this;

		}

		/**
		 * Place la date au premier jour du mois.
		 * @return DateBuilder
		 */
		public DateBuilder firstDayOfMonth() {
			date.set(DAY_OF_MONTH, 1);
			return this;
		}

		/**
		 * Place la date au dernier jour du mois.
		 * @return DateBuilder
		 */
		public DateBuilder lastDayOfMonth() {
			date.set(DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
			return this;
		}

		/**
		 * Place la date au premier jour du mois suivant.
		 * @return DateBuilder
		 */
		public DateBuilder firstDayOfNextMonth() {            
			firstDayOfMonth().add(months(1));
			return this;
		}

		/**
		 * Place la date au dernier jour du mois suivant.
		 * @return DateBuilder
		 */
		public DateBuilder lastDayOfNextMonth() {
			add(months(1)).lastDayOfMonth();
			return this;
		}

		/**
		 * seter pour les millisecondes de la date.
		 * @param millisecond
		 * @return DateBuilder
		 */
		public DateBuilder withMillisecond(int millisecond) {
			date.set(MILLISECOND, millisecond);
			return this;
		}

		/**
		 * seter pour les secondes de la date.
		 * @param second
		 * @return DateBuilder
		 */
		public DateBuilder withSecond(int second) {
			date.set(SECOND, second);
			return this;
		}

		/**
		 * seter pour les minutes de la date.
		 * @param minutes
		 * @return DateBuilder
		 */
		public DateBuilder withMinute(int minute) {
			date.set(MINUTE, minute);
			return this;
		}

		/**
		 * seter pour les heures de la date.
		 * @param heurs
		 * @return DateBuilder
		 */
		public DateBuilder withHour(int hour) {
			date.set(HOUR, hour);
			return this;
		}

		public DateBuilder withHourOfDay(int hour) {
			date.set(HOUR_OF_DAY, hour);
			return this;
		}

		/**
		 * seter pour le jours de la semaine 
		 * de la date (LUNDI/MARDI etc.).
		 * @param dayOfWeek
		 * @return DateBuilder
		 */
		public DateBuilder withDayOfWeek(int day) {
			date.set(DAY_OF_WEEK, day);
			return this;
		}
		
		public DateBuilder withDayOfWeek(WeekDays day) {
			date.set(DAY_OF_WEEK, day.calendaWeekday);
			return this;
		}

		/**
		 * seter pour le jours du mois de la date
		 * (1..31)
		 * @param dayOfMonth
		 * @return DateBuilder
		 */
		public DateBuilder withDayOfMonth(int day) {
			date.set(DAY_OF_MONTH, day);
			return this;
		}

		/**
		 * seter pour le mois de la date.
		 * @param month
		 * @return DateBuilder
		 */
		public DateBuilder withMonth(int month) {
			date.set(MONTH, month);
			return this;
		}
		
		public DateBuilder withMonth(Months month) {
			date.set(MONTH, month.calendaMonth);
			return this;
		}

		/**
		 * seter pour l'ann�e de la date.
		 * @param year
		 * @return DateBuilder
		 */
		public DateBuilder withYear(int year) {
			date.set(YEAR, year);
			return this;
		}

		public String toString() {
			return date.getTime().toString();
		}

		public DateBuilder setAsEqual(Date dateExt) {
			dateExt.setTime(date.getTime().getTime());
			return this;
		}

		public DateBuilder setAsEqual(Calendar calendar) {
			calendar.setTime(date.getTime());
			return this;
		}

		/*
		 * une liste de geter pour recuperer facilement des valeurs 
		 */
		public int getMillisecond() {
			return date.get(Calendar.MILLISECOND);
		}

		public int getSecond() {
			return date.get(Calendar.SECOND);
		}

		public int getMinute() {
			return date.get(Calendar.MINUTE);
		}

		public int getHour() {
			return date.get(Calendar.HOUR);
		}
		
		public int getHourOfDay() {
			return date.get(Calendar.HOUR_OF_DAY);
		}

		public int getDayOfWeek() {
			return date.get(Calendar.DAY_OF_WEEK);
		}

		public WeekDays getWeekDay() {
			return WeekDays.values()[date.get(Calendar.DAY_OF_WEEK)];
		}
		
		public int getWeekMonth() {
			return date.get(Calendar.WEEK_OF_MONTH);
		}

		public int getWeekYear() {
			return date.get(Calendar.WEEK_OF_YEAR);
		}

		public int getDayOfMonth() {
			return date.get(Calendar.DAY_OF_MONTH);
		}

		public int getMonth() {
			return date.get(Calendar.MONTH);
		}

		public Months getMonths() {
			return Months.values()[date.get(Calendar.MONTH)];
		}
		
		public int getYear() {
			return date.get(Calendar.YEAR);
		}


		/**
		 * methode utilitaire pour determiner si c'est un jour du week end.
		 * @return true si jour = samedi ou dimanche, false dans le cas contraire.
		 */
		public boolean isWeekend() {

			if ((date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
					|| (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				return true;
			} else {
				return false;
			}
		}


		/**
		 * Checks if calendar object is on the same day ignoring time.
		 * @param calendar Calendar
		 * @return true if it is the same day
		 */
		public boolean isSameDayAs(Calendar calendar) {
			return (date.get(Calendar.ERA) == calendar.get(Calendar.ERA) &&
					date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
					date.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR));
		}
		
		/**
		 * Checks if date object is on the same day ignoring time.
		 * @param date Date
		 * @return true if it is the same day
		 */
		public boolean isSameDayAs(Date date) {
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return isSameDayAs(calendar);
		}

		/**
		 * Checks if calendar object represent the same date and time.
		 * @param calendar Calendar
		 * @return true if they represent the same millisecond instant
		 */
		public boolean isSameAs(Calendar calendar) {
			return date.getTime().getTime() == calendar.getTime().getTime();
		}
		
		/**
		 * Checks if date object represent the same date and time.
		 * @param date Date
		 * @return true if they represent the same millisecond instant
		 */
		public boolean isSameAs(Date dateToCompare) {
			return date.getTime().getTime() == dateToCompare.getTime();

		}
		
		/**
		 * Checks if calendar object represent the same instant in time.
		 * @param calendar Calendar
		 * @return true if they represent the same millisecond instant
		 */
		public boolean isSameTimeAs(Calendar calendar) {
			return (date.get(Calendar.HOUR) == calendar.get(Calendar.HOUR) &&
					date.get(Calendar.MINUTE) == calendar.get(Calendar.MINUTE) &&
					date.get(Calendar.SECOND) == calendar.get(Calendar.SECOND) &&
					date.get(Calendar.MILLISECOND) == calendar.get(Calendar.MILLISECOND));
		}
		
		/**
		 * Checks if date object represent the same instant in time.
		 * @param date Date
		 * @return true if they represent the same millisecond instant
		 */
		public boolean isSameTimeAs(Date dateToCompare) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateToCompare);
			return isSameTimeAs(calendar);

		}
	}

	public static class TimeRange {

		// range().last(days(2).last(hours(3))
		//range().startWith(date).endWith(date).elapsedTime()
		//range().start(date).last(days(4))


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

//		public TimeRange(Date dateDebut, TimeUnit size) {
//			this.startDate = Calendar.getInstance();
//			this.startDate.setTime(dateDebut);
//			
//			this.endDate = Calendar.getInstance();
//			this.endDate.setTime(dateDebut);
//			
//		}
//		
//		public TimeRange(Calendar dateDebut, TimeUnit size) {
//			this.size = size;
//		}
//		
//		public TimeRange(Date dateDebut, Date dateFin) {
//			this.size = size;
//		}
//		
//		public TimeRange(Calendar dateDebut, Calendar dateFin) {
//			this.size = size;
//		}

		public TimeRange startWith(final Date startDate) {
			this.startDate = Calendar.getInstance();
			this.startDate.setTime(startDate);
			return this;
		}
		
		public TimeRange startWith(final Calendar startDate) {
			this.startDate = Calendar.getInstance();
			this.startDate.setTime(startDate.getTime());
			return this;
		}
		
		public TimeRange startWith(final DateBuilder startDate) {
			this.startDate = Calendar.getInstance();
			this.startDate.setTime(startDate.toDate());
			return this;
		}
		
		public TimeRange endWith(final Date endDate) {
			this.endDate = Calendar.getInstance();
			this.endDate.setTime(endDate);
			return this;
		}
		
		public TimeRange endWith(final Calendar endDate) {
			this.endDate = Calendar.getInstance();
			this.endDate.setTime(endDate.getTime());
			return this;
		}
		
		public TimeRange endWith(final DateBuilder endDate) {
			this.endDate = Calendar.getInstance();
			this.endDate.setTime(endDate.toDate());
			return this;
		}
		
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
		 * Elapsed days based on two Date objects
		 *
		 * @param d1 Date
		 * @param d2 Date
		 *
		 * @return int number of days
		 */
		public int getElapsedDays() {
			return elapsed(Calendar.DATE);
		}

		/**
		 * Elapsed months based on two Date objects
		 *
		 * @param d1 Date
		 * @param d2 Date
		 *
		 * @return int number of months
		 */
		public int getElapsedMonths() {
			return elapsed(Calendar.MONTH);
		}
		
		/**
		 * Elapsed years based on current time
		 *
		 * @param date Date
		 * @return int number of years
		 */
		public int getElapsedYears() {
			return elapsed(Calendar.YEAR);
		}
		    

		 /**
	     * All elaspsed types
	     *
	     * @param g1 GregorianCalendar
	     * @param g2 GregorianCalendar
	     * @param type int (Calendar.FIELD_NAME)
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
		
//		public DateBuilder day() {

//		}

		/**
		 * Test si l'intersection entre les 2 p�riodes d�finies par les
		dates en param�tres,
		 * est vide ou pas.
		 * @param d1 le d�but de la premi�re p�riode
		 * @param d2 la fin de la premi�re p�riode
		 * @param d3 le d�but da la seconde p�riode
		 * @param d4 la fin de la seconde p�riode
		 * @return <code>true</code> ssi les 2 p�riodes ne se chevauchent
		pas ! (intersection on vide).
		 */
		public boolean isDistinctFrom(TimeRange range)  {
			
			return (range.endDate.before(startDate) && range.startDate.before(startDate)) 
				|| (range.endDate.after(endDate) && range.startDate.after(endDate));
		}

		/**
		 * Calcule la diff�rence en nombre de jours, mois, ann�es entre 2 dates
		 * @param dateDebut La date de d�but
		 * @param dateFin La date de fin
		 * @param uniteMesure L'unit� de mesure pour le calcul ; 'J' pour jour, 'M' pour mois, 'A' pour ann�e
		 * @return Le nombre d'unit�s de diff�rence calcul�.
		 */
//		public  int getDifference(Date dateDebut, Date dateFin, char
//				uniteMesure) {
//
//			int nbUnites = 0;
//
//			if (dateDebut != null && dateFin != null) {
//				GregorianCalendar gcDebut = new
//				GregorianCalendar(dateDebut.getYear(),dateDebut.getMonth(),dateDebut.getDate());
//				GregorianCalendar gcFin = new
//				GregorianCalendar(dateFin.getYear(),dateFin.getMonth(),dateFin.getDate());
//
//				while ( gcDebut.before(gcFin) ) {
//					// Diff�rentiel en nb de jours
//					if (uniteMesure == 'J') {
//						gcDebut.add(Calendar.DAY_OF_YEAR, 1);
//					}
//					// Diff�rentiel en nb de mois
//					else if (uniteMesure == 'M') {
//						gcDebut.add(Calendar.MONTH, 1);
//					}
//					// Diff�rentiel en nb d'ann�es
//					else if (uniteMesure == 'A') {
//						gcDebut.add(Calendar.YEAR, 1);
//					}
//					nbUnites++;
//				}
//			}
//
//			return nbUnites;
//
//		}

	}

	public static class TimeUnit {

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
	}
}
