package org.ricone.util;

import org.apache.commons.lang3.StringUtils;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-04-13
 */

@SuppressWarnings("unused")
public class Util {
	public static boolean isRefId(String refId) {
		if (refId.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) return true;
		else return refId.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
	}

	public static boolean isValidSchoolYear(String year) {
		return year.matches("[0-9]+") && year.length() == 4;
	}

	static String get2DigitYear(Date d) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d).substring(2, 4);
	}

	static boolean isGradeGreater(String grade, String grade2) {
		return getGradeAsInt(grade) > getGradeAsInt(grade2);
	}

	public static int getCurrentSchoolYear() {
		LocalDate now = LocalDate.now();
		if(now.getMonth().getValue() >= 7) {
			return now.plusYears(1).getYear();
		}
		return now.getYear();
	}

	private static int getGradeAsInt(String grade) {
		if (grade.equalsIgnoreCase("IT")) {
			return -4; // Infant/Toddler
		}
		else if (grade.equalsIgnoreCase("PR")) {
			return -3; // Preschool
		}
		else if (grade.equalsIgnoreCase("PK")) {
			return -2;
		}
		else if (grade.equalsIgnoreCase("TK")) {
			return -1; // Transitional Kindergarten
		}
		else if (grade.equalsIgnoreCase("KG")) {
			return 0;
		}
		else if (grade.equalsIgnoreCase("PS")) {
			return 14; // Postsecondary
		}
		else if (grade.equalsIgnoreCase("UG")) {
			return 15; // Ungraded
		}
		else if (isInteger(grade)) {
			return Integer.parseInt(grade);
		}
		else {
			return 16;
		}
	}

	private static boolean isInteger(String s) {
		return isInteger(s, 10);
	}

	@SuppressWarnings("SameParameterValue")
	private static boolean isInteger(String s, int radix) {
		if (s.isEmpty()) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			if (i == 0 && s.charAt(i) == '-') {
				if (s.length() == 1) {
					return false;
				}
				else {
					continue;
				}
			}

			if (Character.digit(s.charAt(i), radix) < 0) {
				return false;
			}
		}
		return true;
	}

	public static XMLGregorianCalendar getDateValue(String value) {
		try {
			if(StringUtils.isNotBlank(value)) {
				XMLGregorianCalendar result = null;
				return DatatypeFactory.newInstance().newXMLGregorianCalendar(value);
			}
		}
		catch (Exception ignored) {
		}
		return null;
	}

	public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) {
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		}
		catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String TimestampToISO8601(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		return date.format(formatter);
	}

	public static long ISO8601toLong(String iso) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime ldt = LocalDateTime.parse(iso, formatter);
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()).getTime();
	}
}