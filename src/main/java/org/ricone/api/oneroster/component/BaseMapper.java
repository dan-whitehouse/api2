package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.model.Error;
import org.ricone.api.oneroster.model.*;

import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://stackoverflow.com/questions/299998/instantiating-object-of-type-parameter
 */

public abstract class BaseMapper<T, M extends Base, R1 extends BaseMultiResponse<M>, R2 extends BaseSingleResponse<M>> {
	private Class<T> tableClass;
	private Class<M> modelClass;
	private final Constructor<? extends R1> r1Constructor;
	private final Constructor<? extends R2> r2Constructor;

	public BaseMapper(Class<T> tableClass, Class<M> modelClass, Class<? extends R1> r1Class, Class<? extends R2> r2Class) throws NoSuchMethodException {
		this.tableClass = tableClass;
		this.modelClass = modelClass;
		this.r1Constructor = r1Class.getConstructor();
		this.r2Constructor = r2Class.getConstructor();
	}

	public R1 convert(List<T> instance, RequestData metadata) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<M> list = new ArrayList<>();
		for (T table : instance) {
			M model = map(table);
			if(model != null) {
				list.add(model);
			}
		}

		R1 r1 = r1Constructor.newInstance();
		r1.setData(list);
		r1.setWarnings(mapPartialErrors(metadata, tableClass, modelClass));
		return r1;
	}

	public R2 convert(T table, RequestData metadata) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if(table != null) {
			R2 r2 = r2Constructor.newInstance();
			r2.setData(map(table));
			r2.setWarnings(mapPartialErrors(metadata, tableClass, modelClass));
			return r2;
		}
		return null;
	}

	protected abstract M map(T instance);

	protected abstract Metadata mapMetadata(T instance);

	private List<Error> mapPartialErrors(RequestData metadata, Class<?> table, Class<? extends Base> model) {
		List<Error> statusInfoSets = new ArrayList<>();

		/*
			If the consumer requests that data be selected using non-existent field, ALL data for the record is returned
			and the server must provide the associated transaction status code information of:
				•  CodeMajor value is 'success';
				•  Severity value is 'warning';
				•  CodeMinor value is 'invalid_selection_field';
				•  StatusCode value is the corresponding HTTP response code;
				•  Description should contain the supplied unknown field.
		*/
		if(metadata.getFieldSelector().hasFieldSelection() && !metadata.getFieldSelector().isValidFieldSelection(model)) {
			metadata.getFieldSelector().getInvalidFields(model).forEach(warning -> {
				Error error = new Error();
				error.setCodeMajor(CodeMajor.success);
				error.setCodeMinor(CodeMinor.invalid_selection_field);
				error.setSeverity(Severity.warning);
				error.setDescription("Invalid field: " + warning);
				statusInfoSets.add(error);
			});
		}

		/*
			If the consumer requests that the data is to be sorted by a non-existent field, the data is returned in
			the service provider's default sort order and the server must provide the associated transaction status
			code information of:
				•  CodeMajor value is 'success';
				•  Severity value is 'warning';
				•  CodeMinor value is 'invalid_sort_field';
				•  Description should contain the supplied unknown field.
		*/
		if(metadata.getSorter().isSorted() && !metadata.getSorter().isValidField(table)) {
			Error error = new Error();
			error.setCodeMajor(CodeMajor.success);
			error.setCodeMinor(CodeMinor.invalid_sort_field);
			error.setSeverity(Severity.warning);
			error.setDescription("Invalid field: " + metadata.getSorter().getSort());
			statusInfoSets.add(error);
		}

		if(CollectionUtils.isEmpty(statusInfoSets)) {
			return null;
		}
		return statusInfoSets;
	}
}
