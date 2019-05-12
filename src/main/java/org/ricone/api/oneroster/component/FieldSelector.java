package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.component.error.exception.BlankFieldSelectionException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

public class FieldSelector {
	private Logger logger = LogManager.getLogger(FieldSelector.class);
	private final String FIELDS = "fields";
	private Set<String> fields = null;

	/*
		Field Selection
		It MUST be possible for a requester to select the range of fields to be returned. By default, all mandatory and optional
		fields from the core description of the resource MUST be returned. If any fields are specified in the request then the
		implementation MUST return those fields AND ONLY those fields i.e. the multiplicity rules for an element are overridden.
		Any Field or fields from the Full Data Model MAY be requested. Any field or fields from the Data Model MAY be requested.

		Field selection request MUST make use of the reserved word fields. The value of fields is a comma delimited list of the fields to return.

		Example: To ask for a list of students retuning only the given name and family name:

		GET https://imsglobal.org/ims/oneroster/v1p1/students?fields=givenName,familyName

		If the consumer requests that data be selected using non-existent field, ALL data for the record is returned and the server
		must provide the associated transaction status code information of:
			• CodeMajor value is 'success';
			• Severity value is 'warning';
			• CodeMinor value is 'invalid_selection_field';
			• StatusCode value is the corresponding HTTP response code;
			• Description should contain the supplied unknown field.

		If the consumer requests that data be selected using a blank field the request will be treated as an invalid request.
		The server must provide the associated transaction status code information of:
			•  CodeMajor value is 'failure';
			•  Severity value is 'error';
			•  CodeMinor value is 'invalid_blank_selection_field';
			•  StatusCode value is the corresponding HTTP response code.

		NOTE: Field Selection must be supported for ALL read endpoints.
	 */

	FieldSelector(HttpServletRequest request) throws Exception {
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
		//While testing infinite campus, fields were case-sensitive
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
