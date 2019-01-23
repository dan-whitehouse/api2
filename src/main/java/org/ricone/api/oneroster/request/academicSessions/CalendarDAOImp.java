package org.ricone.api.oneroster.request.academicSessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:AcademicSessions:CalendarDAO")
@SuppressWarnings({"unchecked", "unused"})
class CalendarDAOImp extends BaseDAO implements CalendarDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(CalendarDAOImp.class);
	private final String PRIMARY_KEY = "schoolCalendarRefId";
	private final String SCHOOL_YEAR_KEY = "schoolCalendarSchoolYear";

	@Override
	public SchoolCalendarWrapper getCalendar(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			SchoolCalendarWrapper instance = (SchoolCalendarWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<SchoolCalendarWrapper> getAllCalendars(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<SchoolCalendarWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(SchoolCalendarWrapper instance) {
		instance.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
		Hibernate.initialize(instance.getSchoolCalendar().getSchool());
	}

	private void initialize(List<SchoolCalendarWrapper> instance) {
		instance.forEach(wrapper -> {
			wrapper.getSchoolCalendar().getSchoolCalendarSessions().forEach(scs -> Hibernate.initialize(scs.getSchoolCalendar()));
			Hibernate.initialize(wrapper.getSchoolCalendar().getSchool());
		});
	}
}