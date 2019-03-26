package org.ricone.api.oneroster.request.courses;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.RequestData;
import org.ricone.api.oneroster.model.Course;
import org.ricone.api.oneroster.model.CourseResponse;
import org.ricone.api.oneroster.model.CoursesResponse;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-30
 */

@Component("OneRoster:Courses:CourseFieldSelector")
public class CourseFieldSelector extends BaseFieldSelector<Course> {
	public CourseFieldSelector() {
		super(Course.class);
	}

	CoursesResponse apply(CoursesResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			for (Course c : response.getData()) {
				selectBaseFields(c, metadata);
			}

			if (CollectionUtils.isEmpty(response.getData())) {
				return null;
			}
		}
		return response;
	}

	CourseResponse apply(CourseResponse response, RequestData metadata) {
		if(metadata.getFieldSelector().hasFieldSelection() && metadata.getFieldSelector().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getData(), metadata);
			if (response.getData() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Course instance, RequestData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "title")) {
			instance.setTitle(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "schoolYear")) {
			instance.setSchoolYear(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "courseCode")) {
			instance.setCourseCode(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "grades")) {
			instance.setGrades(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "subjects")) {
			instance.setSubjects(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "subjectCodes")) {
			instance.setSubjectCodes(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelector().getFields(), "org")) {
			instance.setOrg(null);
		}
	}
}