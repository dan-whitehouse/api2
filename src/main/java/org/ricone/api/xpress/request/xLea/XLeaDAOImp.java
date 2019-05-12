package org.ricone.api.xpress.request.xLea;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.error.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("XPress:XLeas:XLeaDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XLeaDAOImp extends BaseDAO implements XLeaDAO {
	@PersistenceContext private EntityManager em;
	private final String PRIMARY_KEY = "leaRefId";
	private final String SCHOOL_YEAR_KEY = "leaSchoolYear";
	private final String LOCAL_ID_KEY = "leaId";
	private final String STATE_ID_KEY = "leaSeaId";

	@Override
	public LeaWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(from.get(PRIMARY_KEY), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			LeaWrapper instance = (LeaWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public LeaWrapper findById(ControllerData metadata, String id, String idType) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(from.get(getIdTypeValue(idType)), id),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			LeaWrapper instance = (LeaWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<LeaWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

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
		select.select(cb.construct(LeaWrapper.class, from.get(LOCAL_ID_KEY), from));
		select.where(
				cb.and(
						schoolYearEquals,
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber() - 1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}
		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(schools.get("schoolRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllBySchoolRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllByCalendarRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(courses.get("courseRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllByCourseRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.INNER);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(courseSections.get("courseSectionRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllByRosterRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(staff.get("staffRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllByStaffRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(student.get("studentRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllByStudentRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<LeaWrapper> findAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);

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
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
				cb.and(
						schoolYearEquals,
						cb.equal(contact.get("studentContactRefId"), refId),
						from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
				)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if (metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber()-1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAllByContactRefId(metadata, refId));
		}

		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearById(ControllerData metaData, String id, String idType) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(from.get(getIdTypeValue(idType)), id),
				from.get(ControllerData.LEA_LOCAL_ID).in(metaData.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAll(ControllerData metaData) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(from.get(ControllerData.LEA_LOCAL_ID).in(metaData.getApplication().getApp().getDistrictLocalIds()));
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(schools.get("schoolRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(courses.get("courseRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.INNER);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(courseSections.get("courseSectionRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(staff.get("staffRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(student.get("studentRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(contact.get("studentContactRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metaData) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metaData.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metaData.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAll(metaData));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				from.get(ControllerData.LEA_LOCAL_ID).in(metaData.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllBySchoolRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(schools.get("schoolRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCalendarRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.LEFT);
		final SetJoin<School, SchoolCalendar> schoolCalendars = (SetJoin<School, SchoolCalendar>) schools.<School, SchoolCalendar>join(JOIN_SCHOOL_CALENDARS, JoinType.LEFT);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByCalendarRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(schoolCalendars.get("schoolCalendarRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByCourseRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByCourseRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(courses.get("courseRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByRosterRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, Course> courses = (SetJoin<School, Course>) schools.<School, Course>join(JOIN_COURSES, JoinType.INNER);
		final SetJoin<Course, CourseSection> courseSections = (SetJoin<Course, CourseSection>) courses.<Course, CourseSection>join(JOIN_COURSE_SECTIONS, JoinType.INNER);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByRosterRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(courseSections.get("courseSectionRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStaffRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StaffAssignment> staffAssignments = (SetJoin<School, StaffAssignment>) schools.<School, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.INNER);
		final Join<StaffAssignment, Staff> staff = staffAssignments.join(JOIN_STAFF, JoinType.INNER);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByStaffRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(staff.get("staffRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByStudentRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(student.get("studentRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByContactRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);
		final SetJoin<Lea, School> schools = (SetJoin<Lea, School>) from.<Lea, School>join(JOIN_SCHOOLS, JoinType.INNER);
		final SetJoin<School, StudentEnrollment> studentEnrollments = (SetJoin<School, StudentEnrollment>) schools.<School, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.INNER);
		final Join<StudentEnrollment, Student> student = studentEnrollments.join(JOIN_STUDENT, JoinType.INNER);
		final SetJoin<Student, StudentContactRelationship> studentContactRelationships = (SetJoin<Student, StudentContactRelationship>) student.<Student, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.INNER);
		final Join<StudentContactRelationship, StudentContact> contact = studentContactRelationships.join(JOIN_STUDENT_CONTACT, JoinType.INNER);

		Predicate schoolYearEquals;
		if(metadata.hasSchoolYear()) {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
		}
		else {
			schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), greatestSchoolYearAllByContactRefId(metadata, refId));
		}

		select.distinct(true);
		select.select(cb.countDistinct(from));
		select.where (
			cb.and (
				schoolYearEquals,
				cb.equal(contact.get("studentContactRefId"), refId),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Initialize **/
	private void initialize(LeaWrapper instance) {
		instance.getLea().getLeaTelephones().forEach(Hibernate::initialize);
		instance.getLea().getSchools().forEach(Hibernate::initialize);
	}

	private void initialize(List<LeaWrapper> instance) {
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});
	}

	private String getIdTypeValue(String idType) {
		if(StringUtils.equalsIgnoreCase(idType, "local")) {
			return LOCAL_ID_KEY;
		}
		else if(StringUtils.equalsIgnoreCase(idType, "state")) {
			return STATE_ID_KEY;
		}
		else {
			return idType;
		}
	}
}