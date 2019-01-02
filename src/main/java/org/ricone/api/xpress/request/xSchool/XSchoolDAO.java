package org.ricone.api.xpress.request.xSchool;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;

import java.util.List;

public interface XSchoolDAO {
	/* Find */
	SchoolWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	SchoolWrapper findByLocalId(ControllerData metadata, String localId);

	List<SchoolWrapper> findAll(ControllerData metadata);

	List<SchoolWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<SchoolWrapper> findAllByCalendarRefId(ControllerData metadata, String refId);

	List<SchoolWrapper> findAllByCourseRefId(ControllerData metadata, String refId);

	List<SchoolWrapper> findAllByRosterRefId(ControllerData metadata, String refId);

	List<SchoolWrapper> findAllByStaffRefId(ControllerData metadata, String refId);

	List<SchoolWrapper> findAllByStudentRefId(ControllerData metadata, String refId);

	List<SchoolWrapper> findAllByContactRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearByLocalId(ControllerData metadata, String localId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByCalendarRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByContactRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllByCalendarRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	int countAllByContactRefId(ControllerData metadata, String refId);
}