package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentContactTelephoneComposite implements Serializable {
    private static final long serialVersionUID = 176876444136049941L;

    protected String studentContactPhoneRefId;
    protected Integer studentContactPhoneSchoolYear;

    public StudentContactTelephoneComposite() {

    }

    public StudentContactTelephoneComposite(String studentContactPhoneRefId, Integer studentContactPhoneSchoolYear) {
        this.studentContactPhoneRefId = studentContactPhoneRefId;
        this.studentContactPhoneSchoolYear = studentContactPhoneSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentContactPhoneRefId == null) ? 0 : studentContactPhoneRefId.hashCode());
        result = prime * result + ((studentContactPhoneSchoolYear == null) ? 0 : studentContactPhoneSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentContactTelephoneComposite other = (StudentContactTelephoneComposite) obj;
        if(studentContactPhoneRefId == null) {
            if(other.studentContactPhoneRefId != null) return false;
        }
        else if(!studentContactPhoneRefId.equals(other.studentContactPhoneRefId)) return false;
        if(studentContactPhoneSchoolYear == null) {
            if(other.studentContactPhoneSchoolYear != null) return false;
        }
        else if(!studentContactPhoneSchoolYear.equals(other.studentContactPhoneSchoolYear)) return false;
        return true;
    }

}
