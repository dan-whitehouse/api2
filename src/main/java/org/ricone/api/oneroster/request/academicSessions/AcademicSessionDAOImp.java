package org.ricone.api.oneroster.request.academicSessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.oneroster.QAcademicSession;
import org.ricone.api.core.model.oneroster.QAcademicSessionChild;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:AcademicSessions:TermDAO")
@SuppressWarnings({"unchecked", "unused"})
class AcademicSessionDAOImp extends BaseDAO implements AcademicSessionDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private AcademicSessionFilterer filterer;
	private Logger logger = LogManager.getLogger(AcademicSessionDAOImp.class);

	@Override
	public QAcademicSession getAcademicSession(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QAcademicSession> select = cb.createQuery(QAcademicSession.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QAcademicSession) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QAcademicSession> getAllAcademicSessions(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QAcademicSession> select = cb.createQuery(QAcademicSession.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllAcademicSessions(metadata));
		}
		return (List<QAcademicSession>) q.getResultList();
	}

	@Override
	public QAcademicSession getCalendar(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QAcademicSession> select = cb.createQuery(QAcademicSession.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), SCHOOL_YEAR),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QAcademicSession) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QAcademicSession> getAllCalendars(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QAcademicSession> select = cb.createQuery(QAcademicSession.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), SCHOOL_YEAR),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllCalendars(metadata));
		}
		return (List<QAcademicSession>) q.getResultList();
	}

	@Override
	public QAcademicSession getTerm(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QAcademicSession> select = cb.createQuery(QAcademicSession.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), TERM),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QAcademicSession) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QAcademicSession> getAllTerms(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QAcademicSession> select = cb.createQuery(QAcademicSession.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), TERM),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllTerms(metadata));
		}
		return (List<QAcademicSession>) q.getResultList();
	}

	@Override
	public int countAllAcademicSessions(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllCalendars(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), SCHOOL_YEAR),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllTerms(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QAcademicSession> from = select.from(QAcademicSession.class);
		final SetJoin<QAcademicSession, QAcademicSessionChild> children = (SetJoin<QAcademicSession, QAcademicSessionChild>) from.<QAcademicSession, QAcademicSessionChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), TERM),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}