package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Error;

import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://stackoverflow.com/questions/299998/instantiating-object-of-type-parameter
 */

public abstract class BaseMapper<V, M extends Base, R1 extends BaseMultiResponse<M>, R2 extends BaseSingleResponse<M>> {
	private Class<V> viewClass;
	private Class<M> modelClass;
	private final Constructor<? extends R1> r1Constructor;
	private final Constructor<? extends R2> r2Constructor;

	public BaseMapper(Class<V> viewClass, Class<M> modelClass, Class<? extends R1> r1Class, Class<? extends R2> r2Class) throws NoSuchMethodException {
		this.viewClass = viewClass;
		this.modelClass = modelClass;
		this.r1Constructor = r1Class.getConstructor();
		this.r2Constructor = r2Class.getConstructor();
	}

	public R1 convert(List<V> instance, RequestData metadata) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<M> list = new ArrayList<>();
		for (V view : instance) {
			M model = map(view);
			if(model != null) {
				list.add(model);
			}
		}

		R1 r1 = r1Constructor.newInstance();
		r1.setData(list);
		r1.setWarnings(mapPartialErrors(metadata, viewClass, modelClass));
		return r1;
	}

	public R2 convert(V view, RequestData metadata) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if(view != null) {
			R2 r2 = r2Constructor.newInstance();
			r2.setData(map(view));
			r2.setWarnings(mapPartialErrors(metadata, viewClass, modelClass));
			return r2;
		}
		return null;
	}

	protected abstract M map(V instance);

	protected abstract org.ricone.api.oneroster.model.Metadata mapMetadata(V instance);

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
