package org.ricone.api.oneroster.request.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.view.*;
import org.ricone.api.oneroster.component.BaseDAO;
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

@Repository("OneRoster:Classes:ClassDAO")
@SuppressWarnings({"unchecked", "unused"})
class ClassDAOImp extends BaseDAOTest implements ClassDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private ClassFilterer filterer;
	private Logger logger = LogManager.getLogger(ClassDAOImp.class);

	@Override
	public ClassView getClass(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(PRIMARY_KEY), refId),
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		try {
			return (ClassView) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<ClassView> getAllClasses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

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
			metadata.getPaging().setPagingHeaders(countAllClasses(metadata));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public List<ClassView> getClassesForTerm(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(terms.get(FIELD_TERM_ID), refId),
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
			metadata.getPaging().setPagingHeaders(countClassesForTerm(metadata, refId));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public List<ClassView> getClassesForCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_COURSE_ID), refId),
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
			metadata.getPaging().setPagingHeaders(countClassesForCourse(metadata, refId));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public List<ClassView> getClassesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ORG_ID), refId),
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
			metadata.getPaging().setPagingHeaders(countClassesForSchool(metadata, refId));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public List<ClassView> getClassesForStudent(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");
		final SetJoin<ClassView, ClassUserView> users = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT).alias("users");;

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(users.get(FIELD_USER_ID), refId),
			cb.equal(users.get(FIELD_ROLE), "student"),
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
			metadata.getPaging().setPagingHeaders(countClassesForStudent(metadata, refId));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public List<ClassView> getClassesForTeacher(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");
		final SetJoin<ClassView, ClassUserView> users = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT).alias("users");;

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates

		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(users.get(FIELD_USER_ID), refId),
			cb.equal(users.get(FIELD_ROLE), "teacher"),
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
			metadata.getPaging().setPagingHeaders(countClassesForTeacher(metadata, refId));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public List<ClassView> getClassesForUser(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ClassView> select = cb.createQuery(ClassView.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");
		final SetJoin<ClassView, ClassUserView> users = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT).alias("users");;

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(users.get(FIELD_USER_ID), refId),
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
			metadata.getPaging().setPagingHeaders(countClassesForUser(metadata, refId));
		}
		return (List<ClassView>) q.getResultList();
	}

	@Override
	public int countAllClasses(ControllerData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

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
	public int countClassesForTerm(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassTermView> classTerms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join(JOIN_CLASS_TERMS, JoinType.LEFT);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(classTerms.get(FIELD_TERM_ID), refId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForCourse(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_COURSE_ID), refId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForSchool(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(from.get(FIELD_ORG_ID), refId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForStudent(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");
		final SetJoin<ClassView, ClassUserView> users = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT).alias("users");;

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(users.get(FIELD_USER_ID), refId),
			cb.equal(users.get(FIELD_ROLE), "student"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForTeacher(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");
		final SetJoin<ClassView, ClassUserView> users = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT).alias("users");;

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(users.get(FIELD_USER_ID), refId),
			cb.equal(users.get(FIELD_ROLE), "teacher"),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countClassesForUser(ControllerData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<ClassView> from = select.from(ClassView.class);
		final SetJoin<ClassView, ClassGradeView> grades = (SetJoin<ClassView, ClassGradeView>) from.<ClassView, ClassGradeView>join("grades", JoinType.LEFT).alias("grades");
		final SetJoin<ClassView, ClassSubjectView> subjects = (SetJoin<ClassView, ClassSubjectView>) from.<ClassView, ClassSubjectView>join("subjects", JoinType.LEFT).alias("subjects");
		final SetJoin<ClassView, ClassPeriodView> periods = (SetJoin<ClassView, ClassPeriodView>) from.<ClassView, ClassPeriodView>join("periods", JoinType.LEFT).alias("periods");
		final SetJoin<ClassView, ClassTermView> terms = (SetJoin<ClassView, ClassTermView>) from.<ClassView, ClassTermView>join("terms", JoinType.LEFT).alias("terms");
		final SetJoin<ClassView, ClassUserView> users = (SetJoin<ClassView, ClassUserView>) from.<ClassView, ClassUserView>join(JOIN_CLASS_USERS, JoinType.LEFT).alias("users");;

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, grades, subjects, periods, terms);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SCHOOL_YEAR_KEY), 2019),
			cb.equal(users.get(FIELD_USER_ID), refId),
			from.get(FIELD_DISTRICT_ID).in(metadata.getApplication().getApp().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}