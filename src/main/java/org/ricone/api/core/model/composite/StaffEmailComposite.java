package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StaffEmailComposite implements Serializable {
    private static final long serialVersionUID = -1729575774582191553L;

    protected String staffEmailRefId;
    protected Integer staffEmailSchoolYear;

    public StaffEmailComposite() {

    }

    public StaffEmailComposite(String staffEmailRefId, Integer staffEmailSchoolYear) {
        this.staffEmailRefId = staffEmailRefId;
        this.staffEmailSchoolYear = staffEmailSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((staffEmailRefId == null) ? 0 : staffEmailRefId.hashCode());
        result = prime * result + ((staffEmailSchoolYear == null) ? 0 : staffEmailSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StaffEmailComposite other = (StaffEmailComposite) obj;
        if(staffEmailRefId == null) {
            if(other.staffEmailRefId != null) return false;
        }
        else if(!staffEmailRefId.equals(other.staffEmailRefId)) return false;
        if(staffEmailSchoolYear == null) {
            if(other.staffEmailSchoolYear != null) return false;
        }
        else if(!staffEmailSchoolYear.equals(other.staffEmailSchoolYear)) return false;
        return true;
    }

}
