package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.error.exception.BlankFieldSelectionException;
import org.ricone.api.xpress.error.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

public class FieldSelectionData {
	private Logger logger = LogManager.getLogger(FieldSelectionData.class);
	private final String FIELDS = "fields";
	private Set<String> fields = null;

	FieldSelectionData(HttpServletRequest request) throws Exception {
		if(request.getParameter(FIELDS) != null) {
			if(StringUtils.isEmpty(request.getParameter(FIELDS))) {
				throw new BlankFieldSelectionException();
			}

			String[] fieldSegments = StringUtils.split(StringUtils.deleteWhitespace(request.getParameter(FIELDS)), ",");
			if(fieldSegments != null) {
				fields = new HashSet<>(Arrays.asList(fieldSegments));
			}
		}
	}

	public Set<String> getFields() {
		return fields;
	}

	public boolean hasFieldSelection() {
		return CollectionUtils.isNotEmpty(fields);
	}

	public boolean isValidFieldSelection(Class<?> clazz) {
		return getInvalidFields(clazz).size() == 0;
	}

	List<String> getInvalidFields(Class<?> clazz) {
		final List<String> actualFieldNames = new ArrayList<>();
		final Field[] baseFields = clazz.getSuperclass().getDeclaredFields(); //Base: sourcedId, status, metadata, dateLastModified
		final Field[] fields = clazz.getDeclaredFields(); //?: Whatever class is passed in, this will always extend Base.
		actualFieldNames.addAll(getFieldNames(baseFields));
		actualFieldNames.addAll(getFieldNames(fields));

		List<String> inputFields = new ArrayList<>(this.fields);
		inputFields.removeAll(actualFieldNames);
		return inputFields;
	}

	private static List<String> getFieldNames(Field[] fields) {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields)
			fieldNames.add(field.getName());
		return fieldNames;
	}
}
