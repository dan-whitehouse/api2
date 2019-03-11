package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "onerosterv1p1_classacademicsession")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QClassAcademicSession implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "ClassAcademicSessionId", unique = true, nullable = false, length = 64)
	@Id private String classAcademicSessionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ClassId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "ClassSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QClass clazz;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "AcademicSessionId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "AcademicSessionSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QAcademicSession academicSession;

	public QClassAcademicSession() {
	}

	public QClassAcademicSession(String classAcademicSessionId, QClass clazz, QAcademicSession academicSession) {
		this.classAcademicSessionId = classAcademicSessionId;
		this.clazz = clazz;
		this.academicSession = academicSession;
	}

	public String getClassAcademicSessionId() {
		return classAcademicSessionId;
	}

	public void setClassAcademicSessionId(String classAcademicSessionId) {
		this.classAcademicSessionId = classAcademicSessionId;
	}

	public QClass getClazz() {
		return clazz;
	}

	public void setClazz(QClass clazz) {
		this.clazz = clazz;
	}

	public QAcademicSession getAcademicSession() {
		return academicSession;
	}

	public void setAcademicSession(QAcademicSession academicSession) {
		this.academicSession = academicSession;
	}
}
