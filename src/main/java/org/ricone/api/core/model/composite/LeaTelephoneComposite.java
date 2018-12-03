package org.ricone.api.core.model.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class LeaTelephoneComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    protected String leaTelephoneRefId;
    protected Integer leaTelephoneSchoolYear;

    public LeaTelephoneComposite() {
    }

    public LeaTelephoneComposite(String leaTelephoneRefId, Integer leaTelephoneSchoolYear) {
        this.leaTelephoneRefId = leaTelephoneRefId;
        this.leaTelephoneSchoolYear = leaTelephoneSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof LeaTelephoneComposite)) return false;

        LeaTelephoneComposite that = (LeaTelephoneComposite) o;

        if(leaTelephoneRefId != null ? !leaTelephoneRefId.equals(that.leaTelephoneRefId) : that.leaTelephoneRefId != null)
            return false;
        return leaTelephoneSchoolYear != null ? leaTelephoneSchoolYear.equals(that.leaTelephoneSchoolYear) : that.leaTelephoneSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = leaTelephoneRefId != null ? leaTelephoneRefId.hashCode() : 0;
        result = 31 * result + (leaTelephoneSchoolYear != null ? leaTelephoneSchoolYear.hashCode() : 0);
        return result;
    }
}
