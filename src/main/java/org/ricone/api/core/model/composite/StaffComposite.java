package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StaffComposite implements Serializable {
    private static final long serialVersionUID = 1333917306786324248L;

    private String staffRefId;
    private Integer staffSchoolYear;

    public StaffComposite() {
    }

    public StaffComposite(String staffRefId, Integer staffSchoolYear) {
        this.staffRefId = staffRefId;
        this.staffSchoolYear = staffSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        StaffComposite that = (StaffComposite) o;

        if(staffRefId != null ? !staffRefId.equals(that.staffRefId) : that.staffRefId != null) return false;
        return staffSchoolYear != null ? staffSchoolYear.equals(that.staffSchoolYear) : that.staffSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = staffRefId != null ? staffRefId.hashCode() : 0;
        result = 31 * result + (staffSchoolYear != null ? staffSchoolYear.hashCode() : 0);
        return result;
    }
}
