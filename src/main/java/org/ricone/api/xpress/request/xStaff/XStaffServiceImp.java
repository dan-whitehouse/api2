package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.StaffEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.error.NoContentException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("XPress:XStaffs:XStaffService")
public class XStaffServiceImp implements XStaffService {
    private final XStaffDAO dao;
    private final XStaffMapper mapper;
    private final XStaffFilterer filterer;
    private final XStaffEventLogDAOImp eventLogDAO;
    private final XStaffEventLogMapper eventLogMapper;
    private final XStaffUsernamePasswordMapper usernamePasswordMapper;

    public XStaffServiceImp(XStaffDAO dao, XStaffMapper mapper, XStaffFilterer filterer, XStaffEventLogDAOImp eventLogDAO, XStaffEventLogMapper eventLogMapper, XStaffUsernamePasswordMapper usernamePasswordMapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.filterer = filterer;
        this.eventLogDAO = eventLogDAO;
        this.eventLogMapper = eventLogMapper;
        this.usernamePasswordMapper = usernamePasswordMapper;
    }

    @Override
    public XStaffResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        StaffWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffResponse findByLocalId(ControllerData metadata, String localId) throws Exception {
        StaffWrapper instance = dao.findByLocalId(metadata, localId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffResponse findByStateId(ControllerData metadata, String localId) throws Exception {
        StaffWrapper instance = dao.findByStateId(metadata, localId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAll(ControllerData metadata) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StaffEventLog>> instance = eventLogDAO.findAll(metadata);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StaffWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StaffEventLog>> instance = eventLogDAO.findAllByLeaRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StaffWrapper> instance = dao.findAllByLeaRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        //ChangesSince
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StaffEventLog>> instance = eventLogDAO.findAllBySchoolRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        //Regular Request
        List<StaffWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }

        //AUPP
        if(metadata.hasAUPP()) {
            if(metadata.isGetAUPP()) {
                return usernamePasswordMapper.convert(instance, metadata, refId);
            }
            throw new NoContentException();
        }

        //Regular Request
        return filterer.apply(mapper.convert(instance), metadata);
    }


    @Override
    public XStaffsResponse findAllByCourse(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StaffEventLog>> instance = eventLogDAO.findAllByCourseRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StaffWrapper> instance = dao.findAllByCourseRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StaffEventLog>> instance = eventLogDAO.findAllByRosterRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StaffWrapper> instance = dao.findAllByRosterRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }


    @Override
    public XStaffsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StaffEventLog>> instance = eventLogDAO.findAllByStudentRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StaffWrapper> instance = dao.findAllByStudentRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}