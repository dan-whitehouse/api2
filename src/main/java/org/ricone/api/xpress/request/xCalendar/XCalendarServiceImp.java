package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.CalendarEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.ricone.error.NoContentException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Transactional
@Service("XPress:XCalendars:XCalendarService")
public class XCalendarServiceImp implements XCalendarService {
    private final XCalendarDAO dao;
    private final XCalendarMapper mapper;
    private final XCalendarFilterer filterer;
    private final XCalendarEventLogDAO eventLogDAO;
    private final XCalendarEventLogMapper eventLogMapper;

    public XCalendarServiceImp(XCalendarDAO dao, XCalendarMapper mapper, XCalendarFilterer filterer, XCalendarEventLogDAO eventLogDAO, XCalendarEventLogMapper eventLogMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.filterer = filterer;
        this.eventLogDAO = eventLogDAO;
        this.eventLogMapper = eventLogMapper;
    }

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
            List<EventLogWrapper<CalendarEventLog>> instance = eventLogDAO.findAll(metadata);
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
            List<EventLogWrapper<CalendarEventLog>> instance = eventLogDAO.findAllByLeaRefId(metadata, refId);
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
            List<EventLogWrapper<CalendarEventLog>> instance = eventLogDAO.findAllByLeaRefId(metadata, refId);
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