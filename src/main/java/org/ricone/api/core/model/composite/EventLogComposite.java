package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */

public class EventLogComposite implements Serializable {
    private static final long serialVersionUID = 7762706758841800156L;

    private String eventRefId;
    private Integer eventSchoolYear;

    public EventLogComposite() {

    }

    public EventLogComposite(String eventRefId, Integer eventSchoolYear) {
        this.eventRefId = eventRefId;
        this.eventSchoolYear = eventSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        EventLogComposite that = (EventLogComposite) o;

        if(eventRefId != null ? !eventRefId.equals(that.eventRefId) : that.eventRefId != null) return false;
        return eventSchoolYear != null ? eventSchoolYear.equals(that.eventSchoolYear) : that.eventSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = eventRefId != null ? eventRefId.hashCode() : 0;
        result = 31 * result + (eventSchoolYear != null ? eventSchoolYear.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventLogComposite{" + "eventRefId='" + eventRefId + '\'' + ", eventSchoolYear=" + eventSchoolYear + '}';
    }
}
