/*
 * RIC One File Bridge
 * Version: 1.0.0 Build 20170604-1
 * Copyright © 2017 New York State Education Department
 * Created At Northeastern Regional Information Center By Daniel Whitehouse
 */

package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"bellScheduleDay"})
public class ClassMeetingDays {
	@JsonProperty("bellScheduleDay")
	@ApiModelProperty(position = 1, value = "The day(s) of the week (e.g., Monday, Wednesday) that the class meets or an indication that a class meets 'out-of-school' or 'self-paced'")
	private String bellScheduleDay;

	public ClassMeetingDays() {
	}

	public ClassMeetingDays(String bellScheduleDay) {
		super();
		this.bellScheduleDay = bellScheduleDay;
	}

	@JsonProperty("bellScheduleDay")
	public String getBellScheduleDay() {
		return bellScheduleDay;
	}

	@JsonProperty("bellScheduleDay")
	public void setBellScheduleDay(String bellScheduleDay) {
		this.bellScheduleDay = bellScheduleDay;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(bellScheduleDay).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "ClassMeetingDays{" + "bellScheduleDay='" + bellScheduleDay + '\'' + '}';
	}
}