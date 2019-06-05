package org.ricone.api.xpress.component.swagger;

import io.swagger.annotations.*;
import org.ricone.api.xpress.model.*;

import java.lang.annotation.*;


public @interface Swagger {

	@interface Controller {

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "App", description = "REST API for App", tags = {"App"})
		@interface AppController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "Validation", description = "REST API for Validation", tags = {"Validation"})
		@interface ValidationController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "xLeas", description = "REST API for xLeas", tags = {"xLeas"})
		@interface XLeaController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "xSchools", description = "REST API for xSchools", tags = {"xSchools"})
		@interface XSchoolController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "xCalendars", description = "REST API for xCalendars", tags = {"xCalendars"})
		@interface XCalendarController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "xCourses", description = "REST API for xCourses", tags = {"xCourses"})
		@interface XCourseController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "xRosters", description = "REST API for xRosters", tags = {"xRosters"})
		@interface XRosterController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "xStaffs", description = "REST API for xStaffs", tags = {"xStaffs"})
		@interface XStaffController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "xStudents", description = "REST API for xStudents", tags = {"xStudents"})
		@interface XStudentController {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "xContacts", description = "REST API for xContacts", tags = {"xContacts"})
		@interface XContactController {}
	}

	//The Operation Annotation is used to define the requests available in Swagger UI.
	@interface Operation {
		/* App */
		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return Current App", tags = {"App"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.app}"
		)@interface GetApp {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return App by appId", tags = {"App"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.app}"
		)@interface GetAppById {}

		/* Validation */


		/* Lea */
		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xLea by refId", tags = {"xLeas"}, authorizations = {@Authorization(value="Bearer")},
			notes = "${swagger.docs.xPress.operation.xLeas}"
		)@interface GetXLeaById {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeas {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xSchool refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXSchool {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xCalendar refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXCalendar {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xCourse refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXCourse {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xRoster refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXRoster {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xStaff refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXStaff {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xStudent refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXStudent {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xLeas by xContact refId", tags = {"xLeas"}, authorizations = { @Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xLeas}"
		) @interface GetXLeasByXContact {}


		/* School */
		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xSchool by refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolById {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchools {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xLea refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xCalendar refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXCalendar {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xCourse refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXCourse {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xRoster refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXRoster {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xStaff refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXStaff {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xStudent refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXStudent {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xSchools by xContact refId", tags = {"xSchools"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xSchools}"
		)@interface GetXSchoolsByXContact {}

		/* Calendar */

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xCalendar by refId", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCalendars}"
		)@interface GetXCalendarByRefId {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCalendars", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCalendars}"
		)@interface GetXCalendars {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCalendars by xLea refId", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCalendars}"
		)@interface GetXCalendarsByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCalendars by xSchool refId", tags = {"xCalendars"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCalendars}"
		)@interface GetXCalendarsByXSchool {}

		/* Course */

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xCourse by refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCourses}"
		)@interface GetXCourseByRefId {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCourses", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCourses}"
		)@interface GetXCourses {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCourses by xLea refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCourses}"
		)@interface GetXCoursesByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCourses by xSchool refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCourses}"
		)@interface GetXCoursesByXSchool {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xCourses by xSchool refId", tags = {"xCourses"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xCourses}"
		)@interface GetXCoursesByXRoster {}

		/* Roster */

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xRoster by refId", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRosterByRefId {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xRosters", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRosters {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xRosters by xLea refId", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRostersByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xRosters by xSchool refId", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRostersByXSchool {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xRosters by xCourse refId", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRostersByXCourse {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xRosters by xStaff refId", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRostersByXStaff {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xRosters by xStudent refId", tags = {"xRosters"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xRosters}"
		)@interface GetXRostersByXStudent {}

		/* Staff */

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xStaff by refId", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffByRefId {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStaffs", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffs {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStaffs by xLea refId", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffsByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStaffs by xSchool refId", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffsByXSchool {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStaffs by xCourse refId", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffsByXCourse {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStaffs by xRoster refId", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffsByXRoster {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStaffs by xStudent refId", tags = {"xStaffs"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStaffs}"
		)@interface GetXStaffsByXStudent {}

		/* Student */

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xStudent by refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudentByRefId {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStudents", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudents {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStudents by xLea refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudentsByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStudents by xSchool refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudentsByXSchool {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStudents by xRoster refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudentsByXRoster {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStudents by xStaff refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudentsByXStaff {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xStudents by xContact refId", tags = {"xStudents"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xStudents}"
		)@interface GetXStudentsByXContact {}

		/* Contact */

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return xContact by refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xContacts}"
		)@interface GetXContactByRefId {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xContacts", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xContacts}"
		)@interface GetXContacts {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xContacts by xLea refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xContacts}"
		)@interface GetXContactsByXLea {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xContacts by xSchool refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xContacts}"
		)@interface GetXContactsByXSchool {}

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiOperation(value = "Return all xContacts by xStudent refId", tags = {"xContacts"}, authorizations = {@Authorization(value="Bearer")},
				notes = "${swagger.docs.xPress.operation.xContacts}"
		)@interface GetXContactsByXStudent {}

	}

	//The Response Annotation is used to define the type of responses one can expect from a request.
	@interface Response {
		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XErrorResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XLea { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XLeasResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XLeas { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XSchoolResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XSchool { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XSchools { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XCalendarResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XCalendar { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XCalendarsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XCalendars { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XCourseResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XCourse { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XCoursesResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XCourses { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XRosterResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XRoster { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XRostersResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XRosters { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XStaffResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XStaff { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XStaffsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XStaffs { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XStudentResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XStudent { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XStudentsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XStudents { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XContactResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XContact { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = XContactsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
		})@interface XContacts { }
	}
}