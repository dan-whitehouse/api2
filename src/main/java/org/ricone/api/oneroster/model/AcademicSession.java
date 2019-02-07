package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "startDate", "endDate", "type", "parent", "children", "schoolYear"})
public class AcademicSession extends Base implements Serializable {
	private final static long serialVersionUID = 5476752215341220106L;
	@JsonProperty("title")
	private String title;
	@JsonProperty("startDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDate;
	@JsonProperty("endDate")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endDate;
	@JsonProperty("type")
	private SessionType type;
	@JsonProperty("parent")
	private GUIDRef parent;
	@JsonProperty("children")
	private List<GUIDRef> children = new ArrayList<>();
	@JsonProperty("schoolYear")
	private String schoolYear;

	/**
	 * No args constructor for use in serialization
	 */
	public AcademicSession() {
	}

	/**
	 * @param schoolYear
	 * @param startDate
	 * @param title
	 * @param children
	 * @param parent
	 * @param endDate
	 * @param type
	 */
	public AcademicSession(String title, LocalDate startDate, LocalDate endDate, SessionType type, GUIDRef parent, List<GUIDRef> children, String schoolYear) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.parent = parent;
		this.children = children;
		this.schoolYear = schoolYear;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("startDate")
	public LocalDate getStartDate() {
		return startDate;
	}

	@JsonProperty("startDate")
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("endDate")
	public LocalDate getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@JsonProperty("type")
	public SessionType getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(SessionType type) {
		this.type = type;
	}

	@JsonProperty("parent")
	public GUIDRef getParent() {
		return parent;
	}

	@JsonProperty("parent")
	public void setParent(GUIDRef parent) {
		this.parent = parent;
	}

	@JsonProperty("children")
	public List<GUIDRef> getChildren() {
		return children;
	}

	@JsonProperty("children")
	public void setChildren(List<GUIDRef> children) {
		this.children = children;
	}

	@JsonProperty("schoolYear")
	public String getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
}