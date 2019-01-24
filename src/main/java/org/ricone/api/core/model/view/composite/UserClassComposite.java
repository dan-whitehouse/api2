package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class UserClassComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String classId;
    private Integer classSchoolYear;

    public UserClassComposite() {
    }

    public UserClassComposite(String classId, Integer classSchoolYear) {
        this.classId = classId;
        this.classSchoolYear = classSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserClassComposite)) return false;

        UserClassComposite that = (UserClassComposite) o;

        if(classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        return classSchoolYear != null ? classSchoolYear.equals(that.classSchoolYear) : that.classSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = classId != null ? classId.hashCode() : 0;
        result = 31 * result + (classSchoolYear != null ? classSchoolYear.hashCode() : 0);
        return result;
    }
}
