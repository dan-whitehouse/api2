package org.ricone.api.xpress.request.xCalendar;

import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

//import api.model.core.wrapper.EventLogWrapper;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-09
 */

public interface XCalendarEventLogDAO{

	List<EventLogWrapper<CalendarEventLog>> findAll(ControllerData metadata, Date iso8601);

	List<EventLogWrapper<CalendarEventLog>> findAllByLeaRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<CalendarEventLog>> findAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId);

	/** Counts **/
	int countAll(ControllerData metadata, Date iso8601);

	int countAllByLeaRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId);

	Date getLatestOpaqueMarker(ControllerData metadata);
}