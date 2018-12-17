package org.ricone.api.oneroster.request.courses;

import org.ricone.api.oneroster.model.Class;
import org.ricone.api.oneroster.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CourseController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/courses/{id}")
	public CourseResponse getCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		Course course = new Course();
		course.setSourcedId(UUID.randomUUID().toString());
		course.setStatus(StatusType.active);
		course.setDateLastModified(Instant.now().toString());

		course.setTitle("Title 1");

		GUIDRef schoolYear = new GUIDRef();
		schoolYear.setHref("http://localhost:8080/ims/oneroster/v1p1/academicSession/");
		schoolYear.setSourcedId(UUID.randomUUID().toString());
		schoolYear.setType(GUIDType.academicSession);

		course.setSchoolYear(schoolYear);
		course.setCourseCode("CHEM101");
		course.getGrades().add("1");
		course.getGrades().add("2");
		course.getSubjects().add("chemistry");

		GUIDRef org = new GUIDRef();
		org.setHref("http://localhost:8080/ims/oneroster/v1p1/org/");
		org.setSourcedId(UUID.randomUUID().toString());
		org.setType(GUIDType.org);

		course.setOrg(org);

		CourseResponse courseResponse = new CourseResponse();
		courseResponse.setCourse(course);

		return courseResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/courses")
	public CoursesResponse getAllCourses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Course course = new Course();
		course.setSourcedId(UUID.randomUUID().toString());
		course.setStatus(StatusType.active);
		course.setDateLastModified(Instant.now().toString());

		course.setTitle("Title 1");

		GUIDRef schoolYear = new GUIDRef();
		schoolYear.setHref("http://localhost:8080/ims/oneroster/v1p1/academicSession/");
		schoolYear.setSourcedId(UUID.randomUUID().toString());
		schoolYear.setType(GUIDType.academicSession);

		course.setSchoolYear(schoolYear);
		course.setCourseCode("CHEM101");
		course.getGrades().add("1");
		course.getGrades().add("2");
		course.getSubjects().add("chemistry");

		GUIDRef org = new GUIDRef();
		org.setHref("http://localhost:8080/ims/oneroster/v1p1/org/");
		org.setSourcedId(UUID.randomUUID().toString());
		org.setType(GUIDType.org);

		course.setOrg(org);

		CoursesResponse coursesResponse = new CoursesResponse();
		coursesResponse.getCourse().add(course);

		return coursesResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/courses")
	public CoursesResponse getCoursesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		Course course = new Course();
		course.setSourcedId(UUID.randomUUID().toString());
		course.setStatus(StatusType.active);
		course.setDateLastModified(Instant.now().toString());

		course.setTitle("Title 1");

		GUIDRef schoolYear = new GUIDRef();
		schoolYear.setHref("http://localhost:8080/ims/oneroster/v1p1/academicSession/");
		schoolYear.setSourcedId(UUID.randomUUID().toString());
		schoolYear.setType(GUIDType.academicSession);

		course.setSchoolYear(schoolYear);
		course.setCourseCode("CHEM101");
		course.getGrades().add("1");
		course.getGrades().add("2");
		course.getSubjects().add("chemistry");

		GUIDRef org = new GUIDRef();
		org.setHref("http://localhost:8080/ims/oneroster/v1p1/org/");
		org.setSourcedId(UUID.randomUUID().toString());
		org.setType(GUIDType.org);

		course.setOrg(org);

		CoursesResponse coursesResponse = new CoursesResponse();
		coursesResponse.getCourse().add(course);

		return coursesResponse;
	}
}
