package org.ricone.api.oneroster.request.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QClass;
import org.ricone.api.core.model.v1p1.QClassAcademicSession;
import org.ricone.api.core.model.v1p1.QUserClass;
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

@Repository("OneRoster:Classes:ClassDAO")
@SuppressWarnings({"unchecked", "unused"})
class ClassDAOImp extends BaseDAO implements ClassDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private ClassFilterer filterer;
	private Logger logger = LogManager.getLogger(ClassDAOImp.class);

	@Override
	public QClass getClass(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		try {
			return (QClass) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QClass> getAllClasses(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllClasses(metadata));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public List<QClass> getClassesForTerm(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(terms.get(ACADEMIC_SESSION).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countClassesForTerm(metadata, refId));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public List<QClass> getClassesForCourse(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(COURSE).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countClassesForCourse(metadata, refId));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public List<QClass> getClassesForSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ORG).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countClassesForSchool(metadata, refId));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public List<QClass> getClassesForStudent(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		final SetJoin<QClass, QUserClass> users = (SetJoin<QClass, QUserClass>) from.<QClass, QUserClass>join(USERS, JoinType.LEFT).alias(USERS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(users.get(USER).get(SOURCED_ID), refId),
			cb.equal(users.get(USER).get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countClassesForStudent(metadata, refId));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public List<QClass> getClassesForTeacher(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		final SetJoin<QClass, QUserClass> users = (SetJoin<QClass, QUserClass>) from.<QClass, QUserClass>join(USERS, JoinType.LEFT).alias(USERS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(users.get(USER).get(SOURCED_ID), refId),
			cb.equal(users.get(USER).get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countClassesForTeacher(metadata, refId));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public List<QClass> getClassesForUser(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QClass> select = cb.createQuery(QClass.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		final SetJoin<QClass, QUserClass> users = (SetJoin<QClass, QUserClass>) from.<QClass, QUserClass>join(USERS, JoinType.LEFT).alias(USERS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(users.get(USER).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countClassesForUser(metadata, refId));
		}
		return (List<QClass>) q.getResultList();
	}

	@Override
	public int countAllClasses(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForTerm(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(terms.get(ACADEMIC_SESSION).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForCourse(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(COURSE).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ORG).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForStudent(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		final SetJoin<QClass, QUserClass> users = (SetJoin<QClass, QUserClass>) from.<QClass, QUserClass>join(USERS, JoinType.LEFT).alias(USERS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(users.get(USER).get(SOURCED_ID), refId),
			cb.equal(users.get(USER).get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForTeacher(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		final SetJoin<QClass, QUserClass> users = (SetJoin<QClass, QUserClass>) from.<QClass, QUserClass>join(USERS, JoinType.LEFT).alias(USERS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(users.get(USER).get(SOURCED_ID), refId),
			cb.equal(users.get(USER).get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForUser(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QClass> from = select.from(QClass.class);
		final SetJoin<QClass, QClassAcademicSession> terms = (SetJoin<QClass, QClassAcademicSession>) from.<QClass, QClassAcademicSession>join(TERMS, JoinType.LEFT).alias(TERMS);
		final SetJoin<QClass, QUserClass> users = (SetJoin<QClass, QUserClass>) from.<QClass, QUserClass>join(USERS, JoinType.LEFT).alias(USERS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(users.get(USER).get(SOURCED_ID), refId),
			from.get(DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}