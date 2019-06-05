package org.ricone.api.xpress.request.xStudent;

import org.ricone.api.core.model.StudentEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.model.XStudent;
import org.ricone.api.xpress.model.XStudents;
import org.ricone.api.xpress.model.XStudentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XStudents:XStudentEventLogMapper")
public class XStudentEventLogMapper {
    private final XStudentMapper mapper;

    public XStudentEventLogMapper(XStudentMapper mapper) {
        this.mapper = mapper;
    }

    public XStudentsResponse convert(List<EventLogWrapper<StudentEventLog>> instance) {
        List<XStudent> list = new ArrayList<>();
        for (EventLogWrapper<StudentEventLog> wrapper : instance) {
            XStudent xStudent = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xStudent != null) {
                list.add(xStudent);
            }
        }

        XStudentsResponse response = new XStudentsResponse();
        XStudents xStudents = new XStudents();
        xStudents.setXStudent(list);

        response.setXStudents(xStudents);
        return response;
    }

    private XStudent map(StudentEventLog eventLog, String districtId) {
        XStudent instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XStudent();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getStudent(), districtId);
            if(instance == null) {
                instance = new XStudent();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}