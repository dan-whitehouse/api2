package org.ricone.api.xpress.dao;

import org.hibernate.Hibernate;
import org.ricone.api.core.model.Lea;
import org.ricone.api.core.model.LeaTelephone;
import org.ricone.api.core.model.School;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.controller.ControllerData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings({"unchecked", "unused", "RedundantTypeArguments"})
public class XLeaDAOImp extends BaseDAO implements XLeaDAO {
    @PersistenceContext private EntityManager em;
    private final String PRIMARY_KEY = "leaRefId";
    private final String SCHOOL_YEAR_KEY = "leaSchoolYear";
    private final String LOCAL_ID_KEY = "leaId";
    private final String BEDS_ID_KEY = "leaSeaId";

    @Override
    public LeaWrapper findByRefId(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public LeaWrapper findByLocalId(ControllerData metadata, String localId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAll(ControllerData metadata) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<LeaWrapper> select = cb.createQuery(LeaWrapper.class);
        final Root<Lea> from = select.from(Lea.class);
        final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

        Predicate schoolYearEquals;
        if(metadata.hasSchoolYear()) {
            schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), metadata.getSchoolYear());
            metadata.getResponse().addHeader("SchoolYear", metadata.getSchoolYear());
        }
        else {
            Integer schoolYear = greatestSchoolYearForAll(metadata);
            schoolYearEquals = cb.equal(from.get(SCHOOL_YEAR_KEY), schoolYear);
            metadata.getResponse().addHeader("SchoolYear", String.valueOf(schoolYear));
        }

        select.distinct(true);
        select.select(cb.construct(LeaWrapper.class, from.get(LOCAL_ID_KEY), from));
        select.where(
            cb.and(
                schoolYearEquals,
                from.get(ControllerData.LEA_LOCAL_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
            )
        );
        select.orderBy(cb.asc(from.get(PRIMARY_KEY)));

        Query q = em.createQuery(select);
        if(metadata.getPaging().isPaged()) {
            q.setFirstResult(metadata.getPaging().getPageNumber() * metadata.getPaging().getPageSize());
            q.setMaxResults(metadata.getPaging().getPageSize());
        }
        List<LeaWrapper> instance = q.getResultList();
        initialize(instance);
        return instance;
    }

    @Override
    public List<LeaWrapper> findAllBySchool(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAllByCalendar(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAllByCourse(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAllByRoster(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAllByStaff(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAllByStudent(ControllerData metadata, String refId) {
        return null;
    }

    @Override
    public List<LeaWrapper> findAllByContact(ControllerData metadata, String refId) {
        return new ArrayList<LeaWrapper>();
    }

    @Override
    public Integer greatestSchoolYearForAll(ControllerData metaData) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Integer> select = cb.createQuery(Integer.class);
        final Root<Lea> from = select.from(Lea.class);
        final SetJoin<Lea, LeaTelephone> leaTelephones = (SetJoin<Lea, LeaTelephone>) from.<Lea, LeaTelephone>join(JOIN_LEA_TELEPHONES, JoinType.LEFT);

        select.distinct(true);
        select.select(cb.greatest(from.<Integer>get(SCHOOL_YEAR_KEY)));
        select.where(
                from.get(ControllerData.LEA_LOCAL_ID).in(metaData.getApplication().getApp().getDistrictLocalIds())
        );

        return em.createQuery(select).getSingleResult();
    }

    @Override
    public Integer greatestSchoolYearForAllBySchool(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer greatestSchoolYearForAllByCalendar(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer greatestSchoolYearForAllByCourse(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer greatestSchoolYearForAllByRoster(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer greatestSchoolYearForAllByStaff(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer greatestSchoolYearForAllByStudent(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer greatestSchoolYearForAllByContact(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAll(ControllerData metaData) {
        return null;
    }

    @Override
    public Integer countAllBySchool(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAllByCalendar(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAllByCourse(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAllByRoster(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAllByStaff(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAllByStudent(ControllerData metaData, String refId) {
        return null;
    }

    @Override
    public Integer countAllByContact(ControllerData metaData, String refId) {
        return null;
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