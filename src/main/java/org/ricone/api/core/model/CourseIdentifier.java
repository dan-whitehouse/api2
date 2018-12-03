package org.ricone.api.core.model;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.CourseIdentifierComposite;

import javax.persistence.*;

@Entity
@Table(name = "courseidentifier")
@IdClass(CourseIdentifierComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -2844248936102976357L;
	
	@Column(name = "CourseIdentifierRefId", unique = true, nullable = false, length = 64)
	@Id
    private String courseIdentifierRefId;
	
	@Column(name = "CourseIdentifierSchoolYear", nullable = false, length = 6)
	@Id
    private Integer courseIdentifierSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="CourseRefId", referencedColumnName="courseRefId", nullable = false),
		@JoinColumn(name="CourseSchoolYear", referencedColumnName="courseSchoolYear", nullable = false)
	})
	private Course course;
	
	@Column(name = "IdentificationSystemCode", length = 50)
	private String identificationSystemCode;
	
	@Column(name = "CourseId", length = 50)
	private String courseId;

	public CourseIdentifier() {
	}

	public CourseIdentifier(String courseIdentifierRefId, Integer courseIdentifierSchoolYear, Course course, String identificationSystemCode, String courseId) {
		super();
		this.courseIdentifierRefId = courseIdentifierRefId;
		this.courseIdentifierSchoolYear = courseIdentifierSchoolYear;
		this.course = course;
		this.identificationSystemCode = identificationSystemCode;
		this.courseId = courseId;
	}

	public String getCourseIdentifierRefId() {
		return this.courseIdentifierRefId;
	}
	public void setCourseIdentifierRefId(String courseIdentifierRefId) {
		this.courseIdentifierRefId = courseIdentifierRefId;
	}

	public Integer getCourseIdentifierSchoolYear() {
		return courseIdentifierSchoolYear;
	}
	public void setCourseIdentifierSchoolYear(Integer courseIdentifierSchoolYear) {
		this.courseIdentifierSchoolYear = courseIdentifierSchoolYear;
	}

	public Course getCourse() {
		return this.course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	public String getIdentificationSystemCode() {
		return this.identificationSystemCode;
	}
	public void setIdentificationSystemCode(String identificationSystemCode) {
		this.identificationSystemCode = identificationSystemCode;
	}

	public String getCourseId() {
		return this.courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "CourseIdentifier [courseIdentifierRefId=" + courseIdentifierRefId + ", courseIdentifierSchoolYear=" + courseIdentifierSchoolYear + ", course=" + course + ", identificationSystemCode=" + identificationSystemCode + ", courseId=" + courseId + "]";
	}
}
