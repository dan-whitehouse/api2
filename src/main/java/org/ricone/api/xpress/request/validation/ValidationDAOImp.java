package org.ricone.api.xpress.request.validation;

import org.ricone.api.core.model.*;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-17
 */

@Repository("RICOne:Validation:ValidationDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class ValidationDAOImp extends BaseDAO implements ValidationDAO {
	@PersistenceContext private EntityManager em;

	@Override
	public int countStudentsByLeaRefId(ControllerData metadata, String leaRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STUDENT_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(lea.get(LEA_REF_ID), leaRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentEmailsByLeaRefId(ControllerData metadata, String leaRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEmail> from = select.from(StudentEmail.class);
		final Join<StudentEmail, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STUDENT_EMAIL_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(from.get("primaryEmailAddressIndicator"), true),
				cb.equal(lea.get(LEA_REF_ID), leaRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentLocalIdsByLeaRefId(ControllerData metadata, String leaRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentIdentifier> from = select.from(StudentIdentifier.class);
		final Join<StudentIdentifier, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STUDENT_ID_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(from.get("identificationSystemCode"), "District"),
				cb.equal(lea.get(LEA_REF_ID), leaRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStaffsByLeaRefId(ControllerData metadata, String leaRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join(JOIN_STAFF_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join(JOIN_STAFF_EMAILS, JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, School> school = staffAssignments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STAFF_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(lea.get(LEA_REF_ID), leaRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStaffEmailsByLeaRefId(ControllerData metadata, String leaRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StaffEmail> from = select.from(StaffEmail.class);
		final Join<StaffEmail, Staff> staff = from.join(JOIN_STAFF, JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, School> school = staffAssignments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STAFF_EMAIL_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(from.get("primaryEmailAddressIndicator"), true),
				cb.equal(lea.get(LEA_REF_ID), leaRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStaffLocalIdsByLeaRefId(ControllerData metadata, String leaRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StaffIdentifier> from = select.from(StaffIdentifier.class);
		final Join<StaffIdentifier, Staff> staff = from.join(JOIN_STAFF, JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, School> school = staffAssignments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STAFF_ID_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(from.get("identificationSystemCode"), "District"),
				cb.equal(lea.get(LEA_REF_ID), leaRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentEnrollmentsPrimaryBySchoolRefId(ControllerData metadata, String schoolRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEnrollment> from = select.from(StudentEnrollment.class);
		final Join<StudentEnrollment, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STUDENT_ENROLLMENT_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(from.get("membershipTypeCode"), "Home"),
				cb.equal(school.get(SCHOOL_REF_ID), schoolRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentEnrollmentsOtherBySchoolRefId(ControllerData metadata, String schoolRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEnrollment> from = select.from(StudentEnrollment.class);
		final Join<StudentEnrollment, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STUDENT_ENROLLMENT_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.notEqual(from.get("membershipTypeCode"), "Home"),
				cb.equal(school.get(SCHOOL_REF_ID), schoolRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStaffAssignmentsPrimaryBySchoolRefId(ControllerData metadata, String schoolRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StaffAssignment> from = select.from(StaffAssignment.class);
		final Join<StaffAssignment, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STAFF_ASSIGNMENT_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(from.get("primaryAssignment"), true),
				cb.equal(school.get(SCHOOL_REF_ID), schoolRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStaffAssignmentsOtherBySchoolRefId(ControllerData metadata, String schoolRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StaffAssignment> from = select.from(StaffAssignment.class);
		final Join<StaffAssignment, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(STAFF_ASSIGNMENT_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.notEqual(from.get("primaryAssignment"), true),
				cb.equal(school.get(SCHOOL_REF_ID), schoolRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countCoursesBySchoolRefId(ControllerData metadata, String schoolRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(COURSE_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(school.get(SCHOOL_REF_ID), schoolRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countCourseSectionsBySchoolRefId(ControllerData metadata, String schoolRefId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.equal(from.get(COURSE_SECTION_SCHOOL_YEAR), metadata.getSchoolYear()),
				cb.equal(school.get(SCHOOL_REF_ID), schoolRefId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult().intValue();
	}
}