package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"refId", "nametype", "nameprefix", "namefamilyName", "namegivenName", "namemiddleName", "namesuffix", "otherNamesnametype", "otherNamesnameprefix", "otherNamesnamefamilyName", "otherNamesnamegivenName", "otherNamesnamemiddleName", "otherNamesnamesuffix", "localId", "stateProvinceId", "otherIdsotherIdtype", "otherIdsotherIdid", "addressaddressType", "addressline1", "addressline2", "addresscity", "addressstateProvince", "addresspostalCode", "addresscountryCode", "phoneNumberphoneNumberType", "phoneNumbernumber", "phoneNumberprimaryIndicator", "otherPhoneNumbersphoneNumberphoneNumberType", "otherPhoneNumbersphoneNumbernumber", "otherPhoneNumbersphoneNumberprimaryIndicator", "emailemailType", "emailemailAddress", "otherEmailsemailemailType", "otherEmailsemailemailAddress", "demographicsraces", "demographicshispanicLatinoEthnicity", "demographicssex", "demographicsbirthDate", "demographicscountryOfBirth", "demographicsusCitizenshipStatus", "enrollmentleaRefId", "enrollmentschoolRefId", "enrollmentstudentSchoolAssociationRefId", "enrollmentresponsibleSchoolType", "enrollmentmembershipType", "enrollmententryDate", "enrollmentexitDate", "enrollmenthomeRoomNumber", "enrollmenthomeRoomTeacherrefId", "enrollmenthomeRoomTeacherlocalId", "enrollmenthomeRoomTeachergivenName", "enrollmenthomeRoomTeacherfamilyName", "enrollmentgradeLevel", "enrollmentprojectedGraduationYear", "enrollmentcounselorrefId", "enrollmentcounselorlocalId", "enrollmentcounselorgivenName", "enrollmentcounselorfamilyName", "enrollmententryTypecode", "enrollmententryTypecodesetName", "enrollmententryTypeotherCodeListcode", "enrollmententryTypeotherCodeListcodesetName", "enrollmentexitTypecode", "enrollmentexitTypecodesetName", "enrollmentexitTypeotherCodeListcode", "enrollmentexitTypeotherCodeListcodesetName", "otherEnrollmentsenrollmentleaRefId", "otherEnrollmentsenrollmentschoolRefId", "otherEnrollmentsenrollmentstudentSchoolAssociationRefId", "otherEnrollmentsenrollmentresponsibleSchoolType", "otherEnrollmentsenrollmentmembershipType", "otherEnrollmentsenrollmententryDate", "otherEnrollmentsenrollmentexitDate", "otherEnrollmentsenrollmenthomeRoomNumber", "otherEnrollmentsenrollmenthomeRoomTeacherrefId", "otherEnrollmentsenrollmenthomeRoomTeacherlocalId", "otherEnrollmentsenrollmenthomeRoomTeachergivenName", "otherEnrollmentsenrollmenthomeRoomTeacherfamilyName", "otherEnrollmentsenrollmentgradeLevel", "otherEnrollmentsenrollmentprojectedGraduationYear", "otherEnrollmentsenrollmentcounselorrefId", "otherEnrollmentsenrollmentcounselorlocalId", "otherEnrollmentsenrollmentcounselorgivenName", "otherEnrollmentsenrollmentcounselorfamilyName", "academicSummarycumulativeWeightedGpa", "academicSummarytermWeightedGpa", "academicSummaryclassRank", "studentContactscontactPersonRefId", "languageslanguagetype", "languageslanguagecode", "id"})
public class XStudentFilter implements IFilter<XStudentFilter>, Serializable {

    @JsonProperty("refId")
    private Boolean refId;
    @JsonProperty("nametype")
    private Boolean nametype;
    @JsonProperty("nameprefix")
    private Boolean nameprefix;
    @JsonProperty("namefamilyName")
    private Boolean namefamilyName;
    @JsonProperty("namegivenName")
    private Boolean namegivenName;
    @JsonProperty("namemiddleName")
    private Boolean namemiddleName;
    @JsonProperty("namesuffix")
    private Boolean namesuffix;
    @JsonProperty("otherNamesnametype")
    private Boolean otherNamesnametype;
    @JsonProperty("otherNamesnameprefix")
    private Boolean otherNamesnameprefix;
    @JsonProperty("otherNamesnamefamilyName")
    private Boolean otherNamesnamefamilyName;
    @JsonProperty("otherNamesnamegivenName")
    private Boolean otherNamesnamegivenName;
    @JsonProperty("otherNamesnamemiddleName")
    private Boolean otherNamesnamemiddleName;
    @JsonProperty("otherNamesnamesuffix")
    private Boolean otherNamesnamesuffix;
    @JsonProperty("localId")
    private Boolean localId;
    @JsonProperty("stateProvinceId")
    private Boolean stateProvinceId;
    @JsonProperty("otherIdsotherIdtype")
    private Boolean otherIdsotherIdtype;
    @JsonProperty("otherIdsotherIdid")
    private Boolean otherIdsotherIdid;
    @JsonProperty("addressaddressType")
    private Boolean addressaddressType;
    @JsonProperty("addressline1")
    private Boolean addressline1;
    @JsonProperty("addressline2")
    private Boolean addressline2;
    @JsonProperty("addresscity")
    private Boolean addresscity;
    @JsonProperty("addressstateProvince")
    private Boolean addressstateProvince;
    @JsonProperty("addresspostalCode")
    private Boolean addresspostalCode;
    @JsonProperty("addresscountryCode")
    private Boolean addresscountryCode;
    @JsonProperty("phoneNumberphoneNumberType")
    private Boolean phoneNumberphoneNumberType;
    @JsonProperty("phoneNumbernumber")
    private Boolean phoneNumbernumber;
    @JsonProperty("phoneNumberprimaryIndicator")
    private Boolean phoneNumberprimaryIndicator;
    @JsonProperty("otherPhoneNumbersphoneNumberphoneNumberType")
    private Boolean otherPhoneNumbersphoneNumberphoneNumberType;
    @JsonProperty("otherPhoneNumbersphoneNumbernumber")
    private Boolean otherPhoneNumbersphoneNumbernumber;
    @JsonProperty("otherPhoneNumbersphoneNumberprimaryIndicator")
    private Boolean otherPhoneNumbersphoneNumberprimaryIndicator;
    @JsonProperty("emailemailType")
    private Boolean emailemailType;
    @JsonProperty("emailemailAddress")
    private Boolean emailemailAddress;
    @JsonProperty("otherEmailsemailemailType")
    private Boolean otherEmailsemailemailType;
    @JsonProperty("otherEmailsemailemailAddress")
    private Boolean otherEmailsemailemailAddress;
    @JsonProperty("demographicsraces")
    private Boolean demographicsraces;
    @JsonProperty("demographicshispanicLatinoEthnicity")
    private Boolean demographicshispanicLatinoEthnicity;
    @JsonProperty("demographicssex")
    private Boolean demographicssex;
    @JsonProperty("demographicsbirthDate")
    private Boolean demographicsbirthDate;
    @JsonProperty("demographicscountryOfBirth")
    private Boolean demographicscountryOfBirth;
    @JsonProperty("demographicsusCitizenshipStatus")
    private Boolean demographicsusCitizenshipStatus;
    @JsonProperty("enrollmentleaRefId")
    private Boolean enrollmentleaRefId;
    @JsonProperty("enrollmentschoolRefId")
    private Boolean enrollmentschoolRefId;
    @JsonProperty("enrollmentstudentSchoolAssociationRefId")
    private Boolean enrollmentstudentSchoolAssociationRefId;
    @JsonProperty("enrollmentresponsibleSchoolType")
    private Boolean enrollmentresponsibleSchoolType;
    @JsonProperty("enrollmentmembershipType")
    private Boolean enrollmentmembershipType;
    @JsonProperty("enrollmententryDate")
    private Boolean enrollmententryDate;
    @JsonProperty("enrollmentexitDate")
    private Boolean enrollmentexitDate;
    @JsonProperty("enrollmenthomeRoomNumber")
    private Boolean enrollmenthomeRoomNumber;
    @JsonProperty("enrollmenthomeRoomTeacherrefId")
    private Boolean enrollmenthomeRoomTeacherrefId;
    @JsonProperty("enrollmenthomeRoomTeacherlocalId")
    private Boolean enrollmenthomeRoomTeacherlocalId;
    @JsonProperty("enrollmenthomeRoomTeachergivenName")
    private Boolean enrollmenthomeRoomTeachergivenName;
    @JsonProperty("enrollmenthomeRoomTeacherfamilyName")
    private Boolean enrollmenthomeRoomTeacherfamilyName;
    @JsonProperty("enrollmentgradeLevel")
    private Boolean enrollmentgradeLevel;
    @JsonProperty("enrollmentprojectedGraduationYear")
    private Boolean enrollmentprojectedGraduationYear;
    @JsonProperty("enrollmentcounselorrefId")
    private Boolean enrollmentcounselorrefId;
    @JsonProperty("enrollmentcounselorlocalId")
    private Boolean enrollmentcounselorlocalId;
    @JsonProperty("enrollmentcounselorgivenName")
    private Boolean enrollmentcounselorgivenName;
    @JsonProperty("enrollmentcounselorfamilyName")
    private Boolean enrollmentcounselorfamilyName;
    @JsonProperty("enrollmententryTypecode")
    private Boolean enrollmententryTypecode;
    @JsonProperty("enrollmententryTypecodesetName")
    private Boolean enrollmententryTypecodesetName;
    @JsonProperty("enrollmententryTypeotherCodeListcode")
    private Boolean enrollmententryTypeotherCodeListcode;
    @JsonProperty("enrollmententryTypeotherCodeListcodesetName")
    private Boolean enrollmententryTypeotherCodeListcodesetName;
    @JsonProperty("enrollmentexitTypecode")
    private Boolean enrollmentexitTypecode;
    @JsonProperty("enrollmentexitTypecodesetName")
    private Boolean enrollmentexitTypecodesetName;
    @JsonProperty("enrollmentexitTypeotherCodeListcode")
    private Boolean enrollmentexitTypeotherCodeListcode;
    @JsonProperty("enrollmentexitTypeotherCodeListcodesetName")
    private Boolean enrollmentexitTypeotherCodeListcodesetName;
    @JsonProperty("otherEnrollmentsenrollmentleaRefId")
    private Boolean otherEnrollmentsenrollmentleaRefId;
    @JsonProperty("otherEnrollmentsenrollmentschoolRefId")
    private Boolean otherEnrollmentsenrollmentschoolRefId;
    @JsonProperty("otherEnrollmentsenrollmentstudentSchoolAssociationRefId")
    private Boolean otherEnrollmentsenrollmentstudentSchoolAssociationRefId;
    @JsonProperty("otherEnrollmentsenrollmentresponsibleSchoolType")
    private Boolean otherEnrollmentsenrollmentresponsibleSchoolType;
    @JsonProperty("otherEnrollmentsenrollmentmembershipType")
    private Boolean otherEnrollmentsenrollmentmembershipType;
    @JsonProperty("otherEnrollmentsenrollmententryDate")
    private Boolean otherEnrollmentsenrollmententryDate;
    @JsonProperty("otherEnrollmentsenrollmentexitDate")
    private Boolean otherEnrollmentsenrollmentexitDate;
    @JsonProperty("otherEnrollmentsenrollmenthomeRoomNumber")
    private Boolean otherEnrollmentsenrollmenthomeRoomNumber;
    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherrefId")
    private Boolean otherEnrollmentsenrollmenthomeRoomTeacherrefId;
    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherlocalId")
    private Boolean otherEnrollmentsenrollmenthomeRoomTeacherlocalId;
    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeachergivenName")
    private Boolean otherEnrollmentsenrollmenthomeRoomTeachergivenName;
    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherfamilyName")
    private Boolean otherEnrollmentsenrollmenthomeRoomTeacherfamilyName;
    @JsonProperty("otherEnrollmentsenrollmentgradeLevel")
    private Boolean otherEnrollmentsenrollmentgradeLevel;
    @JsonProperty("otherEnrollmentsenrollmentprojectedGraduationYear")
    private Boolean otherEnrollmentsenrollmentprojectedGraduationYear;
    @JsonProperty("otherEnrollmentsenrollmentcounselorrefId")
    private Boolean otherEnrollmentsenrollmentcounselorrefId;
    @JsonProperty("otherEnrollmentsenrollmentcounselorlocalId")
    private Boolean otherEnrollmentsenrollmentcounselorlocalId;
    @JsonProperty("otherEnrollmentsenrollmentcounselorgivenName")
    private Boolean otherEnrollmentsenrollmentcounselorgivenName;
    @JsonProperty("otherEnrollmentsenrollmentcounselorfamilyName")
    private Boolean otherEnrollmentsenrollmentcounselorfamilyName;
    @JsonProperty("academicSummarycumulativeWeightedGpa")
    private Boolean academicSummarycumulativeWeightedGpa;
    @JsonProperty("academicSummarytermWeightedGpa")
    private Boolean academicSummarytermWeightedGpa;
    @JsonProperty("academicSummaryclassRank")
    private Boolean academicSummaryclassRank;
    @JsonProperty("studentContactscontactPersonRefId")
    private Boolean studentContactscontactPersonRefId;
    @JsonProperty("languageslanguagetype")
    private Boolean languageslanguagetype;
    @JsonProperty("languageslanguagecode")
    private Boolean languageslanguagecode;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = -2905547540996030279L;

