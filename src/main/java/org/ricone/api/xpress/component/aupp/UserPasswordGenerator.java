package org.ricone.api.xpress.component.aupp;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.ricone.api.core.model.*;
import org.ricone.api.xpress.util.Util;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import static org.apache.commons.text.CharacterPredicates.DIGITS;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 1.2.0
 * @since 2018-04-13
 */

@Component("XPress:AUPP:UserPasswordGenerator")
public class UserPasswordGenerator {
	private static UserPasswordGenerator instance = null;

	public static UserPasswordGenerator getInstance() {
		if (instance == null) {
			synchronized (UserPasswordGenerator.class) {
				if (instance == null) {
					instance = new UserPasswordGenerator();
				}
			}
		}
		return instance;
	}

	/*********************************** ACCESSIBLE METHODS ***********************************/
	public String getUsername(HashMap<String, String> kv, Student student, Integer increment) {
		String method = kv.get("api.aupp.username.method.student");
		String username = null;

		if ("DistrictEmail".equals(method)) {
			username = getDistrictEmail(student);
		}
		else if ("DistrictEmailNoDomain".equals(method)) {
			username = getDistrictEmail(student);
			if (StringUtils.isNotBlank(username) && StringUtils.contains(username, "@")) {
				username = StringUtils.substringBefore(username, "@");
			}
			else {
				username = null;
			}
		}
		else if ("FNI+LN".equals(method)) {
			username = getFNILN(student.getFirstName(), student.getLastName(), increment);
		}
		else if ("LN+FNI".equals(method)) {
			username = getLNFNI(student.getLastName(), student.getFirstName(), increment);
		}
		else if ("LocalId".equals(method)) {
			username = getLocalId(student);
		}
		return clean(username);
	}

	public String getUsername(HashMap<String, String> kv, Staff staff, Integer increment) {
		String method = kv.get("api.aupp.username.method.staff");
		String username = null;

		if ("DistrictEmail".equals(method)) {
			username = getDistrictEmail(staff);
		}
		else if ("DistrictEmailNoDomain".equals(method)) {
			username = getDistrictEmail(staff);
			if (StringUtils.isNotBlank(username) && StringUtils.contains(username, "@")) {
				username = StringUtils.substringBefore(username, "@");
			}
			else {
				username = null;
			}
		}
		else if ("FNI+LN".equals(method)) {
			username = getFNILN(staff.getFirstName(), staff.getLastName(), increment);
		}
		else if ("LN+FNI".equals(method)) {
			username = getLNFNI(staff.getLastName(), staff.getFirstName(), increment);
		}
		else if ("LocalId".equals(method)) {
			username = getLocalId(staff);
		}
		return clean(username);
	}

	public String getPassword(HashMap<String, String> kv, Student student, String appId) {
		String type = kv.get("api.userpass.password.type");
		String methodBasic = kv.get("api.userpass.password.method.basic");
		String methodStandard = kv.get("api.userpass.password.method.standard");
		Boolean methodStandardUnique = BooleanUtils.toBoolean(kv.get("api.userpass.password.method.standard.unique"));
		String delimiter = kv.get("api.userpass.password.delimiter");
		String districtDefinedPassword = kv.get("api.userpass.password.static");
		String password = null;

		if ("Basic".equalsIgnoreCase(type)) {
			password = basic(methodBasic, districtDefinedPassword, student);
		}
		else if ("Standard".equalsIgnoreCase(type)) {
			password = standard(methodStandard, methodStandardUnique, appId, student);
		}
		else if ("Basic+Standard".equalsIgnoreCase(type)) {
			if (Util.isGradeGreater(delimiter, student.getGradeLevelCode())) {
				password = basic(methodBasic, districtDefinedPassword, student);
			}
			else {
				password = standard(methodStandard, methodStandardUnique, appId, student);
			}
		}
		return clean(password);
	}

	public String getPassword(HashMap<String, String> kv, Staff staff, String appId) {
		String type = kv.get("api.userpass.password.type");
		String methodBasic = kv.get("api.userpass.password.method.basic");
		String methodStandard = kv.get("api.userpass.password.method.standard");
		Boolean methodStandardUnique = BooleanUtils.toBoolean(kv.get("api.userpass.password.method.standard.unique"));
		String districtDefinedPassword = kv.get("api.userpass.password.static");
		String password = null;

		if ("Basic".equalsIgnoreCase(type)) {
			password = basic(methodBasic, districtDefinedPassword, staff);
		}
		else if ("Standard".equalsIgnoreCase(type)) {
			password = standard(methodStandard, methodStandardUnique, appId, staff);
		}
		else if ("Basic+Standard".equalsIgnoreCase(type)) {
			password = standard(methodStandard, methodStandardUnique, appId, staff);
		}
		return clean(password);
	}

