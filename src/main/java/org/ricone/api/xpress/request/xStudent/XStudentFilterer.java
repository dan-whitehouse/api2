package org.ricone.api.xpress.request.xStudent;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.*;
import org.ricone.config.cache.FilterCache;
import org.ricone.config.model.XStudentFilter;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component("XPress:XStudents:XStudentFilterer")
public class XStudentFilterer {
    public XStudentFilterer() {
    }

    XStudentsResponse apply(XStudentsResponse response, ControllerData metaData) {
        Iterator<XStudent> iterator = response.getXStudents().getXStudent().iterator();
        while (iterator.hasNext()) {
            XStudent i = iterator.next();
            i = filter(i, FilterCache.getInstance().getXStudentFilter(i.getDistrictId(), metaData.getApplication().getApp()));

            // Remove object from list if empty
            if (i.isEmptyObject()) {
                iterator.remove();
            }
        }
        if (CollectionUtils.isEmpty(response.getXStudents().getXStudent())) {
            return null;
        }
        return response;
    }

    XStudentResponse apply(XStudentResponse response, ControllerData metaData) {
        response.setXStudent(filter(response.getXStudent(), FilterCache.getInstance().getXStudentFilter(response.getXStudent().getDistrictId(), metaData.getApplication().getApp())));
        if (response.getXStudent().isEmptyObject()) {
            return null;
        }
        return response;
    }

    public XStudent filter(XStudent instance, XStudentFilter filter) {
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
        }

        //Other Names
        if(instance.getOtherNames() != null) {
            for (Name i : instance.getOtherNames().getName()) {
                if(!filter.getOtherNamesnamefamilyName()) {
                    i.setFamilyName(null);
                }
                if(!filter.getOtherNamesnamegivenName()) {
                    i.setGivenName(null);
                }
                if(!filter.getOtherNamesnamemiddleName()) {
                    i.setMiddleName(null);
                }
                if(!filter.getOtherNamesnameprefix()) {
                    i.setPrefix(null);
                }
                if(!filter.getOtherNamesnamesuffix()) {
                    i.setSuffix(null);
                }
                if(!filter.getOtherNamesnametype()) {
                    i.setType(null);
                }
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
        }

        //Email
        if(instance.getEmail() != null) {
            if(!filter.getEmailemailType()) {
                instance.getEmail().setEmailType(null);
            }
            if(!filter.getEmailemailAddress()) {
                instance.getEmail().setEmailAddress(null);
            }
        }

        //Other Email
        if(instance.getOtherEmails() != null) {
            for (Email i : instance.getOtherEmails().getEmail()) {
                if(!filter.getOtherEmailsemailemailType()) {
                    i.setEmailType(null);
                }
                if(!filter.getOtherEmailsemailemailAddress()) {
                    i.setEmailAddress(null);
                }
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
        if(instance.getOtherPhoneNumbers() != null) {
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


        //Enrollments
        if(instance.getEnrollment() != null) {
            filterEnrollment(instance.getEnrollment(), filter);
        }

        //Other Enrollments
        if(instance.getOtherEnrollments() != null) {
            for (Enrollment i : instance.getOtherEnrollments().getEnrollment()) {
                filterOtherEnrollment(i, filter);
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
        }

        //Languages
        if(instance.getLanguages() != null) {
            for (Language i : instance.getLanguages().getLanguage()) {
                if(!filter.getLanguageslanguagetype()) {
                    i.setType(null);
                }
                if(!filter.getLanguageslanguagecode()) {
                    i.setCode(null);
                }
            }
        }

        //Contacts
        if(!filter.getStudentContactscontactPersonRefId()) {
            instance.setStudentContacts(null);
        }

        return instance;
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

            for (OtherCode i : enrollment.getEntryType().getOtherCode()) {
                if(!filter.getEnrollmententryTypeotherCodeListcodesetName()) {
                    i.setCodesetName(null);
                }
                if(!filter.getEnrollmententryTypeotherCodeListcode()) {
                    i.setOtherCodeValue(null);
                }

            }
        }

        //Exit Type
        if(enrollment.getExitType() != null) {
            if(!filter.getEnrollmentexitTypecode()) {
                enrollment.getExitType().setCode(null);
            }

            for (OtherCode i : enrollment.getExitType().getOtherCode()) {
                if(!filter.getEnrollmentexitTypeotherCodeListcodesetName()) {
                    i.setCodesetName(null);
                }
                if(!filter.getEnrollmentexitTypeotherCodeListcode()) {
                    i.setOtherCodeValue(null);
                }

            }
        }

        //Home Room Teacher
        if(enrollment.getHomeRoomTeacher() != null && !enrollment.getHomeRoomTeacher().isEmptyObject()) {
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
        }

        //Counselor
        if(enrollment.getCounselor() != null && !enrollment.getCounselor().isEmptyObject()) {
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

            for (OtherCode i : enrollment.getEntryType().getOtherCode()) {
                if(!filter.getEnrollmententryTypeotherCodeListcodesetName()) {
                    i.setCodesetName(null);
                }
                if(!filter.getEnrollmententryTypeotherCodeListcode()) {
                    i.setOtherCodeValue(null);
                }
            }
        }

        //Exit Type
        if(enrollment.getExitType() != null) {
            if(!filter.getEnrollmentexitTypecode()) {
                enrollment.getExitType().setCode(null);
            }

            for (OtherCode i : enrollment.getExitType().getOtherCode()) {
                if(!filter.getEnrollmentexitTypeotherCodeListcodesetName()) {
                    i.setCodesetName(null);
                }
                if(!filter.getEnrollmentexitTypeotherCodeListcode()) {
                    i.setOtherCodeValue(null);
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
        }
    }
}