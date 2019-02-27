package org.ricone.api.oneroster.request.academicSessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.AcademicSessionChildrenView;
import org.ricone.api.core.model.view.AcademicSessionView;
import org.ricone.api.oneroster.component.BaseDAOTest;
import org.ricone.api.oneroster.component.ControllerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:AcademicSessions:TermDAO")
@SuppressWarnings({"unchecked", "unused"})
class AcademicSessionDAOImp extends BaseDAOTest implements AcademicSessionDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private AcademicSessionFilterer filterer;
	private Logger logger = LogManager.getLogger(AcademicSessionDAOImp.class);

	@Override
	public AcademicSessionView getAcademicSession(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

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
			return (AcademicSessionView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<AcademicSessionView> getAllAcademicSessions(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

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

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllAcademicSessions(metadata));
		}
		return (List<AcademicSessionView>) q.getResultList();
	}

	@Override
	public AcademicSessionView getCalendar(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "schoolYear"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (AcademicSessionView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<AcademicSessionView> getAllCalendars(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "schoolYear"),
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
			metadata.getPaging().setPagingHeaders(countAllCalendars(metadata));
		}
		return (List<AcademicSessionView>) q.getResultList();
	}

	@Override
	public AcademicSessionView getTerm(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "term"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (AcademicSessionView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<AcademicSessionView> getAllTerms(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<AcademicSessionView> select = cb.createQuery(AcademicSessionView.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "term"),
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
			metadata.getPaging().setPagingHeaders(countAllTerms(metadata));
		}
		return (List<AcademicSessionView>) q.getResultList();
	}

	@Override
	public int countAllAcademicSessions(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

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
	public int countAllCalendars(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "schoolYear"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllTerms(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<AcademicSessionView> from = select.from(AcademicSessionView.class);
		final SetJoin<AcademicSessionView, AcademicSessionChildrenView> children = (SetJoin<AcademicSessionView, AcademicSessionChildrenView>) from.<AcademicSessionView, AcademicSessionChildrenView>join("children", JoinType.LEFT).alias("children");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_TYPE), "term"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}