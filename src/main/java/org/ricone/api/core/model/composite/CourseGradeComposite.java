package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class CourseGradeComposite implements Serializable {
    private static final long serialVersionUID = 7458966666094579205L;

    protected String courseGradeRefId;
    protected Integer courseGradeSchoolYear;

    public CourseGradeComposite() {

    }

    public CourseGradeComposite(String courseGradeRefId, Integer courseGradeSchoolYear) {
        this.courseGradeRefId = courseGradeRefId;
        this.courseGradeSchoolYear = courseGradeSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseGradeRefId == null) ? 0 : courseGradeRefId.hashCode());
        result = prime * result + ((courseGradeSchoolYear == null) ? 0 : courseGradeSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        CourseGradeComposite other = (CourseGradeComposite) obj;
        if(courseGradeRefId == null) {
            if(other.courseGradeRefId != null) return false;
        }
        else if(!courseGradeRefId.equals(other.courseGradeRefId)) return false;
        if(courseGradeSchoolYear == null) {
            if(other.courseGradeSchoolYear != null) return false;
        }
        else if(!courseGradeSchoolYear.equals(other.courseGradeSchoolYear)) return false;
        return true;
    }

}
