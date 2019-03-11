package org.ricone.api.oneroster.v1p1.request.courses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QCourse;
import org.ricone.api.core.model.view.CourseGradeView;
import org.ricone.api.core.model.view.CourseSubjectView;
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

@Repository("OneRoster2:Courses:CourseDAO")
@SuppressWarnings({"unchecked", "unused"})
class CourseDAOImp extends BaseDAOTest implements CourseDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private CourseFilterer filterer;
	private Logger logger = LogManager.getLogger(CourseDAOImp.class);

	@Override
	public QCourse getCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QCourse> select = cb.createQuery(QCourse.class);
		final Root<QCourse> from = select.from(QCourse.class);
		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

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
			return (QCourse) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QCourse> getAllCourses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QCourse> select = cb.createQuery(QCourse.class);
		final Root<QCourse> from = select.from(QCourse.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
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
			metadata.getPaging().setPagingHeaders(countAllCourses(metadata));
		}
		return (List<QCourse>) q.getResultList();
	}

	@Override
	public List<QCourse> getCoursesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QCourse> select = cb.createQuery(QCourse.class);
		final Root<QCourse> from = select.from(QCourse.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(FIELD_ORG_ID), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
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
			metadata.getPaging().setPagingHeaders(countCoursesForSchool(metadata, refId));
		}
		return (List<QCourse>) q.getResultList();
	}

	@Override
	public int countAllCourses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QCourse> from = select.from(QCourse.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

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
	public int countCoursesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QCourse> from = select.from(QCourse.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
				cb.equal(from.get(FIELD_ORG_ID), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}