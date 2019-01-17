package org.ricone.api.oneroster.request.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.CourseSectionWrapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.xpress.component.BaseDAO;
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
	private final String PRIMARY_KEY = "courseSectionRefId";
	private final String SCHOOL_YEAR_KEY = "courseSectionSchoolYear";

	@Override
	public CourseSectionWrapper getClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
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
	public List<CourseSectionWrapper> getAllClasses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> getClassesForTerm(ControllerData metadata, String refId) throws Exception {
		return null;
	}

	@Override
	public List<CourseSectionWrapper> getClassesForCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(course.get("courseRefId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> getClassesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						cb.equal(school.get("schoolRefId"), refId),
						cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
						lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> getClassesForStudent(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, StudentCourseSection> studentCourseSection = from.join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = studentCourseSection.join(JOIN_STUDENT, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(student.get("studentRefId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> getClassesForTeacher(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);
		final Join<CourseSection, StaffCourseSection> staffCourseSection = from.join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSection.join(JOIN_STAFF, JoinType.LEFT);
		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(staff.get("staffRefId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<CourseSectionWrapper> getClassesForUser(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<CourseSectionWrapper> select = cb.createQuery(CourseSectionWrapper.class);
		final Root<CourseSection> from = select.from(CourseSection.class);

		final Join<CourseSection, StudentCourseSection> studentCourseSection = from.join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = studentCourseSection.join(JOIN_STUDENT, JoinType.LEFT);

		final Join<CourseSection, StaffCourseSection> staffCourseSection = from.join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSection.join(JOIN_STAFF, JoinType.LEFT);

		final Join<CourseSection, Course> course = from.join(JOIN_COURSE, JoinType.LEFT);
		final Join<Course, School> school = course.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(CourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.or( //TODO - IDK if this works...
					cb.equal(student.get("studentRefId"), refId),
					cb.equal(staff.get("staffRefId"), refId)
				),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<CourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
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