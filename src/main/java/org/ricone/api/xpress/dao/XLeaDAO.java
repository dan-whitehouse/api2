package org.ricone.api.xpress.dao;

import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.xpress.controller.ControllerData;

import java.util.List;

public interface XLeaDAO {
	/* Find */
	LeaWrapper findByRefId(ControllerData metadata, String refId);
	LeaWrapper findByLocalId(ControllerData metadata, String localId);
	List<LeaWrapper> findAll(ControllerData metadata);
	List<LeaWrapper> findAllBySchool(ControllerData metadata, String refId);
	List<LeaWrapper> findAllByCalendar(ControllerData metadata, String refId);
	List<LeaWrapper> findAllByCourse(ControllerData metadata, String refId);
	List<LeaWrapper> findAllByRoster(ControllerData metadata, String refId);
	List<LeaWrapper> findAllByStaff(ControllerData metadata, String refId);
	List<LeaWrapper> findAllByStudent(ControllerData metadata, String refId);
	List<LeaWrapper> findAllByContact(ControllerData metadata, String refId);

	/* Greatest School Year */
	Integer greatestSchoolYearForAll(ControllerData metaData);
	Integer greatestSchoolYearForAllBySchool(ControllerData metaData, String refId);
	Integer greatestSchoolYearForAllByCalendar(ControllerData metaData, String refId);
	Integer greatestSchoolYearForAllByCourse(ControllerData metaData, String refId);
	Integer greatestSchoolYearForAllByRoster(ControllerData metaData, String refId);
	Integer greatestSchoolYearForAllByStaff(ControllerData metaData, String refId);
	Integer greatestSchoolYearForAllByStudent(ControllerData metaData, String refId);
	Integer greatestSchoolYearForAllByContact(ControllerData metaData, String refId);

	/* Counts */
	Integer countAll(ControllerData metaData);
	Integer countAllBySchool(ControllerData metaData, String refId);
	Integer countAllByCalendar(ControllerData metaData, String refId);
	Integer countAllByCourse(ControllerData metaData, String refId);
	Integer countAllByRoster(ControllerData metaData, String refId);
	Integer countAllByStaff(ControllerData metaData, String refId);
	Integer countAllByStudent(ControllerData metaData, String refId);
	Integer countAllByContact(ControllerData metaData, String refId);
}