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
@Table(name = "eventlogroster")
public class RosterEventLog extends EventLog implements Serializable {
    private static final long serialVersionUID = 7037730430279770732L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="courseSectionRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="courseSectionSchoolYear", nullable = false)
    })
    private CourseSection courseSection;

    public RosterEventLog() {
    }

    public RosterEventLog(CourseSection courseSection) {
        this.courseSection = courseSection;
    }

    public RosterEventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea, CourseSection courseSection) {
        super(eventRefId, eventSchoolYear, eventType, eventTimestamp, lea);
        this.courseSection = courseSection;
    }

    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        RosterEventLog that = (RosterEventLog) o;

        return courseSection != null ? courseSection.equals(that.courseSection) : that.courseSection == null;
    }

    @Override
    public int hashCode() {
        return courseSection != null ? courseSection.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RosterEventLog{" + "courseSection=" + courseSection + '}';
    }
}