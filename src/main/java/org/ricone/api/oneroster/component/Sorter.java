package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.component.error.exception.InvalidSortingException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Sorter {
	private Logger logger = LogManager.getLogger(Sorter.class);
	private final String SORT = "sort";
	private final String ORDER_BY = "orderBy";

	private String sort = null;
	private String orderBy = null;

	Sorter(HttpServletRequest request) throws Exception {
		if(StringUtils.isNotBlank(request.getParameter(SORT))) {
			sort = request.getParameter(SORT);
		}

		if(StringUtils.isNotBlank(request.getParameter(ORDER_BY))) {
			orderBy = request.getParameter(ORDER_BY);
		}

		/*
			If we provide a sort value, and the orderBy is anything
			other then asc or desc then we need to throw and error.
		*/
		if(isSorted() && request.getParameter(ORDER_BY) != null) {
			if(!isAscending() && !isDescending()) {
				throw new InvalidSortingException("Invalid sort order: " +  orderBy);
			}
		}
	}

	public boolean isSorted() {
		return sort != null;
	}

	private boolean isOrdered() {
		if(orderBy != null) {
			return isAscending() || isDescending();
		}
		return false;
	}

	private boolean isAscending() {
		return orderBy.equalsIgnoreCase("asc");
	}

	private boolean isDescending() {
		return orderBy.equalsIgnoreCase("desc");
	}

	public Order getSortOrder(CriteriaBuilder cb, Root from) {
		if(isSorted() && isValidField(from.getJavaType())) {
			Path sortBy = from.get(sort);

			if(isOrdered()) {
				if(isAscending()) {
					return cb.asc(sortBy);
				}
				else {
					return cb.desc(sortBy);
				}
			}
			return cb.asc(sortBy);
		}
		else {
			return cb.asc(from.get("sourcedId"));
		}
	}

	boolean isValidField(Class<?> clazz) {
		//While testing infinite campus, fields were case-sensitive
		final List<String> actualFieldNames = new ArrayList<>();
		final Field[] baseFields = clazz.getSuperclass().getDeclaredFields(); //Base: sourcedId, status, metadata, dateLastModified
		final Field[] fields = clazz.getDeclaredFields(); //?: Whatever class is passed in, this will always extend Base.
		actualFieldNames.addAll(getFieldNames(baseFields));
		actualFieldNames.addAll(getFieldNames(fields));
		return actualFieldNames.contains(sort);
	}

	private static List<String> getFieldNames(Field[] fields) {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields)
			fieldNames.add(field.getName());
		return fieldNames;
	}

	public String getSort() {
		return sort;
	}

	public String getOrderBy() {
		return orderBy;
	}
}
