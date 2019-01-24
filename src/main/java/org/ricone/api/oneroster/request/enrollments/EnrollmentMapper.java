package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.oneroster.component.BaseMapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("OneRoster:Enrollments:EnrollmentMapper")
class EnrollmentMapper extends BaseMapper {
    EnrollmentMapper() {
    }

    EnrollmentsResponse convert(List<EnrollmentView> instance, ControllerData metadata) {
        List<Enrollment> list = new ArrayList<>();
        for (EnrollmentView wrapper : instance) {
            Enrollment user = map(wrapper, null);
            if(user != null) {
                list.add(user);
            }
        }

        EnrollmentsResponse response = new EnrollmentsResponse();
        response.setEnrollments(list);
        response.setStatusInfoSets(mapErrors(metadata, EnrollmentView.class, Enrollment.class));
        return response;
    }

    EnrollmentResponse convert(EnrollmentView wrapper, ControllerData metadata) {
        if(wrapper != null) {
            EnrollmentResponse response = new EnrollmentResponse();
            response.setEnrollment(map(wrapper, null));
            response.setStatusInfoSets(mapErrors(metadata, EnrollmentView.class, Enrollment.class));
            return response;
        }
        return null;
    }

    private Enrollment map(EnrollmentView instance, String districtId) {
        Enrollment enrollment = new Enrollment();
        enrollment.setSourcedId(instance.getSourcedId());
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
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getSourcedSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }
}