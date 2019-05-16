package org.ricone.api.xpress.request.xStudent;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.StudentEventLog;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XStudentResponse;
import org.ricone.api.xpress.model.XStudentsResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("XPress:XStudents:XStudentService")
public class XStudentServiceImp implements XStudentService {
    @Autowired private XStudentDAO dao;
    @Autowired private XStudentMapper mapper;
    @Autowired private XStudentFilterer filterer;
    @Autowired private XStudentEventLogDAOImp eventLogDAO;
    @Autowired private XStudentEventLogMapper eventLogMapper;
    @Autowired private XStudentUsernamePasswordMapper usernamePasswordMapper;

    @Override
    public XStudentResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        StudentWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentResponse findByLocalId(ControllerData metadata, String localId) throws Exception {
        StudentWrapper instance = dao.findByLocalId(metadata, localId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentResponse findByStateId(ControllerData metadata, String localId) throws Exception {
        StudentWrapper instance = dao.findByStateId(metadata, localId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentsResponse findAll(ControllerData metadata) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StudentEventLog>> instance = eventLogDAO.findAll(metadata);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StudentWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StudentEventLog>> instance = eventLogDAO.findAllByLeaRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StudentWrapper> instance = dao.findAllByLeaRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        //ChangesSince
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StudentEventLog>> instance = eventLogDAO.findAllBySchoolRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        //Regular Request
        List<StudentWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }

        //AUPP
        if(metadata.hasAUPP()) {
            if(metadata.isGetAUPP()) {
                return usernamePasswordMapper.convert(instance, metadata, refId);
            }
            throw new NoContentException(); //Will catch things like createUsers *& deleteUsers
        }

        //Regular Request
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StudentEventLog>> instance = eventLogDAO.findAllByRosterRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StudentWrapper> instance = dao.findAllByRosterRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentsResponse findAllByStaff(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StudentEventLog>> instance = eventLogDAO.findAllByStaffRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StudentWrapper> instance = dao.findAllByStaffRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStudentsResponse findAllByContact(ControllerData metadata, String refId) throws Exception {
        if(metadata.hasChangesSinceMarker()) {
            List<EventLogWrapper<StudentEventLog>> instance = eventLogDAO.findAllByContactRefId(metadata, refId);
            if(CollectionUtils.isEmpty(instance)) {
                throw new NoContentException();
            }
            return filterer.apply(eventLogMapper.convert(instance), metadata);
        }

        List<StudentWrapper> instance = dao.findAllByContactRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}