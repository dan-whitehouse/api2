package org.ricone.api.oneroster.request.orgs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.OrgView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.component.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Orgs:OrgDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class OrgDAOImp extends BaseDAO implements OrgDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(OrgDAOImp.class);

	@Override
	public OrgView getOrg(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (OrgView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<OrgView> getAllOrgs(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(false);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		//Paging
		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllOrgs(metadata));
		}
		return (List<OrgView>) q.getResultList();
	}

	@Override
	public OrgView getSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "school"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (OrgView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<OrgView> getAllSchools(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "school"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllSchools(metadata));
		}
		return (List<OrgView>) q.getResultList();
	}

	/* Counts */

	@Override
	public int countAllOrgs(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<OrgView> from = select.from(OrgView.class);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllSchools(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<OrgView> from = select.from(OrgView.class);

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "school"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, from, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}