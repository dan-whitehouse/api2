package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentTelephoneComposite implements Serializable {
    private static final long serialVersionUID = 3470460106980766056L;

    protected String studentPhoneRefId;
    protected Integer studentPhoneSchoolYear;

    public StudentTelephoneComposite() {

    }

    public StudentTelephoneComposite(String studentPhoneRefId, Integer studentPhoneSchoolYear) {
        this.studentPhoneRefId = studentPhoneRefId;
        this.studentPhoneSchoolYear = studentPhoneSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentPhoneRefId == null) ? 0 : studentPhoneRefId.hashCode());
        result = prime * result + ((studentPhoneSchoolYear == null) ? 0 : studentPhoneSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentTelephoneComposite other = (StudentTelephoneComposite) obj;
        if(studentPhoneRefId == null) {
            if(other.studentPhoneRefId != null) return false;
        }
        else if(!studentPhoneRefId.equals(other.studentPhoneRefId)) return false;
        if(studentPhoneSchoolYear == null) {
            if(other.studentPhoneSchoolYear != null) return false;
        }
        else if(!studentPhoneSchoolYear.equals(other.studentPhoneSchoolYear)) return false;
        return true;
    }

}
