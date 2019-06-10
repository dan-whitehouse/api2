package org.ricone.api.xpress.request.xLea;

import java.util.Iterator;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XLeaFilter;
import org.ricone.init.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.1.0
 * @since 2018-02-16
 */

@Component("XPress:XLeas:XLeaFilterer")
public class XLeaFilterer {
	private final CacheService cacheService;

	public XLeaFilterer(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	XLeasResponse apply(XLeasResponse response, ControllerData metadata) {
		Iterator<XLea> iterator = response.getXLeas().getXLeas().iterator();
		while (iterator.hasNext()) {
			XLea i = iterator.next();
			i = filter(i, cacheService.getXLeaFilter(i.getDistrictId(), metadata.getApplication().getApp().getId()));

			// Remove object from list if empty
			if (i.isEmptyObject()) {
				iterator.remove();
			}
		}
		if (CollectionUtils.isEmpty(response.getXLeas().getXLeas())) {
			return null;
		}
		return response;
	}

	XLeaResponse apply(XLeaResponse response, ControllerData metadata) {
		response.setXLea(filter(response.getXLea(), cacheService.getXLeaFilter(response.getXLea().getDistrictId(), metadata.getApplication().getApp().getId())));
		if (response.getXLea().isEmptyObject()) {
			return null;
		}
		return response;
	}

	private XLea filter(XLea instance, XLeaFilter filter) {
		if (!filter.getRefId()) {
			instance.setRefId(null);
		}
		if (!filter.getLeaName()) {
			instance.setLeaName(null);
		}
		if (!filter.getLocalId()) {
			instance.setLocalId(null);
		}
		if (!filter.getStateProvinceId()) {
			instance.setStateProvinceId(null);
		}
		if (!filter.getNcesId()) {
			instance.setNcesId(null);
		}

		// Address
		if (instance.getAddress() != null && !instance.getAddress().isEmptyObject()) {
			if (!filter.getAddressaddressType()) {
				instance.getAddress().setAddressType(null);
			}
			if (!filter.getAddresscity()) {
				instance.getAddress().setCity(null);
			}
			if (!filter.getAddresscountryCode()) {
				instance.getAddress().setCountryCode(null);
			}
			if (!filter.getAddressline1()) {
				instance.getAddress().setLine1(null);
			}
			if (!filter.getAddressline2()) {
				instance.getAddress().setLine2(null);
			}
			if (!filter.getAddresspostalCode()) {
				instance.getAddress().setPostalCode(null);
			}
			if (!filter.getAddressstateProvince()) {
				instance.getAddress().setStateProvince(null);
			}

			// Remove object if empty
			if (instance.getAddress().isEmptyObject()) {
				instance.setAddress(null);
			}
		}

		// Primary Phone Number
		if (instance.getPhoneNumber() != null && !instance.getPhoneNumber().isEmptyObject()) {
			if (!filter.getPhoneNumbernumber()) {
				instance.getPhoneNumber().setNumber(null);
			}
			if (!filter.getPhoneNumberphoneNumberType()) {
				instance.getPhoneNumber().setPhoneNumberType(null);
			}
			if (!filter.getPhoneNumberprimaryIndicator()) {
				instance.getPhoneNumber().setPrimaryIndicator(null);
			}

			// Remove object if empty
			if (instance.getPhoneNumber().isEmptyObject()) {
				instance.setPhoneNumber(null);
			}
		}

		// Other Phone Numbers
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
		return instance;
	}
}