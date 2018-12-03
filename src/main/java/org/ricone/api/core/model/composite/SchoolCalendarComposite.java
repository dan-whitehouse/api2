package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class SchoolCalendarComposite implements Serializable {
    private static final long serialVersionUID = 1711347052568541484L;

    protected String schoolCalendarRefId;
    protected Integer schoolCalendarSchoolYear;

    public SchoolCalendarComposite() {

    }

    public SchoolCalendarComposite(String schoolCalendarRefId, Integer schoolCalendarSchoolYear) {
        this.schoolCalendarRefId = schoolCalendarRefId;
        this.schoolCalendarSchoolYear = schoolCalendarSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((schoolCalendarRefId == null) ? 0 : schoolCalendarRefId.hashCode());
        result = prime * result + ((schoolCalendarSchoolYear == null) ? 0 : schoolCalendarSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        SchoolCalendarComposite other = (SchoolCalendarComposite) obj;
        if(schoolCalendarRefId == null) {
            if(other.schoolCalendarRefId != null) return false;
        }
        else if(!schoolCalendarRefId.equals(other.schoolCalendarRefId)) return false;
        if(schoolCalendarSchoolYear == null) {
            if(other.schoolCalendarSchoolYear != null) return false;
        }
        else if(!schoolCalendarSchoolYear.equals(other.schoolCalendarSchoolYear)) return false;
        return true;
    }


}
