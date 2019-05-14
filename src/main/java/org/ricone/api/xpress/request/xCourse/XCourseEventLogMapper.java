package org.ricone.api.xpress.request.xCourse;

import org.ricone.api.core.model.CourseEventLog;
import org.ricone.api.core.model.LeaEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.model.*;
import org.ricone.api.xpress.request.xLea.XLeaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XPress:XCourses:XCourseEventLogMapper")
public class XCourseEventLogMapper {
    @Autowired private XCourseMapper mapper;

    public XCourseEventLogMapper() {
    }

    public XCoursesResponse convert(List<EventLogWrapper<CourseEventLog>> instance) {
        List<XCourse> list = new ArrayList<>();
        for (EventLogWrapper<CourseEventLog> wrapper : instance) {
            XCourse xCourse = map(wrapper.getEventLog(), wrapper.getDistrictId());
            if (xCourse != null) {
                list.add(xCourse);
            }
        }

        XCoursesResponse response = new XCoursesResponse();
        XCourses xCourses = new XCourses();
        xCourses.setXCourse(list);

        response.setXCourses(xCourses);
        return response;
    }

    private XCourse map(CourseEventLog eventLog, String districtId) {
        XCourse instance;
        if ("D".equalsIgnoreCase(eventLog.getEventType())) {
            instance = new XCourse();
            instance.setRefId(eventLog.getObjectRefId());
        }
        else {
            instance = mapper.map(eventLog.getCourse(), districtId);
            if(instance == null) {
                instance = new XCourse();
                instance.setRefId(eventLog.getObjectRefId());
            }
        }
        return instance;
    }
}