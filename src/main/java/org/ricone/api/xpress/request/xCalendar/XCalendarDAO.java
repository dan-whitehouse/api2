package org.ricone.api.xpress.request.xCalendar;

import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;

import java.util.List;

public interface XCalendarDAO {
	/* Find */
	SchoolCalendarWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException;

	List<SchoolCalendarWrapper> findAll(ControllerData metadata);

	List<SchoolCalendarWrapper> findAllByLeaRefId(ControllerData metadata, String refId);

	List<SchoolCalendarWrapper> findAllBySchoolRefId(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearByRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAll(ControllerData metadata);

	Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId);

	Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId);

	/* Counts */
	int countAll(ControllerData metaData);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);
}