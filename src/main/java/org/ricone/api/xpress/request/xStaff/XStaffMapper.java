package org.ricone.api.xpress.request.xStaff;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.ricone.api.core.model.Staff;
import org.ricone.api.core.model.StaffAssignment;
import org.ricone.api.core.model.StaffEmail;
import org.ricone.api.core.model.StaffIdentifier;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.xpress.component.BaseMapper;
import org.ricone.api.xpress.component.error.exception.MappingException;
import org.ricone.api.xpress.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("XPress:XStaffs:XStaffMapper")
public class XStaffMapper extends BaseMapper {

    public XStaffMapper() {
    }

    public XStaffsResponse convert(List<StaffWrapper> instance) throws MappingException {
        List<XStaff> list = new ArrayList<>();
        for (StaffWrapper wrapper : instance) {
            XStaff xStaff = map(wrapper.getStaff(), wrapper.getDistrictId());
            if(xStaff != null) {
                list.add(xStaff);
            }
        }

        XStaffsResponse response = new XStaffsResponse();
        XStaffs xStaffs = new XStaffs();
        xStaffs.setXStaff(list);

        response.setXStaffs(xStaffs);
        return response;
    }

    public XStaffResponse convert(StaffWrapper instance) throws MappingException {
        XStaffResponse response = new XStaffResponse();
        XStaff xStaff = map(instance.getStaff(), instance.getDistrictId());
        if(xStaff != null) {
            response.setXStaff(xStaff);
        }
        return response;
    }


    public XStaff map(Staff instance, String districtId) throws MappingException {
        try {
            XStaff xStaff = new XStaff();
            xStaff.setDistrictId(districtId); //Required by Filtering
            xStaff.setRefId(instance.getStaffRefId());
            xStaff.setSex(instance.getSexCode());

            //Name
            Name name = mapName(instance);
            if(name != null) {
                xStaff.setName(name);
            }

            //Email
            for (StaffEmail staffEmail : instance.getStaffEmails()) {
                if(BooleanUtils.isTrue(staffEmail.getPrimaryEmailAddressIndicator())) {
                    Email email = mapEmail(staffEmail);
                    if(email != null) {
                        xStaff.setEmail(email);
                        break;
                    }
                }
            }

            //Identifiers
            List<OtherId> otherIdList = new ArrayList<>();
            for (StaffIdentifier id : instance.getStaffIdentifiers()) {
                if(LOCAL_ID.equals(id.getIdentificationSystemCode())) {
                    xStaff.setLocalId(id.getStaffId());
                }
                else if(STATE_ID.equals(id.getIdentificationSystemCode())) {
                    xStaff.setStateProvinceId(id.getStaffId());
                }
                else {
                    OtherId otherId = mapOtherId(id);
                    if(otherId != null) {
                        otherIdList.add(otherId);
                    }
                }
            }

            //Other Identifiers
            if(CollectionUtils.isNotEmpty(otherIdList)) {
                OtherIds otherIds = new OtherIds();
                otherIds.setOtherId(otherIdList);
                xStaff.setOtherIds(otherIds);
            }

            //Assignments
            List<StaffPersonAssignment> assignmentList = new ArrayList<>();
            for (StaffAssignment assignment : instance.getStaffAssignments()) {
                if(BooleanUtils.isTrue(assignment.getPrimaryAssignment())) {
                    PrimaryAssignment primaryAssignment = mapPrimaryAssignment(assignment);
                    if(primaryAssignment != null) {
                        xStaff.setPrimaryAssignment(primaryAssignment);
                    }
                }
                else {
                    StaffPersonAssignment staffPersonAssignment = mapOtherAssignment(assignment);
                    if(staffPersonAssignment != null) {
                        assignmentList.add(staffPersonAssignment);
                    }
                }
            }

            //Other Assignments
            if(CollectionUtils.isNotEmpty(assignmentList)) {
                OtherAssignments otherAssignments = new OtherAssignments();
                otherAssignments.setStaffPersonAssignment(assignmentList);
                xStaff.setOtherAssignments(otherAssignments);
            }

            //Metadata
            xStaff.setMetadata(mapMetadata(instance));

            return xStaff;
        }
        catch (Exception ex) {
            throw new MappingException();
        }
    }

    private OtherId mapOtherId(StaffIdentifier id) {
        OtherId otherId = new OtherId();
        otherId.setId(id.getStaffId());
        otherId.setType(id.getIdentificationSystemCode());

        if(otherId.isEmptyObject()) {
            return null;
        }
        return otherId;
    }

    private Name mapName(Staff instance) {
        Name name = new Name();
        name.setFamilyName(instance.getLastName());
        name.setGivenName(instance.getFirstName());
        name.setMiddleName(instance.getMiddleName());
        name.setPrefix(instance.getPrefix());
        name.setSuffix(instance.getGenerationCode());
        name.setType(instance.getType());

        if(name.isEmptyObject()) {
            return null;
        }
        return name;
    }

    private PrimaryAssignment mapPrimaryAssignment(StaffAssignment assignment) {
        PrimaryAssignment primaryAssignment = new PrimaryAssignment();
        primaryAssignment.setJobFunction(assignment.getPositionTitle());

        if(assignment.getSchool() != null) {
            primaryAssignment.setSchoolRefId(assignment.getSchool().getSchoolRefId());
            if(assignment.getSchool().getLea() != null) {
                primaryAssignment.setLeaRefId(assignment.getSchool().getLea().getLeaRefId());
            }
        }

        if(primaryAssignment.isEmptyObject()) {
            return null;
        }
        return primaryAssignment;
    }

    private StaffPersonAssignment mapOtherAssignment(StaffAssignment assignment) {
        StaffPersonAssignment staffPersonAssignment = new StaffPersonAssignment();
        staffPersonAssignment.setJobFunction(assignment.getPositionTitle());

        if(assignment.getSchool() != null) {
            staffPersonAssignment.setSchoolRefId(assignment.getSchool().getSchoolRefId());
            if(assignment.getSchool().getLea() != null) {
                staffPersonAssignment.setLeaRefId(assignment.getSchool().getLea().getLeaRefId());
            }
        }

        if(staffPersonAssignment.isEmptyObject()) {
            return null;
        }
        return staffPersonAssignment;
    }

    private Email mapEmail(StaffEmail staffEmail) {
        Email email = new Email();
        email.setEmailAddress(staffEmail.getEmailAddress());
        email.setEmailType(staffEmail.getEmailTypeCode());

        if(email.isEmptyObject()) {
            return null;
        }
        return email;
    }

    private Metadata mapMetadata(Staff staff) {
        return new Metadata(staff.getStaffSchoolYear());
    }
}