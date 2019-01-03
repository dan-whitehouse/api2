package org.ricone.api.xpress.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
public class RequestUtil {
	private static final String idType = "IdType";

	public static boolean isIdTypeLocal(HttpServletRequest request) {
		return StringUtils.equalsIgnoreCase(request.getHeader(idType), "local");
	}

	public static boolean isIdTypeState(HttpServletRequest request) {
		return StringUtils.equalsIgnoreCase(request.getHeader(idType), "state");
	}

	public static boolean isIdTypeBEDS(HttpServletRequest request) {
		return StringUtils.equalsIgnoreCase(request.getHeader(idType), "beds");
	}
}