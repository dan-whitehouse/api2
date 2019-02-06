package org.ricone.api.oneroster.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.oneroster.error.exception.InvalidFilterFieldException;
import org.ricone.api.oneroster.request.courses.CourseFilterer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAOTest {
    private Logger logger = LogManager.getLogger(this.getClass());
    protected static final String PRIMARY_KEY = "sourcedId";
    protected static final String SCHOOL_YEAR_KEY = "sourcedSchoolYear";
    protected static final String FIELD_DISTRICT_ID = "districtId";
    protected static final String FIELD_ORG_ID = "orgId";
    protected static final String FIELD_ORG_TYPE = "orgType";
    protected static final String FIELD_COURSE_ID = "courseId";
    protected static final String FIELD_CLASS_ID = "classId";
    protected static final String FIELD_TERM_ID = "termId";
    protected static final String FIELD_USER_ID = "userId";
    protected static final String FIELD_TYPE = "type";
    protected static final String FIELD_ROLE = "role";

    /* Joins - Core - Views */
    protected static final String JOIN_USER_ORGS = "userOrgs";
    protected static final String JOIN_USER_CLASSES = "userClasses";
    protected static final String JOIN_CLASS_TERMS = "terms";
    protected static final String JOIN_CLASS_USERS = "users";

    protected Predicate[] getWhereClause(ControllerData metadata, CriteriaBuilder cb, CourseFilterer filterer, Predicate methodSpecificPredicate) throws InvalidFilterFieldException {
        final List<Predicate> predicates = new ArrayList<>();
        if(metadata.getFiltering().isFiltered()) {
            predicates.add(methodSpecificPredicate);
            predicates.add(metadata.getFilterableTest().getFiltering(cb, filterer)); //TODO -- Test
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