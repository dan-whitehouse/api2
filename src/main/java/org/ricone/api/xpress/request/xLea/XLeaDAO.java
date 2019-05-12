package org.ricone.api.xpress.request.xLea;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;

import java.util.List;

public interface XLeaDAO {
	/* Find */
	LeaWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	LeaWrapper findById(ControllerData metadata, String id, String idType);

	List<LeaWrapper> findAll(ControllerData metadata);

	List<LeaWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<LeaWrapper> findAllByCalendarRefId(ControllerData metadata, String refId);

	List<LeaWrapper> findAllByCourseRefId(ControllerData metadata, String refId);

	List<LeaWrapper> findAllByRosterRefId(ControllerData metadata, String refId);

	List<LeaWrapper> findAllByStaffRefId(ControllerData metadata, String refId);

	List<LeaWrapper> findAllByStudentRefId(ControllerData metadata, String refId);

	List<LeaWrapper> findAllByContactRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearById(ControllerData metadata, String id, String idType);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByCalendarRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllByContactRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByCalendarRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	int countAllByContactRefId(ControllerData metadata, String refId);
}