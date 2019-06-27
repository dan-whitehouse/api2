package org.ricone.api.oneroster.component.springfox;

import io.swagger.annotations.*;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.xpress.model.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.*;


public @interface Swagger {

	@interface Controller {

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "AcademicSession", description = "One Roster - Academic Sessions", tags = {"AcademicSession"})
		@interface AcademicSession {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "Class", description = "One Roster - Classes", tags = {"Class"})
		@interface Class {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "Course", description = "One Roster - Courses", tags = {"Course"})
		@interface Course {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Inherited @Api(value = "Demographic", description = "One Roster - Demographics", tags = {"Demographic"})
		@interface Demographic {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "Enrollment", description = "One Roster - Enrollments", tags = {"Enrollment"})
		@interface Enrollment {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "Org", description = "One Roster - Orgs", tags = {"Org"})
		@interface Org {}

		@Target({ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@Api(value = "User", description = "One Roster - Users", tags = {"User"})
		@interface User {}
	}

	//The Operation Annotation is used to define the requests available in Swagger UI.
	@interface Operation {

		/* AcademicSession */
		@interface AcademicSession {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAcademicSession", tags = {"AcademicSession"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			)@interface GetAcademicSession {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllAcademicSessions", tags = {"AcademicSession"}, authorizations = { @Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			) @interface GetAllAcademicSessions {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getTerm", tags = {"AcademicSession"}, authorizations = { @Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			) @interface GetTerm {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllTerms", tags = {"AcademicSession"}, authorizations = { @Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			) @interface GetAllTerms {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getGradingPeriod", tags = {"AcademicSession"}, authorizations = { @Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			) @interface GetGradingPeriod {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllGradingPeriods", tags = {"AcademicSession"}, authorizations = { @Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			) @interface GetAllGradingPeriods {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getGradingPeriodsForTerm", tags = {"AcademicSession"}, authorizations = { @Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.academicSession}"
			) @interface GetGradingPeriodsForTerm {}
		}

		/* Class */
		@interface Class {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClass", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClass {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllClasses", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetAllClasses {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClassesForTerm", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClassesForTerm {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClassesForCourse", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClassesForCourse {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClassesForStudent", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClassesForStudent {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClassesForTeacher", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClassesForTeacher {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClassesForSchool", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClassesForSchool {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getClassesForUser", tags = {"Class"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.class}"
			)@interface GetClassesForUser {}
		}

		/* Class */
		@interface Course {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getCourse", tags = {"Course"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.course}"
			)@interface GetCourse {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllCourses", tags = {"Course"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.course}"
			)@interface GetAllCourses {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getCoursesForSchool", tags = {"Course"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.course}"
			)@interface GetCoursesForSchool {}
		}

		/* Demographic */
		@interface Demographic {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getDemographic", tags = {"Demographic"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.demographic}"
			)@interface GetDemographic {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllDemographics", tags = {"Demographic"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.demographic}"
			)@interface GetAllDemographics {}
		}

		/* Enrollment */
		@interface Enrollment {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getEnrollment", tags = {"Enrollment"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.enrollment}"
			)@interface GetEnrollment {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllEnrollments", tags = {"Enrollment"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.enrollment}"
			)@interface GetAllEnrollments {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getEnrollmentsForSchool", tags = {"Enrollment"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.enrollment}"
			)@interface GetEnrollmentsForSchool {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getEnrollmentsForClassInSchool", tags = {"Enrollment"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.enrollment}"
			)@interface GetEnrollmentsForClassInSchool {}
		}

		/* Enrollment */
		@interface Org {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getOrg", tags = {"Org"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.org}"
			)@interface GetOrg {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllOrgs", tags = {"Org"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.org}"
			)@interface GetAllOrgs {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getSchool", tags = {"Org"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.org}"
			)@interface GetSchool {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllSchools", tags = {"Org"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.org}"
			)@interface GetAllSchools {}
		}

		/* User */
		@interface User {
			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getUser", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetUser {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllUsers", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetAllUsers {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getStudent", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetStudent {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllStudents", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetAllStudents {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getStudentsForSchool", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetStudentsForSchool {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getStudentsForClass", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetStudentsForClass {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getStudentsForClassInSchool", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetStudentsForClassInSchool {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getTeacher", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetTeacher {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getAllTeachers", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetAllTeachers {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getTeachersForSchool", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetTeachersForSchool {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getTeachersForClass", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetTeachersForClass {}

			@Target({ElementType.METHOD, ElementType.TYPE})
			@Retention(RetentionPolicy.RUNTIME)
			@ApiOperation(value = "getTeachersForClassInSchool", tags = {"User"}, authorizations = {@Authorization(value="Bearer")},
					notes = "${swagger.docs.oneroster.operation.user}"
			)@interface GetTeachersForClassInSchool {}
		}
	}

	//The Response Annotation is used to define the type of responses one can expect from a request.
	@interface Response {
		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = AcademicSessionResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface AcademicSession { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = AcademicSessionsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface AcademicSessions { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = ClassResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Class { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = ClassesResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Classes { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = CourseResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Course { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = CoursesResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Courses { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = DemographicResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Demographic { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = DemographicsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Demographics { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = EnrollmentResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Enrollment { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = EnrollmentsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Enrollments { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = OrgResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Org { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = OrgsResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Orgs { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = UserResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface User { }

		@Target({ElementType.METHOD, ElementType.TYPE})
		@Retention(RetentionPolicy.RUNTIME)
		@ApiResponses({
				@ApiResponse(code = 200, message = "Success", response = UsersResponse.class),
				@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
				@ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
		})@interface Users { }
	}
}