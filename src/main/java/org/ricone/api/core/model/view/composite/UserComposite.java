package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class UserComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String sourceId;
    private Integer sourceSchoolYear;
    private String role;

    public UserComposite() {
    }

    public UserComposite(String sourceId, Integer sourceSchoolYear, String role) {
        this.sourceId = sourceId;
        this.sourceSchoolYear = sourceSchoolYear;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserComposite)) return false;

        UserComposite that = (UserComposite) o;

        if(sourceId != null ? !sourceId.equals(that.sourceId) : that.sourceId != null) return false;
        if(sourceSchoolYear != null ? !sourceSchoolYear.equals(that.sourceSchoolYear) : that.sourceSchoolYear != null)
            return false;
        return role != null ? role.equals(that.role) : that.role == null;
    }

    @Override
    public int hashCode() {
        int result = sourceId != null ? sourceId.hashCode() : 0;
        result = 31 * result + (sourceSchoolYear != null ? sourceSchoolYear.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
