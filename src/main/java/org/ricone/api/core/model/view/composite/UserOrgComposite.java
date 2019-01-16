package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class UserOrgComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String orgId;
    private Integer orgSchoolYear;

    public UserOrgComposite() {
    }

    public UserOrgComposite(String orgId, Integer orgSchoolYear) {
        this.orgId = orgId;
        this.orgSchoolYear = orgSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserOrgComposite)) return false;

        UserOrgComposite that = (UserOrgComposite) o;

        if(orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        return orgSchoolYear != null ? orgSchoolYear.equals(that.orgSchoolYear) : that.orgSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = orgId != null ? orgId.hashCode() : 0;
        result = 31 * result + (orgSchoolYear != null ? orgSchoolYear.hashCode() : 0);
        return result;
    }
}
