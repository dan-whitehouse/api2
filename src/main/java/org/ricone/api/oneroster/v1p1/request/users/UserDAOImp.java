package org.ricone.api.oneroster.v1p1.request.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.*;
import org.ricone.api.core.model.view.UserClassView;
import org.ricone.api.oneroster.component.BaseDAOTest;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster2:Users:UserDAO")
@SuppressWarnings({"unchecked", "unused"})
class UserDAOImp extends BaseDAOTest implements UserDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private UserFilterer filterer;
	private Logger logger = LogManager.getLogger(UserDAOImp.class);

	@Override
	public QUser getUser(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<QUser> getAllUsers(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);


		select.distinct(false);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllUsers(metadata));
		}

		logger.debug("q.toString: " + q.toString());


		return (List<QUser>) q.getResultList();
	}

	@Override
	public QUser getStudent(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<QUser> getAllStudents(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<QUser> getStudentsForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(FIELD_ORG_ID), refId),
				cb.equal(orgs.get(FIELD_ORG_TYPE), "school")
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
	public List<QUser> getStudentsForClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_CLASS_ID), refId)
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
	public List<QUser> getStudentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_ORG_ID), schoolId),
			cb.equal(classes.get(FIELD_CLASS_ID), classId)
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
	public QUser getTeacher(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<QUser> getAllTeachers(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<QUser> getTeachersForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(FIELD_ORG_ID), refId),
				cb.equal(orgs.get(FIELD_ORG_TYPE), "school")
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
	public List<QUser> getTeachersForClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_CLASS_ID), refId)
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
	public List<QUser> getTeachersForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_ORG_ID), schoolId),
			cb.equal(classes.get(FIELD_CLASS_ID), classId)
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
	public QUser getContact(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "contact"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<QUser> getAllContacts(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QUser> select = cb.createQuery(QUser.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "contact"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public int countAllUsers(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllStudents(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(FIELD_ORG_ID), refId),
				cb.equal(orgs.get(FIELD_ORG_TYPE), "school")
			)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_CLASS_ID), refId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_ORG_ID), schoolId),
			cb.equal(classes.get(FIELD_CLASS_ID), classId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllTeachers(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
				cb.equal(orgs.get(FIELD_ORG_ID), refId),
				cb.equal(orgs.get(FIELD_ORG_TYPE), "school")
			)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_CLASS_ID), refId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserClass> classes = (SetJoin<QUser, QUserClass>) from.<QUser, QUserClass>join("classes", JoinType.LEFT);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(classes.get(FIELD_ORG_ID), schoolId),
			cb.equal(classes.get(FIELD_CLASS_ID), classId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllContacts(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QUser> from = select.from(QUser.class);
		final SetJoin<QUser, QUserIdentifier> userIds = (SetJoin<QUser, QUserIdentifier>) from.<QUser, QUserIdentifier>join("identifiers", JoinType.LEFT).alias("userIds");
		final SetJoin<QUser, QUserAgent> agents = (SetJoin<QUser, QUserAgent>) from.<QUser, QUserAgent>join("agents", JoinType.LEFT).alias("agents");
		final SetJoin<QUser, QUserOrg> orgs = (SetJoin<QUser, QUserOrg>) from.<QUser, QUserOrg>join("orgs", JoinType.LEFT).alias("orgs");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, userIds, agents, orgs);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "contact"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}