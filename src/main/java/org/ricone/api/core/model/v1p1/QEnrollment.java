package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.v1p1.composite.SourcedComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "onerosterv1p1_enrollment")
@IdClass(SourcedComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 100)
public class QEnrollment implements Serializable {
	private static final long serialVersionUID = -2620417938122940193L;

	@Column(name = "SourcedId", unique = true, nullable = false, length = 64)
	@Id private String sourcedId;

	@Column(name = "SourcedSchoolYear", nullable = false, length = 6)
	@Id private Integer sourcedSchoolYear;

	@Column(name = "DistrictId", length = 30)
	private String districtId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "UserId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "UserSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ClassId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "ClassSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QClass clazz;

	@Column(name = "Primary")
	private Boolean primary;

	@Column(name = "BeginDate")
	private LocalDate beginDate;

	@Column(name = "EndDate")
	private LocalDate endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "OrgId", referencedColumnName = "sourcedId"),
			@JoinColumn(name = "OrgSchoolYear", referencedColumnName = "sourcedSchoolYear")
	})
	private QOrg org;

	public QEnrollment() {
	}

	public QEnrollment(String sourcedId, Integer sourcedSchoolYear, String districtId, QUser user, QClass clazz, Boolean primary, LocalDate beginDate, LocalDate endDate, QOrg org) {
		this.sourcedId = sourcedId;
		this.sourcedSchoolYear = sourcedSchoolYear;
		this.districtId = districtId;
		this.user = user;
		this.clazz = clazz;
		this.primary = primary;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.org = org;
	}

	public String getSourcedId() {
		return sourcedId;
	}

	public void setSourcedId(String sourcedId) {
		this.sourcedId = sourcedId;
	}

	public Integer getSourcedSchoolYear() {
		return sourcedSchoolYear;
	}

	public void setSourcedSchoolYear(Integer sourcedSchoolYear) {
		this.sourcedSchoolYear = sourcedSchoolYear;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public QUser getUser() {
		return user;
	}

	public void setUser(QUser user) {
		this.user = user;
	}

	public QClass getClazz() {
		return clazz;
	}

	public void setClazz(QClass clazz) {
		this.clazz = clazz;
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

	public QOrg getOrg() {
		return org;
	}

	public void setOrg(QOrg org) {
		this.org = org;
	}
}
