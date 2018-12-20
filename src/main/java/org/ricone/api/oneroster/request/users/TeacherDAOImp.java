package org.ricone.api.oneroster.request.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffWrapper;
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
public class TeacherDAOImp extends BaseDAO implements TeacherDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(StudentDAOImp.class);
	private final String PRIMARY_KEY = "staffRefId";
	private final String SCHOOL_YEAR_KEY = "staffSchoolYear";

	@Override
	public StaffWrapper getTeacher(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join(JOIN_STAFF_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join(JOIN_STAFF_EMAILS, JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, School> school = staffAssignments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				cb.equal(from.get(PRIMARY_KEY), refId),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			StaffWrapper instance = (StaffWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<StaffWrapper> getAllTeachers(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StaffWrapper> select = cb.createQuery(StaffWrapper.class);
		final Root<Staff> from = select.from(Staff.class);
		final SetJoin<Staff, StaffIdentifier> staffIdentifiers = (SetJoin<Staff, StaffIdentifier>) from.<Staff, StaffIdentifier>join(JOIN_STAFF_IDENTIFIERS, JoinType.LEFT);
		final SetJoin<Staff, StaffEmail> staffEmails = (SetJoin<Staff, StaffEmail>) from.<Staff, StaffEmail>join(JOIN_STAFF_EMAILS, JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) from.<Staff, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, School> school = staffAssignments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);

		List<StaffWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StaffWrapper> getTeachersForSchool(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<StaffWrapper> getTeachersForClass(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<StaffWrapper> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId) {
		return null;
	}

	/** Initialize **/
	private void initialize(StaffWrapper instance) {
		Hibernate.initialize(instance.getStaff().getStaffAssignments());
		instance.getStaff().getStaffAssignments().forEach(o -> {
			Hibernate.initialize(o.getSchool());
			Hibernate.initialize(o.getSchool().getLea());
		});
		Hibernate.initialize(instance.getStaff().getStaffEmails());
		Hibernate.initialize(instance.getStaff().getStaffIdentifiers());
	}

	private void initialize(List<StaffWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaff().getStaffAssignments());
			wrapper.getStaff().getStaffAssignments().forEach(o -> {
				Hibernate.initialize(o.getSchool());
				Hibernate.initialize(o.getSchool().getLea());
			});
			Hibernate.initialize(wrapper.getStaff().getStaffEmails());
			Hibernate.initialize(wrapper.getStaff().getStaffIdentifiers());
		});
	}
}