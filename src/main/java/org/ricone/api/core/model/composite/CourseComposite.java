package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class CourseComposite implements Serializable {
    private static final long serialVersionUID = -8352062885923364027L;

    protected String courseRefId;
    protected Integer courseSchoolYear;

    public CourseComposite() {

    }

    public CourseComposite(String courseRefId, Integer courseSchoolYear) {
        this.courseRefId = courseRefId;
        this.courseSchoolYear = courseSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseRefId == null) ? 0 : courseRefId.hashCode());
        result = prime * result + ((courseSchoolYear == null) ? 0 : courseSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        CourseComposite other = (CourseComposite) obj;
        if(courseRefId == null) {
            if(other.courseRefId != null) return false;
        }
        else if(!courseRefId.equals(other.courseRefId)) return false;
        if(courseSchoolYear == null) {
            if(other.courseSchoolYear != null) return false;
        }
        else if(!courseSchoolYear.equals(other.courseSchoolYear)) return false;
        return true;
    }


}
