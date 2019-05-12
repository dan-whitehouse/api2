package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.component.error.exception.InvalidDataException;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
class Filterer {
	private static Logger logger = LogManager.getLogger(Filterer.class);
	private static final String FILTER = "filter";
	private static final String LOGICAL_AND = " AND ";
	private static final String LOGICAL_OR = " OR ";
	private static final String PREDICATE_EQ = "=";
	private static final String PREDICATE_NEQ = "!=";
	private static final String PREDICATE_GTE = ">=";
	private static final String PREDICATE_GT = ">";
	private static final String PREDICATE_LTE = "<=";
	private static final String PREDICATE_LT = "<";
	private static final String PREDICATE_CON = "~";

	private String filter = null;
	private List<Predicate> predicates;

	Filterer(HttpServletRequest request) {
		if(StringUtils.isNotBlank(request.getParameter(FILTER))) {
			filter = request.getParameter(FILTER);
			predicates = new ArrayList<>();
		}
	}

	boolean isFiltered() {
		return StringUtils.isNotBlank(filter);
	}
	/*
		It MUST be possible to filter collections for elements matching a certain criteria.
		It MUST be possible to filter collections based on any data element in the core description of the resource.

		For version 1.1, it is RECOMMENDED that logical operations are limited to " AND " and " OR " (note the surrounding white space at each side)
		and that there is only one such operator used in any filter i.e. a single 'AND' or a single 'OR' in the filter. A single white space must
		occur before and after the parameter.
	 */
	Predicate getPredicate(CriteriaBuilder cb, BaseFilterer filterer) throws InvalidFilterFieldException, InvalidDataException {
		buildPredicates(cb, filterer);

		Predicate wrapper;
		//if filter contains AND or OR, load in the predicates we created
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
			// Wrapper can not return a null value, as it will always be returned to the DAO.
			// If nothing is filtered, add something like WHERE querySpecificPredicate = someValue AND [1=1]
			wrapper = cb.equal(cb.literal(1), 1); //1=1
		}
		return wrapper;
	}

	private void buildPredicates(CriteriaBuilder cb, BaseFilterer filterer) throws InvalidFilterFieldException, InvalidDataException {
		String[] filters;
		if(isAnd()) {
			filters = getFilterArray(filter, LOGICAL_AND);
			for(String filter : filters) {
				fillPredicatesList(cb, filterer, filter);
			}
		}
		else if(isOr()) {
			filters = getFilterArray(filter, LOGICAL_OR);
			for(String filter : filters) {
				fillPredicatesList(cb, filterer, filter);
			}
		}
		else {
			// Build predicates directly from the filter string
			fillPredicatesList(cb, filterer, filter);
		}
	}

	private String[] getFilterArray(String filter, String logical) throws InvalidDataException {
		String[] filters = StringUtils.splitByWholeSeparator(filter, logical);
		if(filters != null && filters.length > 2) {
			throw new InvalidDataException("The filter parameter only allows one logical operator");
		}
		return filters;
	}

	private void fillPredicatesList(CriteriaBuilder cb, BaseFilterer filterer, String filter) throws InvalidFilterFieldException, InvalidDataException {
		Path path;
		String field;
		String value = getValue(filter); //All values must be wrapped in single quotes
		String predicate = getPredicateKey(filter);

		if(StringUtils.isBlank(value)) {
			throw new InvalidFilterFieldException("The filter parameter value is missing single quotes, or is blank");
		}

		//Determine Predicate Type
		if(isGreaterThanOrEqual(predicate)) {
			field = getField(filter, PREDICATE_GTE);
			path = getPath(filterer, field);
			predicates.add(getGreaterThanOrEqualToPredicate(cb, path, field, value));
		}
		else if(isGreaterThan(predicate)) {
			field = getField(filter, PREDICATE_GT);
			path = getPath(filterer, field);
			predicates.add(getGreaterThanToPredicate(cb, path, field, value));
		}
		else if(isLessThanOrEqual(predicate)) {
			field = getField(filter, PREDICATE_LTE);
			path = getPath(filterer, field);
			predicates.add(getLessThanOrEqualToPredicate(cb, path, field, value));
		}
		else if(isLessThan(predicate)) {
			field = getField(filter, PREDICATE_LT);
			path = getPath(filterer, field);
			predicates.add(getLessThanToPredicate(cb, path, field, value));
		}
		else if(isNotEqual(predicate)) {
			field = getField(filter, PREDICATE_NEQ);
			path = getPath(filterer, field);
			predicates.add(getNotEqualPredicate(cb, path, field, value));
		}
		else if(isEqual(predicate)) {
			field = getField(filter, PREDICATE_EQ);
			path = getPath(filterer, field);
			predicates.add(getEqualPredicate(cb, path, field, value));
			/*if(isMultiValue(value)) {
				String[] values = StringUtils.split(value, ",");
				List<Predicate> list = new ArrayList<>();
				for(String v : values) {
					list.add(getEqualPredicate(cb, path, v));
				}
				predicates.add(cb.and(list.toArray(new Predicate[0])));
			}
			else {
				predicates.add(getEqualPredicate(cb, path, value));
			}*/
		}
		else if(isContains(predicate)) {
			field = getField(filter, PREDICATE_CON);
			path = getPath(filterer, field);
			if(isMultiValue(value)) {
				String[] values = StringUtils.split(value, ",");
				List<Predicate> list = new ArrayList<>();
				for (String v : values) {
					list.add(getLikePredicate(cb, path, field, v));
				}
				predicates.add(cb.or(list.toArray(new Predicate[0])));
			}
			else {
				predicates.add(getLikePredicate(cb, path, field, value));
			}
		}
	}

	private static String getValue(String filter) {
		return StringUtils.substringBetween(filter, "'");
	}

	private static boolean isMultiValue(String value) {
		return StringUtils.contains(value, ",");
	}

	private static Path getPath(BaseFilterer filterer, String field) throws InvalidFilterFieldException, InvalidDataException {
		return filterer.getPath(field);
	}

	private static String getField(String filter, String predicate) {
		return StringUtils.substringBefore(filter, predicate);
	}

	private static String getPredicateKey(String filter) {
		/* filterMinusValue means that we are looking to the left side of the value
		* ie:   filter=someField='someValue'
		*       becomes: filter=someField=
		*       This way the predicate is at the end of the statement.
		*/
		String filterMinusValue = StringUtils.substringBefore(filter, "'");
		if(StringUtils.endsWith(filterMinusValue, PREDICATE_GT)) {
			return PREDICATE_GT;
		}
		else if(StringUtils.endsWith(filterMinusValue, PREDICATE_GTE)) {
			return PREDICATE_GTE;
		}
		else if(StringUtils.endsWith(filterMinusValue, PREDICATE_LT)) {
			return PREDICATE_LT;
		}
		else if(StringUtils.endsWith(filterMinusValue, PREDICATE_LTE)) {
			return PREDICATE_LTE;
		}
		else if(StringUtils.endsWith(filterMinusValue, PREDICATE_NEQ)) {
			return PREDICATE_NEQ;
		}
		else if(StringUtils.endsWith(filterMinusValue, PREDICATE_EQ)) {
			return PREDICATE_EQ;
		}
		else if(StringUtils.endsWith(filterMinusValue, PREDICATE_CON)) {
			return PREDICATE_CON;
		}
		return null;
	}


	// PATH TYPE CHECK
	private static boolean isPathTypeBoolean(Path path) {
		return path.getJavaType().equals(Boolean.class);
	}

	private static boolean isPathTypeLocalDateTime(Path path) {
		return path.getJavaType().equals(LocalDateTime.class);
	}

	private static boolean isPathTypeLocalDate(Path path) {
		return path.getJavaType().equals(LocalDate.class);
	}

	private static boolean isPathTypeInteger(Path path) {
		return path.getJavaType().equals(Integer.class);
	}

	private static boolean isPathTypeString(Path path) {
		return path.getJavaType().equals(String.class);
	}


	// PREDICATE WITH CORRECT OBJECT TYPE
	private static Predicate getGreaterThanOrEqualToPredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeInteger(path)) {
			return cb.greaterThanOrEqualTo(path, toInteger(value));
		}
		else if(isPathTypeLocalDateTime(path)) {
			return cb.greaterThanOrEqualTo(path, toLocalDateTime(value));
		}
		else if(isPathTypeLocalDate(path)) {
			return cb.greaterThanOrEqualTo(path, toLocalDate(value));
		}
		else {
			throw new InvalidDataException("The predicate [>=] can not be used on field [" + field + "]");
		}
	}

	private static Predicate getGreaterThanToPredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeInteger(path)) {
			return cb.greaterThan(path, toInteger(value));
		}
		else if(isPathTypeLocalDateTime(path)) {
			return cb.greaterThan(path, toLocalDateTime(value));
		}
		else if(isPathTypeLocalDate(path)) {
			return cb.greaterThan(path, toLocalDate(value));
		}
		else {
			throw new InvalidDataException("The predicate [>] can not be used on field [" + field + "]");
		}
	}

	private static Predicate getLessThanOrEqualToPredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeInteger(path)) {
			return cb.lessThanOrEqualTo(path, toInteger(value));
		}
		else if(isPathTypeLocalDateTime(path)) {
			return cb.lessThanOrEqualTo(path, toLocalDateTime(value));
		}
		else if(isPathTypeLocalDate(path)) {
			return cb.lessThanOrEqualTo(path, toLocalDate(value));
		}
		else {
			throw new InvalidDataException("The predicate [<=] can not be used on field [" + field + "]");
		}
	}

	private static Predicate getLessThanToPredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeInteger(path)) {
			return cb.lessThan(path, toInteger(value));
		}
		else if(isPathTypeLocalDateTime(path)) {
			return cb.lessThan(path, toLocalDateTime(value));
		}
		else if(isPathTypeLocalDate(path)) {
			return cb.lessThan(path, toLocalDate(value));
		}
		else {
			throw new InvalidDataException("The predicate [<] can not be used on field [" + field + "]");
		}
	}

	private static Predicate getNotEqualPredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeString(path)) {
			return cb.notEqual(path, value);
		}
		else if(isPathTypeInteger(path)) {
			return cb.notEqual(path, toInteger(value));
		}
		else if(isPathTypeBoolean(path)) {
			return cb.notEqual(path, BooleanUtils.toBoolean(value));
		}
		else if(isPathTypeLocalDateTime(path)) {
			return cb.notEqual(path, toLocalDateTime(value));
		}
		else if(isPathTypeLocalDate(path)) {
			return cb.notEqual(path, toLocalDate(value));
		}
		else {
			throw new InvalidDataException("The predicate [!=] can not be used on field [" + field + "]");
		}
	}

	private static Predicate getEqualPredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeString(path)) {
			return cb.equal(path, value);
		}
		if(isPathTypeInteger(path)) {
			return cb.equal(path, toInteger(value));
		}
		else if(isPathTypeBoolean(path)) {
			return cb.equal(path, BooleanUtils.toBoolean(value));
		}
		else if(isPathTypeLocalDateTime(path)) {
			return cb.equal(path, toLocalDateTime(value));
		}
		else if(isPathTypeLocalDate(path)) {
			return cb.equal(path, toLocalDate(value));
		}
		else {
			throw new InvalidDataException("The predicate [=] can not be used on field [" + field + "]");
		}
	}

	private static Predicate getLikePredicate(CriteriaBuilder cb, Path path, String field, String value) throws InvalidDataException {
		if(isPathTypeString(path)) {
			return cb.like(path, "%" + value + "%");
		}
		if(isPathTypeInteger(path)) {
			return cb.like(path.as(String.class), "%" + toInteger(value) + "%");
		}
		else if(isPathTypeBoolean(path)) {
			return cb.like(path, "%" + BooleanUtils.toBoolean(value) + "%");
		}
		else {
			throw new InvalidDataException("The predicate [~] can not be used on field [" + field + "]");
		}
	}

	// LOGICAL OPERATOR
	private boolean isAnd(){
		return StringUtils.contains(filter, LOGICAL_AND);
	}

	private boolean isOr(){
		return StringUtils.contains(filter, LOGICAL_OR);
	}

	//PREDICATE OPERATOR
	private static boolean isEqual(String segment) {
		return StringUtils.equals(segment, PREDICATE_EQ);
	}

	private static boolean isNotEqual(String segment) {
		return StringUtils.equals(segment, PREDICATE_NEQ);
	}

	private static boolean isGreaterThan(String segment) {
		return StringUtils.equals(segment, PREDICATE_GT);
	}

	private static boolean isGreaterThanOrEqual(String segment) {
		return StringUtils.equals(segment, PREDICATE_GTE);
	}

	private static boolean isLessThan(String segment) {
		return StringUtils.equals(segment, PREDICATE_LT);
	}

	private static boolean isLessThanOrEqual(String segment) {
		return StringUtils.equals(segment, PREDICATE_LTE);
	}

	private static boolean isContains(String segment) {
		return StringUtils.equals(segment, PREDICATE_CON);
	}

	//CONVERTERS
	private static LocalDateTime toLocalDateTime(String value) throws InvalidDataException {
		try {
			return ZonedDateTime.parse(value).toLocalDateTime();
		}
		catch(Exception e) {
			throw new InvalidDataException("Value: [" + value + "] is not a valid datetime");
		}
	}

	private static LocalDate toLocalDate(String value) throws InvalidDataException {
		try {
			return LocalDate.parse(value);
		}
		catch(Exception e) {
			throw new InvalidDataException("Value: [" + value + "] is not a valid date");
		}
	}

	private static Integer toInteger(String value) throws InvalidDataException {
		if(NumberUtils.isCreatable(value)) {
			return Integer.parseInt(value);
		}
		throw new InvalidDataException("Value: [" + value + "] is not a valid number");
	}
}