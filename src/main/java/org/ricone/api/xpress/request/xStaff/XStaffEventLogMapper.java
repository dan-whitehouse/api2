package org.ricone.api.xpress.request.xStaff;

import org.ricone.api.core.model.StaffEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.XStaff;
import org.ricone.api.xpress.model.XStaffs;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XStaffs:XStaffEventLogMapper")
public class XStaffEventLogMapper {
    private final XStaffMapper mapper;

    public XStaffEventLogMapper(XStaffMapper mapper) {
        this.mapper = mapper;
    }

    public XStaffsResponse convert(List<EventLogWrapper<StaffEventLog>> instance) throws MappingException {
        List<XStaff> list = new ArrayList<>();
        for (EventLogWrapper<StaffEventLog> wrapper : instance) {
            XStaff xStaff = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xStaff != null) {
                list.add(xStaff);
            }
        }

        XStaffsResponse response = new XStaffsResponse();
        XStaffs xStaffs = new XStaffs();
        xStaffs.setXStaff(list);

        response.setXStaffs(xStaffs);
        return response;
    }

    private XStaff map(StaffEventLog eventLog, String districtId) throws MappingException {
        XStaff instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XStaff();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getStaff(), districtId);
            if(instance == null) {
                instance = new XStaff();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}