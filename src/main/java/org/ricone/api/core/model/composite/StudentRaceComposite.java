package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentRaceComposite implements Serializable {
    private static final long serialVersionUID = -7457763025401562381L;

    protected String studentRaceRefId;
    protected Integer studentRaceSchoolYear;

    public StudentRaceComposite() {

    }

    public StudentRaceComposite(String studentRaceRefId, Integer studentRaceSchoolYear) {
        this.studentRaceRefId = studentRaceRefId;
        this.studentRaceSchoolYear = studentRaceSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentRaceRefId == null) ? 0 : studentRaceRefId.hashCode());
        result = prime * result + ((studentRaceSchoolYear == null) ? 0 : studentRaceSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentRaceComposite other = (StudentRaceComposite) obj;
        if(studentRaceRefId == null) {
            if(other.studentRaceRefId != null) return false;
        }
        else if(!studentRaceRefId.equals(other.studentRaceRefId)) return false;
        if(studentRaceSchoolYear == null) {
            if(other.studentRaceSchoolYear != null) return false;
        }
        else if(!studentRaceSchoolYear.equals(other.studentRaceSchoolYear)) return false;
        return true;
    }

}
