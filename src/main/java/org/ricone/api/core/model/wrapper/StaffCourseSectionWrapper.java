package org.ricone.api.core.model.wrapper;


import org.ricone.api.core.model.StaffCourseSection;
import org.ricone.api.core.model.StudentCourseSection;

public class StaffCourseSectionWrapper {
	private String districtId;
	private StaffCourseSection staffCourseSection;

	public StaffCourseSectionWrapper() {
	}

	public StaffCourseSectionWrapper(String districtId, StaffCourseSection staffCourseSection) {
		this.districtId = districtId;
		this.staffCourseSection = staffCourseSection;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public StaffCourseSection getStaffCourseSection() {
		return staffCourseSection;
	}

	public void setStaffCourseSection(StaffCourseSection staffCourseSection) {
		this.staffCourseSection = staffCourseSection;
	}
}