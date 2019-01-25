package org.ricone.api.core.model.view.composite;

import java.io.Serializable;

/**
 * @project: API
 * @author: Dan on 3/1/2018.
 */
public class ClassTermComposite implements Serializable {
    private static final long serialVersionUID = -6727456278122460145L;

    private String termId;
    private Integer termSchoolYear;

    public ClassTermComposite() {
    }

    public ClassTermComposite(String termId, Integer termSchoolYear) {
        this.termId = termId;
        this.termSchoolYear = termSchoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof ClassTermComposite)) return false;

        ClassTermComposite that = (ClassTermComposite) o;

        if(termId != null ? !termId.equals(that.termId) : that.termId != null) return false;
        return termSchoolYear != null ? termSchoolYear.equals(that.termSchoolYear) : that.termSchoolYear == null;
    }

    @Override
    public int hashCode() {
        int result = termId != null ? termId.hashCode() : 0;
        result = 31 * result + (termSchoolYear != null ? termSchoolYear.hashCode() : 0);
        return result;
    }
}
