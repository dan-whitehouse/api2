package org.ricone.api.core.model.wrapper;


import org.ricone.api.core.model.CourseSection;

public class CourseSectionWrapper {
	private String districtId;
	private CourseSection courseSection;

	public CourseSectionWrapper() {
	}

	public CourseSectionWrapper(String districtId, CourseSection courseSection) {
		this.districtId = districtId;
		this.courseSection = courseSection;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public CourseSection getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(CourseSection courseSection) {
		this.courseSection = courseSection;
	}
}