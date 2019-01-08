package org.ricone.api.oneroster.request.enrollments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentCourseSectionWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Enrollments:StudentDAO")
@SuppressWarnings({"unchecked", "unused"})
class StudentDAOImp extends BaseDAO implements StudentDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(StudentDAOImp.class);
	private final String PRIMARY_KEY = "studentCourseSectionRefId";
	private final String SCHOOL_YEAR_KEY = "studentCourseSectionSchoolYear";

	@Override
	public StudentCourseSectionWrapper getEnrollment(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentCourseSectionWrapper> select = cb.createQuery(StudentCourseSectionWrapper.class);
		final Root<StudentCourseSection> from = select.from(StudentCourseSection.class);
		final Join<StudentCourseSection, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentCourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			StudentCourseSectionWrapper instance = (StudentCourseSectionWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<StudentCourseSectionWrapper> getAllEnrollments(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentCourseSectionWrapper> select = cb.createQuery(StudentCourseSectionWrapper.class);
		final Root<StudentCourseSection> from = select.from(StudentCourseSection.class);
		final Join<StudentCourseSection, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentCourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		 /*if (metaData.getPaging().isPaged()) {
            q.setFirstResult((metaData.getPaging().getPageNumber()-1) * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());

            //If Paging - Set MetaData PagingInfo Total Objects
            metaData.getPaging().setTotalObjects(countAll(em, metaData));
        }*/

		List<StudentCourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentCourseSectionWrapper> getEnrollmentsForSchool(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentCourseSectionWrapper> select = cb.createQuery(StudentCourseSectionWrapper.class);
		final Root<StudentCourseSection> from = select.from(StudentCourseSection.class);
		final Join<StudentCourseSection, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentCourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(school.get("schoolRefId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		 /*if (metaData.getPaging().isPaged()) {
            q.setFirstResult((metaData.getPaging().getPageNumber()-1) * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());

            //If Paging - Set MetaData PagingInfo Total Objects
            metaData.getPaging().setTotalObjects(countAll(em, metaData));
        }*/

		List<StudentCourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentCourseSectionWrapper> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentCourseSectionWrapper> select = cb.createQuery(StudentCourseSectionWrapper.class);
		final Root<StudentCourseSection> from = select.from(StudentCourseSection.class);
		final Join<StudentCourseSection, CourseSection> courseSection = from.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final Join<StudentCourseSection, Student> student = from.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentCourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(school.get("schoolRefId"), schoolId),
				cb.equal(courseSection.get("courseSectionRefId"), classId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		 /*if (metaData.getPaging().isPaged()) {
            q.setFirstResult((metaData.getPaging().getPageNumber()-1) * metaData.getPaging().getPageSize());
            q.setMaxResults(metaData.getPaging().getPageSize());

            //If Paging - Set MetaData PagingInfo Total Objects
            metaData.getPaging().setTotalObjects(countAll(em, metaData));
        }*/

		List<StudentCourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(StudentCourseSectionWrapper instance) {
		Hibernate.initialize(instance.getStudentCourseSection().getStudent());
		Hibernate.initialize(instance.getStudentCourseSection().getCourseSection());
	}

	private void initialize(List<StudentCourseSectionWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentCourseSection().getStudent());
			Hibernate.initialize(wrapper.getStudentCourseSection().getCourseSection());
		});
	}
}