    /**
     * No args constructor for use in serialization
     */
    public XStudentFilter() {
        refId = true;
        nametype = true;
        nameprefix = true;
        namefamilyName = true;
        namegivenName = true;
        namemiddleName = true;
        namesuffix = true;
        otherNamesnametype = true;
        otherNamesnameprefix = true;
        otherNamesnamefamilyName = true;
        otherNamesnamegivenName = true;
        otherNamesnamemiddleName = true;
        otherNamesnamesuffix = true;
        localId = true;
        stateProvinceId = true;
        otherIdsotherIdtype = true;
        otherIdsotherIdid = true;
        addressaddressType = true;
        addressline1 = true;
        addressline2 = true;
        addresscity = true;
        addressstateProvince = true;
        addresspostalCode = true;
        addresscountryCode = true;
        phoneNumberphoneNumberType = true;
        phoneNumbernumber = true;
        phoneNumberprimaryIndicator = true;
        otherPhoneNumbersphoneNumberphoneNumberType = true;
        otherPhoneNumbersphoneNumbernumber = true;
        otherPhoneNumbersphoneNumberprimaryIndicator = true;
        emailemailType = true;
        emailemailAddress = true;
        otherEmailsemailemailType = true;
        otherEmailsemailemailAddress = true;
        demographicsraces = true;
        demographicshispanicLatinoEthnicity = true;
        demographicssex = true;
        demographicsbirthDate = true;
        demographicscountryOfBirth = true;
        demographicsusCitizenshipStatus = true;
        enrollmentleaRefId = true;
        enrollmentschoolRefId = true;
        enrollmentstudentSchoolAssociationRefId = true;
        enrollmentresponsibleSchoolType = true;
        enrollmentmembershipType = true;
        enrollmententryDate = true;
        enrollmentexitDate = true;
        enrollmenthomeRoomNumber = true;
        enrollmenthomeRoomTeacherrefId = true;
        enrollmenthomeRoomTeacherlocalId = true;
        enrollmenthomeRoomTeachergivenName = true;
        enrollmenthomeRoomTeacherfamilyName = true;
        enrollmentgradeLevel = true;
        enrollmentprojectedGraduationYear = true;
        enrollmentcounselorrefId = true;
        enrollmentcounselorlocalId = true;
        enrollmentcounselorgivenName = true;
        enrollmentcounselorfamilyName = true;
        enrollmententryTypecode = true;
        enrollmententryTypecodesetName = true;
        enrollmententryTypeotherCodeListcode = true;
        enrollmententryTypeotherCodeListcodesetName = true;
        enrollmentexitTypecode = true;
        enrollmentexitTypecodesetName = true;
        enrollmentexitTypeotherCodeListcode = true;
        enrollmentexitTypeotherCodeListcodesetName = true;
        otherEnrollmentsenrollmentleaRefId = true;
        otherEnrollmentsenrollmentschoolRefId = true;
        otherEnrollmentsenrollmentstudentSchoolAssociationRefId = true;
        otherEnrollmentsenrollmentresponsibleSchoolType = true;
        otherEnrollmentsenrollmentmembershipType = true;
        otherEnrollmentsenrollmententryDate = true;
        otherEnrollmentsenrollmentexitDate = true;
        otherEnrollmentsenrollmenthomeRoomNumber = true;
        otherEnrollmentsenrollmenthomeRoomTeacherrefId = true;
        otherEnrollmentsenrollmenthomeRoomTeacherlocalId = true;
        otherEnrollmentsenrollmenthomeRoomTeachergivenName = true;
        otherEnrollmentsenrollmenthomeRoomTeacherfamilyName = true;
        otherEnrollmentsenrollmentgradeLevel = true;
        otherEnrollmentsenrollmentprojectedGraduationYear = true;
        otherEnrollmentsenrollmentcounselorrefId = true;
        otherEnrollmentsenrollmentcounselorlocalId = true;
        otherEnrollmentsenrollmentcounselorgivenName = true;
        otherEnrollmentsenrollmentcounselorfamilyName = true;
        academicSummarycumulativeWeightedGpa = true;
        academicSummarytermWeightedGpa = true;
        academicSummaryclassRank = true;
        studentContactscontactPersonRefId = true;
        languageslanguagetype = true;
        languageslanguagecode = true;
    }

