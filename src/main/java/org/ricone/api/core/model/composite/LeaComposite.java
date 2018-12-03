package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class LeaComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    protected String leaRefId;
    protected Integer leaSchoolYear;

    public LeaComposite() {
    }

    public LeaComposite(String leaRefId, Integer leaSchoolYear) {
        this.leaRefId = leaRefId;
        this.leaSchoolYear = leaSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof LeaComposite)) return false;

        LeaComposite that = (LeaComposite) o;

        if(leaRefId != null ? !leaRefId.equals(that.leaRefId) : that.leaRefId != null) return false;
        return leaSchoolYear != null ? leaSchoolYear.equals(that.leaSchoolYear) : that.leaSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = leaRefId != null ? leaRefId.hashCode() : 0;
        result = 31 * result + (leaSchoolYear != null ? leaSchoolYear.hashCode() : 0);
        return result;
    }
}
