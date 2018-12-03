package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class CourseIdentifierComposite implements Serializable {
    private static final long serialVersionUID = 949567927225598141L;

    protected String courseIdentifierRefId;
    protected Integer courseIdentifierSchoolYear;

    public CourseIdentifierComposite() {

    }

    public CourseIdentifierComposite(String courseIdentifierRefId, Integer courseIdentifierSchoolYear) {
        this.courseIdentifierRefId = courseIdentifierRefId;
        this.courseIdentifierSchoolYear = courseIdentifierSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseIdentifierRefId == null) ? 0 : courseIdentifierRefId.hashCode());
        result = prime * result + ((courseIdentifierSchoolYear == null) ? 0 : courseIdentifierSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        CourseIdentifierComposite other = (CourseIdentifierComposite) obj;
        if(courseIdentifierRefId == null) {
            if(other.courseIdentifierRefId != null) return false;
        }
        else if(!courseIdentifierRefId.equals(other.courseIdentifierRefId)) return false;
        if(courseIdentifierSchoolYear == null) {
            if(other.courseIdentifierSchoolYear != null) return false;
        }
        else if(!courseIdentifierSchoolYear.equals(other.courseIdentifierSchoolYear)) return false;
        return true;
    }


}
