package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.EventLog;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.1
 * @since 2018-11-05
 */

public class EventLogWrapper<T extends EventLog> {
	private String districtId;
	private T eventLog;

	public EventLogWrapper() {
	}

	public EventLogWrapper(String districtId, T eventLog) {
		this.districtId = districtId;
		this.eventLog = eventLog;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public T getEventLog() {
		return eventLog;
	}

	public void setEventLog(T eventLog) {
		this.eventLog = eventLog;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		EventLogWrapper<?> that = (EventLogWrapper<?>) o;

		if(districtId != null ? !districtId.equals(that.districtId) : that.districtId != null) return false;
		return eventLog != null ? eventLog.equals(that.eventLog) : that.eventLog == null;
	}

	@Override
	public int hashCode() {
		int result = districtId != null ? districtId.hashCode() : 0;
		result = 31 * result + (eventLog != null ? eventLog.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "EventLogWrapper{" + "districtId='" + districtId + '\'' + ", changesSince=" + eventLog + '}';
	}
}