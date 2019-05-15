package org.ricone.api.xpress.request.xLea;

import org.ricone.api.core.model.LeaEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.model.XLea;
import org.ricone.api.xpress.model.XLeas;
import org.ricone.api.xpress.model.XLeasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XLeas:XLeaEventLogMapper")
public class XLeaEventLogMapper {
    @Autowired private XLeaMapper mapper;

    public XLeaEventLogMapper() {
    }

    public XLeasResponse convert(List<EventLogWrapper<LeaEventLog>> instance) {
        List<XLea> list = new ArrayList<>();
        for (EventLogWrapper<LeaEventLog> wrapper : instance) {
            XLea xLea = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xLea != null) {
                list.add(xLea);
            }
        }

        XLeasResponse response = new XLeasResponse();
        XLeas xLeas = new XLeas();
        xLeas.setXLeas(list);

        response.setXLeas(xLeas);
        return response;
    }

    private XLea map(LeaEventLog eventLog, String districtId) {
        XLea instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XLea();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getLea(), districtId);
            if(instance == null) {
                instance = new XLea();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}