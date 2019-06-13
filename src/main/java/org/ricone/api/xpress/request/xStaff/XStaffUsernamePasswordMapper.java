package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.Staff;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.aupp.UserPasswordGenerator;
import org.ricone.api.xpress.component.error.exception.ForbiddenException;
import org.ricone.api.xpress.model.AppProvisioningInfo;
import org.ricone.api.xpress.model.XStaff;
import org.ricone.api.xpress.model.XStaffs;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.error.NoContentException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Component("XPress:XStaffs:XStaffUsernamePasswordMapper")
public class XStaffUsernamePasswordMapper {
    private final UserPasswordGenerator generator;

    public XStaffUsernamePasswordMapper(UserPasswordGenerator generator) {
        this.generator = generator;
    }

    public XStaffsResponse convert(List<StaffWrapper> instance, ControllerData metadata, String schoolRefId) throws Exception{
        //Grab The Correct KV Map (Could be for a District or School
        HashMap<String, String> kvMap = getKVs(metadata, schoolRefId);

        XStaffsResponse response = new XStaffsResponse(new XStaffs());
        for (StaffWrapper wrapper : instance) {
            XStaff xStaff = map(metadata, kvMap, wrapper.getStaff());
            if (xStaff != null) {
                response.getXStaffs().getXStaff().add(xStaff);
            }
        }

        if(CollectionUtils.isEmpty(response.getXStaffs().getXStaff())) {
            throw new NoContentException();
        }
        return response;
    }

    private XStaff map(ControllerData metadata, HashMap<String, String> kvMap, Staff staff) {
        AppProvisioningInfo appProvisioningInfo = new AppProvisioningInfo();
        appProvisioningInfo.setLoginId(generator.getUsername(kvMap, staff, null));
        appProvisioningInfo.setTempPassword(generator.getPassword(kvMap, staff, metadata.getApplication().getApp().getId()));

        if(StringUtils.isEmpty(appProvisioningInfo.getLoginId()) || StringUtils.isEmpty(appProvisioningInfo.getTempPassword())) {
            return null;
        }

        XStaff xStaff = new XStaff();
        xStaff.setRefId(staff.getStaffRefId());
        xStaff.setAppProvisioningInfo(appProvisioningInfo);
        return xStaff;
    }

    private HashMap<String, String> getKVs(ControllerData metadata, String refId) throws ForbiddenException {
        HashMap<String, String> kvMap = metadata.getApplication().getSchoolKVsBySchoolRefId(refId);
        if(MapUtils.isEmpty(kvMap)) {
            kvMap = metadata.getApplication().getDistrictKVsBySchoolRefId(refId);
        }

        if(MapUtils.isEmpty(kvMap)) {
            throw new ForbiddenException("The district associated to this school has not been configured for account provisioning");
        }
        return kvMap;
    }
}