package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class EnrollmentComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String userClassId;
    private Integer userClassSchoolYear;

    public EnrollmentComposite() {
    }

    public EnrollmentComposite(String userClassId, Integer userClassSchoolYear) {
        this.userClassId = userClassId;
        this.userClassSchoolYear = userClassSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof EnrollmentComposite)) return false;

        EnrollmentComposite that = (EnrollmentComposite) o;

        if(userClassId != null ? !userClassId.equals(that.userClassId) : that.userClassId != null) return false;
        return userClassSchoolYear != null ? userClassSchoolYear.equals(that.userClassSchoolYear) : that.userClassSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = userClassId != null ? userClassId.hashCode() : 0;
        result = 31 * result + (userClassSchoolYear != null ? userClassSchoolYear.hashCode() : 0);
        return result;
    }
}
