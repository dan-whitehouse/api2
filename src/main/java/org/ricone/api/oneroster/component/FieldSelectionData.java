package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.model.Base;
import org.ricone.api.xpress.error.exception.BadRequestException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.*;

public class FieldSelectionData {
	private Logger logger = LogManager.getLogger(FieldSelectionData.class);
	private final String FIELDS = "fields";
	private Set<String> fields = null;

	FieldSelectionData(HttpServletRequest request) throws BadRequestException {
		if(StringUtils.isNotBlank(request.getParameter(FIELDS))) {
			String[] fieldSegments = StringUtils.split(StringUtils.deleteWhitespace(request.getParameter(FIELDS)), ",");
			if(fieldSegments != null) {
				fields = new HashSet<>(Arrays.asList(fieldSegments));
			}
		}
	}

	public boolean hasFieldSelection(Class<?> clazz) {
		return CollectionUtils.isNotEmpty(fields) && areAllFieldsValid(clazz);
	}

	public Set<String> getFields() {
		return fields;
	}

	private boolean areAllFieldsValid(Class<?> clazz) {
		final List<String> actualFieldNames = new ArrayList<>();
		final Field[] baseFields = clazz.getSuperclass().getDeclaredFields(); //Base: sourcedId, status, metadata, dateLastModified
		final Field[] fields = clazz.getDeclaredFields(); //?: Whatever class is passed in, this will always extend Base.
		actualFieldNames.addAll(getFieldNames(baseFields));
		actualFieldNames.addAll(getFieldNames(fields));

		List<String> inputFields = new ArrayList<>(this.fields);
		inputFields.removeAll(actualFieldNames);
		return inputFields.size() == 0;
	}

	private static List<String> getFieldNames(Field[] fields) {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields)
			fieldNames.add(field.getName());
		return fieldNames;
	}
}
