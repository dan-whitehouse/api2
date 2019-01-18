package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.EnrollmentComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@IdClass(EnrollmentComposite.class)
@Immutable @Entity @Table(name = "enrollmentview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class EnrollmentView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "UserClassId")
	@Id private String userClassId;

	@Column(name = "UserClassSchoolYear")
	@Id private Integer userClassSchoolYear;

	@Column(name = "UserId")
	private String userId;

	@Column(name = "UserSchoolYear")
	private Integer userSchoolYear;

	@Column(name = "Role")
	private String role;

	@Column(name = "ClassId")
	private String classId;

	@Column(name = "ClassSchoolYear")
	private Integer classSchoolYear;

	@Column(name = "Primary")
	private Boolean primary;

	@Column(name = "BeginDate")
	private LocalDate beginDate;

	@Column(name = "EndDate")
	private LocalDate endDate;

	@Column(name = "OrgId")
	private String orgId;

	@Column(name = "OrgSchoolYear")
	private Integer orgSchoolYear;

	public EnrollmentView() {
	}

	public EnrollmentView(String userClassId, Integer userClassSchoolYear, String userId, Integer userSchoolYear, String role, String classId, Integer classSchoolYear, Boolean primary, LocalDate beginDate, LocalDate endDate, String orgId, Integer orgSchoolYear) {
		this.userClassId = userClassId;
		this.userClassSchoolYear = userClassSchoolYear;
		this.userId = userId;
		this.userSchoolYear = userSchoolYear;
		this.role = role;
		this.classId = classId;
		this.classSchoolYear = classSchoolYear;
		this.primary = primary;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.orgId = orgId;
		this.orgSchoolYear = orgSchoolYear;
	}

	public String getUserClassId() {
		return userClassId;
	}

	public void setUserClassId(String userClassId) {
		this.userClassId = userClassId;
	}

	public Integer getUserClassSchoolYear() {
		return userClassSchoolYear;
	}

	public void setUserClassSchoolYear(Integer userClassSchoolYear) {
		this.userClassSchoolYear = userClassSchoolYear;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserSchoolYear() {
		return userSchoolYear;
	}

	public void setUserSchoolYear(Integer userSchoolYear) {
		this.userSchoolYear = userSchoolYear;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Integer getClassSchoolYear() {
		return classSchoolYear;
	}

	public void setClassSchoolYear(Integer classSchoolYear) {
		this.classSchoolYear = classSchoolYear;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgSchoolYear() {
		return orgSchoolYear;
	}

	public void setOrgSchoolYear(Integer orgSchoolYear) {
		this.orgSchoolYear = orgSchoolYear;
	}
}
