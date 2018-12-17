package org.ricone.api.oneroster.model;

public enum OrgType {
	department, //Denotes a department. A department may be a subset in a school or a set of schools. Added in V1.1.
	school, //Denotes a school. This is the unit of assignment for classes and enrollments.
	district, //Denotes a school district. Added in V1.1.
	local, //V1.0 instances will use this value to identify districts.
	state, //Denotes a state level organization.
	national //Denotes a national level organization.
}
