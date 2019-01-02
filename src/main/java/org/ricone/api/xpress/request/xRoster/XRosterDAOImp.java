package org.ricone.api.xpress.request.xRoster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.request.xSchool.XSchoolDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XRosterDAOImp extends BaseDAO implements XRosterDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(XRosterDAOImp.class);
	private final String PRIMARY_KEY = "courseSectionRefId";
	private final String SCHOOL_YEAR_KEY = "courseSectionSchoolYear";


	@Override
	public CourseSectionWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			CourseSectionWrapper instance = (CourseSectionWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;

	}

	@Override
	public List<CourseSectionWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			//metadata.getPaging().setTotalObjects(countAll(em, metadata));
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(lea.get("leaRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			//metadata.getPaging().setTotalObjects(countAllByLeaRefId(em, metadata, refId));
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(school.get("schoolRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			//metadata.getPaging().setTotalObjects(countAllBySchoolRefId(em, metadata, refId));
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> findAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByCourseRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(course.get("courseRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber() - 1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			//metadata.getPaging().setTotalObjects(countAllByCourseRefId(em, metadata, refId));
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>join(JOIN_STAFF_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>join(JOIN_STAFF_IDENTIFIERS, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByStaffRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(staff.get("staffRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			//metadata.getPaging().setTotalObjects(countAllByStaffRefId(em, metadata, refId));
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> findAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentCourseSections.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByStudentRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(student.get("studentRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			//metadata.getPaging().setTotalObjects(countAllByStudentRefId(em, metadata, refId));
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(from.get(PRIMARY_KEY), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(lea.get("leaRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(school.get("schoolRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(course.get("courseRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>join(JOIN_STAFF_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>join(JOIN_STAFF_IDENTIFIERS, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(staff.get("staffRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentCourseSections.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(student.get("studentRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByCourseRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(course.get("courseRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) from.<CourseSection, StaffCourseSection>join(JOIN_STAFF_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join(JOIN_STAFF, JoinType.LEFT);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) staff.<Staff, StaffIdentifier>join(JOIN_STAFF_IDENTIFIERS, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByStaffRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(staff.get("staffRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final SetJoin<CourseSection, StudentCourseSection> studentCourseSections = (SetJoin<CourseSection, StudentCourseSection>) from.<CourseSection, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentCourseSections.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) student.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByStudentRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(student.get("studentRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Initialize **/
	private void initialize(CourseSectionWrapper instance) {
		Hibernate.initialize(instance.getCourseSection().getCourse());
		Hibernate.initialize(instance.getCourseSection().getCourse().getSchool());
		Hibernate.initialize(instance.getCourseSection().getCourse().getSchool().getLea());
		Hibernate.initialize(instance.getCourseSection().getSchoolCalendarSession());
		Hibernate.initialize(instance.getCourseSection().getSchoolCalendarSession().getSchoolCalendar());
		Hibernate.initialize(instance.getCourseSection().getCourseSectionSchedules());
		Hibernate.initialize(instance.getCourseSection().getStaffCourseSections());
		instance.getCourseSection().getStaffCourseSections().forEach(tcs -> {
			Hibernate.initialize(tcs.getStaff());
			Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
		});
		Hibernate.initialize(instance.getCourseSection().getStudentCourseSections());
		instance.getCourseSection().getStudentCourseSections().forEach(scs -> {
			Hibernate.initialize(scs.getStudent());
			Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
		});
	}

	private void initialize(List<CourseSectionWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getCourseSection().getCourse());
			Hibernate.initialize(wrapper.getCourseSection().getCourse().getSchool());
			Hibernate.initialize(wrapper.getCourseSection().getCourse().getSchool().getLea());
			Hibernate.initialize(wrapper.getCourseSection().getSchoolCalendarSession());
			Hibernate.initialize(wrapper.getCourseSection().getSchoolCalendarSession().getSchoolCalendar());
			Hibernate.initialize(wrapper.getCourseSection().getCourseSectionSchedules());
			Hibernate.initialize(wrapper.getCourseSection().getStaffCourseSections());
			wrapper.getCourseSection().getStaffCourseSections().forEach(tcs -> {
				Hibernate.initialize(tcs.getStaff());
				Hibernate.initialize(tcs.getStaff().getStaffIdentifiers());
			});
			Hibernate.initialize(wrapper.getCourseSection().getStudentCourseSections());
			wrapper.getCourseSection().getStudentCourseSections().forEach(scs -> {
				Hibernate.initialize(scs.getStudent());
				Hibernate.initialize(scs.getStudent().getStudentIdentifiers());
			});
		});
	}
}