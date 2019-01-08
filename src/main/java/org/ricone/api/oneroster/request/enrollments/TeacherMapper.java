package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.StaffCourseSection;
import org.ricone.api.core.model.StudentCourseSection;
import org.ricone.api.core.model.wrapper.StaffCourseSectionWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Enrollments:TeacherMapper")
class TeacherMapper {
    TeacherMapper() {
    }

    EnrollmentsResponse convert(List<StaffCourseSectionWrapper> instance) {
        List<Enrollment> list = new ArrayList<>();
        for (StaffCourseSectionWrapper wrapper : instance) {
            Enrollment user = map(wrapper.getStaffCourseSection(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        EnrollmentsResponse response = new EnrollmentsResponse();
        response.setEnrollments(list);
        return response;
    }

    EnrollmentResponse convert(StaffCourseSectionWrapper wrapper) {
        if(wrapper != null) {
            EnrollmentResponse response = new EnrollmentResponse();
            response.setEnrollment(map(wrapper.getStaffCourseSection(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Enrollment map(StaffCourseSection instance, String districtId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSourcedId(instance.getStaffCourseSectionRefId());
        enrollment.setStatus(StatusType.active);
        enrollment.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStaffCourseSectionSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        enrollment.setMetadata(metadata);

        enrollment.setUser(MappingUtil.buildGUIDRef("students", instance.getStaff().getStaffRefId(), GUIDType.teacher));
        enrollment.setClass_(MappingUtil.buildGUIDRef("classes", instance.getStaff().getStaffRefId(), GUIDType.clazz));
        enrollment.setSchool(MappingUtil.buildGUIDRef("schools", instance.getStaff().getStaffRefId(), GUIDType.org));
        enrollment.setRole(RoleType.teacher);
        enrollment.setPrimary(instance.getTeacherOfRecord());
        enrollment.setBeginDate(null);
        enrollment.setEndDate(null);

        return enrollment;
    }
}