package org.ricone.security.jwt;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: api2
 * @author: Dan on 12/4/2018.
 */
class FakePermissionsLoader {
	static List<PathPermission> getPathPermissions() {
		List<PathPermission> pathPermissions = new ArrayList<>();
		PathPermission pathPermission1 = new PathPermission("/requests/xLea", true, true, false, true, true);
		PathPermission pathPermission2 = new PathPermission("/requests/xSchools/{}/xLea", true, false, false, false, false);
		PathPermission pathPermission3 = new PathPermission("/requests/xCalendars/{}/xLea", true, false, false, false, false);
		PathPermission pathPermission4 = new PathPermission("/requests/xCourses/{}/xLea", true, false, false, false, false);
		PathPermission pathPermission5 = new PathPermission("/requests/xRosters/{}/xLea", true, false, false, false, false);
		PathPermission pathPermission6 = new PathPermission("/requests/xStaffs/{}/xLea", true, false, false, false, false);
		PathPermission pathPermission7 = new PathPermission("/requests/xStudents/{}/xLea", true, false, false, false, false);
		PathPermission pathPermission8 = new PathPermission("/requests/xContacts/{}/xLea", true, false, false, false, false);

		pathPermissions.add(pathPermission1);
		pathPermissions.add(pathPermission2);
		pathPermissions.add(pathPermission3);
		pathPermissions.add(pathPermission4);
		pathPermissions.add(pathPermission5);
		pathPermissions.add(pathPermission6);
		pathPermissions.add(pathPermission7);
		pathPermissions.add(pathPermission8);

		return pathPermissions;
	}
}
