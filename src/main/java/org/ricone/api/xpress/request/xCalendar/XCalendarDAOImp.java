package org.ricone.api.xpress.request.xCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.SchoolCalendar;
import org.ricone.api.core.model.SchoolCalendarSession;
import org.ricone.api.core.model.wrapper.SchoolCalendarWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("XPress:XCalendars:XCalendarDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XCalendarDAOImp extends BaseDAO implements XCalendarDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(XCalendarDAOImp.class);
	private final String PRIMARY_KEY = "schoolCalendarRefId";
	private final String SCHOOL_YEAR_KEY = "schoolCalendarSchoolYear";

	@Override
	public SchoolCalendarWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearByRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
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
	public List<SchoolCalendarWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAll(metadata);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}

		List<SchoolCalendarWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolCalendarWrapper> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByLeaRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(lea.get("leaRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByLeaRefId(metadata, refId));
		}

		List<SchoolCalendarWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolCalendarWrapper> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolCalendarWrapper> select = cb.createQuery(SchoolCalendarWrapper.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllBySchoolRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolCalendarWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(school.get("schoolRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}

		List<SchoolCalendarWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}


	@Override
	public Integer greatestSchoolYearAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()));
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(lea.get("leaRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(school.get("schoolRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAll(metadata));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByLeaRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(lea.get("leaRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<SchoolCalendar> from = select.from(SchoolCalendar.class);
		final SetJoin<SchoolCalendar, SchoolCalendarSession> schoolCalendarSessions = (SetJoin<SchoolCalendar, SchoolCalendarSession>) from.<SchoolCalendar, SchoolCalendarSession>join(JOIN_SCHOOL_CALENDAR_SESSIONS, JoinType.LEFT);
		final Join<SchoolCalendar, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllBySchoolRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(school.get("schoolRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
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