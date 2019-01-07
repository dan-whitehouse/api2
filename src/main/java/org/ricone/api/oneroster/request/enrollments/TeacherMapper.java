package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.StaffCourseSection;
import org.ricone.api.core.model.StudentCourseSection;
import org.ricone.api.core.model.wrapper.StaffCourseSectionWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherMapper {
    public TeacherMapper() {
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

        GUIDRef guidRefStudent = new GUIDRef();
        guidRefStudent.setHref("http://localhost:8080/ims/oneroster/v1p1/students/");
        guidRefStudent.setSourcedId(instance.getStaff().getStaffRefId());
        guidRefStudent.setType(GUIDType.teacher);

        GUIDRef guidRefClass = new GUIDRef();
        guidRefClass.setHref("http://localhost:8080/ims/oneroster/v1p1/classes/");
        guidRefClass.setSourcedId(instance.getCourseSection().getCourseSectionRefId());
        guidRefClass.setType(GUIDType.clazz);

        GUIDRef guidRefSchool = new GUIDRef();
        guidRefSchool.setHref("http://localhost:8080/ims/oneroster/v1p1/schools/");
        guidRefSchool.setSourcedId(instance.getCourseSection().getCourse().getSchool().getSchoolRefId());
        guidRefSchool.setType(GUIDType.org);

        enrollment.setUser(guidRefStudent);
        enrollment.setClass_(guidRefClass);
        enrollment.setSchool(guidRefSchool);
        enrollment.setRole(RoleType.teacher);
        enrollment.setPrimary(instance.getTeacherOfRecord());
        enrollment.setBeginDate(null);
        enrollment.setEndDate(null);

        return enrollment;
    }
}