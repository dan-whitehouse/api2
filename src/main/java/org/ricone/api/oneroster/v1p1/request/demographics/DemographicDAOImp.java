package org.ricone.api.oneroster.v1p1.request.demographics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QDemographic;
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

@Repository("OneRoster2:Demographics:DemographicDAO")
@SuppressWarnings({"unchecked", "unused"})
class DemographicDAOImp extends BaseDAOTest implements DemographicDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private DemographicFilterer filterer;
	private Logger logger = LogManager.getLogger(DemographicDAOImp.class);

	@Override
	public QDemographic getDemographic(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QDemographic> select = cb.createQuery(QDemographic.class);
		final Root<QDemographic> from = select.from(QDemographic.class);

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
			return (QDemographic) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QDemographic> getAllDemographics(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QDemographic> select = cb.createQuery(QDemographic.class);
		final Root<QDemographic> from = select.from(QDemographic.class);

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
			metadata.getPaging().setPagingHeaders(countAllDemographics(metadata));
		}
		return (List<QDemographic>) q.getResultList();
	}

	@Override
	public int countAllDemographics(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QDemographic> from = select.from(QDemographic.class);

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
}