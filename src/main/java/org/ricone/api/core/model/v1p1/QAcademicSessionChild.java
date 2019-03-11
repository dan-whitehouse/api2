package org.ricone.api.core.model.v1p1;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "onerosterv1p1_academicsessionchild")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QAcademicSessionChild implements java.io.Serializable {
	private static final long serialVersionUID = -2033689116210795367L;

	@Column(name = "AcademicSessionChildId", unique = true, nullable = false, length = 64)
	@Id private String academicSessionChildId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "ChildId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "ChildSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QAcademicSession child;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "AcademicSessionId", referencedColumnName = "sourcedId", nullable = false),
			@JoinColumn(name = "AcademicSessionSchoolYear", referencedColumnName = "sourcedSchoolYear", nullable = false)
	})
	private QAcademicSession academicSession;

	public QAcademicSessionChild() {
	}

	public QAcademicSessionChild(String academicSessionChildId, QAcademicSession child, QAcademicSession academicSession) {
		this.academicSessionChildId = academicSessionChildId;
		this.child = child;
		this.academicSession = academicSession;
	}

	public String getAcademicSessionChildId() {
		return academicSessionChildId;
	}

	public void setAcademicSessionChildId(String academicSessionChildId) {
		this.academicSessionChildId = academicSessionChildId;
	}

	public QAcademicSession getChild() {
		return child;
	}

	public void setChild(QAcademicSession child) {
		this.child = child;
	}

	public QAcademicSession getAcademicSession() {
		return academicSession;
	}

	public void setAcademicSession(QAcademicSession academicSession) {
		this.academicSession = academicSession;
	}
}
