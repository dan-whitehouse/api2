package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StaffCourseSectionComposite implements Serializable {
    private static final long serialVersionUID = 1204828760308820182L;

    protected String staffCourseSectionRefId;
    protected Integer staffCourseSectionSchoolYear;

    public StaffCourseSectionComposite() {

    }

    public StaffCourseSectionComposite(String staffCourseSectionRefId, Integer staffCourseSectionSchoolYear) {
        this.staffCourseSectionRefId = staffCourseSectionRefId;
        this.staffCourseSectionSchoolYear = staffCourseSectionSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((staffCourseSectionRefId == null) ? 0 : staffCourseSectionRefId.hashCode());
        result = prime * result + ((staffCourseSectionSchoolYear == null) ? 0 : staffCourseSectionSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StaffCourseSectionComposite other = (StaffCourseSectionComposite) obj;
        if(staffCourseSectionRefId == null) {
            if(other.staffCourseSectionRefId != null) return false;
        }
        else if(!staffCourseSectionRefId.equals(other.staffCourseSectionRefId)) return false;
        if(staffCourseSectionSchoolYear == null) {
            if(other.staffCourseSectionSchoolYear != null) return false;
        }
        else if(!staffCourseSectionSchoolYear.equals(other.staffCourseSectionSchoolYear)) return false;
        return true;
    }

}
