package org.ricone.api.xpress.request.xSchool;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XSchoolFilter;
import org.springframework.stereotype.Component;


import java.util.Iterator;

@Component("XPress:XSchools:XSchoolFilterer")
public class XSchoolFilterer {
    public XSchoolFilterer() {
    }

    XSchoolsResponse apply(XSchoolsResponse response, ControllerData metaData) {
        Iterator<XSchool> iterator = response.getXSchools().getXSchool().iterator();
        while (iterator.hasNext()) {
            XSchool i = iterator.next();
            i = filter(i, FilterCache.getInstance().getXSchoolFilter(i.getDistrictId(), metaData.getApplication().getApp()));

            // Remove object from list if empty
            if (i.isEmptyObject()) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(response.getXSchools().getXSchool())) {
            return null;
        }
        return response;
    }

    XSchoolResponse apply(XSchoolResponse response, ControllerData metaData) {
        response.setXSchool(filter(response.getXSchool(), FilterCache.getInstance().getXSchoolFilter(response.getXSchool().getDistrictId(), metaData.getApplication().getApp())));
        if (response.getXSchool().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private XSchool filter(XSchool instance, XSchoolFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if(!filter.getSchoolName()) {
            instance.setSchoolName(null);
        }
        if(!filter.getLeaRefId()) {
            instance.setLeaRefId(null);
        }

        //Address
        if(instance.getAddress() != null && !instance.getAddress().isEmptyObject()) {
            if(!filter.getAddressaddressType()) {
                instance.getAddress().setAddressType(null);
            }
            if(!filter.getAddresscity()) {
                instance.getAddress().setCity(null);
            }
            if(!filter.getAddresscountryCode()) {
                instance.getAddress().setCountryCode(null);
            }
            if(!filter.getAddressline1()) {
                instance.getAddress().setLine1(null);
            }
            if(!filter.getAddressline2()) {
                instance.getAddress().setLine2(null);
            }
            if(!filter.getAddresspostalCode()) {
                instance.getAddress().setPostalCode(null);
            }
            if(!filter.getAddressstateProvince()) {
                instance.getAddress().setStateProvince(null);
            }
        }

        //Primary Phone Number
        if(instance.getPhoneNumber() != null && !instance.getPhoneNumber().isEmptyObject()) {
            if(!filter.getPhoneNumbernumber()) {
                instance.getPhoneNumber().setNumber(null);
            }
            if(!filter.getPhoneNumberphoneNumberType()) {
                instance.getPhoneNumber().setPhoneNumberType(null);
            }
            if(!filter.getPhoneNumberprimaryIndicator()) {
                instance.getPhoneNumber().setPrimaryIndicator(null);
            }
        }


        //Other Phone Numbers
        if (instance.getOtherPhoneNumbers() != null) {
            for (PhoneNumber i : instance.getOtherPhoneNumbers().getPhoneNumber()) {
                if(!filter.getOtherPhoneNumbersphoneNumbernumber()) {
                    i.setNumber(null);
                }
                if(!filter.getOtherPhoneNumbersphoneNumberphoneNumberType()) {
                    i.setPhoneNumberType(null);
                }
                if(!filter.getOtherPhoneNumbersphoneNumberprimaryIndicator()) {
                    i.setPrimaryIndicator(null);
                }
            }
        }

        //Grade Levels
        if(!filter.getGradeLevels()) {
            instance.setGradeLevels(null);
        }

        //Identifiers
        if(!filter.getLocalId()) {
            instance.setLocalId(null);
        }
        if(!filter.getStateProvinceId()) {
            instance.setStateProvinceId(null);
        }

        //Other Identifiers
        if(instance.getOtherIds() != null)
        for (OtherId i : instance.getOtherIds().getOtherId()) {
            if(!filter.getOtherIdsotherIdid()) {
                i.setId(null);
            }
            if(!filter.getOtherIdsotherIdtype()) {
                i.setType(null);
            }
        }
        return instance;
    }
}