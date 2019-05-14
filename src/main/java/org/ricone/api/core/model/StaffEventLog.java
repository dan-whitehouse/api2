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
@Table(name = "eventlogstaff")
public class StaffEventLog extends EventLog implements Serializable {
    private static final long serialVersionUID = -6171141676303732139L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="staffRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="staffSchoolYear", nullable = false)
    })
    private Staff staff;

    public StaffEventLog() {
    }

    public StaffEventLog(Staff staff) {
        this.staff = staff;
    }

    public StaffEventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea, Staff staff) {
        super(eventRefId, eventSchoolYear, eventType, eventTimestamp, lea);
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        StaffEventLog that = (StaffEventLog) o;

        return staff != null ? staff.equals(that.staff) : that.staff == null;
    }

    @Override
    public int hashCode() {
        return staff != null ? staff.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "StaffEventLog{" + "staff=" + staff + '}';
    }
}