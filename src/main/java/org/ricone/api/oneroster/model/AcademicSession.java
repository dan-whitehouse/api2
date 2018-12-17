package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "title", "startDate", "endDate", "type", "parent", "children", "schoolYear"})
public class AcademicSession extends Base implements Serializable {

	@JsonProperty("title")
	private String title;
	@JsonProperty("startDate")
	private String startDate;
	@JsonProperty("endDate")
	private String endDate;
	@JsonProperty("type")
	private SessionType type;
	@JsonProperty("parent")
	private GUIDRef parent;
	@JsonProperty("children")
	private List<GUIDRef> children = new ArrayList<GUIDRef>();
	@JsonProperty("schoolYear")
	private String schoolYear;
	private final static long serialVersionUID = 5476752215341220106L;

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
	public AcademicSession(String title, String startDate, String endDate, SessionType type, GUIDRef parent, List<GUIDRef> children, String schoolYear) {
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
	public String getStartDate() {
		return startDate;
	}

	@JsonProperty("startDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(String endDate) {
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