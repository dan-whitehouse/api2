package org.ricone.api.xpress.request.validation;

import org.ricone.api.core.model.Lea;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.validation.*;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-17
 */

@Component("RICOne:Validation:ValidationMapper")
public class ValidationMapper {

    public ValidationMapper() {
    }

    Validation mapLea(Lea lea, int studentCount, int studentEmailsCount, int studentLocalIdsCount, int staffCount, int staffEmailsCount, int staffLocalIdsCount) throws MappingException {
        try {
            Validation validation = new Validation(new Districts());
            District district = new District();
            district.setRefId(lea.getLeaRefId());
            district.setName(lea.getLeaName());

            Students students = new Students();
            students.setTotal(studentCount);
            students.setEmail(new Email(studentEmailsCount, getPercentage(studentCount, studentEmailsCount)));
            students.setLocalId(new LocalId(studentLocalIdsCount, getPercentage(studentCount, studentLocalIdsCount)));
            district.setStudents(students);

            Staff staffs = new Staff();
            staffs.setTotal(staffCount);
            staffs.setEmail(new Email(staffEmailsCount, getPercentage(staffCount, staffEmailsCount)));
            staffs.setLocalId(new LocalId(staffLocalIdsCount, getPercentage(staffCount, staffLocalIdsCount)));
            district.setStaff(staffs);

            district.setSchools(new Schools());
            validation.getDistricts().getDistrict().add(district);
            return validation;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new MappingException("Mapping Exception: " + ex.getLocalizedMessage());
        }
    }

    void mapSchool(Validation validation, org.ricone.api.core.model.School school, int studentEnrollmentPrimaryCount, int studentEnrollmentOtherCount, int staffAssignmentPrimaryCount, int staffAssignmentOtherCount, int courseCount, int courseSectionCount) {
        District district = validation.getDistricts().getDistrict().get(0);

        School sch = new School();
        sch.setRefId(school.getSchoolRefId());
        sch.setName(school.getSchoolName());

        sch.setStudentEnrollments(new StudentEnrollments(studentEnrollmentPrimaryCount, studentEnrollmentOtherCount));
        sch.setStaffAssignments(new StaffAssignments(staffAssignmentPrimaryCount, staffAssignmentOtherCount));
        sch.setCourses(courseCount);
        sch.setCourseSections(courseSectionCount);

        district.getSchools().getSchool().add(sch);
    }

    private String getPercentage(int total, int found) {
        float percent = (found * 100.0f) / total;
        return String.format("%.02f", percent) + "%";
    }
}