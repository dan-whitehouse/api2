package org.ricone.api.oneroster.request.enrollments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Enrollments:EnrollmentMapper")
class EnrollmentMapper extends BaseMapper<EnrollmentView, Enrollment, EnrollmentsResponse, EnrollmentResponse> {
    private Logger logger = LogManager.getLogger(this.getClass());

    EnrollmentMapper() throws NoSuchMethodException {
        super(EnrollmentView.class, Enrollment.class, EnrollmentsResponse.class, EnrollmentResponse.class);
    }

    @Override protected Enrollment map(EnrollmentView instance) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSourcedId(instance.getSourcedId());
        enrollment.setStatus(StatusType.active);
        enrollment.setDateLastModified(null);
        enrollment.setMetadata(mapMetadata(instance));

        enrollment.setRole(RoleType.valueOf(instance.getRole()));
        enrollment.setPrimary(instance.getPrimary());
        enrollment.setBeginDate(instance.getBeginDate());   //TODO: Not available in core. Though maybe available in staging?
        enrollment.setEndDate(instance.getEndDate());       //TODO: Not available in core. Though maybe available in staging?

        enrollment.setUser(MappingUtil.buildGUIDRef("users", instance.getUserId(), GUIDType.valueOf(instance.getRole())));
        enrollment.setClass_(MappingUtil.buildGUIDRef("classes", instance.getClassId(), GUIDType.clazz));
        enrollment.setSchool(MappingUtil.buildGUIDRef("schools", instance.getOrgId(), GUIDType.org));
        return enrollment;
    }

    @Override protected Metadata mapMetadata(EnrollmentView instance) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", instance.getDistrictId());
        return metadata;
    }
}