package org.ricone.api.oneroster.request.enrollments2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.view.EnrollmentView;
import org.ricone.api.core.model.wrapper.StudentCourseSectionWrapper;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.component.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("OneRoster:Enrollments2:StudentDAO")
@SuppressWarnings({"unchecked", "unused"})
class EnrollmentDAOImp extends BaseDAO implements EnrollmentDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(EnrollmentDAOImp.class);
	private final String PRIMARY_KEY = "userClassId";
	private final String SCHOOL_YEAR_KEY = "userClassSchoolYear";

	@Override
	public EnrollmentView getEnrollment(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<EnrollmentView> getAllEnrollments(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<EnrollmentView> select = cb.createQuery(EnrollmentView.class);
		final Root<EnrollmentView> from = select.from(EnrollmentView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
				//lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}

		List<EnrollmentView> instance = q.getResultList();
		//initialize(instance);
		return instance;
	}

	@Override
	public List<EnrollmentView> getEnrollmentsForSchool(ControllerData metadata, String refId) {
		return null;
	}

	@Override
	public List<EnrollmentView> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) {
		return null;
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