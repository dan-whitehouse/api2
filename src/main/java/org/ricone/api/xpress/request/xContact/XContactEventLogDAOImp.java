package org.ricone.api.xpress.request.xContact;

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
 * @version 1.2.1
 * @since 2018-11-09
 */

@SuppressWarnings({"unchecked", "RedundantTypeArguments"})
@Repository("XPress:XContacts:XCalendarEventLogDAO")
public class XContactEventLogDAOImp extends BaseDAO implements XContactEventLogDAO {
	@PersistenceContext
	private EntityManager em;

	public List<EventLogWrapper<ContactEventLog>> findAll(ControllerData metadata, Date iso8601) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());
			
			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata, iso8601));
		}

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	public List<EventLogWrapper<ContactEventLog>> findAllByLeaRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				cb.equal(lea.get(LEA_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByLeaRefId(metadata, iso8601, refId));
		}

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	public List<EventLogWrapper<ContactEventLog>> findAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<ContactEventLog, StudentContact> studentContact = from.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) studentContact.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				cb.equal(school.get(SCHOOL_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, iso8601, refId));
		}

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	public List<EventLogWrapper<ContactEventLog>> findAllByStudentRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<ContactEventLog, StudentContact> studentContact = from.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) studentContact.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(EventLogWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
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
			metadata.getPaging().setTotalObjects(countAllByStudentRefId(metadata, iso8601, refId));
		}

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	/** Counts **/
	public int countAll(ControllerData metadata, Date iso8601) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	public int countAllByLeaRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				cb.equal(lea.get(LEA_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	public int countAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<ContactEventLog, StudentContact> studentContact = from.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) studentContact.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				cb.equal(school.get(SCHOOL_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	public int countAllByStudentRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<ContactEventLog, StudentContact> studentContact = from.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) studentContact.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), iso8601),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				cb.equal(student.get(STUDENT_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Latest Opaque Markers **/
	public Date getLatestOpaqueMarker(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Date> select = cb.createQuery(Date.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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