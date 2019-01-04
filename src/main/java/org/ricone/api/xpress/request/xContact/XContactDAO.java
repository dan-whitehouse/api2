package org.ricone.api.xpress.request.xContact;

import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;

import java.util.List;

public interface XContactDAO {
	/* Find */
	StudentContactWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	List<StudentContactWrapper> findAll(ControllerData metadata);

	List<StudentContactWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<StudentContactWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<StudentContactWrapper> findAllByStudentRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);
}