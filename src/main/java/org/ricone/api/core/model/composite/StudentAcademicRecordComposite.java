package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentAcademicRecordComposite implements Serializable {
    private static final long serialVersionUID = 7043438568368610966L;

    protected String studentAcademicRecordRefId;
    protected Integer studentAcademicRecordSchoolYear;

    public StudentAcademicRecordComposite() {

    }

    public StudentAcademicRecordComposite(String studentAcademicRecordRefId, Integer studentAcademicRecordSchoolYear) {
        this.studentAcademicRecordRefId = studentAcademicRecordRefId;
        this.studentAcademicRecordSchoolYear = studentAcademicRecordSchoolYear;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((studentAcademicRecordRefId == null) ? 0 : studentAcademicRecordRefId.hashCode());
        result = prime * result + ((studentAcademicRecordSchoolYear == null) ? 0 : studentAcademicRecordSchoolYear.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        StudentAcademicRecordComposite other = (StudentAcademicRecordComposite) obj;
        if(studentAcademicRecordRefId == null) {
            if(other.studentAcademicRecordRefId != null) return false;
        }
        else if(!studentAcademicRecordRefId.equals(other.studentAcademicRecordRefId)) return false;
        if(studentAcademicRecordSchoolYear == null) {
            if(other.studentAcademicRecordSchoolYear != null) return false;
        }
        else if(!studentAcademicRecordSchoolYear.equals(other.studentAcademicRecordSchoolYear)) return false;
        return true;
    }

}
