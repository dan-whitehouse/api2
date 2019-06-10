package org.ricone.api.xpress.request.xContact;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
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

@Repository("XPress:XContacts:XContactDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XContactDAOImp extends BaseDAO implements XContactDAO {
	@PersistenceContext private EntityManager em;
	private final Logger logger = LogManager.getLogger(XContactDAOImp.class);
	private final String PRIMARY_KEY = "studentContactRefId";
	private final String SCHOOL_YEAR_KEY = "studentContactSchoolYear";

	@Override
	public StudentContactWrapper findByRefId(ControllerData metadata, String refId) throws NotFoundException {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				schoolYearEquals,
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			StudentContactWrapper instance = (StudentContactWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<StudentContactWrapper> findAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<StudentContactWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<StudentContactWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<StudentContactWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentContactWrapper> findAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<StudentContactWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public Integer greatestSchoolYearByRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds()));

		return em.createQuery(select).getSingleResult();
	}

	@Override
	public Integer greatestSchoolYearAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

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
	public Integer greatestSchoolYearAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
		select.where(
			cb.and(
				cb.equal(student.get("studentRefId"), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult();
	}

	@Override
	public int countAll(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByLeaRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllBySchoolRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllByStudentRefId(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
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
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getDistrictLocalIds())
			)
		);
		return em.createQuery(select).getSingleResult().intValue();
	}

	/** Initialize **/
	private void initialize(StudentContactWrapper instance) {
		Hibernate.initialize(instance.getStudentContact().getStudentContactAddresses());
		Hibernate.initialize(instance.getStudentContact().getStudentContactEmails());
		Hibernate.initialize(instance.getStudentContact().getStudentContactIdentifiers());
		Hibernate.initialize(instance.getStudentContact().getStudentContactOtherNames());
		Hibernate.initialize(instance.getStudentContact().getStudentContactTelephones());
		//FIXME: AT the moment I am getting an EntityNotFoundException, so I turned off the mapping for this.
		/*Hibernate.initialize(instance.getStudentContact().getStudentContactRelationships());
		instance.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));*/
	}

	private void initialize(List<StudentContactWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());
			//FIXME: AT the moment I am getting an EntityNotFoundException, so I turned off the mapping for this.
			/*Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));*/
		});
	}
}