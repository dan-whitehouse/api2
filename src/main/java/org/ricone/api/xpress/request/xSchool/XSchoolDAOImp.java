package org.ricone.api.xpress.request.xSchool;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
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

@Repository("XPress:XSchools:XSchoolDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XSchoolDAOImp extends BaseDAO implements XSchoolDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(XSchoolDAOImp.class);
	private final String PRIMARY_KEY = "schoolRefId";
	private final String SCHOOL_YEAR_KEY = "schoolSchoolYear";
	private final String ID_KEY = "schoolId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final String SYSTEM_CODE_STATE = "SEA";
	private final String SYSTEM_CODE_LOCAL = "LEA";

	@Override
	public SchoolWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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


		logger.debug("districts in app: " +metadata.getApplication().getApp().getId() + " - " + metadata.getApplication().getDistrictLocalIds());

		select.distinct(true);
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			SchoolWrapper instance = (SchoolWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public SchoolWrapper findById(ControllerData metadata, String id, String idType) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearById(metadata, id, idType);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(schoolIdentifiers.get(ID_KEY), id),
				cb.equal(schoolIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), getIdTypeValue(idType)),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			SchoolWrapper instance = (SchoolWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<SchoolWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) from.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByCalendarRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByCalendarRefId(metadata, refId));
		}

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join(JOIN_COURSES, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(courses.get("courseRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByCourseRefId(metadata, refId));
		}

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join(JOIN_COURSES, JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) from.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(staff.get("staffRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByStaffRefId(metadata, refId));
		}

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(student.get("studentRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByStudentRefId(metadata, refId));
		}

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<SchoolWrapper> findAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, metadata.getSchoolYear());
		}
		else {
			Integer schoolYear = greatestSchoolYearAllByContactRefId(metadata, refId);
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
			metadata.getResponse().addHeader(ControllerData.SCHOOL_YEAR_KEY, String.valueOf(schoolYear));
		}

		select.distinct(true);
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(contact.get("studentContactRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//If Paging - Set ControllerData PagingInfo Total Objects
			metadata.getPaging().setTotalObjects(countAllByContactRefId(metadata, refId));
		}

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
	public Integer greatestSchoolYearById(ControllerData metadata, String id, String idType) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(schoolIdentifiers.get(ID_KEY), id),
				cb.equal(schoolIdentifiers.get(IDENTIFICATION_SYSTEM_CODE), getIdTypeValue(idType)),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(lea.get("leaRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) from.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join(JOIN_COURSES, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(courses.get("courseRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join(JOIN_COURSES, JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(courseSections.get("courseSectionRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) from.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(staff.get("staffRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(student.get("studentRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(cb.and(cb.equal(contact.get("studentContactRefId"), refId), lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
	public int countAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) from.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByCalendarRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join(JOIN_COURSES, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
				cb.equal(courses.get("courseRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) from.<School, Course>join(JOIN_COURSES, JoinType.LEFT);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) from.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) from.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByContactRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(contact.get("studentContactRefId"), refId), 
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Initialize **/
	private void initialize(SchoolWrapper instance) {
		Hibernate.initialize(instance.getSchool().getSchoolTelephones());
		Hibernate.initialize(instance.getSchool().getSchoolGrades());
		Hibernate.initialize(instance.getSchool().getSchoolIdentifiers());
		Hibernate.initialize(instance.getSchool().getLea());
	}

	private void initialize(List<SchoolWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getSchool().getSchoolTelephones());
			Hibernate.initialize(wrapper.getSchool().getSchoolGrades());
			Hibernate.initialize(wrapper.getSchool().getSchoolIdentifiers());
			Hibernate.initialize(wrapper.getSchool().getLea());
		});
	}

	private String getIdTypeValue(String idType) {
		if(StringUtils.equalsIgnoreCase(idType, "local")) {
			return SYSTEM_CODE_LOCAL;
		}
		else if(StringUtils.equalsIgnoreCase(idType, "state")) {
			return SYSTEM_CODE_STATE;
		}
		else {
			return idType;
		}
	}
}