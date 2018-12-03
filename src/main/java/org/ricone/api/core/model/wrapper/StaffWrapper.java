package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.Staff;

public class StaffWrapper {
	private String districtId;
	private Staff staff;

	public StaffWrapper() {
	}

	public StaffWrapper(String districtId, Staff staff) {
		this.districtId = districtId;
		this.staff = staff;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}