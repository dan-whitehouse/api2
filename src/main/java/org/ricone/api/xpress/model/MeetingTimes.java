/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"meetingTime"})
public class MeetingTimes {
	@JsonProperty("meetingTime")
	@ApiModelProperty(position = 1, value = "Information about the periodic days and meeting times of a section")
	private List<MeetingTime> meetingTime;

	public MeetingTimes() {
		meetingTime = new ArrayList<>();
	}

	public MeetingTimes(List<MeetingTime> meetingTime) {
		super();
		this.meetingTime = meetingTime;
	}

	@JsonProperty("meetingTime")
	public List<MeetingTime> getMeetingTime() {
		return meetingTime;
	}

	@JsonProperty("meetingTime")
	public void setMeetingTime(List<MeetingTime> meetingTime) {
		this.meetingTime = meetingTime;
	}

	@Override
	public String toString() {
		return "MeetingTimes{" + "meetingTime=" + meetingTime + '}';
	}
}