package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class UserComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String sourcedId;
    private Integer sourcedSchoolYear;
    private String role;

    public UserComposite() {
    }

    public UserComposite(String sourcedId, Integer sourcedSchoolYear, String role) {
        this.sourcedId = sourcedId;
        this.sourcedSchoolYear = sourcedSchoolYear;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserComposite)) return false;

        UserComposite that = (UserComposite) o;

        if(sourcedId != null ? !sourcedId.equals(that.sourcedId) : that.sourcedId != null) return false;
        if(sourcedSchoolYear != null ? !sourcedSchoolYear.equals(that.sourcedSchoolYear) : that.sourcedSchoolYear != null)
            return false;
        return role != null ? role.equals(that.role) : that.role == null;
    }

    @Override
    public int hashCode() {
        int result = sourcedId != null ? sourcedId.hashCode() : 0;
        result = 31 * result + (sourcedSchoolYear != null ? sourcedSchoolYear.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
