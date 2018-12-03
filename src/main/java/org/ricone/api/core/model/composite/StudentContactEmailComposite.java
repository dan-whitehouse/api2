package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentContactEmailComposite implements Serializable {
    private static final long serialVersionUID = -5000095396627583745L;

    protected String studentContactEmailRefId;
    protected Integer studentContactEmailSchoolYear;

    public StudentContactEmailComposite() {

    }

    public StudentContactEmailComposite(String studentContactEmailRefId, Integer studentContactEmailSchoolYear) {
        this.studentContactEmailRefId = studentContactEmailRefId;
        this.studentContactEmailSchoolYear = studentContactEmailSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentContactEmailRefId == null) ? 0 : studentContactEmailRefId.hashCode());
        result = prime * result + ((studentContactEmailSchoolYear == null) ? 0 : studentContactEmailSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentContactEmailComposite other = (StudentContactEmailComposite) obj;
        if(studentContactEmailRefId == null) {
            if(other.studentContactEmailRefId != null) return false;
        }
        else if(!studentContactEmailRefId.equals(other.studentContactEmailRefId)) return false;
        if(studentContactEmailSchoolYear == null) {
            if(other.studentContactEmailSchoolYear != null) return false;
        }
        else if(!studentContactEmailSchoolYear.equals(other.studentContactEmailSchoolYear)) return false;
        return true;
    }

}
