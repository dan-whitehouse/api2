package org.ricone.api.oneroster.request.enrollments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QEnrollment;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component("OneRoster:Enrollments:EnrollmentMapper")
class EnrollmentMapper extends BaseMapper<QEnrollment, Enrollment, EnrollmentsResponse, EnrollmentResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    EnrollmentMapper() throws NoSuchMethodException {
        super(QEnrollment.class, Enrollment.class, EnrollmentsResponse.class, EnrollmentResponse.class);
    }

    @Override protected Enrollment map(QEnrollment instance) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSourcedId(instance.getSourcedId());
        enrollment.setStatus(StatusType.valueOf(instance.getStatus()));
        enrollment.setDateLastModified(instance.getDateLastModified().atZone(ZoneId.systemDefault()));
        enrollment.setMetadata(mapMetadata(instance));

        if(instance.getUser() != null) {
            enrollment.setRole(RoleType.valueOf(instance.getUser().getRole()));
            enrollment.setUser(MappingUtil.buildGUIDRef("users", instance.getUser().getSourcedId(), GUIDType.valueOf(instance.getUser().getRole())));
        }

        enrollment.setPrimary(instance.getPrimary());
        enrollment.setBeginDate(instance.getBeginDate());   //TODO: Not available in core. Though maybe available in staging?
        enrollment.setEndDate(instance.getEndDate());       //TODO: Not available in core. Though maybe available in staging?

        if(instance.getClazz() != null) {
            enrollment.setClass_(MappingUtil.buildGUIDRef("classes", instance.getClazz().getSourcedId(), GUIDType.clazz));
        }

        if(instance.getOrg() != null) {
            enrollment.setSchool(MappingUtil.buildGUIDRef("schools", instance.getOrg().getSourcedId(), GUIDType.org));
        }
        return enrollment;
    }

    @Override protected Metadata mapMetadata(QEnrollment instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}