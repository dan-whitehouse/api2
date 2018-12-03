package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.Course;

public class CourseWrapper {
	private String districtId;
	private Course course;

	public CourseWrapper() {
	}

	public CourseWrapper(String districtId, Course course) {
		this.districtId = districtId;
		this.course = course;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}