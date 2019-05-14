package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.CalendarEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.error.NoContentException;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("XPress:XCalendars:XCalendarService")
public class XCalendarServiceImp implements XCalendarService {
    @Autowired private XCalendarDAO dao;
    @Autowired private XCalendarMapper mapper;
    @Autowired private XCalendarFilterer filterer;
    @Autowired private XCalendarEventLogDAO eventLogDAO;
    @Autowired private XCalendarEventLogMapper eventLogMapper;

    @Override
    public XCalendarResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        SchoolCalendarWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCalendarsResponse findAll(ControllerData metadata) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<CalendarEventLog>> instance = eventLogDAO.findAll(metadata, metadata.getChangesSinceLocalDateTime());
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<SchoolCalendarWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCalendarsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<CalendarEventLog>> instance = eventLogDAO.findAllByLeaRefId(metadata, metadata.getChangesSinceLocalDateTime(), refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<SchoolCalendarWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCalendarsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {

        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<CalendarEventLog>> instance = eventLogDAO.findAllByLeaRefId(metadata, metadata.getChangesSinceLocalDateTime(), refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<SchoolCalendarWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}