package org.ricone.api.xpress.request.xStudent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("XPress:XStudents:XStudentDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XStudentDAOImp extends BaseDAO implements XStudentDAO {
	@PersistenceContext private EntityManager em;
	private final Logger logger = LogManager.getLogger(XStudentDAOImp.class);
	private final String PRIMARY_KEY = "studentRefId";
	private final String ENROLLMENT_KEY = "studentEnrollmentRefId";
	private final String SCHOOL_YEAR_KEY = "studentSchoolYear";
	private final String ID_KEY = "studentId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final String SYSTEM_CODE_DISTRICT = "District";
	private final String SYSTEM_CODE_STATE = "State";

	@Override
	public StudentWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindByRefId(metadata, refId, cb, select, from, studentEnrollments, lea));

		Query q = em.createQuery(select);
		try {
			StudentWrapper instance = (StudentWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public StudentWrapper findByLocalId(ControllerData metadata, String localId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindByLocalId(metadata, localId, cb, select, from, studentEnrollments, lea, studentIdentifiers));

		Query q = em.createQuery(select);
		try {
			StudentWrapper instance = (StudentWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public StudentWrapper findByStateId(ControllerData metadata, String stateId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindByStateId(metadata, stateId, cb, select, from, studentEnrollments, lea, studentIdentifiers));

		Query q = em.createQuery(select);
		try {
			StudentWrapper instance = (StudentWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<StudentWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAll(metadata, cb, select, from, studentEnrollments, lea));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}

		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByLeaRefId(metadata, refId, cb, select, from, studentEnrollments, lea));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByLeaRefId(metadata, refId));
		}

		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllBySchoolRefId(metadata, refId, cb, select, from, studentEnrollments, lea, school));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}

		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByRosterRefId(metadata, refId, cb, select, from, studentEnrollments, lea, courseSection));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByRosterRefId(metadata, refId));
		}

		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.INNER);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join("staff", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByStaffRefId(metadata, refId, cb, select, from, studentEnrollments, lea, staff));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByStaffRefId(metadata, refId));
		}

		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentWrapper> findAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(whereFindAllByContactRefId(metadata, refId, cb, select, from, studentEnrollments, lea, studentContact));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByContactRefId(metadata, refId));
		}

		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	private Predicate whereFindByRefId(ControllerData metadata, String refId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearByRefId(metadata, refId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(from.get(PRIMARY_KEY), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(fromSq1.get(PRIMARY_KEY), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(fromSq2.get(PRIMARY_KEY), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(from.get(PRIMARY_KEY), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindByLocalId(ControllerData metadata, String localId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea, SetJoin<Student, StudentIdentifier> studentIdentifiers) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearByLocalId(metadata, localId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(studentIdentifiers.get(ID_KEY), localId), cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_DISTRICT));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentIdentifier> studentIdentifiersSq1 = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(studentIdentifiersSq1.get(ID_KEY), localId),
				cb.equal(studentIdentifiersSq1.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_DISTRICT),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentIdentifier> studentIdentifiersSq2 = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(studentIdentifiersSq2.get(ID_KEY), localId),
				cb.equal(studentIdentifiersSq2.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_DISTRICT),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			Predicate localIdPredicate = cb.and(cb.equal(studentIdentifiers.get(ID_KEY), localId), cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_DISTRICT));

			return cb.and(localIdInControllerData, schoolYearEquals, localIdPredicate, subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindByStateId(ControllerData metadata, String stateId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea, SetJoin<Student, StudentIdentifier> studentIdentifiers) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearByStateId(metadata, stateId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(studentIdentifiers.get(ID_KEY), stateId), cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_STATE));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentIdentifier> studentIdentifiersSq1 = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(studentIdentifiersSq1.get(ID_KEY), stateId),
				cb.equal(studentIdentifiersSq1.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_STATE),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentIdentifier> studentIdentifiersSq2 = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(studentIdentifiersSq2.get(ID_KEY), stateId),
				cb.equal(studentIdentifiersSq2.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_STATE),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			Predicate stateIdPredicate = cb.and(cb.equal(studentIdentifiers.get(ID_KEY), stateId), cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_STATE));
			return cb.and(localIdInControllerData, schoolYearEquals, stateIdPredicate, subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAll(ControllerData metadata, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearAll(metadata);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData);
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(localIdInControllerData, schoolYearEquals, subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByLeaRefId(ControllerData metadata, String refId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearAllByLeaRefId(metadata, refId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(lea.get("leaRefId"), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(leaSq1.get("leaRefId"), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(leaSq2.get("leaRefId"), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(lea.get("leaRefId"), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllBySchoolRefId(ControllerData metadata, String refId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from, SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea, Join<StudentEnrollment, School> school) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearAllBySchoolRefId(metadata, refId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(school.get("schoolRefId"), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(schoolSq1.get("schoolRefId"), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(schoolSq2.get("schoolRefId"), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(school.get("schoolRefId"), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByRosterRefId(ControllerData metadata, String refId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from,  SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea, Join<StudentCourseSection, CourseSection> courseSection) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearAllByRosterRefId(metadata, refId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(courseSection.get("courseSectionRefId"), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq1 = (SetJoin<Student, StudentCourseSection>) fromSq1.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq1 = studentCourseSectionsSq1.join(JOIN_COURSE_SECTION, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(courseSectionSq1.get("courseSectionRefId"), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq2 = (SetJoin<Student, StudentCourseSection>) fromSq2.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq2 = studentCourseSectionsSq2.join(JOIN_COURSE_SECTION, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(courseSectionSq2.get("courseSectionRefId"), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(courseSection.get("courseSectionRefId"), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByStaffRefId(ControllerData metadata, String refId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from,  SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea, Join<StaffCourseSection, Staff> staff) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearAllByStaffRefId(metadata, refId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(staff.get("staffRefId"), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq1 = (SetJoin<Student, StudentCourseSection>) fromSq1.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq1 = studentCourseSectionsSq1.join(JOIN_COURSE_SECTION, JoinType.INNER);
			final SetJoin<CourseSection, StaffCourseSection> staffCourseSectionsSq1 = (SetJoin<CourseSection, StaffCourseSection>) courseSectionSq1.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
			final Join<StaffCourseSection, Staff> staffSq1 = staffCourseSectionsSq1.join("staff", JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(staffSq1.get("staffRefId"), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentCourseSection> studentCourseSectionsSq2 = (SetJoin<Student, StudentCourseSection>) fromSq2.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
			final Join<StudentCourseSection, CourseSection> courseSectionSq2 = studentCourseSectionsSq2.join(JOIN_COURSE_SECTION, JoinType.INNER);
			final SetJoin<CourseSection, StaffCourseSection> staffCourseSectionsSq2 = (SetJoin<CourseSection, StaffCourseSection>) courseSectionSq2.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
			final Join<StaffCourseSection, Staff> staffSq2 = staffCourseSectionsSq2.join("staff", JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(staffSq2.get("staffRefId"), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(staff.get("staffRefId"), refId), subQuery1Predicate, subQuery2Predicate);
		}
	}

	private Predicate whereFindAllByContactRefId(ControllerData metadata, String refId, CriteriaBuilder cb, CriteriaQuery<?> select, Root<Student> from,  SetJoin<Student, StudentEnrollment> studentEnrollments, Join<School, Lea> lea, Join<StudentContactRelationship, StudentContact> studentContact) {
		Predicate localIdInControllerData = lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds());
		Predicate schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		Integer greatestSchoolYear = greatestSchoolYearAllByContactRefId(metadata, refId);

		if(!metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(greatestSchoolYear));
		}
		else {
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}

		if(metadata.getApplication().getApp().includeExitedStudents()) {
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(studentContact.get("studentContactRefId"), refId));
		}
		else {
			/* START SUBQUERY 1 */
			final Subquery<String> subQuery1 = select.subquery(String.class);
			final Root<Student> fromSq1 = subQuery1.from(Student.class);
			final SetJoin<Student, StudentContactRelationship> studentContactRelationshipsSq1 = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
			final Join<StudentContactRelationship, StudentContact> studentContactSq1 = studentContactRelationshipsSq1.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq1 = (SetJoin<Student, StudentEnrollment>) fromSq1.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq1 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq1.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq1 = studentEnrollmentsSq1.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq1 = schoolSq1.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq1 = cb.equal(fromSq1.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery1.select(studentEnrollmentsSq1.get(ENROLLMENT_KEY));
			subQuery1.where(
				leaSq1.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq1,
				cb.equal(studentContactSq1.get("studentContactRefId"), refId),
				cb.equal(studentEnrollmentsSq1.get("membershipTypeCode"), "Home"),
				cb.isNotNull(studentEnrollmentsSq1.get("enrollmentExitDate"))
			);

			/* START SUBQUERY 2 */
			final Subquery<String> subQuery2 = select.subquery(String.class);
			final Root<Student> fromSq2 = subQuery2.from(Student.class);
			final SetJoin<Student, StudentContactRelationship> studentContactRelationshipsSq2 = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
			final Join<StudentContactRelationship, StudentContact> studentContactSq2 = studentContactRelationshipsSq2.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
			final SetJoin<Student, StudentEnrollment> studentEnrollmentsSq2 = (SetJoin<Student, StudentEnrollment>) fromSq2.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
			final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodesSq2 = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollmentsSq2.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
			final Join<StudentEnrollment, School> schoolSq2 = studentEnrollmentsSq2.join(JOIN_SCHOOL, JoinType.LEFT);
			final Join<School, Lea> leaSq2 = schoolSq2.join(JOIN_LEA, JoinType.LEFT);

			Predicate schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			if(!metadata.hasSchoolYear()) {
				schoolYearEqualsSq2 = cb.equal(fromSq2.get(SCHOOL_YEAR_KEY), greatestSchoolYear);
			}

			subQuery2.select(studentEnrollmentsSq2.get(ENROLLMENT_KEY));
			subQuery2.where(
				leaSq2.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds()),
				schoolYearEqualsSq2,
				cb.equal(studentContactSq2.get("studentContactRefId"), refId),
				cb.equal(studentEnrollmentsSq2.get("membershipTypeCode"), "Home"),
				cb.equal(entryExitCodesSq2.get("entryExitType"), "Exit"),
				cb.equal(entryExitCodesSq2.get("systemTypeCode"), "CEDS")
			);

			/* PREPARE RETURN */
			Predicate subQuery1Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery1).not());
			Predicate subQuery2Predicate = cb.and(studentEnrollments.get(ENROLLMENT_KEY).in(subQuery2).not());
			return cb.and(schoolYearEquals, localIdInControllerData, cb.equal(studentContact.get("studentContactRefId"), refId),  subQuery1Predicate, subQuery2Predicate);
		}
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(from.get(PRIMARY_KEY), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearByLocalId(ControllerData metadata, String localId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(studentIdentifiers.get(ID_KEY), localId), cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_DISTRICT), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearByStateId(ControllerData metadata, String stateId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentIdentifier> studentIdentifiers = (SetJoin<Student, StudentIdentifier>) from.<Student, StudentIdentifier>join(JOIN_STUDENT_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(studentIdentifiers.get(ID_KEY), stateId), cb.equal(studentIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), SYSTEM_CODE_STATE), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(school.get("schoolRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(courseSection.get("courseSectionRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.INNER);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join("staff", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(staff.get("staffRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(studentContact.get("studentContactRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAll(metadata, cb, select, from, studentEnrollments, lea));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByLeaRefId(metadata, refId, cb, select, from, studentEnrollments, lea));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllBySchoolRefId(metadata, refId, cb, select, from, studentEnrollments, lea, school));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByRosterRefId(metadata, refId, cb, select, from, studentEnrollments, lea, courseSection));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentCourseSection> studentCourseSections = (SetJoin<Student, StudentCourseSection>) from.<Student, StudentCourseSection>join(JOIN_STUDENT_COURSE_SECTIONS, JoinType.LEFT);
		final Join<StudentCourseSection, CourseSection> courseSection = studentCourseSections.join(JOIN_COURSE_SECTION, JoinType.INNER);
		final SetJoin<CourseSection, StaffCourseSection> staffCourseSections = (SetJoin<CourseSection, StaffCourseSection>) courseSection.<CourseSection, StaffCourseSection>join("staffCourseSections", JoinType.LEFT);
		final Join<StaffCourseSection, Staff> staff = staffCourseSections.join("staff", JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByStaffRefId(metadata, refId, cb, select, from, studentEnrollments, lea, staff));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) from.<Student, StudentContactRelationship>join("studentContactRelationships", JoinType.LEFT);
		final Join<StudentContactRelationship, StudentContact> studentContact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(whereFindAllByContactRefId(metadata, refId, cb, select, from, studentEnrollments, lea, studentContact));
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Initialize **/
	private void initialize(StudentWrapper instance) {
		Hibernate.initialize(instance.getStudent().getStudentAcademicRecords());
		Hibernate.initialize(instance.getStudent().getStudentAddresses());
		Hibernate.initialize(instance.getStudent().getStudentEmails());
		Hibernate.initialize(instance.getStudent().getStudentIdentifiers());
		Hibernate.initialize(instance.getStudent().getStudentLanguages());
		Hibernate.initialize(instance.getStudent().getStudentOtherNames());
		Hibernate.initialize(instance.getStudent().getStudentRaces());
		Hibernate.initialize(instance.getStudent().getStudentTelephones());
		Hibernate.initialize(instance.getStudent().getStudentContactRelationships());

		Hibernate.initialize(instance.getStudent().getStudentEnrollments());
		instance.getStudent().getStudentEnrollments().forEach(se -> {
			// Home Room Teacher
			if (se.getTeacher() != null) {
				Hibernate.initialize(se.getTeacher());
				se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			// Counselor
			if (se.getStaff() != null) {
				Hibernate.initialize(se.getStaff());
				se.getStaff().getStaffIdentifiers().forEach(Hibernate::initialize);
			}

			Hibernate.initialize(se.getEntryExitCodes());
			Hibernate.initialize(se.getSchool());
			Hibernate.initialize(se.getSchool().getLea());
		});
	}

	private void initialize(List<StudentWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudent().getStudentAcademicRecords());
			Hibernate.initialize(wrapper.getStudent().getStudentAddresses());
			Hibernate.initialize(wrapper.getStudent().getStudentEmails());
			Hibernate.initialize(wrapper.getStudent().getStudentIdentifiers());
			Hibernate.initialize(wrapper.getStudent().getStudentLanguages());
			Hibernate.initialize(wrapper.getStudent().getStudentOtherNames());
			Hibernate.initialize(wrapper.getStudent().getStudentRaces());
			Hibernate.initialize(wrapper.getStudent().getStudentTelephones());
			Hibernate.initialize(wrapper.getStudent().getStudentContactRelationships());

			Hibernate.initialize(wrapper.getStudent().getStudentEnrollments());
			wrapper.getStudent().getStudentEnrollments().forEach(se -> {
				// Home Room Teacher
				if (se.getTeacher() != null) {
					Hibernate.initialize(se.getTeacher());
					se.getTeacher().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				// Counselor
				if (se.getStaff() != null) {
					Hibernate.initialize(se.getStaff());
					se.getStaff().getStaffIdentifiers().forEach(Hibernate::initialize);
				}

				Hibernate.initialize(se.getEntryExitCodes());
				Hibernate.initialize(se.getSchool());
				Hibernate.initialize(se.getSchool().getLea());
			});
		});
	}
}