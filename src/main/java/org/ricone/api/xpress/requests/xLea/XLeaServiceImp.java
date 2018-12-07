package org.ricone.api.xpress.requests.xLea;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.error.exception.NoContentException;
import org.ricone.error.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XLeaServiceImp implements XLeaService {
    @Autowired private XLeaDAO dao;
    @Autowired private XLeaMapper mapper;
    @Autowired private XLeaFilterer filterer;

    @Override
    public XLeaResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        LeaWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XLeaResponse findByLocalId(ControllerData metadata, String localId) throws Exception {
        LeaWrapper instance = dao.findByLocalId(metadata, localId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XLeasResponse findAll(ControllerData metadata) throws Exception {
        List<LeaWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XLeasResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XLeasResponse findAllByCalendar(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllByCalendarRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XLeasResponse findAllByCourse(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllByCourseRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XLeasResponse findAllByRoster(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllByRosterRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XLeasResponse findAllByStaff(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllByStaffRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XLeasResponse findAllByStudent(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllByStudentRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XLeasResponse findAllByContact(ControllerData metadata, String refId) throws Exception {
        List<LeaWrapper> instance = dao.findAllByContactRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }
}