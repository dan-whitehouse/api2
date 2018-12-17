package org.ricone.api.oneroster.request.users;

import org.ricone.api.oneroster.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;

@RestController
public class UserController {

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users/{id}")
	public UserResponse getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		User user = new User();
		user.setSourcedId(UUID.randomUUID().toString());
		user.setStatus(StatusType.active);
		user.setDateLastModified(Instant.now().toString());

		user.setUsername("dwhitehouse");

		user.getUserIds().add(new UserId("localId", "123456"));
		user.getUserIds().add(new UserId("stateId", "785412369"));

		user.setEnabledUser("true");
		user.setGivenName("Daniel");
		user.setFamilyName("Whitehouse");
		user.setMiddleName("David");
		user.setRole(RoleType.student);
		user.setIdentifier(UUID.randomUUID().toString());
		user.setEmail("daniel.whitehouse@neric.org");
		user.setSms("518-810-3880");
		user.setPhone("518-810-3880");
		//user.setAgents();
		user.getGrades().add("12");
		user.setPassword("NunYahDamBiznass");

		UserResponse userResponse = new UserResponse();
		userResponse.setUser(user);

		return userResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/users")
	public UsersResponse getAllUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = new User();
		user.setSourcedId(UUID.randomUUID().toString());
		user.setStatus(StatusType.active);
		user.setDateLastModified(Instant.now().toString());

		user.setUsername("dwhitehouse");

		user.getUserIds().add(new UserId("localId", "123456"));
		user.getUserIds().add(new UserId("stateId", "785412369"));

		user.setEnabledUser("true");
		user.setGivenName("Daniel");
		user.setFamilyName("Whitehouse");
		user.setMiddleName("David");
		user.setRole(RoleType.student);
		user.setIdentifier(UUID.randomUUID().toString());
		user.setEmail("daniel.whitehouse@neric.org");
		user.setSms("518-810-3880");
		user.setPhone("518-810-3880");
		//user.setAgents();

		GUIDRef guidRef = new GUIDRef();
		guidRef.setHref("http://localhost:8080/ims/oneroster/v1p1/orgs/");
		guidRef.setSourcedId(UUID.randomUUID().toString());
		guidRef.setType(GUIDType.org);

		user.getOrgs().add(guidRef);



		user.getGrades().add("12");
		user.setPassword("NunYahDamBiznass");

		UsersResponse usersResponse = new UsersResponse();
		usersResponse.getUsers().add(user);

		return usersResponse;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students/{id}")
	public UserResponse getStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/students")
	public UsersResponse getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/students")
	public UsersResponse getStudentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/students")
	public UsersResponse getStudentsForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers/{id}")
	public UserResponse getTeacher(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/teachers")
	public UsersResponse getAllTeachers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/teachers")
	public UsersResponse getTeachersForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/classes/{id}/teachers")
	public UsersResponse getTeachersForClass(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}
}
