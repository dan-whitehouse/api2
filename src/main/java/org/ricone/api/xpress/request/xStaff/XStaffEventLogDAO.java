package org.ricone.api.xpress.request.xStaff;

import org.ricone.api.core.model.StaffEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

interface XStaffEventLogDAO {

	List<EventLogWrapper<StaffEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<StaffEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StaffEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StaffEventLog>> findAllByCourseRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StaffEventLog>> findAllByRosterRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<StaffEventLog>> findAllByStudentRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}