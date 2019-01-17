package org.ricone.api.oneroster.request.classes;

import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface ClassDAO {
	CourseSectionWrapper getClass(ControllerData metadata, String refId) throws Exception;

	List<CourseSectionWrapper> getAllClasses(ControllerData metadata) throws Exception;

	List<CourseSectionWrapper> getClassesForTerm(ControllerData metadata, String refId) throws Exception;

	List<CourseSectionWrapper> getClassesForCourse(ControllerData metadata, String refId) throws Exception;

	List<CourseSectionWrapper> getClassesForStudent(ControllerData metadata, String refId) throws Exception;

	List<CourseSectionWrapper> getClassesForTeacher(ControllerData metadata, String refId) throws Exception;

	List<CourseSectionWrapper> getClassesForSchool(ControllerData metadata, String refId) throws Exception;

	List<CourseSectionWrapper> getClassesForUser(ControllerData metadata, String refId) throws Exception;
}