    /**
     * @param otherIdsotherIdtype
     * @param enrollmenthomeRoomTeacherrefId
     * @param otherEnrollmentsenrollmentstudentSchoolAssociationRefId
     * @param otherEnrollmentsenrollmentleaRefId
     * @param namesuffix
     * @param emailemailType
     * @param enrollmentexitTypeotherCodeListcode
     * @param addresscountryCode
     * @param otherPhoneNumbersphoneNumbernumber
     * @param demographicscountryOfBirth
     * @param otherNamesnameprefix
     * @param enrollmentexitDate
     * @param otherEnrollmentsenrollmentcounselorgivenName
     * @param enrollmentcounselorgivenName
     * @param otherEmailsemailemailType
     * @param otherEnrollmentsenrollmentcounselorfamilyName
     * @param otherEnrollmentsenrollmentexitDate
     * @param addresscity
     * @param enrollmentcounselorlocalId
     * @param nameprefix
     * @param otherEnrollmentsenrollmentcounselorrefId
     * @param enrollmententryDate
     * @param enrollmententryTypeotherCodeListcodesetName
     * @param otherEnrollmentsenrollmentcounselorlocalId
     * @param namefamilyName
     * @param enrollmententryTypecode
     * @param enrollmentresponsibleSchoolType
     * @param addressstateProvince
     * @param phoneNumbernumber
     * @param otherEnrollmentsenrollmentprojectedGraduationYear
     * @param otherEnrollmentsenrollmentmembershipType
     * @param enrollmenthomeRoomTeacherfamilyName
     * @param enrollmentexitTypecode
     * @param addressaddressType
     * @param otherEnrollmentsenrollmentgradeLevel
     * @param otherPhoneNumbersphoneNumberphoneNumberType
     * @param enrollmentstudentSchoolAssociationRefId
     * @param otherEnrollmentsenrollmenthomeRoomTeacherlocalId
     * @param phoneNumberphoneNumberType
     * @param demographicshispanicLatinoEthnicity
     * @param enrollmenthomeRoomTeachergivenName
     * @param enrollmententryTypecodesetName
     * @param addressline2
     * @param otherNamesnamegivenName
     * @param addressline1
     * @param academicSummarytermWeightedGpa
     * @param enrollmentgradeLevel
     * @param otherEnrollmentsenrollmenthomeRoomTeacherrefId
     * @param enrollmentexitTypeotherCodeListcodesetName
     * @param otherPhoneNumbersphoneNumberprimaryIndicator
     * @param academicSummarycumulativeWeightedGpa
     * @param otherEnrollmentsenrollmenthomeRoomTeacherfamilyName
     * @param addresspostalCode
     * @param otherNamesnamefamilyName
     * @param enrollmentleaRefId
     * @param otherIdsotherIdid
     * @param studentContactscontactPersonRefId
     * @param enrollmentcounselorrefId
     * @param namemiddleName
     * @param enrollmenthomeRoomNumber
     * @param languageslanguagetype
     * @param refId
     * @param otherEmailsemailemailAddress
     * @param localId
     * @param emailemailAddress
     * @param enrollmentschoolRefId
     * @param id
     * @param otherNamesnamesuffix
     * @param demographicssex
     * @param otherEnrollmentsenrollmentresponsibleSchoolType
     * @param stateProvinceId
     * @param enrollmentcounselorfamilyName
     * @param phoneNumberprimaryIndicator
     * @param otherEnrollmentsenrollmenthomeRoomTeachergivenName
     * @param nametype
     * @param namegivenName
     * @param otherEnrollmentsenrollmentschoolRefId
     * @param enrollmententryTypeotherCodeListcode
     * @param enrollmentprojectedGraduationYear
     * @param demographicsusCitizenshipStatus
     * @param languageslanguagecode
     * @param demographicsraces
     * @param enrollmentmembershipType
     * @param otherEnrollmentsenrollmenthomeRoomNumber
     * @param enrollmenthomeRoomTeacherlocalId
     * @param otherEnrollmentsenrollmententryDate
     * @param otherNamesnamemiddleName
     * @param otherNamesnametype
     * @param academicSummaryclassRank
     * @param enrollmentexitTypecodesetName
     * @param demographicsbirthDate
     */
    public XStudentFilter(Boolean refId, Boolean nametype, Boolean nameprefix, Boolean namefamilyName, Boolean namegivenName, Boolean namemiddleName, Boolean namesuffix, Boolean otherNamesnametype, Boolean otherNamesnameprefix, Boolean otherNamesnamefamilyName, Boolean otherNamesnamegivenName, Boolean otherNamesnamemiddleName, Boolean otherNamesnamesuffix, Boolean localId, Boolean stateProvinceId, Boolean otherIdsotherIdtype, Boolean otherIdsotherIdid, Boolean addressaddressType, Boolean addressline1, Boolean addressline2, Boolean addresscity, Boolean addressstateProvince, Boolean addresspostalCode, Boolean addresscountryCode, Boolean phoneNumberphoneNumberType, Boolean phoneNumbernumber, Boolean phoneNumberprimaryIndicator, Boolean otherPhoneNumbersphoneNumberphoneNumberType, Boolean otherPhoneNumbersphoneNumbernumber, Boolean otherPhoneNumbersphoneNumberprimaryIndicator, Boolean emailemailType, Boolean emailemailAddress, Boolean otherEmailsemailemailType, Boolean otherEmailsemailemailAddress, Boolean demographicsraces, Boolean demographicshispanicLatinoEthnicity, Boolean demographicssex, Boolean demographicsbirthDate, Boolean demographicscountryOfBirth, Boolean demographicsusCitizenshipStatus, Boolean enrollmentleaRefId, Boolean enrollmentschoolRefId, Boolean enrollmentstudentSchoolAssociationRefId, Boolean enrollmentresponsibleSchoolType, Boolean enrollmentmembershipType, Boolean enrollmententryDate, Boolean enrollmentexitDate, Boolean enrollmenthomeRoomNumber, Boolean enrollmenthomeRoomTeacherrefId, Boolean enrollmenthomeRoomTeacherlocalId, Boolean enrollmenthomeRoomTeachergivenName, Boolean enrollmenthomeRoomTeacherfamilyName, Boolean enrollmentgradeLevel, Boolean enrollmentprojectedGraduationYear, Boolean enrollmentcounselorrefId, Boolean enrollmentcounselorlocalId, Boolean enrollmentcounselorgivenName, Boolean enrollmentcounselorfamilyName, Boolean enrollmententryTypecode, Boolean enrollmententryTypecodesetName, Boolean enrollmententryTypeotherCodeListcode, Boolean enrollmententryTypeotherCodeListcodesetName, Boolean enrollmentexitTypecode, Boolean enrollmentexitTypecodesetName, Boolean enrollmentexitTypeotherCodeListcode, Boolean enrollmentexitTypeotherCodeListcodesetName, Boolean otherEnrollmentsenrollmentleaRefId, Boolean otherEnrollmentsenrollmentschoolRefId, Boolean otherEnrollmentsenrollmentstudentSchoolAssociationRefId, Boolean otherEnrollmentsenrollmentresponsibleSchoolType, Boolean otherEnrollmentsenrollmentmembershipType, Boolean otherEnrollmentsenrollmententryDate, Boolean otherEnrollmentsenrollmentexitDate, Boolean otherEnrollmentsenrollmenthomeRoomNumber, Boolean otherEnrollmentsenrollmenthomeRoomTeacherrefId, Boolean otherEnrollmentsenrollmenthomeRoomTeacherlocalId, Boolean otherEnrollmentsenrollmenthomeRoomTeachergivenName, Boolean otherEnrollmentsenrollmenthomeRoomTeacherfamilyName, Boolean otherEnrollmentsenrollmentgradeLevel, Boolean otherEnrollmentsenrollmentprojectedGraduationYear, Boolean otherEnrollmentsenrollmentcounselorrefId, Boolean otherEnrollmentsenrollmentcounselorlocalId, Boolean otherEnrollmentsenrollmentcounselorgivenName, Boolean otherEnrollmentsenrollmentcounselorfamilyName, Boolean academicSummarycumulativeWeightedGpa, Boolean academicSummarytermWeightedGpa, Boolean academicSummaryclassRank, Boolean studentContactscontactPersonRefId, Boolean languageslanguagetype, Boolean languageslanguagecode, Integer id) {
        super();
        this.refId = refId;
        this.nametype = nametype;
        this.nameprefix = nameprefix;
        this.namefamilyName = namefamilyName;
        this.namegivenName = namegivenName;
        this.namemiddleName = namemiddleName;
        this.namesuffix = namesuffix;
        this.otherNamesnametype = otherNamesnametype;
        this.otherNamesnameprefix = otherNamesnameprefix;
        this.otherNamesnamefamilyName = otherNamesnamefamilyName;
        this.otherNamesnamegivenName = otherNamesnamegivenName;
        this.otherNamesnamemiddleName = otherNamesnamemiddleName;
        this.otherNamesnamesuffix = otherNamesnamesuffix;
        this.localId = localId;
        this.stateProvinceId = stateProvinceId;
        this.otherIdsotherIdtype = otherIdsotherIdtype;
        this.otherIdsotherIdid = otherIdsotherIdid;
        this.addressaddressType = addressaddressType;
        this.addressline1 = addressline1;
        this.addressline2 = addressline2;
        this.addresscity = addresscity;
        this.addressstateProvince = addressstateProvince;
        this.addresspostalCode = addresspostalCode;
        this.addresscountryCode = addresscountryCode;
        this.phoneNumberphoneNumberType = phoneNumberphoneNumberType;
        this.phoneNumbernumber = phoneNumbernumber;
        this.phoneNumberprimaryIndicator = phoneNumberprimaryIndicator;
        this.otherPhoneNumbersphoneNumberphoneNumberType = otherPhoneNumbersphoneNumberphoneNumberType;
        this.otherPhoneNumbersphoneNumbernumber = otherPhoneNumbersphoneNumbernumber;
        this.otherPhoneNumbersphoneNumberprimaryIndicator = otherPhoneNumbersphoneNumberprimaryIndicator;
        this.emailemailType = emailemailType;
        this.emailemailAddress = emailemailAddress;
        this.otherEmailsemailemailType = otherEmailsemailemailType;
        this.otherEmailsemailemailAddress = otherEmailsemailemailAddress;
        this.demographicsraces = demographicsraces;
        this.demographicshispanicLatinoEthnicity = demographicshispanicLatinoEthnicity;
        this.demographicssex = demographicssex;
        this.demographicsbirthDate = demographicsbirthDate;
        this.demographicscountryOfBirth = demographicscountryOfBirth;
        this.demographicsusCitizenshipStatus = demographicsusCitizenshipStatus;
        this.enrollmentleaRefId = enrollmentleaRefId;
        this.enrollmentschoolRefId = enrollmentschoolRefId;
        this.enrollmentstudentSchoolAssociationRefId = enrollmentstudentSchoolAssociationRefId;
        this.enrollmentresponsibleSchoolType = enrollmentresponsibleSchoolType;
        this.enrollmentmembershipType = enrollmentmembershipType;
        this.enrollmententryDate = enrollmententryDate;
        this.enrollmentexitDate = enrollmentexitDate;
        this.enrollmenthomeRoomNumber = enrollmenthomeRoomNumber;
        this.enrollmenthomeRoomTeacherrefId = enrollmenthomeRoomTeacherrefId;
        this.enrollmenthomeRoomTeacherlocalId = enrollmenthomeRoomTeacherlocalId;
        this.enrollmenthomeRoomTeachergivenName = enrollmenthomeRoomTeachergivenName;
        this.enrollmenthomeRoomTeacherfamilyName = enrollmenthomeRoomTeacherfamilyName;
        this.enrollmentgradeLevel = enrollmentgradeLevel;
        this.enrollmentprojectedGraduationYear = enrollmentprojectedGraduationYear;
        this.enrollmentcounselorrefId = enrollmentcounselorrefId;
        this.enrollmentcounselorlocalId = enrollmentcounselorlocalId;
        this.enrollmentcounselorgivenName = enrollmentcounselorgivenName;
        this.enrollmentcounselorfamilyName = enrollmentcounselorfamilyName;
        this.enrollmententryTypecode = enrollmententryTypecode;
        this.enrollmententryTypecodesetName = enrollmententryTypecodesetName;
        this.enrollmententryTypeotherCodeListcode = enrollmententryTypeotherCodeListcode;
        this.enrollmententryTypeotherCodeListcodesetName = enrollmententryTypeotherCodeListcodesetName;
        this.enrollmentexitTypecode = enrollmentexitTypecode;
        this.enrollmentexitTypecodesetName = enrollmentexitTypecodesetName;
        this.enrollmentexitTypeotherCodeListcode = enrollmentexitTypeotherCodeListcode;
        this.enrollmentexitTypeotherCodeListcodesetName = enrollmentexitTypeotherCodeListcodesetName;
        this.otherEnrollmentsenrollmentleaRefId = otherEnrollmentsenrollmentleaRefId;
        this.otherEnrollmentsenrollmentschoolRefId = otherEnrollmentsenrollmentschoolRefId;
        this.otherEnrollmentsenrollmentstudentSchoolAssociationRefId = otherEnrollmentsenrollmentstudentSchoolAssociationRefId;
        this.otherEnrollmentsenrollmentresponsibleSchoolType = otherEnrollmentsenrollmentresponsibleSchoolType;
        this.otherEnrollmentsenrollmentmembershipType = otherEnrollmentsenrollmentmembershipType;
        this.otherEnrollmentsenrollmententryDate = otherEnrollmentsenrollmententryDate;
        this.otherEnrollmentsenrollmentexitDate = otherEnrollmentsenrollmentexitDate;
        this.otherEnrollmentsenrollmenthomeRoomNumber = otherEnrollmentsenrollmenthomeRoomNumber;
        this.otherEnrollmentsenrollmenthomeRoomTeacherrefId = otherEnrollmentsenrollmenthomeRoomTeacherrefId;
        this.otherEnrollmentsenrollmenthomeRoomTeacherlocalId = otherEnrollmentsenrollmenthomeRoomTeacherlocalId;
        this.otherEnrollmentsenrollmenthomeRoomTeachergivenName = otherEnrollmentsenrollmenthomeRoomTeachergivenName;
        this.otherEnrollmentsenrollmenthomeRoomTeacherfamilyName = otherEnrollmentsenrollmenthomeRoomTeacherfamilyName;
        this.otherEnrollmentsenrollmentgradeLevel = otherEnrollmentsenrollmentgradeLevel;
        this.otherEnrollmentsenrollmentprojectedGraduationYear = otherEnrollmentsenrollmentprojectedGraduationYear;
        this.otherEnrollmentsenrollmentcounselorrefId = otherEnrollmentsenrollmentcounselorrefId;
        this.otherEnrollmentsenrollmentcounselorlocalId = otherEnrollmentsenrollmentcounselorlocalId;
        this.otherEnrollmentsenrollmentcounselorgivenName = otherEnrollmentsenrollmentcounselorgivenName;
        this.otherEnrollmentsenrollmentcounselorfamilyName = otherEnrollmentsenrollmentcounselorfamilyName;
        this.academicSummarycumulativeWeightedGpa = academicSummarycumulativeWeightedGpa;
        this.academicSummarytermWeightedGpa = academicSummarytermWeightedGpa;
        this.academicSummaryclassRank = academicSummaryclassRank;
        this.studentContactscontactPersonRefId = studentContactscontactPersonRefId;
        this.languageslanguagetype = languageslanguagetype;
        this.languageslanguagecode = languageslanguagecode;
        this.id = id;
    }

