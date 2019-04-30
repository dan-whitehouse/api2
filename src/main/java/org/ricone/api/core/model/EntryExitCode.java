package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.EntryExitCodeComposite;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "entryexitcode")
@IdClass(EntryExitCodeComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntryExitCode implements Serializable {
	private static final long serialVersionUID = -6732821891680730343L;
	
	@Column(name = "EntryExitCodeRefId", unique = true, nullable = false, length = 64)
	@Id private String entryExitCodeRefId;
	
	@Column(name = "EntryExitCodeSchoolYear", nullable = false, length = 6)
	@Id private Integer entryExitCodeSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="StudentEnrollmentRefId", referencedColumnName="studentEnrollmentRefId", nullable = false),
		@JoinColumn(name="StudentEnrollmentSchoolYear", referencedColumnName="studentEnrollmentSchoolYear", nullable = false)
	})
	private StudentEnrollment studentEnrollment;
	
	@Column(name = "SystemTypeCode", length = 50)
	private String systemTypeCode;
	
	@Column(name = "EntryExitType", length = 50)
	private String entryExitType;
	
	@Column(name = "Code", length = 50)
	private String code;

	public EntryExitCode() {
	}

	public EntryExitCode(String entryExitCodeRefId, Integer entryExitCodeSchoolYear, StudentEnrollment studentEnrollment, String systemTypeCode, String entryExitType, String code) {
		super();
		this.entryExitCodeRefId = entryExitCodeRefId;
		this.entryExitCodeSchoolYear = entryExitCodeSchoolYear;
		this.studentEnrollment = studentEnrollment;
		this.systemTypeCode = systemTypeCode;
		this.entryExitType = entryExitType;
		this.code = code;
	}

	public String getEntryExitCodeRefId() {
		return entryExitCodeRefId;
	}
	public void setEntryExitCodeRefId(String entryExitCodeRefId) {
		this.entryExitCodeRefId = entryExitCodeRefId;
	}

	public Integer getEntryExitCodeSchoolYear() {
		return entryExitCodeSchoolYear;
	}
	public void setEntryExitCodeSchoolYear(Integer entryExitCodeSchoolYear) {
		this.entryExitCodeSchoolYear = entryExitCodeSchoolYear;
	}

	public StudentEnrollment getStudentEnrollment() {
		return studentEnrollment;
	}
	public void setStudentEnrollment(StudentEnrollment studentEnrollment) {
		this.studentEnrollment = studentEnrollment;
	}

	public String getSystemTypeCode() {
		return systemTypeCode;
	}
	public void setSystemTypeCode(String systemTypeCode) {
		this.systemTypeCode = systemTypeCode;
	}

	public String getEntryExitType() {
		return entryExitType;
	}
	public void setEntryExitType(String entryExitType) {
		this.entryExitType = entryExitType;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "EntryExitCode [entryExitCodeRefId=" + entryExitCodeRefId + ", entryExitCodeSchoolYear=" + entryExitCodeSchoolYear + ", studentEnrollment=" + studentEnrollment + ", systemTypeCode=" + systemTypeCode + ", entryExitType=" + entryExitType + ", code=" + code + "]";
	}
}
