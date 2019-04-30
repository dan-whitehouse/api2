package org.ricone.api.core.model;

import org.ricone.api.core.model.composite.SchemaVersionComposite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "schemaversion")
@IdClass(SchemaVersionComposite.class)
public class SchemaVersion implements Serializable {
	private static final long serialVersionUID = -5220447978182945197L;
	
	@Column(name = "Major")
	@Id
    private Integer major;
	
	@Column(name = "Minor")
	@Id
    private Integer minor;
	
	@Column(name = "BugFix")
	@Id
    private Integer bugFix;
	
	@Column(name = "Description")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DateApplied")
	private Date dateApplied;

	public SchemaVersion() {
	}

	public SchemaVersion(Integer major, Integer minor, Integer bugFix, String description, Date dateApplied) {
		super();
		this.major = major;
		this.minor = minor;
		this.bugFix = bugFix;
		this.description = description;
		this.dateApplied = dateApplied;
	}

	
	public Integer getMajor() {
		return major;
	}
	public void setMajor(Integer major) {
		this.major = major;
	}

	public Integer getMinor() {
		return minor;
	}
	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	public Integer getBugFix() {
		return bugFix;
	}
	public void setBugFix(Integer bugFix) {
		this.bugFix = bugFix;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

	@Override
	public String toString() {
		return "SchemaVersion [major=" + major + ", minor=" + minor + ", bugFix=" + bugFix + ", description=" + description + ", dateApplied=" + dateApplied + "]";
	}
}
