package com.aric.esb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author Dursun KOC
 * 
 */
public class DateUtils {
	/**
	 * @param date
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	public static XMLGregorianCalendar dateToXMLGregorianCalendar(Date date)
			throws DatatypeConfigurationException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		GregorianCalendar gregCal = (GregorianCalendar) calendar;
		XMLGregorianCalendar retVal = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gregCal);
		return retVal;
	}

	/**
	 * @param dateString
	 * @param dateFormat
	 * @return
	 */
	public static Date parseDate(String dateString, String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date date;
			date = sdf.parse(dateString);
			return date;
		} catch (Exception e) {
			throw new RuntimeException("Cannot parse dateString:'" + dateString
					+ "' using dateFormat:'" + dateFormat + "'!", e);
		}
	}

	/**
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String formatDate(Date date, String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			String format = sdf.format(date);
			return format;
		} catch (Exception e) {
			throw new RuntimeException("Cannot format date:'" +date 
					+ "' using dateFormat:'" + dateFormat + "'!", e);
		}
	}
}
