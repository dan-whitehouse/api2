package org.ricone.api.oneroster.v1p1.request.orgs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.v1p1.QOrg;
import org.ricone.api.core.model.v1p1.QOrgChild;
import org.ricone.api.oneroster.component.BaseDAOTest;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.request.orgs.OrgFilterer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster2:Orgs:OrgDAO")
@SuppressWarnings({"unchecked", "unused"})
class OrgDAOImp extends BaseDAOTest implements OrgDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private OrgFilterer filterer;
	private Logger logger = LogManager.getLogger(OrgDAOImp.class);

	@Override
	public QOrg getOrg(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

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
			return (QOrg) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QOrg> getAllOrgs(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		//Paging
		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllOrgs(metadata));
		}
		return (List<QOrg>) q.getResultList();
	}

	@Override
	public QOrg getSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "school"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QOrg) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QOrg> getAllSchools(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "school"),
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
			metadata.getPaging().setPagingHeaders(countAllSchools(metadata));
		}
		return (List<QOrg>) q.getResultList();
	}

	/* Counts */

	@Override
	public int countAllOrgs(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

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
	public int countAllSchools(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "school"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}