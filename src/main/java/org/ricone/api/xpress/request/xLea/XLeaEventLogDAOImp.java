package org.ricone.api.xpress.request.xLea;

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

@Repository("XPress:XLeas:XLeaEventLogDAO")
public class XLeaEventLogDAOImp extends BaseDAO implements XLeaEventLogDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(schools.get(SCHOOL_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(schoolCalendars.get(SCHOOL_CALENDAR_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByCalendarRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(courses.get(COURSE_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByCourseRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(courseSections.get(COURSE_SECTION_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByRosterRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(staff.get(STAFF_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByStaffRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(student.get(STUDENT_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByStudentRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<LeaEventLog>> findAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(contact.get(STUDENT_CONTACT_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByContactRefId(metadata, refId));
		}

		return (List<EventLogWrapper<LeaEventLog>>) q.getResultList();
	}

	/** Counts **/
	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(schools.get(SCHOOL_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(schoolCalendars.get(SCHOOL_CALENDAR_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(courses.get(COURSE_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.INNER);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(courseSections.get(COURSE_SECTION_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(staff.get(STAFF_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(student.get(STUDENT_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) lea.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
				cb.and(
						cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
						cb.equal(contact.get(STUDENT_CONTACT_REF_ID), refId)
				)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Latest Opaque Markers **/

	@Override
	public Date getLatestOpaqueMarker(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Date> select = cb.createQuery(Date.class);
		final Root<LeaEventLog> from = select.from(LeaEventLog.class);
		final Join<LeaEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.select(cb.greatest(from.<Date>get(EVENT_TIMESTAMP)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Date instance = em.createQuery(select).getSingleResult();
		if (instance != null) {
			return instance;
		}
		return new Date();
	}
}
