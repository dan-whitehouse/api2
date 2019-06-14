package org.ricone.api.xpress.request.xSchool;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.CacheService;
import org.ricone.config.model.XSchoolFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XSchools:XSchoolFilterer")
public class XSchoolFilterer {
    private final CacheService cacheService;

    public XSchoolFilterer(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    XSchoolsResponse apply(XSchoolsResponse response, ControllerData metadata) {
        //Filter All
        response.getXSchools().getXSchool().forEach(xSchool -> {
            filter(xSchool, cacheService.getXSchoolFilter(xSchool.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXSchools().getXSchool().removeIf(XSchool::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXSchools().getXSchool())) {
            return null;
        }
        return response;
    }

    XSchoolResponse apply(XSchoolResponse response, ControllerData metadata) {
        filter(response.getXSchool(), cacheService.getXSchoolFilter(response.getXSchool().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXSchool().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private void filter(XSchool instance, XSchoolFilter filter) {
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

            // Remove object if empty
            if (instance.getAddress().isEmptyObject()) {
                instance.setAddress(null);
            }
        }

        //Primary Phone Number
        if(instance.getPhoneNumber() != null) {
            if(!filter.getPhoneNumbernumber()) {
                instance.getPhoneNumber().setNumber(null);
            }
            if(!filter.getPhoneNumberphoneNumberType()) {
                instance.getPhoneNumber().setPhoneNumberType(null);
            }
            if(!filter.getPhoneNumberprimaryIndicator()) {
                instance.getPhoneNumber().setPrimaryIndicator(null);
            }

            // Remove object if empty
            if (instance.getPhoneNumber().isEmptyObject()) {
                instance.setPhoneNumber(null);
            }
        }


        //Other Phone Numbers
        if(instance.getOtherPhoneNumbers() != null) {
            instance.getOtherPhoneNumbers().getPhoneNumber().forEach(phoneNumber -> {
                if (!filter.getOtherPhoneNumbersphoneNumbernumber()) {
                    phoneNumber.setNumber(null);
                }
                if (!filter.getOtherPhoneNumbersphoneNumberphoneNumberType()) {
                    phoneNumber.setPhoneNumberType(null);
                }
                if (!filter.getOtherPhoneNumbersphoneNumberprimaryIndicator()) {
                    phoneNumber.setPrimaryIndicator(null);
                }
            });
            instance.getOtherPhoneNumbers().getPhoneNumber().removeIf(PhoneNumber::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherPhoneNumbers().getPhoneNumber())) {
                instance.setOtherPhoneNumbers(null);
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
    }
}