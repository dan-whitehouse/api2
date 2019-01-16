package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class UserAgentComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String agentId;
    private Integer agentSchoolYear;

    public UserAgentComposite() {
    }

    public UserAgentComposite(String agentId, Integer agentSchoolYear) {
        this.agentId = agentId;
        this.agentSchoolYear = agentSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof UserAgentComposite)) return false;

        UserAgentComposite that = (UserAgentComposite) o;

        if(agentId != null ? !agentId.equals(that.agentId) : that.agentId != null) return false;
        return agentSchoolYear != null ? agentSchoolYear.equals(that.agentSchoolYear) : that.agentSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = agentId != null ? agentId.hashCode() : 0;
        result = 31 * result + (agentSchoolYear != null ? agentSchoolYear.hashCode() : 0);
        return result;
    }
}
