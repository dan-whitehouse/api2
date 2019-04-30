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
@Table(name = "courseeventlog")
public class CourseEventLog extends EventLog implements Serializable {
    private static final long serialVersionUID = 7028300751315961670L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="courseRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="courseSchoolYear", nullable = false)
    })
    private Course course;

    public CourseEventLog() {
    }

    public CourseEventLog(Course course) {
        this.course = course;
    }

    public CourseEventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea, Course course) {
        super(eventRefId, eventSchoolYear, eventType, eventTimestamp, lea);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        CourseEventLog that = (CourseEventLog) o;

        return course != null ? course.equals(that.course) : that.course == null;
    }

    @Override
    public int hashCode() {
        return course != null ? course.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CourseEventLog{" + "course=" + course + '}';
    }
}