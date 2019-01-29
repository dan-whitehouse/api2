package org.ricone.api.oneroster.component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BaseDAO {
    protected final String DISTRICT_ID = "districtId";
    protected final String PRIMARY_KEY = "sourcedId";
    protected final String SCHOOL_YEAR_KEY = "sourcedSchoolYear";

    /* Joins - Core - Views */
    protected final String JOIN_USER_ORGS = "userOrgs";
    protected final String JOIN_USER_CLASSES = "userClasses";
    protected final String JOIN_CLASS_TERMS = "terms";
    protected final String JOIN_CLASS_USERS = "users";

    protected Predicate[] getWhereClause(ControllerData metadata, CriteriaBuilder cb, Root from, Predicate methodSpecificPredicate) {
        final List<Predicate> predicates = new ArrayList<>();
        if(metadata.getFiltering().isFiltered()) {
            predicates.add(methodSpecificPredicate);
            predicates.add(metadata.getFiltering().getFiltering(cb, from));
        }
        else {
            predicates.add(methodSpecificPredicate);
        }
        return predicates.toArray(new Predicate[0]);
    }

    protected Order getSortOrder(ControllerData metadata, CriteriaBuilder cb, Root from) {
        if(metadata.getSorting().isSorted()) {
            return metadata.getSorting().getSortOrder(cb, from);
        }
        else {
            return cb.asc(from.get(PRIMARY_KEY));
        }
    }
}