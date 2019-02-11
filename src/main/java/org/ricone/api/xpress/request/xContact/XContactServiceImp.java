package org.ricone.api.xpress.request.xContact;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.error.NoContentException;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XContactResponse;
import org.ricone.api.xpress.model.XContactsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XContactServiceImp implements XContactService {
    @Autowired private XContactDAO dao;
    @Autowired private XContactMapper mapper;
    @Autowired private XContactFilterer filterer;

    @Override
    public XContactResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        StudentContactWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XContactsResponse findAll(ControllerData metadata) throws Exception {
        List<StudentContactWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return mapper.convert(instance);
    }

    @Override
    public XContactsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        List<StudentContactWrapper> instance = dao.findAllByLeaRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XContactsResponse findAllBySchool(ControllerData metadata, String refId) throws Exception {
        List<StudentContactWrapper> instance = dao.findAllBySchoolRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XContactsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception {
        List<StudentContactWrapper> instance = dao.findAllByStudentRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}