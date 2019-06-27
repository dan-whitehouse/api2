package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(position = 5, value = "", example = "Spring Term")
	private String title;
	@JsonProperty("startDate")
	@ApiModelProperty(position = 6, value = "", example = "2019-01-01")
	private LocalDate startDate;
	@JsonProperty("endDate")
	@ApiModelProperty(position = 7, value = "", example = "2019-04-30")
	private LocalDate endDate;
	@JsonProperty("type")
	@ApiModelProperty(position = 8, value = "The set of permitted tokens for the type of academic session")
	private SessionType type;
	@JsonProperty("parent")
	@ApiModelProperty(position = 9, value = "Link to parent AcademicSession i.e. an AcademicSession 'sourcedId'")
	private GUIDRef parent;
	@JsonProperty("children")
	@ApiModelProperty(position = 10, value = "Links to children AcademicSession i.e. an AcademicSession 'sourcedId'")
	private List<GUIDRef> children = new ArrayList<>();
	@JsonProperty("schoolYear")
	@ApiModelProperty(position = 11, value = "The school year for the academic session. This year should include the school year end", example = "2019")
	private Integer schoolYear;

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
	public AcademicSession(String title, LocalDate startDate, LocalDate endDate, SessionType type, GUIDRef parent, List<GUIDRef> children, Integer schoolYear) {
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
	public Integer getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}
}