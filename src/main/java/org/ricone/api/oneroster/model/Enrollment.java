package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "role", "primary", "user", "class", "school", "beginDate", "endDate"})
public class Enrollment extends Base implements Serializable {
	private final static long serialVersionUID = -471764017962444775L;
	@JsonProperty("role")
	@ApiModelProperty(position = 5, value = "The set of permitted tokens for the type of role")
	private RoleType role;
	@JsonProperty("primary")
	@ApiModelProperty(position = 6, value = "Applicable only to teachers. Only one teacher should be designated as the primary teacher for a class in the period defined by the begin/end dates.", example = "")
	private Boolean primary;
	@JsonProperty("user")
	@ApiModelProperty(position = 7, value = "Link to the enrolled User i.e. a User 'sourcedId'")
	private GUIDRef user;
	@JsonProperty("class")
	@ApiModelProperty(position = 8, value = "Link to the class on which the user is enrolled i.e. a Class 'sourcedId'")
	private GUIDRef _class;
	@JsonProperty("school")
	@ApiModelProperty(position = 9, value = "Link to the school at which the class is being provided i.e. an Org 'sourcedId'")
	private GUIDRef school;
	@JsonProperty("beginDate")
	@ApiModelProperty(position = 10, value = "The start date for the enrollment (inclusive). This date must be within the period of the associated Academic Session for the class (Term/Semester/SchoolYear)", example = "2019-04-23")
	private LocalDate beginDate;
	@JsonProperty("endDate")
	@ApiModelProperty(position = 11, value = "The end date for the enrollment (exclusive). This date must be within the period of the associated Academic Session for the class (Term/Semester/SchoolYear)", example = "2020-03-31")
	private LocalDate endDate;

	/**
	 * No args constructor for use in serialization
	 */
	public Enrollment() {
	}

	/**
	 * @param school
	 * @param _class
	 * @param primary
	 * @param role
	 * @param endDate
	 * @param beginDate
	 * @param user
	 */
	public Enrollment(RoleType role, Boolean primary, GUIDRef user, GUIDRef _class, GUIDRef school, LocalDate beginDate, LocalDate endDate) {
		super();
		this.role = role;
		this.primary = primary;
		this.user = user;
		this._class = _class;
		this.school = school;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	@JsonProperty("role")
	public RoleType getRole() {
		return role;
	}

	@JsonProperty("role")
	public void setRole(RoleType role) {
		this.role = role;
	}

	@JsonProperty("primary")
	public Boolean getPrimary() {
		return primary;
	}

	@JsonProperty("primary")
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	@JsonProperty("user")
	public GUIDRef getUser() {
		return user;
	}

	@JsonProperty("user")
	public void setUser(GUIDRef user) {
		this.user = user;
	}

	@JsonProperty("class")
	public GUIDRef getClass_() {
		return _class;
	}

	@JsonProperty("class")
	public void setClass_(GUIDRef _class) {
		this._class = _class;
	}

	@JsonProperty("school")
	public GUIDRef getSchool() {
		return school;
	}

	@JsonProperty("school")
	public void setSchool(GUIDRef school) {
		this.school = school;
	}

	@JsonProperty("beginDate")
	public LocalDate getBeginDate() {
		return beginDate;
	}

	@JsonProperty("beginDate")
	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	@JsonProperty("endDate")
	public LocalDate getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
