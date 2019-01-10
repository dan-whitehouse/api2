package org.ricone.api.core.model.wrapper;


import org.ricone.api.core.model.SchoolCalendarSession;

public class SchoolCalendarSessionWrapper {
	private String districtId;
	private SchoolCalendarSession schoolCalendarSession;

	public SchoolCalendarSessionWrapper() {
	}

	public SchoolCalendarSessionWrapper(String districtId, SchoolCalendarSession schoolCalendarSession) {
		this.districtId = districtId;
		this.schoolCalendarSession = schoolCalendarSession;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public SchoolCalendarSession getSchoolCalendarSession() {
		return schoolCalendarSession;
	}

	public void setSchoolCalendarSession(SchoolCalendarSession schoolCalendarSession) {
		this.schoolCalendarSession = schoolCalendarSession;
	}
}