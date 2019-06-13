package org.ricone.api.xpress.request.xStudent;

import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.EventLogWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-05-15
 */

@Repository("XPress:XStudents:XStudentEventLogDAO")
public class XStudentEventLogDAOImp extends BaseDAO implements XStudentEventLogDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EventLogWrapper<StudentEventLog>> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAll(metadata, cb, select, from, studentEnrollments, lea));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}

		return (List<EventLogWrapper<StudentEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<StudentEventLog>> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByLeaRefId(metadata, cb, select, from, studentEnrollments, lea, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByLeaRefId(metadata, refId));
		}

		return (List<EventLogWrapper<StudentEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<StudentEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllBySchoolRefId(metadata, cb, select, from, studentEnrollments, lea, school, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}

		return (List<EventLogWrapper<StudentEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<StudentEventLog>> findAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) student.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByRosterRefId(metadata, cb, select, from, studentEnrollments, lea, courseSection, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByRosterRefId(metadata, refId));
		}

		return (List<EventLogWrapper<StudentEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<StudentEventLog>> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) student.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByStaffRefId(metadata, cb, select, from, studentEnrollments, lea, staff, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByStaffRefId(metadata, refId));
		}

		return (List<EventLogWrapper<StudentEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<StudentEventLog>> findAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByContactRefId(metadata, cb, select, from, studentEnrollments, lea, studentContact, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByContactRefId(metadata, refId));
		}

		return (List<EventLogWrapper<StudentEventLog>>) q.getResultList();
	}

	/** Counts **/

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAll(metadata, cb, select, from, studentEnrollments, lea));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByLeaRefId(metadata, cb, select, from, studentEnrollments, lea, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllBySchoolRefId(metadata, cb, select, from, studentEnrollments, lea, school, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) student.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByRosterRefId(metadata, cb, select, from, studentEnrollments, lea, courseSection, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) student.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByStaffRefId(metadata, cb, select, from, studentEnrollments, lea, staff, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<StudentEventLog, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByContactRefId(metadata, cb, select, from, studentEnrollments, lea, studentContact, refId));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Latest Opaque Markers **/
	
	@Override
	public Date getLatestOpaqueMarker(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Date> select = cb.createQuery(Date.class);
		final Root<StudentEventLog> from = select.from(StudentEventLog.class);
		final Join<StudentEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.select(cb.greatest(from.<Date>get(EVENT_TIMESTAMP)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Date instance = em.createQuery(select).getSingleResult();
		if (instance != null) {
			return instance;
		}
		return new Date();
	}
	
	/** Where Clauses **/
	
	private Predicate whereFindAll(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<StudentEventLog> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<StudentEventLog, Lea> lea) {
		Predicate iso8601GreaterThanEqualTo = cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime());
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds());

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData);
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq1 = subQuery1.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq1 = fromSq1.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq1 = fromSq1.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) studentSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

			subQuery1.select(studentEnrollmentsSq1.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery1.where
			(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq2 = subQuery2.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq2 = fromSq2.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq2 = fromSq2.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) studentSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);

			subQuery2.select(studentEnrollmentsSq2.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery2.where
			(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery2).not());
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByLeaRefId(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<StudentEventLog> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<StudentEventLog, Lea> lea, String refId) {
		Predicate iso8601GreaterThanEqualTo = cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime());
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds());

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, cb.equal(lea.get(LEA_REF_ID), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq1 = subQuery1.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq1 = fromSq1.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq1 = fromSq1.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) studentSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);

			subQuery1.select(studentEnrollmentsSq1.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery1.where
			(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(leaSq1.get(LEA_REF_ID), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq2 = subQuery2.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq2 = fromSq2.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq2 = fromSq2.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) studentSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);

			subQuery2.select(studentEnrollmentsSq2.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery2.where
			(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(leaSq2.get(LEA_REF_ID), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery2).not());
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, cb.equal(lea.get(LEA_REF_ID), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllBySchoolRefId(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<StudentEventLog> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<StudentEventLog, Lea> lea, Join<StudentEnrollment, School> school, String refId) {
		Predicate iso8601GreaterThanEqualTo = cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime());
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds());

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, cb.equal(school.get(SCHOOL_REF_ID), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq1 = subQuery1.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq1 = fromSq1.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq1 = fromSq1.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) studentSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);

			subQuery1.select(studentEnrollmentsSq1.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery1.where
			(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(schoolSq1.get(SCHOOL_REF_ID), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq2 = subQuery2.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq2 = fromSq2.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq2 = fromSq2.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) studentSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);

			subQuery2.select(studentEnrollmentsSq2.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery2.where
			(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(schoolSq2.get(SCHOOL_REF_ID), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery2).not());
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, cb.equal(school.get(SCHOOL_REF_ID), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByRosterRefId(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<StudentEventLog> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<StudentEventLog, Lea> lea, Join<StudentCourseSection, CourseSection> courseSection, String refId) {
		Predicate iso8601GreaterThanEqualTo = cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime());
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds());

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, cb.equal(courseSection.get(COURSE_SECTION_REF_ID), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq1 = subQuery1.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq1 = fromSq1.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq1 = fromSq1.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq1 = (SetJoin<Student, StudentCourseSection>) studentSq1.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq1 = studentCourseSectionsSq1.join(JOIN_COURSE_SECTION, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) studentSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);


			subQuery1.select(studentEnrollmentsSq1.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery1.where
			(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(courseSectionSq1.get(COURSE_SECTION_REF_ID), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq2 = subQuery2.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq2 = fromSq2.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq2 = fromSq2.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq2 = (SetJoin<Student, StudentCourseSection>) studentSq2.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq2 = studentCourseSectionsSq2.join(JOIN_COURSE_SECTION, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) studentSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);

			subQuery2.select(studentEnrollmentsSq2.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery2.where
			(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(courseSectionSq2.get(COURSE_SECTION_REF_ID), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery2).not());
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, cb.equal(courseSection.get(COURSE_SECTION_REF_ID), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByStaffRefId(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<StudentEventLog> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<StudentEventLog, Lea> lea, Join<StaffCourseSection, Staff> staff, String refId) {
		Predicate iso8601GreaterThanEqualTo = cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime());
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds());
		Predicate staffRefIdEqualsRefId = cb.equal(staff.get(STAFF_REF_ID), refId);

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, staffRefIdEqualsRefId);
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq1 = subQuery1.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq1 = fromSq1.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq1 = fromSq1.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) studentSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq1 = (SetJoin<Student, StudentCourseSection>) studentSq1.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq1 = studentCourseSectionsSq1.join(JOIN_COURSE_SECTION, JoinType.LEFT);
			final SetJoin<CourseSection, StaffCourseSection> staffCourseSectionsSq1 = (SetJoin<CourseSection, StaffCourseSection>) courseSectionSq1.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
			final Join<StaffCourseSection, Staff> staffSq1 = staffCourseSectionsSq1.join(JOIN_STAFF, JoinType.LEFT);


			subQuery1.select(studentEnrollmentsSq1.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery1.where
			(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(staffSq1.get(STAFF_REF_ID), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq2 = subQuery2.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq2 = fromSq2.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq2 = fromSq2.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) studentSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq2 = (SetJoin<Student, StudentCourseSection>) studentSq2.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq2 = studentCourseSectionsSq2.join(JOIN_COURSE_SECTION, JoinType.LEFT);
			final SetJoin<CourseSection, StaffCourseSection> staffCourseSectionsSq2 = (SetJoin<CourseSection, StaffCourseSection>) courseSectionSq2.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
			final Join<StaffCourseSection, Staff> staffSq2 = staffCourseSectionsSq2.join(JOIN_STAFF, JoinType.LEFT);


			subQuery2.select(studentEnrollmentsSq2.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery2.where
			(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(staffSq2.get(STAFF_REF_ID), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery2).not());
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, staffRefIdEqualsRefId, subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByContactRefId(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<StudentEventLog> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<StudentEventLog, Lea> lea, Join<StudentContactRelationship, StudentContact> studentContact, String refId) {
		Predicate iso8601GreaterThanEqualTo = cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime());
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds());
		Predicate studentContactRefIdEqualsRefId = cb.equal(studentContact.get(STUDENT_CONTACT_REF_ID), refId);

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, studentContactRefIdEqualsRefId);
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq1 = subQuery1.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq1 = fromSq1.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq1 = fromSq1.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) studentSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<Student, StudentContactRelationship> studentContactRelationshipsSq1 = (SetJoin<Student, StudentContactRelationship>) studentSq1.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
			final Join<StudentContactRelationship, StudentContact> studentContactSq1 = studentContactRelationshipsSq1.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);


			subQuery1.select(studentEnrollmentsSq1.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery1.where
			(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(studentContactSq1.get(STUDENT_CONTACT_REF_ID), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<StudentEventLog> fromSq2 = subQuery2.from(StudentEventLog.class);
			final Join<StudentEventLog, Lea> leaSq2 = fromSq2.join(JOIN_LEA, JoinType.LEFT);
			final Join<StudentEventLog, Student> studentSq2 = fromSq2.join(JOIN_STUDENT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) studentSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final SetJoin<Student, StudentContactRelationship> studentContactRelationshipsSq2 = (SetJoin<Student, StudentContactRelationship>) studentSq2.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
			final Join<StudentContactRelationship, StudentContact> studentContactSq2 = studentContactRelationshipsSq2.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);


			subQuery2.select(studentEnrollmentsSq2.get(STUDENT_ENROLLMENT_REF_ID));
			subQuery2.where
			(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(studentContactSq2.get(STUDENT_CONTACT_REF_ID), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(STUDENT_ENROLLMENT_REF_ID).in(subQuery2).not());
			return cb.and(iso8601GreaterThanEqualTo, localIdInControllerData, studentContactRefIdEqualsRefId, subQuery1Predicate, subQuery2Predicate);
		}
	}
}