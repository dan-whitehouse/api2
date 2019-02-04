package org.ricone.api.oneroster.request.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.UserClassView;
import org.ricone.api.core.model.view.UserOrgView;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Users:UserDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class UserDAOImp extends BaseDAO implements UserDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(UserDAOImp.class);

	@Override
	public UserView getUser(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (UserView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<UserView> getAllUsers(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(false);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllUsers(metadata));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public UserView getStudent(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (UserView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<UserView> getAllStudents(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllStudents(metadata));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public List<UserView> getStudentsForSchool(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join(JOIN_USER_ORGS, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
				cb.equal(userOrgs.get(FIELD_ORG_ID), refId),
				cb.equal(userOrgs.get(FIELD_ORG_TYPE), "school")
			)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countStudentsForClass(metadata, refId));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public List<UserView> getStudentsForClass(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_CLASS_ID), refId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countStudentsForClass(metadata, refId));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public List<UserView> getStudentsForClassInSchool(ControllerData metadata, String schoolId, String classId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_ORG_ID), schoolId),
			cb.equal(userClasses.get(FIELD_CLASS_ID), classId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countStudentsForClassInSchool(metadata, schoolId, classId));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public UserView getTeacher(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(FIELD_ROLE), "teacher"),
				from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			return (UserView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<UserView> getAllTeachers(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllTeachers(metadata));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public List<UserView> getTeachersForSchool(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join(JOIN_USER_ORGS, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
				cb.equal(userOrgs.get(FIELD_ORG_ID), refId),
				cb.equal(userOrgs.get(FIELD_ORG_TYPE), "school")
			)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countTeachersForSchool(metadata, refId));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public List<UserView> getTeachersForClass(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_CLASS_ID), refId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countTeachersForClass(metadata, refId));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public List<UserView> getTeachersForClassInSchool(ControllerData metadata, String schoolId, String classId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_ORG_ID), schoolId),
			cb.equal(userClasses.get(FIELD_CLASS_ID), classId)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countTeachersForClassInSchool(metadata, schoolId, classId));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public UserView getContact(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "contact"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (UserView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<UserView> getAllContacts(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "contact"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllContacts(metadata));
		}
		return (List<UserView>) q.getResultList();
	}

	@Override
	public int countAllUsers(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllStudents(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForSchool(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join(JOIN_USER_ORGS, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
					cb.equal(userOrgs.get(FIELD_ORG_ID), refId),
					cb.equal(userOrgs.get(FIELD_ORG_TYPE), "school")
			)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForClass(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_CLASS_ID), refId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countStudentsForClassInSchool(ControllerData metadata, String schoolId, String classId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_ORG_ID), schoolId),
			cb.equal(userClasses.get(FIELD_CLASS_ID), classId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllTeachers(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForSchool(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join(JOIN_USER_ORGS, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.and(
					cb.equal(userOrgs.get(FIELD_ORG_ID), refId),
					cb.equal(userOrgs.get(FIELD_ORG_TYPE), "school")
			)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForClass(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_CLASS_ID), refId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countTeachersForClassInSchool(ControllerData metadata, String schoolId, String classId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join(JOIN_USER_CLASSES, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
			cb.equal(userClasses.get(FIELD_ORG_ID), schoolId),
			cb.equal(userClasses.get(FIELD_CLASS_ID), classId)
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllContacts(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<UserView> from = select.from(UserView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ROLE), "contact"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}