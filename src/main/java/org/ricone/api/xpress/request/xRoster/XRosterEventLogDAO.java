package org.ricone.api.xpress.request.xRoster;

import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */
interface XRosterEventLogDAO {

	List<EventLogWrapper<RosterEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<RosterEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<RosterEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<RosterEventLog>> findAllByCourseRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<RosterEventLog>> findAllByStaffRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<RosterEventLog>> findAllByStudentRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByCourseRefId(ControllerData metadata, String refId);

	int countAllByStaffRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}