	/*********************************** USERNAME METHODS ***********************************/
	// District Email - Student
	private String getDistrictEmail(Student student) {
		String email = getStudentEmail(student.getStudentEmails());

		if (StringUtils.isNotEmpty(email)) {
			return email.toLowerCase();
		}
		return null;
	}

	// District Email - Staff
	private String getDistrictEmail(Staff staff) {
		String email = getStaffEmail(staff.getStaffEmails());

		if (StringUtils.isNotEmpty(email)) {
			return email.toLowerCase();
		}
		return null;
	}

	// First Name Initial + Last Name
	private String getFNILN(String firstName, String lastName, Integer increment) {
		if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
			if (increment != null) {
				return (firstName.charAt(0) + "" + lastName).toLowerCase() + "" + increment;
			}
			return (firstName.charAt(0) + "" + lastName).replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		}
		return null;
	}

	// Last Name + First Name Initial
	private String getLNFNI(String lastName, String firstName, Integer increment) {
		if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
			if (increment != null) {
				return (lastName + "" + firstName.charAt(0)).toLowerCase() + "" + increment;
			}
			return (lastName + "" + firstName.charAt(0)).replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		}
		return null;
	}

	// LocalId - Student
	private String getLocalId(Student student) {
		if (CollectionUtils.isNotEmpty(student.getStudentIdentifiers())) {
			String localId = null;
			String tmp = getStudentLocalId(student.getStudentIdentifiers());

			if (StringUtils.isNotBlank(tmp)) {
				localId = tmp;
			}
			return localId;
		}
		return null;
	}

	// LocalId - Staff
	private String getLocalId(Staff staff) {
		if (CollectionUtils.isNotEmpty(staff.getStaffIdentifiers())) {
			String localId = null;
			String tmp = getStaffLocalId(staff.getStaffIdentifiers());

			if (StringUtils.isNotBlank(tmp)) {
				localId = tmp;
			}
			return localId;
		}
		return null;
	}

	/*********************************** PASSWORD METHODS ***********************************/
	private String basic(String methodBasic, String districtDefinedPassword, Student student) {
		if ("DistrictDefined".equals(methodBasic)) {
			return getDistrictDefined(districtDefinedPassword);
		}
		else if ("FNI+LNI+YY".equals(methodBasic)) {
			return getFNILNIYY(student.getFirstName(), student.getLastName(), student.getBirthdate());
		}
		return null;
	}

	private String basic(String methodBasic, String districtDefinedPassword, Staff staff) {
		if ("DistrictDefined".equals(methodBasic)) {
			return getDistrictDefined(districtDefinedPassword);
		}
		else if ("FNI+LNI+YY".equals(methodBasic)) {
			return getFNILNIYY(staff.getFirstName(), staff.getLastName(), staff.getBirthdate());
		}
		return null;
	}

	private String standard(String methodStandard, Boolean methodStandardUnique, String appId, Student student) {
		String password = null;

		if ("FNI+LNI+LocalIdL4".equals(methodStandard)) {
			password = getFNILNILocalIdL4(student);
		}

		if (StringUtils.isNotBlank(password)) {
			if (methodStandardUnique != null && methodStandardUnique) {
				password += getHashBySize(appId, 6);
			}
		}
		return password;
	}

	private String standard(String methodStandard, Boolean methodStandardUnique, String appId, Staff staff) {
		String password = null;

		if ("FNI+LNI+LocalIdL4".equals(methodStandard)) {
			password = getFNILNILocalIdL4(staff);
		}
	
		if (StringUtils.isNotBlank(password)) {
			if (methodStandardUnique != null && methodStandardUnique) {
				password += getHashBySize(appId, 6);
			}
		}
		return password;
	}

	// Basic || Basic+Standard - First Name Initial + Last Name Initial + 2 Digit Birth Year
	private String getFNILNIYY(String firstName, String lastName, Date birthDate) {
		if (StringUtils.isNotBlank(firstName) && StringUtils.isNotBlank(lastName)) {
			String yy;

			if (birthDate != null && StringUtils.isNotBlank(birthDate.toString())) {
				yy = Util.get2DigitYear(birthDate);
			}
			else {
				RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').filteredBy(DIGITS).build();
				yy = generator.generate(2);
			}
			return (firstName.charAt(0) + "" + lastName.charAt(0) + "" + yy).toLowerCase();
		}
		return null;
	}

	// Basic || Basic+Standard - DistrictDefined
	private String getDistrictDefined(String districtDefinedPassword) {
		if (StringUtils.isNotBlank(districtDefinedPassword)) {
			return districtDefinedPassword;
		}
		return null;
	}

	// Standard - First Name Initial + Last Name Initial + LocalId's Last 4 Digits
	private String getFNILNILocalIdL4(Student student) {
		if (StringUtils.isNotBlank(student.getFirstName()) && StringUtils.isNotBlank(student.getLastName())) {
			String localId;
			String tmp = getStudentLocalId(student.getStudentIdentifiers());

			if (StringUtils.isNotBlank(tmp)) {
				localId = StringUtils.substring(tmp, tmp.length() - 4, tmp.length());
			}
			else {
				RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').filteredBy(DIGITS).build();
				localId = generator.generate(4);
			}
			return (student.getFirstName().charAt(0) + "" + student.getLastName().charAt(0) + "" + localId).toLowerCase();
		}
		return null;
	}

	// Standard - First Name Initial + Last Name Initial + LocalId's Last 4 Digits
	private String getFNILNILocalIdL4(Staff staff) {
		if (StringUtils.isNotBlank(staff.getFirstName()) && StringUtils.isNotBlank(staff.getLastName())) {
			String localId;
			String tmp = getStaffLocalId(staff.getStaffIdentifiers());

			if (StringUtils.isNotBlank(tmp)) {
				localId = StringUtils.substring(tmp, tmp.length() - 4, tmp.length());
			}
			else {
				RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').filteredBy(DIGITS).build();
				localId = generator.generate(4);
			}
			return (staff.getFirstName().charAt(0) + "" + staff.getLastName().charAt(0) + "" + localId).toLowerCase();
		}
		return null;
	}

	// Not Offical - Testing more complex generation.
	@SuppressWarnings("unused")
	private String getComplexPW(Staff staff) {
		String localId = getStaffLocalId(staff.getStaffIdentifiers());
		if (StringUtils.isNotBlank(staff.getFirstName()) && StringUtils.isNotBlank(staff.getLastName()) && StringUtils.isNotBlank(localId)) {
			String data = staff.getFirstName().charAt(0) + "" + staff.getLastName().charAt(0) + localId;
			return DigestUtils.sha256Hex(data);
		}
		return null;
	}

	/*********************************** COMMON METHODS ***********************************/
	private String getStudentLocalId(Set<StudentIdentifier> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			for (StudentIdentifier id : ids) {
				if ("District".equals(id.getIdentificationSystemCode())) {
					return id.getStudentId();
				}
			}
		}
		return null;
	}

	private String getStaffLocalId(Set<StaffIdentifier> ids) {
		if (CollectionUtils.isNotEmpty(ids)) {
			for (StaffIdentifier id : ids) {
				if ("District".equals(id.getIdentificationSystemCode())) {
					return id.getStaffId();
				}
			}
		}
		return null;
	}

	private String getStudentEmail(Set<StudentEmail> emails) {
		if (CollectionUtils.isNotEmpty(emails)) {
			for (StudentEmail e : emails) {
				if ("Organizational".equalsIgnoreCase(e.getEmailTypeCode())) {
					return e.getEmailAddress();
				}
			}
		}
		return null;
	}

	private String getStaffEmail(Set<StaffEmail> emails) {
		if (CollectionUtils.isNotEmpty(emails)) {
			for (StaffEmail e : emails) {
				if ("Organizational".equalsIgnoreCase(e.getEmailTypeCode())) {
					return e.getEmailAddress();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("SameParameterValue")
	private String getHashBySize(String unhashed, int length) {
		String hashed = DigestUtils.sha1Hex(unhashed);
		return hashed.substring(0, (length));
	}

	private String clean(String text) {
		String out = StringUtils.deleteWhitespace(text);
		if (StringUtils.isNotBlank(out)) {
			return out;
		}
		return null;
	}
}