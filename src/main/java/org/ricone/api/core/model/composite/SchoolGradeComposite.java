package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class SchoolGradeComposite implements Serializable {
    private static final long serialVersionUID = -5141951184324035875L;

    protected String schoolGradeRefId;
    protected Integer schoolGradeSchoolYear;

    public SchoolGradeComposite() {

    }

    public SchoolGradeComposite(String schoolGradeRefId, Integer schoolGradeSchoolYear) {
        this.schoolGradeRefId = schoolGradeRefId;
        this.schoolGradeSchoolYear = schoolGradeSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schoolGradeRefId == null) ? 0 : schoolGradeRefId.hashCode());
        result = prime * result + ((schoolGradeSchoolYear == null) ? 0 : schoolGradeSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchoolGradeComposite other = (SchoolGradeComposite) obj;
        if(schoolGradeRefId == null) {
            if(other.schoolGradeRefId != null) return false;
        }
        else if(!schoolGradeRefId.equals(other.schoolGradeRefId)) return false;
        if(schoolGradeSchoolYear == null) {
            if(other.schoolGradeSchoolYear != null) return false;
        }
        else if(!schoolGradeSchoolYear.equals(other.schoolGradeSchoolYear)) return false;
        return true;
    }

}
