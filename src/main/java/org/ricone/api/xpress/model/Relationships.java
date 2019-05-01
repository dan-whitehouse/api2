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
@JsonPropertyOrder({"relationship"})
public class Relationships {
	@JsonProperty("relationship")
	@ApiModelProperty(position = 1, value = "The nature of the person's relationship to a learner. The learner may be an Early Learning Child, K12 Student, Postsecondary Student, or an adult learner in a workforce education program")
	private List<Relationship> relationship;

	public Relationships() {
		relationship = new ArrayList<>();
	}

	public Relationships(List<Relationship> relationship) {
		super();
		this.relationship = relationship;
	}

	@JsonProperty("relationship")
	public List<Relationship> getRelationship() {
		return relationship;
	}

	@JsonProperty("relationship")
	public void setRelationship(List<Relationship> relationship) {
		this.relationship = relationship;
	}

	@Override
	public String toString() {
		return "Relationships{" + "relationship=" + relationship + '}';
	}
}