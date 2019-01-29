package org.ricone.api.oneroster.request.orgs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.view.OrgView;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.component.BaseDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository("OneRoster:Orgs:OrgDAO")
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
class OrgDAOImp extends BaseDAO implements OrgDAO {
	@PersistenceContext private EntityManager em;
	private Logger logger = LogManager.getLogger(OrgDAOImp.class);
	private final String PRIMARY_KEY = "leaRefId";
	private final String SCHOOL_YEAR_KEY = "leaSchoolYear";

	@Override
	public OrgView getOrg(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get("sourceId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019")
			)
		);

		Query q = em.createQuery(select);
		try {
			OrgView instance = (OrgView) q.getSingleResult();
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<OrgView> getAllOrgs(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);
		final Join<OrgView, Lea> lea = from.join(JOIN_LEA, JoinType.LEFT);

		//Method Specific Predicate
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(lea.get(SCHOOL_YEAR_KEY), 2019),
			lea.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
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
		}

		List<OrgView> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	@Override
	public OrgView getSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OrgView> select = cb.createQuery(OrgView.class);
		final Root<OrgView> from = select.from(OrgView.class);

		select.distinct(true);
		select.select(from);
		select.where(
			cb.and(
				cb.equal(from.get("sourceId"), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
				cb.equal(from.get("type"), "school")
			)
		);

		Query q = em.createQuery(select);
		try {
			OrgView instance = (OrgView) q.getSingleResult();
			return instance;
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
			cb.equal(from.get("type"), "school")
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

		List<OrgView> instance = q.getResultList();
		return instance;
	}

	private void initialize(OrgView instance) {
		Hibernate.initialize(instance.getLea());
		instance.getLea().getSchools().forEach(Hibernate::initialize);
	}

	private void initialize(List<OrgView> instance) {
		instance.forEach(wrapper -> {
			Hibernate.initialize(wrapper.getLea());
			wrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});
	}
}