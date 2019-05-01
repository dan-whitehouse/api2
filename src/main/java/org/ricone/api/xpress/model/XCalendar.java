/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "schoolRefId", "schoolYear", "sessions", "metadata"})
@JsonRootName(value = "xCalendar")
public class XCalendar extends XWrapper {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	@ApiModelProperty(position = 1, value = "The refId of the calendar")
	private String refId;

	@JsonProperty("schoolRefId")
	@ApiModelProperty(position = 2, value = "The refId of the school to which this calendar applies. ")
	private String schoolRefId;

	@JsonProperty("schoolYear")
	@ApiModelProperty(position = 3, value = "The school year for the calendar")
	private String schoolYear;

	@JsonProperty("sessions")
	@ApiModelProperty(position = 4, value = "A list of sessions for the calendar")
	private Sessions sessions;

	@JsonProperty("metadata")
	@ApiModelProperty(position = 5, value = "")
	private Metadata metadata;

	public XCalendar() {
	}

	public XCalendar(String refId, String schoolRefId, String schoolYear, Sessions sessions, Metadata metadata) {
		this.refId = refId;
		this.schoolRefId = schoolRefId;
		this.schoolYear = schoolYear;
		this.sessions = sessions;
		this.metadata = metadata;
	}

	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	public String getRefId() {
		return refId;
	}

	@JsonProperty("@refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("schoolRefId")
	public String getSchoolRefId() {
		return schoolRefId;
	}

	@JsonProperty("schoolRefId")
	public void setSchoolRefId(String schoolRefId) {
		this.schoolRefId = schoolRefId;
	}

	@JsonProperty("schoolYear")
	public String getSchoolYear() {
		return schoolYear;
	}

	@JsonProperty("schoolYear")
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	@JsonProperty("sessions")
	public Sessions getSessions() {
		return sessions;
	}

	@JsonProperty("sessions")
	public void setSessions(Sessions sessions) {
		this.sessions = sessions;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(refId, schoolRefId, schoolYear, sessions, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XCalendar{" + "refId='" + refId + '\'' + ", schoolRefId='" + schoolRefId + '\'' + ", schoolYear='" + schoolYear + '\'' + ", sessions=" + sessions + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XCalendar)) return false;

		XCalendar xCalendar = (XCalendar) o;

		if(refId != null ? !refId.equals(xCalendar.refId) : xCalendar.refId != null) return false;
		if(schoolRefId != null ? !schoolRefId.equals(xCalendar.schoolRefId) : xCalendar.schoolRefId != null)
			return false;
		if(schoolYear != null ? !schoolYear.equals(xCalendar.schoolYear) : xCalendar.schoolYear != null) return false;
		if(sessions != null ? !sessions.equals(xCalendar.sessions) : xCalendar.sessions != null) return false;
		return metadata != null ? metadata.equals(xCalendar.metadata) : xCalendar.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (schoolRefId != null ? schoolRefId.hashCode() : 0);
		result = 31 * result + (schoolYear != null ? schoolYear.hashCode() : 0);
		result = 31 * result + (sessions != null ? sessions.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}