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

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

public interface XCalendarEventLogDAO{

	List<EventLogWrapper<CalendarEventLog>> findAll(ControllerData metadata);

	List<EventLogWrapper<CalendarEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<CalendarEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	Date getLatestOpaqueMarker(ControllerData metadata);
}