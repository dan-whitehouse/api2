package org.ricone.api.oneroster.component;

import org.ricone.api.oneroster.model.*;

import java.lang.Class;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper {
	protected List<StatusInfoSet> mapErrors(ControllerData metadata, Class<?> table, Class<? extends Base> model) {
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
		return statusInfoSets;
	}
}
