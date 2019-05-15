package org.ricone.api.xpress.request.xLea;

import org.ricone.api.core.model.LeaEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

public interface XLeaEventLogDAO {

	List<EventLogWrapper<LeaEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<LeaEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByCalendarRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByCourseRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByRosterRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByStaffRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByStudentRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<LeaEventLog>> findAllByContactRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByCalendarRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByRosterRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	int countAllByContactRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}