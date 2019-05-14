package org.ricone.api.xpress.request.xLea;


import org.ricone.api.core.model.LeaEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

public interface XLeaEventLogDAO {

	List<EventLogWrapper<LeaEventLog>> findAll(ControllerData metadata, Date iso8601);

	List<EventLogWrapper<LeaEventLog>> findAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByCalendarRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByCourseRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByRosterRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByStaffRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByStudentRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByContactRefId(ControllerData metadata, Date iso8601, String refId);

	/** Counts **/
	int countAll(ControllerData metadata, Date iso8601);

	int countAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByCalendarRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByCourseRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByRosterRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByStaffRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByStudentRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByContactRefId(ControllerData metadata, Date iso8601, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}