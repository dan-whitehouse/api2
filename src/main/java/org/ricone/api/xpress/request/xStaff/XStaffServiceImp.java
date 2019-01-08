package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.model.XStaffResponse;
import org.ricone.api.xpress.model.XStaffsResponse;
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
public class XStaffServiceImp implements XStaffService {
    @Autowired private XStaffDAO dao;
    @Autowired private XStaffMapper mapper;
    @Autowired private XStaffFilterer filterer;

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
        List<StaffWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        List<StaffWrapper> instance = dao.findAllByLeaRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        List<StaffWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }


    @Override
    public XStaffsResponse findAllByCourse(ControllerData metadata, String refId) throws Exception {
        List<StaffWrapper> instance = dao.findAllByCourseRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XStaffsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception {
        List<StaffWrapper> instance = dao.findAllByRosterRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }


    @Override
    public XStaffsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception {
        List<StaffWrapper> instance = dao.findAllByStudentRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}