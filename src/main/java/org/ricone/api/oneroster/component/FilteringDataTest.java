package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.error.exception.InvalidDataException;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FilteringDataTest {
	private Logger logger = LogManager.getLogger(FilteringDataTest.class);
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

	FilteringDataTest(HttpServletRequest request) throws Exception {
		if(StringUtils.isNotBlank(request.getParameter(FILTER))) {
			filter = request.getParameter(FILTER);
			predicates = new ArrayList<>();
		}
	}

	public boolean isFiltered() {
		return StringUtils.isNotBlank(filter);
	}

	/*
		It MUST be possible to filter collections for elements matching a certain criteria.
		It MUST be possible to filter collections based on any data element in the core description of the resource.

		For version 1.1, it is RECOMMENDED that logical operations are limited to " AND " and " OR " (note the surrounding white space at each side)
		and that there is only one such operator used in any filter i.e. a single 'AND' or a single 'OR' in the filter. A single white space must
		occur before and after the parameter.
	 */
	Predicate getFiltering(CriteriaBuilder cb, BaseFilterer filterer) throws InvalidFilterFieldException, InvalidDataException {
		buildPredicates(cb, filterer);

		//if filter contains AND or OR, load in the predicates we created
		Predicate wrapper;

		logger.debug("predicates: " + predicates);

		if(CollectionUtils.isNotEmpty(predicates)) {
			if(isAnd()) {
				wrapper = cb.and(predicates.toArray(new Predicate[0]));
			}
			else if(isOr()) {
				wrapper = cb.or(predicates.toArray(new Predicate[0]));
			}
			else {
				wrapper = predicates.toArray(new Predicate[0])[0]; //Our list will only have 1 thing in it.
			}
		}
		else {
			//Because we add whatever is in this wrapper to the DAO, I have to return something so that DAO doesn't receive a null value.
			// This will add something like where querySpecificPredicates = someValues [and 1 = 1]
			wrapper = cb.equal(cb.literal(1), 1); //1=1
		}
		logger.debug("wrapper: " + wrapper);
		return wrapper;
	}

	private void buildPredicates(CriteriaBuilder cb, BaseFilterer filterer) throws InvalidFilterFieldException, InvalidDataException {
		if(isAnd()) {
			String[] segments = StringUtils.split(filter, LOGICAL_AND);
			for(String segment : segments) {
				buildPredicate(cb, filterer, segment);
			}
		}
		else if(isOr()) {
			String[] segments = StringUtils.split(filter, LOGICAL_OR);
			for(String segment : segments) {
				buildPredicate(cb, filterer, segment);
			}
		}
		else {
			buildPredicate(cb, filterer, filter); //build predicate directly from the filter string
		}
	}

	private void buildPredicate(CriteriaBuilder cb, BaseFilterer filterer, String segment) throws InvalidFilterFieldException, InvalidDataException {
		Path path;
		String value = StringUtils.substringBetween(segment, "'"); //All values must be wrapped in single quotes

		if(StringUtils.isBlank(value)) {
			throw new InvalidFilterFieldException("The filter parameter value is missing single quotes, or is blank");
		}

		if(isEqual(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_EQ));
			if(StringUtils.contains(value, ",")) {
				String[] valueSegments = StringUtils.split(value, ",");
				List<Predicate> p = new ArrayList<>();
				for(String valueSegment : valueSegments) {
					p.add(cb.equal(path, valueSegment));
				}
				predicates.add(cb.and(p.toArray(new Predicate[0])));
			}
			else {
				if(path.getJavaType().equals(Boolean.class)) {
					predicates.add(cb.equal(path, BooleanUtils.toBoolean(value)));
				}
				else {
					predicates.add(cb.equal(path, value));
				}
			}
		}
		else if(isNotEqual(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_NEQ));
			if(path.getJavaType().equals(Boolean.class)) {
				predicates.add(cb.notEqual(path, BooleanUtils.toBoolean(value)));
			}
			else {
				predicates.add(cb.notEqual(path, value));
			}
		}
		else if(isGreaterThanOrEqual(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_GTE));
			if(path != null) {
				predicates.add(cb.greaterThanOrEqualTo(path, value));
			}
		}
		else if(isGreaterThan(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_GT));
			predicates.add(cb.greaterThan(path, value));
		}
		else if(isLessThanOrEqual(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_LTE));
			if(path != null) {
				predicates.add(cb.lessThanOrEqualTo(path, value));
			}
		}
		else if(isLessThan(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_LT));
			if(path != null) {
				predicates.add(cb.lessThan(path, value));
			}
		}
		else if(isContains(segment)) {
			path = filterer.getPath(StringUtils.substringBefore(segment, PREDICATE_CON));
			if(StringUtils.contains(value, ",")) {
				String[] valueSegments = StringUtils.split(value, ",");
				List<Predicate> p = new ArrayList<>();
				for (String valueSegment : valueSegments) {
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
