package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StaffAssignmentComposite implements Serializable {
    private static final long serialVersionUID = -7701990040087695275L;

    protected String staffAssignmentRefId;
    protected Integer staffAssignmentSchoolYear;

    public StaffAssignmentComposite() {

    }

    public StaffAssignmentComposite(String staffAssignmentRefId, Integer staffAssignmentSchoolYear) {
        this.staffAssignmentRefId = staffAssignmentRefId;
        this.staffAssignmentSchoolYear = staffAssignmentSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((staffAssignmentRefId == null) ? 0 : staffAssignmentRefId.hashCode());
        result = prime * result + ((staffAssignmentSchoolYear == null) ? 0 : staffAssignmentSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StaffAssignmentComposite other = (StaffAssignmentComposite) obj;
        if(staffAssignmentRefId == null) {
            if(other.staffAssignmentRefId != null) return false;
        }
        else if(!staffAssignmentRefId.equals(other.staffAssignmentRefId)) return false;
        if(staffAssignmentSchoolYear == null) {
            if(other.staffAssignmentSchoolYear != null) return false;
        }
        else if(!staffAssignmentSchoolYear.equals(other.staffAssignmentSchoolYear)) return false;
        return true;
    }

}
