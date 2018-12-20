package org.ricone.api.oneroster.request.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentWrapper;
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
@SuppressWarnings({"unchecked", "unused"})
public class StudentDAOImp extends BaseDAO implements StudentDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(StudentDAOImp.class);
	private final String PRIMARY_KEY = "studentRefId";
	private final String ENROLLMENT_KEY = "studentEnrollmentRefId";
	private final String SCHOOL_YEAR_KEY = "studentSchoolYear";

	@Override
	public StudentWrapper getStudent(ControllerData metadata, String refId) {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
			lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

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
	public List<StudentWrapper> getAllStudents(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StudentWrapper> select = cb.createQuery(StudentWrapper.class);
		final Root<Student> from = select.from(Student.class);
		final SetJoin<Student, StudentEnrollment> studentEnrollments = (SetJoin<Student, StudentEnrollment>) from.<Student, StudentEnrollment>join(JOIN_STUDENT_ENROLLMENTS, JoinType.LEFT);
		final SetJoin<StudentEnrollment, EntryExitCode> entryExitCodes = (SetJoin<StudentEnrollment, EntryExitCode>) studentEnrollments.<StudentEnrollment, EntryExitCode>join(JOIN_ENTRY_EXIT_CODES, JoinType.LEFT);
		final Join<StudentEnrollment, School> school = studentEnrollments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StudentWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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


		List<StudentWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StudentWrapper> getStudentsForSchool(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<StudentWrapper> getStudentsForClass(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<StudentWrapper> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId) {
		return null;
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
		instance.getStudent().getStudentContactRelationships().forEach(sc -> {
			if(sc.getStudentContact() != null) {
				Hibernate.initialize(sc.getStudentContact());
			}
		});

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
			wrapper.getStudent().getStudentContactRelationships().forEach(sc -> {
				if(sc.getStudentContact() != null) {
					Hibernate.initialize(sc.getStudentContact());
				}
			});

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