package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.XStaffFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XStaffs:XStaffFilterer")
public class XStaffFilterer {
    private final AppService cacheService;

    public XStaffFilterer(AppService cacheService) {
        this.cacheService = cacheService;
    }

    XStaffsResponse apply(XStaffsResponse response, ControllerData metadata) {
        //Filter All
        response.getXStaffs().getXStaff().forEach(xStaff -> {
            filter(xStaff, cacheService.getXStaffFilter(xStaff.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXStaffs().getXStaff().removeIf(XStaff::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXStaffs().getXStaff())) {
            return null;
        }
        return response;
    }

    XStaffResponse apply(XStaffResponse response, ControllerData metadata) {
        filter(response.getXStaff(), cacheService.getXStaffFilter(response.getXStaff().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXStaff().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private void filter(XStaff instance, XStaffFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if(!filter.getSex()) {
            instance.setSex(null);
        }

        //Name
        if(instance.getName() != null) {
            if(!filter.getNamefamilyName()) {
                instance.getName().setFamilyName(null);
            }
            if(!filter.getNamegivenName()) {
                instance.getName().setGivenName(null);
            }
            if(!filter.getNamemiddleName()) {
                instance.getName().setMiddleName(null);
            }
            if(!filter.getNameprefix()) {
                instance.getName().setPrefix(null);
            }
            if(!filter.getNamesuffix()) {
                instance.getName().setSuffix(null);
            }
            if(!filter.getNametype()) {
                instance.getName().setType(null);
            }

            // Remove object if empty
            if (instance.getName().isEmptyObject()) {
                instance.setName(null);
            }
        }

        //Email
        if(instance.getEmail() != null && !instance.getEmail().isEmptyObject()) {
            if(!filter.getEmailemailType()) {
                instance.getEmail().setEmailType(null);
            }
            if(!filter.getEmailemailAddress()) {
                instance.getEmail().setEmailAddress(null);
            }

            // Remove object if empty
            if (instance.getEmail().isEmptyObject()) {
                instance.setEmail(null);
            }
        }

        //Identifiers
        if(!filter.getLocalId()) {
            instance.setLocalId(null);
        }
        if(!filter.getStateProvinceId()) {
            instance.setStateProvinceId(null);
        }

        //Other Identifiers
        if(instance.getOtherIds() != null) {
            instance.getOtherIds().getOtherId().forEach(otherId -> {
                if(!filter.getOtherIdsotherIdid()) {
                    otherId.setId(null);
                }
                if(!filter.getOtherIdsotherIdtype()) {
                    otherId.setType(null);
                }
            });
            instance.getOtherIds().getOtherId().removeIf(OtherId::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherIds().getOtherId())) {
                instance.setOtherIds(null);
            }
        }

        //Assignments
        if(instance.getPrimaryAssignment() != null && !instance.getPrimaryAssignment().isEmptyObject()) {
            if(!filter.getPrimaryAssignmentleaRefId()) {
                instance.getPrimaryAssignment().setLeaRefId(null);
            }
            if(!filter.getPrimaryAssignmentschoolRefId()) {
                instance.getPrimaryAssignment().setSchoolRefId(null);
            }
            if(!filter.getPrimaryAssignmentjobFunction()) {
                instance.getPrimaryAssignment().setJobFunction(null);
            }

            // Remove object if empty
            if (instance.getPrimaryAssignment().isEmptyObject()) {
                instance.setPrimaryAssignment(null);
            }
        }

        //Other Assignments
        if(instance.getOtherAssignments() != null) {
            instance.getOtherAssignments().getStaffPersonAssignment().forEach(personAssignment -> {
                if(!filter.getOtherAssignmentsstaffPersonAssignmentleaRefId()) {
                    personAssignment.setLeaRefId(null);
                }
                if(!filter.getOtherAssignmentsstaffPersonAssignmentschoolRefId()) {
                    personAssignment.setSchoolRefId(null);
                }
                if(!filter.getOtherAssignmentsstaffPersonAssignmentjobFunction()) {
                    personAssignment.setJobFunction(null);
                }
            });

            instance.getOtherAssignments().getStaffPersonAssignment().removeIf(StaffPersonAssignment::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherAssignments().getStaffPersonAssignment())) {
                instance.setOtherAssignments(null);
            }
        }
    }
}