package org.ricone.api.core.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */

@Entity
@Table(name = "studenteventlog")
public class StudentEventLog  extends EventLog implements Serializable {
    private static final long serialVersionUID = 5974939838069996873L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="studentRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="studentSchoolYear", nullable = false)
    })
    private Student student;

    public StudentEventLog() {
    }

    public StudentEventLog(Student student) {
        this.student = student;
    }

    public StudentEventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea, Student student) {
        super(eventRefId, eventSchoolYear, eventType, eventTimestamp, lea);
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        StudentEventLog that = (StudentEventLog) o;

        return student != null ? student.equals(that.student) : that.student == null;
    }

    @Override
    public int hashCode() {
        return student != null ? student.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StudentEventLog{" + "student=" + student + '}';
    }
}