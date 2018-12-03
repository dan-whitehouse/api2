package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StaffIdentifierComposite implements Serializable {
    private static final long serialVersionUID = 6349888116223226380L;

    protected String staffIdentifierRefId;
    protected Integer staffIdentifierSchoolYear;

    public StaffIdentifierComposite() {

    }

    public StaffIdentifierComposite(String staffIdentifierRefId, Integer staffIdentifierSchoolYear) {
        this.staffIdentifierRefId = staffIdentifierRefId;
        this.staffIdentifierSchoolYear = staffIdentifierSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((staffIdentifierRefId == null) ? 0 : staffIdentifierRefId.hashCode());
        result = prime * result + ((staffIdentifierSchoolYear == null) ? 0 : staffIdentifierSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StaffIdentifierComposite other = (StaffIdentifierComposite) obj;
        if(staffIdentifierRefId == null) {
            if(other.staffIdentifierRefId != null) return false;
        }
        else if(!staffIdentifierRefId.equals(other.staffIdentifierRefId)) return false;
        if(staffIdentifierSchoolYear == null) {
            if(other.staffIdentifierSchoolYear != null) return false;
        }
        else if(!staffIdentifierSchoolYear.equals(other.staffIdentifierSchoolYear)) return false;
        return true;
    }

}
