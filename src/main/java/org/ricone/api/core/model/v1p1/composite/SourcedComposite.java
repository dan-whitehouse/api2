package org.ricone.api.core.model.v1p1.composite;

import java.io.Serializable;

public class SourcedComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    protected String sourcedId;
    protected Integer sourcedSchoolYear;

    public SourcedComposite() {
    }

    public SourcedComposite(String sourcedId, Integer sourcedSchoolYear) {
        this.sourcedId = sourcedId;
        this.sourcedSchoolYear = sourcedSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof SourcedComposite)) return false;

        SourcedComposite that = (SourcedComposite) o;

        if(sourcedId != null ? !sourcedId.equals(that.sourcedId) : that.sourcedId != null) return false;
        return sourcedSchoolYear != null ? sourcedSchoolYear.equals(that.sourcedSchoolYear) : that.sourcedSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = sourcedId != null ? sourcedId.hashCode() : 0;
        result = 31 * result + (sourcedSchoolYear != null ? sourcedSchoolYear.hashCode() : 0);
        return result;
    }
}