    @JsonProperty("refId")
    public Boolean getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(Boolean refId) {
        this.refId = refId;
    }

    @JsonProperty("nametype")
    public Boolean getNametype() {
        return nametype;
    }

    @JsonProperty("nametype")
    public void setNametype(Boolean nametype) {
        this.nametype = nametype;
    }

    @JsonProperty("nameprefix")
    public Boolean getNameprefix() {
        return nameprefix;
    }

    @JsonProperty("nameprefix")
    public void setNameprefix(Boolean nameprefix) {
        this.nameprefix = nameprefix;
    }

    @JsonProperty("namefamilyName")
    public Boolean getNamefamilyName() {
        return namefamilyName;
    }

    @JsonProperty("namefamilyName")
    public void setNamefamilyName(Boolean namefamilyName) {
        this.namefamilyName = namefamilyName;
    }

    @JsonProperty("namegivenName")
    public Boolean getNamegivenName() {
        return namegivenName;
    }

    @JsonProperty("namegivenName")
    public void setNamegivenName(Boolean namegivenName) {
        this.namegivenName = namegivenName;
    }

    @JsonProperty("namemiddleName")
    public Boolean getNamemiddleName() {
        return namemiddleName;
    }

    @JsonProperty("namemiddleName")
    public void setNamemiddleName(Boolean namemiddleName) {
        this.namemiddleName = namemiddleName;
    }

