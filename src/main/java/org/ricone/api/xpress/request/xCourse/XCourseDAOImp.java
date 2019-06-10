package org.ricone.api.xpress.request.xCourse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.CourseWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("XPress:XCourses:XCourseDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XCourseDAOImp extends BaseDAO implements XCourseDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(XCourseDAOImp.class);
	private final String PRIMARY_KEY = "courseRefId";
	private final String SCHOOL_YEAR_KEY = "courseSchoolYear";

	@Override
	public CourseWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearByRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

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
	public List<CourseWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAll(metadata);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}

		List<CourseWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseWrapper> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByLeaRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByLeaRefId(metadata, refId));
		}

		List<CourseWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseWrapper> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllBySchoolRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}

		List<CourseWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseWrapper> findAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseWrapper> select = cb.createQuery(CourseWrapper.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) from.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByRosterRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(courseSections.get("courseSectionRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByRosterRefId(metadata, refId));
		}

		List<CourseWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(lea.get("leaRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(school.get("schoolRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) from.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(courseSections.get("courseSectionRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAll(metadata));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByLeaRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(lea.get("leaRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllBySchoolRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(school.get("schoolRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Course> from = select.from(Course.class);
		final Join<Course, School> school = from.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);
		final SetJoin<Course, CourseIdentifier> courseIdentifiers = (SetJoin<Course, CourseIdentifier>) from.<Course, CourseIdentifier>join(JOIN_COURSE_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Course, CourseGrade> courseGrades = (SetJoin<Course, CourseGrade>) from.<Course, CourseGrade>join(JOIN_COURSE_GRADES, JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) from.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByRosterRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(courseSections.get("courseSectionRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
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