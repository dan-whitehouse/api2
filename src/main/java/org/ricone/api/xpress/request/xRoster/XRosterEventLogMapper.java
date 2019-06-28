package org.ricone.api.xpress.request.xRoster;

import org.ricone.api.core.model.RosterEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.XRoster;
import org.ricone.api.xpress.model.XRosters;
import org.ricone.api.xpress.model.XRostersResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XRosters:XRosterEventLogMapper")
public class XRosterEventLogMapper {
    private final XRosterMapper mapper;

    public XRosterEventLogMapper(XRosterMapper mapper) {
        this.mapper = mapper;
    }

    public XRostersResponse convert(List<EventLogWrapper<RosterEventLog>> instance) throws MappingException {
        List<XRoster> list = new ArrayList<>();
        for (EventLogWrapper<RosterEventLog> wrapper : instance) {
            XRoster xRoster = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xRoster != null) {
                list.add(xRoster);
            }
        }

        XRostersResponse response = new XRostersResponse();
        XRosters xRosters = new XRosters();
        xRosters.setXRoster(list);

        response.setXRosters(xRosters);
        return response;
    }

    private XRoster map(RosterEventLog eventLog, String districtId) throws MappingException {
        XRoster instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XRoster();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getCourseSection(), districtId);
            if(instance == null) {
                instance = new XRoster();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}