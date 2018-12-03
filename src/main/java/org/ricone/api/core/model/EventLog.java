package org.ricone.api.core.model;

import org.ricone.api.core.model.composite.EventLogComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */

@MappedSuperclass
@IdClass(EventLogComposite.class)
public class EventLog implements Serializable {
    private static final long serialVersionUID = -2513634207456103548L;

    @Id
    @Column(name = "EventRefId", unique = true, nullable = false, length = 64)
    private String eventRefId;

    @Column(name = "EventSchoolYear", nullable = false, length = 6)
    private Integer eventSchoolYear;

    @Column(name = "EventType", nullable = false, length = 30)
    private String eventType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EventTimestamp", nullable = false, length = 19)
    private Date eventTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="LEARefId", referencedColumnName="leaRefId", nullable = false),
            @JoinColumn(name="LEASchoolYear", referencedColumnName="leaSchoolYear", nullable = false)
    })
    private Lea lea;

    @Column(name = "ObjectRefId", nullable = false, length = 64, insertable = false, updatable = false)
    private String objectRefId;

    @Column(name = "ObjectSchoolYear", nullable = false, length = 6, insertable = false, updatable = false)
    private Integer objectSchoolYear;

    public EventLog() {

    }

    public EventLog(String eventRefId, Integer eventSchoolYear, String eventType, Date eventTimestamp, Lea lea) {
        this.eventRefId = eventRefId;
        this.eventSchoolYear = eventSchoolYear;
        this.eventType = eventType;
        this.eventTimestamp = eventTimestamp;
        this.lea = lea;
    }

    public String getEventRefId() {
        return eventRefId;
    }

    public void setEventRefId(String eventRefId) {
        this.eventRefId = eventRefId;
    }

    public Integer getEventSchoolYear() {
        return eventSchoolYear;
    }

    public void setEventSchoolYear(Integer eventSchoolYear) {
        this.eventSchoolYear = eventSchoolYear;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Lea getLea() {
        return lea;
    }

    public void setLea(Lea lea) {
        this.lea = lea;
    }

    public String getObjectRefId() {
        return objectRefId;
    }

    public void setObjectRefId(String objectRefId) {
        this.objectRefId = objectRefId;
    }

    public Integer getObjectSchoolYear() {
        return objectSchoolYear;
    }

    public void setObjectSchoolYear(Integer objectSchoolYear) {
        this.objectSchoolYear = objectSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        EventLog eventLog = (EventLog) o;

        if(eventRefId != null ? !eventRefId.equals(eventLog.eventRefId) : eventLog.eventRefId != null) return false;
        if(eventSchoolYear != null ? !eventSchoolYear.equals(eventLog.eventSchoolYear) : eventLog.eventSchoolYear != null)
            return false;
        if(eventType != null ? !eventType.equals(eventLog.eventType) : eventLog.eventType != null) return false;
        if(eventTimestamp != null ? !eventTimestamp.equals(eventLog.eventTimestamp) : eventLog.eventTimestamp != null)
            return false;
        if(lea != null ? !lea.equals(eventLog.lea) : eventLog.lea != null) return false;
        if(objectRefId != null ? !objectRefId.equals(eventLog.objectRefId) : eventLog.objectRefId != null) return false;
        return objectSchoolYear != null ? objectSchoolYear.equals(eventLog.objectSchoolYear) : eventLog.objectSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = eventRefId != null ? eventRefId.hashCode() : 0;
        result = 31 * result + (eventSchoolYear != null ? eventSchoolYear.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (eventTimestamp != null ? eventTimestamp.hashCode() : 0);
        result = 31 * result + (lea != null ? lea.hashCode() : 0);
        result = 31 * result + (objectRefId != null ? objectRefId.hashCode() : 0);
        result = 31 * result + (objectSchoolYear != null ? objectSchoolYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventLog{" + "eventRefId='" + eventRefId + '\'' + ", eventSchoolYear=" + eventSchoolYear + ", eventType='" + eventType + '\'' + ", eventTimestamp=" + eventTimestamp + ", lea=" + lea + ", objectRefId='" + objectRefId + '\'' + ", objectSchoolYear=" + objectSchoolYear + '}';
    }
}