    @JsonProperty("namesuffix")
    public Boolean getNamesuffix() {
        return namesuffix;
    }

    @JsonProperty("namesuffix")
    public void setNamesuffix(Boolean namesuffix) {
        this.namesuffix = namesuffix;
    }

    @JsonProperty("otherNamesnametype")
    public Boolean getOtherNamesnametype() {
        return otherNamesnametype;
    }

    @JsonProperty("otherNamesnametype")
    public void setOtherNamesnametype(Boolean otherNamesnametype) {
        this.otherNamesnametype = otherNamesnametype;
    }

    @JsonProperty("otherNamesnameprefix")
    public Boolean getOtherNamesnameprefix() {
        return otherNamesnameprefix;
    }

    @JsonProperty("otherNamesnameprefix")
    public void setOtherNamesnameprefix(Boolean otherNamesnameprefix) {
        this.otherNamesnameprefix = otherNamesnameprefix;
    }

    @JsonProperty("otherNamesnamefamilyName")
    public Boolean getOtherNamesnamefamilyName() {
        return otherNamesnamefamilyName;
    }

    @JsonProperty("otherNamesnamefamilyName")
    public void setOtherNamesnamefamilyName(Boolean otherNamesnamefamilyName) {
        this.otherNamesnamefamilyName = otherNamesnamefamilyName;
    }

    @JsonProperty("otherNamesnamegivenName")
    public Boolean getOtherNamesnamegivenName() {
        return otherNamesnamegivenName;
    }

    @JsonProperty("otherNamesnamegivenName")
    public void setOtherNamesnamegivenName(Boolean otherNamesnamegivenName) {
        this.otherNamesnamegivenName = otherNamesnamegivenName;
    }

    @JsonProperty("otherNamesnamemiddleName")
    public Boolean getOtherNamesnamemiddleName() {
        return otherNamesnamemiddleName;
    }

    @JsonProperty("otherNamesnamemiddleName")
    public void setOtherNamesnamemiddleName(Boolean otherNamesnamemiddleName) {
        this.otherNamesnamemiddleName = otherNamesnamemiddleName;
    }

    @JsonProperty("otherNamesnamesuffix")
    public Boolean getOtherNamesnamesuffix() {
        return otherNamesnamesuffix;
    }

    @JsonProperty("otherNamesnamesuffix")
    public void setOtherNamesnamesuffix(Boolean otherNamesnamesuffix) {
        this.otherNamesnamesuffix = otherNamesnamesuffix;
    }

    @JsonProperty("localId")
    public Boolean getLocalId() {
        return localId;
    }

    @JsonProperty("localId")
    public void setLocalId(Boolean localId) {
        this.localId = localId;
    }

    @JsonProperty("stateProvinceId")
    public Boolean getStateProvinceId() {
        return stateProvinceId;
    }

