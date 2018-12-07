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
		PathPermission pathPermission1 = new PathPermission("/requests/xLeas", true, true, false, true, true);
		PathPermission pathPermission2 = new PathPermission("/requests/xSchools/{}/xLeas", true, false, false, false, false);
		PathPermission pathPermission3 = new PathPermission("/requests/xCalendars/{}/xLeas", true, false, false, false, false);
		PathPermission pathPermission4 = new PathPermission("/requests/xCourses/{}/xLeas", true, false, false, false, false);
		PathPermission pathPermission5 = new PathPermission("/requests/xRosters/{}/xLeas", true, false, false, false, false);
		PathPermission pathPermission6 = new PathPermission("/requests/xStaffs/{}/xLeas", true, false, false, false, false);
		PathPermission pathPermission7 = new PathPermission("/requests/xStudents/{}/xLeas", true, false, false, false, false);
		PathPermission pathPermission8 = new PathPermission("/requests/xContacts/{}/xLeas", true, false, false, false, false);

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
