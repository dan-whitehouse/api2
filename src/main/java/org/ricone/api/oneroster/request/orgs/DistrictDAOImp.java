package org.ricone.api.oneroster.request.orgs;

import org.hibernate.Hibernate;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.component.BaseDAO;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.request.xLea.XLeaDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class DistrictDAOImp extends BaseDAO implements DistrictDAO {
	@PersistenceContext private EntityManager em;
	private final String PRIMARY_KEY = "leaRefId";
	private final String SCHOOL_YEAR_KEY = "leaSchoolYear";
	private final String LOCAL_ID_KEY = "leaId";

	@Override
	public LeaWrapper getDistrict(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get(ControllerData.LEA_LOCAL_ID), from));
		select.where(
			cb.and(
				cb.equal(from.get(PRIMARY_KEY), refId),
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);

		Query q = em.createQuery(select);
		try {
			LeaWrapper instance = (LeaWrapper) q.getSingleResult();
			initialize(instance);
			return instance;
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<LeaWrapper> getAllDistricts(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
		final Root<Lea> from = select.from(Lea.class);
		final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

		select.distinct(true);
		select.select(cb.construct(LeaWrapper.class, from.get(LOCAL_ID_KEY), from));
		select.where(
			cb.and(
				cb.equal(from.get(SCHOOL_YEAR_KEY), "2019"),
				from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
			)
		);
		select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

		Query q = em.createQuery(select);
		/*if(metadata.getPaging().isPaged()) {
			q.setFirstResult((metadata.getPaging().getPageNumber() - 1) * metadata.getPaging().getPageSize());
			q.setMaxResults(metadata.getPaging().getPageSize());

			//Set ControllerData Paging Total Objects - Headers will no be included
			metadata.getPaging().setTotalObjects(countAll(metadata));
		}*/
		List<LeaWrapper> instance = q.getResultList();
		initialize(instance);
		return instance;
	}

	/** Initialize **/
	private void initialize(LeaWrapper instance) {
		instance.getLea().getLeaTelephones().forEach(Hibernate::initialize);
		instance.getLea().getSchools().forEach(Hibernate::initialize);
	}

	private void initialize(List<LeaWrapper> instance) {
		instance.forEach(leaWrapper -> {
			leaWrapper.getLea().getLeaTelephones().forEach(Hibernate::initialize);
			leaWrapper.getLea().getSchools().forEach(Hibernate::initialize);
		});
	}
}