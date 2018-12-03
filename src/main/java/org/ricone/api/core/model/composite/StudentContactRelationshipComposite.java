package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentContactRelationshipComposite implements Serializable {
    private static final long serialVersionUID = 7558358718609949957L;

    protected String studentContactRelationshipRefId;
    protected Integer studentContactRelationshipSchoolYear;

    public StudentContactRelationshipComposite() {

    }

    public StudentContactRelationshipComposite(String studentContactRelationshipRefId, Integer studentContactRelationshipSchoolYear) {
        this.studentContactRelationshipRefId = studentContactRelationshipRefId;
        this.studentContactRelationshipSchoolYear = studentContactRelationshipSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentContactRelationshipRefId == null) ? 0 : studentContactRelationshipRefId.hashCode());
        result = prime * result + ((studentContactRelationshipSchoolYear == null) ? 0 : studentContactRelationshipSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentContactRelationshipComposite other = (StudentContactRelationshipComposite) obj;
        if(studentContactRelationshipRefId == null) {
            if(other.studentContactRelationshipRefId != null) return false;
        }
        else if(!studentContactRelationshipRefId.equals(other.studentContactRelationshipRefId)) return false;
        if(studentContactRelationshipSchoolYear == null) {
            if(other.studentContactRelationshipSchoolYear != null) return false;
        }
        else if(!studentContactRelationshipSchoolYear.equals(other.studentContactRelationshipSchoolYear)) return false;
        return true;
    }

}
