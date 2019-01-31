package org.ricone.api.oneroster.component;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.model.*;

import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class BaseMapper2<V, M extends Base, R1 extends BaseMultiResponse<M>, R2 extends BaseSingleResponse<M>> {
	private Class<V> viewClass;
	private Class<M> modelClass;
	private final Constructor<? extends R1> r1Constructor;
	private final Constructor<? extends R2> r2Constructor;

	public BaseMapper2(Class<V> viewClass, Class<M> modelClass, Class<? extends R1> r1Class, Class<? extends R2> r2Class) throws NoSuchMethodException {
		this.viewClass = viewClass;
		this.modelClass = modelClass;
		this.r1Constructor = r1Class.getConstructor();
		this.r2Constructor = r2Class.getConstructor();
	}

	public R1 convert(List<V> instance, ControllerData metadata) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<M> list = new ArrayList<>();
		for (V view : instance) {
			M model = map(view);
			if(model != null) {
				list.add(model);
			}
		}

		R1 r1 = r1Constructor.newInstance();
		r1.setData(list);
		r1.setStatusInfoSets(mapErrors(metadata, viewClass, modelClass));
		return r1;
	}

	public R2 convert(V view, ControllerData metadata) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if(view != null) {
			R2 r2 = r2Constructor.newInstance();
			r2.setData(map(view));
			r2.setStatusInfoSets(mapErrors(metadata, viewClass, modelClass));
			return r2;
		}
		return null;
	}

	protected abstract M map(V instance);

	protected abstract Metadata mapMetadata(V instance);

	private List<StatusInfoSet> mapErrors(ControllerData metadata, Class<?> table, Class<? extends Base> model) {
		List<StatusInfoSet> statusInfoSets = new ArrayList<>();

		if(metadata.getSorting().isSorted() && !metadata.getSorting().isValidField(table)) {
			StatusInfoSet sortError = new StatusInfoSet();
			sortError.setImsxCodeMajor(CodeMajor.success);
			sortError.setImsxCodeMinor(CodeMinor.invalid_sort_field);
			sortError.setImsxSeverity(Severity.warning);
			sortError.setImsxDescription("The field used in the sort parameter doesn't exist.");
			statusInfoSets.add(sortError);
		}

		if(metadata.getFieldSelection().hasFieldSelection() && !metadata.getFieldSelection().isValidFieldSelection(model)) {
			StatusInfoSet sortError = new StatusInfoSet();
			sortError.setImsxCodeMajor(CodeMajor.success);
			sortError.setImsxCodeMinor(CodeMinor.invalid_selection_field);
			sortError.setImsxSeverity(Severity.warning);
			sortError.setImsxDescription("One or more of the fields " + metadata.getFieldSelection().getInvalidFields(model) + " included in the fields parameter doesn't exist.");
			statusInfoSets.add(sortError);
		}

		if(CollectionUtils.isEmpty(statusInfoSets)) {
			return null;
		}
		return statusInfoSets;
	}
}
