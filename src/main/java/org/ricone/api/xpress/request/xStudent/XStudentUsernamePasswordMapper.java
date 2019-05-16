package org.ricone.api.xpress.request.xStudent;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.aupp.UserPasswordGenerator;
import org.ricone.api.xpress.component.aupp.UsernamePasswordDAO;
import org.ricone.api.xpress.component.error.exception.ForbiddenException;
import org.ricone.api.xpress.model.AppProvisioningInfo;
import org.ricone.api.xpress.model.XStudent;
import org.ricone.api.xpress.model.XStudentsResponse;
import org.ricone.error.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XStudents:XStudentUsernamePasswordMapper")
public class XStudentUsernamePasswordMapper {
    @Autowired private UserPasswordGenerator generator;
    @Autowired private UsernamePasswordDAO dao;

    public XStudentUsernamePasswordMapper() {
    }

    public XStudentsResponse convert(List<StudentWrapper> instance, ControllerData metadata, String schoolRefId) throws Exception {
        // Needed to populate App Leas so metaData.getApp().getDistrictKVsBySchool isn't empty
        dao.initAppLeas(metadata, schoolRefId);

        //Grab The Correct KV Map (Could be for a District or School
        HashMap<String, String> kvMap = getKVs(metadata, schoolRefId);

        XStudentsResponse response = new XStudentsResponse();
        for (StudentWrapper wrapper : instance) {
            XStudent xStudent = map(metadata, kvMap, wrapper.getStudent());
            if (xStudent != null) {
                response.getXStudents().getXStudent().add(xStudent);
            }
        }

        if(CollectionUtils.isEmpty(response.getXStudents().getXStudent())) {
            throw new NoContentException();
        }
        return response;
    }

    private XStudent map(ControllerData metadata, HashMap<String, String> kvMap, Student student) {
        AppProvisioningInfo appProvisioningInfo = new AppProvisioningInfo();
        appProvisioningInfo.setLoginId(generator.getUsername(kvMap, student, null));
        appProvisioningInfo.setTempPassword(generator.getPassword(kvMap, student, metadata.getApplication().getApp().getId()));

        if(StringUtils.isEmpty(appProvisioningInfo.getLoginId()) || StringUtils.isEmpty(appProvisioningInfo.getTempPassword())) {
            return null;
        }

        XStudent xStudent = new XStudent();
        xStudent.setRefId(student.getStudentRefId());
        xStudent.setAppProvisioningInfo(appProvisioningInfo);
        return xStudent;
    }

    private HashMap<String, String> getKVs(ControllerData metadata, String refId) throws ForbiddenException {
        HashMap<String, String> kvMap = metadata.getApplication().getApp().getSchoolKVsBySchool(refId);
        if(MapUtils.isEmpty(kvMap)) {
            kvMap = metadata.getApplication().getApp().getDistrictKVsBySchool(refId);
        }

        if(MapUtils.isEmpty(kvMap)) {
            throw new ForbiddenException("The district associated to this school has not been configured for account provisioning");
        }
        return kvMap;
    }
}