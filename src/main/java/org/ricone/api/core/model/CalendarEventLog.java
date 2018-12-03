package org.ricone.api.core.model;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */

@Entity
@Table(name = "calendareventlog")
public class CalendarEventLog extends EventLog {
    private static final long serialVersionUID = 6026800897572684220L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="schoolCalendarRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="schoolCalendarSchoolYear", nullable = false)
    })
    private SchoolCalendar schoolCalendar;

    public CalendarEventLog() {
    }

    public CalendarEventLog(SchoolCalendar schoolCalendar) {
        this.schoolCalendar = schoolCalendar;
    }

    public CalendarEventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea, SchoolCalendar schoolCalendar) {
        super(eventRefId, eventSchoolYear, eventType, eventTimestamp, lea);
        this.schoolCalendar = schoolCalendar;
    }

    public SchoolCalendar getSchoolCalendar() {
        return schoolCalendar;
    }

    public void setSchoolCalendar(SchoolCalendar schoolCalendar) {
        this.schoolCalendar = schoolCalendar;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        CalendarEventLog that = (CalendarEventLog) o;

        return schoolCalendar != null ? schoolCalendar.equals(that.schoolCalendar) : that.schoolCalendar == null;
    }

    @Override
    public int hashCode() {
        return schoolCalendar != null ? schoolCalendar.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CalendarEventLog{" + "schoolCalendar=" + schoolCalendar + '}';
    }
}