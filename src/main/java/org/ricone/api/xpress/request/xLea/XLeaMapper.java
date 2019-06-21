package org.ricone.api.xpress.request.xLea;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.LeaTelephone;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.BaseMapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XPress:XLeas:XLeaMapper")
public class XLeaMapper extends BaseMapper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public XLeaMapper() {
    }

    public XLeasResponse convert(List<LeaWrapper> instance) throws MappingException {
        List<XLea> list = new ArrayList<>();
        for (LeaWrapper wrapper : instance) {
            XLea xLea = map(wrapper.getLea(), wrapper.getDistrictId());
            if(xLea != null) {
                list.add(xLea);
            }
        }

        XLeasResponse response = new XLeasResponse();
        XLeas xLeas = new XLeas();
        xLeas.setXLeas(list);

        response.setXLeas(xLeas);
        return response;
    }

    public XLeaResponse convert(LeaWrapper wrapper) throws MappingException {
        XLeaResponse response = new XLeaResponse();
        response.setXLea(map(wrapper.getLea(), wrapper.getDistrictId()));
        return response;
    }

    public XLea map(Lea instance, String districtId) throws MappingException {
        try {
            XLea xLea = new XLea();
            xLea.setDistrictId(districtId); // Required By Wrapper
            xLea.setRefId(instance.getLeaRefId());
            xLea.setLeaName(instance.getLeaName());
            xLea.setLocalId(instance.getLeaId());
            xLea.setStateProvinceId(instance.getLeaSeaId());
            xLea.setNcesId(instance.getLeaNcesId());

            //Address
            Address address = mapAddress(instance);
            if(address != null) {
                xLea.setAddress(address);
            }

            //PhoneNumber - Primary
            List<PhoneNumber> phoneNumbers = new ArrayList<>();
            for (LeaTelephone telephone : instance.getLeaTelephones()) {
                PhoneNumber phone = mapPhone(telephone);
                if(phone != null) {
                    if(BooleanUtils.isTrue(telephone.getPrimaryTelephoneNumberIndicator())) {
                        xLea.setPhoneNumber(phone);
                    }
                    else {
                        phoneNumbers.add(phone);
                    }
                }
            }

            //PhoneNumbers - Other
            if(CollectionUtils.isNotEmpty(phoneNumbers)) {
                OtherPhoneNumbers otherPhoneNumbers = new OtherPhoneNumbers();
                otherPhoneNumbers.setPhoneNumber(phoneNumbers);
                xLea.setOtherPhoneNumbers(otherPhoneNumbers);
            }

            //Metadata
            xLea.setMetadata(mapMetadata(instance));

            return xLea;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new MappingException("Mapping Exception: " + ex.getLocalizedMessage());
        }
    }

    private Address mapAddress(Lea lea) {
        Address address = new Address();
        address.setAddressType(lea.getAddressType());
        address.setLine1(lea.getStreetNumberAndName());
        address.setLine2(lea.getLine2());
        address.setPostalCode(lea.getPostalCode());
        address.setCity(lea.getCity());
        address.setCountryCode(lea.getCountryCode());
        address.setStateProvince(lea.getStateCode());

        if(address.isEmptyObject()) {
            return null;
        }
        return address;
    }

    private PhoneNumber mapPhone(LeaTelephone telephone) {
        PhoneNumber phone = new PhoneNumber();
        phone.setNumber(telephone.getTelephoneNumber());
        phone.setPhoneNumberType(telephone.getTelephoneNumberTypeCode());
        phone.setPrimaryIndicator(BooleanUtils.toStringTrueFalse(telephone.getPrimaryTelephoneNumberIndicator()));

        if(phone.isEmptyObject()) {
            return null;
        }
        return phone;
    }

    private Metadata mapMetadata(Lea lea) {
        return new Metadata(lea.getLeaSchoolYear());
    }
}