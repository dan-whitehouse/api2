package org.ricone.api.oneroster.request.courses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.view.CourseView;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Courses:CourseDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class CourseDAOImp extends BaseDAO implements CourseDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(CourseDAOImp.class);

	@Override
	public CourseView getCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseView> select = cb.createQuery(CourseView.class);
		final Root<CourseView> from = select.from(CourseView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (CourseView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<CourseView> getAllCourses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseView> select = cb.createQuery(CourseView.class);
		final Root<CourseView> from = select.from(CourseView.class);

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

		return (List<CourseView>) q.getResultList();
	}

	@Override
	public List<CourseView> getCoursesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseView> select = cb.createQuery(CourseView.class);
		final Root<CourseView> from = select.from(CourseView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get("orgId"), refId),
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

		return (List<CourseView>) q.getResultList();
	}
}