package org.ricone.api.oneroster.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.component.error.exception.InvalidDataException;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO {
    private Logger logger = LogManager.getLogger(this.getClass());
    /* Composite PK */
    protected static final String SOURCED_ID = "sourcedId";
    protected static final String SOURCED_SCHOOL_YEAR = "sourcedSchoolYear";

    /* Model Fields */
    protected static final String DISTRICT_ID = "districtId";
    protected static final String ORG = "org";
    protected static final String COURSE = "course";
    protected static final String CLASS = "clazz";
    protected static final String USER = "user";
    protected static final String ACADEMIC_SESSION = "academicSession";

    protected static final String TYPE = "type";
    protected static final String ROLE = "role";

    /* Model Field Values */
    protected static final String SCHOOL = "school";
    protected static final String SCHOOL_YEAR = "schoolYear";
    protected static final String STUDENT = "student";
    protected static final String TEACHER = "teacher";
    protected static final String TERM = "term";

    /* Join Fields */
    protected static final String AGENTS = "agents";
    protected static final String CLASSES = "classes";
    protected static final String CHILDREN = "children";
    protected static final String CONTACT = "contact";
    protected static final String IDENTIFIERS = "identifiers";
    protected static final String ORGS = "orgs";
    protected static final String TERMS = "terms";
    protected static final String USERS = "users";


    protected Predicate[] getWhereClause(RequestData requestData, CriteriaBuilder cb, BaseFilterer filterer, Predicate methodSpecificPredicate) throws InvalidFilterFieldException, InvalidDataException {
        final List<Predicate> predicates = new ArrayList<>();
        if(requestData.getFilterer().isFiltered()) {
            predicates.add(methodSpecificPredicate);
            predicates.add(requestData.getFilterer().getPredicate(cb, filterer));
        }
        else {
            predicates.add(methodSpecificPredicate);
        }
        return predicates.toArray(new Predicate[0]);
    }

    protected Order getSortOrder(RequestData requestData, CriteriaBuilder cb, Root from) {
        if(requestData.getSorter().isSorted()) {
            return requestData.getSorter().getSortOrder(cb, from);
        }
        else {
            return cb.asc(from.get(SOURCED_ID));
        }
    }
}