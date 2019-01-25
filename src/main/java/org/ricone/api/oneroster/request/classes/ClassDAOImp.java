package org.ricone.api.oneroster.request.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.view.ClassTermView;
import org.ricone.api.core.model.view.ClassUserView;
import org.ricone.api.core.model.view.ClassView;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Classes:ClassDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class ClassDAOImp extends BaseDAO implements ClassDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(ClassDAOImp.class);

	@Override
	public ClassView getClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019)
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		try {
			ClassView instance = (ClassView) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<ClassView> getAllClasses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
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

		List<ClassView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<ClassView> getClassesForTerm(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassTermView> classTerms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join(JOIN_CLASS_TERMS, JoinType.LEFT);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
			cb.equal(classTerms.get("termId"), refId)
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

		List<ClassView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<ClassView> getClassesForCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
			cb.equal(from.get("courseId"), refId)
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

		List<ClassView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<ClassView> getClassesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
			cb.equal(from.get("orgId"), refId)
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

		List<ClassView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<ClassView> getClassesForStudent(ControllerData metadata, String refId) throws Exception {
		try {
			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
			final Root<ClassView> from = select.from(ClassView.class);
			final SetJoin<ClassView, ClassUserView> classUsers = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT);

			final Predicate methodSpecificPredicate = cb.and(
					cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
					cb.equal(classUsers.get("userId"), refId),
					cb.equal(classUsers.get("role"), "student")
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

			List<ClassView> instance = q.getResultList();
			initialize(instance);
			return instance;
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<ClassView> getClassesForTeacher(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassUserView> classUsers = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(classUsers.get("userId"), refId),
			cb.equal(classUsers.get("role"), "teacher")
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

		List<ClassView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<ClassView> getClassesForUser(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassUserView> classUsers = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(classUsers.get("userId"), refId)
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

		List<ClassView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(ClassView instance) {
		Hibernate.initialize(instance.getGrades());
		Hibernate.initialize(instance.getPeriods());
		Hibernate.initialize(instance.getSubjects());
		Hibernate.initialize(instance.getTerms());
	}

	private void initialize(List<ClassView> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getGrades());
			Hibernate.initialize(wrapper.getPeriods());
			Hibernate.initialize(wrapper.getSubjects());
			Hibernate.initialize(wrapper.getTerms());
		});
	}
}