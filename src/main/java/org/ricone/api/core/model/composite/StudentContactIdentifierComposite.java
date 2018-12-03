package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentContactIdentifierComposite implements Serializable {
    private static final long serialVersionUID = 7621861491693849441L;

    protected String studentContactIdentifierRefId;
    protected Integer studentContactIdentifierSchoolYear;

    public StudentContactIdentifierComposite() {

    }

    public StudentContactIdentifierComposite(String studentContactIdentifierRefId, Integer studentContactIdentifierSchoolYear) {
        this.studentContactIdentifierRefId = studentContactIdentifierRefId;
        this.studentContactIdentifierSchoolYear = studentContactIdentifierSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentContactIdentifierRefId == null) ? 0 : studentContactIdentifierRefId.hashCode());
        result = prime * result + ((studentContactIdentifierSchoolYear == null) ? 0 : studentContactIdentifierSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentContactIdentifierComposite other = (StudentContactIdentifierComposite) obj;
        if(studentContactIdentifierRefId == null) {
            if(other.studentContactIdentifierRefId != null) return false;
        }
        else if(!studentContactIdentifierRefId.equals(other.studentContactIdentifierRefId)) return false;
        if(studentContactIdentifierSchoolYear == null) {
            if(other.studentContactIdentifierSchoolYear != null) return false;
        }
        else if(!studentContactIdentifierSchoolYear.equals(other.studentContactIdentifierSchoolYear)) return false;
        return true;
    }

}
