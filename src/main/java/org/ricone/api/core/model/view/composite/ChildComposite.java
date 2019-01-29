package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 1/28/2019.
 */
public class ChildComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String childId;
    private Integer childSchoolYear;

    public ChildComposite() {
    }

    public ChildComposite(String childId, Integer childSchoolYear) {
        this.childId = childId;
        this.childSchoolYear = childSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ChildComposite)) return false;

        ChildComposite that = (ChildComposite) o;

        if(childId != null ? !childId.equals(that.childId) : that.childId != null) return false;
        return childSchoolYear != null ? childSchoolYear.equals(that.childSchoolYear) : that.childSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = childId != null ? childId.hashCode() : 0;
        result = 31 * result + (childSchoolYear != null ? childSchoolYear.hashCode() : 0);
        return result;
    }
}
