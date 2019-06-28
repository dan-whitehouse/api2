package org.ricone.api.xpress.request.xContact;

import org.ricone.api.core.model.ContactEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.ControllerData;

import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

interface XContactEventLogDAO {

	List<EventLogWrapper<ContactEventLog>> findAll(ControllerData metadata);
	
	List<EventLogWrapper<ContactEventLog>> findAllByLeaRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<ContactEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId);

	List<EventLogWrapper<ContactEventLog>> findAllByStudentRefId(ControllerData metadata, String refId);

	/** Counts **/
	int countAll(ControllerData metadata);

	int countAllByLeaRefId(ControllerData metadata, String refId);

	int countAllBySchoolRefId(ControllerData metadata, String refId);

	int countAllByStudentRefId(ControllerData metadata, String refId);

	/** Latest Opaque Markers **/
	Date getLatestOpaqueMarker(ControllerData metadata);
}