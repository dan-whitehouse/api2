package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.SchoolCalendar;

public class SchoolCalendarWrapper {
	private String districtId;
	private SchoolCalendar schoolCalendar;

	public SchoolCalendarWrapper() {
	}

	public SchoolCalendarWrapper(String districtId, SchoolCalendar schoolCalendar) {
		this.districtId = districtId;
		this.schoolCalendar = schoolCalendar;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public SchoolCalendar getSchoolCalendar() {
		return schoolCalendar;
	}

	public void setSchoolCalendar(SchoolCalendar schoolCalendar) {
		this.schoolCalendar = schoolCalendar;
	}
}