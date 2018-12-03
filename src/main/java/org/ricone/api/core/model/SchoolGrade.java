package org.ricone.api.core.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ricone.api.core.model.composite.SchoolGradeComposite;

import javax.persistence.*;

@Entity
@Table(name = "schoolgrade")
@IdClass(SchoolGradeComposite.class)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SchoolGrade implements java.io.Serializable {
	private static final long serialVersionUID = 7441130279606831553L;
	
	@Column(name = "SchoolGradeRefId", unique = true, nullable = false, length = 64)
	@Id
    private String schoolGradeRefId;
	
	@Column(name = "SchoolGradeSchoolYear", length = 6)
	@Id
    private Integer schoolGradeSchoolYear;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SchoolRefId", referencedColumnName="schoolRefId", nullable = false),
		@JoinColumn(name="SchoolSchoolYear", referencedColumnName="schoolSchoolYear", nullable = false)
	})
	private School school;
	
	@Column(name = "GradeLevelCode", nullable = false, length = 50)
	private String gradeLevelCode;

	public SchoolGrade() {
	}

	public SchoolGrade(String schoolGradeRefId, Integer schoolGradeSchoolYear, School school, String gradeLevelCode) {
		super();
		this.schoolGradeRefId = schoolGradeRefId;
		this.schoolGradeSchoolYear = schoolGradeSchoolYear;
		this.school = school;
		this.gradeLevelCode = gradeLevelCode;
	}

	public String getSchoolGradeRefId() {
		return this.schoolGradeRefId;
	}
	public void setSchoolGradeRefId(String schoolGradeRefId) {
		this.schoolGradeRefId = schoolGradeRefId;
	}

	public Integer getSchoolGradeSchoolYear() {
		return schoolGradeSchoolYear;
	}
	public void setSchoolGradeSchoolYear(Integer schoolGradeSchoolYear) {
		this.schoolGradeSchoolYear = schoolGradeSchoolYear;
	}

	public School getSchool() {
		return this.school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public String getGradeLevelCode() {
		return this.gradeLevelCode;
	}
	public void setGradeLevelCode(String gradeLevelCode) {
		this.gradeLevelCode = gradeLevelCode;
	}

	@Override
	public String toString() {
		return "SchoolGrade [schoolGradeRefId=" + schoolGradeRefId + ", schoolGradeSchoolYear=" + schoolGradeSchoolYear + ", school=" + school + ", gradeLevelCode=" + gradeLevelCode + "]";
	}
}
