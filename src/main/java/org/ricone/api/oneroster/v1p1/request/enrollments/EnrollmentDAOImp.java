package org.ricone.api.oneroster.v1p1.request.enrollments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QEnrollment;
import org.ricone.api.oneroster.component.BaseDAOTest;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("OneRoster2:Enrollments:EnrollmentDAO")
@SuppressWarnings({"unchecked", "unused"})
class EnrollmentDAOImp extends BaseDAOTest implements EnrollmentDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private EnrollmentFilterer filterer;
	private Logger logger = LogManager.getLogger(EnrollmentDAOImp.class);

	@Override
	public QEnrollment getEnrollment(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QEnrollment> select = cb.createQuery(QEnrollment.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QEnrollment) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QEnrollment> getAllEnrollments(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QEnrollment> select = cb.createQuery(QEnrollment.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllEnrollments(metadata));
		}
		return (List<QEnrollment>) q.getResultList();
	}

	@Override
	public List<QEnrollment> getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QEnrollment> select = cb.createQuery(QEnrollment.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ORG_ID), refId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countEnrollmentsForSchool(metadata, refId));
		}
		return (List<QEnrollment>) q.getResultList();
	}

	@Override
	public List<QEnrollment> getEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QEnrollment> select = cb.createQuery(QEnrollment.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ORG_ID), schoolId),
			cb.equal(from.get(FIELD_CLASS_ID), classId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countEnrollmentsForClassInSchool(metadata, schoolId, classId));
		}
		return (List<QEnrollment>) q.getResultList();
	}

	@Override
	public int countAllEnrollments(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ORG_ID), refId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countEnrollmentsForClassInSchool(ControllerData metadata, String schoolId, String classId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QEnrollment> from = select.from(QEnrollment.class);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ORG_ID), schoolId),
			cb.equal(from.get(FIELD_CLASS_ID), classId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}