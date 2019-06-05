package org.ricone.api.xpress.request.xSchool;

import org.ricone.api.core.model.SchoolEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.model.XSchool;
import org.ricone.api.xpress.model.XSchools;
import org.ricone.api.xpress.model.XSchoolsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XSchools:XSchoolEventLogMapper")
public class XSchoolEventLogMapper {
    private final XSchoolMapper mapper;

    public XSchoolEventLogMapper(XSchoolMapper mapper) {
        this.mapper = mapper;
    }

    public XSchoolsResponse convert(List<EventLogWrapper<SchoolEventLog>> instance) {
        List<XSchool> list = new ArrayList<>();
        for (EventLogWrapper<SchoolEventLog> wrapper : instance) {
            XSchool xSchool = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xSchool != null) {
                list.add(xSchool);
            }
        }

        XSchoolsResponse response = new XSchoolsResponse();
        XSchools xSchools = new XSchools();
        xSchools.setXSchool(list);

        response.setXSchools(xSchools);
        return response;
    }

    private XSchool map(SchoolEventLog eventLog, String districtId) {
        XSchool instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XSchool();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getSchool(), districtId);
            if(instance == null) {
                instance = new XSchool();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}