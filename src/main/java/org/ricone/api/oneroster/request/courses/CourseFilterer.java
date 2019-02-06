package org.ricone.api.oneroster.request.courses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component("OneRoster:Courses:CourseFilterer")
public class CourseFilterer {
	private Logger logger = LogManager.getLogger(this.getClass());
	private Root from;
	private List<Join> joinList = new ArrayList<>();


	public CourseFilterer() {
	}

	void addJoins(Root<?> from, Join... joins) {
		this.from = from;
		Collections.addAll(joinList, joins);
	}

	private Path getPath(String field) {
		switch(field) {
			case "sourcedId": return from.get(field);
			case "status": return from.get(field);
			case "title": return from.get(field);
			case "schoolYear.href": return null;
			case "schoolYear.sourcedId": return from.get("academicSessionId");
			case "schoolYear.type": return null;
			case "courseCode": return from.get(field);
			case "grades": return getJoin("grades").get("gradeLevel");
			case "subjects": return getJoin("subjects").get("subject");
			case "org.href": return null;
			case "org.sourcedId": return from.get("academicSessionId");
			case "org.type": return null;
			case "subjectCodes": return getJoin("subjects").get("subjectCode");
			default: break;
		}
		return null;
	}

	private Join getJoin(String field) {
		return joinList.stream().filter(join -> field.equalsIgnoreCase(join.getAlias())).findFirst().get();
	}
}
