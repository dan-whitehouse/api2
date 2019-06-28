package org.ricone.api.xpress.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;


/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-04-13
 */

@SuppressWarnings("unused")
public class ControllerUtil {
	private static final String ID_TYPE = "IdType";
	private static final String GET_USERS = "getUsers";
	public static final String CHANGES_SINCE_MARKER = "changesSinceMarker";

	public static boolean isIdTypeLocal(HttpServletRequest request) {
		return StringUtils.equalsIgnoreCase(request.getHeader(ID_TYPE), "local");
	}

	public static boolean isIdTypeState(HttpServletRequest request) {
		return StringUtils.equalsIgnoreCase(request.getHeader(ID_TYPE), "state");
	}

	public static boolean isIdTypeBEDS(HttpServletRequest request) {
		return StringUtils.equalsIgnoreCase(request.getHeader(ID_TYPE), "beds");
	}

	private void test(List<Object> objects) {
		objects.removeIf(o -> {
			boolean isRemoveable = Objects.nonNull(o);
			if(isRemoveable) {
				//do something here
			}
			return isRemoveable;
		});


	}

}