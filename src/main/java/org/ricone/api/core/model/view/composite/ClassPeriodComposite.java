package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class ClassPeriodComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String periodId;
    private Integer periodSchoolYear;

    public ClassPeriodComposite() {
    }

    public ClassPeriodComposite(String periodId, Integer periodSchoolYear) {
        this.periodId = periodId;
        this.periodSchoolYear = periodSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ClassPeriodComposite)) return false;

        ClassPeriodComposite that = (ClassPeriodComposite) o;

        if(periodId != null ? !periodId.equals(that.periodId) : that.periodId != null) return false;
        return periodSchoolYear != null ? periodSchoolYear.equals(that.periodSchoolYear) : that.periodSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = periodId != null ? periodId.hashCode() : 0;
        result = 31 * result + (periodSchoolYear != null ? periodSchoolYear.hashCode() : 0);
        return result;
    }
}
