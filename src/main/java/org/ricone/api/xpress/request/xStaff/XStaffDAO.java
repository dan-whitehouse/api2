package org.ricone.api.xpress.request.xStaff;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;

import java.util.List;

public interface XStaffDAO {
	/* Find */
	StaffWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	StaffWrapper findByLocalId(ControllerData metadata, String localId);

	StaffWrapper findByStateId(ControllerData metadata, String stateId);

	List<StaffWrapper> findAll(ControllerData metadata);

	List<StaffWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<StaffWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<StaffWrapper> findAllByCourseRefId(ControllerData metadata, String refId);

	List<StaffWrapper> findAllByRosterRefId(ControllerData metadata, String refId);

	List<StaffWrapper> findAllByStudentRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearByLocalId(ControllerData metadata, String localId);

	Integer greatestSchoolYearByStateId(ControllerData metadata, String localId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);
}