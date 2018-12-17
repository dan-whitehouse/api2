package org.ricone.api.oneroster.request.classes;

import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Class;
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
public class ClassController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}")
	public ClassResponse getClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		Class class1 = new Class();
		class1.setSourcedId(UUID.randomUUID().toString());
		class1.setStatus(StatusType.active);
		class1.setDateLastModified(Instant.now().toString());
		class1.setTitle("Title 1");
		class1.setClassType(ClassType.homeroom);
		class1.setLocation("Some Place...");
		class1.getGrades().add("1");
		class1.getGrades().add("2");
		class1.getSubjects().add("chemistry");

		GUIDRef course = new GUIDRef();
		course.setHref("http://localhost:8080/ims/oneroster/v1p1/courses/");
		course.setSourcedId(UUID.randomUUID().toString());
		course.setType(GUIDType.course);

		GUIDRef school = new GUIDRef();
		school.setHref("http://localhost:8080/ims/oneroster/v1p1/orgs/");
		school.setSourcedId(UUID.randomUUID().toString());
		school.setType(GUIDType.org);

		GUIDRef term1 = new GUIDRef();
		term1.setHref("http://localhost:8080/ims/oneroster/v1p1/terms/");
		term1.setSourcedId(UUID.randomUUID().toString());
		term1.setType(GUIDType.term);

		GUIDRef term2 = new GUIDRef();
		term2.setHref("http://localhost:8080/ims/oneroster/v1p1/terms/");
		term2.setSourcedId(UUID.randomUUID().toString());
		term2.setType(GUIDType.term);

		class1.setCourse(course);
		class1.setSchool(school);
		class1.getTerms().add(term1);
		class1.getTerms().add(term2);

		ClassResponse classResponse = new ClassResponse();
		classResponse.setClass_(class1);

		return classResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes")
	public ClassesResponse getAllClasses(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Class class1 = new Class();
		class1.setSourcedId(UUID.randomUUID().toString());
		class1.setStatus(StatusType.active);
		class1.setDateLastModified(Instant.now().toString());
		class1.setTitle("Title 1");
		class1.setClassType(ClassType.homeroom);
		class1.setLocation("Some Place...");
		class1.getGrades().add("1");
		class1.getGrades().add("2");
		class1.getSubjects().add("chemistry");

		GUIDRef course = new GUIDRef();
		course.setHref("http://localhost:8080/ims/oneroster/v1p1/courses/");
		course.setSourcedId(UUID.randomUUID().toString());
		course.setType(GUIDType.course);

		GUIDRef school = new GUIDRef();
		school.setHref("http://localhost:8080/ims/oneroster/v1p1/orgs/");
		school.setSourcedId(UUID.randomUUID().toString());
		school.setType(GUIDType.org);

		GUIDRef term1 = new GUIDRef();
		term1.setHref("http://localhost:8080/ims/oneroster/v1p1/terms/");
		term1.setSourcedId(UUID.randomUUID().toString());
		term1.setType(GUIDType.term);

		GUIDRef term2 = new GUIDRef();
		term2.setHref("http://localhost:8080/ims/oneroster/v1p1/terms/");
		term2.setSourcedId(UUID.randomUUID().toString());
		term2.setType(GUIDType.term);


		class1.setCourse(course);
		class1.setSchool(school);
		class1.getTerms().add(term1);
		class1.getTerms().add(term2);

		List<Class> classes = new ArrayList<>();
		classes.add(class1);

		ClassesResponse classesResponse = new ClassesResponse();
		classesResponse.setClass_(classes);

		return classesResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/terms/{id}/classes")
	public ClassesResponse getClassesForTerm(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/courses/{id}/classes")
	public ClassesResponse getClassesForCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students/{id}/classes")
	public ClassesResponse getClassesForStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers/{id}/classes")
	public ClassesResponse getClassesForTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/classes")
	public ClassesResponse getClassesForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users/{id}/classes")
	public ClassesResponse getClassesForUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}
}
