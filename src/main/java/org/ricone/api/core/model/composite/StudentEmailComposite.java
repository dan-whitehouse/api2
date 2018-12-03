package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentEmailComposite implements Serializable {
    private static final long serialVersionUID = 3678466085742698241L;

    protected String studentEmailRefId;
    protected Integer studentEmailSchoolYear;

    public StudentEmailComposite() {

    }

    public StudentEmailComposite(String studentEmailRefId, Integer studentEmailSchoolYear) {
        this.studentEmailRefId = studentEmailRefId;
        this.studentEmailSchoolYear = studentEmailSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentEmailRefId == null) ? 0 : studentEmailRefId.hashCode());
        result = prime * result + ((studentEmailSchoolYear == null) ? 0 : studentEmailSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentEmailComposite other = (StudentEmailComposite) obj;
        if(studentEmailRefId == null) {
            if(other.studentEmailRefId != null) return false;
        }
        else if(!studentEmailRefId.equals(other.studentEmailRefId)) return false;
        if(studentEmailSchoolYear == null) {
            if(other.studentEmailSchoolYear != null) return false;
        }
        else if(!studentEmailSchoolYear.equals(other.studentEmailSchoolYear)) return false;
        return true;
    }

}
