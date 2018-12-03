package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentOtherNameComposite implements Serializable {
    private static final long serialVersionUID = 4739425229781131347L;

    protected String studentOtherNameRefId;
    protected Integer studentOtherNameSchoolYear;

    public StudentOtherNameComposite() {

    }

    public StudentOtherNameComposite(String studentOtherNameRefId, Integer studentOtherNameSchoolYear) {
        this.studentOtherNameRefId = studentOtherNameRefId;
        this.studentOtherNameSchoolYear = studentOtherNameSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentOtherNameRefId == null) ? 0 : studentOtherNameRefId.hashCode());
        result = prime * result + ((studentOtherNameSchoolYear == null) ? 0 : studentOtherNameSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentOtherNameComposite other = (StudentOtherNameComposite) obj;
        if(studentOtherNameRefId == null) {
            if(other.studentOtherNameRefId != null) return false;
        }
        else if(!studentOtherNameRefId.equals(other.studentOtherNameRefId)) return false;
        if(studentOtherNameSchoolYear == null) {
            if(other.studentOtherNameSchoolYear != null) return false;
        }
        else if(!studentOtherNameSchoolYear.equals(other.studentOtherNameSchoolYear)) return false;
        return true;
    }

}
