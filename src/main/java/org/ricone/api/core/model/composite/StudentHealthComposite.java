package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentHealthComposite implements Serializable {
    private static final long serialVersionUID = -5187725829006462046L;

    protected String studentHealthRefId;
    protected Integer studentHealthSchoolYear;

    public StudentHealthComposite() {

    }

    public StudentHealthComposite(String studentHealthRefId, Integer studentHealthSchoolYear) {
        this.studentHealthRefId = studentHealthRefId;
        this.studentHealthSchoolYear = studentHealthSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentHealthRefId == null) ? 0 : studentHealthRefId.hashCode());
        result = prime * result + ((studentHealthSchoolYear == null) ? 0 : studentHealthSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentHealthComposite other = (StudentHealthComposite) obj;
        if(studentHealthRefId == null) {
            if(other.studentHealthRefId != null) return false;
        }
        else if(!studentHealthRefId.equals(other.studentHealthRefId)) return false;
        if(studentHealthSchoolYear == null) {
            if(other.studentHealthSchoolYear != null) return false;
        }
        else if(!studentHealthSchoolYear.equals(other.studentHealthSchoolYear)) return false;
        return true;
    }

}
