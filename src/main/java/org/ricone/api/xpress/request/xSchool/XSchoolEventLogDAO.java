package org.ricone.api.xpress.request.xSchool;

import org.ricone.api.core.model.SchoolEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

interface XSchoolEventLogDAO {

	List<EventLogWrapper<SchoolEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<SchoolEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<SchoolEventLog>> findAllByCalendarRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<SchoolEventLog>> findAllByCourseRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<SchoolEventLog>> findAllByRosterRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<SchoolEventLog>> findAllByStaffRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<SchoolEventLog>> findAllByStudentRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<SchoolEventLog>> findAllByContactRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllByCalendarRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	int countAllByContactRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}