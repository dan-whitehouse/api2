package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class CourseSectionComposite implements Serializable {
    private static final long serialVersionUID = -3585877180313580241L;

    protected String courseSectionRefId;
    protected Integer courseSectionSchoolYear;

    public CourseSectionComposite() {

    }

    public CourseSectionComposite(String courseSectionRefId, Integer courseSectionSchoolYear) {
        this.courseSectionRefId = courseSectionRefId;
        this.courseSectionSchoolYear = courseSectionSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseSectionRefId == null) ? 0 : courseSectionRefId.hashCode());
        result = prime * result + ((courseSectionSchoolYear == null) ? 0 : courseSectionSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        CourseSectionComposite other = (CourseSectionComposite) obj;
        if(courseSectionRefId == null) {
            if(other.courseSectionRefId != null) return false;
        }
        else if(!courseSectionRefId.equals(other.courseSectionRefId)) return false;
        if(courseSectionSchoolYear == null) {
            if(other.courseSectionSchoolYear != null) return false;
        }
        else if(!courseSectionSchoolYear.equals(other.courseSectionSchoolYear)) return false;
        return true;
    }

}
