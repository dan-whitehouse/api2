package org.ricone.api.core.model.wrapper;


import org.ricone.api.core.model.Student;

public class StudentWrapper {
	private String districtId;
	private Student student;

	public StudentWrapper() {
	}

	public StudentWrapper(String districtId, Student student) {
		this.districtId = districtId;
		this.student = student;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}