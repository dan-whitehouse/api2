package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentContactAddressComposite implements Serializable {
    private static final long serialVersionUID = 6663489771484894332L;

    protected String studentContactAddressRefId;
    protected Integer studentContactAddressSchoolYear;

    public StudentContactAddressComposite() {

    }

    public StudentContactAddressComposite(String studentContactAddressRefId, Integer studentContactAddressSchoolYear) {
        this.studentContactAddressRefId = studentContactAddressRefId;
        this.studentContactAddressSchoolYear = studentContactAddressSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentContactAddressRefId == null) ? 0 : studentContactAddressRefId.hashCode());
        result = prime * result + ((studentContactAddressSchoolYear == null) ? 0 : studentContactAddressSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentContactAddressComposite other = (StudentContactAddressComposite) obj;
        if(studentContactAddressRefId == null) {
            if(other.studentContactAddressRefId != null) return false;
        }
        else if(!studentContactAddressRefId.equals(other.studentContactAddressRefId)) return false;
        if(studentContactAddressSchoolYear == null) {
            if(other.studentContactAddressSchoolYear != null) return false;
        }
        else if(!studentContactAddressSchoolYear.equals(other.studentContactAddressSchoolYear)) return false;
        return true;
    }

}
