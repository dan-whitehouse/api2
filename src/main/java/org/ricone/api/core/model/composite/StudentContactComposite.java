package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Andrew Pieniezny <andrew.pieniezny@neric.org>
 * @version 1.2.0
 * @since Mar 5, 2018
 */
public class StudentContactComposite implements Serializable {
    private static final long serialVersionUID = 5432768876720311346L;

    private String studentContactRefId;
    private Integer studentContactSchoolYear;

    public StudentContactComposite() {

    }

    public StudentContactComposite(String studentContactRefId, Integer studentContactSchoolYear) {
        this.studentContactRefId = studentContactRefId;
        this.studentContactSchoolYear = studentContactSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        StudentContactComposite that = (StudentContactComposite) o;

        if(studentContactRefId != null ? !studentContactRefId.equals(that.studentContactRefId) : that.studentContactRefId != null)
            return false;
        return studentContactSchoolYear != null ? studentContactSchoolYear.equals(that.studentContactSchoolYear) : that.studentContactSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = studentContactRefId != null ? studentContactRefId.hashCode() : 0;
        result = 31 * result + (studentContactSchoolYear != null ? studentContactSchoolYear.hashCode() : 0);
        return result;
    }
}
