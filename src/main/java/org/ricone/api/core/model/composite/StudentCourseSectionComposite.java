package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentCourseSectionComposite implements Serializable {
    private static final long serialVersionUID = -9138313354310561716L;

    protected String studentCourseSectionRefId;
    protected Integer studentCourseSectionSchoolYear;

    public StudentCourseSectionComposite() {

    }

    public StudentCourseSectionComposite(String studentCourseSectionRefId, Integer studentCourseSectionSchoolYear) {
        this.studentCourseSectionRefId = studentCourseSectionRefId;
        this.studentCourseSectionSchoolYear = studentCourseSectionSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentCourseSectionRefId == null) ? 0 : studentCourseSectionRefId.hashCode());
        result = prime * result + ((studentCourseSectionSchoolYear == null) ? 0 : studentCourseSectionSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentCourseSectionComposite other = (StudentCourseSectionComposite) obj;
        if(studentCourseSectionRefId == null) {
            if(other.studentCourseSectionRefId != null) return false;
        }
        else if(!studentCourseSectionRefId.equals(other.studentCourseSectionRefId)) return false;
        if(studentCourseSectionSchoolYear == null) {
            if(other.studentCourseSectionSchoolYear != null) return false;
        }
        else if(!studentCourseSectionSchoolYear.equals(other.studentCourseSectionSchoolYear)) return false;
        return true;
    }

}
