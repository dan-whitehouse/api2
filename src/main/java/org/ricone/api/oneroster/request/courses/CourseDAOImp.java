package org.ricone.api.oneroster.request.courses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.CourseGradeView;
import org.ricone.api.core.model.view.CourseSubjectView;
import org.ricone.api.core.model.view.CourseView;
import org.ricone.api.oneroster.component.BaseDAO;
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

@Repository("OneRoster:Courses:CourseDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class CourseDAOImp extends BaseDAOTest implements CourseDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private CourseFilterer filterer;
	private Logger logger = LogManager.getLogger(CourseDAOImp.class);

	@Override
	public CourseView getCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseView> select = cb.createQuery(CourseView.class);
		final Root<CourseView> from = select.from(CourseView.class);

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
		final SetJoin<CourseView, CourseSubjectView> subjects = (SetJoin<CourseView, CourseSubjectView>) from.<CourseView, CourseSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<CourseView, CourseGradeView> grades = (SetJoin<CourseView, CourseGradeView>) from.<CourseView, CourseGradeView>join("grades", JoinType.LEFT).alias("grades");
		filterer.addJoins(from, subjects, grades);

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
		return (List<CourseView>) q.getResultList();
	}

	@Override
	public List<CourseView> getCoursesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseView> select = cb.createQuery(CourseView.class);
		final Root<CourseView> from = select.from(CourseView.class);

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
		return (List<CourseView>) q.getResultList();
	}

	@Override
	public int countAllCourses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseView> from = select.from(CourseView.class);
		final SetJoin<CourseView, CourseSubjectView> subjects = (SetJoin<CourseView, CourseSubjectView>) from.<CourseView, CourseSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<CourseView, CourseGradeView> grades = (SetJoin<CourseView, CourseGradeView>) from.<CourseView, CourseGradeView>join("grades", JoinType.LEFT).alias("grades");
		filterer.addJoins(from, subjects, grades);

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
		final Root<CourseView> from = select.from(CourseView.class);

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