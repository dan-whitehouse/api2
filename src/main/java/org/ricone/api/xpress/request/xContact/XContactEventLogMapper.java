package org.ricone.api.xpress.request.xContact;

import org.ricone.api.core.model.ContactEventLog;
import org.ricone.api.core.model.LeaEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.model.*;
import org.ricone.api.xpress.request.xLea.XLeaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XPress:XContacts:XContactEventLogMapper")
public class XContactEventLogMapper {
    @Autowired private XContactMapper mapper;

    public XContactEventLogMapper() {
    }

    public XContactsResponse convert(List<EventLogWrapper<ContactEventLog>> instance) {
        List<XContact> list = new ArrayList<>();
        for (EventLogWrapper<ContactEventLog> wrapper : instance) {
            XContact xContact = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xContact != null) {
                list.add(xContact);
            }
        }

        XContactsResponse response = new XContactsResponse();
        XContacts xContacts = new XContacts();
        xContacts.setXContact(list);

        response.setXContacts(xContacts);
        return response;
    }

    private XContact map(ContactEventLog eventLog, String districtId) {
        XContact instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XContact();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getStudentContact(), districtId);
            if(instance == null) {
                instance = new XContact();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}