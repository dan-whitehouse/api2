package org.ricone.api.xpress.request.xContact;

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
 * @version 1.2.1
 * @since 2018-11-09
 */

interface XContactEventLogDAO {

	List<EventLogWrapper<ContactEventLog>> findAll(ControllerData metadata, Date iso8601);
	
	List<EventLogWrapper<ContactEventLog>> findAllByLeaRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<ContactEventLog>> findAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId);

	List<EventLogWrapper<ContactEventLog>> findAllByStudentRefId(ControllerData metadata, Date iso8601, String refId);

	/** Counts **/
	int countAll(ControllerData metadata, Date iso8601);

	int countAllByLeaRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId);

	int countAllByStudentRefId(ControllerData metadata, Date iso8601, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}