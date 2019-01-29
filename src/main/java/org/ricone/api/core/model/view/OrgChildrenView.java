package org.ricone.api.core.model.view;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;
import org.ricone.api.core.model.view.composite.ChildComposite;

import javax.persistence.*;
import java.io.Serializable;

@IdClass(ChildComposite.class)
@Immutable @Entity @Table(name = "orgchildrenview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OrgChildrenView implements Serializable {
	private static final long serialVersionUID = -1680434938122940174L;

	@Column(name = "ChildId")
	@Id private String childId;

	@Column(name = "ChildSchoolYear")
	@Id private Integer childSchoolYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name="SourcedId", referencedColumnName="sourcedId", nullable = false),
			@JoinColumn(name="SourcedSchoolYear", referencedColumnName="sourcedSchoolYear", nullable = false),
	})
	private OrgView orgView;

	public OrgChildrenView() {
	}

	public OrgChildrenView(String childId, Integer childSchoolYear, OrgView orgView) {
		this.childId = childId;
		this.childSchoolYear = childSchoolYear;
		this.orgView = orgView;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public Integer getChildSchoolYear() {
		return childSchoolYear;
	}

	public void setChildSchoolYear(Integer childSchoolYear) {
		this.childSchoolYear = childSchoolYear;
	}

	public OrgView getOrgView() {
		return orgView;
	}

	public void setOrgView(OrgView orgView) {
		this.orgView = orgView;
	}
}
