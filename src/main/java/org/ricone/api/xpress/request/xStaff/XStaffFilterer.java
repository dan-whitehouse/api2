package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XStaffFilter;
import org.ricone.init.CacheService;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component("XPress:XStaffs:XStaffFilterer")
public class XStaffFilterer {
    private final CacheService cacheService;

    public XStaffFilterer(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    XStaffsResponse apply(XStaffsResponse response, ControllerData metadata) {
        Iterator<XStaff> iterator = response.getXStaffs().getXStaff().iterator();
        while (iterator.hasNext()) {
            XStaff i = iterator.next();
            i = filter(i, cacheService.getXStaffFilter(i.getDistrictId(), metadata.getApplication().getApp().getId()));

            // Remove object from list if empty
            if (i.isEmptyObject()) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(response.getXStaffs().getXStaff())) {
            return null;
        }
        return response;
    }

    XStaffResponse apply(XStaffResponse response, ControllerData metadata) {
        response.setXStaff(filter(response.getXStaff(), cacheService.getXStaffFilter(response.getXStaff().getDistrictId(), metadata.getApplication().getApp().getId())));
        if (response.getXStaff().isEmptyObject()) {
            return null;
        }
        return response;
    }

    public XStaff filter(XStaff instance, XStaffFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if(!filter.getSex()) {
            instance.setSex(null);
        }

        //Name
        if(instance.getName() != null && !instance.getName().isEmptyObject()) {
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
        }

        //Email
        if(instance.getEmail() != null && !instance.getEmail().isEmptyObject()) {
            if(!filter.getEmailemailType()) {
                instance.getEmail().setEmailType(null);
            }
            if(!filter.getEmailemailAddress()) {
                instance.getEmail().setEmailAddress(null);
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
            for (OtherId i : instance.getOtherIds().getOtherId()) {
                if(!filter.getOtherIdsotherIdid()) {
                    i.setId(null);
                }
                if(!filter.getOtherIdsotherIdtype()) {
                    i.setType(null);
                }
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
            if(!filter.getPrimaryAssignmentschoolRefId()) {
                instance.getPrimaryAssignment().setSchoolRefId(null);
            }
        }

        //Other Assignments
        if(instance.getOtherAssignments() != null) {
            for (StaffPersonAssignment i : instance.getOtherAssignments().getStaffPersonAssignment()) {
                if(!filter.getOtherAssignmentsstaffPersonAssignmentleaRefId()) {
                    i.setLeaRefId(null);
                }
                if(!filter.getOtherAssignmentsstaffPersonAssignmentschoolRefId()) {
                    i.setSchoolRefId(null);
                }
                if(!filter.getOtherAssignmentsstaffPersonAssignmentjobFunction()) {
                    i.setJobFunction(null);
                }
            }
        }
        return instance;
    }
}