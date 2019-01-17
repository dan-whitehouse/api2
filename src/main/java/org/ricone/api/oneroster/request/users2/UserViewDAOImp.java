package org.ricone.api.oneroster.request.users2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.UserClassView;
import org.ricone.api.core.model.view.UserOrgView;
import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.xpress.component.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Users2:UserViewDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class UserViewDAOImp extends BaseDAO implements UserViewDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(UserViewDAOImp.class);
	private final String PRIMARY_KEY = "sourceId";
	private final String SCHOOL_YEAR_KEY = "sourceSchoolYear";
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
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019)//,
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<UserView> instance = q.getResultList();
		return instance;
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

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "student")
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join("userOrgs", JoinType.LEFT);
		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "student"),
				cb.and(
					cb.equal(userOrgs.get("orgId"), refId),
					cb.equal(userOrgs.get("orgType"), "school")
				)
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join("userClasses", JoinType.LEFT);
		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "student"),
				cb.equal(userClasses.get("classId"), refId)
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join("userClasses", JoinType.LEFT);
		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "student"),
				cb.equal(userClasses.get("orgId"), schoolId),
				cb.equal(userClasses.get("classId"), classId)
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "teacher")
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join("userOrgs", JoinType.LEFT);
		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "teacher"),
				cb.and(
						cb.equal(userOrgs.get("orgId"), refId),
						cb.equal(userOrgs.get("orgType"), "school")
				)
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join("userClasses", JoinType.LEFT);
		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "teacher"),
				cb.equal(userClasses.get("classId"), refId)
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
		final SetJoin<UserView, UserClassView> userClasses = (SetJoin<UserView, UserClassView>) from.<UserView, UserClassView>join("userClasses", JoinType.LEFT);
		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "student"),
				cb.equal(userClasses.get("orgId"), schoolId),
				cb.equal(userClasses.get("classId"), classId)
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get(ROLE), "contact")
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<UserView> instance = q.getResultList();
		return instance;
	}
}