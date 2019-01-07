package org.ricone.api.oneroster.request.enrollments;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.Student;
import org.ricone.api.core.model.StudentCourseSection;
import org.ricone.api.core.model.StudentEmail;
import org.ricone.api.core.model.StudentTelephone;
import org.ricone.api.core.model.wrapper.StudentCourseSectionWrapper;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.oneroster.model.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class StudentMapper {
    public StudentMapper() {
    }

    EnrollmentsResponse convert(List<StudentCourseSectionWrapper> instance) {
        List<Enrollment> list = new ArrayList<>();
        for (StudentCourseSectionWrapper wrapper : instance) {
            Enrollment user = map(wrapper.getStudentCourseSection(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        EnrollmentsResponse response = new EnrollmentsResponse();
        response.setEnrollments(list);
        return response;
    }

    EnrollmentResponse convert(StudentCourseSectionWrapper wrapper) {
        if(wrapper != null) {
            EnrollmentResponse response = new EnrollmentResponse();
            response.setEnrollment(map(wrapper.getStudentCourseSection(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private Enrollment map(StudentCourseSection instance, String districtId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSourcedId(instance.getStudentCourseSectionRefId());
        enrollment.setStatus(StatusType.active);
        enrollment.setDateLastModified(null);

        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStudentCourseSectionSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        enrollment.setMetadata(metadata);

        GUIDRef guidRefStudent = new GUIDRef();
        guidRefStudent.setHref("http://localhost:8080/ims/oneroster/v1p1/students/");
        guidRefStudent.setSourcedId(instance.getStudent().getStudentRefId());
        guidRefStudent.setType(GUIDType.student);

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
        enrollment.setRole(RoleType.student);
        enrollment.setPrimary(null); //Ignore, this isn't for student's
        enrollment.setBeginDate(null);
        enrollment.setEndDate(null);

        return enrollment;
    }
}