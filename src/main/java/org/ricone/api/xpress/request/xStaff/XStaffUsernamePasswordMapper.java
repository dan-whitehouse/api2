package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.MapUtils;
import org.ricone.api.core.model.Staff;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.aupp.User;
import org.ricone.api.xpress.component.aupp.UserPasswordGenerator;
import org.ricone.api.xpress.model.AppProvisioningInfo;
import org.ricone.api.xpress.model.XStaff;
import org.ricone.api.xpress.model.XStaffsResponse;
import org.ricone.config.cache.FilterCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired private UserPasswordGenerator generator;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public XStaffUsernamePasswordMapper() {
    }

    public XStaffsResponse convert(List<StaffWrapper> instance, ControllerData metadata, String refId) {
        XStaffsResponse response = new XStaffsResponse();
        for (StaffWrapper wrapper : instance) {
            XStaff xStaff = map(wrapper.getStaff(), metadata, refId);
            if (xStaff != null) {
                response.getXStaffs().getXStaff().add(xStaff);
            }
        }
        return response;
    }

    private XStaff map(Staff staff, ControllerData metadata, String refId) {
        AppProvisioningInfo appProvisioningInfo = new AppProvisioningInfo();
        appProvisioningInfo.setLoginId(generator.getUsername(getKVs(metadata, refId), staff, null));
        appProvisioningInfo.setTempPassword(generator.getPassword(getKVs(metadata, refId), staff, metadata.getApplication().getApp().getId()));

        XStaff xStaff = new XStaff();
        xStaff.setRefId(staff.getStaffRefId());
        xStaff.setAppProvisioningInfo(appProvisioningInfo);
        return xStaff;
    }

    private HashMap<String, String> getKVs(ControllerData metadata, String refId) {
        logger.debug("Getting AUPP School KV for: " + metadata.getApplication().getApp().getId());
        HashMap<String, String> kvMap = metadata.getApplication().getApp().getSchoolKVsBySchool(refId);
        if(MapUtils.isEmpty(kvMap)) {
            logger.debug("School KV Didn't work... getting District KV");
            kvMap = metadata.getApplication().getApp().getDistrictKVsBySchool(refId);
        }
        logger.debug("Did we get a KV? " + (kvMap != null));

        if(kvMap == null) {
            //TODO - Throw some kind of exception
        }

        return kvMap;
    }
}