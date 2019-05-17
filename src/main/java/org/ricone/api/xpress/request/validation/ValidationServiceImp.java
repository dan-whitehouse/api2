package org.ricone.api.xpress.request.validation;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.validation.Validation;
import org.ricone.api.xpress.request.xLea.XLeaDAO;
import org.ricone.config.FilterService;
import org.ricone.error.NoContentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.LogManager;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-17
 */

@Transactional
@Service("RICOne:Validation:ValidationService")
public class ValidationServiceImp implements ValidationService {
    @Autowired private ValidationDAO dao;
    @Autowired private ValidationMapper mapper;
    @Autowired private XLeaDAO leaDAO;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Validation findByLeaRefId(ControllerData metadata, String refId) throws Exception {
        LeaWrapper wrapper = leaDAO.findByRefId(metadata, refId);
        if(wrapper == null) {
            throw new NoContentException();
        }

        //Get Counts For The District
        int studentCount = dao.countStudentsByLeaRefId(metadata, refId);
        int studentEmailsCount = dao.countStudentEmailsByLeaRefId(metadata, refId);
        int studentLocalIdsCount = dao.countStudentLocalIdsByLeaRefId(metadata, refId);
        int staffCount = dao.countStaffsByLeaRefId(metadata, refId);
        int staffEmailsCount = dao.countStaffEmailsByLeaRefId(metadata, refId);
        int staffLocalIdsCount = dao.countStaffLocalIdsByLeaRefId(metadata, refId);

        //Map The District Object With The District Level Counts
        Validation validation = mapper.mapLea(wrapper.getLea(), studentCount, studentEmailsCount, studentLocalIdsCount, staffCount, staffEmailsCount, staffLocalIdsCount);

        //Foreach School In Our LEA, Lets Get Counts & Map
        wrapper.getLea().getSchools().forEach(school -> {
            int studentEnrollmentPrimaryCount = dao.countStudentEnrollmentsPrimaryBySchoolRefId(metadata, school.getSchoolRefId());
            int studentEnrollmentOtherCount = dao.countStudentEnrollmentsOtherBySchoolRefId(metadata, school.getSchoolRefId());
            int staffAssignmentPrimaryCount = dao.countStaffAssignmentsPrimaryBySchoolRefId(metadata, school.getSchoolRefId());
            int staffAssignmentOtherCount = dao.countStaffAssignmentsOtherBySchoolRefId(metadata, school.getSchoolRefId());
            int courseCount = dao.countCoursesBySchoolRefId(metadata, school.getSchoolRefId());
            int courseSectionCount = dao.countCourseSectionsBySchoolRefId(metadata, school.getSchoolRefId());

            //Update Our Validation.Districts.District.Schools list with a School from the Lea.Schools
            mapper.mapSchool(validation, school, studentEnrollmentPrimaryCount, studentEnrollmentOtherCount, staffAssignmentPrimaryCount, staffAssignmentOtherCount, courseCount, courseSectionCount);
        });

        return validation;
    }
}