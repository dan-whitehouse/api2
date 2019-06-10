package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentComposite implements Serializable {
    private static final long serialVersionUID = 1176739284675287278L;

    protected String studentRefId;
    protected Integer studentSchoolYear;

    public StudentComposite() {

    }

    public StudentComposite(String studentRefId, Integer studentSchoolYear) {
        this.studentRefId = studentRefId;
        this.studentSchoolYear = studentSchoolYear;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentRefId == null) ? 0 : studentRefId.hashCode());
        result = prime * result + ((studentSchoolYear == null) ? 0 : studentSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentComposite other = (StudentComposite) obj;
        if(studentRefId == null) {
            if(other.studentRefId != null) return false;
        }
        else if(!studentRefId.equals(other.studentRefId)) return false;
        if(studentSchoolYear == null) {
            if(other.studentSchoolYear != null) return false;
        }
        else if(!studentSchoolYear.equals(other.studentSchoolYear)) return false;
        return true;
    }

}
