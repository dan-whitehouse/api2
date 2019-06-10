package org.ricone.config.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.*;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/*@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
		@JsonSubTypes.Type(value=XLeaFilter.class, name = "XLeaFilter"),
		@JsonSubTypes.Type(value=XSchoolFilter.class, name = "XSchoolFilter"),
		@JsonSubTypes.Type(value=XCalendarFilter.class, name = "XCalendarFilter"),
		@JsonSubTypes.Type(value=XCourseFilter.class, name = "XCourseFilter"),
		@JsonSubTypes.Type(value=XRosterFilter.class, name = "XRosterFilter"),
		@JsonSubTypes.Type(value=XStaffFilter.class, name = "XStaffFilter"),
		@JsonSubTypes.Type(value=XStudentFilter.class, name = "XStudentFilter"),
		@JsonSubTypes.Type(value=XContactFilter.class, name = "XContactFilter")
})
@JsonIgnoreProperties(ignoreUnknown = true)*/
public interface IFilter<T> {
}