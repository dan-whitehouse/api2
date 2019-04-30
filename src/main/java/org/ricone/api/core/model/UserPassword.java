package org.ricone.api.core.model;

//import api.model.core.composite.UserPasswordComposite;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "userPassword")
//@IdClass(UserPasswordComposite.class)
public class UserPassword implements Serializable {
	private static final long serialVersionUID = 8607824899883665976L;

	@Column(name = "entityRefId", nullable = false)
	@Id private String entityRefId;
	
	/*@Column(name = "entitySchoolYear", length = 6)
	@Id private Integer entitySchoolYear;*/
	
	@Column(name = "entityType", nullable = false)
	@Id private String entityType;
	
	@Column(name = "appId", nullable = false)
	@Id private String appId;

	@Column(name = "tempPassword", nullable = false)
	private String tempPassword;
	
	@Column(name = "expiryDate") //, nullable = false removed by PROD-4203
	private Date expiryDate;
	
	@Column(name = "lastRetrieved")
	private Date lastRetrieved;
	
	@Column(name = "generationDate", nullable = false)
	private Date generationDate;

	// Transient
	@Transient
	private Staff staff;
	@Transient
	private Student student;

	public UserPassword() {
	}

	public UserPassword(String entityRefId, /*Integer entitySchoolYear,*/ String entityType, String appId, String tempPassword, Date expiryDate, Date lastRetrieved, Date generationDate, Staff staff) {
		this.entityRefId = entityRefId;
		//this.entitySchoolYear = entitySchoolYear;
		this.entityType = entityType;
		this.appId = appId;
		this.tempPassword = tempPassword;
		this.expiryDate = expiryDate;
		this.lastRetrieved = lastRetrieved;
		this.generationDate = generationDate;
		this.staff = staff;
	}

	public UserPassword(String entityRefId, /*Integer entitySchoolYear,*/ String entityType, String appId, String tempPassword, Date expiryDate, Date lastRetrieved, Date generationDate, Student student) {
		this.entityRefId = entityRefId;
		//this.entitySchoolYear = entitySchoolYear;
		this.entityType = entityType;
		this.appId = appId;
		this.tempPassword = tempPassword;
		this.expiryDate = expiryDate;
		this.lastRetrieved = lastRetrieved;
		this.generationDate = generationDate;
		this.student = student;
	}

	/*
		Needed to add this constructor once removing the UserPasswordComposite class.
		User for lookups in the XAppProvisioningService provisionStaffsBySchool/provisionStudentsBySchool methods
	*/
	public UserPassword(String entityRefId, String entityType, String appId) {
		this.entityRefId = entityRefId;
		this.entityType = entityType;
		this.appId = appId;
	}

	public String getEntityRefId() {
		return entityRefId;
	}
	public void setEntityRefId(String entityRefId) {
		this.entityRefId = entityRefId;
	}

	/*public Integer getEntitySchoolYear() { return entitySchoolYear; }
	public void setEntitySchoolYear(Integer entitySchoolYear) { this.entitySchoolYear = entitySchoolYear; }*/

	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getLastRetrieved() {
		return lastRetrieved;
	}
	public void setLastRetrieved(Date lastRetrieved) {
		this.lastRetrieved = lastRetrieved;
	}

	public Date getGenerationDate() {
		return generationDate;
	}
	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	@Transient
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Transient
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "UserPassword{" + "entityRefId='" + entityRefId + '\'' + ", entityType=" + entityType + ", appId='" + appId + '\'' + ", tempPassword='" + tempPassword + '\'' + ", expiryDate=" + expiryDate + ", lastRetrieved=" + lastRetrieved + ", generationDate=" + generationDate + ", staff=" + staff + ", student=" + student + '}';
	}
}
