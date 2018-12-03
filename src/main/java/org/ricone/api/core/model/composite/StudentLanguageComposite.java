package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentLanguageComposite implements Serializable {
    private static final long serialVersionUID = -578684677025119602L;

    protected String studentLanguageRefId;
    protected Integer studentLanguageSchoolYear;

    public StudentLanguageComposite() {

    }

    public StudentLanguageComposite(String studentLanguageRefId, Integer studentLanguageSchoolYear) {
        this.studentLanguageRefId = studentLanguageRefId;
        this.studentLanguageSchoolYear = studentLanguageSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentLanguageRefId == null) ? 0 : studentLanguageRefId.hashCode());
        result = prime * result + ((studentLanguageSchoolYear == null) ? 0 : studentLanguageSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentLanguageComposite other = (StudentLanguageComposite) obj;
        if(studentLanguageRefId == null) {
            if(other.studentLanguageRefId != null) return false;
        }
        else if(!studentLanguageRefId.equals(other.studentLanguageRefId)) return false;
        if(studentLanguageSchoolYear == null) {
            if(other.studentLanguageSchoolYear != null) return false;
        }
        else if(!studentLanguageSchoolYear.equals(other.studentLanguageSchoolYear)) return false;
        return true;
    }

}
