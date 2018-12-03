package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentAddressComposite implements Serializable {
    private static final long serialVersionUID = 483514767677764263L;

    protected String studentAddressRefId;
    protected Integer studentAddressSchoolYear;

    public StudentAddressComposite() {

    }

    public StudentAddressComposite(String studentAddressRefId, Integer studentAddressSchoolYear) {
        this.studentAddressRefId = studentAddressRefId;
        this.studentAddressSchoolYear = studentAddressSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentAddressRefId == null) ? 0 : studentAddressRefId.hashCode());
        result = prime * result + ((studentAddressSchoolYear == null) ? 0 : studentAddressSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentAddressComposite other = (StudentAddressComposite) obj;
        if(studentAddressRefId == null) {
            if(other.studentAddressRefId != null) return false;
        }
        else if(!studentAddressRefId.equals(other.studentAddressRefId)) return false;
        if(studentAddressSchoolYear == null) {
            if(other.studentAddressSchoolYear != null) return false;
        }
        else if(!studentAddressSchoolYear.equals(other.studentAddressSchoolYear)) return false;
        return true;
    }

}
