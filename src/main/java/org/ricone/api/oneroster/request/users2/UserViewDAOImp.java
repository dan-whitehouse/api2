package org.ricone.api.oneroster.request.users2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.view.UserAgentView;
import org.ricone.api.core.model.view.UserIdentifierView;
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

	@Override
	public UserView getUser(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<UserView> select = cb.createQuery(UserView.class);
		final Root<UserView> from = select.from(UserView.class);
		final SetJoin<UserView, UserIdentifierView> userIds = (SetJoin<UserView, UserIdentifierView>) from.<UserView, UserIdentifierView>join("userIds", JoinType.LEFT);
		final SetJoin<UserView, UserOrgView> userOrgs = (SetJoin<UserView, UserOrgView>) from.<UserView, UserOrgView>join("userOrgs", JoinType.LEFT);

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
		//initialize(instance);
		return instance;
	}

	@Override
	public UserView getStudent(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getAllStudents(ControllerData metadata) {
		return null;
	}

	@Override
	public List<UserView> getStudentsForSchool(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getStudentsForClass(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) {
		return null;
	}

	@Override
	public UserView getTeacher(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getAllTeachers(ControllerData metadata) {
		return null;
	}

	@Override
	public List<UserView> getTeachersForSchool(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getTeachersForClass(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) {
		return null;
	}

	@Override
	public UserView getContact(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<UserView> getAllContacts(ControllerData metadata) {
		return null;
	}

	private void initialize(UserView instance) {
		Hibernate.initialize(instance.getUserIds());
		Hibernate.initialize(instance.getUserOrgs());
		Hibernate.initialize(instance.getUserAgents());
	}

	private void initialize(List<UserView> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getUserIds());
			Hibernate.initialize(wrapper.getUserOrgs());
			Hibernate.initialize(wrapper.getUserAgents());
		});
	}
}