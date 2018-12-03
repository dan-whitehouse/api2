package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class CourseSectionScheduleComposite implements Serializable {
    private static final long serialVersionUID = 882908206086261544L;

    protected String courseSectionScheduleRefId;
    protected Integer courseSectionScheduleSchoolYear;

    public CourseSectionScheduleComposite() {

    }

    public CourseSectionScheduleComposite(String courseSectionScheduleRefId, Integer courseSectionScheduleSchoolYear) {
        this.courseSectionScheduleRefId = courseSectionScheduleRefId;
        this.courseSectionScheduleSchoolYear = courseSectionScheduleSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseSectionScheduleRefId == null) ? 0 : courseSectionScheduleRefId.hashCode());
        result = prime * result + ((courseSectionScheduleSchoolYear == null) ? 0 : courseSectionScheduleSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        CourseSectionScheduleComposite other = (CourseSectionScheduleComposite) obj;
        if(courseSectionScheduleRefId == null) {
            if(other.courseSectionScheduleRefId != null) return false;
        }
        else if(!courseSectionScheduleRefId.equals(other.courseSectionScheduleRefId)) return false;
        if(courseSectionScheduleSchoolYear == null) {
            if(other.courseSectionScheduleSchoolYear != null) return false;
        }
        else if(!courseSectionScheduleSchoolYear.equals(other.courseSectionScheduleSchoolYear)) return false;
        return true;
    }

}
