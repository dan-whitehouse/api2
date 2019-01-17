package org.ricone.api.oneroster.request.academicSessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:AcademicSessions:TermDAO")
@SuppressWarnings({"unchecked", "unused"})
class TermDAOImp extends BaseDAO implements TermDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(TermDAOImp.class);
	private final String PRIMARY_KEY = "schoolCalendarSessionRefId";
	private final String SCHOOL_YEAR_KEY = "schoolCalendarSessionSchoolYear";

	@Override
	public SchoolCalendarSessionWrapper getTerm(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarSessionWrapper> select = cb.createQuery(SchoolCalendarSessionWrapper.class);
		final Root<SchoolCalendarSession> from = select.from(SchoolCalendarSession.class);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = from.join(JOIN_SCHOOL_CALENDAR, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = schoolCalendar.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarSessionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			SchoolCalendarSessionWrapper instance = (SchoolCalendarSessionWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<SchoolCalendarSessionWrapper> getAllTerms(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarSessionWrapper> select = cb.createQuery(SchoolCalendarSessionWrapper.class);
		final Root<SchoolCalendarSession> from = select.from(SchoolCalendarSession.class);
		final Join<SchoolCalendarSession, SchoolCalendar> schoolCalendar = from.join(JOIN_SCHOOL_CALENDAR, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = schoolCalendar.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarSessionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<SchoolCalendarSessionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(SchoolCalendarSessionWrapper instance) {
		Hibernate.initialize(instance.getSchoolCalendarSession());
		Hibernate.initialize(instance.getSchoolCalendarSession().getSchoolCalendar());
		Hibernate.initialize(instance.getSchoolCalendarSession().getSchoolCalendar().getSchool());
	}

	private void initialize(List<SchoolCalendarSessionWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getSchoolCalendarSession());
			Hibernate.initialize(wrapper.getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(wrapper.getSchoolCalendarSession().getSchoolCalendar().getSchool());
		});
	}
}