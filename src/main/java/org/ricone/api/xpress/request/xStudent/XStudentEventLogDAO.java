package org.ricone.api.xpress.request.xStudent;

import org.ricone.api.core.model.StudentEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

interface XStudentEventLogDAO {

	List<EventLogWrapper<StudentEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<StudentEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StudentEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StudentEventLog>> findAllByRosterRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StudentEventLog>> findAllByStaffRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StudentEventLog>> findAllByContactRefId(ControllerData metadata, String refId);
	
	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByContactRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}