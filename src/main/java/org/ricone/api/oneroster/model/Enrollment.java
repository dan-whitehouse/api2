package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"sourcedId", "status", "dateLastModified", "metadata", "role", "primary", "user", "class", "school", "beginDate", "endDate"})
public class Enrollment extends Base implements Serializable {

	@JsonProperty("role")
	private RoleType role;
	@JsonProperty("primary")
	private Boolean primary;
	@JsonProperty("user")
	private GUIDRef user;
	@JsonProperty("class")
	private GUIDRef _class;
	@JsonProperty("school")
	private GUIDRef school;
	@JsonProperty("beginDate")
	private String beginDate;
	@JsonProperty("endDate")
	private String endDate;
	private final static long serialVersionUID = -471764017962444775L;

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
	public Enrollment(RoleType role, Boolean primary, GUIDRef user, GUIDRef _class, GUIDRef school, String beginDate, String endDate) {
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
	public String getBeginDate() {
		return beginDate;
	}

	@JsonProperty("beginDate")
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
