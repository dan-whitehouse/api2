package org.ricone.api.oneroster.request.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.oneroster.*;
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

@Repository("OneRoster:Users:UserDAO")
@SuppressWarnings({"unchecked", "unused"})
class UserDAOImp extends BaseDAO implements UserDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private UserFilterer filterer;
	private Logger logger = LogManager.getLogger(UserDAOImp.class);

	@Override
	public QUser getUser(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

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
			return (QUser) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QUser> getAllUsers(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
			//cb.notEqual(from.get(ROLE), "other")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllUsers(metadata));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public QUser getStudent(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QUser) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QUser> getAllStudents(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
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
			metadata.getPaging().setPagingHeaders(countAllStudents(metadata));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public List<QUser> getStudentsForSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(ORG).get(SOURCED_ID), refId),
				cb.equal(orgs.get(ORG).get(TYPE), SCHOOL)
			)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countStudentsForClass(metadata, refId));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public List<QUser> getStudentsForClass(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), refId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countStudentsForClass(metadata, refId));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public List<QUser> getStudentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(ORG).get(SOURCED_ID), schoolId),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), classId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countStudentsForClassInSchool(metadata, schoolId, classId));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public QUser getTeacher(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QUser) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QUser> getAllTeachers(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
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
			metadata.getPaging().setPagingHeaders(countAllTeachers(metadata));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public List<QUser> getTeachersForSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(ORG).get(SOURCED_ID), refId),
				cb.equal(orgs.get(ORG).get(TYPE), SCHOOL)
			)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countTeachersForSchool(metadata, refId));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public List<QUser> getTeachersForClass(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), refId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countTeachersForClass(metadata, refId));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public List<QUser> getTeachersForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(ORG).get(SOURCED_ID), schoolId),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), classId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countTeachersForClassInSchool(metadata, schoolId, classId));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public QUser getContact(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), CONTACT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QUser) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QUser> getAllContacts(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), CONTACT),
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
			metadata.getPaging().setPagingHeaders(countAllContacts(metadata));
		}
		return (List<QUser>) q.getResultList();
	}

	@Override
	public int countAllUsers(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

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
	public int countAllStudents(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(ORG).get(SOURCED_ID), refId),
				cb.equal(orgs.get(ORG).get(TYPE), SCHOOL)
			)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForClass(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), refId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), STUDENT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(ORG).get(SOURCED_ID), schoolId),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), classId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllTeachers(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(ORG).get(SOURCED_ID), refId),
				cb.equal(orgs.get(ORG).get(TYPE), SCHOOL)
			)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForClass(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), refId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForClassInSchool(RequestData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join(CLASSES, JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), TEACHER),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds()),
			cb.equal(classes.get(ORG).get(SOURCED_ID), schoolId),
			cb.equal(classes.get(CLASS).get(SOURCED_ID), classId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllContacts(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> identifiers = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join(IDENTIFIERS, JoinType.LEFT).alias(IDENTIFIERS);
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join(AGENTS, JoinType.LEFT).alias(AGENTS);
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join(ORGS, JoinType.LEFT).alias(ORGS);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, identifiers, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(ROLE), CONTACT),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}