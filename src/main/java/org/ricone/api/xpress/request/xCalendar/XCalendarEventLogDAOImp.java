package org.ricone.api.xpress.request.xCalendar;

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
@Repository("XPress:XCalendars:XCalendarEventLogDAO")
public class XCalendarEventLogDAOImp extends BaseDAO implements XCalendarEventLogDAO {
	@PersistenceContext
	private EntityManager em;
	private final String EVENT_TIMESTAMP = "eventTimestamp";

	public List<EventLogWrapper<CalendarEventLog>> findAll(ControllerData metadata, Date iso8601) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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

		return (List<EventLogWrapper<CalendarEventLog>>) q.getResultList();
	}

	public List<EventLogWrapper<CalendarEventLog>> findAllByLeaRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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

		return (List<EventLogWrapper<CalendarEventLog>>) q.getResultList();
	}

	public List<EventLogWrapper<CalendarEventLog>> findAllBySchoolRefId(ControllerData metadata, Date iso8601, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EventLogWrapper> select = cb.createQuery(EventLogWrapper.class);
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<CalendarEventLog, SchoolCalendar> schoolCalendar = from.join(JOIN_SCHOOL_CALENDAR, JoinType.LEFT);
		final Join<Course, School> school = schoolCalendar.join(JOIN_SCHOOL, JoinType.LEFT);

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

		return (List<EventLogWrapper<CalendarEventLog>>) q.getResultList();
	}


	/** Counts **/
	public int countAll(ControllerData metadata, Date iso8601) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);
		final Join<CalendarEventLog, SchoolCalendar> schoolCalendar = from.join(JOIN_SCHOOL_CALENDAR, JoinType.LEFT);
		final Join<Course, School> school = schoolCalendar.join(JOIN_SCHOOL, JoinType.LEFT);

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

	/** Latest Opaque Markers **/
	public Date getLatestOpaqueMarker(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Date> select = cb.createQuery(Date.class);
		final Root<CalendarEventLog> from = select.from(CalendarEventLog.class);
		final Join<CalendarEventLog, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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