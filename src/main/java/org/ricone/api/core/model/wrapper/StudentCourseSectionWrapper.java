package org.ricone.api.core.model.wrapper;


import org.ricone.api.core.model.StudentCourseSection;

public class StudentCourseSectionWrapper {
	private String districtId;
	private StudentCourseSection studentCourseSection;

	public StudentCourseSectionWrapper() {
	}

	public StudentCourseSectionWrapper(String districtId, StudentCourseSection studentCourseSection) {
		this.districtId = districtId;
		this.studentCourseSection = studentCourseSection;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public StudentCourseSection getStudentCourseSection() {
		return studentCourseSection;
	}

	public void setStudentCourseSection(StudentCourseSection studentCourseSection) {
		this.studentCourseSection = studentCourseSection;
	}
}