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
@Table(name = "contacteventlog")
public class ContactEventLog extends EventLog{
    private static final long serialVersionUID = 3438435272684287814L;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumns({
            @JoinColumn(name="ObjectRefId", referencedColumnName="studentContactRefId", nullable = false),
            @JoinColumn(name="ObjectSchoolYear", referencedColumnName="studentContactSchoolYear", nullable = false)
    })
    private StudentContact studentContact;

    public ContactEventLog() {
    }

    public ContactEventLog(StudentContact studentContact) {
        this.studentContact = studentContact;
    }

    public ContactEventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea, StudentContact studentContact) {
        super(eventRefId, eventSchoolYear, eventType, eventTimestamp, lea);
        this.studentContact = studentContact;
    }

    public StudentContact getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(StudentContact studentContact) {
        this.studentContact = studentContact;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ContactEventLog that = (ContactEventLog) o;

        return studentContact != null ? studentContact.equals(that.studentContact) : that.studentContact == null;
    }

    @Override
    public int hashCode() {
        return studentContact != null ? studentContact.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ContactEventLog{" + "studentContact=" + studentContact + '}';
    }
}