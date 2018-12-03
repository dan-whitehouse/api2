package org.ricone.api.core.model.wrapper;

import org.ricone.api.core.model.StudentContact;

public class StudentContactWrapper {
	private String districtId;
	private StudentContact studentContact;

	public StudentContactWrapper() {
	}

	public StudentContactWrapper(String districtId, StudentContact studentContact) {
		this.districtId = districtId;
		this.studentContact = studentContact;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public StudentContact getStudentContact() {
		return studentContact;
	}

	public void setStudentContact(StudentContact studentContact) {
		this.studentContact = studentContact;
	}
}