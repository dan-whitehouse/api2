package org.ricone.api.oneroster.request.enrollments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StaffCourseSectionWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Enrollments:TeacherDAO")
@SuppressWarnings({"unchecked", "unused"})
class TeacherDAOImp extends BaseDAO implements TeacherDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(TeacherDAOImp.class);
	private final String PRIMARY_KEY = "staffCourseSectionRefId";
	private final String SCHOOL_YEAR_KEY = "staffCourseSectionSchoolYear";

	@Override
	public StaffCourseSectionWrapper getEnrollment(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<StaffCourseSectionWrapper> getAllEnrollments(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<StaffCourseSectionWrapper> select = cb.createQuery(StaffCourseSectionWrapper.class);
		final Root<StaffCourseSection> from = select.from(StaffCourseSection.class);
		final Join<StaffCourseSection, Staff> staff = from.join(JOIN_STAFF, JoinType.LEFT);
		final SetJoin<Staff, StaffAssignment> staffAssignments = (SetJoin<Staff, StaffAssignment>) staff.<Staff, StaffAssignment>join(JOIN_STAFF_ASSIGNMENTS, JoinType.LEFT);
		final Join<StaffAssignment, School> school = staffAssignments.join(JOIN_SCHOOL, JoinType.LEFT);
		final Join<School, Lea> lea = school.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(StaffCourseSectionWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<StaffCourseSectionWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public List<StaffCourseSectionWrapper> getEnrollmentsForSchool(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<StaffCourseSectionWrapper> getEnrollmentsForClassInSchool(ControllerData metadata, String refId) {
		return null;
	}

	/** Initialize **/
	private void initialize(StaffCourseSectionWrapper instance) {
		Hibernate.initialize(instance.getStaffCourseSection().getStaff());
		Hibernate.initialize(instance.getStaffCourseSection().getCourseSection());
	}

	private void initialize(List<StaffCourseSectionWrapper> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getStaffCourseSection().getStaff());
			Hibernate.initialize(wrapper.getStaffCourseSection().getCourseSection());
		});
	}
}