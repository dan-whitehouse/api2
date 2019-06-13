package org.ricone.api.xpress.request.xRoster;

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
@Repository("XPress:XRosters:XRosterEventLogDAO")
public class XRosterEventLogDAOImp extends BaseDAO implements XRosterEventLogDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EventLogWrapper<RosterEventLog>> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
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

		return (List<EventLogWrapper<RosterEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<RosterEventLog>> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(lea.get(LEA_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByLeaRefId(metadata, refId));
		}

		return (List<EventLogWrapper<RosterEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<RosterEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final Join<CourseSection, Course> course = courseSection.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(school.get(SCHOOL_REF_ID), refId)
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

		return (List<EventLogWrapper<RosterEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<RosterEventLog>> findAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final Join<CourseSection, Course> course = courseSection.join(JOIN_COURSE, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(course.get(COURSE_REF_ID), refId)
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

		return (List<EventLogWrapper<RosterEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<RosterEventLog>> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join(JOIN_STAFF_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
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

		return (List<EventLogWrapper<RosterEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<RosterEventLog>> findAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) courseSection.<CourseSection, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = studentCourseSections.join(JOIN_STUDENT, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
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

		return (List<EventLogWrapper<RosterEventLog>>) q.getResultList();
	}

	/** Counts **/

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(lea.get(LEA_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final Join<CourseSection, Course> course = courseSection.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(school.get(SCHOOL_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final Join<CourseSection, Course> course = courseSection.join(JOIN_COURSE, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(course.get(COURSE_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join(JOIN_STAFF_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
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
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<RosterEventLog, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) courseSection.<CourseSection, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = studentCourseSections.join(JOIN_STUDENT, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(student.get(STUDENT_REF_ID), refId)
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
		final Root<RosterEventLog> from = select.from(RosterEventLog.class);
		final Join<RosterEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.select(cb.greatest(from.<Date>get(EVENT_TIMESTAMP)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()));
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Date instance = em.createQuery(select).getSingleResult();
		if (instance != null) {
			return instance;
		}
		return new Date();
	}
}