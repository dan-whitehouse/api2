package org.ricone.api.xpress.request.xStudent;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.CacheService;
import org.ricone.config.model.XStudentFilter;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-06-14
 */

@Component("XPress:XStudents:XStudentFilterer")
public class XStudentFilterer {
    private final CacheService cacheService;

    public XStudentFilterer(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    XStudentsResponse apply(XStudentsResponse response, ControllerData metadata) {
        //Filter All
        response.getXStudents().getXStudent().forEach(xStudent -> {
            filter(xStudent, cacheService.getXStudentFilter(xStudent.getDistrictId(), metadata.getApplication().getApp().getId()));
        });

        //Remove All Empty Instances
        response.getXStudents().getXStudent().removeIf(XStudent::isEmptyObject);

        if (CollectionUtils.isEmpty(response.getXStudents().getXStudent())) {
            return null;
        }
        return response;
    }

    XStudentResponse apply(XStudentResponse response, ControllerData metadata) {
        filter(response.getXStudent(), cacheService.getXStudentFilter(response.getXStudent().getDistrictId(), metadata.getApplication().getApp().getId()));
        if (response.getXStudent().isEmptyObject()) {
            return null;
        }
        return response;
    }

    private void filter(XStudent instance, XStudentFilter filter) {
        if(!filter.getRefId()) {
            instance.setRefId(null);
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

        //Demographics
        if(instance.getDemographics() != null) {
            if(!filter.getDemographicsbirthDate()) {
                instance.getDemographics().setBirthDate(null);
            }
            if(!filter.getDemographicscountryOfBirth()) {
                instance.getDemographics().setCountryOfBirth(null);
            }
            if(!filter.getDemographicshispanicLatinoEthnicity()) {
                instance.getDemographics().setHispanicLatinoEthnicity(null);
            }
            if(!filter.getDemographicssex()) {
                instance.getDemographics().setSex(null);
            }
            if(!filter.getDemographicsusCitizenshipStatus()) {
                instance.getDemographics().setUsCitizenshipStatus(null);
            }
            if(!filter.getDemographicsraces()) {
                instance.getDemographics().setRaces(null);
            }

            // Remove object if empty
            if (instance.getDemographics().isEmptyObject()) {
                instance.setDemographics(null);
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


        //Enrollments
        if(instance.getEnrollment() != null) {
            filterEnrollment(instance.getEnrollment(), filter);

            // Remove object if empty
            if (instance.getEnrollment().isEmptyObject()) {
                instance.setEnrollment(null);
            }
        }

        //Other Enrollments
        if(instance.getOtherEnrollments() != null) {
            instance.getOtherEnrollments().getEnrollment().forEach(enrollment -> {
                filterOtherEnrollment(enrollment, filter);
            });
            instance.getOtherEnrollments().getEnrollment().removeIf(Enrollment::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getOtherEnrollments().getEnrollment())) {
                instance.setOtherEnrollments(null);
            }
        }

        //Academic Summary
        if(instance.getAcademicSummary() != null) {
            if(!filter.getAcademicSummaryclassRank()) {
                instance.getAcademicSummary().setClassRank(null);
            }
            if(!filter.getAcademicSummarytermWeightedGpa()) {
                instance.getAcademicSummary().setTermWeightedGpa(null);
            }
            if(!filter.getAcademicSummarycumulativeWeightedGpa()) {
                instance.getAcademicSummary().setCumulativeWeightedGpa(null);
            }

            // Remove object if empty
            if (instance.getAcademicSummary().isEmptyObject()) {
                instance.setAcademicSummary(null);
            }
        }

        //Languages
        if(instance.getLanguages() != null) {
            instance.getLanguages().getLanguage().forEach(language -> {
                if(!filter.getLanguageslanguagetype()) {
                    language.setType(null);
                }
                if(!filter.getLanguageslanguagecode()) {
                    language.setCode(null);
                }
            });
            instance.getLanguages().getLanguage().removeIf(Language::isEmptyObject);

            if (CollectionUtils.isEmpty(instance.getLanguages().getLanguage())) {
                instance.setLanguages(null);
            }
        }

        //Contacts
        if(!filter.getStudentContactscontactPersonRefId()) {
            instance.setStudentContacts(null);
        }
    }


    private void filterEnrollment(Enrollment enrollment, XStudentFilter filter) {
        if(!filter.getEnrollmentstudentSchoolAssociationRefId()) {
            enrollment.setStudentSchoolAssociationRefId(null);
        }
        if(!filter.getEnrollmenthomeRoomNumber()) {
            enrollment.setHomeRoomNumber(null);
        }
        if(!filter.getEnrollmentgradeLevel()) {
            enrollment.setGradeLevel(null);
        }
        if(!filter.getEnrollmentmembershipType()) {
            enrollment.setMembershipType(null);
        }
        if(!filter.getEnrollmentprojectedGraduationYear()) {
            enrollment.setProjectedGraduationYear(null);
        }
        if(!filter.getEnrollmentresponsibleSchoolType()) {
            enrollment.setResponsibleSchoolType(null);
        }
        if(!filter.getEnrollmententryDate()) {
            enrollment.setEntryDate(null);
        }
        if(!filter.getEnrollmentexitDate()) {
            enrollment.setExitDate(null);
        }
        if(!filter.getEnrollmentschoolRefId()) {
            enrollment.setSchoolRefId(null);
        }
        if(!filter.getEnrollmentleaRefId()) {
            enrollment.setLeaRefId(null);
        }

        //Entry Type
        if(enrollment.getEntryType() != null) {
            if(!filter.getEnrollmententryTypecode()) {
                enrollment.getEntryType().setCode(null);
            }

            if(enrollment.getEntryType() != null) {
                enrollment.getEntryType().getOtherCode().forEach(code -> {
                    if(!filter.getEnrollmententryTypeotherCodeListcodesetName()) {
                        code.setCodesetName(null);
                    }
                    if(!filter.getEnrollmententryTypeotherCodeListcode()) {
                        code.setOtherCodeValue(null);
                    }
                });
                enrollment.getEntryType().getOtherCode().removeIf(OtherCode::isEmptyObject);

                if (CollectionUtils.isEmpty(enrollment.getEntryType().getOtherCode())) {
                    enrollment.setEntryType(null);
                }
            }
        }

        //Exit Type
        if(enrollment.getExitType() != null) {
            if(!filter.getEnrollmentexitTypecode()) {
                enrollment.getExitType().setCode(null);
            }

            if(enrollment.getExitType() != null) {
                enrollment.getExitType().getOtherCode().forEach(code -> {
                    if(!filter.getEnrollmentexitTypeotherCodeListcodesetName()) {
                        code.setCodesetName(null);
                    }
                    if(!filter.getEnrollmentexitTypeotherCodeListcode()) {
                        code.setOtherCodeValue(null);
                    }
                });
                enrollment.getExitType().getOtherCode().removeIf(OtherCode::isEmptyObject);

                if (CollectionUtils.isEmpty(enrollment.getExitType().getOtherCode())) {
                    enrollment.setExitType(null);
                }
            }
        }

        //Home Room Teacher
        if(enrollment.getHomeRoomTeacher() != null) {
            if(!filter.getEnrollmenthomeRoomTeacherrefId()) {
                enrollment.getHomeRoomTeacher().setRefId(null);
            }
            if(!filter.getEnrollmenthomeRoomTeacherlocalId()) {
                enrollment.getHomeRoomTeacher().setLocalId(null);
            }
            if(!filter.getEnrollmenthomeRoomTeachergivenName()) {
                enrollment.getHomeRoomTeacher().setGivenName(null);
            }
            if(!filter.getEnrollmenthomeRoomTeacherfamilyName()) {
                enrollment.getHomeRoomTeacher().setFamilyName(null);
            }

            // Remove object if empty
            if (enrollment.getHomeRoomTeacher().isEmptyObject()) {
                enrollment.setHomeRoomTeacher(null);
            }
        }

        //Counselor
        if(enrollment.getCounselor() != null) {
            if(!filter.getEnrollmentcounselorrefId()) {
                enrollment.getCounselor().setRefId(null);
            }
            if(!filter.getEnrollmentcounselorlocalId()) {
                enrollment.getCounselor().setLocalId(null);
            }
            if(!filter.getEnrollmentcounselorgivenName()) {
                enrollment.getCounselor().setGivenName(null);
            }
            if(!filter.getEnrollmentcounselorfamilyName()) {
                enrollment.getCounselor().setFamilyName(null);
            }

            // Remove object if empty
            if (enrollment.getCounselor().isEmptyObject()) {
                enrollment.setCounselor(null);
            }
        }
    }

    private void filterOtherEnrollment(Enrollment enrollment, XStudentFilter filter) {
        if(!filter.getOtherEnrollmentsenrollmentstudentSchoolAssociationRefId()) {
            enrollment.setStudentSchoolAssociationRefId(null);
        }
        if(!filter.getOtherEnrollmentsenrollmenthomeRoomNumber()) {
            enrollment.setHomeRoomNumber(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentgradeLevel()) {
            enrollment.setGradeLevel(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentmembershipType()) {
            enrollment.setMembershipType(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentprojectedGraduationYear()) {
            enrollment.setProjectedGraduationYear(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentresponsibleSchoolType()) {
            enrollment.setResponsibleSchoolType(null);
        }
        if(!filter.getOtherEnrollmentsenrollmententryDate()) {
            enrollment.setEntryDate(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentexitDate()) {
            enrollment.setExitDate(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentschoolRefId()) {
            enrollment.setSchoolRefId(null);
        }
        if(!filter.getOtherEnrollmentsenrollmentleaRefId()) {
            enrollment.setLeaRefId(null);
        }

        //Entry Type
        if(enrollment.getEntryType() != null) {
            if(!filter.getEnrollmententryTypecode()) {
                enrollment.getEntryType().setCode(null);
            }

            if(enrollment.getEntryType() != null) {
                enrollment.getEntryType().getOtherCode().forEach(code -> {
                    if(!filter.getEnrollmententryTypeotherCodeListcodesetName()) {
                        code.setCodesetName(null);
                    }
                    if(!filter.getEnrollmententryTypeotherCodeListcode()) {
                        code.setOtherCodeValue(null);
                    }
                });
                enrollment.getEntryType().getOtherCode().removeIf(OtherCode::isEmptyObject);

                if (CollectionUtils.isEmpty(enrollment.getEntryType().getOtherCode())) {
                    enrollment.setEntryType(null);
                }
            }
        }

        //Exit Type
        if(enrollment.getExitType() != null) {
            if(!filter.getEnrollmentexitTypecode()) {
                enrollment.getExitType().setCode(null);
            }

            if(enrollment.getExitType() != null) {
                enrollment.getExitType().getOtherCode().forEach(code -> {
                    if(!filter.getEnrollmentexitTypeotherCodeListcodesetName()) {
                        code.setCodesetName(null);
                    }
                    if(!filter.getEnrollmentexitTypeotherCodeListcode()) {
                        code.setOtherCodeValue(null);
                    }
                });
                enrollment.getExitType().getOtherCode().removeIf(OtherCode::isEmptyObject);

                if (CollectionUtils.isEmpty(enrollment.getExitType().getOtherCode())) {
                    enrollment.setExitType(null);
                }
            }
        }

        //Home Room Teacher
        if(enrollment.getHomeRoomTeacher() != null) {
            if(!filter.getOtherEnrollmentsenrollmenthomeRoomTeacherrefId()) {
                enrollment.getHomeRoomTeacher().setRefId(null);
            }
            if(!filter.getOtherEnrollmentsenrollmenthomeRoomTeacherlocalId()) {
                enrollment.getHomeRoomTeacher().setLocalId(null);
            }
            if(!filter.getOtherEnrollmentsenrollmenthomeRoomTeachergivenName()) {
                enrollment.getHomeRoomTeacher().setGivenName(null);
            }
            if(!filter.getOtherEnrollmentsenrollmenthomeRoomTeacherfamilyName()) {
                enrollment.getHomeRoomTeacher().setFamilyName(null);
            }

            // Remove object if empty
            if (enrollment.getHomeRoomTeacher().isEmptyObject()) {
                enrollment.setHomeRoomTeacher(null);
            }
        }

        //Counselor
        if(enrollment.getCounselor() != null) {
            if(!filter.getOtherEnrollmentsenrollmentcounselorrefId()) {
                enrollment.getCounselor().setRefId(null);
            }
            if(!filter.getOtherEnrollmentsenrollmentcounselorlocalId()) {
                enrollment.getCounselor().setLocalId(null);
            }
            if(!filter.getOtherEnrollmentsenrollmentcounselorgivenName()) {
                enrollment.getCounselor().setGivenName(null);
            }
            if(!filter.getOtherEnrollmentsenrollmentcounselorfamilyName()) {
                enrollment.getCounselor().setFamilyName(null);
            }

            // Remove object if empty
            if (enrollment.getCounselor().isEmptyObject()) {
                enrollment.setCounselor(null);
            }
        }
    }
}