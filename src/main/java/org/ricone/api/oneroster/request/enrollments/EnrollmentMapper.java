package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Enrollments:EnrollmentMapper")
class EnrollmentMapper {
    EnrollmentMapper() {
    }

    EnrollmentsResponse convert(List<EnrollmentView> instance) {
        List<Enrollment> list = new ArrayList<>();
        for (EnrollmentView wrapper : instance) {
            Enrollment user = map(wrapper, null);
            if(user != null) {
                list.add(user);
            }
        }

        EnrollmentsResponse response = new EnrollmentsResponse();
        response.setEnrollments(list);
        return response;
    }

    EnrollmentResponse convert(EnrollmentView wrapper) {
        if(wrapper != null) {
            EnrollmentResponse response = new EnrollmentResponse();
            response.setEnrollment(map(wrapper, null));
            return response;
        }
        return null;
    }

    private Enrollment map(EnrollmentView instance, String districtId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSourcedId(instance.getUserClassId());
        enrollment.setStatus(StatusType.active);
        enrollment.setDateLastModified(null);
        enrollment.setMetadata(mapMetadata(instance, districtId));

        enrollment.setRole(RoleType.valueOf(instance.getRole()));
        enrollment.setPrimary(instance.getPrimary());
        enrollment.setBeginDate(instance.getBeginDate());
        enrollment.setEndDate(instance.getEndDate());

        enrollment.setUser(MappingUtil.buildGUIDRef("users", instance.getUserId(), GUIDType.valueOf(instance.getRole())));
        enrollment.setClass_(MappingUtil.buildGUIDRef("classes", instance.getClassId(), GUIDType.clazz));
        enrollment.setSchool(MappingUtil.buildGUIDRef("schools", instance.getOrgId(), GUIDType.org));
        return enrollment;
    }

    private Metadata mapMetadata(EnrollmentView instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getUserClassSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}