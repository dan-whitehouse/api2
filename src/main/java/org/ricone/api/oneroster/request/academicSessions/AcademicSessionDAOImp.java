package org.ricone.api.oneroster.request.academicSessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.core.model.wrapper.SchoolCalendarSessionWrapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.component.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("OneRoster:AcademicSessions:TermDAO")
@SuppressWarnings({"unchecked", "unused"})
class AcademicSessionDAOImp extends BaseDAO implements AcademicSessionDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(AcademicSessionDAOImp.class);

	@Override
	public AcademicSessionView getAcademicSession(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (AcademicSessionView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<AcademicSessionView> getAllAcademicSessions(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}
		return (List<AcademicSessionView>) q.getResultList();
	}

	@Override
	public AcademicSessionView getCalendar(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get("type"), "schoolYear")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (AcademicSessionView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<AcademicSessionView> getAllCalendars(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get("type"), "schoolYear")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}
		return (List<AcademicSessionView>) q.getResultList();
	}

	@Override
	public AcademicSessionView getTerm(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get("type"), "term")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (AcademicSessionView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<AcademicSessionView> getAllTerms(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get("type"), "term")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}
		return (List<AcademicSessionView>) q.getResultList();
	}
}