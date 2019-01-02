package org.ricone.api.xpress.request.xRoster;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XRosterResponse;
import org.ricone.api.xpress.model.XRostersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XRosterServiceImp implements XRosterService {
    @Autowired private XRosterDAO dao;
    @Autowired private XRosterMapper mapper;
    @Autowired private XRosterFilterer filterer;

    @Override
    public XRosterResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        CourseSectionWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }


    @Override
    public XRostersResponse findAll(ControllerData metadata) throws Exception {
        List<CourseSectionWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XRostersResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        List<CourseSectionWrapper> instance = dao.findAllByLeaRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XRostersResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        List<CourseSectionWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XRostersResponse findAllByCourse(ControllerData metadata, String refId) throws Exception {
        List<CourseSectionWrapper> instance = dao.findAllByCourseRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XRostersResponse findAllByStaff(ControllerData metadata, String refId) throws Exception {
        List<CourseSectionWrapper> instance = dao.findAllByStaffRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XRostersResponse findAllByStudent(ControllerData metadata, String refId) throws Exception {
        List<CourseSectionWrapper> instance = dao.findAllByStudentRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}