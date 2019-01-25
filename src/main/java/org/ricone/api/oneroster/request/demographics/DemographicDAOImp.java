package org.ricone.api.oneroster.request.demographics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.DemographicView;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.ControllerData;
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

@Repository("OneRoster:Demographics:DemographicDAO")
@SuppressWarnings({"unchecked", "unused"})
class DemographicDAOImp extends BaseDAO implements DemographicDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(DemographicDAOImp.class);

	@Override
	public DemographicView getDemographic(ControllerData metadata, String refId) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<DemographicView> select = cb.createQuery(DemographicView.class);
		final Root<DemographicView> from = select.from(DemographicView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (DemographicView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<DemographicView> getAllDemographics(ControllerData metadata) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<DemographicView> select = cb.createQuery(DemographicView.class);
		final Root<DemographicView> from = select.from(DemographicView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
		}
		return (List<DemographicView>) q.getResultList();
	}
}