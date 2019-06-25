package org.ricone.api.xpress.request.xContact;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.AppService;
import org.ricone.config.model.XContactFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XContacts:XContactFilterer")
public class XContactFilterer {
    private final AppService cacheService;

    public XContactFilterer(AppService cacheService) {
        this.cacheService = cacheService;
    }

    XContactsResponse apply(XContactsResponse response, ControllerData metadata) {
        //Filter All
        response.getXContacts().getXContact().forEach(xContact -> {
            filter(xContact, cacheService.getXContactFilter(xContact.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXContacts().getXContact().removeIf(XContact::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXContacts().getXContact())) {
            return null;
        }
        return response;
    }

    XContactResponse apply(XContactResponse response, ControllerData metadata) {
        filter(response.getXContact(), cacheService.getXContactFilter(response.getXContact().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXContact().isEmptyObject()) {
            return null;
        }
        return response;
    }

    public XContact filter(XContact instance, XContactFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
        }
        if (!filter.getSex()) {
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

        //Other Names
        if(instance.getOtherNames() != null) {
            instance.getOtherNames().getName().forEach(name -> {
                if(!filter.getOtherNamesnamefamilyName()) {
                    name.setFamilyName(null);
                }
                if(!filter.getOtherNamesnamegivenName()) {
                    name.setGivenName(null);
                }
                if(!filter.getOtherNamesnamemiddleName()) {
                    name.setMiddleName(null);
                }
                if(!filter.getOtherNamesnameprefix()) {
                    name.setPrefix(null);
                }
                if(!filter.getOtherNamesnamesuffix()) {
                    name.setSuffix(null);
                }
                if(!filter.getOtherNamesnametype()) {
                    name.setType(null);
                }
            });
            instance.getOtherNames().getName().removeIf(Name::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherNames().getName())) {
                instance.setOtherNames(null);
            }
        }

        //Email
        if(instance.getEmail() != null) {
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

        //Other Email
        if(instance.getOtherEmails() != null) {
            instance.getOtherEmails().getEmail().forEach(email -> {
                if(!filter.getOtherEmailsemailemailType()) {
                    email.setEmailType(null);
                }
                if(!filter.getOtherEmailsemailemailAddress()) {
                    email.setEmailAddress(null);
                }
            });
            instance.getOtherEmails().getEmail().removeIf(Email::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherEmails().getEmail())) {
                instance.setOtherEmails(null);
            }
        }

        //Identifiers
        if(!filter.getLocalId()) {
            instance.setLocalId(null);
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

        //Address
        if(instance.getAddress() != null) {
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

        //Academic Summary
        if(instance.getRelationships() != null) {
            instance.getRelationships().getRelationship().forEach(relationship -> {
                if(!filter.getRelationshipsrelationshipcommunicationsIndicator()) {
                    relationship.setCommunicationsIndicator(null);
                }
                if(!filter.getRelationshipsrelationshipcontactSequence()) {
                    relationship.setContactSequence(null);
                }
                if(!filter.getRelationshipsrelationshipcustodialIndicator()) {
                    relationship.setCustodialIndicator(null);
                }
                if(!filter.getRelationshipsrelationshipemergencyContactIndicator()) {
                    relationship.setEmergencyContactIndicator(null);
                }
                if(!filter.getRelationshipsrelationshipfinancialResponsibilityIndicator()) {
                    relationship.setFinancialResponsibilityIndicator(null);
                }
                if(!filter.getRelationshipsrelationshiplivesWith()) {
                    relationship.setLivesWith(null);
                }
                if(!filter.getRelationshipsrelationshipprimaryContactIndicator()) {
                    relationship.setPrimaryContactIndicator(null);
                }
                if(!filter.getRelationshipsrelationshiprelationshipCode()) {
                    relationship.setRelationshipCode(null);
                }
                if(!filter.getRelationshipsrelationshiprestrictions()) {
                    relationship.setRestrictions(null);
                }
                if(!filter.getRelationshipsrelationshipstudentRefId()) {
                    relationship.setStudentRefId(null);
                }
            });
            instance.getRelationships().getRelationship().removeIf(Relationship::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getRelationships().getRelationship())) {
                instance.setRelationships(null);
            }
        }
        return instance;
    }
}