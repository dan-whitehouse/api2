package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentEnrollmentComposite implements Serializable {
    private static final long serialVersionUID = -2143992235447038814L;

    protected String studentEnrollmentRefId;
    protected Integer studentEnrollmentSchoolYear;

    public StudentEnrollmentComposite() {

    }

    public StudentEnrollmentComposite(String studentEnrollmentRefId, Integer studentEnrollmentSchoolYear) {
        this.studentEnrollmentRefId = studentEnrollmentRefId;
        this.studentEnrollmentSchoolYear = studentEnrollmentSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentEnrollmentRefId == null) ? 0 : studentEnrollmentRefId.hashCode());
        result = prime * result + ((studentEnrollmentSchoolYear == null) ? 0 : studentEnrollmentSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentEnrollmentComposite other = (StudentEnrollmentComposite) obj;
        if(studentEnrollmentRefId == null) {
            if(other.studentEnrollmentRefId != null) return false;
        }
        else if(!studentEnrollmentRefId.equals(other.studentEnrollmentRefId)) return false;
        if(studentEnrollmentSchoolYear == null) {
            if(other.studentEnrollmentSchoolYear != null) return false;
        }
        else if(!studentEnrollmentSchoolYear.equals(other.studentEnrollmentSchoolYear)) return false;
        return true;
    }

}