    @JsonProperty("stateProvinceId")
    public void setStateProvinceId(Boolean stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    @JsonProperty("otherIdsotherIdtype")
    public Boolean getOtherIdsotherIdtype() {
        return otherIdsotherIdtype;
    }

    @JsonProperty("otherIdsotherIdtype")
    public void setOtherIdsotherIdtype(Boolean otherIdsotherIdtype) {
        this.otherIdsotherIdtype = otherIdsotherIdtype;
    }

    @JsonProperty("otherIdsotherIdid")
    public Boolean getOtherIdsotherIdid() {
        return otherIdsotherIdid;
    }

    @JsonProperty("otherIdsotherIdid")
    public void setOtherIdsotherIdid(Boolean otherIdsotherIdid) {
        this.otherIdsotherIdid = otherIdsotherIdid;
    }

    @JsonProperty("addressaddressType")
    public Boolean getAddressaddressType() {
        return addressaddressType;
    }

    @JsonProperty("addressaddressType")
    public void setAddressaddressType(Boolean addressaddressType) {
        this.addressaddressType = addressaddressType;
    }

    @JsonProperty("addressline1")
    public Boolean getAddressline1() {
        return addressline1;
    }

    @JsonProperty("addressline1")
    public void setAddressline1(Boolean addressline1) {
        this.addressline1 = addressline1;
    }

    @JsonProperty("addressline2")
    public Boolean getAddressline2() {
        return addressline2;
    }

    @JsonProperty("addressline2")
    public void setAddressline2(Boolean addressline2) {
        this.addressline2 = addressline2;
    }

    @JsonProperty("addresscity")
    public Boolean getAddresscity() {
        return addresscity;
    }

    @JsonProperty("addresscity")
    public void setAddresscity(Boolean addresscity) {
        this.addresscity = addresscity;
    }

    @JsonProperty("addressstateProvince")
    public Boolean getAddressstateProvince() {
        return addressstateProvince;
    }

    @JsonProperty("addressstateProvince")
    public void setAddressstateProvince(Boolean addressstateProvince) {
        this.addressstateProvince = addressstateProvince;
    }

    @JsonProperty("addresspostalCode")
    public Boolean getAddresspostalCode() {
        return addresspostalCode;
    }

    @JsonProperty("addresspostalCode")
    public void setAddresspostalCode(Boolean addresspostalCode) {
        this.addresspostalCode = addresspostalCode;
    }

    @JsonProperty("addresscountryCode")
    public Boolean getAddresscountryCode() {
        return addresscountryCode;
    }

    @JsonProperty("addresscountryCode")
    public void setAddresscountryCode(Boolean addresscountryCode) {
        this.addresscountryCode = addresscountryCode;
    }

    @JsonProperty("phoneNumberphoneNumberType")
    public Boolean getPhoneNumberphoneNumberType() {
        return phoneNumberphoneNumberType;
    }

    @JsonProperty("phoneNumberphoneNumberType")
    public void setPhoneNumberphoneNumberType(Boolean phoneNumberphoneNumberType) {
        this.phoneNumberphoneNumberType = phoneNumberphoneNumberType;
    }

    @JsonProperty("phoneNumbernumber")
    public Boolean getPhoneNumbernumber() {
        return phoneNumbernumber;
    }

    @JsonProperty("phoneNumbernumber")
    public void setPhoneNumbernumber(Boolean phoneNumbernumber) {
        this.phoneNumbernumber = phoneNumbernumber;
    }

    @JsonProperty("phoneNumberprimaryIndicator")
    public Boolean getPhoneNumberprimaryIndicator() {
        return phoneNumberprimaryIndicator;
    }

    @JsonProperty("phoneNumberprimaryIndicator")
    public void setPhoneNumberprimaryIndicator(Boolean phoneNumberprimaryIndicator) {
        this.phoneNumberprimaryIndicator = phoneNumberprimaryIndicator;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberphoneNumberType")
    public Boolean getOtherPhoneNumbersphoneNumberphoneNumberType() {
        return otherPhoneNumbersphoneNumberphoneNumberType;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberphoneNumberType")
    public void setOtherPhoneNumbersphoneNumberphoneNumberType(Boolean otherPhoneNumbersphoneNumberphoneNumberType) {
        this.otherPhoneNumbersphoneNumberphoneNumberType = otherPhoneNumbersphoneNumberphoneNumberType;
    }

    @JsonProperty("otherPhoneNumbersphoneNumbernumber")
    public Boolean getOtherPhoneNumbersphoneNumbernumber() {
        return otherPhoneNumbersphoneNumbernumber;
    }

    @JsonProperty("otherPhoneNumbersphoneNumbernumber")
    public void setOtherPhoneNumbersphoneNumbernumber(Boolean otherPhoneNumbersphoneNumbernumber) {
        this.otherPhoneNumbersphoneNumbernumber = otherPhoneNumbersphoneNumbernumber;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberprimaryIndicator")
    public Boolean getOtherPhoneNumbersphoneNumberprimaryIndicator() {
        return otherPhoneNumbersphoneNumberprimaryIndicator;
    }

    @JsonProperty("otherPhoneNumbersphoneNumberprimaryIndicator")
    public void setOtherPhoneNumbersphoneNumberprimaryIndicator(Boolean otherPhoneNumbersphoneNumberprimaryIndicator) {
        this.otherPhoneNumbersphoneNumberprimaryIndicator = otherPhoneNumbersphoneNumberprimaryIndicator;
    }

    @JsonProperty("emailemailType")
    public Boolean getEmailemailType() {
        return emailemailType;
    }

    @JsonProperty("emailemailType")
    public void setEmailemailType(Boolean emailemailType) {
        this.emailemailType = emailemailType;
    }

    @JsonProperty("emailemailAddress")
    public Boolean getEmailemailAddress() {
        return emailemailAddress;
    }

    @JsonProperty("emailemailAddress")
    public void setEmailemailAddress(Boolean emailemailAddress) {
        this.emailemailAddress = emailemailAddress;
    }

    @JsonProperty("otherEmailsemailemailType")
    public Boolean getOtherEmailsemailemailType() {
        return otherEmailsemailemailType;
    }

    @JsonProperty("otherEmailsemailemailType")
    public void setOtherEmailsemailemailType(Boolean otherEmailsemailemailType) {
        this.otherEmailsemailemailType = otherEmailsemailemailType;
    }

    @JsonProperty("otherEmailsemailemailAddress")
    public Boolean getOtherEmailsemailemailAddress() {
        return otherEmailsemailemailAddress;
    }

    @JsonProperty("otherEmailsemailemailAddress")
    public void setOtherEmailsemailemailAddress(Boolean otherEmailsemailemailAddress) {
        this.otherEmailsemailemailAddress = otherEmailsemailemailAddress;
    }

    @JsonProperty("demographicsraces")
    public Boolean getDemographicsraces() {
        return demographicsraces;
    }

    @JsonProperty("demographicsraces")
    public void setDemographicsraces(Boolean demographicsraces) {
        this.demographicsraces = demographicsraces;
    }

    @JsonProperty("demographicshispanicLatinoEthnicity")
    public Boolean getDemographicshispanicLatinoEthnicity() {
        return demographicshispanicLatinoEthnicity;
    }

    @JsonProperty("demographicshispanicLatinoEthnicity")
    public void setDemographicshispanicLatinoEthnicity(Boolean demographicshispanicLatinoEthnicity) {
        this.demographicshispanicLatinoEthnicity = demographicshispanicLatinoEthnicity;
    }

    @JsonProperty("demographicssex")
    public Boolean getDemographicssex() {
        return demographicssex;
    }

    @JsonProperty("demographicssex")
    public void setDemographicssex(Boolean demographicssex) {
        this.demographicssex = demographicssex;
    }

    @JsonProperty("demographicsbirthDate")
    public Boolean getDemographicsbirthDate() {
        return demographicsbirthDate;
    }

    @JsonProperty("demographicsbirthDate")
    public void setDemographicsbirthDate(Boolean demographicsbirthDate) {
        this.demographicsbirthDate = demographicsbirthDate;
    }

    @JsonProperty("demographicscountryOfBirth")
    public Boolean getDemographicscountryOfBirth() {
        return demographicscountryOfBirth;
    }

    @JsonProperty("demographicscountryOfBirth")
    public void setDemographicscountryOfBirth(Boolean demographicscountryOfBirth) {
        this.demographicscountryOfBirth = demographicscountryOfBirth;
    }

    @JsonProperty("demographicsusCitizenshipStatus")
    public Boolean getDemographicsusCitizenshipStatus() {
        return demographicsusCitizenshipStatus;
    }

    @JsonProperty("demographicsusCitizenshipStatus")
    public void setDemographicsusCitizenshipStatus(Boolean demographicsusCitizenshipStatus) {
        this.demographicsusCitizenshipStatus = demographicsusCitizenshipStatus;
    }

    @JsonProperty("enrollmentleaRefId")
    public Boolean getEnrollmentleaRefId() {
        return enrollmentleaRefId;
    }

    @JsonProperty("enrollmentleaRefId")
    public void setEnrollmentleaRefId(Boolean enrollmentleaRefId) {
        this.enrollmentleaRefId = enrollmentleaRefId;
    }

    @JsonProperty("enrollmentschoolRefId")
    public Boolean getEnrollmentschoolRefId() {
        return enrollmentschoolRefId;
    }

    @JsonProperty("enrollmentschoolRefId")
    public void setEnrollmentschoolRefId(Boolean enrollmentschoolRefId) {
        this.enrollmentschoolRefId = enrollmentschoolRefId;
    }

    @JsonProperty("enrollmentstudentSchoolAssociationRefId")
    public Boolean getEnrollmentstudentSchoolAssociationRefId() {
        return enrollmentstudentSchoolAssociationRefId;
    }

    @JsonProperty("enrollmentstudentSchoolAssociationRefId")
    public void setEnrollmentstudentSchoolAssociationRefId(Boolean enrollmentstudentSchoolAssociationRefId) {
        this.enrollmentstudentSchoolAssociationRefId = enrollmentstudentSchoolAssociationRefId;
    }

    @JsonProperty("enrollmentresponsibleSchoolType")
    public Boolean getEnrollmentresponsibleSchoolType() {
        return enrollmentresponsibleSchoolType;
    }

    @JsonProperty("enrollmentresponsibleSchoolType")
    public void setEnrollmentresponsibleSchoolType(Boolean enrollmentresponsibleSchoolType) {
        this.enrollmentresponsibleSchoolType = enrollmentresponsibleSchoolType;
    }

    @JsonProperty("enrollmentmembershipType")
    public Boolean getEnrollmentmembershipType() {
        return enrollmentmembershipType;
    }

    @JsonProperty("enrollmentmembershipType")
    public void setEnrollmentmembershipType(Boolean enrollmentmembershipType) {
        this.enrollmentmembershipType = enrollmentmembershipType;
    }

    @JsonProperty("enrollmententryDate")
    public Boolean getEnrollmententryDate() {
        return enrollmententryDate;
    }

    @JsonProperty("enrollmententryDate")
    public void setEnrollmententryDate(Boolean enrollmententryDate) {
        this.enrollmententryDate = enrollmententryDate;
    }

    @JsonProperty("enrollmentexitDate")
    public Boolean getEnrollmentexitDate() {
        return enrollmentexitDate;
    }

    @JsonProperty("enrollmentexitDate")
    public void setEnrollmentexitDate(Boolean enrollmentexitDate) {
        this.enrollmentexitDate = enrollmentexitDate;
    }

    @JsonProperty("enrollmenthomeRoomNumber")
    public Boolean getEnrollmenthomeRoomNumber() {
        return enrollmenthomeRoomNumber;
    }

    @JsonProperty("enrollmenthomeRoomNumber")
    public void setEnrollmenthomeRoomNumber(Boolean enrollmenthomeRoomNumber) {
        this.enrollmenthomeRoomNumber = enrollmenthomeRoomNumber;
    }

    @JsonProperty("enrollmenthomeRoomTeacherrefId")
    public Boolean getEnrollmenthomeRoomTeacherrefId() {
        return enrollmenthomeRoomTeacherrefId;
    }

    @JsonProperty("enrollmenthomeRoomTeacherrefId")
    public void setEnrollmenthomeRoomTeacherrefId(Boolean enrollmenthomeRoomTeacherrefId) {
        this.enrollmenthomeRoomTeacherrefId = enrollmenthomeRoomTeacherrefId;
    }

    @JsonProperty("enrollmenthomeRoomTeacherlocalId")
    public Boolean getEnrollmenthomeRoomTeacherlocalId() {
        return enrollmenthomeRoomTeacherlocalId;
    }

    @JsonProperty("enrollmenthomeRoomTeacherlocalId")
    public void setEnrollmenthomeRoomTeacherlocalId(Boolean enrollmenthomeRoomTeacherlocalId) {
        this.enrollmenthomeRoomTeacherlocalId = enrollmenthomeRoomTeacherlocalId;
    }

    @JsonProperty("enrollmenthomeRoomTeachergivenName")
    public Boolean getEnrollmenthomeRoomTeachergivenName() {
        return enrollmenthomeRoomTeachergivenName;
    }

    @JsonProperty("enrollmenthomeRoomTeachergivenName")
    public void setEnrollmenthomeRoomTeachergivenName(Boolean enrollmenthomeRoomTeachergivenName) {
        this.enrollmenthomeRoomTeachergivenName = enrollmenthomeRoomTeachergivenName;
    }

    @JsonProperty("enrollmenthomeRoomTeacherfamilyName")
    public Boolean getEnrollmenthomeRoomTeacherfamilyName() {
        return enrollmenthomeRoomTeacherfamilyName;
    }

    @JsonProperty("enrollmenthomeRoomTeacherfamilyName")
    public void setEnrollmenthomeRoomTeacherfamilyName(Boolean enrollmenthomeRoomTeacherfamilyName) {
        this.enrollmenthomeRoomTeacherfamilyName = enrollmenthomeRoomTeacherfamilyName;
    }

    @JsonProperty("enrollmentgradeLevel")
    public Boolean getEnrollmentgradeLevel() {
        return enrollmentgradeLevel;
    }

    @JsonProperty("enrollmentgradeLevel")
    public void setEnrollmentgradeLevel(Boolean enrollmentgradeLevel) {
        this.enrollmentgradeLevel = enrollmentgradeLevel;
    }

    @JsonProperty("enrollmentprojectedGraduationYear")
    public Boolean getEnrollmentprojectedGraduationYear() {
        return enrollmentprojectedGraduationYear;
    }

    @JsonProperty("enrollmentprojectedGraduationYear")
    public void setEnrollmentprojectedGraduationYear(Boolean enrollmentprojectedGraduationYear) {
        this.enrollmentprojectedGraduationYear = enrollmentprojectedGraduationYear;
    }

    @JsonProperty("enrollmentcounselorrefId")
    public Boolean getEnrollmentcounselorrefId() {
        return enrollmentcounselorrefId;
    }

    @JsonProperty("enrollmentcounselorrefId")
    public void setEnrollmentcounselorrefId(Boolean enrollmentcounselorrefId) {
        this.enrollmentcounselorrefId = enrollmentcounselorrefId;
    }

    @JsonProperty("enrollmentcounselorlocalId")
    public Boolean getEnrollmentcounselorlocalId() {
        return enrollmentcounselorlocalId;
    }

    @JsonProperty("enrollmentcounselorlocalId")
    public void setEnrollmentcounselorlocalId(Boolean enrollmentcounselorlocalId) {
        this.enrollmentcounselorlocalId = enrollmentcounselorlocalId;
    }

    @JsonProperty("enrollmentcounselorgivenName")
    public Boolean getEnrollmentcounselorgivenName() {
        return enrollmentcounselorgivenName;
    }

    @JsonProperty("enrollmentcounselorgivenName")
    public void setEnrollmentcounselorgivenName(Boolean enrollmentcounselorgivenName) {
        this.enrollmentcounselorgivenName = enrollmentcounselorgivenName;
    }

    @JsonProperty("enrollmentcounselorfamilyName")
    public Boolean getEnrollmentcounselorfamilyName() {
        return enrollmentcounselorfamilyName;
    }

    @JsonProperty("enrollmentcounselorfamilyName")
    public void setEnrollmentcounselorfamilyName(Boolean enrollmentcounselorfamilyName) {
        this.enrollmentcounselorfamilyName = enrollmentcounselorfamilyName;
    }

    @JsonProperty("enrollmententryTypecode")
    public Boolean getEnrollmententryTypecode() {
        return enrollmententryTypecode;
    }

    @JsonProperty("enrollmententryTypecode")
    public void setEnrollmententryTypecode(Boolean enrollmententryTypecode) {
        this.enrollmententryTypecode = enrollmententryTypecode;
    }

    @JsonProperty("enrollmententryTypecodesetName")
    public Boolean getEnrollmententryTypecodesetName() {
        return enrollmententryTypecodesetName;
    }

    @JsonProperty("enrollmententryTypecodesetName")
    public void setEnrollmententryTypecodesetName(Boolean enrollmententryTypecodesetName) {
        this.enrollmententryTypecodesetName = enrollmententryTypecodesetName;
    }

    @JsonProperty("enrollmententryTypeotherCodeListcode")
    public Boolean getEnrollmententryTypeotherCodeListcode() {
        return enrollmententryTypeotherCodeListcode;
    }

    @JsonProperty("enrollmententryTypeotherCodeListcode")
    public void setEnrollmententryTypeotherCodeListcode(Boolean enrollmententryTypeotherCodeListcode) {
        this.enrollmententryTypeotherCodeListcode = enrollmententryTypeotherCodeListcode;
    }

    @JsonProperty("enrollmententryTypeotherCodeListcodesetName")
    public Boolean getEnrollmententryTypeotherCodeListcodesetName() {
        return enrollmententryTypeotherCodeListcodesetName;
    }

    @JsonProperty("enrollmententryTypeotherCodeListcodesetName")
    public void setEnrollmententryTypeotherCodeListcodesetName(Boolean enrollmententryTypeotherCodeListcodesetName) {
        this.enrollmententryTypeotherCodeListcodesetName = enrollmententryTypeotherCodeListcodesetName;
    }

    @JsonProperty("enrollmentexitTypecode")
    public Boolean getEnrollmentexitTypecode() {
        return enrollmentexitTypecode;
    }

    @JsonProperty("enrollmentexitTypecode")
    public void setEnrollmentexitTypecode(Boolean enrollmentexitTypecode) {
        this.enrollmentexitTypecode = enrollmentexitTypecode;
    }

    @JsonProperty("enrollmentexitTypecodesetName")
    public Boolean getEnrollmentexitTypecodesetName() {
        return enrollmentexitTypecodesetName;
    }

    @JsonProperty("enrollmentexitTypecodesetName")
    public void setEnrollmentexitTypecodesetName(Boolean enrollmentexitTypecodesetName) {
        this.enrollmentexitTypecodesetName = enrollmentexitTypecodesetName;
    }

    @JsonProperty("enrollmentexitTypeotherCodeListcode")
    public Boolean getEnrollmentexitTypeotherCodeListcode() {
        return enrollmentexitTypeotherCodeListcode;
    }

    @JsonProperty("enrollmentexitTypeotherCodeListcode")
    public void setEnrollmentexitTypeotherCodeListcode(Boolean enrollmentexitTypeotherCodeListcode) {
        this.enrollmentexitTypeotherCodeListcode = enrollmentexitTypeotherCodeListcode;
    }

    @JsonProperty("enrollmentexitTypeotherCodeListcodesetName")
    public Boolean getEnrollmentexitTypeotherCodeListcodesetName() {
        return enrollmentexitTypeotherCodeListcodesetName;
    }

    @JsonProperty("enrollmentexitTypeotherCodeListcodesetName")
    public void setEnrollmentexitTypeotherCodeListcodesetName(Boolean enrollmentexitTypeotherCodeListcodesetName) {
        this.enrollmentexitTypeotherCodeListcodesetName = enrollmentexitTypeotherCodeListcodesetName;
    }

    @JsonProperty("otherEnrollmentsenrollmentleaRefId")
    public Boolean getOtherEnrollmentsenrollmentleaRefId() {
        return otherEnrollmentsenrollmentleaRefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentleaRefId")
    public void setOtherEnrollmentsenrollmentleaRefId(Boolean otherEnrollmentsenrollmentleaRefId) {
        this.otherEnrollmentsenrollmentleaRefId = otherEnrollmentsenrollmentleaRefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentschoolRefId")
    public Boolean getOtherEnrollmentsenrollmentschoolRefId() {
        return otherEnrollmentsenrollmentschoolRefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentschoolRefId")
    public void setOtherEnrollmentsenrollmentschoolRefId(Boolean otherEnrollmentsenrollmentschoolRefId) {
        this.otherEnrollmentsenrollmentschoolRefId = otherEnrollmentsenrollmentschoolRefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentstudentSchoolAssociationRefId")
    public Boolean getOtherEnrollmentsenrollmentstudentSchoolAssociationRefId() {
        return otherEnrollmentsenrollmentstudentSchoolAssociationRefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentstudentSchoolAssociationRefId")
    public void setOtherEnrollmentsenrollmentstudentSchoolAssociationRefId(Boolean otherEnrollmentsenrollmentstudentSchoolAssociationRefId) {
        this.otherEnrollmentsenrollmentstudentSchoolAssociationRefId = otherEnrollmentsenrollmentstudentSchoolAssociationRefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentresponsibleSchoolType")
    public Boolean getOtherEnrollmentsenrollmentresponsibleSchoolType() {
        return otherEnrollmentsenrollmentresponsibleSchoolType;
    }

    @JsonProperty("otherEnrollmentsenrollmentresponsibleSchoolType")
    public void setOtherEnrollmentsenrollmentresponsibleSchoolType(Boolean otherEnrollmentsenrollmentresponsibleSchoolType) {
        this.otherEnrollmentsenrollmentresponsibleSchoolType = otherEnrollmentsenrollmentresponsibleSchoolType;
    }

    @JsonProperty("otherEnrollmentsenrollmentmembershipType")
    public Boolean getOtherEnrollmentsenrollmentmembershipType() {
        return otherEnrollmentsenrollmentmembershipType;
    }

    @JsonProperty("otherEnrollmentsenrollmentmembershipType")
    public void setOtherEnrollmentsenrollmentmembershipType(Boolean otherEnrollmentsenrollmentmembershipType) {
        this.otherEnrollmentsenrollmentmembershipType = otherEnrollmentsenrollmentmembershipType;
    }

    @JsonProperty("otherEnrollmentsenrollmententryDate")
    public Boolean getOtherEnrollmentsenrollmententryDate() {
        return otherEnrollmentsenrollmententryDate;
    }

    @JsonProperty("otherEnrollmentsenrollmententryDate")
    public void setOtherEnrollmentsenrollmententryDate(Boolean otherEnrollmentsenrollmententryDate) {
        this.otherEnrollmentsenrollmententryDate = otherEnrollmentsenrollmententryDate;
    }

    @JsonProperty("otherEnrollmentsenrollmentexitDate")
    public Boolean getOtherEnrollmentsenrollmentexitDate() {
        return otherEnrollmentsenrollmentexitDate;
    }

    @JsonProperty("otherEnrollmentsenrollmentexitDate")
    public void setOtherEnrollmentsenrollmentexitDate(Boolean otherEnrollmentsenrollmentexitDate) {
        this.otherEnrollmentsenrollmentexitDate = otherEnrollmentsenrollmentexitDate;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomNumber")
    public Boolean getOtherEnrollmentsenrollmenthomeRoomNumber() {
        return otherEnrollmentsenrollmenthomeRoomNumber;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomNumber")
    public void setOtherEnrollmentsenrollmenthomeRoomNumber(Boolean otherEnrollmentsenrollmenthomeRoomNumber) {
        this.otherEnrollmentsenrollmenthomeRoomNumber = otherEnrollmentsenrollmenthomeRoomNumber;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherrefId")
    public Boolean getOtherEnrollmentsenrollmenthomeRoomTeacherrefId() {
        return otherEnrollmentsenrollmenthomeRoomTeacherrefId;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherrefId")
    public void setOtherEnrollmentsenrollmenthomeRoomTeacherrefId(Boolean otherEnrollmentsenrollmenthomeRoomTeacherrefId) {
        this.otherEnrollmentsenrollmenthomeRoomTeacherrefId = otherEnrollmentsenrollmenthomeRoomTeacherrefId;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherlocalId")
    public Boolean getOtherEnrollmentsenrollmenthomeRoomTeacherlocalId() {
        return otherEnrollmentsenrollmenthomeRoomTeacherlocalId;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherlocalId")
    public void setOtherEnrollmentsenrollmenthomeRoomTeacherlocalId(Boolean otherEnrollmentsenrollmenthomeRoomTeacherlocalId) {
        this.otherEnrollmentsenrollmenthomeRoomTeacherlocalId = otherEnrollmentsenrollmenthomeRoomTeacherlocalId;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeachergivenName")
    public Boolean getOtherEnrollmentsenrollmenthomeRoomTeachergivenName() {
        return otherEnrollmentsenrollmenthomeRoomTeachergivenName;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeachergivenName")
    public void setOtherEnrollmentsenrollmenthomeRoomTeachergivenName(Boolean otherEnrollmentsenrollmenthomeRoomTeachergivenName) {
        this.otherEnrollmentsenrollmenthomeRoomTeachergivenName = otherEnrollmentsenrollmenthomeRoomTeachergivenName;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherfamilyName")
    public Boolean getOtherEnrollmentsenrollmenthomeRoomTeacherfamilyName() {
        return otherEnrollmentsenrollmenthomeRoomTeacherfamilyName;
    }

    @JsonProperty("otherEnrollmentsenrollmenthomeRoomTeacherfamilyName")
    public void setOtherEnrollmentsenrollmenthomeRoomTeacherfamilyName(Boolean otherEnrollmentsenrollmenthomeRoomTeacherfamilyName) {
        this.otherEnrollmentsenrollmenthomeRoomTeacherfamilyName = otherEnrollmentsenrollmenthomeRoomTeacherfamilyName;
    }

    @JsonProperty("otherEnrollmentsenrollmentgradeLevel")
    public Boolean getOtherEnrollmentsenrollmentgradeLevel() {
        return otherEnrollmentsenrollmentgradeLevel;
    }

    @JsonProperty("otherEnrollmentsenrollmentgradeLevel")
    public void setOtherEnrollmentsenrollmentgradeLevel(Boolean otherEnrollmentsenrollmentgradeLevel) {
        this.otherEnrollmentsenrollmentgradeLevel = otherEnrollmentsenrollmentgradeLevel;
    }

    @JsonProperty("otherEnrollmentsenrollmentprojectedGraduationYear")
    public Boolean getOtherEnrollmentsenrollmentprojectedGraduationYear() {
        return otherEnrollmentsenrollmentprojectedGraduationYear;
    }

    @JsonProperty("otherEnrollmentsenrollmentprojectedGraduationYear")
    public void setOtherEnrollmentsenrollmentprojectedGraduationYear(Boolean otherEnrollmentsenrollmentprojectedGraduationYear) {
        this.otherEnrollmentsenrollmentprojectedGraduationYear = otherEnrollmentsenrollmentprojectedGraduationYear;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorrefId")
    public Boolean getOtherEnrollmentsenrollmentcounselorrefId() {
        return otherEnrollmentsenrollmentcounselorrefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorrefId")
    public void setOtherEnrollmentsenrollmentcounselorrefId(Boolean otherEnrollmentsenrollmentcounselorrefId) {
        this.otherEnrollmentsenrollmentcounselorrefId = otherEnrollmentsenrollmentcounselorrefId;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorlocalId")
    public Boolean getOtherEnrollmentsenrollmentcounselorlocalId() {
        return otherEnrollmentsenrollmentcounselorlocalId;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorlocalId")
    public void setOtherEnrollmentsenrollmentcounselorlocalId(Boolean otherEnrollmentsenrollmentcounselorlocalId) {
        this.otherEnrollmentsenrollmentcounselorlocalId = otherEnrollmentsenrollmentcounselorlocalId;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorgivenName")
    public Boolean getOtherEnrollmentsenrollmentcounselorgivenName() {
        return otherEnrollmentsenrollmentcounselorgivenName;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorgivenName")
    public void setOtherEnrollmentsenrollmentcounselorgivenName(Boolean otherEnrollmentsenrollmentcounselorgivenName) {
        this.otherEnrollmentsenrollmentcounselorgivenName = otherEnrollmentsenrollmentcounselorgivenName;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorfamilyName")
    public Boolean getOtherEnrollmentsenrollmentcounselorfamilyName() {
        return otherEnrollmentsenrollmentcounselorfamilyName;
    }

    @JsonProperty("otherEnrollmentsenrollmentcounselorfamilyName")
    public void setOtherEnrollmentsenrollmentcounselorfamilyName(Boolean otherEnrollmentsenrollmentcounselorfamilyName) {
        this.otherEnrollmentsenrollmentcounselorfamilyName = otherEnrollmentsenrollmentcounselorfamilyName;
    }

    @JsonProperty("academicSummarycumulativeWeightedGpa")
    public Boolean getAcademicSummarycumulativeWeightedGpa() {
        return academicSummarycumulativeWeightedGpa;
    }

    @JsonProperty("academicSummarycumulativeWeightedGpa")
    public void setAcademicSummarycumulativeWeightedGpa(Boolean academicSummarycumulativeWeightedGpa) {
        this.academicSummarycumulativeWeightedGpa = academicSummarycumulativeWeightedGpa;
    }

    @JsonProperty("academicSummarytermWeightedGpa")
    public Boolean getAcademicSummarytermWeightedGpa() {
        return academicSummarytermWeightedGpa;
    }

    @JsonProperty("academicSummarytermWeightedGpa")
    public void setAcademicSummarytermWeightedGpa(Boolean academicSummarytermWeightedGpa) {
        this.academicSummarytermWeightedGpa = academicSummarytermWeightedGpa;
    }

    @JsonProperty("academicSummaryclassRank")
    public Boolean getAcademicSummaryclassRank() {
        return academicSummaryclassRank;
    }

    @JsonProperty("academicSummaryclassRank")
    public void setAcademicSummaryclassRank(Boolean academicSummaryclassRank) {
        this.academicSummaryclassRank = academicSummaryclassRank;
    }

    @JsonProperty("studentContactscontactPersonRefId")
    public Boolean getStudentContactscontactPersonRefId() {
        return studentContactscontactPersonRefId;
    }

    @JsonProperty("studentContactscontactPersonRefId")
    public void setStudentContactscontactPersonRefId(Boolean studentContactscontactPersonRefId) {
        this.studentContactscontactPersonRefId = studentContactscontactPersonRefId;
    }

    @JsonProperty("languageslanguagetype")
    public Boolean getLanguageslanguagetype() {
        return languageslanguagetype;
    }

    @JsonProperty("languageslanguagetype")
    public void setLanguageslanguagetype(Boolean languageslanguagetype) {
        this.languageslanguagetype = languageslanguagetype;
    }

    @JsonProperty("languageslanguagecode")
    public Boolean getLanguageslanguagecode() {
        return languageslanguagecode;
    }

    @JsonProperty("languageslanguagecode")
    public void setLanguageslanguagecode(Boolean languageslanguagecode) {
        this.languageslanguagecode = languageslanguagecode;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

}