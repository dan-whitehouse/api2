package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.School;

public class SchoolWrapper {
	private String districtId;
	private School school;

	public SchoolWrapper() {
	}

	public SchoolWrapper(String districtId, School school) {
		this.districtId = districtId;
		this.school = school;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}