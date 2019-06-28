package org.ricone.api.xpress.request.xCourse;

import org.ricone.api.core.model.CourseEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.XCourse;
import org.ricone.api.xpress.model.XCourses;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XCourses:XCourseEventLogMapper")
public class XCourseEventLogMapper {
    private final XCourseMapper mapper;

    public XCourseEventLogMapper(XCourseMapper mapper) {
        this.mapper = mapper;
    }

    public XCoursesResponse convert(List<EventLogWrapper<CourseEventLog>> instance) throws MappingException {
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

    private XCourse map(CourseEventLog eventLog, String districtId) throws MappingException {
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