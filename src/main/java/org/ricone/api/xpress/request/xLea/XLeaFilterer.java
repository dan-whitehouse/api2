package org.ricone.api.xpress.request.xLea;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.PhoneNumber;
import org.ricone.api.xpress.model.XLea;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.config.cache.CacheService;
import org.ricone.config.model.XLeaFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XLeas:XLeaFilterer")
public class XLeaFilterer {
	private final CacheService cacheService;

	public XLeaFilterer(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	XLeasResponse apply(XLeasResponse response, ControllerData metadata) {
		//Filter All
		response.getXLeas().getXLeas().forEach(xLea -> {
			filter(xLea, cacheService.getXLeaFilter(xLea.getDistrictId(), metadata.getApplication().getApp().getId()));
		});

		//Remove All Empty Instances
		response.getXLeas().getXLeas().removeIf(XLea::isEmptyObject);

		if (CollectionUtils.isEmpty(response.getXLeas().getXLeas())) {
			return null;
		}
		return response;
	}

	XLeaResponse apply(XLeaResponse response, ControllerData metadata) {
		filter(response.getXLea(), cacheService.getXLeaFilter(response.getXLea().getDistrictId(), metadata.getApplication().getApp().getId()));
		if (response.getXLea().isEmptyObject()) {
			return null;
		}
		return response;
	}

	private void filter(XLea instance, XLeaFilter filter) {
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
	}
}