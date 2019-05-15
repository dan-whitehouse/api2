package org.ricone.api.xpress.request.xStudent;

import org.apache.commons.collections4.MapUtils;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.aupp.User;
import org.ricone.api.xpress.component.aupp.UserPasswordGenerator;
import org.ricone.api.xpress.model.*;
import org.ricone.api.xpress.request.xStaff.XStaffMapper;
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

    public XStudentUsernamePasswordMapper() {
    }

    public XStudentsResponse convert(List<StudentWrapper> instance, ControllerData metadata, String refId) {
        XStudentsResponse response = new XStudentsResponse();
        for (StudentWrapper wrapper : instance) {
            XStudent xStudent = map(wrapper.getStudent(), metadata, refId);
            if (xStudent != null) {
                response.getXStudents().getXStudent().add(xStudent);
            }
        }
        return response;
    }

    private XStudent map(Student student, ControllerData metadata, String refId) {
        AppProvisioningInfo appProvisioningInfo = new AppProvisioningInfo();
        appProvisioningInfo.setLoginId(generator.getUsername(getKVs(metadata, refId), student, null));
        appProvisioningInfo.setTempPassword(generator.getPassword(getKVs(metadata, refId), student, metadata.getApplication().getApp().getId()));

        XStudent xStudent = new XStudent();
        xStudent.setRefId(student.getStudentRefId());
        xStudent.setAppProvisioningInfo(appProvisioningInfo);
        return xStudent;
    }

    private HashMap<String, String> getKVs(ControllerData metadata, String refId) {
        HashMap<String, String> kvMap = metadata.getApplication().getApp().getSchoolKVsBySchool(refId);
        if(MapUtils.isEmpty(kvMap)) {
            kvMap = metadata.getApplication().getApp().getDistrictKVsBySchool(refId);
        }
        return kvMap;
    }
}