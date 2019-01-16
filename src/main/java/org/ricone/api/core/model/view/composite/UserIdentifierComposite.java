package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class UserIdentifierComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String identifierId;
    private Integer identifierSchoolYear;

    public UserIdentifierComposite() {
    }

    public UserIdentifierComposite(String identifierId, Integer identifierSchoolYear) {
        this.identifierId = identifierId;
        this.identifierSchoolYear = identifierSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserIdentifierComposite)) return false;

        UserIdentifierComposite that = (UserIdentifierComposite) o;

        if(identifierId != null ? !identifierId.equals(that.identifierId) : that.identifierId != null) return false;
        return identifierSchoolYear != null ? identifierSchoolYear.equals(that.identifierSchoolYear) : that.identifierSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = identifierId != null ? identifierId.hashCode() : 0;
        result = 31 * result + (identifierSchoolYear != null ? identifierSchoolYear.hashCode() : 0);
        return result;
    }
}
