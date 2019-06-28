package org.ricone.api.xpress.request.xCourse;

import org.ricone.api.core.model.CourseEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

interface XCourseEventLogDAO {

	List<EventLogWrapper<CourseEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<CourseEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<CourseEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<CourseEventLog>> findAllByRosterRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}