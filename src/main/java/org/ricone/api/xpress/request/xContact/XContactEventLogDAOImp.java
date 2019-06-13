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
 * @version 2.0.0
 * @since 2019-05-15
 */

@Repository("XPress:XContacts:XCalendarEventLogDAO")
public class XContactEventLogDAOImp extends BaseDAO implements XContactEventLogDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EventLogWrapper<ContactEventLog>> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<ContactEventLog>> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<ContactEventLog>> findAllBySchoolRefId(ControllerData metadata, String refId) {
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

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	@Override
	public List<EventLogWrapper<ContactEventLog>> findAllByStudentRefId(ControllerData metadata, String refId) {
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

		return (List<EventLogWrapper<ContactEventLog>>) q.getResultList();
	}

	/** Counts **/

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
				cb.greaterThanOrEqualTo(from.get(EVENT_TIMESTAMP), metadata.getChangesSinceLocalDateTime()),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()),
				cb.equal(school.get(SCHOOL_REF_ID), refId)
			)
		);
		select.orderBy(cb.asc(from.get(EVENT_TIMESTAMP)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStudentRefId(ControllerData metadata, String refId) {
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
		final Root<ContactEventLog> from = select.from(ContactEventLog.class);
		final Join<ContactEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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