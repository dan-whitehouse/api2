package org.ricone.api.oneroster.request.courses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.request.xCourse.XCourseDAO;
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
	private final String PRIMARY_KEY = "courseRefId";
	private final String SCHOOL_YEAR_KEY = "courseSchoolYear";

	@Override
	public CourseWrapper getCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			CourseWrapper instance = (CourseWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<CourseWrapper> getAllCourses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		/*if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getOffset()-1) * metadata.getPaging().getLimit());
			q.setMaxResults(metadata.getPaging().getLimit());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}*/

		List<CourseWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseWrapper> getCoursesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(school.get("schoolRefId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		/*if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getOffset()-1) * metadata.getPaging().getLimit());
			q.setMaxResults(metadata.getPaging().getLimit());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}*/

		List<CourseWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(CourseWrapper instance) {
		Hibernate.initialize(instance.getCourse().getCourseIdentifiers());
		Hibernate.initialize(instance.getCourse().getCourseGrades());
		Hibernate.initialize(instance.getCourse().getSchool());
	}

	private void initialize(List<CourseWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getCourse().getCourseIdentifiers());
			Hibernate.initialize(wrapper.getCourse().getCourseGrades());
			Hibernate.initialize(wrapper.getCourse().getSchool());
		});
	}
}