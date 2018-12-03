package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentIdentifierComposite implements Serializable {
    private static final long serialVersionUID = -4879766863395970164L;

    protected String studentIdentifierRefId;
    protected Integer studentIdentifierSchoolYear;

    public StudentIdentifierComposite() {

    }

    public StudentIdentifierComposite(String studentIdentifierRefId, Integer studentIdentifierSchoolYear) {
        super();
        this.studentIdentifierRefId = studentIdentifierRefId;
        this.studentIdentifierSchoolYear = studentIdentifierSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentIdentifierRefId == null) ? 0 : studentIdentifierRefId.hashCode());
        result = prime * result + ((studentIdentifierSchoolYear == null) ? 0 : studentIdentifierSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentIdentifierComposite other = (StudentIdentifierComposite) obj;
        if(studentIdentifierRefId == null) {
            if(other.studentIdentifierRefId != null) return false;
        }
        else if(!studentIdentifierRefId.equals(other.studentIdentifierRefId)) return false;
        if(studentIdentifierSchoolYear == null) {
            if(other.studentIdentifierSchoolYear != null) return false;
        }
        else if(!studentIdentifierSchoolYear.equals(other.studentIdentifierSchoolYear)) return false;
        return true;
    }
}
