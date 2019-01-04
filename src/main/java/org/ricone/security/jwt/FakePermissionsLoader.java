package org.ricone.security.jwt;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: api2
 * @author: Dan on 12/4/2018.
 */
class FakePermissionsLoader {
	static List<PathPermission> getPathPermissions() {
		return List.of(
				//Leas
				new PathPermission("/requests/xLeas", true, true, false, true),
				new PathPermission("/requests/xSchools/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xSchools/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xCalendars/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xCourses/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xRosters/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xStaffs/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xStudents/{}/xLeas", true, false, false, false),
				new PathPermission("/requests/xContacts/{}/xLeas", true, false, false, false),

				//Schools
				new PathPermission("/requests/xSchools", true, true, false, true),

				//Calendars
				new PathPermission("/requests/xCalendars", true, true, false, true),

				//Courses
				new PathPermission("/requests/xCourses", true, true, false, true),

				//Rosters
				new PathPermission("/requests/xRosters", true, true, false, true),

				//Staffs
				new PathPermission("/requests/xStaffs", true, true, false, true),
				new PathPermission("/requests/xSchools/{}/xStaffs", true, true, false, true),

				//Students
				new PathPermission("/requests/xStudents", true, true, false, true),

				//Contacts
				new PathPermission("/requests/xContacts", true, true, false, true)
		);
	}
}
