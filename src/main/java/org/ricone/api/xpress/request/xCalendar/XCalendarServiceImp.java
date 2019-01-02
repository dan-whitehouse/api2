package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XCalendarResponse;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.request.xLea.XLeaDAO;
import org.ricone.api.xpress.request.xLea.XLeaFilterer;
import org.ricone.api.xpress.request.xLea.XLeaMapper;
import org.ricone.api.xpress.request.xLea.XLeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XCalendarServiceImp implements XCalendarService {
    @Autowired private XCalendarDAO dao;
    @Autowired private XCalendarMapper mapper;
    @Autowired private XCalendarFilterer filterer;

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
        List<SchoolCalendarWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCalendarsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        List<SchoolCalendarWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XCalendarsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        List<SchoolCalendarWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}