package org.ricone.api.oneroster.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"enrollments"
})
public class EnrollmentsResponse implements Serializable
{

@JsonProperty("enrollments")
private List<Enrollment> enrollments = new ArrayList<Enrollment>();
private final static long serialVersionUID = -3656792804387663344L;

/**
* No args constructor for use in serialization
* 
*/
public EnrollmentsResponse() {
}

/**
* 
* @param enrollments
*/
public EnrollmentsResponse(List<Enrollment> enrollments) {
super();
this.enrollments = enrollments;
}

@JsonProperty("enrollments")
public List<Enrollment> getEnrollments() {
return enrollments;
}

@JsonProperty("enrollments")
public void setEnrollments(List<Enrollment> enrollments) {
this.enrollments = enrollments;
}

}