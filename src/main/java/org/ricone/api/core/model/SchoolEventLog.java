package org.ricone.api.core.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */

@Entity
@Table(name = "schooleventlog")
public class SchoolEventLog extends EventLog implements Serializable {
    private static final long serialVersionUID = 8596207385746991408L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="schoolRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
    })
    private School school;

    public SchoolEventLog() {
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        SchoolEventLog that = (SchoolEventLog) o;

        return school != null ? school.equals(that.school) : that.school == null;
    }

    @Override
    public int hashCode() {
        return school != null ? school.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SchoolEventLog{" + "school=" + school + '}';
    }
}