package org.ricone.security.jwt;

import org.ricone.security.acl.PathPermission;

import java.util.List;

/**
 * @project: api2
 * @author: Dan on 12/4/2018.
 */
class FakePermissionsLoader {
	private static final String appId = "NoSecurityAccount";

	static List<PathPermission> getPathPermissions() {
		return List.of(
				//Leas
				new PathPermission(appId,"/requests/xLeas", true, true, false, true),
				new PathPermission(appId,"/requests/xSchools/{}/xLeas", true, false, false, false),
				new PathPermission(appId,"/requests/xCalendars/{}/xLeas", true, false, false, false),
				new PathPermission(appId,"/requests/xCourses/{}/xLeas", true, false, false, false),
				new PathPermission(appId,"/requests/xRosters/{}/xLeas", true, false, false, false),
				new PathPermission(appId,"/requests/xStaffs/{}/xLeas", true, false, false, false),
				new PathPermission(appId,"/requests/xStudents/{}/xLeas", true, false, false, false),
				new PathPermission(appId,"/requests/xContacts/{}/xLeas", true, false, false, false),

				//Schools
				new PathPermission(appId,"/requests/xSchools", true, true, false, true),
				new PathPermission(appId,"/requests/xLeas/{}/xSchools", true, false, false, false),

				//Calendars
				new PathPermission(appId,"/requests/xCalendars", true, true, false, true),

				//Courses
				new PathPermission(appId,"/requests/xCourses", true, true, false, true),

				//Rosters
				new PathPermission(appId,"/requests/xRosters", true, true, false, true),

				//Staffs
				new PathPermission(appId,"/requests/xStaffs", true, true, false, true),
				new PathPermission(appId,"/requests/xLeas/{}/xStaffs", true, true, false, true),
				new PathPermission(appId,"/requests/xSchools/{}/xStaffs", true, true, false, true),

				//Students
				new PathPermission(appId,"/requests/xStudents", true, true, false, true),
				new PathPermission(appId,"/requests/xLeas/{}/xStudents", true, true, false, true),
				new PathPermission(appId,"/requests/xSchools/{}/xStudents", true, true, false, true),

				//Contacts
				new PathPermission(appId,"/requests/xContacts", true, true, false, true)
		);
	}
}
