package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.error.exception.BadRequestException;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FilteringData {
	private Logger logger = LogManager.getLogger(FilteringData.class);
	private final String FILTER = "filter";
	private final String LOGICAL_AND = " AND ";
	private final String LOGICAL_OR = " OR ";
	private final String PREDICATE_EQ = "=";
	private final String PREDICATE_NEQ = "!=";
	private final String PREDICATE_GTE = ">=";
	private final String PREDICATE_GT = ">";
	private final String PREDICATE_LTE = "<=";
	private final String PREDICATE_LT = "<";
	private final String PREDICATE_CON = "~";

	private String filter = null;
	private List<Predicate> predicates;

	FilteringData(HttpServletRequest request) throws BadRequestException {
		if(StringUtils.isNotBlank(request.getParameter(FILTER))) {
			filter = request.getParameter(FILTER);
			predicates = new ArrayList<>();
		}
	}

	public boolean hasFiltering() {
		return StringUtils.isNotBlank(filter);
	}

	public Predicate getFiltering(CriteriaBuilder cb, Root from) {
		buildPredicates(cb, from);

		//if filter contains AND or OR, load in the predicates we created
		Predicate wrapper = null;
		if(isAnd()) {
			wrapper = cb.and(predicates.toArray(new Predicate[0]));
		}
		else if(isOr()) {
			wrapper = cb.or(predicates.toArray(new Predicate[0]));
		}
		else {
			wrapper = predicates.toArray(new Predicate[0])[0]; //Our list will only have 1 thing in it.
		}
		return wrapper;
	}

	private void buildPredicates(CriteriaBuilder cb, Root from) {
		if(isAnd()) {
			String[] segments = StringUtils.split(filter, LOGICAL_AND);
			for(String segment : segments) {
				buildPredicate(cb, from, segment);
			}
		}
		else if(isOr()) {
			String[] segments = StringUtils.split(filter, LOGICAL_OR);
			for(String segment : segments) {
				buildPredicate(cb, from, segment);
			}
		}
		else {
			buildPredicate(cb, from, filter); //build predicate directly from the filter string
		}
	}

	private void buildPredicate(CriteriaBuilder cb, Root from, String segment) {
		Path path; //TODO - At the moment, I only select from the From table. Do something to determine the table based on the field.
		String value = StringUtils.substringBetween(segment, "'"); //All values must be wrapped in single quotes
		if(isEqual(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_EQ));
			if(StringUtils.contains(value, ",")) {
				String[] valueSegments = StringUtils.split(value, ",");
				List<Predicate> p = new ArrayList<>();
				for(String valueSegment : valueSegments) {
					p.add(cb.equal(path, valueSegment));
				}
				Predicate and = cb.and(p.toArray(new Predicate[0]));
				predicates.add(and);
			}
			else {
				predicates.add(cb.equal(path, value));
			}
		}
		else if(isNotEqual(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_NEQ));
			predicates.add(cb.notEqual(path, value));
		}
		else if(isGreaterThanOrEqual(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_GTE));
			predicates.add(cb.greaterThanOrEqualTo(path, value));
		}
		else if(isGreaterThan(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_GT));
			predicates.add(cb.greaterThan(path, value));
		}
		else if(isLessThanOrEqual(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_LTE));
			predicates.add(cb.lessThanOrEqualTo(path, value));
		}
		else if(isLessThan(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_LT));
			predicates.add(cb.lessThan(path, value));
		}
		else if(isContains(segment)) {
			path = from.get(StringUtils.substringBefore(segment, PREDICATE_CON));
			if(StringUtils.contains(value, ",")) {
				String[] valueSegments = StringUtils.split(value, ",");
				List<Predicate> p = new ArrayList<>();
				for(String valueSegment : valueSegments) {
					p.add(cb.like(path, valueSegment));
				}
				Predicate and = cb.or(p.toArray(new Predicate[0]));
				predicates.add(and);
			}
			else {
				predicates.add(cb.like(path, "%" + value + "%"));
			}
		}
	}

	// Note for tomorrow: First determine what the logical operator is, then the predicate.
	// Value will always be wrapped in single quotes.
	// Field will always be to the left of the predicate operator

	// LOGICAL OPERATOR
	private boolean isAnd(){
		return StringUtils.contains(filter, LOGICAL_AND);
	}

	private boolean isOr(){
		return StringUtils.contains(filter, LOGICAL_OR);
	}

	//PREDICATE OPERATOR
	private boolean isEqual(String segment) {
		return StringUtils.contains(segment, PREDICATE_EQ);
	}

	private boolean isNotEqual(String segment) {
		return StringUtils.contains(segment, PREDICATE_NEQ);
	}

	private boolean isGreaterThan(String segment) {
		return StringUtils.contains(segment, PREDICATE_GT);
	}

	private boolean isGreaterThanOrEqual(String segment) {
		return StringUtils.contains(segment, PREDICATE_GTE);
	}

	private boolean isLessThan(String segment) {
		return StringUtils.contains(segment, PREDICATE_LT);
	}

	private boolean isLessThanOrEqual(String segment) {
		return StringUtils.contains(segment, PREDICATE_LTE);
	}

	private boolean isContains(String segment) {
		return StringUtils.contains(segment, PREDICATE_CON);
	}
}
