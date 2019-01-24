package org.ricone.api.oneroster.request.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.UserClassView;
import org.ricone.api.core.model.view.UserOrgView;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.component.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("OneRoster:Users:UserDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class UserDAOImp extends BaseDAO implements UserDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(UserDAOImp.class);
	private final String PRIMARY_KEY = "sourcedId";
	private final String SCHOOL_YEAR_KEY = "sourcedSchoolYear";
	private final String ROLE = "role";

	@Override
	public UserView getUser(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
			)
		);

		Query q = em.createQuery(select);
		try {
			UserView instance = (UserView) q.getSingleResult();
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<UserView> getAllUsers(ControllerData metadata) {
		try {
			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
			final Root<UserView> from = select.from(UserView.class);

			//Method Specific Predicate
			final Predicate methodSpecificPredicate = cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019)
			);

			select.distinct(false);
			select.select(from);
			select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
			select.orderBy(getSortOrder(metadata, cb, from));

			Query q = em.createQuery(select);
			if(metadata.getPaging().isPaged()) {
				q.setFirstResult(metadata.getPaging().getOffset());
				q.setMaxResults(metadata.getPaging().getLimit());
			}

			List<UserView> instance = q.getResultList();
			return instance;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserView getStudent(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				cb.equal(from.get(ROLE), "student")
			)
		);

		Query q = em.createQuery(select);
		try {
			UserView instance = (UserView) q.getSingleResult();
			return instance;
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
			cb.equal(from.get(ROLE), "student")
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

		List<UserView> instance = q.getResultList();
		return instance;
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
			cb.equal(from.get(ROLE), "student"),
			cb.and(
				cb.equal(userOrgs.get("orgId"), refId),
				cb.equal(userOrgs.get("orgType"), "school")
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
		}

		List<UserView> instance = q.getResultList();
		return instance;
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
			cb.equal(from.get(ROLE), "student"),
			cb.equal(userClasses.get("classId"), refId)
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

		List<UserView> instance = q.getResultList();
		return instance;
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
			cb.equal(from.get(ROLE), "student"),
			cb.equal(userClasses.get("orgId"), schoolId),
			cb.equal(userClasses.get("classId"), classId)
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

		List<UserView> instance = q.getResultList();
		return instance;
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
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				cb.equal(from.get(ROLE), "teacher")
			)
		);

		Query q = em.createQuery(select);
		try {
			UserView instance = (UserView) q.getSingleResult();
			return instance;
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
			cb.equal(from.get(ROLE), "teacher")
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

		List<UserView> instance = q.getResultList();
		return instance;
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
			cb.equal(from.get(ROLE), "teacher"),
			cb.and(
				cb.equal(userOrgs.get("orgId"), refId),
				cb.equal(userOrgs.get("orgType"), "school")
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
		}

		List<UserView> instance = q.getResultList();
		return instance;
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
			cb.equal(from.get(ROLE), "teacher"),
			cb.equal(userClasses.get("classId"), refId)
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

		List<UserView> instance = q.getResultList();
		return instance;
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
			cb.equal(from.get(ROLE), "student"),
			cb.equal(userClasses.get("orgId"), schoolId),
			cb.equal(userClasses.get("classId"), classId)
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

		List<UserView> instance = q.getResultList();
		return instance;
	}

	@Override
	public UserView getContact(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				cb.equal(from.get(ROLE), "contact")
			)
		);

		Query q = em.createQuery(select);
		try {
			UserView instance = (UserView) q.getSingleResult();
			return instance;
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
			cb.equal(from.get(ROLE), "contact")
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

		List<UserView> instance = q.getResultList();
		return instance;
	}
}