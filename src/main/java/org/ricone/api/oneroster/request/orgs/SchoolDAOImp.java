package org.ricone.api.oneroster.request.orgs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Orgs:SchoolDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class SchoolDAOImp extends BaseDAO implements SchoolDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(SchoolDAOImp.class);
	private final String PRIMARY_KEY = "schoolRefId";
	private final String SCHOOL_YEAR_KEY = "schoolSchoolYear";
	private final String LOCAL_ID_KEY = "schoolId";
	private final String IDENTIFICATION_SYSTEM_CODE = "identificationSystemCode";
	private final String SYSTEM_CODE_BEDS = "SEA";
	private final String SYSTEM_CODE_LEA = "LEA";

	@Override
	public SchoolWrapper getSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
	public List<SchoolWrapper> getAllSchools(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<SchoolWrapper> select = cb.createQuery(SchoolWrapper.class);
		final Root<School> from = select.from(School.class);
		final SetJoin<School, SchoolGrade> schoolGrades = (SetJoin<School, SchoolGrade>) from.<School, SchoolGrade>join(JOIN_SCHOOL_GRADES, JoinType.LEFT);
		final SetJoin<School, SchoolTelephone> schoolTelephones = (SetJoin<School, SchoolTelephone>) from.<School, SchoolTelephone>join(JOIN_SCHOOL_TELEPHONES, JoinType.LEFT);
		final SetJoin<School, SchoolIdentifier> schoolIdentifiers = (SetJoin<School, SchoolIdentifier>) from.<School, SchoolIdentifier>join(JOIN_SCHOOL_IDENTIFIERS, JoinType.LEFT);
		final Join<School, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(SchoolWrapper.class, lea.get(ControllerData.LEA_LOCAL_ID), from));
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

		List<SchoolWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
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
}