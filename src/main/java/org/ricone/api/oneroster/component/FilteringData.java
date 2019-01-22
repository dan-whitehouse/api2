package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.error.exception.BadRequestException;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FilteringData {
	private Logger logger = LogManager.getLogger(FilteringData.class);
	private final String FILTER = "filter";
	private String filter = null;
	private List<Predicate> predicates;

	FilteringData(HttpServletRequest request) throws BadRequestException {
		if(StringUtils.isNotBlank(request.getParameter(FILTER))) {
			filter = request.getParameter(FILTER);
		}
	}

	public boolean hasFiltering() {
		return filter != null;
	}

	public Predicate getFiltering(CriteriaBuilder cb, Root from, Class<?> rootClass) {
		if(isAnd()) {
			String[] segments = StringUtils.split(filter, " AND ");
			for(String segment : segments) {

				Path path = from.get("");

				if(isEqual(segment)) {
					cb.equal(path, "");
				}
				else if(isNotEqual(segment)) {
					cb.notEqual(path, "");
				}
				else if(isGreaterThanOrEqual(segment)) {
					cb.greaterThanOrEqualTo(path, 0);
				}
				else if(isGreaterThan(segment)) {
					cb.greaterThan(path, 0);
				}
				else if(isLessThanOrEqual(segment)) {
					cb.lessThanOrEqualTo(path, 0);
				}
				else if(isLessThan(segment)) {
					cb.lessThan(path, 0);
				}
				else if(isContains(segment)) {
					//idk
				}
			}
		}
		else if(isOr()) {
			String[] segments = StringUtils.split(filter, " OR ");
		}



		//if filter contains AND or OR

		Predicate wrapper = null;
		if(isAnd()) {
			wrapper = cb.and(

			);
		}
		else if(isOr()) {
			wrapper = cb.and(

			);
		}
		else {

		}
		return wrapper;
	}

	private boolean isAnd(){
		return StringUtils.contains(filter, " AND ");
	}

	private boolean isOr(){
		return StringUtils.contains(filter, " OR ");
	}

	private boolean isEqual(String segment) {
		return StringUtils.contains(segment, "=");
	}

	private boolean isNotEqual(String segment) {
		return StringUtils.contains(segment, "!=");
	}

	private boolean isGreaterThan(String segment) {
		return StringUtils.contains(segment, ">");
	}

	private boolean isGreaterThanOrEqual(String segment) {
		return StringUtils.contains(segment, ">=");
	}

	private boolean isLessThan(String segment) {
		return StringUtils.contains(segment, "<");
	}

	private boolean isLessThanOrEqual(String segment) {
		return StringUtils.contains(segment, "<=");
	}

	private boolean isContains(String segment) {
		return StringUtils.contains(segment, "~");
	}
}
