package org.ricone.api.xpress.request.xStudent;

import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;

import java.util.List;

public interface XStudentDAO {
	/* Find */
	StudentWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	StudentWrapper findByLocalId(ControllerData metadata, String localId);

	StudentWrapper findByStateId(ControllerData metadata, String stateId);

	List<StudentWrapper> findAll(ControllerData metadata);

	List<StudentWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<StudentWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<StudentWrapper> findAllByRosterRefId(ControllerData metadata, String refId);

	List<StudentWrapper> findAllByStaffRefId(ControllerData metadata, String refId);

	List<StudentWrapper> findAllByContactRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearByLocalId(ControllerData metadata, String localId);

	Integer greatestSchoolYearByStateId(ControllerData metadata, String localId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByContactRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByContactRefId(ControllerData metadata, String refId);
}