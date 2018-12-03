package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version x.x.x
 * @since Mar 5, 2018
 */
public class StudentContactOtherNameComposite implements Serializable {
    private static final long serialVersionUID = -8297728768414061270L;

    protected String studentContactOtherNameRefId;
    protected Integer studentContactOtherNameSchoolYear;

    public StudentContactOtherNameComposite() {

    }

    public StudentContactOtherNameComposite(String studentContactOtherNameRefId, Integer studentContactOtherNameSchoolYear) {
        this.studentContactOtherNameRefId = studentContactOtherNameRefId;
        this.studentContactOtherNameSchoolYear = studentContactOtherNameSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentContactOtherNameRefId == null) ? 0 : studentContactOtherNameRefId.hashCode());
        result = prime * result + ((studentContactOtherNameSchoolYear == null) ? 0 : studentContactOtherNameSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentContactOtherNameComposite other = (StudentContactOtherNameComposite) obj;
        if(studentContactOtherNameRefId == null) {
            if(other.studentContactOtherNameRefId != null) return false;
        }
        else if(!studentContactOtherNameRefId.equals(other.studentContactOtherNameRefId)) return false;
        if(studentContactOtherNameSchoolYear == null) {
            if(other.studentContactOtherNameSchoolYear != null) return false;
        }
        else if(!studentContactOtherNameSchoolYear.equals(other.studentContactOtherNameSchoolYear)) return false;
        return true;
    }

}
