package org.ricone.api.xpress.request.xCourse;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.ricone.api.xpress.model.XCourseResponse;
import org.ricone.api.xpress.model.XCoursesResponse;
import org.ricone.api.xpress.request.xCalendar.XCalendarDAO;
import org.ricone.api.xpress.request.xCalendar.XCalendarFilterer;
import org.ricone.api.xpress.request.xCalendar.XCalendarMapper;
import org.ricone.api.xpress.request.xCalendar.XCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XCourseServiceImp implements XCourseService {
    @Autowired private XCourseDAO dao;
    @Autowired private XCourseMapper mapper;
    @Autowired private XCourseFilterer filterer;

    @Override
    public XCourseResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        CourseWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCoursesResponse findAll(ControllerData metadata) throws Exception {
        List<CourseWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCoursesResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        List<CourseWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCoursesResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        List<CourseWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCoursesResponse findAllByRoster(ControllerData metadata, String refId) throws Exception {
        List<CourseWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}