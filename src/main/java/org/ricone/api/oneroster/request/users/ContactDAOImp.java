package org.ricone.api.oneroster.request.users;

import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffWrapper;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class ContactDAOImp extends BaseDAO implements ContactDAO {
	@PersistenceContext private EntityManager em;
	private final String PRIMARY_KEY = "studentContactRefId";
	private final String SCHOOL_YEAR_KEY = "studentContactSchoolYear";

	@Override
	public StudentContactWrapper getContact(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<StudentContactWrapper> getAllContacts(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentContactWrapper> select = cb.createQuery(StudentContactWrapper.class);
		final Root<StudentContact> from = select.from(StudentContact.class);
		final SetJoin<StudentContact, StudentContactRelationship> studentContactRelationships = (SetJoin<StudentContact, StudentContactRelationship>) from.<StudentContact, StudentContactRelationship>join(JOIN_STUDENT_CONTACT_RELATIONSHIPS, JoinType.LEFT);
		final Join<StudentContactRelationship, Student> student = studentContactRelationships.join(JOIN_STUDENT, JoinType.LEFT);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) student.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentContactWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);

		List<StudentContactWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(StudentContactWrapper instance) {
		Hibernate.initialize(instance.getStudentContact().getStudentContactAddresses());
		Hibernate.initialize(instance.getStudentContact().getStudentContactEmails());
		Hibernate.initialize(instance.getStudentContact().getStudentContactIdentifiers());
		Hibernate.initialize(instance.getStudentContact().getStudentContactOtherNames());
		Hibernate.initialize(instance.getStudentContact().getStudentContactTelephones());
		Hibernate.initialize(instance.getStudentContact().getStudentContactRelationships());
		instance.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
	}

	private void initialize(List<StudentContactWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactAddresses());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactEmails());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactIdentifiers());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactOtherNames());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactTelephones());
			Hibernate.initialize(wrapper.getStudentContact().getStudentContactRelationships());
			wrapper.getStudentContact().getStudentContactRelationships().forEach(cr -> Hibernate.initialize(cr.getStudent()));
		});
	}
}