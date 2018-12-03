package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class SchoolCalendarSessionComposite implements Serializable {
    private static final long serialVersionUID = 4773162434770342596L;

    protected String schoolCalendarSessionRefId;
    protected Integer schoolCalendarSessionSchoolYear;

    public SchoolCalendarSessionComposite() {

    }

    public SchoolCalendarSessionComposite(String schoolCalendarSessionRefId, Integer schoolCalendarSessionSchoolYear) {
        this.schoolCalendarSessionRefId = schoolCalendarSessionRefId;
        this.schoolCalendarSessionSchoolYear = schoolCalendarSessionSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schoolCalendarSessionRefId == null) ? 0 : schoolCalendarSessionRefId.hashCode());
        result = prime * result + ((schoolCalendarSessionSchoolYear == null) ? 0 : schoolCalendarSessionSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchoolCalendarSessionComposite other = (SchoolCalendarSessionComposite) obj;
        if(schoolCalendarSessionRefId == null) {
            if(other.schoolCalendarSessionRefId != null) return false;
        }
        else if(!schoolCalendarSessionRefId.equals(other.schoolCalendarSessionRefId)) return false;
        if(schoolCalendarSessionSchoolYear == null) {
            if(other.schoolCalendarSessionSchoolYear != null) return false;
        }
        else if(!schoolCalendarSessionSchoolYear.equals(other.schoolCalendarSessionSchoolYear)) return false;
        return true;
    }

}
